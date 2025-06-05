package Model;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CustomerScreen extends JFrame {
    private JPanel mainPanel;
    private JButton btnupdateExpiry;
    private JButton btnviewbill;
    private JButton btnlogout;
    private JLabel lblDashboard;

    public CustomerScreen() {
        btnupdateExpiry.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
               updateExpiry ue=new updateExpiry();
               setVisible(false);
               ue.showScreen();
            }
        });
        btnviewbill.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
              displayBill db=new displayBill("Customer");
              setVisible(false);
              db.showScreen();
            }
        });
        btnlogout.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null, "Log out successful!");
                HomeScreen hs = new HomeScreen();
                setVisible(false);
                hs.showScreen();
            }
        });
    }
    public void showScreen()
    {
        this.setTitle("Customer Dashboard");
        this.setVisible(true);
        this.setContentPane(this.mainPanel);
        this.setSize(500,500);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }
}
