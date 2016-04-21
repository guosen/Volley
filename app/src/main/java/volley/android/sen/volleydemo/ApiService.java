package volley.android.sen.volleydemo;

import java.util.List;

import retrofit.Call;
import retrofit.Callback;
import retrofit.http.GET;
import retrofit.http.Headers;
import retrofit.http.Path;
import retrofit.http.Query;
import volley.android.sen.volleydemo.domain.GanHuoData;

/**
 * Created by shouwang on 16/4/7.
 */
public interface ApiService {
//    String END_POINT="http://api.meiyaapp.cn/";
      String END_POINT="http://gank.io/api/";

    @Headers("Cache-Control: public, max-age=3600")
    @GET("v1/users/{id}?signature=123456")
    Call<HttpResponse<UserInfo>> getUserInfo(
          @Path("id") int id,
          @Query("with_recipients") int with_recipients,
          @Query("with_talent") int with_talent
    );
    @GET("data/Android/30/{page}")
    Call<HttpResponse<List<GanHuoData>>>getGanHuo(
          @Path("page") int page
    );

    @GET("data/福利/30/{page}")
    Call<HttpResponse<List<GanHuoData>>>getFuLi(
      @Path("page") int page
    );

}
