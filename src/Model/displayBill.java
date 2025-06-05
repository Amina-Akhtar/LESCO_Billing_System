package Model;

import Controller.*;
import View.*;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Objects;

public class displayBill extends JFrame {
    private JPanel mainPanel;
    private JButton btnView;
    private JLabel lblcnic;
    private JLabel lblid;
    private JLabel lblType;
    private JTextField tfid;
    private JTextField tfcnic;
    private JComboBox<String> cbType;
    private JButton btnClose;
    private Billing bill=new Billing();
    private  String type;
    public displayBill(String t) {
        type=t;
        FileHandling myFile = new FileHandling();
        ArrayList<Billing> bills = new ArrayList<>();
        myFile.loadBilling(bills);
        ArrayList<Customer> customers = new ArrayList<>();
        myFile.loadCustomers(customers);
          cbType.addItem("1 Phase");
          cbType.addItem("2 Phase");
          btnClose.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(type=="Customer") {
                    CustomerScreen cs = new CustomerScreen();
                    setVisible(false);
                    cs.showScreen();
                }
                else {
                    EmployeeScreen es= new EmployeeScreen();
                    setVisible(false);
                    es.showScreen();
                }
            }
        });
        btnView.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String id = tfid.getText();
                String cnic = tfcnic.getText();
                String type = cbType.getSelectedItem().toString();
                if(id.isEmpty()||cnic.isEmpty()||type.isEmpty())
                {
                    JOptionPane.showMessageDialog(null, "Error! Enter all the fields");
                    tfid.setText("");
                    tfcnic.setText("");
                    cbType.setSelectedIndex(0);
                    tfid.requestFocus();
                }
                else if(cnic.length()!=13)
                {
                    JOptionPane.showMessageDialog(null,"Error! Enter a valid 13 digit CNIC!");
                    tfcnic.setText("");
                    tfcnic.requestFocus();
                }
                else
                {
                    bill.displayBillingIndividual(bills,customers,id);
                }
            }
        });
    }
    public void showScreen()
    {
        this.setTitle("Customer Dashboard");
        this.setVisible(true);
        this.setContentPane(this.mainPanel);
        this.setSize(1200,1000);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }
}
