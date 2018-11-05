package demo.utils.com.myapplication.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import demo.utils.com.myapplication.R;
import demo.utils.com.myapplication.entity.People;
import demo.utils.com.myapplication.activity.DataBaseActivity;

/**
 * Created by sts on 2018/2/28.
 */

public class MyAdapter extends BaseAdapter {
    private List<People> mPeopleLists;
    private Context mContext;

    public MyAdapter(DataBaseActivity dataBaseActivity,  List<People> peopleList) {
        mPeopleLists = peopleList;
        mContext = dataBaseActivity;
    }

    @Override
    public int getCount() {
        return mPeopleLists.size();
    }

    @Override
    public Object getItem(int position) {
        return mPeopleLists.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if(convertView==null){
            LayoutInflater inflater = LayoutInflater.from(mContext);
            convertView =inflater.inflate(R.layout.database_item,null);
            viewHolder = new ViewHolder();

            viewHolder.tv_database_all=(TextView) convertView.findViewById(R.id.tv_database_all);
            String singlePeople=mPeopleLists.get(position).toString();
            viewHolder.tv_database_all.setText(singlePeople);
            convertView.setTag(viewHolder);

        }else {
            viewHolder =(ViewHolder)convertView.getTag();
        }
        return convertView;

}

    //避免了就是每次在getVIew的时候，都需要重新的findViewById，
    // 重新找到控件，然后进行控件的赋值以及事件相应设置。这样其实在做重复的事情)
    class ViewHolder{
        TextView tv_database_all;

    }
}
