package springhero.models.observer;

import java.beans.PropertyChangeListener;

public interface Observable {

    void attachObserver(PropertyChangeListener newObserver);

    void detachObserver(PropertyChangeListener observer);
}