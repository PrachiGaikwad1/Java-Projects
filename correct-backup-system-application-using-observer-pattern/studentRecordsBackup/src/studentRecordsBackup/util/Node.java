package studentRecordsBackup.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Node implements SubjectI, ObserverI {
    private int bNumber;
    private String firstName;
    private List<ObserverI> observers;
    public Node left;
    public Node right;

    private Map<FilterI, List<ObserverI>> filterObserverMap = new HashMap<>();

    public Node(int bNumber, String firstName) {
        this.bNumber = bNumber;
        this.firstName = firstName;
        this.observers = new ArrayList<>();
    }

    public int getBNumber() {
        return bNumber;
    }

    public void setBNumber(int newBNumber) {
        bNumber = newBNumber;
    }

    public String getFirstName() {
        return firstName;
    }
    
    @Override
    public void registerObserver(ObserverI observer, FilterI filter) {
        if (!filterObserverMap.containsKey(filter)) {
            filterObserverMap.put(filter, new ArrayList<>());
        }
        filterObserverMap.get(filter).add(observer);
    }

    @Override
    public void unregisterObserver(ObserverI observer) {
        for (Map.Entry<FilterI, List<ObserverI>> entry : filterObserverMap.entrySet()) {
            List<ObserverI> observers = entry.getValue();
            observers.remove(observer);
        }
    }

    @Override
    public void notifyAllObservers(int incrementValue) {
        for (Map.Entry<FilterI, List<ObserverI>> entry : filterObserverMap.entrySet()) {
            FilterI filter = entry.getKey();
            if (filter.check(bNumber)) {
                for (ObserverI observer : entry.getValue()) {
                    observer.update(incrementValue);
                }
            }
        }
    }

    @Override
    public void update(int incrementValue) {
        bNumber += incrementValue;
    }

    @Override
    public String toString() {
        return bNumber + ":" + firstName + ", ";
    }
}
