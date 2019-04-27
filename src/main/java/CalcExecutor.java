public class CalcExecutor {

    private static CalculatorModel model;
    private static CalculatorView view;
    private static CalcControler controler;

    public static void main(String[] args) {
        initClasses();
        controler.execute();
    }

    private static void initClasses() {
        model = new CalcModel();
        controler = new CalcControler();
        controler.addModel(model);
        view = new CalcView("Calculator", controler);
        controler.addView(view);
    }
}
