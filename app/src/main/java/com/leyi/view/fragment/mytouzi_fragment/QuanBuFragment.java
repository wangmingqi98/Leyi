package com.leyi.view.fragment.mytouzi_fragment;


import android.support.v4.app.Fragment;
import android.view.View;
import android.widget.ListView;

import com.leyi.R;
import com.leyi.adapter.MyTouZiAdapter;
import com.leyi.base.BaseFragment;
import com.leyi.bean.UCLendBean;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class QuanBuFragment extends BaseFragment {
List<UCLendBean> lendBeen;





    private ListView indent_list;
    MyTouZiAdapter myAdentListAdapter;
    public QuanBuFragment(List<UCLendBean> lendBeen) {
        // Required empty public constructor
        this.lendBeen=lendBeen;


    }

    @Override
    public void onClick(View v) {

    }

    @Override
    protected void initView(View view) {
        if(lendBeen.size()==0){

        }else {
            indent_list = view.findViewById(R.id.indent_list);
            myAdentListAdapter=new MyTouZiAdapter(getActivity(),lendBeen);
            indent_list.setAdapter(myAdentListAdapter);
        }

    }
    @Override
    protected void initData() {




    }

    @Override
    protected void updateTitleBar() {

    }

    @Override
    protected int layoutId() {
        return R.layout.fragment_quan_bu;
    }

}
