package com.example.wood121.viewdemos.net.mqtt.api;

/**
 * mqtt消息api
 * Created by xuchao on 2016/6/1.
 */
public class MqttApi {

//    private static MqttApi instance = null;
//    private MqttService api;
//
//    private void createapi() {
//        api = RetrofitManager.getRetrofit(new OkHttpTag(this.getClass().getName())).create(MqttService.class);
//    }
//
//    public static MqttApi getInstance() {
//        if (instance == null) {
//            synchronized (MqttApi.class) {
//                if (instance == null) {
//                    instance = new MqttApi();
//                }
//            }
//        }
//        instance.createapi();
//        return instance;
//    }
//
//    /**
//     * 获取mqtt配置
//     *
//     */
//    public  Observable<MqttConnBean>  getConfig() {
//        HetParamsMerge params = new HetParamsMerge();
//        String path = "/v1/mqtt/getConfig";
//        String host= AppGlobalHost.getHost();
//        if (host.contains("dp")||host.contains("50")){
//            path="/v1/app/open/mqtt/getConfig";
//        }
//        //clife和开放平台接口不一致
//        if(!AppNetDelegate.IS_OPEN_PLATFORM){
//            path="/v1/device/mqtt/getConfig";
//        }
//
//        return api.getConfig(path, params
//                .setPath(path)
//                .isHttps(true)
//                .sign(true)
//                .accessToken(true)
//                .timeStamp(true)
//                .getParams()).compose(RxSchedulers._io_main());
//    }
//
//
//    /**
//     * 订阅emq推送的内容
//     * isPush        是否推送   0是需要。1是取消
//     * transferType
//     */
//    public  Observable<String>  registerTransferRequire(String isPush,String transferType ) {
//        HetParamsMerge params = new HetParamsMerge();
//        //String path = "/v1/device/mqtt/transferRequire";
//        String path = "/v1/mqtt/transferRequire";
//        String host= AppGlobalHost.getHost();
//        if (host.contains("dp")||host.contains("50")){
//           //path="/v1/app/open/device/mqtt/transferRequire";
//            path="/v1/app/open/mqtt/transferRequire";
//        }
//        params.add("isPush",isPush);
//        params.add("transferType",transferType);
//        //clife和开放平台接口不一致
//        if(!AppNetDelegate.IS_OPEN_PLATFORM){
//            path="/v1/device/mqtt/transferRequire";;
//        }
//        return api.registerTransferRequire(path, params
//                .setPath(path)
//                .isHttps(true)
//                .sign(true)
//                .accessToken(true)
//                .timeStamp(true)
//                .getParams()).compose(RxSchedulers._io_main());
//    }

}
