package Model;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class HomeScreen extends JFrame{
    public JPanel mainPanel;
    private JLabel lblWelcome;
    private JButton btnCustomer;
    private JButton btnExit;
    private JButton btnEmployee;

    public HomeScreen() {
        btnCustomer.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                   CustomerScreen cs = new CustomerScreen();
                   setVisible(false);
                   cs.setVisible(true);
                   cs.showScreen();
            }
        });
        btnEmployee.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                    EmployeeSignin es = new EmployeeSignin();
                    setVisible(false);
                    es.showScreen();
            }
        });
        btnExit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(rootPane,"Application has been terminated");
                setVisible(false);
            }
        });
    }
    public void showScreen()
    {
        this.setVisible(true);
        this.setContentPane(this.mainPanel);
        this.setSize(500,500);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }
}
