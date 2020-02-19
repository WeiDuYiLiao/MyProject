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
    private View view;

    public DiseaseAdapter( Context context) {
        list = new ArrayList<>();
        this.context = context;
    }

    public void addAll(List<Department> departments,boolean b){
        if (departments!=null){
            list.addAll(departments);
            iscircle=b;
        }
    }

    @NonNull
    @Override
    public DiseaseHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (iscircle) {
            view = LayoutInflater.from(context).inflate(R.layout.circledosease_layout, null, false);
            return new DiseaseHolder(view);
        } else {
            view = LayoutInflater.from(context).inflate(R.layout.disease1_layout, null, false);
            return new DiseaseHolder(view);
        }
    }
    @Override
    public void onBindViewHolder(@NonNull DiseaseHolder holder, int position) {
        Department department = list.get(position);
        if (iscircle){
            holder.disease_name.setText(department.departmentName);
            holder.disease_name.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    clickLisnerdisease.Circle(list.get(position).id);
                    clickLisnerdisease.OnClikcheck(list.get(position).departmentName);
                }
            });
        }else {
            if (p == 0) {
                holder.disease_name.setText(department.departmentName);
                holder.disease_linear.setBackgroundColor(Color.rgb(255, 255, 255));
                holder.disease_name.setTextColor(Color.rgb(0, 0, 255));
                holder.disease_view.setVisibility(View.VISIBLE);
                p++;
            }
            holder.disease_name.setText(department.departmentName);
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    holder.disease_view.setVisibility(View.VISIBLE);
                    holder.disease_name.setText(department.departmentName);
                    holder.disease_linear.setBackgroundColor(Color.rgb(255, 255, 255));
                    holder.disease_name.setTextColor(Color.rgb(0, 0, 255));
                }
            });
        }
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
            if (iscircle){
                disease_name = itemView.findViewById(R.id.disease_name);
                disease_linear=null;
                disease_view=null;
            }else {
                disease_name = itemView.findViewById(R.id.disease_name);
                disease_linear = itemView.findViewById(R.id.disease_linear);
                disease_view = itemView.findViewById(R.id.disease_view);
            }
        }
    }

    public OnClickLisnerdisease clickLisnerdisease;

    public void setClickLisnerdisease(OnClickLisnerdisease clickLisnerdisease) {
        this.clickLisnerdisease = clickLisnerdisease;
    }



    //接口回调
    public OnClickLisnerdisease onClickLisnerdisease;

    public void setOnClickLisnerdisease(OnClickLisnerdisease onClickLisnerdisease) {
        this.onClickLisnerdisease = onClickLisnerdisease;
    }

    public interface OnClickLisnerdisease{
        void OnClikcheck(String str);
        void OnClik(View view, int i);
        void Circle(int departmentid);
    }
}
