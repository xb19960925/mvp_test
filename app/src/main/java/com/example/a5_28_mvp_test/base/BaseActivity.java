package com.example.a5_28_mvp_test.base;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.a5_28_mvp_test.interfaces.DataListener;
import com.example.frame.LoadTypeConfig;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

public class BaseActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }
    public void initRecyclerView(RecyclerView pRecyclerView, SmartRefreshLayout pRefreshLayout, final DataListener pDataListener){
        if (pRecyclerView != null)pRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        if (pRefreshLayout !=null){
            pRefreshLayout.setOnRefreshListener(new OnRefreshListener() {
                @Override
                public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                    if (pDataListener != null)pDataListener.dataType(LoadTypeConfig.REFRESH);
                }
            });
            pRefreshLayout.setOnLoadMoreListener(new OnLoadMoreListener() {
                @Override
                public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                    if (pDataListener != null)pDataListener.dataType(LoadTypeConfig.MORE);
                }
            });
        }
    }

    public void showLog(Object content){
        Log.e("呲牙", content.toString() );
    }

    public void showToast(Object content){
        Toast.makeText(getApplicationContext(), content.toString(), Toast.LENGTH_SHORT).show();
    }
}
