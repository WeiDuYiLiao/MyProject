package com.wd.health.view.adapter;

import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.wd.health.R;
import com.wd.health.model.Department;
import com.wd.health.model.DrugsCategoryList;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

/*时间:2020/1/9
创建人:yang 
创建人:杨靖宇*/
public class DrugsCategoryAdapter extends RecyclerView.Adapter<DrugsCategoryAdapter.DiseaseHolder> {
    private List<DrugsCategoryList> list;
    private Context context;
    private boolean iscircle;
    private int p=0;

    public DrugsCategoryAdapter(Context context) {
        list = new ArrayList<>();
        this.context = context;
    }

    public void addAll(List<DrugsCategoryList> drugsCategoryLists){
        if (drugsCategoryLists!=null){
            list.addAll(drugsCategoryLists);
        }
    }

    @NonNull
    @Override
    public DiseaseHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.drugscategory_layout, null, false);

        return new DiseaseHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DiseaseHolder holder, int position) {
        DrugsCategoryList drugsCategoryList = list.get(position);
        holder.disease_name.setText(drugsCategoryList.name);

        holder.itemView.setTag(position);

        if (p ==position){
            holder.drugscategory_view.setVisibility(View.VISIBLE);
            holder.disease_name.setTextColor(Color.parseColor("#03A9F4"));
            holder.drugscategory_linear.setBackgroundColor(Color.rgb(255,255,255));

        }else {
            holder.drugscategory_view.setVisibility(View.GONE);
            holder.disease_name.setTextColor(Color.parseColor("#333333"));
            holder.drugscategory_linear.setBackgroundColor(Color.argb(1,242,242,241));
        }
        holder .itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                p = (int) v.getTag();
                Log.d("sss",list.get(position).id+"");
                onClickLisnerdrugsCategory.OnClik(list.get(position).id);
                notifyDataSetChanged();
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

  /*  public void setCollBack() {

    }*/

    class DiseaseHolder extends RecyclerView.ViewHolder{

        private final TextView disease_name;
        private final LinearLayout drugscategory_linear;
        private final View drugscategory_view;
        /*     private final LinearLayout disease_linear;
        private final View disease_view;*/


        public DiseaseHolder(@NonNull View itemView) {
            super(itemView);
            disease_name = itemView.findViewById(R.id.drugscategory_name);
            drugscategory_linear = itemView.findViewById(R.id.drugscategory_linear);
            drugscategory_view = itemView.findViewById(R.id.drugscategory_view);
           /* disease_linear = itemView.findViewById(R.id.disease_linear);
            disease_view = itemView.findViewById(R.id.disease_view);*/
        }
    }


    //接口回调
    public OnClickLisnerdrugsCategory onClickLisnerdrugsCategory;

    public void setOnClickLisnerdrugsCategory(OnClickLisnerdrugsCategory onClickLisnerdrugsCategory) {
        this.onClickLisnerdrugsCategory = onClickLisnerdrugsCategory;
    }

    public interface OnClickLisnerdrugsCategory{
        void OnClik(int i);
    }
}
