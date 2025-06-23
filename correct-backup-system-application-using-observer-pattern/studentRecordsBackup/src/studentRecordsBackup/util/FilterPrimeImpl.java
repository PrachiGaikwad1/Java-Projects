package studentRecordsBackup.util;

public class FilterPrimeImpl implements FilterI {
    @Override
    public boolean check(int value) {
        return PrimeChecker.isPrime(value);
    }
}
