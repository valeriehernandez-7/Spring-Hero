package springhero.models.ObvserverPattern;

import springhero.models.main.Cell;

public interface Observable {
    void attach(Observer o);
    void detach(Observer o);
    void notifyObserver(Cell cell);
}
