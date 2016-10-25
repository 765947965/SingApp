package cn.xxs.horizontalgridview.singapp;

import android.content.Context;
import android.os.Parcelable;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

public abstract class PagerBaseAdapter<T> extends PagerAdapter {
    protected LayoutInflater mInflater;
    protected Context mContext;
    protected List<T> list;
    protected final int mItemLayoutId;

    public PagerBaseAdapter(Context context, List<T> datas, int itemLayoutId) {
        mContext = context;
        mInflater = LayoutInflater.from(mContext);
        list = datas;
        mItemLayoutId = itemLayoutId;
    }


    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }

    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return this.list.size();
    }

    public T getItem(int position) {
        return list.get(position);
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        View imageLayout = this.mInflater.inflate(mItemLayoutId, container, false);
        ViewHolder viewHolder = new ViewHolder(imageLayout, null);
        try {
            convert(viewHolder, getItem(position), list, position);
        } catch (Exception e) {
            e.printStackTrace();
        }
        container.addView(imageLayout, 0);
        return viewHolder.getConvertView();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;//官方提示这样写
    }

    @Override
    public void restoreState(Parcelable state, ClassLoader loader) {
    }

    @Override
    public Parcelable saveState() {
        return null;
    }

    protected abstract void convert(ViewHolder holder, T item, List<T> list, int position);


    private ViewHolder getViewHolder(int position, ViewGroup parent) {
        return ViewHolder.get(mContext, null, parent, mItemLayoutId, position);
    }

}
