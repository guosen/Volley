package volley.android.sen.volleydemo;
import android.content.Context;

import com.facebook.stetho.Stetho;
import com.facebook.stetho.okhttp.StethoInterceptor;
import com.squareup.okhttp.Cache;
import com.squareup.okhttp.Interceptor;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import java.io.File;
import java.io.IOException;

import myInterceptor.CacheInterceptor;
import myInterceptor.LoggingInterceptor;
import retrofit.GsonConverterFactory;
import retrofit.Retrofit;

/**
 * Created by shouwang on 16/4/7.
 *
 */
public class ApiManager {
    public static final String HEADER_ACCEPT_JSON = "application/json";
    public static final String HEADER_ACCEPT      = "Accept";
    //对API 数据采用gzip压缩
    private static final String HEADER_ACCEPT_ENCODING = "Accept-Encoding";
    private static final String ENCODING_GZIP = "gzip";

    private        ApiService mApiService;
    private static ApiManager sInstance;
    private Context mContext;
    public ApiManager(Context context) {
         mContext=context;
    }

    private ApiService createService() {
         OkHttpClient client=new OkHttpClient();
        //----------
        //设置头部 例如返回类型是Json/text
        //-----------
         client.interceptors().add(new Interceptor() {
             @Override
             public Response intercept(Chain chain) throws IOException {
                 final Request originalRequest = chain.request();
                 final Request requestWithUserAgent = originalRequest.newBuilder()
                         .addHeader(HEADER_ACCEPT, HEADER_ACCEPT_JSON)
                         .build();
                 Response response = chain.proceed(requestWithUserAgent);
                 return response;
             }
         });
        //日志拦截 https://github.com/square/okhttp/wiki/Interceptors
        client.interceptors().add(new LoggingInterceptor());
        client.interceptors().add(new CacheInterceptor(mContext));
        //开启Stetho，
       // client.interceptors().add(new StethoInterceptor()); 方法废弃
        File cacheFile = new File(mContext.getCacheDir(), "[缓存目录]");
        Cache cache = new Cache(cacheFile, 1024 * 1024 * 100); //100Mb
        client.setCache(cache);




        //设置请求  并设置HttpClient (OKHttp)
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ApiService.END_POINT)
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build();


        return retrofit.create(ApiService.class);

    }

    public ApiService getService() {
        if (mApiService == null) {
            synchronized (this) {
                if (mApiService == null)
                    mApiService = createService();
            }
        }
        return mApiService;
    }

    public static ApiManager getInstance(Context context) {
        if (sInstance == null) {
            sInstance = new ApiManager(context.getApplicationContext());
        }
        return sInstance;
    }
}
