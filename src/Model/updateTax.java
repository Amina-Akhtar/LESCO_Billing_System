package Model;

import Controller.NADRA;
import Controller.TariffTax;
import View.FileHandling;

import javax.swing.*;
import javax.swing.table.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class updateTax extends JFrame{
    private JLabel lbltaxupdate;
    private JLabel lblsearch;
    private JTextField tfsearch;
    private JPanel mainPanel;
    private JPanel panel1;
    private JPanel panel2;
    private JPanel panel3;
    private JButton btnclose;
    private JTable tbltax;
    private JScrollPane panelscroll;
    private TariffTax t=new TariffTax();

    public updateTax() {
        ButtonTableCellRenderer renderer = new ButtonTableCellRenderer();
        ButtonTableCellEditor editor = new ButtonTableCellEditor(new JCheckBox());

        FileHandling fh=new FileHandling();
        ArrayList<TariffTax> taxes=new ArrayList<>();
        fh.loadTariff(taxes);
        Object[][] result=t.displayTariff(taxes);
        DefaultTableModel model = new DefaultTableModel(new Object[]{"Meter Type", "Regular Unit Price", "Peak Hour Unit Price", "Tax", "Fixed Charges", "Update"}, 0);
        tbltax.setModel(model);
        tbltax.setRowHeight(50);
        for(int i=0;i<result.length;i++){
            Object[] row = new Object[]{result[i][0],result[i][1],result[i][2],result[i][3],result[i][4],"Update"};
            model.addRow(row);
        }

        TableColumnModel col=tbltax.getColumnModel();
        col.getColumn(5).setCellRenderer(renderer);
        col.getColumn(5).setCellEditor(editor);

        editor.button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int row = tbltax.getSelectedRow();
                if (row == -1) {
                    JOptionPane.showMessageDialog(null, "Error! Select a row to update");
                } else {
                    String meter = tbltax.getValueAt(row, 0).toString();
                    int reg = Integer.parseInt(tbltax.getValueAt(row, 1).toString());
                    int peak = Integer.parseInt(tbltax.getValueAt(row, 2).toString());
                    int tax = Integer.parseInt(tbltax.getValueAt(row, 3).toString());
                    int fixedCharges = Integer.parseInt(tbltax.getValueAt(row, 4).toString());
                    t.updateTax(taxes,row,meter,reg,peak,tax,fixedCharges);
                   setVisible(false);
                   updateTax ut=new updateTax();
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
                DefaultTableModel obj=(DefaultTableModel)tbltax.getModel();
                TableRowSorter<DefaultTableModel> obj1=new TableRowSorter<>(obj);
                tbltax.setRowSorter(obj1);
                obj1.setRowFilter(RowFilter.regexFilter(tfsearch.getText()));
            }
        });
    }

    public void showScreen()
    {
        this.setTitle("Update Tariff Tax");
        this.setVisible(true);
        this.setContentPane(this.mainPanel);
        this.setSize(1200,1000);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }

    private static class ButtonTableCellRenderer extends JButton implements TableCellRenderer{
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
