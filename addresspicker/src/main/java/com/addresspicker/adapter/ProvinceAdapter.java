package com.addresspicker.adapter;

import android.graphics.Color;
import android.support.annotation.Nullable;

import com.addresspicker.R;
import com.addresspicker.model.AddressBean;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

public class ProvinceAdapter extends BaseQuickAdapter<AddressBean, BaseViewHolder> {

    public ProvinceAdapter(int layoutResId, @Nullable List<AddressBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, AddressBean item) {
        helper.setText(R.id.textview, item.getLabel());
        helper.setTextColor(R.id.textview, item.isStatus() ? Color.parseColor("#36A7FF") : Color.parseColor("#333333"));
    }
}
