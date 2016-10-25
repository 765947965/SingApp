package cn.xxs.horizontalgridview.singapp;

import android.content.Context;
import android.text.Spanned;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;

public class ViewHolder {
    private int mPosition;
    private final SparseArray<View> mViews;
    private View mConvertView;
    private OnClickListener mOnClickListener;
    private View mItemView;

    public ViewHolder(View parent) {
        mConvertView = parent;
        mViews = new SparseArray<View>();
    }

    public ViewHolder(View parent, OnClickListener clickListener) {
        mOnClickListener = clickListener;
        mConvertView = parent;
        mViews = new SparseArray<View>();
    }


    private ViewHolder(Context context, View itemView, ViewGroup parent, int layoutId, int position) {
        this.mPosition = position;
        this.mViews = new SparseArray<View>();
        mItemView = itemView;
        mConvertView = LayoutInflater.from(context).inflate(layoutId, parent, false);
        mConvertView.setTag(this);
    }

    /**
     * 拿到一个ViewHolder对象
     *
     * @param context
     * @param convertView
     * @param parent
     * @param layoutId
     * @param position
     * @return
     */
    public static ViewHolder get(Context context, View convertView,
                                 ViewGroup parent, int layoutId, int position) {
        if (convertView == null) {
            return new ViewHolder(context, convertView, parent, layoutId, position);
        }
        return (ViewHolder) convertView.getTag();
    }

    public View getConvertView() {
        return mConvertView;
    }


    /**
     * 通过控件的Id获取对于的控件，如果没有则加入views
     *
     * @param viewId
     * @return
     */
    public <T extends View> T getView(int viewId) {
        View view = mViews.get(viewId);
        if (view == null) {
            view = mConvertView.findViewById(viewId);
            mViews.put(viewId, view);
        }
        return (T) view;
    }

    /**
     * 获取控件并点击
     *
     * @param viewId
     * @return T
     * @author longluliu
     * @date 2015-1-5 下午7:11:28
     */
    public <T extends View> T getViewAndOnClick(int viewId) {
        View view = getView(viewId);
        setClickListener(view);
        return (T) view;
    }

    /**
     * 设置点击监听
     *
     * @param viewId
     * @return void
     * @author longluliu
     * @date 2015-1-5 下午7:17:02
     */
    public View setOnClickListener(int viewId) {
        View view = getView(viewId);
        setClickListener(view);
        return view;
    }

    /**
     * 为TextView设置字符串
     *
     * @param viewId
     * @param text
     * @return
     */
    public TextView setText(int viewId, String text) {
        TextView view = getView(viewId);
        view.setText(text);
        return view;
    }

    /**
     * 为TextView设置字符串
     *
     * @param viewId
     * @param text
     * @return
     */
    public TextView setText(int viewId, CharSequence text) {
        TextView view = getView(viewId);
        if (view != null) {
            view.setText(text);
        }
        return view;
    }

    /**
     * 为TextView设置字符串
     *
     * @param viewId
     * @param text
     * @return
     */
    public TextView setText(int viewId, Spanned text) {
        TextView view = getView(viewId);
        if (view != null) {
            view.setText(text);
        }
        return view;
    }

    /**
     * 为TextView设置字体颜色
     *
     * @param viewId
     * @return
     */
    public TextView setTextColor(int viewId, int Color) {
        TextView view = getView(viewId);
        if (view != null) {
            view.setTextColor(Color);
        }
        return view;
    }

    /**
     * 为TextView设置字符串并设置点击事件
     *
     * @param viewId
     * @param text
     * @return
     */
    public TextView setTextAndOnClick(int viewId, String text) {
        TextView view = setText(viewId, text);
        setClickListener(view);
        return view;
    }

    /**
     * 为Button设置字符串
     *
     * @param viewId
     * @param text
     * @return
     */
    public Button setButtonText(int viewId, String text) {
        Button view = getView(viewId);
        if (view != null) {
            view.setText(text);
        }
        return view;
    }

    /**
     * 为Button设置字符串并设置点击事件
     *
     * @param viewId
     * @param text
     * @return
     */
    public Button setButtonAndOnClick(int viewId, String text) {
        Button view = setButtonText(viewId, text);
        setClickListener(view);
        return view;
    }

    /**
     * 为ImageView设置图片
     *
     * @param viewId
     * @param drawableId
     * @return
     */
    public ImageView setImageResource(int viewId, int drawableId) {
        ImageView view = getView(viewId);
        if (view != null) {
            view.setImageResource(drawableId);
        }
        return view;
    }

    /**
     * 设置 背景
     *
     * @param viewId
     * @param drawableId
     * @return View
     * @author longluliu
     * @date 2015-4-18 下午5:43:11
     */
    public View setBackgroundResource(int viewId, int drawableId) {
        View view = getView(viewId);
        if (view != null) {
            view.setBackgroundResource(drawableId);
        }
        return view;
    }

    /**
     * 设置图片并点击
     *
     * @param viewId
     * @param drawableId
     * @return ImageView
     * @author longluliu
     * @date 2015-1-5 下午7:19:37
     */
    public ImageView setImageResourceAndOnClick(int viewId, int drawableId) {
        ImageView view = setImageResource(viewId, drawableId);
        setClickListener(view);
        return view;
    }


    public RadioButton setRadioChecked(int viewId) {
        RadioButton view = getView(viewId);
        if (view != null) {
            view.setChecked(true);
        }
        return view;
    }


    public TextView setCheckBoxChecked(int viewId, boolean isCheck) {
        CheckBox view = getView(viewId);
        if (view != null) {
            view.setChecked(isCheck);
        }
        return view;
    }

    public View setVisibility(int viewId, int visibility) {
        View view = getView(viewId);
        view.setVisibility(visibility);
        return view;
    }

    public int getVisibility(int viewId) {
        View view = getView(viewId);
        return view.getVisibility();
    }

    /**
     * 设置点击事件
     *
     * @param view
     * @return void
     * @author longluliu
     * @date 2015-1-5 下午6:59:47
     */
    public void setClickListener(View view) {
        if (mOnClickListener != null && view != null) {
            view.setOnClickListener(mOnClickListener);
        }
    }

    public void setClickListener(OnClickListener clickListener) {
        mOnClickListener = clickListener;
    }

    public int getPosition() {
        return mPosition;
    }


    public View getmItemView() {
        return mItemView;
    }


    public void setmItemView(View mItemView) {
        this.mItemView = mItemView;
    }

}
