package com.leyi.view.fragment.fragment_touzi;


import android.support.v4.app.Fragment;
import android.view.View;
import android.widget.TextView;

import com.leyi.R;
import com.leyi.base.BaseFragment;
import com.leyi.bean.ToubiaoDetilsBean;

/**
 * A simple {@link Fragment} subclass.
 */
public class FengxianControFragment extends BaseFragment {
ToubiaoDetilsBean toubiaoDetilsBean;
    private TextView textView;

    public FengxianControFragment(ToubiaoDetilsBean toubiaoDetilsBean) {
        // Required empty public constructor


        this.toubiaoDetilsBean=toubiaoDetilsBean;

    }




    @Override
    public void onClick(View v) {

    }

    @Override
    protected void initView(View view) {
        textView = view.findViewById(R.id.fengxian);
    }

    @Override
    protected void initData() {
textView.setText(toubiaoDetilsBean.getRisk_security()+"");
    }

    @Override
    protected void updateTitleBar() {

    }

    @Override
    protected int layoutId() {
        return R.layout.fragment_fengxian_contro;
    }

}
