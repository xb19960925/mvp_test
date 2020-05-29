package com.example.frame;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class NetManger {
    private NetManger() {
    }

    private static volatile NetManger sNetManger;

    public static NetManger getInstance() {
        if (sNetManger == null) {
            synchronized (NetManger.class) {
                sNetManger = new NetManger();
            }
        }
        return sNetManger;
    }

    public <T> ApiService getService(T... t) {
        String baseUrl = ServerAddressConfig.BASE_URL;
        if (t != null && t.length != 0) {
            baseUrl = (String) t[0];
        }
        return new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
//                .client(NetInterceptor.getNetInterceptor().getClientWithoutCache())
                .client(new OkHttpClient())
                .build()
                .create(ApiService.class);
    }

        public <T, O> void netWork(Observable<T> localTestInfo, final ConnectionPersenter pPresenter, final int whichApi, final int dataType, O... o) {
        localTestInfo.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseOberver() {
                    @Override
                    public void onSuccess(Object value) {
                        pPresenter.success(whichApi, dataType, value, o);
                    }

                    @Override
                    public void onFailed(Throwable e) {
                        pPresenter.error(whichApi, e);
                    }
                });
    }
}
