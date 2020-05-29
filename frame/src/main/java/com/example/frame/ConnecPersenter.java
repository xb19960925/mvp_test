package com.example.frame;

import java.lang.ref.SoftReference;

public class ConnecPersenter<V extends ConnectionView,M extends ConnectionModel> implements ConnectionPersenter {
    private SoftReference<V> view;
    private  SoftReference<M> connectionModel;


        public ConnecPersenter(V view,M connectionModel) {
            this.view=new SoftReference<>(view);
            this.connectionModel=new SoftReference<>(connectionModel);
    }

    @Override
    public void getData(int whichApi, Object... p) {
        if (connectionModel!=null&&connectionModel.get()!=null)connectionModel.get().getNetData(this, whichApi, p);
    }

    @Override
    public void success(int whichApi, int loadType, Object... d) {
       if (view!=null&&view.get()!=null)view.get().success(whichApi, loadType, d);
    }

    @Override
    public void error(int whichApi, Throwable pThrowable) {
        if (view!=null&&view.get()!=null)view.get().error(whichApi, pThrowable);
    }
    public void clear(){
        if (view != null){
            view.clear();
            view = null;
        }
        if (connectionModel != null){
            connectionModel.clear();
            connectionModel = null;
        }
    }
}
