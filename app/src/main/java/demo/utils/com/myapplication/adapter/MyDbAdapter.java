package demo.utils.com.myapplication.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import demo.utils.com.myapplication.R;
import demo.utils.com.myapplication.entity.RenBean;

/**
 * Created by sts on 2018/3/21.
 */

public class MyDbAdapter extends BaseAdapter {
    private Context mContext;
    private List<RenBean> mRens;


    public MyDbAdapter(Context context, List<RenBean> rens) {
        mContext = context;
        mRens = rens;
    }

    @Override
    public int getCount() {
        return mRens.size();
    }

    @Override
    public Object getItem(int i) {
        return mRens.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        LayoutInflater layoutInflater = LayoutInflater.from(mContext);
        View convertView = layoutInflater.inflate(R.layout.view_ren_item, null);
        TextView tv_name = (TextView) convertView.findViewById(R.id.tv_name);
        TextView tv_age = (TextView) convertView.findViewById(R.id.tv_age);
        TextView tv_phone = (TextView) convertView.findViewById(R.id.tv_phone);

        tv_name.setText(mRens.get(i).getName());
        tv_age.setText(mRens.get(i).getAge() + "");
        tv_phone.setText(mRens.get(i).getPhone());

        return convertView;
    }
}
