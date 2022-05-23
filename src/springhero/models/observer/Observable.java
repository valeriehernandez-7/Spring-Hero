package springhero.models.observer;

import java.beans.PropertyChangeListener;

/**
 * Observable interface.
 * @author <a href="https://github.com/valeriehernandez-7">Valerie M. Hernández Fernández</a>
 */
public interface Observable {
    /**
     *
     * @param newObserver
     */
    void attachObserver(PropertyChangeListener newObserver);

    /**
     *
     * @param observer
     */
    void detachObserver(PropertyChangeListener observer);
}