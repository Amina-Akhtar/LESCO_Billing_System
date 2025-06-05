package Model;

import Controller.Billing;
import Controller.Customer;
import View.FileHandling;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;
import javax.swing.table.TableRowSorter;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

public class payBill extends JFrame {

    private JPanel mainPanel;
    private JLabel lblsearch;
    private JTextField tfsearch;
    private JScrollPane panelscroll;
    private JPanel panel2;
    private JPanel panel3;
    private JPanel panel1;
    private JLabel lblpaybill;
    private JTable tblbill;
    private JButton btnclose;
    private JButton btnpayill;
    private Billing b=new Billing();

    public payBill() {
        FileHandling fh=new FileHandling();
        ArrayList<Billing> billings=new ArrayList<>();
        fh.loadBilling(billings);
         ArrayList<Customer>customers=new ArrayList<>();
         fh.loadCustomers(customers);
        Object[][] result=b.displayBilling(billings,customers);
        DefaultTableModel model = new DefaultTableModel(new Object[]{"Customer ID", "Month", "Current Regular Meter Reading", "Peak Hour Meter Reading", "Reading Entry Date", "Cost of electricity","Sales Tax","Fixed Charges","Total Amount","Status","Payment Date"}, 0);
        tblbill.setModel(model);
        tblbill.setRowHeight(30);
        for(int i=0;i<result.length;i++){
            Object[] row = new Object[]{result[i][0],result[i][1],result[i][2],result[i][3],result[i][4],result[i][5],result[i][6],result[i][7],result[i][8],result[i][9],result[i][10]};
            model.addRow(row);
        }
        btnpayill.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int row = tblbill.getSelectedRow();
                if (row == -1) {
                    JOptionPane.showMessageDialog(null, "Error! Select a row to update");
                } else {
                    String status = tblbill.getValueAt(row, 9).toString();
                    int id = Integer.parseInt(tblbill.getValueAt(row, 0).toString());
                    b.payBilling(billings,customers,row,status,id);
                    setVisible(false);
                    payBill pb=new payBill();
                    pb.showScreen();
                }
            }
        });

        TableColumnModel col=tblbill.getColumnModel();
        DefaultTableCellRenderer center=new DefaultTableCellRenderer();
        center.setHorizontalAlignment(JLabel.CENTER);
        col.getColumn(1).setCellRenderer(center);
        col.getColumn(2).setCellRenderer(center);
        col.getColumn(3).setCellRenderer(center);
        col.getColumn(4).setCellRenderer(center);
        col.getColumn(5).setCellRenderer(center);
        col.getColumn(6).setCellRenderer(center);
        col.getColumn(7).setCellRenderer(center);
        col.getColumn(8).setCellRenderer(center);
        col.getColumn(9).setCellRenderer(center);
        col.getColumn(10).setCellRenderer(center);

        btnclose.addActionListener(new ActionListener() {
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
                DefaultTableModel obj=(DefaultTableModel)tblbill.getModel();
                TableRowSorter<DefaultTableModel> obj1=new TableRowSorter<>(obj);
                tblbill.setRowSorter(obj1);
                obj1.setRowFilter(RowFilter.regexFilter(tfsearch.getText()));
            }
        });
    }

    public void showScreen()
    {
        this.setTitle("Bill Payment");
        this.setVisible(true);
        this.setContentPane(this.mainPanel);
        this.setSize(1200,1000);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }
}
