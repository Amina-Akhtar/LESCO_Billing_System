package Model;

import Controller.Billing;
import Controller.Customer;
import Controller.NADRA;
import View.FileHandling;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class addMeter extends JFrame {
    private JPanel mainPanel;
    private JButton btnadd;
    private JPanel panel2;
    private JTextField tfcnic;
    private JTextField tfissue;
    private JTextField tfexpiry;
    private JTextField tfname;
    private JTextField tfaddress;
    private JLabel lblcnic;
    private JLabel lblissue;
    private JLabel lblexpiry;
    private JLabel lblname;
    private JLabel lbladdress;
    private JComboBox cbcust;
    private JLabel lblcust;
    private JLabel lblphone;
    private JTextField tfphone;
    private JLabel lblmeter;
    private JComboBox cbmeter;
    private JLabel lbladdmeter;
    private JPanel panel3;
    private JPanel panel4;
    private JButton btncancel;

    public addMeter() {
         Customer c=new Customer();
        Billing b=new Billing();
        FileHandling fh=new FileHandling();
        ArrayList<NADRA> nadras=new ArrayList<>();
        fh.loadNADRADB(nadras);
        ArrayList<Customer>customers=new ArrayList<>();
        fh.loadCustomers(customers);
        ArrayList<Billing>billings=new ArrayList<>();
        fh.loadBilling(billings);
        btnadd.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String icnic = tfcnic.getText();
                String issue = tfissue.getText();
                String expiry = tfexpiry.getText();
                String name = tfname.getText();
                String address = tfaddress.getText();
                String phone = tfphone.getText();
                String meter = cbmeter.getSelectedItem().toString();
                String cust=cbcust.getSelectedItem().toString();
                if(icnic.isEmpty()||issue.isEmpty()||expiry.isEmpty()||name.isEmpty()||address.isEmpty()||phone.isEmpty()){
                    JOptionPane.showMessageDialog(null, "Error! Enter all the fields");
                }
                else if(icnic.length()!=13)

                {
                  JOptionPane.showMessageDialog(null, "Error! Enter 13 digit valid CNIC");
                  tfcnic.setText("");
                  tfcnic.requestFocus();
                }
                else if(issue.length() != 10)
                {
                    JOptionPane.showMessageDialog(null, "Error! Enter valid date (DD/MM/YYYY)");
                    tfissue.setText("");
                    tfissue.requestFocus();
                }
                else if(expiry.length() != 10 || c.isPastDate(expiry)==false)
                {
                    JOptionPane.showMessageDialog(null, "Error! Enter valid date (DD/MM/YYYY)");
                    tfexpiry.setText("");
                    tfexpiry.requestFocus();
                }
                else if(phone.length()!=11)
                {
                    JOptionPane.showMessageDialog(null, "Error! Enter valid 11 digit phone number");
                   tfphone.setText("");
                   tfphone.requestFocus();
                }
                else
                {
                    if(c.addCustomer(customers,nadras,icnic,issue,expiry,name,address,phone,meter,cust))
                    {
                    b.addBilling(billings, customers);
                    EmployeeScreen es=new EmployeeScreen();
                    setVisible(false);
                    es.showScreen();
                    }
                }

            }
        });
        btncancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                EmployeeScreen es=new EmployeeScreen();
                setVisible(false);
                es.showScreen();
            }
        });
    }

    public void showScreen()
    {
        this.setTitle("Add meter");
        this.setVisible(true);
        this.setContentPane(this.mainPanel);
        this.setSize(1200,1000);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }
}
