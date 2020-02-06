package com.wd.health.view.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.wd.health.R;
import com.wd.health.model.DiseaseCategory;
import com.wd.health.model.DrugsKnowledgeList;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

/*时间:2020/1/9
创建人:yang 
创建人:杨靖宇*/
public class DrugsKnowledgeAdapter extends RecyclerView.Adapter<DrugsKnowledgeAdapter.DiseaseHolder> {
    private List<DrugsKnowledgeList> list;
    private Context context;

    public DrugsKnowledgeAdapter(Context context) {
        list = new ArrayList<>();
        this.context = context;
    }

    public void addAll(List<DrugsKnowledgeList> drugsKnowledgeLists){
        if (drugsKnowledgeLists!=null){
            this.list.addAll(drugsKnowledgeLists);
            Log.i("bbbb", list.size()+"");
        }else {
            Log.i("aaaa", "onBindViewHolder: "+"为空");
        }
    }

    @NonNull
    @Override
    public DiseaseHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.drugsknowledge_layout, null, false);
        return new DiseaseHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DiseaseHolder holder, int position) {
        DrugsKnowledgeList drugsKnowledgeList = list.get(position);
        //Log.i("aaaa", "onBindViewHolder: "+drugsKnowledgeList.picture);
        holder.drugsknowledge_img.setImageURI(drugsKnowledgeList.picture);
        holder.disease_name.setText(drugsKnowledgeList.name);
    /*    holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickLisnerDrugsKnowledge.OnClik(position);
            }
        });*/
    holder.itemView.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            onClickLisnerDrugsKnowledge.OnClik(position);
        }
    });

    }

    @Override
    public int getItemCount() {
        return list.size();
    }
    class DiseaseHolder extends RecyclerView.ViewHolder{

        private final TextView disease_name;
        private final SimpleDraweeView drugsknowledge_img;

        public DiseaseHolder(@NonNull View itemView) {
            super(itemView);
            disease_name = itemView.findViewById(R.id.drugsknowledge_name);
            drugsknowledge_img = itemView.findViewById(R.id.drugsknowledge_img);

        }
    }
    public void categoryclear(){
        list.clear();
    }
    //接口回调
    public OnClickLisnerDrugsKnowledge onClickLisnerDrugsKnowledge;

    public void setOnClickLisnerDrugsKnowledge(OnClickLisnerDrugsKnowledge onClickLisnerDrugsKnowledge) {
        this.onClickLisnerDrugsKnowledge = onClickLisnerDrugsKnowledge;
    }

    public interface OnClickLisnerDrugsKnowledge{
        void OnClik( int i);
    }

    }
