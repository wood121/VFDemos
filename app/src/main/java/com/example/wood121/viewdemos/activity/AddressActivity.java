package com.example.wood121.viewdemos.activity;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.baidu.location.BDAbstractLocationListener;
import com.baidu.location.BDLocation;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.baidu.mapapi.SDKInitializer;
import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.MapStatus;
import com.baidu.mapapi.map.MapStatusUpdate;
import com.baidu.mapapi.map.MapStatusUpdateFactory;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.model.LatLng;
import com.baidu.mapapi.search.core.PoiInfo;
import com.baidu.mapapi.search.core.SearchResult;
import com.baidu.mapapi.search.geocode.GeoCodeResult;
import com.baidu.mapapi.search.geocode.GeoCoder;
import com.baidu.mapapi.search.geocode.OnGetGeoCoderResultListener;
import com.baidu.mapapi.search.geocode.ReverseGeoCodeOption;
import com.baidu.mapapi.search.geocode.ReverseGeoCodeResult;
import com.baidu.mapapi.search.poi.OnGetPoiSearchResultListener;
import com.baidu.mapapi.search.poi.PoiDetailResult;
import com.baidu.mapapi.search.poi.PoiIndoorResult;
import com.baidu.mapapi.search.poi.PoiNearbySearchOption;
import com.baidu.mapapi.search.poi.PoiResult;
import com.baidu.mapapi.search.poi.PoiSearch;
import com.baidu.mapapi.search.poi.PoiSortType;
import com.example.wood121.viewdemos.BaseActivity;
import com.example.wood121.viewdemos.R;
import com.example.wood121.viewdemos.adapter.LocationSearchAdapter;
import com.example.wood121.viewdemos.util.ToastUtil;
import com.example.wood121.viewdemos.widget.ClearEditText;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class AddressActivity extends BaseActivity {


    private static final int POI_ALL = 0;
    private static final int POI_COMMUNITY = 1;
    private static final int POI_OFFICE_BUILDING = 2;
    private static final int POI_SCHOOL = 3;
    @BindView(R.id.tv_city)
    TextView tvCity;
    @BindView(R.id.clear_et)
    ClearEditText clearEt;
    @BindView(R.id.tv_cancel)
    TextView tvCancel;
    @BindView(R.id.mapview)
    MapView mapview;
    @BindView(R.id.iv_location)
    ImageView ivLocation;
    @BindView(R.id.tabLayout)
    TabLayout tabLayout;
    @BindView(R.id.recycleView_show)
    RecyclerView recycleViewShow;
    @BindView(R.id.ll_map)
    LinearLayout llMap;
    @BindView(R.id.recycleView_search)
    RecyclerView recycleViewSearch;
    private LocationClient locationClient;

    public BDLocation mLocation;    //具有定位信息
    private BaiduMap baiduMap;
    private MapStatusUpdate mapStatusUpdate;
    private LatLng latLngStatus;
    private LatLng latLng;
    private LocationSearchAdapter locationSearchAdapter;
    private String keyword;
    private PoiSearch poiSearch;
    private GeoCoder geoCoder;

    @Override
    protected void initData(Bundle savedInstanceState) {
        SDKInitializer.initialize(getApplicationContext());
        mLocation = new BDLocation();
    }

    @Override
    protected int initPageLayoutId() {
        return R.layout.activity_address;
    }

    @Override
    protected void initPageViewListener() {
        baiduMap = mapview.getMap();

        //设置Tab
        final String titles[] = {"全部", "小区", "写字楼", "学校"};
        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                int tabTag = (int) tab.getTag();
                ToastUtil.showToast(AddressActivity.this, "tabTag:" + tabTag);
                switch (tabTag) {
                    case POI_ALL:
                        keyword = titles[POI_ALL];
                        getAllPois();   //获取全部的数据
                        break;
                    case POI_COMMUNITY:
                        keyword = titles[POI_COMMUNITY];
                        getSpecPois();
                        break;
                    case POI_OFFICE_BUILDING:
                        keyword = titles[POI_OFFICE_BUILDING];
                        getSpecPois();
                        break;
                    case POI_SCHOOL:
                        keyword = titles[POI_SCHOOL];
                        getSpecPois();
                        break;
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
        tabLayout.addTab(tabLayout.newTab().setText(titles[0]).setTag(POI_ALL));
        tabLayout.addTab(tabLayout.newTab().setText(titles[1]).setTag(POI_COMMUNITY));
        tabLayout.addTab(tabLayout.newTab().setText(titles[2]).setTag(POI_OFFICE_BUILDING));
        tabLayout.addTab(tabLayout.newTab().setText(titles[3]).setTag(POI_SCHOOL));

        //设置布局样式
        recycleViewShow.setLayoutManager(new LinearLayoutManager(this));
        locationSearchAdapter = new LocationSearchAdapter(this);
        recycleViewShow.setAdapter(locationSearchAdapter);
        locationSearchAdapter.setOnItemClickListener(new LocationSearchAdapter.onItemClickListener() {
            @Override
            public void onItemClick(View view, String address) {
                ToastUtil.showToast(AddressActivity.this, address);
            }
        });

        startLocate();
    }

    private void startLocate() {
        locationClient = new LocationClient(getApplicationContext());

        LocationClientOption clientOption = new LocationClientOption();
        clientOption.setLocationMode(LocationClientOption.LocationMode.Hight_Accuracy);
        clientOption.setCoorType("bd0911");
        //下部的设置都是可选的
        clientOption.setScanSpan(1000);
        clientOption.setOpenGps(true);
        clientOption.setWifiCacheTimeOut(5 * 60 * 1000);    //wifi进行扫描确认
        //需要进行打开的设置
        clientOption.setIsNeedAddress(true);    //是否需要具体的地址
        clientOption.setIsNeedLocationPoiList(true);    //是否需要周边POI
        clientOption.setIsNeedLocationDescribe(true);
        locationClient.setLocOption(clientOption);

        locationClient.registerLocationListener(new BDAbstractLocationListener() {

            @Override
            public void onReceiveLocation(BDLocation bdLocation) {
                //bdLocation有全部
                Log.e("url", "bdLocation:" + bdLocation.getLongitude()
                        + ":" + bdLocation.getLatitude()
                        + " address:" + bdLocation.getAddrStr());
                if (bdLocation != null) {
                    mLocation = bdLocation;
                    initMap();
                    locationClient.stop();
                } else {
                    ToastUtil.showToast(AddressActivity.this, "定位失败");
                }
            }
        });

        locationClient.start();
    }

    private void initMap() {
        //开启定位图层
        baiduMap.setMyLocationEnabled(true);

        //显示定位城市
        tvCity.setText(mLocation.getCity());

        //定位处于中心位置
        latLng = new LatLng(mLocation.getLatitude(), mLocation.getLongitude());
        mapStatusUpdate = MapStatusUpdateFactory.newLatLngZoom(latLng, 16.0f);
        baiduMap.animateMapStatus(mapStatusUpdate);

        //获取poi信息
        getAllPois();
//        List<Poi> poiList = mLocation.getPoiList();

        //页面位置刷新
        baiduMap.setOnMapStatusChangeListener(new BaiduMap.OnMapStatusChangeListener() {
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
                latLng = mapStatus.target;
                if ("全部".equals(keyword)) {
                    getAllPois();
                } else {
                    getSpecPois();
                }
            }
        });
    }

    //获取：小区、写字楼、学校
    private void getSpecPois() {
        if (poiSearch == null) {
            poiSearch = PoiSearch.newInstance();
        }
        poiSearch.setOnGetPoiSearchResultListener(new OnGetPoiSearchResultListener() {
            @Override
            public void onGetPoiResult(PoiResult poiResult) {
                List<PoiInfo> poiInfos = poiResult.getAllPoi();
                if (poiInfos != null) {
                    locationSearchAdapter.updateRecView(poiInfos);
                } else {
                    ToastUtil.showToast(AddressActivity.this, "没有数据");
                }
            }

            @Override
            public void onGetPoiDetailResult(PoiDetailResult poiDetailResult) {

            }

            @Override
            public void onGetPoiIndoorResult(PoiIndoorResult poiIndoorResult) {

            }
        });

        //分的区域内检索内容
//        LatLngBounds latLngBounds = new LatLngBounds.Builder().include(latLng).include(latLng).build();
//        poiSearch.searchInBound(new PoiBoundSearchOption().bound(latLngBounds).keyword(keyword).pageCapacity(10));

        poiSearch.searchNearby(new PoiNearbySearchOption()
                .keyword(keyword)
                .sortType(PoiSortType.distance_from_near_to_far)
                .location(latLng)
                .radius(5000)   //单位是米
                .pageNum(10));
    }

    //获取全部的poi信息
    private void getAllPois() {
        //创建查询对象
        if (geoCoder == null) {
            geoCoder = GeoCoder.newInstance();
        }
        //设置监听
        geoCoder.setOnGetGeoCodeResultListener(new OnGetGeoCoderResultListener() {
            @Override
            public void onGetGeoCodeResult(GeoCodeResult geoCodeResult) {

            }

            @Override
            public void onGetReverseGeoCodeResult(ReverseGeoCodeResult reverseGeoCodeResult) {
                if (reverseGeoCodeResult == null || reverseGeoCodeResult.error != SearchResult.ERRORNO.NO_ERROR) {
                    //没有找到检索结果
                    Log.e("url", "没有找到检索结果");
                } else {
                    List<PoiInfo> poiList = reverseGeoCodeResult.getPoiList();
                    //刷新页面
                    if (poiList != null) {
                        Log.e("url", "poiList:" + poiList.get(0).address);
                        locationSearchAdapter.updateRecView(poiList);
                    }
                }
            }
        });
        //发起逆地理查询
        if (latLng != null) {
            geoCoder.reverseGeoCode(new ReverseGeoCodeOption().location(latLng));
        }
    }

    @OnClick({R.id.tv_city, R.id.clear_et, R.id.tv_cancel, R.id.iv_location})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_city:
                ToastUtil.showToast(this, "进行定位的城市");
                break;
            case R.id.clear_et:
                break;
            case R.id.tv_cancel:
                break;
            case R.id.iv_location:
                baiduMap.animateMapStatus(mapStatusUpdate);
                ToastUtil.showToast(this, "回到自己的位置");
                break;
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        mapview.onPause();
    }

    @Override
    protected void onResume() {
        super.onResume();
        mapview.onResume();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        locationClient.stop();
        mapview.onDestroy();
    }
}