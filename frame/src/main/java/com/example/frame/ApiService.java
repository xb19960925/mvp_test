package com.example.frame;



import com.example.mydata.ListBean;

import java.util.Map;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;

public interface ApiService {

    //这是获取网络数据的链接
    String netUrl="http://static.owspace.com/";
    @GET(".")
    Observable<ListBean> getData(@QueryMap Map<String, Object> params, @Query("page_id") int page);
}
