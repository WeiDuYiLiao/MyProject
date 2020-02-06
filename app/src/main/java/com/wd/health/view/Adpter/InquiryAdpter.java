package com.wd.health.view.Adpter;


import android.net.Uri;
import android.widget.ImageView;

import androidx.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.wd.health.R;
import com.wd.health.contract.InquiryBean;

import java.util.List;

/**
 * <p>文件描述：<p>
 * <p>作者：孙尔哲<p>
 * <p>创建时间：2020/1/9<p>
 * <p>更改时间：2020/1/9<p>
 */
public class InquiryAdpter extends BaseQuickAdapter<InquiryBean, BaseViewHolder> {


    private ImageView imageView;

    public InquiryAdpter(int layoutResId, @Nullable List<InquiryBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, InquiryBean item) {
           helper.setText(R.id.show_inner_name,item.departmentName);
           imageView = helper.getView(R.id.show_inner);
           Uri uri = Uri.parse(item.pic);
           imageView.setImageURI(uri);

    }
}
