package application;

import android.app.Application;

import com.facebook.stetho.Stetho;

import cn.jpush.android.api.JPushInterface;

/**
 * Created by shouwang on 16/4/8.
 */
public class MyApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Stetho.initializeWithDefaults(this);

        JPushInterface.setDebugMode(true); 	// 设置开启日志,发布时请关闭日志
        JPushInterface.init(this);
    }
}
