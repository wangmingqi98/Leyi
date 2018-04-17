package com.leyi.view.fragment.tradcordfragment;


import android.support.v4.app.Fragment;
import android.view.View;
import android.widget.ListView;

import com.leyi.R;
import com.leyi.adapter.MyAdentListAdapter;
import com.leyi.base.BaseFragment;
import com.leyi.bean.UCMoneyLogBean;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.header.ClassicsHeader;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class ChongzhiFragment extends BaseFragment {





    private ListView indent_list;
    MyAdentListAdapter myAdentListAdapter;


    List<UCMoneyLogBean.ObjectsBean.ItemBean> shouyilist;
    private SmartRefreshLayout smart;

    public ChongzhiFragment( List<UCMoneyLogBean.ObjectsBean.ItemBean> shouyilist) {
        this.shouyilist=shouyilist;
        // Required empty public constructor
    }

    @Override
    public void onClick(View v) {

    }

    @Override
    protected void initView(View view) {
        indent_list = view.findViewById(R.id.indent_list);

        myAdentListAdapter=new MyAdentListAdapter(getActivity(),shouyilist);
        indent_list.setAdapter(myAdentListAdapter);
        smart = view.findViewById(R.id.smart);
        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                smart.setRefreshHeader(new ClassicsHeader(getActivity()));
            }
        });

    }
    @Override
    protected void initData() {

    }

    @Override
    protected void updateTitleBar() {

    }

    @Override
    protected int layoutId() {
        return R.layout.fragment_chongzhi;
    }

}
