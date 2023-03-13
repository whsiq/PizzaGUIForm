import javax.swing.*;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.text.DecimalFormat;

public class PizzaGUIFrame extends JFrame {

    JPanel mainPnl;
    JPanel crustPnl;
    JPanel sizePnl;
    JPanel toppingsPnl;
    JPanel optionsPnl;

    JPanel receiptPnl;

    JRadioButton thinRB;
    JRadioButton regularRB;
    JRadioButton deepDishRB;

    JComboBox<String> sizeCB;
    String[] sizeOptions = {"Small", "Medium", "Large", "Super"};

    JCheckBox pepperoniCB;
    JCheckBox jalapenoCB;
    JCheckBox sausageCB;
    JCheckBox baconCB;
    JCheckBox pineAppleCB;
    JCheckBox mushroomCB;

    JButton orderBtn;
    JButton clearBtn;
    JButton quitBtn;

    JTextArea receiptTA;

    int reply;
    double sizePrice;
    double toppingsPrice;
    double totalPrice;
    double tax;

    DecimalFormat df = new DecimalFormat("0.00");

    public PizzaGUIFrame() {

        mainPnl = new JPanel();
        mainPnl.setLayout(new BorderLayout());

        createCrustPnl();
        mainPnl.add(crustPnl, BorderLayout.NORTH);

        createSizePnl();
        mainPnl.add(sizePnl, BorderLayout.CENTER);

        createToppingPnl();
        mainPnl.add(toppingsPnl, BorderLayout.EAST);

        createReceiptPnl();
        mainPnl.add(receiptPnl, BorderLayout.SOUTH);

        add(mainPnl);
        setSize(600, 700);
        setTitle("Krusty Krab Pizza Order");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);

    }

    private void createCrustPnl() {

        crustPnl = new JPanel();
        crustPnl.setLayout(new GridLayout(1,3));
        crustPnl.setBorder(new TitledBorder(new EtchedBorder(), "Choose Your Crust"));

        thinRB = new JRadioButton("Thin");
        regularRB = new JRadioButton("Regular");
        deepDishRB = new JRadioButton("Deep Dish");

        crustPnl.add(thinRB);
        crustPnl.add(regularRB);
        crustPnl.add(deepDishRB);
    }

    private void createSizePnl() {

        sizePnl = new JPanel();
        sizePnl.setBorder(new TitledBorder(new EtchedBorder(), "Choose Your Size"));

        sizeCB = new JComboBox<>(sizeOptions);
        sizePnl.add(sizeCB);

    }

    private void createToppingPnl() {

        toppingsPnl = new JPanel();
        toppingsPnl.setBorder(new TitledBorder(new EtchedBorder(), "Choose Your Toppings ($1.00 each)"));
        toppingsPnl.setLayout(new GridLayout(2, 3));

        pepperoniCB = new JCheckBox("Pepperoni");
        jalapenoCB = new JCheckBox("Jalapenos");
        sausageCB = new JCheckBox("Sausage");
        baconCB = new JCheckBox("Bacon");
        pineAppleCB = new JCheckBox("Pineapple");
        mushroomCB = new JCheckBox("Mushrooms");

        toppingsPnl.add(pepperoniCB);
        toppingsPnl.add(jalapenoCB);
        toppingsPnl.add(sausageCB);
        toppingsPnl.add(baconCB);
        toppingsPnl.add(pineAppleCB);
        toppingsPnl.add(mushroomCB);

    }

    private void createOptionPnl() {

        optionsPnl = new JPanel();

        orderBtn = new JButton("Order");
        orderBtn.addActionListener((ActionEvent ae) ->
                {
                    receiptTA.setText("");
                    toppingsPrice = 0;


                    if((thinRB.isSelected() || regularRB.isSelected() || deepDishRB.isSelected())
                            && (pepperoniCB.isSelected() || jalapenoCB.isSelected() || sausageCB.isSelected()
                            || baconCB.isSelected() || pineAppleCB.isSelected() || mushroomCB.isSelected())
                    ){
                        String receipt = "           Krusty Krab Pizza         \n";
                        receipt += "=====================================\n";

                        // Line 1: Type of Crust and Size

                        if(thinRB.isSelected())
                            receipt += "Thin, ";
                        else if (regularRB.isSelected())
                            receipt += "Regular, ";
                        else if (deepDishRB.isSelected())
                            receipt += "Deep Dish, ";

                        receipt += (String) sizeCB.getSelectedItem();

                        if (sizeCB.getSelectedItem() == "Small")
                            sizePrice = 8.00;
                        else if (sizeCB.getSelectedItem() == "Medium")
                            sizePrice = 12.00;
                        else if (sizeCB.getSelectedItem() == "Large")
                            sizePrice = 16.00;
                        else if (sizeCB.getSelectedItem() == "Super")
                            sizePrice = 20.00;

                        receipt += "          $"+sizePrice+"\n\n";

                        // Toppings Lines:

                        if(pepperoniCB.isSelected()) {
                            toppingsPrice += 1;
                            receipt += "Pepperoni            $1.00\n";
                        }
                        if(jalapenoCB.isSelected()) {
                            toppingsPrice += 1;
                            receipt += "Jalapenos            $1.00\n";
                        }
                        if(sausageCB.isSelected()) {
                            toppingsPrice += 1;
                            receipt += "Sausage              $1.00\n";
                        }
                        if(baconCB.isSelected()) {
                            toppingsPrice += 1;
                            receipt += "Bacon                  $1.00\n";
                        }
                        if(pineAppleCB.isSelected()) {
                            toppingsPrice += 1;
                            receipt += "Pineapple            $1.00\n";
                        }
                        if(mushroomCB.isSelected()) {
                            toppingsPrice += 1;
                            receipt += "Mushrooms          $1.00\n";
                        }
                        receipt += "\n";

                        // Get subtotal, tax, and total price
                        // Subtotal
                        totalPrice = sizePrice + toppingsPrice;
                        receipt += "Sub-total:              $"+totalPrice+"\n";

                        // Tax
                        tax = totalPrice * .07;
                        receipt += "Tax (7%):              $"+df.format(tax)+"\n";
                        receipt += "-------------------------------------------\n";
                        // Total
                        totalPrice += tax;
                        receipt += "Total:                     $"+totalPrice+"\n";
                        receipt += "=====================================";

                        receiptTA.append(receipt);
                    }

                    else if(!thinRB.isSelected()
                            && !regularRB.isSelected()
                            && !deepDishRB.isSelected()
                    )
                    {
                        receiptTA.setText("Please select a crust");
                    }

                    else if(!pepperoniCB.isSelected()
                            && !jalapenoCB.isSelected()
                            && !sausageCB.isSelected()
                            && !baconCB.isSelected()
                            && !pineAppleCB.isSelected()
                            && !mushroomCB.isSelected()
                    )
                    {
                        receiptTA.setText("Please select at least one topping");
                    }


                }
        );


        clearBtn = new JButton("Clear");
        clearBtn.addActionListener((ActionEvent ae) -> {
            receiptTA.setText("");
            thinRB.setSelected(false);
            regularRB.setSelected(false);
            deepDishRB.setSelected(false);
            sizeCB.setSelectedIndex(0);
            pepperoniCB.setSelected(false);
            jalapenoCB.setSelected(false);
            sausageCB.setSelected(false);
            baconCB.setSelected(false);
            pineAppleCB.setSelected(false);
            mushroomCB.setSelected(false);

        });

        quitBtn = new JButton("Quit");
        quitBtn.addActionListener((ActionEvent ae) -> {
            reply = JOptionPane.showConfirmDialog(null, "Are you sure you want to quit?", "Quit", JOptionPane.YES_NO_OPTION);
            if(reply == 0){
                System.exit(0);
            }
        });

        optionsPnl.add(orderBtn);
        optionsPnl.add(clearBtn);
        optionsPnl.add(quitBtn);
    }

    private void createReceiptPnl() {

        receiptPnl = new JPanel();
        receiptPnl.setLayout(new BorderLayout());
        receiptPnl.setBorder(new TitledBorder(new EtchedBorder(), "Receipt:"));

        receiptTA = new JTextArea(25,20);
        receiptTA.setEditable(false);

        createOptionPnl();

        receiptPnl.add(receiptTA, BorderLayout.CENTER);
        receiptPnl.add(optionsPnl, BorderLayout.SOUTH);

    }

}
