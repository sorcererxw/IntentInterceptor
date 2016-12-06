package com.sorcererxw.intentinterceptor.ui.adapters;

import android.content.Context;
import android.support.v4.util.Pair;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.sorcererxw.intentinterceptor.R;
import com.sorcererxw.intentinterceptor.models.DataBean;
import com.sorcererxw.intentinterceptor.utils.HtmlBuilder;

import org.sufficientlysecure.htmltextview.HtmlTextView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @description:
 * @author: Sorcerer
 * @date: 2016/12/5
 */

@SuppressWarnings("deprecation")
public class DataAdapter extends RecyclerView.Adapter<DataAdapter.DataViewHolder> {
    @SuppressWarnings("unchecked")
    private Pair<String, Integer>[] mFlagPairs = new Pair[]{
            new Pair<>("FLAG_GRANT_READ_URI_PERMISSION", 0x00000001),
            new Pair<>("FLAG_GRANT_WRITE_URI_PERMISSION", 0x00000002),
            new Pair<>("FLAG_FROM_BACKGROUND", 0x00000004),
            new Pair<>("FLAG_DEBUG_LOG_RESOLUTION", 0x00000008),
            new Pair<>("FLAG_EXCLUDE_STOPPED_PACKAGES", 0x00000010),
            new Pair<>("FLAG_INCLUDE_STOPPED_PACKAGES", 0x00000020),
            new Pair<>("FLAG_GRANT_PERSISTABLE_URI_PERMISSION", 0x00000040),
            new Pair<>("FLAG_GRANT_PREFIX_URI_PERMISSION", 0x00000080),
            new Pair<>("FLAG_DEBUG_TRIAGED_MISSING", 0x00000100),
            new Pair<>("FLAG_ACTIVITY_NO_HISTORY", 0x40000000),
            new Pair<>("FLAG_ACTIVITY_SINGLE_TOP", 0x20000000),
            new Pair<>("FLAG_ACTIVITY_NEW_TASK", 0x10000000),
            new Pair<>("FLAG_ACTIVITY_MULTIPLE_TASK", 0x08000000),
            new Pair<>("FLAG_ACTIVITY_CLEAR_TOP", 0x04000000),
            new Pair<>("FLAG_ACTIVITY_FORWARD_RESULT", 0x02000000),
            new Pair<>("FLAG_ACTIVITY_PREVIOUS_IS_TOP", 0x01000000),
            new Pair<>("FLAG_ACTIVITY_EXCLUDE_FROM_RECENTS", 0x00800000),
            new Pair<>("FLAG_ACTIVITY_BROUGHT_TO_FRONT", 0x00400000),
            new Pair<>("FLAG_ACTIVITY_RESET_TASK_IF_NEEDED", 0x00200000),
            new Pair<>("FLAG_ACTIVITY_LAUNCHED_FROM_HISTORY", 0x00100000),
            new Pair<>("FLAG_ACTIVITY_CLEAR_WHEN_TASK_RESET", 0x00080000),
            new Pair<>("FLAG_ACTIVITY_NO_USER_ACTION", 0x00040000),
            new Pair<>("FLAG_ACTIVITY_REORDER_TO_FRONT", 0X00020000),
            new Pair<>("FLAG_ACTIVITY_NO_ANIMATION", 0X00010000),
            new Pair<>("FLAG_ACTIVITY_CLEAR_TASK", 0X00008000),
            new Pair<>("FLAG_ACTIVITY_TASK_ON_HOME", 0X00004000),
            new Pair<>("FLAG_ACTIVITY_RETAIN_IN_RECENTS", 0x00002000),
            new Pair<>("FLAG_ACTIVITY_LAUNCH_ADJACENT", 0x00001000),
            new Pair<>("FLAG_RECEIVER_REGISTERED_ONLY", 0x40000000),
            new Pair<>("FLAG_RECEIVER_REPLACE_PENDING", 0x20000000),
            new Pair<>("FLAG_RECEIVER_FOREGROUND", 0x10000000),
            new Pair<>("FLAG_RECEIVER_NO_ABORT", 0x08000000),
            new Pair<>("FLAG_RECEIVER_REGISTERED_ONLY_BEFORE_BOOT", 0x04000000),
            new Pair<>("FLAG_RECEIVER_BOOT_UPGRADE", 0x02000000),
            new Pair<>("FLAG_RECEIVER_INCLUDE_BACKGROUND", 0x01000000),
            new Pair<>("FLAG_RECEIVER_EXCLUDE_BACKGROUND", 0x00800000),
    };


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
        holder.time.setText(Html.fromHtml("<b>time: </b>" + mList.get(position).getTime()));
        holder.action.setText(new HtmlBuilder()
                .appendBold("action: ")
                .appendText(mList.get(position).getAction())
                .build());
        holder.clipData.setText(new HtmlBuilder()
                .appendBold("clipData: ")
                .appendText(mList.get(position).getClipData())
                .build());

