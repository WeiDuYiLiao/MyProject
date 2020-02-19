package com.wd.health.view.adapter;
/*时间:2020/1/12 0012
创建人:郭学飞*/


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.wd.health.R;
import com.wd.health.model.ShowCircleBean;

import java.util.List;

public class CircleAdapter extends RecyclerView.Adapter<CircleAdapter.CircleHolder> {
      private List<ShowCircleBean> list;
      private Context context;

    public CircleAdapter(Context context) {
        this.context = context;
    }

    public void addAll(List<ShowCircleBean> list){
        this.list=list;
    }

    public void cancelAll(){
        this.list=null;
    }


    @NonNull
    @Override
    public CircleHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
       View view = LayoutInflater.from(context).inflate(R.layout.circle_layout, null, false);
        return new CircleHolder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull CircleHolder holder, int position) {
            holder.title_circle_text.setText(list.get(position).getTitle());
            holder.detail_circle_text.setText(list.get(position).getDetail());
            //时间转换
            /*SimpleDateFormat df = new SimpleDateFormat("yyyy"+"年"+"MM"+"月"+"dd"+"日"+"   HH:mm:ss");
            String datetime = df.format(list.get(position).getReleaseTime());
            holder.time_circle_text.setText(datetime);*/
            holder.collectionNum.setText("收藏  "+list.get(position).getCollectionNum());
            holder.commentNum.setText("评论  "+list.get(position).getCommentNum());

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class CircleHolder extends RecyclerView.ViewHolder {

        private final TextView title_circle_text,detail_circle_text
                               ,time_circle_text,collectionNum,commentNum;
                ;

        public CircleHolder(@NonNull View itemView) {
            super(itemView);
            title_circle_text = itemView.findViewById(R.id.title_circle_text);
            detail_circle_text = itemView.findViewById(R.id.detail_circle_text);
            time_circle_text = itemView.findViewById(R.id.time_circle_text);
            collectionNum = itemView.findViewById(R.id.collectionNum);
            commentNum = itemView.findViewById(R.id.commentNum);
        }
    }
}
