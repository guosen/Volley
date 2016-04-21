package volley.android.sen.volleydemo.Fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

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
import volley.android.sen.volleydemo.adapter.MyImageAdapter;
import volley.android.sen.volleydemo.domain.GanHuoData;
import volley.android.sen.volleydemo.wdget.MyItemDecoration;

/**
 * Created by shouwang on 16/4/20.
 */
public class ImageStaggeFragment extends Fragment {
    @Bind(R.id.vRecycleView)
    RecyclerView vRecycleView;
    private View vRoot;

    private MyImageAdapter mAdapter;
    private List<GanHuoData> mGanHuoDataList;
    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this,view);
        initRecyclerView(vRecycleView);
        getDataFromServer();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if(vRoot==null){
            vRoot=inflater.inflate(R.layout.layout_fragment_image,container,false);
        }
        return vRoot;
    }

    private void initRecyclerLayoutManager(RecyclerView recyclerView) {
        // 错列网格布局
        recyclerView.setLayoutManager(new StaggeredGridLayoutManager(2,
                StaggeredGridLayoutManager.VERTICAL));
    }

    private void initItemDecoration(RecyclerView recyclerView) {
        recyclerView.addItemDecoration(new MyItemDecoration(getContext()));
    }

    private void initRecyclerView(RecyclerView recyclerView) {
        recyclerView.setHasFixedSize(true); // 设置固定大小
        initRecyclerLayoutManager(recyclerView); // 初始化布局
        initRecyclerAdapter(recyclerView); // 初始化适配器
        initItemDecoration(recyclerView); // 初始化装饰
        initItemAnimator(recyclerView); // 初始化动画效果
    }

    private void initRecyclerAdapter(RecyclerView recyclerView) {
        mAdapter = new MyImageAdapter(mGanHuoDataList,getContext());
        recyclerView.setAdapter(mAdapter);
    }
    private void initItemAnimator(RecyclerView recyclerView) {
        recyclerView.setItemAnimator(new DefaultItemAnimator()); // 默认动画
    }

    private void getDataFromServer(){
        Call<HttpResponse<List<GanHuoData>>> call= ApiManager.getInstance(getContext()).getService().getFuLi(1);
        call.enqueue(new Callback<HttpResponse<List<GanHuoData>>>() {
            @Override
            public void onResponse(Response<HttpResponse<List<GanHuoData>>> response, Retrofit retrofit) {
                //Log.i(Tag,response.body().results.size()+"");
                mGanHuoDataList=response.body().data;
                mAdapter.setGanHuoDataList(response.body().results);
                mAdapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Throwable t) {

            }
        });
    }
}
