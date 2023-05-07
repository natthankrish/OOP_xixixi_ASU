package org.example.program.entities.commodities;

import org.example.program.entities.bills.Observer;

public interface Subject {
    void registerObserver(Observer observer);
    void removeObserver(Observer observer);
    void notifyObservers();
}