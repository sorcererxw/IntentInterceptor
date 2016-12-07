package com.sorcererxw.intentinterceptor.ui.adapters;

import android.app.Dialog;
import android.content.Context;
import android.support.v4.util.Pair;
import android.support.v7.widget.RecyclerView;
import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.sorcererxw.intentinterceptor.R;
import com.sorcererxw.intentinterceptor.models.DataBean;
import com.sorcererxw.intentinterceptor.utils.HtmlBuilder;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

import static android.view.View.GONE;
import static android.view.View.VISIBLE;

/**
 * @description:
 * @author: Sorcerer
 * @date: 2016/12/5
 */

@SuppressWarnings("deprecation")
public class DataAdapter extends RecyclerView.Adapter<DataAdapter.DataViewHolder> {
    @SuppressWarnings("unchecked")
    public static final Pair<String, Integer>[] FLAG_PAIRS = new Pair[]{
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

    public void clearData() {
        int count = mList.size();
        mList.clear();
        notifyItemRangeRemoved(0, count);
    }

    @Override
    public DataViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new DataViewHolder(
                LayoutInflater.from(mContext).inflate(R.layout.item_data, parent, false));
    }

    @Override
    public void onBindViewHolder(final DataViewHolder holder, int position) {

        holder.itemView
                .setOnClickListener(new DataItemOnClickListener(mContext, mList.get(position)));

        HtmlBuilder htmlBuilder = new HtmlBuilder();
        htmlBuilder.newLine(1)
                .appendBold("time: ").appendText(mList.get(position).getTime()).newLine(2)
                .appendBold("from: ").appendText(mList.get(position).getFrom()).newLine(2)
                .appendBold("to: ").appendText(mList.get(position).getTo()).newLine(1);
        holder.content.setText(htmlBuilder.build());
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    static class DataViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.textView_item_content)
        TextView content;

        public DataViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    public static class DataItemOnClickListener implements View.OnClickListener {

        private Context mContext;
        private DataBean mDataBean;

        public DataItemOnClickListener(Context context, DataBean dataBean) {
            mContext = context;
            mDataBean = dataBean;
        }

        @BindView(R.id.textView_dialog_content)
        TextView content;

        @Override
        public void onClick(View v) {
            Dialog dialog = new Dialog(mContext);
            View view = View.inflate(mContext, R.layout.dialog_data, null);
            ButterKnife.bind(this, view);
            DataBean dataBean = mDataBean;

            HtmlBuilder htmlBuilder = new HtmlBuilder();
            htmlBuilder.newLine()
                    .appendBold("time: ").appendText(dataBean.getTime()).newLine(2)
                    .appendBold("from: ").appendText(dataBean.getFrom()).newLine(2)
                    .appendBold("to: ").appendText(dataBean.getTo()).newLine(2)
                    .appendBold("action: ").appendText(dataBean.getAction()).newLine(2)
                    .appendBold("clipData: ").appendText(dataBean.getClipData()).newLine(2);
            htmlBuilder.appendBold("flags: ")
                    .appendText(String.format("0x%08X", dataBean.getFlags()));
            for (Pair<String, Integer> pair : FLAG_PAIRS) {
                if (pair.second == dataBean.getFlags()) {
                    htmlBuilder.appendItalic(" (" + pair.first + ")");
                    break;
                }
            }
            htmlBuilder.newLine();
            htmlBuilder
                    .appendBold("dataString: ").appendText(dataBean.getDataString()).newLine(2)
                    .appendBold("type: ").appendText(dataBean.getType()).newLine(2)
                    .appendBold("componentName: ").appendText(dataBean.getComponentName())
                    .newLine(2)
                    .appendBold("scheme: ").appendText(dataBean.getScheme()).newLine(2)
                    .appendBold("package: ").appendText(dataBean.getPackageX()).newLine(2)
                    .appendBold("requestCode: ").appendText(dataBean.getRequestCode() + "")
                    .newLine(2);

            htmlBuilder.appendBold("categories: ");
            if (dataBean.getCategories() == null) {
                htmlBuilder.appendText("null");
            } else if (dataBean.getCategories().size() == 0) {
                htmlBuilder.appendText("[]");
            } else {
                htmlBuilder.appendText("[").newLine();
                for (String c : dataBean.getCategories()) {
                    htmlBuilder.appendTab(1).appendText(c).appendText(",").newLine();
                }
                htmlBuilder.appendText("]");
            }
            htmlBuilder.newLine(2);

            htmlBuilder.appendBold("intentExtras: ");
            if (dataBean.getIntentExtras() == null) {
                htmlBuilder.appendText("null");
            } else if (dataBean.getIntentExtras().size() == 0) {
                htmlBuilder.appendText("[]");
            } else {
                htmlBuilder.appendText("[").newLine();
                for (DataBean.IntentExtrasBean ie : dataBean.getIntentExtras()) {
                    htmlBuilder
                            .appendTab(1).appendText("{").newLine()
                            .appendTab(2).appendText("key: ").appendText(ie.getKey()).newLine()
                            .appendTab(2).appendText("value: ").appendText(ie.getValue())
                            .newLine()
                            .appendTab(2).appendText("class: ").appendText(ie.getClassX())
                            .newLine()
                            .appendTab(1).appendText("},").newLine();
                }
                htmlBuilder.appendText("]");
            }
            htmlBuilder.newLine(2);

            htmlBuilder.appendBold("bundle: ");
            if (dataBean.getBundle() == null) {
                htmlBuilder.appendText("null");
            } else if (dataBean.getBundle().size() == 0) {
                htmlBuilder.appendText("[]");
            } else {
                htmlBuilder.appendText("[").newLine();
                for (DataBean.BundleBean ie : dataBean.getBundle()) {
                    htmlBuilder
                            .appendTab(1).appendText("{").newLine()
                            .appendTab(2).appendText("key: ").appendText(ie.getKey()).newLine()
                            .appendTab(2).appendText("value: ").appendText(ie.getValue())
                            .newLine()
                            .appendTab(2).appendText("class: ").appendText(ie.getClassX())
                            .newLine()
                            .appendTab(1).appendText("},").newLine();
                }
                htmlBuilder.appendText("]");
            }
            htmlBuilder.newLine();
            content.setText(htmlBuilder.build());

            dialog.setContentView(view);
            if (dialog.getWindow() != null) {
                dialog.getWindow().setLayout(WindowManager.LayoutParams.MATCH_PARENT,
                        WindowManager.LayoutParams.MATCH_PARENT);
            }
            dialog.show();
        }
    }
}
