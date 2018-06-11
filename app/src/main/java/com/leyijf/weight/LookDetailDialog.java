package com.leyijf.weight;

import android.app.Dialog;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Display;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;

import com.leyijf.R;
import com.leyijf.adapter.LookDetailAdapter;
import com.leyijf.bean.LookDetailBean;

import java.util.List;

/**
 * 查看明细dialog
 * Created by wmq on 2018/5/10.
 */

public class LookDetailDialog extends Dialog{
    private Context mContext;
    private Dialog dialog;
    private ImageView close;
    private LinearLayoutManager linearLayoutManager;
    private RecyclerView recyclerView;
    private LookDetailAdapter adapter;
    private List<LookDetailBean.LoadUserBean> load_user;
    public LookDetailDialog(@NonNull Context context,List<LookDetailBean.LoadUserBean> load_user) {
        super(context);
        this.mContext = context;
        this.load_user = load_user;
        dialog = new Dialog(mContext, R.style.custom_dialog2);
        linearLayoutManager = new LinearLayoutManager(mContext);
        dialog.setContentView(R.layout.dialog_lookdetail);
        close = dialog.findViewById(R.id.close);
        recyclerView = dialog.findViewById(R.id.detail_list);
        recyclerView.setLayoutManager(linearLayoutManager);
        adapter = new LookDetailAdapter(R.layout.item_look_detail,load_user);
        recyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
        WindowManager manager = (WindowManager) mContext.getSystemService(Context.WINDOW_SERVICE);
        Display display = manager.getDefaultDisplay();
        WindowManager.LayoutParams params = dialog.getWindow().getAttributes();
        params.width = display.getWidth() - 200;
        dialog.getWindow().setAttributes(params);
        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });

    }
    public void show(){
        dialog.show();
    }
    public void hide(){
        dialog.hide();
    }
    public void dismiss(){
        dialog.dismiss();
    }
}
