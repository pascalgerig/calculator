import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.util.LinkedList;
import java.util.Observer;
import javax.swing.*;
import java.util.List;

//TODO include WindowAdapter
public class CalcView extends JFrame implements Observer, CalculatorView{
    protected CalcModel model;
    protected CalcControler controler;

    //Panels
    protected JPanel panel;
    protected JPanel leftPanel;
    protected JPanel leftTopPanel;
    protected JPanel leftBottomPanel;
    protected JPanel rightPanel;

    //Labels
    protected JLabel result;

    //Operation Buttons
    protected List<JButton> operationButtonList;

    //Number Buttons
    protected List<JButton> numberButtonList;

    public CalcView(String title, CalcControler controler) {
        super(title);
        this.setSize(500, 500);

        this.controler = controler;
        this.controler.addObserver(this);

        this.panel = new JPanel(new GridLayout(0, 2, 0, 10));
        add(this.panel);

        this.initComponentLists();
        this.setup();

        //this.show();
    }

    //TODO outdated
    public CalcView(String title) throws HeadlessException {
        super(title);
        this.setSize(500, 500);

        this.model = new CalcModel();
        this.model.addObserver(this);

        this.panel = new JPanel(new GridLayout(0, 2, 0, 10));
        add(this.panel);

        this.initComponentLists();
        this.setup();

        this.show();
    }

    protected void initComponentLists() {
        this.operationButtonList = new LinkedList<JButton>();
        this.numberButtonList = new LinkedList<JButton>();
    }

    protected void setup() {
        this.setupleft();
        this.setupRight();
        panel.add(leftPanel);
        panel.add(rightPanel);
    }

    protected void setupleft() {
        this.leftPanel = new JPanel(new GridLayout(2, 0, 20, 20));
        this.leftTopPanel = new JPanel(new GridLayout(4, 3, 10, 10));
        this.leftBottomPanel = new JPanel(new GridLayout(2, 0, 10, 10));
        this.addNumbers();
        this.addOperations();
        leftPanel.add(leftTopPanel);
        leftPanel.add(leftBottomPanel);
    }

    protected void setupRight() {
        this.rightPanel = new JPanel();
        this.result = new JLabel("0");
        this.rightPanel.add(this.result);
    }

    protected void addOperations() {
        JButton plus = new JButton("+");
        plus.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                controler.updateQuery(Operation.ADD);
            }
        });
        JButton minus = new JButton("-");
        minus.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                controler.updateQuery(Operation.SUB);
            }
        });
        JButton mult = new JButton("*");
        mult.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                controler.updateQuery(Operation.MULT);
            }
        });
        JButton div = new JButton("/");
        div.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                controler.updateQuery(Operation.DIV);
            }
        });
        JButton mod = new JButton("mod");
        mod.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                controler.updateQuery(Operation.MOD);
            }
        });
        JButton equals = new JButton("=");
        equals.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                controler.displayResult();
            }
        });

        operationButtonList.add(plus);
        operationButtonList.add(minus);
        operationButtonList.add(mult);
        operationButtonList.add(div);
        operationButtonList.add(mod);
        operationButtonList.add(equals);


        for(JButton operator : this.operationButtonList) {
            leftBottomPanel.add(operator);
        }
    }

    protected void addNumbers() {
        JButton one = new JButton("1");
        one.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                controler.updateQuery(1);
            }
        });
        JButton two = new JButton("2");
        two.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                controler.updateQuery(2);
            }
        });
        JButton three = new JButton("3");
        three.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                controler.updateQuery(3);
            }
        });
        JButton four = new JButton("4");
        four.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                controler.updateQuery(4);
            }
        });
        JButton five = new JButton("5");
        five.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                controler.updateQuery(5);
            }
        });
        JButton six = new JButton("6");
        six.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                controler.updateQuery(6);
            }
        });
        JButton seven = new JButton("7");
        seven.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                controler.updateQuery(7);
            }
        });
        JButton eight = new JButton("8");
        eight.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                controler.updateQuery(8);
            }
        });
        JButton nine = new JButton("9");
        nine.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                controler.updateQuery(9);
            }
        });
        JButton zero = new JButton("0");
        zero.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                controler.updateQuery(0);
            }
        });

        numberButtonList.add(one);
        numberButtonList.add(two);
        numberButtonList.add(three);
        numberButtonList.add(four);
        numberButtonList.add(five);
        numberButtonList.add(six);
        numberButtonList.add(seven);
        numberButtonList.add(eight);
        numberButtonList.add(nine);
        numberButtonList.add(zero);

        for (JButton number : numberButtonList) {
            leftTopPanel.add(number);
        }
    }

    //This is called if the model decides to notify its observer
    public void update(java.util.Observable o, Object arg) {
        this.result.setText((String) arg);
    }

    public void display() {
        this.show();
    }
}
