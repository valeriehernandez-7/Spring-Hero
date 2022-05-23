package springhero.models.observer;

import java.beans.PropertyChangeListener;

/**
 * Observable interface.
 * @author <a href="https://github.com/valeriehernandez-7">Valerie M. Hernández Fernández</a>
 */
public interface Observable {

    void attachObserver(PropertyChangeListener newObserver);

    void detachObserver(PropertyChangeListener observer);
}