package Model;

import Controller.Customer;
import View.FileHandling;

import javax.swing.*;
import javax.swing.table.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class updateMeter extends JFrame {
    private JPanel mainPanel;
    private JPanel panel1;
    private JPanel panel2;
    private JPanel panel3;
    private JLabel lblupdate;
    private JTextField tfsearch;
    private JLabel lblsearch;
    private JButton btnclose;
    private JScrollPane panelscroll;
    private JTable tblcust;

    private Customer c=new Customer();

    public updateMeter() {
        ButtonTableCellRenderer renderer = new ButtonTableCellRenderer();
        ButtonTableCellEditor editor = new ButtonTableCellEditor(new JCheckBox());

        FileHandling fh=new FileHandling();
        ArrayList<Customer> customers=new ArrayList<>();
        fh.loadCustomers(customers);
        Object[][] result=c.displayCustomers(customers);
        DefaultTableModel model = new DefaultTableModel(new Object[]{"Customer ID", "CNIC", "Name", "Address", "Phone Number", "Customer Type","Meter Type","Connection Date","Regular Units","Peak Hour Units","Update"}, 0);
        tblcust.setModel(model);
        tblcust.setRowHeight(30);
        for(int i=0;i<result.length;i++){
            Object[] row = new Object[]{result[i][0],result[i][1],result[i][2],result[i][3],result[i][4],result[i][5],result[i][6],result[i][7],result[i][8],result[i][9],"Update"};
            model.addRow(row);
        }

        TableColumnModel col=tblcust.getColumnModel();
        col.getColumn(10).setCellRenderer(renderer);
        col.getColumn(10).setCellEditor(editor);

        editor.button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int row = tblcust.getSelectedRow();
                if (row == -1) {
                    JOptionPane.showMessageDialog(null, "Error! Select a row to update");
                }
                else
                {
                    int id=Integer.parseInt(tblcust.getValueAt(row,0).toString());
                    String cnic = tblcust.getValueAt(row, 1).toString();
                    String name=tblcust.getValueAt(row, 2).toString();
                    String address=tblcust.getValueAt(row, 3).toString();
                    String phone=tblcust.getValueAt(row, 4).toString();
                    String custt=tblcust.getValueAt(row, 5).toString();
                    String metert=tblcust.getValueAt(row, 6).toString();
                    String connD=tblcust.getValueAt(row, 7).toString();
                    int reg=Integer.parseInt(tblcust.getValueAt(row, 8).toString());
                    int peak=Integer.parseInt(tblcust.getValueAt(row, 9).toString());
                    c.updateCustomer(customers,row,id,cnic,name,address,phone,custt,metert,connD,reg,peak);
                    setVisible(false);
                    updateMeter ut=new updateMeter();
                    ut.showScreen();
                }
            }
        });

        DefaultTableCellRenderer center=new DefaultTableCellRenderer();
        center.setHorizontalAlignment(JLabel.CENTER);
        col.getColumn(1).setCellRenderer(center);
        col.getColumn(2).setCellRenderer(center);
        col.getColumn(3).setCellRenderer(center);
        col.getColumn(4).setCellRenderer(center);
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
                DefaultTableModel obj=(DefaultTableModel)tblcust.getModel();
                TableRowSorter<DefaultTableModel> obj1=new TableRowSorter<>(obj);
                tblcust.setRowSorter(obj1);
                obj1.setRowFilter(RowFilter.regexFilter(tfsearch.getText()));
            }
        });
    }

    public void showScreen()
    {
        this.setTitle("Update meter information");
        this.setVisible(true);
        this.setContentPane(this.mainPanel);
        this.setSize(1200,1000);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }
    private static class ButtonTableCellRenderer extends JButton implements TableCellRenderer {
        public ButtonTableCellRenderer() {
            setText("Update");
            setFocusable(false);
        }
        @Override
        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
            return this;
        }
    }
    private static class ButtonTableCellEditor extends DefaultCellEditor {
        private JButton button;
        private String label;
        private boolean clicked;
        private int row;

        public ButtonTableCellEditor(JCheckBox checkBox) {
            super(checkBox);
            button = new JButton("Update");
            button.setOpaque(true);
            button.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    fireEditingStopped();
                }
            });
        }

        @Override
        public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
            label = (value == null) ? "Update" : value.toString();
            button.setText(label);
            this.row = row;
            clicked = true;
            return button;
        }

        @Override
        public Object getCellEditorValue() {
            clicked = false;
            return label;
        }

        @Override
        public boolean stopCellEditing() {
            clicked = false;
            return super.stopCellEditing();
        }

        @Override
        protected void fireEditingStopped() {
            super.fireEditingStopped();
        }
    }

}
