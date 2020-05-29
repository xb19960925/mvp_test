package com.example.a5_28_mvp_test;

import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.a5_28_mvp_test.base.BaseMvpActivity;
import com.example.frame.ApiConfig;
import com.example.a5_28_mvp_test.model.ConnecModel;
import com.example.frame.ConnectionModel;
import com.example.frame.LoadTypeConfig;
import com.example.mydata.ListBean;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;

import java.util.List;
import java.util.Map;

;import butterknife.BindView;

public class MainActivity extends BaseMvpActivity {

    @BindView(R.id.recycler)
    RecyclerView recycler;
    @BindView(R.id.smart)
    SmartRefreshLayout smart;
    private ListAdapter listAdapter;
    private int pageId = 0;
    private List<ListBean.DatasBean> datas;
    private Map<String, Object> params;


    @Override
    protected ConnectionModel setModel() {
        return new ConnecModel();
    }

    @Override
    protected void initData() {

        connecPersenter.getData(ApiConfig.TEST_GET, LoadTypeConfig.NORMAL, params, pageId);
    }


    @Override
    protected void initView() {
        params = new ParamHashMap().add("c", "api").add("a", "getList");
        initRecyclerView(recycler, smart, mode -> {
            if (mode == LoadTypeConfig.REFRESH){
                pageId = 0;
                connecPersenter.getData(ApiConfig.TEST_GET, LoadTypeConfig.REFRESH, params, pageId);
            } else {
                pageId++;
                connecPersenter.getData(ApiConfig.TEST_GET, LoadTypeConfig.MORE, params, pageId);
            }
        });
        listAdapter = new ListAdapter(this);
        recycler.setAdapter(listAdapter);
    }


    @Override
    protected int getLayout() {
        return R.layout.activity_main;
    }

    @Override
    public void netSuccess(int whichApi, int loadType, Object[] d) {
        switch (whichApi) {
            case ApiConfig.TEST_GET:
                datas = ((ListBean) d[0]).getDatas();
                if (loadType == LoadTypeConfig.MORE) {
                    listAdapter.setDatas(datas);
                    smart.finishLoadMore();
                } else if (loadType == LoadTypeConfig.REFRESH) {
                    listAdapter.setRefreshDatas(datas);
                    smart.finishRefresh();
                } else {
                    listAdapter.setDatas(datas);
                }
                break;
        }
    }


}
