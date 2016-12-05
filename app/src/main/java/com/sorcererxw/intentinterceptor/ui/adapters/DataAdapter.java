package com.sorcererxw.intentinterceptor.ui.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.sorcererxw.intentinterceptor.R;
import com.sorcererxw.intentinterceptor.models.DataBean;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @description:
 * @author: Sorcerer
 * @date: 2016/12/5
 */

public class DataAdapter extends RecyclerView.Adapter<DataAdapter.DataViewHolder> {
    private List<DataBean> mList = new ArrayList<>();

    private Context mContext;

    public DataAdapter(Context context) {
        mContext = context;
    }

    public void setData(List<DataBean> list) {
        mList = list;
        notifyDataSetChanged();
    }

    @Override
    public DataViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new DataViewHolder(
                LayoutInflater.from(mContext).inflate(R.layout.item_data, parent, false));
    }

    @Override
    public void onBindViewHolder(DataViewHolder holder, int position) {
        holder.action.setText(mList.get(position).getAction());
        holder.clipData.setText(mList.get(position).getClipData());
        holder.flags.setText(mList.get(position).getFlags() + "");
        holder.dataString.setText(mList.get(position).getDataString());
        holder.type.setText(mList.get(position).getType());
        holder.componentName.setText(mList.get(position).getComponentName());
        holder.scheme.setText(mList.get(position).getScheme());
        holder.packageName.setText(mList.get(position).getPackageX());
        holder.requestCode.setText(mList.get(position).getRequestCode()+"");
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    static class DataViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.textView_item_action)
        TextView action;
        @BindView(R.id.textView_item_clipdata)
        TextView clipData;
        @BindView(R.id.textView_item_flags)
        TextView flags;
        @BindView(R.id.textView_item_datastring)
        TextView dataString;
        @BindView(R.id.textView_item_type)
        TextView type;
        @BindView(R.id.textView_item_componentname)
        TextView componentName;
        @BindView(R.id.textView_item_scheme)
        TextView scheme;
        @BindView(R.id.textView_item_package)
        TextView packageName;
        @BindView(R.id.textView_item_requestcode)
        TextView requestCode;

        public DataViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
