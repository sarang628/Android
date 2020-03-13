package com.example.sarang.agreementsample.util;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.widget.Toast;

/**
 * Created by erise on 2016. 6. 17..
 */
public class UtilNetwork {
    public static boolean IsWifiAvailable(Context context)
    {
        ConnectivityManager m_NetConnectMgr= (ConnectivityManager)context.getSystemService(Context.CONNECTIVITY_SERVICE);
        boolean bConnect = false;
        try
        {
            if( m_NetConnectMgr == null ) return false;

            NetworkInfo info = m_NetConnectMgr.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
            bConnect = (info.isAvailable() && info.isConnected());

        }
        catch(Exception e)
        {
            return false;
        }
        if(CNCconst.DEBUGMODE) System.out.println("[JIN]   ****** IsWifiAvailable      : " + bConnect);
        return bConnect;
    }

    public static boolean IsMobileAvailable(Context context)
    {
        ConnectivityManager m_NetConnectMgr= (ConnectivityManager)context.getSystemService(Context.CONNECTIVITY_SERVICE);
        boolean bConnect = false;
        try
        {
            if( m_NetConnectMgr == null ) return false;
            NetworkInfo info = m_NetConnectMgr.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);
            bConnect = (info.isAvailable() && info.isConnected());
        }
        catch(Exception e)
        {
            return false;
        }
        if(CNCconst.DEBUGMODE) System.out.println("[JIN]   ****** IsMobileAvailable      : " + bConnect);
        return bConnect;
    }

    public static int networkCheck(Context context)
    {
        int check = 1;

        if (!UtilNetwork.IsMobileAvailable(context) && !UtilNetwork.IsWifiAvailable(context))
        {
            Toast.makeText(context, "* 네트워크 연결 상태를 확인하세요!", Toast.LENGTH_SHORT).show();
            check = 0;
            return check;
        }

        return check;
    }

}
