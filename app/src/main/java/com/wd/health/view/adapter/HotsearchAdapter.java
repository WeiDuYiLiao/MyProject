package com.wd.health.view.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.wd.health.R;
import com.wd.health.model.Department;
import com.wd.health.model.PopularSearch;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

/*时间:2020/2/12
创建人:yang 
创建人:杨靖宇*/
public class HotsearchAdapter extends RecyclerView.Adapter<HotsearchAdapter.HotsearchHolder> {
    private List<PopularSearch> list;
    private Context context;

    public HotsearchAdapter( Context context) {
        list=new ArrayList<>();
        this.context = context;
    }
    public void addAll(List<PopularSearch> popularSearches){
        if (popularSearches!=null){
            list.addAll(popularSearches);
        }
    }


    @NonNull
    @Override
    public HotsearchHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.hotsearch_layout, null, false);
        return new HotsearchHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HotsearchHolder holder, int position) {
        PopularSearch popularSearch = list.get(position);
        holder.hotsearch_name.setText(popularSearch.name);

    }

    @Override
    public int getItemCount() {
        return list.size();
    }
    class  HotsearchHolder extends RecyclerView.ViewHolder{


        private final TextView hotsearch_name;

        public HotsearchHolder(@NonNull View itemView) {
            super(itemView);
            hotsearch_name = itemView.findViewById(R.id.hotsearch_name);
        }
    }

}
