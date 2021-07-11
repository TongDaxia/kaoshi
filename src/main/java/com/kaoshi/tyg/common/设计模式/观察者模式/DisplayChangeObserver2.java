package com.kaoshi.tyg.common.设计模式.观察者模式;

import java.util.Observable;
import java.util.Observer;

public class DisplayChangeObserver2 implements Observer, DisPlayData {

    private Observable observable;
    private String data;

    /**
     * 在初始化的时候进行注册
     *
     * @param observable
     */
    public DisplayChangeObserver2(Observable observable) {
        this.observable = observable;
        observable.addObserver(this);
    }

    /**
     * This method is called whenever the observed object is changed. An
     * application calls an <tt>Observable</tt> object's
     * <code>notifyObservers</code> method to have all the object's
     * observers notified of the change.
     *
     * @param o   the observable object.
     * @param arg an argument passed to the <code>notifyObservers</code>
     */
    @Override
    public void update(Observable o, Object arg) {
        if (o instanceof DataSourceChange) {
            DataSourceChange observer = (DataSourceChange) o;

            //推的模式
            if (arg instanceof String) {
                this.data = (String) arg;
            } else {
                //拉的模式
                this.data = observer.getData();
            }
            display();
        }
    }

    @Override
    public void display() {
        System.out.println("DisplayChangeObserver2 changed:" + data);
    }



}
