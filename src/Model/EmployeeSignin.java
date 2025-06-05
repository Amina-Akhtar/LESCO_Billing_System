package Model;

import Controller.Employee;
import View.FileHandling;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;

public class EmployeeSignin extends JFrame{
    private JLabel lblemplSign;
    private JTextField tfun;
    private JPasswordField pfpass;
    private JLabel lblun;
    private JLabel lblpass;
    private JButton btnSignin;
    private JPanel mainPanel;
    Employee emp=new Employee();
    public EmployeeSignin()
    {
        FileHandling myFile = new FileHandling();
        ArrayList<Employee> employees = new ArrayList<>();
        myFile.loadEmployees(employees);
         btnSignin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(tfun.getText().equals("")||pfpass.getText().equals(""))
                {
                    JOptionPane.showMessageDialog(null, "Error! Enter all the fields");
                    tfun.setText("");
                    pfpass.setText("");
                    tfun.requestFocus();
                }
                if( emp.signInEmployee(employees,tfun.getText(), String.copyValueOf(pfpass.getPassword())))
                {
                    JOptionPane.showMessageDialog(rootPane,"Employee Sign In Successfully");
                    EmployeeScreen empScreen = new EmployeeScreen();
                    setVisible(false);
                    empScreen.showScreen();
                }
                else {
                    JOptionPane.showMessageDialog(rootPane,"Employee Sign in failed");
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
