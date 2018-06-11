package com.leyijf.adapter;

import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Administrator on 2018/3/22.
 */

public class HetongPAdapter extends PagerAdapter {

        private List<TextView> listdata;

        public HetongPAdapter(List<TextView> listdata) {
            this.listdata = listdata;
        }

        @Override
        public int getCount() {
            return listdata.size();
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view==object;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {

            container.addView(listdata.get(position));
            return listdata.get(position);
        }


        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
//        super.destroyItem(container, position, object);
            container.removeView(listdata.get(position));
        }
    }

