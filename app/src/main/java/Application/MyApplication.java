package application;

import android.app.Application;
import android.content.pm.PackageManager;
import android.os.Environment;
import android.util.Log;

import com.alipay.euler.andfix.patch.PatchManager;
import com.facebook.stetho.Stetho;
import com.facebook.stetho.server.ProtocolDetectingSocketHandler;

import java.io.IOException;

import cn.jpush.android.api.JPushInterface;

/**
 * Created by shouwang on 16/4/8.
 */
public class MyApplication extends Application {
    PatchManager patchManager;
    @Override
    public void onCreate() {
        super.onCreate();
        Stetho.initializeWithDefaults(this);
        JPushInterface.setDebugMode(true); 	// 设置开启日志,发布时请关闭日志
        JPushInterface.init(this);


//        patchManager = new PatchManager(this);
//        patchManager.init(getAppVersion());//current version
//        patchManager.loadPatch();
//        try {
//            patchManager.addPatch(Environment.getExternalStorageDirectory() + "/v.apatch");
//            Log.i("MyApplication","success");
//        }catch (Exception ex){
//            Log.i("MyApplication",ex.getMessage());
//            Log.i("MyApplication",Environment.getExternalStorageDirectory().toString());
//        }
    }
    public String getAppVersion(){
        String appversion="";
        try {
             appversion = getPackageManager().getPackageInfo(getPackageName(), 0).versionName;
        }catch (PackageManager.NameNotFoundException ex){

        }finally {

        }

        return appversion;
    }
}
