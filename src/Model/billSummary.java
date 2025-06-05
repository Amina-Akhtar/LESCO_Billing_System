package Model;

import Controller.Billing;
import View.FileHandling;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class billSummary extends JFrame {
    private JLabel lblsum;
    private JTextArea tareport;
    private JButton btnClose;
    private JPanel mainPanel;

    public billSummary() {
        tareport.setAlignmentY(Component.CENTER_ALIGNMENT);
        tareport.setAlignmentY(Component.CENTER_ALIGNMENT);
        btnClose.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                EmployeeScreen es1 = new EmployeeScreen();
                setVisible(false);
                es1.showScreen();
            }
        });
    }
    public void showScreen()
    {
        this.setTitle("Bill Summary Report");
        this.setVisible(true);
        this.setContentPane(this.mainPanel);
        this.setSize(500,500);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        FileHandling fd=new FileHandling();
        ArrayList<Billing>bills=new ArrayList<>();
        fd.loadBilling(bills);
        Billing b=new Billing();

        String result=b.displayBillingSummary(bills);
        tareport.setText(result);

    }
}
