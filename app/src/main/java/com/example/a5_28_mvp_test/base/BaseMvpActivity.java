package com.example.a5_28_mvp_test.base;

import android.os.Bundle;
import android.text.TextUtils;

import androidx.annotation.Nullable;

import com.example.frame.ConnecPersenter;
import com.example.frame.ConnectionModel;
import com.example.frame.ConnectionView;

import butterknife.ButterKnife;

public abstract class BaseMvpActivity<M extends ConnectionModel> extends BaseActivity implements ConnectionView {
    public M mModel;
    public ConnecPersenter connecPersenter;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayout());
        ButterKnife.bind(this);
        mModel = setModel();
        connecPersenter = new ConnecPersenter(this, mModel);
        initView();
        initData();
    }

    protected abstract M setModel();

    protected abstract void initData();

    protected abstract void initView();

    protected abstract int getLayout();

    public abstract void netSuccess(int whichApi, int loadType, Object[] d);
    public  void netFailed(int whichApi, Throwable pThrowable){}

    @Override
    protected void onDestroy() {
        super.onDestroy();
        connecPersenter.clear();
    }

    @Override
    public void success(int whichApi, int loadType, Object[] d) {
        netSuccess(whichApi, loadType, d);
    }

    @Override
    public void error(int whichApi, Throwable pThrowable) {
        showLog("net work error:"+whichApi+"error content:"+pThrowable!=null&& !TextUtils.isEmpty(pThrowable.getMessage())?pThrowable.getMessage():"不名错误类型");
        netFailed(whichApi, pThrowable);
    }
}
