package Model;

import Controller.Billing;
import Controller.Customer;
import Controller.TariffTax;
import View.FileHandling;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

public class updateBill extends JFrame {

    private JPanel mainPanel;
    private JLabel lblbill;
    private JLabel lblsearch;
    private JTextField tfsearch;
    private JTable tblbill;
    private JScrollPane panelscroll;
    private JPanel panel3;
    private JPanel panel2;
    private JPanel panel1;
    private JButton btngenerate;
    private JButton btnclose;
    private JButton updateButton;
    private Billing b=new Billing();
    public updateBill() {

        FileHandling fh=new FileHandling();
        ArrayList<Billing> bills=new ArrayList<>();
        fh.loadBilling(bills);
        ArrayList<TariffTax> taxes=new ArrayList<>();
        fh.loadTariff(taxes);
        ArrayList<Customer> custs=new ArrayList<>();
        fh.loadCustomers(custs);
        Object[][] result=b.displayBilling(bills,custs);
        DefaultTableModel model = new DefaultTableModel(new Object[]{"Customer ID", "Month", "Current Regular Meter Reading", "Peak Hour Meter Reading", "Reading Entry Date", "Cost of electricity","Sales Tax","Fixed Charges","Total Amount","Status","Payment Date"}, 0);
        tblbill.setModel(model);
        tblbill.setRowHeight(30);
        for(int i=0;i<result.length;i++){
            Object[] row = new Object[]{result[i][0],result[i][1],result[i][2],result[i][3],result[i][4],result[i][5],result[i][6],result[i][7],result[i][8],result[i][9],result[i][10]};
            model.addRow(row);
        }

        btnclose.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                EmployeeScreen es=new EmployeeScreen();
                setVisible(false);
                es.showScreen();
            }
        });
        btngenerate.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int row=tblbill.getSelectedRow();
                if(row==-1){
                    JOptionPane.showMessageDialog(null,"Error! Select a row to generate bill");
                }
                else
                {
                    String mon=JOptionPane.showInputDialog("Month: ");
                    int reg=Integer.parseInt(JOptionPane.showInputDialog("Current Meter Reading Regular: "));
                    int peak=Integer.parseInt(JOptionPane.showInputDialog("Current Meter Reading Peak: "));
                    int id = Integer.parseInt(tblbill.getValueAt(row, 0).toString());
                    b.calculateBilling(bills,taxes,custs,id,mon,reg,peak,row);
                    setVisible(false);
                    updateBill ub=new updateBill();
                    ub.showScreen();
                }
            }
        });
        tfsearch.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                super.keyReleased(e);
                DefaultTableModel obj=(DefaultTableModel)tblbill.getModel();
                TableRowSorter<DefaultTableModel> obj1=new TableRowSorter<>(obj);
                tblbill.setRowSorter(obj1);
                obj1.setRowFilter(RowFilter.regexFilter(tfsearch.getText()));
            }
        });
        updateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int row=tblbill.getSelectedRow();
                if(row==-1)
                {
                    JOptionPane.showMessageDialog(null,"Error! Select a row to update bill");
                }
                else {
                    int ID= Integer.parseInt(tblbill.getValueAt(row, 0).toString());
                    String month=tblbill.getValueAt(row, 1).toString();
                    int reg=Integer.parseInt(tblbill.getValueAt(row, 2).toString());
                    int peak=Integer.parseInt(tblbill.getValueAt(row, 3).toString());
                    String entryDate=tblbill.getValueAt(row, 4).toString();
                    int cost=Integer.parseInt(tblbill.getValueAt(row, 5).toString());
                    int tax=Integer.parseInt(tblbill.getValueAt(row, 6).toString());
                    int fc=Integer.parseInt(tblbill.getValueAt(row,7).toString());
                    int amount=Integer.parseInt(tblbill.getValueAt(row,8).toString());
                    String s=tblbill.getValueAt(row,9).toString();
                    String payDate=tblbill.getValueAt(row,10).toString();
                    b.updateBilling(bills,row,ID,month,reg,peak,entryDate,cost,tax,fc,amount,s,payDate);
                    setVisible(false);
                    updateBill ub=new updateBill();
                    ub.showScreen();
                }
            }
        });
    }

    public void showScreen()
    {
        this.setTitle("Generate Bill");
        this.setVisible(true);
        this.setContentPane(this.mainPanel);
        this.setSize(1200,1000);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }
}
