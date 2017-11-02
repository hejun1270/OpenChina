package com.itheima.openchina.receivers;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;

/**
 * User:DoctorHe <p/>
 * Date: 2017/10/19 16:25 <p/>
 * Project:GooglePlay <p/>
 * Package:com.adminhj.googleplay.reciver <p/>
 * Desc:    <p/>
 */

public class NetWorkStateReciver extends BroadcastReceiver {
    private static final String TAG = "NetWorkStateReciver";

    private NetWorkLinstener netWorkLinstener;

    @Override
    public void onReceive(Context context, Intent intent) {

        // 这个监听网络连接的设置，包括wifi和移动数据的打开和关闭。.
        // 最好用的还是这个监听。wifi如果打开，关闭，以及连接上可用的连接都会接到监听。见log
        // 这个广播的最大弊端是比上边两个广播的反应要慢，如果只是要监听wifi，我觉得还是用上边两个配合比较合适
        if (ConnectivityManager.CONNECTIVITY_ACTION.equals(intent.getAction())) {
            ConnectivityManager manager = (ConnectivityManager) context
                    .getSystemService(Context.CONNECTIVITY_SERVICE);
            Log.i(TAG, "CONNECTIVITY_ACTION");

            NetworkInfo activeNetwork = manager.getActiveNetworkInfo();
            if (activeNetwork != null) { // connected to the internet
                if (activeNetwork.isConnected()) {
                    if (activeNetwork.getType() == ConnectivityManager.TYPE_WIFI) {
                        // connected to wifi
                        setContectState("当前WiFi连接可用", true);
                    } else if (activeNetwork.getType() == ConnectivityManager.TYPE_MOBILE) {
                        // connected to the mobile provider's data plan
                        setContectState("当前移动网络连接可用", true);
                    }
                } else {
                    setContectState("当前没有网络连接", false);
                }
                Log.i(TAG, "info.getTypeName()" + activeNetwork.getTypeName());
                Log.i(TAG, "getSubtypeName()" + activeNetwork.getSubtypeName());
                Log.i(TAG, "getState()" + activeNetwork.getState());
                Log.i(TAG, "getDetailedState()"
                        + activeNetwork.getDetailedState().name());
                Log.i(TAG, "getDetailedState()" + activeNetwork.getExtraInfo());
                Log.i(TAG, "getType()" + activeNetwork.getType());
            } else {   // not connected to the internet
                setContectState("当前没有网络连接", false);
                Log.i(TAG, "当前没有网络连接，请确保你已经打开网络 ");

            }
        }
    }

    private void setContectState(String msg, boolean isConnected) {
        if (netWorkLinstener != null) {
            netWorkLinstener.onConnect(msg, isConnected);
        }
    }

    public void setNetWorkLinstener(NetWorkLinstener netWorkLinstener) {
        this.netWorkLinstener = netWorkLinstener;
    }

    public interface NetWorkLinstener {
        void onConnect(String msg, boolean isConnect);
    }
}
