package volley.android.sen.volleydemo.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import volley.android.sen.volleydemo.R;
import volley.android.sen.volleydemo.domain.GanHuoData;

/**
 * Created by shouwang on 16/4/20.
 */
public class MyImageAdapter extends RecyclerView.Adapter<MyImageAdapter.MyViewHolder> {
    private List<GanHuoData> mGanHuoDataList;
    private List<Integer> mHeights;
    private Context  mContext;
    public MyImageAdapter(List<GanHuoData> dataList, Context context){
        mGanHuoDataList=dataList;
        mHeights=new ArrayList<>();
        mContext=context;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView= LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_item_image_fuli,parent,false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        GanHuoData ganHuoData=mGanHuoDataList.get(position);
        //随机高度模仿瀑布流
        if(mHeights.size()<=position){
            mHeights.add((int)(500+Math.random()*300));
        }
        ViewGroup.LayoutParams lp=holder.ivImage.getLayoutParams();
        lp.height=mHeights.get(position);
        holder.ivImage.setLayoutParams(lp);
        holder.itemView.setLayoutParams(lp);
        Glide.with(mContext).load(ganHuoData.url).placeholder(R.mipmap.small).into(holder.ivImage);

    }

    @Override
    public int getItemCount() {
        return mGanHuoDataList==null?0:mGanHuoDataList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        @Bind(R.id.ivImage)
        ImageView ivImage;
        public MyViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }

    }

    public void setGanHuoDataList(List<GanHuoData> list){
        mGanHuoDataList=list;
    }


}
