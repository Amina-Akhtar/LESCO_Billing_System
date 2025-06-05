package Controller;

import View.*;

import javax.swing.*;
import java.io.*;
import java.text.*;
import java.util.*;
import java.time.*;
import java.time.format.*;

public class Employee {
    private String username;
    private String password;
    Scanner input=new Scanner(System.in);
    public Employee()
    {
        username=" ";
        password=" ";
    }
    public Employee(String username, String password,ArrayList<Employee> e)
    {
        if(checkUnique(e,username))
        {
            this.username = username;
            this.password = password;
        }
        else
        {
            System.out.println("Error! Username already exists, Sign up failed!");
        }
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    public boolean checkUnique(ArrayList<Employee> e,String user)
    {
        for(int i=0;i<e.size();i++)
        {
            if(e.get(i).getUsername().equals(user))
            {
                return false;
            }
        }
        return true;
    }

    public void changePassword(ArrayList<Employee>e,String cp_user,String cp_pass,String n_pass)
    {
        if(e.isEmpty())
        {
            System.out.println("Error! Employees not found");
            return;
        }int check=0;
        for(int i=0;i<e.size();i++)
        {
            if((e.get(i).getUsername().equals(cp_user))&&(e.get(i).getPassword().equals(cp_pass)))
            {
                e.get(i).setPassword(n_pass);
                check=1;
                break;
            }
        }
        if(check==0)
        {
            JOptionPane.showMessageDialog(null,"Error: Username not found!");
        }
        else
        {
            FileHandling myFile=new FileHandling();
            myFile.saveEmployees(e);
            e.clear();
            myFile.loadEmployees(e);
            JOptionPane.showMessageDialog(null,"Password changed successfully!");
        }
    }

    public boolean signInEmployee(ArrayList<Employee> e,String un,String pass)
    {
        for(int i=0;i<e.size();i++)
        {
            if((e.get(i).getUsername().equals(un))&&(e.get(i).getPassword().equals(pass)))
            {
                this.setUsername(un);
                this.setPassword(pass);
                return true;
            }
        }
        return false;
    }

    public void displayEmployess(ArrayList<Employee> e)
    {
        if(e.size()<0)
        {
            System.out.println("No employee information found!:");
            return;
        }
        System.out.println("Employee Information:");
        for(int i=0;i<e.size();i++)
        {
            System.out.println("Username:"+e.get(i).getUsername()+" Password:"+e.get(i).getPassword());
        }
        System.out.println(" ");
    }

}
