package springhero.models.ObvserverPattern;

import java.util.ArrayList;

public class Observable {
    protected ArrayList<Observer> observer;


    public void attach(){}
    public void detach(){}
    public void notifyObserver(){}
}
