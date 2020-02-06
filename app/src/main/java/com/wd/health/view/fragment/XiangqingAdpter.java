package com.wd.health.view.fragment;

import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.wd.health.R;
import com.wd.health.contract.XiangqingBase;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * <p>文件描述：<p>
 * <p>作者：孙尔哲<p>
 * <p>创建时间：2020/1/9<p>
 * <p>更改时间：2020/1/9<p>
 */
public class XiangqingAdpter  extends RecyclerView.Adapter {

     private List<XiangqingBase>  list;
     private Context  context;

    public XiangqingAdpter( Context context) {
        list = new ArrayList<>();
        this.context = context;
    }

     public   void   addAll(List<XiangqingBase> data){
         if (data!=null){
              list.addAll(data);
         }
     }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {

        if (i == 0) {

            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.information_img_null_layout, parent, false);
            return new ImgNullViewHolder(view);

        } else if (i == 2) {

            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.information_img_three_layout, parent, false);
            return new ImgThreeViewHolder(view);

        } else if (i == 3) {

            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.information_img_one_layout, parent, false);
            return new ImgOneViewHolder(view);

        } else {
            return null;
        }
    }

    @Override
    public int getItemViewType(int position) {
        XiangqingBase xiangqingBase = list.get(position);
        String[] split = xiangqingBase.thumbnail.split(";");
        int imgSize = split.length;
        if (imgSize==0){
            return 0;
        }else if (imgSize ==3){
            return 2;
        }else {
            return 3;
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        int viewType = holder.getItemViewType();
        XiangqingBase xiangqingBase = list.get(position);

        String[] split = xiangqingBase.thumbnail.split(";");


        Date date = new Date(xiangqingBase.releaseTime);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");

        if (viewType == 0) {

            ImgNullViewHolder imgNullViewHolder = (ImgNullViewHolder) holder;
            imgNullViewHolder.information_img_null_title.setText(xiangqingBase.title);

            imgNullViewHolder.information_img_null_releaseTime.setText(simpleDateFormat.format(date));

            imgNullViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    jing.gg(position);
                }
            });


        } else if (viewType == 2) {

            ImgThreeViewHolder imgThreeViewHolder = (ImgThreeViewHolder) holder;
            imgThreeViewHolder.information_img_three_title.setText(xiangqingBase.title);
            imgThreeViewHolder.information_img_three_releaseTime.setText(simpleDateFormat.format(date));

            Uri uri = Uri.parse(split[0]);
            imgThreeViewHolder.information_img_three_img1.setImageURI(uri);

            Uri uri1 = Uri.parse(split[1]);
            imgThreeViewHolder.information_img_three_img2.setImageURI(uri1);

            Uri uri2 = Uri.parse(split[2]);
            imgThreeViewHolder.information_img_three_img3.setImageURI(uri2);

            imgThreeViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    jing.gg(position);
                }
            });

        } else if (viewType == 3) {

            ImgOneViewHolder imgOneViewHolder = (ImgOneViewHolder) holder;
            imgOneViewHolder.information_img_one_title.setText(xiangqingBase.title);

            imgOneViewHolder.information_img_one_releaseTime.setText(simpleDateFormat.format(date));

            Uri uri = Uri.parse(split[0]);
            imgOneViewHolder.information_img_one_img.setImageURI(uri);


            imgOneViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    jing.gg(position);
                }
            });


        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public void clear() {
        list.clear();
    }
    public class ImgNullViewHolder extends RecyclerView.ViewHolder {

        private final TextView information_img_null_title;
        private final TextView information_img_null_source;
        private final TextView information_img_null_releaseTime;
        public WebView condet_context;

        public ImgNullViewHolder(@io.reactivex.annotations.NonNull View itemView) {
            super(itemView);
            information_img_null_title = itemView.findViewById(R.id.information_img_null_title);
            information_img_null_source = itemView.findViewById(R.id.information_img_null_source);
            information_img_null_releaseTime = itemView.findViewById(R.id.information_img_null_releaseTime);
        }
    }

    public class ImgOneViewHolder extends RecyclerView.ViewHolder {

        private final SimpleDraweeView information_img_one_img;
        private final TextView information_img_one_title;
        private final TextView information_img_one_releaseTime;
        private final TextView information_img_one_source;
        public WebView condet_context;

        public ImgOneViewHolder(@io.reactivex.annotations.NonNull View itemView) {
            super(itemView);
            information_img_one_title = itemView.findViewById(R.id.information_img_one_title);
            information_img_one_img = itemView.findViewById(R.id.information_img_one_img);
            information_img_one_releaseTime = itemView.findViewById(R.id.information_img_one_releaseTime);
            information_img_one_source = itemView.findViewById(R.id.information_img_one_source);
        }
    }

    public class ImgThreeViewHolder extends RecyclerView.ViewHolder {

        private final TextView information_img_three_title;
        private final TextView information_img_three_source;
        private final TextView information_img_three_releaseTime;
        private final SimpleDraweeView information_img_three_img1;
        private final SimpleDraweeView information_img_three_img2;
        private final SimpleDraweeView information_img_three_img3;

        public ImgThreeViewHolder(@io.reactivex.annotations.NonNull View itemView) {
            super(itemView);
            information_img_three_title = itemView.findViewById(R.id.information_img_three_title);
            information_img_three_source = itemView.findViewById(R.id.information_img_three_source);
            information_img_three_releaseTime = itemView.findViewById(R.id.information_img_three_releaseTime);
            information_img_three_img1 = itemView.findViewById(R.id.information_img_three_img1);
            information_img_three_img2 = itemView.findViewById(R.id.information_img_three_img2);
            information_img_three_img3 = itemView.findViewById(R.id.information_img_three_img3);
        }
    }

    public Jing jing;

    public void setJing(Jing jing) {
        this.jing = jing;
    }

    public interface  Jing{
        void gg(int id);
    }
}
