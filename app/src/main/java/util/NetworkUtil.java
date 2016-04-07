package util;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import static android.telephony.TelephonyManager.NETWORK_TYPE_1xRTT;
import static android.telephony.TelephonyManager.NETWORK_TYPE_CDMA;
import static android.telephony.TelephonyManager.NETWORK_TYPE_EDGE;
import static android.telephony.TelephonyManager.NETWORK_TYPE_EHRPD;
import static android.telephony.TelephonyManager.NETWORK_TYPE_EVDO_0;
import static android.telephony.TelephonyManager.NETWORK_TYPE_EVDO_A;
import static android.telephony.TelephonyManager.NETWORK_TYPE_EVDO_B;
import static android.telephony.TelephonyManager.NETWORK_TYPE_GPRS;
import static android.telephony.TelephonyManager.NETWORK_TYPE_HSDPA;
import static android.telephony.TelephonyManager.NETWORK_TYPE_HSPA;
import static android.telephony.TelephonyManager.NETWORK_TYPE_HSPAP;
import static android.telephony.TelephonyManager.NETWORK_TYPE_HSUPA;
import static android.telephony.TelephonyManager.NETWORK_TYPE_IDEN;
import static android.telephony.TelephonyManager.NETWORK_TYPE_LTE;
import static android.telephony.TelephonyManager.NETWORK_TYPE_UMTS;

/**
 * Created by shouwang on 16/4/7.
 */
public class NetworkUtil {


    public static boolean isNetworkAvailable(Context context) {
        ConnectivityManager connManager =(ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo mNetworkInfo = connManager.getActiveNetworkInfo();
        if (mNetworkInfo != null) {
            return mNetworkInfo.isAvailable();
        }
        return false;
    }


    public static boolean isNetworkConnected(Context context) {
        ConnectivityManager connManager =(ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkinfo = connManager.getActiveNetworkInfo();
        if (networkinfo != null) {
            return networkinfo.isConnected();
        }
        return false;
    }


	/*public static boolean isWifiAvailable1(Context context) {
		ConnectivityManager connManager =(ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo mWifi = connManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
		if (mWifi != null) {
			return mWifi.isAvailable() && mWifi.isConnected();
		}
		return false;
	}*/


    public static boolean isWifiAvailable(Context context) {
        NetworkInfo netInfo = getNetInfo(context);
        if (netInfo != null) {
            if (ConnectivityManager.TYPE_WIFI == netInfo.getType()) {
                return  netInfo.isAvailable() && netInfo.isConnected();
            }else {
                return false;
            }
        }
        return false;
    }

    public static NetworkInfo getNetInfo(Context context) {
        ConnectivityManager manager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = manager.getActiveNetworkInfo();
        return netInfo;
    }
    public static String getNetworkTypeString(Context context) {
        String ret = "unknown";
        switch (getNetworkType(context)) {
            case NETWORK_CLASS_2_G:
                ret = "2g";
                break;
            case NETWORK_CLASS_3_G:
                ret = "3g";
                break;
            case  NETWORK_CLASS_4_G:
                ret = "4g";
                break;
            case NETWORK_WIFI:
                ret = "wifi";
                break;
        }
        return ret;
    }
    public static int getNetworkType(Context context) {
        ConnectivityManager manager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo info = manager.getActiveNetworkInfo();
        if (info == null) {
            return NETWORK_CLASS_UNKNOWN;
        }
        if (info.getType() == ConnectivityManager.TYPE_MOBILE) {
            return getNetworkClass(info.getSubtype());
        } else if (info.getType() == ConnectivityManager.TYPE_WIFI) {
            return NETWORK_WIFI;
        }
        return NETWORK_CLASS_UNKNOWN;
    }

    //Copyed from TelephonyManager
    /** Unknown network class. {@hide} */
    public static final int NETWORK_CLASS_UNKNOWN = 0;
    /** Class of broadly defined "2G" networks. {@hide} */
    public static final int NETWORK_CLASS_2_G = 1;
    /** Class of broadly defined "3G" networks. {@hide} */
    public static final int NETWORK_CLASS_3_G = 2;
    /** Class of broadly defined "4G" networks. {@hide} */
    public static final int NETWORK_CLASS_4_G = 3;
    //自己添加，标示WIFI
    public static final int NETWORK_WIFI = 11;
    /**
     * Return general class of network type, such as "3G" or "4G". In cases
     * where classification is contentious, this method is conservative.
     *
     * @hide
     */
    public static int getNetworkClass(int networkType) {
        switch (networkType) {
            case NETWORK_TYPE_GPRS:
            case 16/*NETWORK_TYPE_GSM*/:
            case NETWORK_TYPE_EDGE:
            case NETWORK_TYPE_CDMA:
            case NETWORK_TYPE_1xRTT:
            case NETWORK_TYPE_IDEN:
                return NETWORK_CLASS_2_G;
            case NETWORK_TYPE_UMTS:
            case NETWORK_TYPE_EVDO_0:
            case NETWORK_TYPE_EVDO_A:
            case NETWORK_TYPE_HSDPA:
            case NETWORK_TYPE_HSUPA:
            case NETWORK_TYPE_HSPA:
            case NETWORK_TYPE_EVDO_B:
            case NETWORK_TYPE_EHRPD:
            case NETWORK_TYPE_HSPAP:
                return NETWORK_CLASS_3_G;
            case NETWORK_TYPE_LTE:
                return NETWORK_CLASS_4_G;
            default:
                return NETWORK_CLASS_UNKNOWN;
        }
    }
}
