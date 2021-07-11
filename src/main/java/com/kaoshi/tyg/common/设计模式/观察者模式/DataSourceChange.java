package com.kaoshi.tyg.common.设计模式.观察者模式;

import java.util.Observable;

public class DataSourceChange  extends Observable {



    private String data;

    public String getData() {
        return data;
    }

    /**
     * update 入口
     * @param data
     */
    public void setData(String data) {
        this.data = data;
        dataChange();

    }

    /**
     * 拉的模式
     */
//    public void dataChange(){
//        setChanged();
//        notifyObservers();
//    }

    /**
     * 推模式
     */
    public void dataChange(){
        setChanged();
        notifyObservers(data);
    }

}
