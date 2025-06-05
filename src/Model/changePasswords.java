package Model;

import Controller.Employee;
import View.FileHandling;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class changePasswords extends JFrame{
    private JPanel mainPanel;
    private JLabel lblChange;
    private JLabel lblopass;
    private JLabel lblid;
    private JLabel lblnpass;
    private JButton changeButton;
    private JTextField tfid;
    private JPasswordField pfopass;
    private JPasswordField pfnpass;
    private Employee emp=new Employee();
    public changePasswords() {

        FileHandling fh=new FileHandling();
        ArrayList<Employee>employees=new ArrayList<>();
        fh.loadEmployees(employees);
        changeButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                String id = tfid.getText();
                String passold= String.copyValueOf(pfopass.getPassword());
                String passnew = String.copyValueOf(pfnpass.getPassword());
                if(id.isEmpty()||passold.isEmpty()||passnew.isEmpty()) {
                    JOptionPane.showMessageDialog(null,"Error! Enter all the fields");
                    tfid.setText("");
                    pfopass.setText("");
                    pfnpass.setText("");
                    tfid.requestFocus();
                }
                if(passold.equals(passnew)) {
                    JOptionPane.showMessageDialog(null,"New password cannot be same as old password");
                    pfnpass.setText("");
                    pfnpass.requestFocus();
                }
                else
                {
                    emp.changePassword(employees,id,passold,passnew);
                    EmployeeScreen empScreen = new EmployeeScreen();
                    setVisible(false);
                    empScreen.showScreen();
                }
            }
        });
    }
    public void showScreen()
    {
        this.setTitle("Change Password");
        this.setVisible(true);
        this.setContentPane(this.mainPanel);
        this.setSize(1200,1000);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }
}
