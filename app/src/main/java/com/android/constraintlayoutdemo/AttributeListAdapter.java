package com.android.constraintlayoutdemo;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.ScaleAnimation;
import android.widget.TextView;

import java.util.List;

public class AttributeListAdapter extends RecyclerView.Adapter {

    private List<Attribute> mDatas;
    private LayoutInflater mInflater;
    private ScaleAnimation mScaleAnimation;

    public AttributeListAdapter(Context context, List<Attribute> datas) {
        mDatas = datas;
        mInflater = LayoutInflater.from(context);

        mScaleAnimation = new ScaleAnimation(0f, 1f, 1f, 1f);
        mScaleAnimation.setInterpolator(new AccelerateDecelerateInterpolator());
        mScaleAnimation.setFillAfter(true);
        mScaleAnimation.setDuration(1500);
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new BaseViewHolder(mInflater.inflate(R.layout.item_attribute_list, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        bindItem((BaseViewHolder) holder, position);
    }

    @Override
    public int getItemCount() {
        return mDatas.size();
    }

    private void bindItem(BaseViewHolder holder, final int position) {
        final Attribute data = mDatas.get(position);
        final String name = data.getName();
        final int value = data.getValue();
        final int resourceId = data.getResourceId();
        final boolean hadShow = data.getHadShowAnimation();

        holder.tvName.setText(name);
        holder.tvValue.setText(String.valueOf(value));
        holder.viewAttribute.setBackgroundResource(resourceId);

        final ViewGroup.LayoutParams lp = holder.viewAttribute.getLayoutParams();
        lp.width = (int) (DisplayUtils.dip2px(value) * 0.8f);

        if (!hadShow) {
            data.setHadShowAnimation(true);
            holder.viewAttribute.startAnimation(mScaleAnimation);
        }
    }

    private class BaseViewHolder extends RecyclerView.ViewHolder {

        private TextView tvName;
        private TextView tvValue;
        private View viewAttribute;

        private BaseViewHolder(View itemView) {
            super(itemView);
            tvName = itemView.findViewById(R.id.tv_name);
            tvValue = itemView.findViewById(R.id.tv_value);
            viewAttribute = itemView.findViewById(R.id.view_attribute);
        }
    }
}
