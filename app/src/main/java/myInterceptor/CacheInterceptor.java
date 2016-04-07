package myInterceptor;

import android.content.Context;

import com.squareup.okhttp.CacheControl;
import com.squareup.okhttp.Interceptor;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import java.io.IOException;
import java.util.logging.Logger;

import util.NetworkUtil;

/**
 * Created by shouwang on 16/4/7.
 */
public class CacheInterceptor implements Interceptor {
    private Context mContext;
    public CacheInterceptor(Context context){
        mContext=context;
    }
    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();
        if(!NetworkUtil.isNetworkAvailable(mContext)){
            request = request.newBuilder()
                    .cacheControl(CacheControl.FORCE_CACHE)
                    .build();
//            Logger.t(TAG).w("no network");
        }
        Response originalResponse = chain.proceed(request);
        if(NetworkUtil.isNetworkAvailable(mContext)){
            //有网的时候读接口上的@Headers里的配置，你可以在这里进行统一的设置
            String cacheControl = request.cacheControl().toString();
            return originalResponse.newBuilder()
                    .header("Cache-Control", cacheControl)
                    .removeHeader("Pragma")
                    .build();
        }else{
            return originalResponse.newBuilder()
                    .header("Cache-Control", "public, only-if-cached, max-stale=2419200")
                    .removeHeader("Pragma")
                    .build();
        }
    }
}
