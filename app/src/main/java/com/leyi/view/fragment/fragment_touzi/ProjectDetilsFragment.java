package com.leyi.view.fragment.fragment_touzi;


import android.graphics.drawable.BitmapDrawable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.text.Html;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.leyi.R;
import com.leyi.adapter.HetongPAdapter;
import com.leyi.base.BaseFragment;
import com.leyi.util.URLImageParser;
import com.leyi.view.activity.YouBiaoActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class ProjectDetilsFragment extends BaseFragment {


    public TextView hukuan_name;
;
    public  TextView desions;
    String content;
    String himg;

    PopupWindow popupWindow;
    private View inflate;
    private String[] split;

    private YouBiaoActivity activity;
    private ViewPager hetongbanner;
    private List hetonglist;
    private HetongPAdapter hetongPAdapter;
    private LinearLayout lin;
    private List<TextView> listdata;
    private List<ImageView> doit;
    private String substring;
    private List bigimg;
    private int finalJ;
    private int finalI;
    private int finalI1;


    public ProjectDetilsFragment(String dession,String hetong) {
        // Required empty public constructor
         this.content=dession;
         this.himg=hetong;

    }


    @Override
    public void onClick(View v) {

    }

    @Override
    protected void initView(View view) {

        hukuan_name=view.findViewById(R.id.hukuan_name);

        desions=view.findViewById(R.id.jiekuang_miaoshu);
          hetongbanner = view.findViewById(R.id.hetong_banner);
        lin = view.findViewById(R.id.lin);
       desions.setText(Html.fromHtml(content));

        Log.e(".....",himg+"");
        Log.e(".....",himg.length()+"");
        if(himg.length()!=0){
            String s = himg.replace("./public/attachment/", "http://leyibank.com//public/attachment/");
            split = s.split("</p>");
            hetonglist = new ArrayList();
            initDatas();
            initListener();
            hetongPAdapter = new HetongPAdapter(listdata);
            hetongbanner.setAdapter(hetongPAdapter);
            hetongbanner.setCurrentItem(0);



            Log.e(".....",s+"");
        }


//        for (int i = 0; i < listdata.size(); i++) {
//            listdata.get(i).setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    showNoLogin(i,);
//                }
//            });
//        }


    }

    @Override
    protected void initData() {

    }

    @Override
    protected void updateTitleBar() {

    }

    @Override
    protected int layoutId() {
        return R.layout.fragment_project_detils;
    }





    private void showNoLogin(String imgurl) {
        View  peicepop = LayoutInflater.from(getActivity()).inflate(R.layout.bantoupop,null);
        ImageView big_img = peicepop.findViewById(R.id.big_img);
        LinearLayout linearLayout = peicepop.findViewById(R.id.line);


        popupWindow = new PopupWindow(peicepop, ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT);
        popupWindow.setBackgroundDrawable(new BitmapDrawable());
        popupWindow.setFocusable(true);// 取得焦点
        //点击外部消失
        popupWindow.setOutsideTouchable(true);
        //设置可以点击
        popupWindow.setTouchable(true);
        popupWindow.setSoftInputMode(PopupWindow.INPUT_METHOD_NEEDED);

        popupWindow.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);
        popupWindow.showAtLocation(this.getActivity().getWindow().getDecorView(), Gravity.CENTER,0,0);
        // 产生背景变暗效果
        WindowManager.LayoutParams ayoutParams = getActivity().getWindow()
                .getAttributes();
        ayoutParams.alpha = 0.4f;
        getActivity().getWindow().addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
        getActivity().getWindow().setAttributes(ayoutParams);

        popupWindow.update();
        popupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {

            // 在dismiss中恢复透明度
            public void onDismiss() {
                WindowManager.LayoutParams lp = ProjectDetilsFragment.this.getActivity().getWindow()
                        .getAttributes();
                lp.alpha = 1f;
                ProjectDetilsFragment.this.getActivity().getWindow().addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
                ProjectDetilsFragment.this.getActivity().getWindow().setAttributes(lp);
            }
        });
        Glide.with(getActivity()).load(imgurl).into(big_img);

        linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                popupWindow.dismiss();
            }
        });




    }



    private void initListener() {
        hetongbanner.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                for (int i = 0; i <split.length ; i++) {
                    if((position%split.length)==i){
                        doit.get(i).setBackgroundResource(R.drawable.doitselected);
                    }else {
                        doit.get(i).setBackgroundResource(R.drawable.doitnormol);
                    }

                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });


    }



    private void initDatas() {
        listdata = new ArrayList<>();
        bigimg = new ArrayList();
        for (int i = 0; i <split.length ; i++) {
            TextView img=new TextView(getActivity());
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            layoutParams.gravity=Gravity.CENTER;
            img.setLayoutParams(layoutParams);
            img.setText(Html.fromHtml(split[i],new URLImageParser(getActivity(),img),null));
            if(i==0&&split[0].length()>30){
                substring = split[i].substring(16, 85);
                bigimg.add(0,substring);

            }else  {

                if(split[i].length()>30){
                    substring= split[i].substring(18,87);
                    Log.e("--haha-",substring+"");
                    bigimg.add(i,substring);

                }else {
                    bigimg.add(i,substring);
                }



            }




            Log.e("---",substring+"");
            listdata.add(img);


        }

        if(listdata.size()!=0){

             toso(0);
            toso(1);
            toso(2);
            toso(3);
            toso(4);
            toso(5);
            toso(6);
            toso(7);
            toso(8);
            toso(9);


        }




//        for (int j = 0; j < listdata.size(); j++) {
//            finalJ = j;
//            listdata.get(j).setOnClickListener(new View.OnClickListener() {
//        @Override
//        public void onClick(View v) {
//            showNoLogin((String) bigimg.get(finalJ));
//            return;
//        }
//    });
//        }

        doit = new ArrayList<>();
        for (int i = 0; i <split.length ; i++) {
            ImageView doitimg=new ImageView(getActivity());

            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            layoutParams.leftMargin=8;
            layoutParams.rightMargin=8;
            layoutParams.height=12;
            layoutParams.width=12;
            if(i==0){
                doitimg.setBackgroundResource(R.drawable.doitselected);
            }else {
                doitimg.setBackgroundResource(R.drawable.doitnormol);
            }
            lin.addView(doitimg,layoutParams);
            doit.add(doitimg);
        }




    }

    private void toso(final int i) {
        if(listdata.size()>i){
            listdata.get(i).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    showNoLogin((String) bigimg.get(i));
                }
            });
        }
    }


}