        HtmlBuilder flagsBuilder = new HtmlBuilder().appendBold("flags: ")
                .appendText(String.format("0x%08X", mList.get(position).getFlags()));
        for (Pair<String, Integer> pair : mFlagPairs) {
            if (pair.second == mList.get(position).getFlags()) {
                flagsBuilder.appendItalic(" (" + pair.first + ")");
                break;
            }
        }
        holder.flags.setText(flagsBuilder.build());
        holder.dataString.setText(new HtmlBuilder()
                .appendBold("dataString: ")
                .appendText(mList.get(position).getDataString())
                .build());
        holder.type.setText(new HtmlBuilder()
                .appendBold("type: ")
                .appendText(mList.get(position).getType())
                .build());
        holder.componentName.setText(new HtmlBuilder()
                .appendBold("componentName: ")
                .appendText(mList.get(position).getComponentName())
                .build());
        holder.scheme.setText(new HtmlBuilder()
                .appendBold("scheme: ")
                .appendText(mList.get(position).getScheme())
                .build());
        holder.packageName.setText(new HtmlBuilder()
                .appendBold("package: ")
                .appendText(mList.get(position).getPackageX())
                .build());
        holder.requestCode.setText(new HtmlBuilder()
                .appendBold("requestCode: ")
                .appendText(mList.get(position).getRequestCode() + "")
                .build());

        HtmlBuilder categoriesBuilder = new HtmlBuilder().appendBold("categories: ");
        if (mList.get(position).getCategories() == null) {
            categoriesBuilder.appendText("null");
        } else if (mList.get(position).getCategories().size() == 0) {
            categoriesBuilder.appendText("[]");
        } else {
            categoriesBuilder.appendText("[").newLine();
            for (String c : mList.get(position).getCategories()) {
                categoriesBuilder.appendTab(1).appendText(c).appendText(",").newLine();
            }
            categoriesBuilder.appendText("]");
        }
        holder.categories.setText(categoriesBuilder.build());

        HtmlBuilder intentExtrasBuilder = new HtmlBuilder().appendBold("intentExtras: ");
        if (mList.get(position).getIntentExtras() == null) {
            intentExtrasBuilder.appendText("null");
        } else if (mList.get(position).getIntentExtras().size() == 0) {
            intentExtrasBuilder.appendText("[]");
        } else {
            intentExtrasBuilder.appendText("[").newLine();
            for (DataBean.IntentExtrasBean ie : mList.get(position).getIntentExtras()) {
                intentExtrasBuilder
                        .appendTab(1).appendText("{").newLine()
                        .appendTab(2).appendText("key: ").appendText(ie.getKey()).newLine()
                        .appendTab(2).appendText("value: ").appendText(ie.getValue()).newLine()
                        .appendTab(2).appendText("class: ").appendText(ie.getClassX()).newLine()
                        .appendTab(1).appendText("},").newLine();
            }
            intentExtrasBuilder.appendText("]");
        }
        holder.intentExtras.setText(intentExtrasBuilder.build());

        HtmlBuilder bundleBuilder = new HtmlBuilder().appendBold("bundle: ");
        if (mList.get(position).getBundle() == null) {
            bundleBuilder.appendText("null");
        } else if (mList.get(position).getBundle().size() == 0) {
            bundleBuilder.appendText("[]");
        } else {
            bundleBuilder.appendText("[").newLine();
            for (DataBean.BundleBean ie : mList.get(position).getBundle()) {
                bundleBuilder
                        .appendTab(1).appendText("{").newLine()
                        .appendTab(2).appendText("key: ").appendText(ie.getKey()).newLine()
                        .appendTab(2).appendText("value: ").appendText(ie.getValue()).newLine()
                        .appendTab(2).appendText("class: ").appendText(ie.getClassX()).newLine()
                        .appendTab(1).appendText("},").newLine();
            }
            bundleBuilder.appendText("]");
        }
        holder.bundle.setText(bundleBuilder.build());

    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    static class DataViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.textView_item_time)
        TextView time;
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
        @BindView(R.id.textView_item_categories)
        TextView categories;
        @BindView(R.id.textView_item_intentextras)
        TextView intentExtras;
        @BindView(R.id.textView_item_bundle)
        TextView bundle;

        public DataViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
