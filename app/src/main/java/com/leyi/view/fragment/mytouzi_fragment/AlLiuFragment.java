package com.leyi.view.fragment.mytouzi_fragment;


import android.support.v4.app.Fragment;
import android.view.View;
import android.widget.ListView;

import com.leyi.R;
import com.leyi.adapter.MyTouZiAdapter;
import com.leyi.base.BaseFragment;
import com.leyi.bean.UCLendBean;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class AlLiuFragment extends BaseFragment {



    List<UCLendBean> lendBeanList;


    private ListView indent_list;
    MyTouZiAdapter myAdentListAdapter;


    List<UCLendBean> lendBeen;

    public AlLiuFragment(List<UCLendBean> lendBeen) {
        // Required empty public constructor
        this.lendBeen=lendBeen;


    }

    @Override
    public void onClick(View v) {

    }

    @Override
    protected void initView(View view) {
        lendBeanList=new ArrayList<>();
        if(lendBeen.size()==0){

        }else {
            for (int i = 0; i < lendBeen.size(); i++) {
                if(lendBeen.get(i).getDeal_status().equals("3")){
                    lendBeanList.add(lendBeen.get(i));
                }


            }

            indent_list = view.findViewById(R.id.indent_list);
            myAdentListAdapter=new MyTouZiAdapter(getActivity(),lendBeanList);
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
        return R.layout.fragment_al_liu;
    }

}
