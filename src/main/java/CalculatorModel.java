import java.util.Observer;

public interface CalculatorModel {
    void updateQuery(Operation operation);
    void updateQuery(int value);
    String displayResult();

    void addObserver(Observer observer);
}
