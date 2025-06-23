package studentRecordsBackup.util;

public interface SubjectI {
    void registerObserver(ObserverI observer, FilterI filter);
    void unregisterObserver(ObserverI observer);
    void notifyAllObservers(int increment_value);
}
