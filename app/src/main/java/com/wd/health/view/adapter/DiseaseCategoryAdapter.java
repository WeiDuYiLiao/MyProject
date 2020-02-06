package com.wd.health.view.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.wd.health.R;
import com.wd.health.model.Department;
import com.wd.health.model.DiseaseCategory;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

/*时间:2020/1/9
创建人:yang 
创建人:杨靖宇*/
public class DiseaseCategoryAdapter extends RecyclerView.Adapter<DiseaseCategoryAdapter.DiseaseHolder> {
    private List<DiseaseCategory> list;
    private Context context;
    private int p=0;

    public DiseaseCategoryAdapter(Context context) {
        list = new ArrayList<>();
        this.context = context;
    }

    public void addAll(List<DiseaseCategory> diseaseCategories){
        if (diseaseCategories!=null){
            list.addAll(diseaseCategories);
        }
    }

    @NonNull
    @Override
    public DiseaseHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.diseasecategory_layout, null, false);
        return new DiseaseHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DiseaseHolder holder, int position) {
        DiseaseCategory diseaseCategory = list.get(position);
        holder.disease_name.setText(diseaseCategory.name);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickLisnerDiseaseCategory.OnClik(position);
            }
        });

    }

    @Override
    public int getItemCount() {
        return list.size();
    }
    class DiseaseHolder extends RecyclerView.ViewHolder{

        private final TextView disease_name;


        public DiseaseHolder(@NonNull View itemView) {
            super(itemView);
            disease_name = itemView.findViewById(R.id.diseasecategory_name);


        }
    }
    public void categoryclear(){
        list.clear();
    }


    //接口回调
    public OnClickLisnerDiseaseCategory onClickLisnerDiseaseCategory;

    public void setOnClickLisnerDiseaseCategory(OnClickLisnerDiseaseCategory onClickLisnerDiseaseCategory) {
        this.onClickLisnerDiseaseCategory = onClickLisnerDiseaseCategory;
    }

    public interface OnClickLisnerDiseaseCategory{
        void OnClik( int i);
    }

    }
