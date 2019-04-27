import java.util.Observer;

public class CalcControler {
    private CalculatorModel model;
    private CalculatorView view;

    public CalcControler() {

    }

    public void addModel(CalculatorModel model) {
        this.model = model;
    }

    public void addView(CalculatorView view) {
        this.view = view;
    }

    public synchronized void addObserver(Observer observer) {
        this.model.addObserver(observer);
    }

    public void updateQuery(Operation operation) {
        this.model.updateQuery(operation);
    }

    public void displayResult() {
        this.model.displayResult();
    }

    public void updateQuery(int value) {
        this.model.updateQuery(value);
    }

    public void execute() {
        this.view.display();
    }
}
