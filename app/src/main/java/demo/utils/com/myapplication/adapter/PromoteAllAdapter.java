package demo.utils.com.myapplication.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import demo.utils.com.myapplication.R;
import demo.utils.com.myapplication.activity.BaseActivity;
import demo.utils.com.myapplication.activity.PromoteActivity;
import demo.utils.com.myapplication.activity.PromoteGoupActivity;

/**
 * @author SuTongsheng
 * @create 2018/10/18
 * @Describe
 */
public class PromoteAllAdapter extends BaseAdapter {
    private Context mContext;
    private List<String> items;

    public PromoteAllAdapter(Context mContext, List<String> items) {
        this.mContext = mContext;
        this.items = items;
    }

    @Override
    public int getCount() {
        return items.size();
    }

    @Override
    public Object getItem(int position) {
        return items.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView == null) {
            convertView = LayoutInflater.from(mContext).inflate(R.layout.item_gv_promote_all, null);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        viewHolder.tv_item_name.setText(items.get(position));
        viewHolder.ll_item_all.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (position == 0) {//慕课
//
                } else if (position == 1) {//开发进阶PromoteGoupActivity
                    mContext.startActivity(new Intent(mContext, BaseActivity.class));
                } else if (position == 2) {//设计模式

                } else if (position == 3) {//爱上Android

                }
            }
        });
        return convertView;
    }

    class ViewHolder {
        private TextView tv_item_name;
        private final LinearLayout ll_item_all;

        public ViewHolder(View convertView) {
            tv_item_name = (TextView) convertView.findViewById(R.id.tv_item_name);
            ll_item_all = (LinearLayout) convertView.findViewById(R.id.ll_item_all);
        }
    }
}
