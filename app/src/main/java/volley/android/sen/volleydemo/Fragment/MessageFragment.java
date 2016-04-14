package volley.android.sen.volleydemo.Fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import retrofit.Call;
import retrofit.Callback;
import retrofit.Response;
import retrofit.Retrofit;
import volley.android.sen.volleydemo.ApiManager;
import volley.android.sen.volleydemo.HttpResponse;
import volley.android.sen.volleydemo.R;
import volley.android.sen.volleydemo.adapter.MyRecycleAdapter;
import volley.android.sen.volleydemo.domain.GanHuoData;
import volley.android.sen.volleydemo.wdget.HeadFootRecycleView;

/**
 * Created by shouwang on 16/4/13.
 */
public class MessageFragment extends Fragment {
    private View rootView;
    @Bind(R.id.recycleView)
    HeadFootRecycleView recycleView;
    private MyRecycleAdapter mMyRecycleAdapter;
    private List<GanHuoData>mGanHuoDataList=new ArrayList<>();
    private final String Tag="MessageFragment";
    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this,view);
        mMyRecycleAdapter=new MyRecycleAdapter(getContext(),mGanHuoDataList);
        mMyRecycleAdapter.setHeaderCount(1);
        recycleView.setLayoutManager(new LinearLayoutManager(getContext()));
        recycleView.setAdapter(mMyRecycleAdapter);
        getDataFromServer();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if(rootView==null){
            rootView=inflater.inflate(R.layout.layout_fragment_message,container,false);
        }
        return rootView;
    }

    private void getDataFromServer(){
        Call<HttpResponse<List<GanHuoData>>>call=ApiManager.getInstance(getContext()).getService().getGanHuo(1);
        call.enqueue(new Callback<HttpResponse<List<GanHuoData>>>() {
           @Override
           public void onResponse(Response<HttpResponse<List<GanHuoData>>> response, Retrofit retrofit) {
               Log.i(Tag,response.body().results.size()+"");
               mMyRecycleAdapter.setGanHuoDataList(response.body().results);
               mMyRecycleAdapter.notifyDataSetChanged();
           }

           @Override
           public void onFailure(Throwable t) {

           }
       });
    }

}
