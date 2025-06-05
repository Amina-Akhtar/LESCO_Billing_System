package Model;

import Controller.Customer;
import Controller.NADRA;
import View.FileHandling;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;
import javax.swing.table.TableRowSorter;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

public class expireCNIC extends JFrame{
    private JPanel mainPanel;
    private JButton btnClose;
    private JLabel lblexpire;
    private JTable tbldata;
    private JPanel panel3;
    private JPanel panel2;
    private JPanel panel1;
    private JTextField tfsearch;
    private JLabel lblsearch;
    private NADRA n=new NADRA();
    public expireCNIC() {
        FileHandling fh=new FileHandling();
        ArrayList<NADRA> nadras=new ArrayList<>();
        fh.loadNADRADB(nadras);
        ArrayList<Customer>customers=new ArrayList<>();
        fh.loadCustomers(customers);
        Object[][] result=n.viewExpiringCNICs(nadras,customers);
        DefaultTableModel model = new DefaultTableModel(new Object[]{"Name","ID","CNIC","Expiry Date","Status"}, 0);
        tbldata.setModel(model);
        tbldata.setRowHeight(30);
        TableColumnModel col=tbldata.getColumnModel();
        col.getColumn(0).setMinWidth(100);

        for(int i=0;i<result.length;i++){
            Object[] row = new Object[]{result[i][0],result[i][1],result[i][2],result[i][3],result[i][4]};
            model.addRow(row);
        }
        btnClose.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                EmployeeScreen es=new EmployeeScreen();
                setVisible(false);
                es.showScreen();
            }
        });
        tfsearch.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                super.keyReleased(e);
                DefaultTableModel obj=(DefaultTableModel)tbldata.getModel();
                TableRowSorter<DefaultTableModel>obj1=new TableRowSorter<>(obj);
                tbldata.setRowSorter(obj1);
                obj1.setRowFilter(RowFilter.regexFilter(tfsearch.getText()));
            }
        });
    }

    public void showScreen()
    {
        this.setTitle("Expiring CNICs");
        this.setVisible(true);
        this.setContentPane(this.mainPanel);
        this.setSize(1200,1000);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }
}
