package com.wd.health.view.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.wd.health.R;
import com.wd.health.model.DiseaseSearchVoList;
import com.wd.health.model.DrugsSearchVoList;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

/*时间:2020/2/12
创建人:yang 
创建人:杨靖宇*/
public class DiseaseSearchVoListAdapter extends RecyclerView.Adapter<DiseaseSearchVoListAdapter.HotsearchHolder> {
    private List<DiseaseSearchVoList> list;
    private Context context;

    public DiseaseSearchVoListAdapter(Context context) {
        list=new ArrayList<>();
        this.context = context;
    }
    public void addAll(List<DiseaseSearchVoList> diseaseSearchVoLists){
        if (diseaseSearchVoLists!=null){
            list.addAll(diseaseSearchVoLists);
        }
    }


    @NonNull
    @Override
    public HotsearchHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.diseasesearchvo_layout, null, false);
        return new HotsearchHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HotsearchHolder holder, int position) {
        DiseaseSearchVoList diseaseSearchVoList = list.get(position);
        holder.drugs_name.setText(diseaseSearchVoList.diseaseName);

    }

    @Override
    public int getItemCount() {
        return list.size();
    }
    class  HotsearchHolder extends RecyclerView.ViewHolder{


        private final TextView drugs_name;

        public HotsearchHolder(@NonNull View itemView) {
            super(itemView);
            drugs_name = itemView.findViewById(R.id.disease_name);
        }
    }

}
