package application;

import android.app.Application;

import com.facebook.stetho.Stetho;

/**
 * Created by shouwang on 16/4/8.
 */
public class MyApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Stetho.initializeWithDefaults(this);
    }
}
