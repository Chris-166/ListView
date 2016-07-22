package com.example.fanff.baseadapterdemo;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * 创建数据适配器
 * Created by herd_youth on 2016/4/15.
 */
public class MyBaseAdapter extends BaseAdapter{

    private List<ItemBean> mList;
    private LayoutInflater mInflater;

    // 通过构造器关联数据源与数据适配器
    public MyBaseAdapter(Context context, List<ItemBean> list){
        mList = list;
        // 使用当前要使用的界面对象context去初始化布局装载器对象mInflater
        mInflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return mList.size();
    }

    @Override
    public Object getItem(int position) {
        return mList.get(position);
    }

    // 返回指定索引对应的数据项
    @Override
    public long getItemId(int position) {
        return position;
    }


   /* *//**
     * 返回每一项对应的内容
     * 缺点：没有利用到ListView的缓存机制
     *      每次都会创建新的View，不管当前这个Item是否在屏幕上被调用过(即是否被缓存过)
     * @param position
     * @param convertView
     * @param parent
     * @return
     *//*
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // 将布局文件转为View对象
        View view = mInflater.inflate(R.layout.item, null);
        ImageView imageView = (ImageView) view.findViewById(R.id.iv_img);
        TextView title = (TextView) view.findViewById(R.id.tv_title);
        TextView content = (TextView) view.findViewById(R.id.tv_content);
        ItemBean bean = mList.get(position);
        imageView.setImageResource(bean.getItemImageResid());
        title.setText(bean.getItemContent());
        content.setText(bean.getItemContent());

        return view;
    }*/

    /**
     * 改善处：使用系统的convertView来较好的利用ListView的缓存机制，避免重复大量的创建convertView
     * 缺点：findViewById依然会浪费大量的时间去调用视图树
     * @param position
     * @param convertView
     * @param parent
     * @return
     *//*
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null){// View未被实例化，即缓冲池中无缓存才创建View
            convertView = mInflater.inflate(R.layout.item, null);
        }else{
            ImageView imageView = (ImageView) convertView.findViewById(R.id.iv_img);
            TextView title = (TextView) convertView.findViewById(R.id.tv_title);
            TextView content = (TextView) convertView.findViewById(R.id.tv_content);
            ItemBean bean = mList.get(position);
            imageView.setImageResource(bean.getItemImageResid());
            title.setText(bean.getItemContent());
            content.setText(bean.getItemContent());
        }
        return convertView;
    }*/

    /**
     * 既利用了ListView的缓存，
     * 更通过ViewHolder类来显示数据的视图的缓存，避免了多次通过findViewById寻找控件
     * @param position
     * @param convertView
     * @param parent
     * @return
     */
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView == null){// View未被实例化，即缓冲池中无缓存才创建View
            // 将控件id保存在viewHolder中
            viewHolder = new ViewHolder();
            viewHolder.imageView = (ImageView) convertView.findViewById(R.id.iv_img);
            viewHolder.title = (TextView) convertView.findViewById(R.id.tv_title);
            viewHolder.content = (TextView) convertView.findViewById(R.id.tv_content);
            // 通过setTag将ViewHolder与convertView绑定
            convertView.setTag(viewHolder);
        } else{
            // 通过ViewHolder对象找到对应控件
            viewHolder = (ViewHolder) convertView.getTag();
            ItemBean bean = mList.get(position);
            viewHolder.imageView.setImageResource(bean.getItemImageResid());
            viewHolder.title.setText(bean.getItemContent());
            viewHolder.content.setText(bean.getItemContent());
        }
        return convertView;
    }

    // 避免重复的findViewById的操作
    class ViewHolder{
        public ImageView imageView;
        public TextView title;
        public TextView content;
    }
}
