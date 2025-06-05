package Model;

import Controller.NADRA;
import View.FileHandling;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class updateExpiry extends JFrame{
    private JPanel mainPanel;
    private JLabel custid;
    private JLabel cnic;
    private JLabel expiryDate;
    private JButton btnUpdate;
    private JTextField tfid;
    private JTextField tfcnic;
    private JTextField tfexpiry;
    NADRA Nadra = new NADRA();
    public updateExpiry() {
        FileHandling myFile = new FileHandling();
        ArrayList<NADRA> nadras = new ArrayList<>();
        myFile.loadNADRADB(nadras);
        btnUpdate.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String id = tfid.getText();
                String cnic = tfcnic.getText();
                String expiry = tfexpiry.getText();
                if ((id.isEmpty()) || (cnic.isEmpty()) || (expiry.isEmpty())) {
                    JOptionPane.showMessageDialog(null, "Error! Enter all the fields");
                    tfid.setText("");
                    tfcnic.setText("");
                    tfexpiry.setText("");
                    tfid.requestFocus();
                }
                else if(cnic.length()!=13)
                {
                        JOptionPane.showMessageDialog(null,"Error! Enter a valid 13 digit CNIC!");
                        tfcnic.setText("");
                        tfcnic.requestFocus();
                }
                else if((expiry.length()!=10)||(Nadra.isPastDate(expiry)==false))
                {
                      JOptionPane.showMessageDialog(null,"Error! Enter the valid expiry date");
                        tfexpiry.setText("");
                        tfexpiry.requestFocus();
                }
                else
                {
                    Nadra.updateExpiry(nadras, id,cnic,expiry);
                    setVisible(false);
                    CustomerScreen cs = new CustomerScreen();
                    cs.showScreen();
                }
            }
        });
    }
    public void showScreen()
    {
        this.setVisible(true);
        this.setContentPane(this.mainPanel);
        this.setSize(1200,1000);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }
}
