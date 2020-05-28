package com.example.frame;

import com.example.mydata.ListBean;

import java.util.Map;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class ConnecModel implements ConnectionModel {
    @Override
    public void getNetData(final ConnectionPersenter connectionPersenter, final int whichApi, Object[] t) {
        final int loadType = (int) t[0];
        Map param = (Map) t[1];
        int pageId = (int) t[2];

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ApiService.netUrl)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        Observable<ListBean> data = retrofit.create(ApiService.class).getData(param, pageId);
        data.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<ListBean>() {
                    @Override
                    public void accept(ListBean listBean) throws Exception {
                        connectionPersenter.success(whichApi, loadType,listBean);
                    }
                },new Consumer<Throwable>(){

            @Override
            public void accept(Throwable throwable) throws Exception {
                    connectionPersenter.error(whichApi, throwable);
            }
        });
    }
}
