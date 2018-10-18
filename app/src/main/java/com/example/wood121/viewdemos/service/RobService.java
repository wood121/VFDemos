package com.example.wood121.viewdemos.service;

import android.accessibilityservice.AccessibilityService;
import android.annotation.TargetApi;
import android.app.Notification;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.util.Log;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityNodeInfo;

import com.example.wood121.viewdemos.mine_part.EmptyActivity;
import com.example.wood121.viewdemos.util.PhoneController;
import com.example.wood121.viewdemos.util.ToastUtil;

import java.util.List;

/**
 * 整个窗口内容本质上是关于AccessibilityWindowInfo和AccessibilityNodeInfo的树结构,我称之为内容树.
 */
public class RobService extends AccessibilityService {

    private static final String TAG = AccessibilityService.class.getSimpleName();
    private static final int MSG_BACK_HOME = 0;
    private static final int MSG_BACK_ONCE = 1;
    boolean hasNotify = false;
    boolean hasLuckyMoney = true;
    private static boolean sIsBound = false;
    private boolean hasClickLuckyMoney;

    public RobService() {
        //如果配置能够获取窗口内容,则会返回当前活动窗口的根节点
//        AccessibilityNodeInfo rootInActiveWindow = getRootInActiveWindow();
        //获取当前服务的配置信息
//        AccessibilityServiceInfo serviceInfo = getServiceInfo();
        //设置当前服务的配置信息（另外种 meta-data配置方式）
//        setServiceInfo(serviceInfo);
//        getSystemService()
    }

    @Override
    protected void onServiceConnected() {
        Log.e("url", "抢红包：onServiceConnected");
        ToastUtil.showToast(this, "onServiceConnected 开启");
        super.onServiceConnected();
    }

    //系统通过sendAccessibiliyEvent()不断的发送AccessibilityEvent到此处
    /*
    打开微信界面：'com.tencent.mm.ui.LauncherUI'   微信在后台才会触发
    红包接受界面：'com.tencent.mm.plugin.luckymoney.ui.LuckyMoneyReceiveUI'
    红包详情:'com.tencent.mm.plugin.luckymoney.ui.LuckyMoneyDetailUI'
     */
    @TargetApi(Build.VERSION_CODES.N)
    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR2)
    @Override
    public void onAccessibilityEvent(AccessibilityEvent accessibilityEvent) {

        //获取到事件源头
        int eventType = accessibilityEvent.getEventType();

        switch (eventType) {
            case AccessibilityEvent.TYPE_NOTIFICATION_STATE_CHANGED:
                if (PhoneController.isLockScreen(this)) { //处于锁屏状态直接打开
                    PhoneController.wakeAndUnlockScreen(this);
                }
                handleNotification(accessibilityEvent);
                hasNotify = true;
                break;
            case AccessibilityEvent.TYPE_WINDOW_STATE_CHANGED:
            case AccessibilityEvent.TYPE_WINDOW_CONTENT_CHANGED:
                AccessibilityNodeInfo rootNode = getRootInActiveWindow();
                hasClickLuckyMoney = false;
                clickLuckyMoney(rootNode);

                if (hasClickLuckyMoney) {   //红色的开无法进行操作
                    //回退一下
                    startActivity(new Intent(this, EmptyActivity.class));
                }

                String className = accessibilityEvent.getClassName().toString();
                if (className.equals("com.tencent.mm.plugin.luckymoney.ui.LuckyMoneyReceiveUI")) {
                    //点击红包、显示开的界面的操作，通过button的id进行操作
                    openPacket();
                } else if (className.equals("com.tencent.mm.plugin.luckymoney.ui.LuckyMoneyDetailUI")) {
                    //进入类红包详情页面
                    close();
                }
                break;
            default:
                disableSelf();
                break;
        }
    }

    //关闭红包详情界面,实现自动返回聊天窗口，【右上角的返回按键】
    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR2)
    private void close() {
        AccessibilityNodeInfo nodeInfo = getRootInActiveWindow();
        if (nodeInfo != null) {
            //LinearLayout的界面操作我找到的id是ho,博客中是ez，正确的该是com.tencent.mm:id/ho
            List<AccessibilityNodeInfo> infos = nodeInfo.findAccessibilityNodeInfosByViewId("com.tencent.mm:id/ho");
            nodeInfo.recycle();
            for (AccessibilityNodeInfo item : infos) {
                item.performAction(AccessibilityNodeInfo.ACTION_CLICK);
            }
        }
    }

    //模拟点击,拆开红包 【button的按钮操作】
    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR2)
    private void openPacket() {
        //7.0上 拿不到这个对象？
        AccessibilityNodeInfo nodeInfo = getRootInActiveWindow();

        if (nodeInfo != null) {
            //为了演示,直接查看了红包控件的id b9m是控件通过Android Device Monitor/镜像出来的效果,开的button
            //b9m是博客中的数据、自己查找到的是 c2i,@id/c2i错误,com.tencent.mm:id/c2i！！！
            List<AccessibilityNodeInfo> nodoInfos = nodeInfo.findAccessibilityNodeInfosByViewId("com.tencent.mm:id/c2i");
            nodeInfo.recycle();
            for (AccessibilityNodeInfo item : nodoInfos) {
                item.performAction(AccessibilityNodeInfo.ACTION_CLICK);
            }
        }
    }

    //打开新的红包
    private void clickLuckyMoney(AccessibilityNodeInfo rootNode) {
        if (rootNode != null) {
            int childCount = rootNode.getChildCount();  //遍历查找数据
            for (int i = childCount - 1; i >= 0; i--) {  // 倒序查找最新的红包
                AccessibilityNodeInfo node = rootNode.getChild(i);
                if (node == null)
                    continue;
                CharSequence text = node.getText();
                if (text != null && text.toString().equals("领取红包")) {
                    AccessibilityNodeInfo parent = node.getParent();
                    while (parent != null) {
                        if (parent.isClickable()) {
                            parent.performAction(AccessibilityNodeInfo.ACTION_CLICK);
                            hasClickLuckyMoney = true;
                            break;
                        }
                        parent = parent.getParent();
                    }
                }
                clickLuckyMoney(node);
            }
        }
    }

    //处理 [微信红包]通知栏
    private void handleNotification(AccessibilityEvent event) {
        List<CharSequence> texts = event.getText();
        if (!texts.isEmpty()) {
            for (CharSequence text : texts) {
                String content = text.toString();   //获取基本信息
                //如果微信红包的提示信息,则模拟点击进入相应的聊天窗口
                if (content.contains("[微信红包]")) {
                    if (event.getParcelableData() != null && event.getParcelableData() instanceof Notification) {
                        Notification notification = (Notification) event.getParcelableData();
                        PendingIntent pendingIntent = notification.contentIntent;
                        try {
                            pendingIntent.send();
                        } catch (PendingIntent.CanceledException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }
    }


    @Override
    public void onInterrupt() {

    }
}
