package com.wd.health.view.fragment;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.wd.health.R;
import com.wd.health.contract.ZixunBean;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>文件描述：<p>
 * <p>作者：孙尔哲<p>
 * <p>创建时间：2020/1/9<p>
 * <p>更改时间：2020/1/9<p>
 */
public class ZixunAdpter extends RecyclerView.Adapter<ZixunAdpter.MyViewHolder> {

     private List<ZixunBean>  list;
     private Context  context;
    private View inflate;
    int  p;

    public ZixunAdpter( Context context) {
        list = new ArrayList<>();
        this.context = context;
    }

    public   void  addAll(List<ZixunBean> data){
          if (data!=null){
              list.addAll(data);
          }
    }

    @NonNull
    @Override
    public ZixunAdpter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        inflate = LayoutInflater.from(context).inflate(R.layout.zixun_layout, parent, false);
        return new MyViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull ZixunAdpter.MyViewHolder holder, int position) {
          holder.textView.setText(list.get(position).name);
          holder.itemView.setTag(position);


          holder.itemView.setOnClickListener(new View.OnClickListener() {
              @Override
              public void onClick(View v) {
                  p=(int) v.getTag();

                  onActu.onActi(list.get(position).getId(),list.get(position).name);
                  notifyDataSetChanged();
              }
          });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
    class  MyViewHolder  extends RecyclerView.ViewHolder{
        private final TextView textView;
        private final LinearLayout lin_lin;
        private final View bing_zheng_view;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.text_view);
            lin_lin = itemView.findViewById(R.id.lin_lin);
            bing_zheng_view = itemView.findViewById(R.id.bing_zheng_view);
        }
    }
    private  OnActu  onActu;

    public void setOnActu(OnActu onActu) {
        this.onActu = onActu;
    }

    public  interface  OnActu{
        void  onActi(int Id,String name);
    }
}
