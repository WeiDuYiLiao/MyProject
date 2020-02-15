package com.wd.health.view.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.wd.health.R;
import com.wd.health.model.Department;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

/*时间:2020/1/9
创建人:yang 
创建人:杨靖宇*/
public class DiseaseAdapter extends RecyclerView.Adapter<DiseaseAdapter.DiseaseHolder> {
    private List<Department> list;
    private Context context;
    private boolean iscircle;
    private int p=0;

    public DiseaseAdapter( Context context) {
        list = new ArrayList<>();
        this.context = context;
    }

    public void addAll(List<Department> departments){
        if (departments!=null){
            list.addAll(departments);
        }
    }

    @NonNull
    @Override
    public DiseaseHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.disease1_layout, null, false);
        return new DiseaseHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DiseaseHolder holder, int position) {
        Department department = list.get(position);
        holder.disease_name.setText(department.departmentName);

        holder.itemView.setTag(position);

        if (p ==position){
            holder.disease_view.setVisibility(View.VISIBLE);
            holder.disease_name.setTextColor(Color.parseColor("#03A9F4"));
            holder.disease_linear.setBackgroundColor(Color.rgb(255,255,255));


        }else {

            holder.disease_view.setVisibility(View.GONE);
            holder.disease_name.setTextColor(Color.parseColor("#333333"));
            holder.disease_linear.setBackgroundColor(Color.argb(1,242,242,241));


        }


        holder .itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                p = (int) v.getTag();
                onClickLisnerdisease.OnClik(list.get(position).id);
                notifyDataSetChanged();
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
    class DiseaseHolder extends RecyclerView.ViewHolder{

        private final TextView disease_name;
        private final LinearLayout disease_linear;
        private final View disease_view;


        public DiseaseHolder(@NonNull View itemView) {
            super(itemView);
            disease_name = itemView.findViewById(R.id.disease_name);
            disease_linear = itemView.findViewById(R.id.disease_linear);
            disease_view = itemView.findViewById(R.id.disease_view);
        }
    }


    //接口回调
    public OnClickLisnerdisease onClickLisnerdisease;

    public void setOnClickLisnerdisease(OnClickLisnerdisease onClickLisnerdisease) {
        this.onClickLisnerdisease = onClickLisnerdisease;
    }

    public interface OnClickLisnerdisease{
        void OnClik( int i);
    }
}
