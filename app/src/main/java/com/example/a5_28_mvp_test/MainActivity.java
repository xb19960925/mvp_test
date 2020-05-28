package com.example.a5_28_mvp_test;

import android.os.Bundle;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.frame.ApiConfig;
import com.example.frame.ConnecModel;
import com.example.frame.ConnecPersenter;
import com.example.frame.ConnectionView;
import com.example.frame.LoadTypeConfig;
import com.example.mydata.ListBean;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadMoreListener;


;import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity implements ConnectionView {

    private RecyclerView recycler;
    private ListAdapter listAdapter;
    private SmartRefreshLayout smart;
    private int pageId=0;
    private ConnecPersenter connecPersenter;
    private ConnecModel connecModel;
    private List<ListBean.DatasBean> datas;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initData();
    }

    private void initData() {
        connecModel = new ConnecModel();
        connecPersenter = new ConnecPersenter(this, connecModel);
        final Map<String, Object> params = new ParamHashMap().add("c", "api").add("a", "getList");
        smart.setOnRefreshLoadMoreListener(new OnRefreshLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                pageId++;
                connecPersenter.getData(ApiConfig.TEST_GET, LoadTypeConfig.MORE,params,pageId);
            }

            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                pageId=0;
                connecPersenter.getData(ApiConfig.TEST_GET, LoadTypeConfig.REFRESH,params,pageId);
            }
        });

        connecPersenter.getData(ApiConfig.TEST_GET, LoadTypeConfig.NORMAL,params,pageId);
    }


    private void initView() {
        smart = (SmartRefreshLayout) findViewById(R.id.smart);
        recycler = (RecyclerView) findViewById(R.id.recycler);
        recycler.setLayoutManager(new LinearLayoutManager(this));
        recycler.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        listAdapter = new ListAdapter(this);
        recycler.setAdapter(listAdapter);

    }

    private static final String TAG = "MainActivity";
    @Override
    public void success(int whichApi, int loadType, Object[] d) {
        switch (whichApi){
            case ApiConfig.TEST_GET:
                datas = ((ListBean) d[0]).getDatas();
                if (loadType == LoadTypeConfig.MORE){
                    listAdapter.setDatas(datas);
                    smart.finishLoadMore();
                } else if (loadType == LoadTypeConfig.REFRESH) {
                    listAdapter.setRefreshDatas(datas);
                    smart.finishRefresh();
                }else {
                    listAdapter.setDatas(datas);
                }
                break;
        }
    }

    @Override
    public void error(int whichApi, Throwable pThrowable) {

    }
}
