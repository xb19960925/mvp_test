package com.example.frame;

public interface ConnectionView<D> {
    void success(int whichApi,int loadType,D... d);
    void error(int whichApi,Throwable pThrowable);
}
