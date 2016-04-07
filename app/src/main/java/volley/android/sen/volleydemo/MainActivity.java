package volley.android.sen.volleydemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import retrofit.Call;
import retrofit.Callback;
import retrofit.Response;
import retrofit.Retrofit;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Call<HttpResponse<UserInfo>> call=ApiManager.getInstance(this).getService().getUserInfo(91625, 0, 0);
        call.enqueue(new Callback<HttpResponse<UserInfo>>() {
            @Override
            public void onFailure(Throwable t) {
                Log.i("MainActivity",t.getMessage());
            }

            @Override
            public void onResponse(Response<HttpResponse<UserInfo>> response, Retrofit retrofit) {
                Log.i("MainActivity",response.body().data.username+"    ++++++++++");
            }
        });
    }
}
