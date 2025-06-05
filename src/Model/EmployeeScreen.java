package Model;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EmployeeScreen extends JFrame {
    private JPanel mainPanel;
    private JLabel lblDashboard;
    private JButton btnpayBill;
    private JButton btnmonthlyBill;
    private JButton btnaddMEter;
    private JButton btnupdateInfo;
    private JButton btntaxRates;
    private JButton btnindividualBill;
    private JButton btnsummaryRep;
    private JButton btnexpireCNIC;
    private JButton btnchangePass;
    private JButton btnLogout;

    public EmployeeScreen() {
        btnLogout.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null, "Log out successful!");
                HomeScreen hs = new HomeScreen();
                setVisible(false);
                hs.showScreen();
            }
        });
        btnchangePass.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                changePasswords cps=new changePasswords();
                setVisible(false);
                cps.showScreen();
            }
        });
        btnsummaryRep.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                billSummary bs = new billSummary();
                setVisible(false);
                bs.showScreen();
            }
        });
        btnindividualBill.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                displayBill ib=new displayBill("Employee");
                setVisible(false);
                ib.showScreen();
            }
        });
        btnexpireCNIC.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                expireCNIC eCNIC=new expireCNIC();
                setVisible(false);
                eCNIC.showScreen();
            }
        });
        btnaddMEter.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addMeter am=new addMeter();
                setVisible(false);
                am.showScreen();
            }
        });
        btntaxRates.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
              updateTax ut=new updateTax();
              setVisible(false);
              ut.showScreen();
            }
        });
        btnupdateInfo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateMeter um=new updateMeter();
                setVisible(false);
                um.showScreen();
            }
        });
        btnpayBill.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                payBill pb=new payBill();
                setVisible(false);
                pb.showScreen();
            }
        });
        btnmonthlyBill.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateBill ub=new updateBill();
                setVisible(false);
                ub.showScreen();
            }
        });
    }
  public void showScreen()
    {
        this.setTitle("Employee Dashboard");
        this.setVisible(true);
        this.setContentPane(this.mainPanel);
        this.setSize(1200,1000);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }
}
