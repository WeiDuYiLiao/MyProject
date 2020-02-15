package com.wd.health.view.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.wd.health.R;
import com.wd.health.model.DoctorSearchVoList;
import com.wd.health.model.PopularSearch;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

/*时间:2020/2/12
创建人:yang 
创建人:杨靖宇*/
public class DoctorSearchVoListAdapter extends RecyclerView.Adapter<DoctorSearchVoListAdapter.HotsearchHolder> {
    private List<DoctorSearchVoList> list;
    private Context context;

    public DoctorSearchVoListAdapter(Context context) {
        list=new ArrayList<>();
        this.context = context;
    }
    public void addAll(List<DoctorSearchVoList> doctorSearchVoLists){
        if (doctorSearchVoLists!=null){
            list.addAll(doctorSearchVoLists);
        }
    }


    @NonNull
    @Override
    public HotsearchHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.doctorsearchvo_layout, null, false);
        return new HotsearchHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HotsearchHolder holder, int position) {
        DoctorSearchVoList doctorSearchVoList = list.get(position);
       // Log.i("aaaa", "onBindViewHolder: "+doctorSearchVoList.doctorName);
          holder.doctor_name.setText(doctorSearchVoList.doctorName);

    }

    @Override
    public int getItemCount() {
        return list.size();
    }
    class  HotsearchHolder extends RecyclerView.ViewHolder{


        private final TextView doctor_name;

        public HotsearchHolder(@NonNull View itemView) {
            super(itemView);
            doctor_name = itemView.findViewById(R.id.doctor_name);
        }
    }

}
