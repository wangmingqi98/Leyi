package com.leyijf.weight;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ListAdapter;
import android.widget.ListView;

/**
 * Created by wmq on 2018/5/17.
 */

public class CustomListView extends ListView {
    private int height;
    public CustomListView(Context context, AttributeSet attrs, int defStyle) {

        super(context, attrs, defStyle);

        // TODO Auto-generated constructor stub

    }

    public CustomListView(Context context, AttributeSet attrs) {

        super(context, attrs);

        // TODO Auto-generated constructor stub

    }

    public CustomListView(Context context) {

        super(context);

        // TODO Auto-generated constructor stub

    }
    @Override
    public void setAdapter(ListAdapter adapter) {
        super.setAdapter(adapter);
        View item = adapter.getView(0, null, null);
        item.measure(MeasureSpec.makeMeasureSpec(0, MeasureSpec.UNSPECIFIED), MeasureSpec.makeMeasureSpec(0, MeasureSpec.UNSPECIFIED));
        int itemHeight = item.getMeasuredHeight();
        int count = adapter.getCount();
        height = itemHeight * count;
        measure(MeasureSpec.makeMeasureSpec(0, MeasureSpec.UNSPECIFIED), height);
    }

    public int getRealHeight(){
        return height;
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int expandSpec = MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE >> 2,MeasureSpec.AT_MOST);
        super.onMeasure(widthMeasureSpec, expandSpec);
    }
    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        if(ev.getAction() == MotionEvent.ACTION_MOVE){
            return true;
        }
        return super.dispatchTouchEvent(ev);
    }
}
