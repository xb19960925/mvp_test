package com.example.a5_28_mvp_test.model;

import com.example.frame.ApiConfig;
import com.example.frame.ApiService;
import com.example.frame.ConnectionModel;
import com.example.frame.ConnectionPersenter;
import com.example.frame.NetManger;
import com.example.mydata.ListBean;

import java.util.Map;


import retrofit2.Retrofit;


public class ConnecModel implements ConnectionModel {
    NetManger instance = NetManger.getInstance();
    @Override
    public void getNetData(final ConnectionPersenter connectionPersenter, final int whichApi, Object[] t) {
        switch (whichApi){
            case ApiConfig.TEST_GET:
                final int loadType = (int) t[0];
                Map param = (Map) t[1];
                int pageId = (int) t[2];
                instance.netWork(instance.getService().getData(param, pageId), connectionPersenter, whichApi, loadType);
                break;

        }



    }
}
