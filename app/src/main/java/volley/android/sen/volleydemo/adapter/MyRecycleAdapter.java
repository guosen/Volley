package volley.android.sen.volleydemo.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import volley.android.sen.volleydemo.R;
import volley.android.sen.volleydemo.domain.GanHuoData;

/**
 * Created by shouwang on 16/3/25.
 */
public class MyRecycleAdapter extends BaseRecycleAdapter {
    private List<GanHuoData>mGanHuoDataList;
    private LayoutInflater mLayoutInflater;
    private Context mContext;

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if(holder instanceof GanHuoHeader){

        }else {
            GanhuoViewHolder ganhuoViewHolder = (GanhuoViewHolder) holder;
            GanHuoData ganhuo = mGanHuoDataList.get(position-mHeaderCount);
            if (ganhuo != null) {
                ganhuoViewHolder.tvName.setText(ganhuo.who);
                ganhuoViewHolder.tvDesc.setText(ganhuo.desc);
            }
        }

    }

    @Override
    public int getItemCount() {
        return super.getItemCount();
    }

    @Override
    public RecyclerView.ViewHolder onCreateHeaderView(ViewGroup parent) {
        return new GanHuoHeader(mLayoutInflater.inflate(R.layout.layout_list_header,parent,false));
    }

    @Override
    public RecyclerView.ViewHolder onCreateContentView(ViewGroup parent) {
       return  new GanhuoViewHolder(mLayoutInflater.inflate(R.layout.layout_item_ganhuo,parent,false));
    }

    @Override
    public RecyclerView.ViewHolder onCreateBottomView(ViewGroup parent) {
        return null;
    }

    @Override
    public int getContentItemCount() {
        return mGanHuoDataList==null?0:mGanHuoDataList.size();
    }

    public MyRecycleAdapter(Context context,List<GanHuoData> list) {
        super(context);
        mContext=context;
        mLayoutInflater=LayoutInflater.from(context);
        mGanHuoDataList=list;

    }

    public void setGanHuoDataList(List<GanHuoData>list){
        mGanHuoDataList=list;
    }

    public static class GanhuoViewHolder extends RecyclerView.ViewHolder{
        ImageView mImageView;
        @Bind(R.id.tvName)
        TextView tvName;
        @Bind(R.id.tvDesc)
        TextView tvDesc;
         GanhuoViewHolder(View view) {
            super(view);
             ButterKnife.bind(this,view);
            // mImageView=(ImageView) view.findViewById(R.id.ivRecycle);
        }
    }

    public static class GanHuoHeader extends RecyclerView.ViewHolder{
        GanHuoHeader(View view){
            super(view);
        }
    }
}
