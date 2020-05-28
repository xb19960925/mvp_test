package com.example.frame;

public interface ConnectionPersenter<P> extends ConnectionView{
    void getData(int whichApi,P... p);
}
