package com.example.frame;

public class ConnecPersenter implements ConnectionPersenter {
    private ConnectionView view;
    private  ConnectionModel connectionModel;


    public ConnecPersenter(ConnectionView view,ConnectionModel connectionModel) {
        this.view = view;
        this.connectionModel=connectionModel;
    }

    @Override
    public void getData(int whichApi, Object... p) {
        connectionModel.getNetData(this, whichApi, p);
    }

    @Override
    public void success(int whichApi, int loadType, Object... d) {
        view.success(whichApi, loadType, d);
    }

    @Override
    public void error(int whichApi, Throwable pThrowable) {
        view.error(whichApi, pThrowable);
    }
}
