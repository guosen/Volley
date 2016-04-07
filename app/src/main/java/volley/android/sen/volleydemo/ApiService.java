package volley.android.sen.volleydemo;

import retrofit.Call;
import retrofit.Callback;
import retrofit.http.GET;
import retrofit.http.Headers;
import retrofit.http.Path;
import retrofit.http.Query;

/**
 * Created by shouwang on 16/4/7.
 */
public interface ApiService {
    String END_POINT="http://api.meiyaapp.cn/";

    @Headers("Cache-Control: public, max-age=3600")
    @GET("v1/users/{id}?signature=123456")
    Call<HttpResponse<UserInfo>> getUserInfo(
          @Path("id") int id,
          @Query("with_recipients") int with_recipients,
          @Query("with_talent") int with_talent
    );
}
