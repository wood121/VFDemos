package com.example.wood121.viewdemos.activity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;

import com.baidu.location.BDAbstractLocationListener;
import com.baidu.location.BDLocation;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.baidu.location.Poi;
import com.baidu.mapapi.SDKInitializer;
import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.BitmapDescriptor;
import com.baidu.mapapi.map.BitmapDescriptorFactory;
import com.baidu.mapapi.map.InfoWindow;
import com.baidu.mapapi.map.MapPoi;
import com.baidu.mapapi.map.MapStatus;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.map.MarkerOptions;
import com.baidu.mapapi.map.MyLocationConfiguration;
import com.baidu.mapapi.map.MyLocationData;
import com.baidu.mapapi.map.OverlayOptions;
import com.baidu.mapapi.map.TextOptions;
import com.baidu.mapapi.map.UiSettings;
import com.baidu.mapapi.model.LatLng;
import com.baidu.mapapi.search.core.SearchResult;
import com.baidu.mapapi.search.geocode.GeoCodeOption;
import com.baidu.mapapi.search.geocode.GeoCodeResult;
import com.baidu.mapapi.search.geocode.GeoCoder;
import com.baidu.mapapi.search.geocode.OnGetGeoCoderResultListener;
import com.baidu.mapapi.search.geocode.ReverseGeoCodeOption;
import com.baidu.mapapi.search.geocode.ReverseGeoCodeResult;
import com.baidu.mapapi.search.poi.OnGetPoiSearchResultListener;
import com.baidu.mapapi.search.poi.PoiAddrInfo;
import com.baidu.mapapi.search.poi.PoiCitySearchOption;
import com.baidu.mapapi.search.poi.PoiDetailResult;
import com.baidu.mapapi.search.poi.PoiDetailSearchOption;
import com.baidu.mapapi.search.poi.PoiIndoorResult;
import com.baidu.mapapi.search.poi.PoiResult;
import com.baidu.mapapi.search.poi.PoiSearch;
import com.baidu.mapapi.search.route.BikingRouteLine;
import com.baidu.mapapi.search.route.BikingRouteResult;
import com.baidu.mapapi.search.route.DrivingRouteResult;
import com.baidu.mapapi.search.route.IndoorRouteResult;
import com.baidu.mapapi.search.route.MassTransitRouteResult;
import com.baidu.mapapi.search.route.OnGetRoutePlanResultListener;
import com.baidu.mapapi.search.route.PlanNode;
import com.baidu.mapapi.search.route.RoutePlanSearch;
import com.baidu.mapapi.search.route.TransitRouteResult;
import com.baidu.mapapi.search.route.WalkingRoutePlanOption;
import com.baidu.mapapi.search.route.WalkingRouteResult;
import com.baidu.mapapi.search.sug.OnGetSuggestionResultListener;
import com.baidu.mapapi.search.sug.SuggestionResult;
import com.baidu.mapapi.search.sug.SuggestionSearch;
import com.baidu.mapapi.search.sug.SuggestionSearchOption;
import com.example.wood121.viewdemos.R;
import com.example.wood121.viewdemos.overlayutil.PoiOverlay;
import com.example.wood121.viewdemos.util.ToastUtil;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class BaiduMapActivity extends AppCompatActivity {

    @BindView(R.id.bmapView)
    MapView bmapView;
    @BindView(R.id.toolbar)
    Toolbar toolbar;

    private BaiduMap baiduMap;
    private LocationClient locationClient;
    /**
     * mCurrentMode = LocationMode.FOLLOWING;//定位跟随态
     * mCurrentMode = LocationMode.NORMAL;   //默认为 LocationMode.NORMAL 普通态
     * mCurrentMode = LocationMode.COMPASS;  //定位罗盘态
     */
    private MyLocationConfiguration.LocationMode currentMode = MyLocationConfiguration.LocationMode.FOLLOWING;
    private double latitude;
    private double longitude;
    private float radius;
    private SDKReceiver mReceiver;
    private boolean enable;
    private PoiSearch poiSearch;
    private SuggestionSearch suggestionSearch;
    private GeoCoder geoCoder;
    private RoutePlanSearch routePlanSearch;

    //事件的广播
    public class SDKReceiver extends BroadcastReceiver {
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            if (action.equals(SDKInitializer.SDK_BROADTCAST_ACTION_STRING_PERMISSION_CHECK_ERROR)) {
                // key 验证失败，相应处理

            }
        }
    }

    //地图状态改变相关接口的
    BaiduMap.OnMapStatusChangeListener statusListener = new BaiduMap.OnMapStatusChangeListener() {
        @Override
        public void onMapStatusChangeStart(MapStatus mapStatus) {

        }

        @Override
        public void onMapStatusChangeStart(MapStatus mapStatus, int i) {

        }

        @Override
        public void onMapStatusChange(MapStatus mapStatus) {

        }

        @Override
        public void onMapStatusChangeFinish(MapStatus mapStatus) {

        }
    };

    BaiduMap.OnMapClickListener clickListener = new BaiduMap.OnMapClickListener() {
        @Override
        public void onMapClick(LatLng latLng) {

        }

        @Override
        public boolean onMapPoiClick(MapPoi mapPoi) {
            return false;
        }
    };

    BaiduMap.OnMapDoubleClickListener doubleClickListener = new BaiduMap.OnMapDoubleClickListener() {
        @Override
        public void onMapDoubleClick(LatLng latLng) {

        }
    };

    //地图加载完成接口
    BaiduMap.OnMapLoadedCallback loadedCallback = new BaiduMap.OnMapLoadedCallback() {
        @Override
        public void onMapLoaded() {

        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //在使用SDK各组件之前初始化context信息，传入ApplicationContext
        //注意该方法要再setContentView方法之前实现
        SDKInitializer.initialize(getApplicationContext());
        setContentView(R.layout.activity_baidu_map);
        ButterKnife.bind(this);

        locationSets(); //进行定位
        initView(); //显示涂层
        initBroadcastReceiver();
    }

    private void initBroadcastReceiver() {
        IntentFilter iFilter = new IntentFilter();
        iFilter.addAction(SDKInitializer.SDK_BROADTCAST_ACTION_STRING_PERMISSION_CHECK_ERROR);
        iFilter.addAction(SDKInitializer.SDK_BROADCAST_ACTION_STRING_NETWORK_ERROR);
        mReceiver = new SDKReceiver();
        registerReceiver(mReceiver, iFilter);
    }

    @Override
    protected void onResume() {
        super.onResume();
        bmapView.onResume();
    }

    //获取经纬度
    private void locationSets() {
        //定位对象
        locationClient = new LocationClient(getApplicationContext());

        //BDAbstractLocationListener:最新版本新增的定位信息回调abstract类，开发者可以选择性实现其中的功能
        this.locationClient.registerLocationListener(new BDAbstractLocationListener() {
            @Override
            public void onConnectHotSpotMessage(String s, int i) {
                super.onConnectHotSpotMessage(s, i);
            }

            @Override
            public void onReceiveLocation(BDLocation bdLocation) {

                //经纬度的信息
                //获取纬度信息
                latitude = bdLocation.getLatitude();
                longitude = bdLocation.getLongitude();   //经度信息
                radius = bdLocation.getRadius();
                String coorType = bdLocation.getCoorType(); //获取经纬度坐标类型
                int locType = bdLocation.getLocType();
                Log.e("url", "latitude:" + latitude
                        + "longitude:" + longitude
                        + " locType" + locType
                        + " coorType" + coorType);

                //具体的地址信息
                String addrStr = bdLocation.getAddrStr();   //详细地址
                String country = bdLocation.getCountry();
                String province = bdLocation.getProvince();
                String city = bdLocation.getCity();
                String district = bdLocation.getDistrict();
                String street = bdLocation.getStreet();
                Log.e("url", "addrStr:" + addrStr +
                        " country:" + country +
                        " province" + province +
                        " city" + city +
                        " district" + district +
                        " street" + street);

                //地址描述
                String locationDescribe = bdLocation.getLocationDescribe();
                Log.e("url", "locationDescribe:" + locationDescribe);

                //周边poi
                List<Poi> poiList = bdLocation.getPoiList();
                String things = "";
                for (int i = 0; i < poiList.size(); i++) {
                    Poi poi = poiList.get(i);
                    things = things + poi.getName();
                }
                Log.e("url", "poiList.size():" + poiList.size() + " things" + things);

                //国内外位置判断
                int locationWhere = bdLocation.getLocationWhere();
                int locationWhereInCn = BDLocation.LOCATION_WHERE_IN_CN;
                int locationWhereOutCn = BDLocation.LOCATION_WHERE_OUT_CN;
                Log.e("url", "locationWhereInCn:" + (locationWhereInCn == locationWhereInCn) +
                        " locationWhereOutCn:" + (locationWhereOutCn == locationWhere));

                obtainLocations();  //显示定位
                markLocations();  //点标记绘制
                textMarkLocations();    //文字覆盖
                infoWindowShow();   //覆盖物

                locationClient.stop();
            }
        });

        //定位sdk参数
        LocationClientOption clientOption = new LocationClientOption();
        clientOption.setLocationMode(LocationClientOption.LocationMode.Hight_Accuracy);
        //LocationMode.Hight_Accuracy：高精度；
        //LocationMode. Battery_Saving：低功耗；
        //LocationMode. Device_Sensors：仅使用设备；

        clientOption.setCoorType("bd0911");
        //gcj02：国测局坐标；
        //bd09ll：百度经纬度坐标；
        //bd09：百度墨卡托坐标；
        //海外地区定位，无需设置坐标类型，统一返回wgs84类型坐标

        clientOption.setScanSpan(1000);
        //可选，设置发起定位请求的间隔，int类型，单位ms
        //如果设置为0，则代表单次定位，即仅定位一次，默认为0
        //如果设置非0，需设置1000ms以上才有效

        clientOption.setOpenGps(true);
        //可选，设置是否使用gps，默认false
        //使用高精度和仅用设备两种定位模式的，参数必须设置为true

        clientOption.setLocationNotify(true);
        //可选，设置是否当GPS有效时按照1S/1次频率输出GPS结果，默认false

        clientOption.setIgnoreKillProcess(false);
        //可选，定位SDK内部是一个service，并放到了独立进程。
        //设置是否在stop的时候杀死这个进程，默认（建议）不杀死，即setIgnoreKillProcess(true)

        clientOption.SetIgnoreCacheException(false);
        //可选，设置是否收集Crash信息，默认收集，即参数为false

        clientOption.setWifiCacheTimeOut(5 * 60 * 1000);
        //可选，7.2版本新增能力
        //如果设置了该接口，首次启动定位时，会先判断当前WiFi是否超出有效期，若超出有效期，会先重新扫描WiFi，然后定位

        clientOption.setEnableSimulateGps(false);
        //可选，设置是否需要过滤GPS仿真结果，默认需要，即参数为false

        this.locationClient.setLocOption(clientOption);
        //mLocationClient为第二步初始化过的LocationClient对象
        //需将配置好的LocationClientOption对象，通过setLocOption方法传递给LocationClient对象使用
        //更多LocationClientOption的配置，请参照类参考中LocationClientOption类的详细说明

        clientOption.setIsNeedAddress(true);//默认是false，只有开发者需要的情况下需要进行设置（是否需要具体地址）
        clientOption.setIsNeedLocationDescribe(true);   //（是否需要地址描述）
        clientOption.setIsNeedLocationPoiList(true);    //（是否需要周边poi）

        this.locationClient.start(); //发起定位信息获取
    }

    //信息弹框
    private void infoWindowShow() {
        //创建InfoWindow展示的view
        Button button = new Button(getApplicationContext());
        button.setBackgroundResource(R.mipmap.circle_bar);

        //定义用于显示该InfoWindow的坐标点
        LatLng pt = new LatLng(latitude, longitude);

        //创建InfoWindow , 传入 view， 地理坐标， y 轴偏移量
        InfoWindow mInfoWindow = new InfoWindow(button, pt, -47);

        //显示InfoWindow
        baiduMap.showInfoWindow(mInfoWindow);
    }

    //文字覆盖
    private void textMarkLocations() {
        LatLng llText = new LatLng(latitude + 0.005, longitude);
        //构建文字Option对象，用于在地图上添加文字
        OverlayOptions textOption = new TextOptions()
                .bgColor(0xAAFFFF00)
                .fontSize(24)
                .fontColor(0xFFFF00FF)
                .text("百度地图SDK")
                .rotate(-30)
                .position(llText);

        //在地图上添加该文字对象并显示
        baiduMap.addOverlay(textOption);
    }

    //添加marker、标记某个位置
    private void markLocations() {
        LatLng point = new LatLng(latitude + 0.001, longitude);
        BitmapDescriptor bitmapDescriptor = BitmapDescriptorFactory.fromResource(R.mipmap.icon_openmap_mark);
        MarkerOptions markerOptions = new MarkerOptions()
                .position(point)
                .icon(bitmapDescriptor)
                .title("wood121添加的标题");

        baiduMap.addOverlay(markerOptions);
    }

    //显示定位
    private void obtainLocations() {
        //开启定位图层
        baiduMap.setMyLocationEnabled(true);

        //设定地位数据
        MyLocationData myLocationData = new MyLocationData.Builder()
                .accuracy(radius)
                .direction(100)
                .latitude(latitude)
                .longitude(longitude)
                .build();
        //radius:0.0 latitude:0.0 longitude:0.0???这种地址显示位置在几内亚湾
        Log.e("url", "radius:" + radius + " latitude:" + latitude + " longitude:" + longitude);
        baiduMap.setMyLocationData(myLocationData);

        //设置定位图层的配置
        BitmapDescriptor descriptor = BitmapDescriptorFactory.fromResource(R.mipmap.icon_openmap_focuse_mark);
        int accuracyCircleFillColor = 0xAAFFFF88;//自定义精度圈填充颜色
        int accuracyCircleStrokeColor = 0xAA00FF00;
        MyLocationConfiguration myLocationConfiguration = new MyLocationConfiguration(currentMode,
                true,
                descriptor,
                accuracyCircleFillColor,
                accuracyCircleStrokeColor);
        baiduMap.setMyLocationConfiguration(myLocationConfiguration);
    }

    //地图类型显示
    private void initView() {
        baiduMap = bmapView.getMap();
        poiSearch = PoiSearch.newInstance();
        geoCoder = GeoCoder.newInstance();
        routePlanSearch = RoutePlanSearch.newInstance();
        //标题栏
        toolbar.setTitle("百度地图");
        toolbar.setTitle("子标题");
        toolbar.setLogo(R.mipmap.circle_bar);
        setSupportActionBar(toolbar);
        toolbar.setNavigationIcon(R.mipmap.circle_gps_icon);    //最左边的导航栏图标
        //切换地图类型
        toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.action_search:    //普通地图
                        baiduMap.setMapType(BaiduMap.MAP_TYPE_NORMAL);
                        break;
                    case R.id.action_notifications: //卫星地图
                        baiduMap.setMapType(BaiduMap.MAP_TYPE_SATELLITE);
                        break;
                    case R.id.action_settings:  //交通图
                        baiduMap.setTrafficEnabled(!baiduMap.isTrafficEnabled());
                        break;
                    case R.id.action_hot:   //热力城市图
                        baiduMap.setBaiduHeatMapEnabled(!baiduMap.isBaiduHeatMapEnabled());
                        break;
                    case R.id.action_single:
                        ToastUtil.showToast(BaiduMapActivity.this, "action_offline");
                        break;
                    case R.id.action_offline:
                        ToastUtil.showToast(BaiduMapActivity.this, "action_offline");
                        break;
                    case R.id.action_gesture:   //控件与手势
                        enable = !enable;
                        settingMaps();
                        break;
                    case R.id.action_inCity:
                        checkoutPoi();
                        break;
                    case R.id.action_poiDetail:
                        getPoiDetail();
                        break;
                    case R.id.action_sug:
                        checkoutSugPoi();
                        break;
                    case R.id.action_address2LL:
                        address2LL();
                        break;
                    case R.id.action_ll2address:
                        ll2Address();
                        break;
                    case R.id.action_bike:
                        bikeWay();
                        break;
                    case R.id.action_walk:
                        walkWay();
                        break;
                    case R.id.action_bikeNai:
                        bikeNai();
                        break;
                }
                return true;
            }
        });
    }

    //骑行导航
    private void bikeNai() {
    }

    //步行路线
    private void walkWay() {
        routePlanSearch.setOnGetRoutePlanResultListener(new OnGetRoutePlanResultListener() {
            @Override
            public void onGetWalkingRouteResult(WalkingRouteResult walkingRouteResult) {
                //获取步行路线
                Log.e("url", "walkingRouteResult:" + walkingRouteResult.getSuggestAddrInfo());
                ToastUtil.showToast(BaiduMapActivity.this, "onGetWalkingRouteResult" + walkingRouteResult);
            }

            @Override
            public void onGetTransitRouteResult(TransitRouteResult transitRouteResult) {

            }

            @Override
            public void onGetMassTransitRouteResult(MassTransitRouteResult massTransitRouteResult) {

            }

            @Override
            public void onGetDrivingRouteResult(DrivingRouteResult drivingRouteResult) {
                //驾车路线
            }

            @Override
            public void onGetIndoorRouteResult(IndoorRouteResult indoorRouteResult) {

            }

            @Override
            public void onGetBikingRouteResult(BikingRouteResult bikingRouteResult) {
                //骑行路线
                List<BikingRouteLine> routeLines = bikingRouteResult.getRouteLines();
                if (routeLines != null) {
                    for (int i = 0; i < routeLines.size(); i++) {
                        Log.e("url", routeLines.get(i).getAllStep().toArray().toString());
                    }
                }
            }
        });

        PlanNode stNode = PlanNode.withCityNameAndPlaceName("深圳", "大冲商务中心");
        PlanNode enNode = PlanNode.withCityNameAndPlaceName("深圳", "深圳大学");
        routePlanSearch.walkingSearch(new WalkingRoutePlanOption().from(stNode).to(enNode));
//        routePlanSearch.bikingSearch(new BikingRoutePlanOption().from(stNode).to(enNode));
//        routePlanSearch.drivingSearch(new DrivingRoutePlanOption().from(stNode).to(enNode));
    }

    //骑行路线
    private void bikeWay() {
        ToastUtil.showToast(this, "bikeWay——没有写里面的实现");
    }

    //反地理编码
    private void ll2Address() {
        geoCoder.setOnGetGeoCodeResultListener(new OnGetGeoCoderResultListener() {
            @Override
            public void onGetGeoCodeResult(GeoCodeResult geoCodeResult) {
                if (geoCodeResult == null || geoCodeResult.error != SearchResult.ERRORNO.NO_ERROR) {
                    //没有检索到结果
                }

                //获取地理编码结果
                LatLng location = geoCodeResult.getLocation();
                Log.e("url", "location:" + location.latitude + ":" + location.longitude);
            }

            @Override
            public void onGetReverseGeoCodeResult(ReverseGeoCodeResult reverseGeoCodeResult) {
                if (reverseGeoCodeResult == null || reverseGeoCodeResult.error != SearchResult.ERRORNO.NO_ERROR) {
                    //没有找到检索结果
                }
                ReverseGeoCodeResult.AddressComponent addressDetail = reverseGeoCodeResult.getAddressDetail();
                Log.e("url", "addressDetail:" + addressDetail.province + addressDetail.district);

                //获取反向地理编码结果
            }
        });
        LatLng latLng = new LatLng(latitude, longitude);
        geoCoder.reverseGeoCode(new ReverseGeoCodeOption().location(latLng));
        ToastUtil.showToast(this, "ll2Address—反地理编码的");
    }

    //地理编码
    private void address2LL() {
        geoCoder.setOnGetGeoCodeResultListener(new OnGetGeoCoderResultListener() {
            @Override
            public void onGetGeoCodeResult(GeoCodeResult geoCodeResult) {
                if (geoCodeResult == null || geoCodeResult.error != SearchResult.ERRORNO.NO_ERROR) {
                    //没有检索到结果
                }

                //获取地理编码结果
                LatLng location = geoCodeResult.getLocation();
                Log.e("url", "location:" + location.latitude + ":" + location.longitude);
            }

            @Override
            public void onGetReverseGeoCodeResult(ReverseGeoCodeResult reverseGeoCodeResult) {
                if (reverseGeoCodeResult == null || reverseGeoCodeResult.error != SearchResult.ERRORNO.NO_ERROR) {
                    //没有找到检索结果
                }
                ReverseGeoCodeResult.AddressComponent addressDetail = reverseGeoCodeResult.getAddressDetail();
                Log.e("url", "addressDetail:" + addressDetail.province + addressDetail.district);

                //获取反向地理编码结果
            }
        });
        geoCoder.geocode(new GeoCodeOption().city("深圳").address("大冲商务中心"));
        ToastUtil.showToast(this, "address2LL—地理编码");
    }

    private void checkoutSugPoi() {
        suggestionSearch = SuggestionSearch.newInstance();
        suggestionSearch.setOnGetSuggestionResultListener(new OnGetSuggestionResultListener() {
            @Override
            public void onGetSuggestionResult(SuggestionResult suggestionResult) {
                //未找到相关结果
                if (suggestionResult == null || suggestionResult.getAllSuggestions() == null) {
                    return;
                } else {
                    List<SuggestionResult.SuggestionInfo> allSuggestions = suggestionResult.getAllSuggestions();
                    for (int i = 0; i < allSuggestions.size(); i++) {
                        SuggestionResult.SuggestionInfo suggestionInfo = allSuggestions.get(i);
                        Log.e("url", suggestionInfo.district);

                    }
                }
            }
        });
        suggestionSearch.requestSuggestion(new SuggestionSearchOption().city("深圳").keyword("腾讯"));

    }

    //poi查找的详情
    private void getPoiDetail() {
        String uid = "";
        poiSearch.searchPoiDetail(new PoiDetailSearchOption().poiUid(uid));

    }

    private class MyPoiOverlay extends PoiOverlay {

        /**
         * 构造函数
         *
         * @param baiduMap 该 PoiOverlay 引用的 BaiduMap 对象
         */
        public MyPoiOverlay(BaiduMap baiduMap) {
            super(baiduMap);
        }

        @Override
        public boolean onPoiClick(int i) {
            super.onPoiClick(i);
            return true;
        }
    }

    //周边POI检索
    private void checkoutPoi() {
        poiSearch.setOnGetPoiSearchResultListener(new OnGetPoiSearchResultListener() {
            @Override
            public void onGetPoiResult(PoiResult poiResult) {
                if (poiResult == null || poiResult.error == SearchResult.ERRORNO.RESULT_NOT_FOUND) {
                    Log.e("url", "poiResult is null");
                    return;
                }

                if (poiResult.error == SearchResult.ERRORNO.NO_ERROR) {
                    baiduMap.clear();
                    MyPoiOverlay myPoiOverlay = new MyPoiOverlay(baiduMap);
                    baiduMap.setOnMarkerClickListener(myPoiOverlay);
                    myPoiOverlay.setData(poiResult);
                    myPoiOverlay.addToMap();
                    myPoiOverlay.zoomToSpan();
                    return;
                }

                List<PoiAddrInfo> allAddr = poiResult.getAllAddr();
                if (allAddr != null) {
                    for (int i = 0; i < allAddr.size(); i++) {
                        PoiAddrInfo poiAddrInfo = allAddr.get(i);
                        Log.e("url", poiAddrInfo.address + poiAddrInfo.name);
                    }
                }
            }

            @Override
            public void onGetPoiDetailResult(PoiDetailResult poiDetailResult) {
                Log.e("url", poiDetailResult.toString());
            }

            @Override
            public void onGetPoiIndoorResult(PoiIndoorResult poiIndoorResult) {
                Log.e("url", poiIndoorResult.toString());
            }
        });


        poiSearch.searchInCity(new PoiCitySearchOption()
                .city("深圳")
                .keyword("美食")
                .pageNum(10));

        //周边搜索
//        poiSearch.searchNearby(new PoiNearbySearchOption()
//                .keyword("餐厅")
//                .sortType(PoiSortType.distance_from_near_to_far)
//                .location()
//                .radius(radius)
//                .pageNum(10));

        //在范围内搜索
//        LatLng southwest = new LatLng( 39.92235, 116.380338 );
//        LatLng northeast = new LatLng( 39.947246, 116.414977);
//
//        LatLngBounds searchbound = new LatLngBounds.Builder()
//
//                .include(southwest).include(northeast)
//
//                .build();
//        poiSearch.searchInBound(new PoiBoundSearchOption().bound(searchbound)
//                .keyword("餐厅"));

    }

    //控件与手势
    private void settingMaps() {
        //地图Logo不允许遮挡，可通过以下方法可以设置地图边界区域，来避免UI遮挡.
//        baiduMap.setViewPadding(100, 100, 100, 100);
        //指南针默认为开启状态，可以关闭显示
        UiSettings uiSettings = baiduMap.getUiSettings();
        uiSettings.setCompassEnabled(enable);

        uiSettings.setScrollGesturesEnabled(enable);  //平移、地图是否可以平移
        uiSettings.setZoomGesturesEnabled(enable);    //缩放、控制是否启用或禁用缩放手势，默认开启
        uiSettings.setOverlookingGesturesEnabled(enable); //俯视、控制是否启用或禁用俯视（3D）功能
        uiSettings.setRotateGesturesEnabled(enable);  //旋转、控制是否启用或禁用地图旋转功能，默认开启
        uiSettings.setAllGesturesEnabled(!enable);    //禁止所有手势、控制是否一并禁止所有手势，默认关闭

        //比例尺
        baiduMap.setMaxAndMinZoomLevel(100, 100);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_baidumap, menu);
        return true;
    }

    @Override
    protected void onPause() {
        super.onPause();
        bmapView.onPause();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(mReceiver);
        baiduMap.setMyLocationEnabled(false);
        poiSearch.destroy();
        suggestionSearch.destroy();
        geoCoder.destroy();
        bmapView.onDestroy();
    }
}
