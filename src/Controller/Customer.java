package Controller;

import View.*;

import javax.swing.*;
import java.util.*;
import java.text.*;
import java.time.*;
import java.io.*;
import java.time.format.*;

public class Customer {

    Scanner input=new Scanner(System.in);
    private int cust_id;
    private String CNIC;
    private String name;
    private String address;
    private String phone_num;
    private String cust_type;
    private String meter_type;
    private String connectionDate;
    private int regUnits;
    private int peakHourUnits;
    public Customer()
    {
    }
    public Customer(int cust_id, String CNIC, String name, String address, String phone_num, String cust_type, String meter_type, String connectionDate, int regUnits, int peakHourUnits)
    {
        this.cust_id = cust_id;
        this.CNIC = CNIC;
        this.name = name;
        this.address = address;
        this.phone_num = phone_num;
        this.cust_type = cust_type;
        this.meter_type = meter_type;
        this.connectionDate = connectionDate;
        this.regUnits = regUnits;
        this.peakHourUnits = peakHourUnits;
    }
    public int getCust_id() {
        return cust_id;
    }

    public void setCust_id(int cust_id) {
        this.cust_id = cust_id;
    }

    public String getCNIC() {
        return CNIC;
    }

    public void setCNIC(String CNIC) {
        this.CNIC = CNIC;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone_num() {
        return phone_num;
    }

    public void setPhone_num(String phone_num) {
        this.phone_num = phone_num;
    }

    public String getCust_type() {
        return cust_type;
    }

    public void setCust_type(String cust_type) {
        this.cust_type = cust_type;
    }

    public String getMeter_type() {
        return meter_type;
    }

    public void setMeter_type(String meter_type) {
        this.meter_type = meter_type;
    }

    public String getConnectionDate() {
        return connectionDate;
    }

    public void setConnectionDate(String connectionDate) {
        this.connectionDate = connectionDate;
    }

    public int getRegUnits() {
        return regUnits;
    }

    public void setRegUnits(int regUnits) {
        this.regUnits = regUnits;
    }

    public int getPeakHourUnits() {
        return peakHourUnits;
    }

    public void setPeakHourUnits(int peakHourUnits) {
        this.peakHourUnits = peakHourUnits;
    }

    public boolean signInCustomer(ArrayList<Customer> c)
    {
        System.out.print("Enter your ID:");
        int id = input.nextInt();
        input.nextLine();
        System.out.print("Enter your CNIC:");
        String cnic = input.nextLine();
        if(c.isEmpty())
        {
            System.out.println("Error! No customers found.Ask employe to add customer.");
        }
        for (Customer customer : c)
        {
            if (customer.getCust_id() == id && customer.getCNIC().equals(cnic))
            {
                this.setCust_id(id);
                this.setCNIC(cnic);
                this.setName(customer.getName());
                this.setAddress(customer.getAddress());
                this.setPhone_num(customer.getPhone_num());
                this.setCust_type(customer.getCust_type());
                this.setMeter_type(customer.getMeter_type());
                this.setConnectionDate(customer.getConnectionDate());
                this.setRegUnits(customer.getRegUnits());
                this.setPeakHourUnits(customer.getPeakHourUnits());
                return true;
            }
        }
        return false;
    }

    public String todaysDate()
    {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate today = LocalDate.now();
        return today.format(formatter);
    }

    public boolean isPastDate(String date)
    {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        try {
            LocalDate inputDate = LocalDate.parse(date, formatter);
            LocalDate today = LocalDate.now();

            return !(inputDate.isBefore(today));
        } catch (DateTimeParseException e) {
            System.out.println("Error! Invalid date format: " + e.getMessage());
            return false;
        }
    }

    public boolean addRecord(ArrayList<NADRA> NDB,ArrayList<Customer> cust,String CNIC,String issue,String expiry)
    {
        int count=0;
        for(int i=0;i<cust.size();i++)
        {
            if(cust.get(i).getCNIC().equals(CNIC))
            {
                count++;
            }
        }
        if(count>2)
        {
            return false;
        }
        else
        {
            int hint=0;
            for(int i=0;i<NDB.size();i++)
            {
                if(NDB.get(i).getCNIC().equals(CNIC))
                {
                    hint++;
                    break;
                }

            }
            if(hint==0)
            {
                NADRA nadra=new NADRA(CNIC,issue,expiry);
                NDB.add(nadra);
                FileHandling f=new FileHandling();
                f.saveNADRADB(NDB);
                NDB.clear();
                f.loadNADRADB(NDB);
            }
            return true;
        }
    }

    public boolean addCustomer(ArrayList<Customer> cust,ArrayList<NADRA>nadra,String c_cnic,String i_date,String e_date,String name,String address,String phone,String meter,String custt)
    {
        if((addRecord(nadra,cust, c_cnic, i_date, e_date))==false)
        {
            JOptionPane.showMessageDialog(null,"Error! Maximum 3 meters allowed per CNIC!");
            return false;
        }
        String s_conn_Date = todaysDate();
        Random random = new Random();
        int cust_id = 1000 + random.nextInt(9000);
        for (Customer customer : cust) {
            if (customer.getCust_id() == cust_id)
            {
                cust_id = 1000 + random.nextInt(9000);
            }
        }
        int peakHourUnit = (Objects.equals(meter, "1 Phase")) ? -1 : 0;
        Customer newCustomer = new Customer(cust_id, c_cnic, name, address, phone, custt, meter, s_conn_Date, 0, peakHourUnit);
        cust.add(newCustomer);
        FileHandling f=new FileHandling();
        f.saveCustomers(cust);
        cust.clear();
        f.loadCustomers(cust);
        JOptionPane.showMessageDialog(null,"Customer ID :"+cust.get(cust.size()-1).getCust_id());
        JOptionPane.showMessageDialog(null,"Meter added successfully!");
        return true;
    }

    public void updateCustomer(ArrayList<Customer> cust,int choice,int id,String cnic,String name,String address,String phone,String custt,String metert,String connD,int reg,int peak)
    {
        if (cust.isEmpty())
        {
            JOptionPane.showMessageDialog(null,"Error! No data available for update!");
        }
        else
        {
            cust.get(choice).setCust_id(id);
            cust.get(choice).setName(name);
            cust.get(choice).setAddress(address);
            cust.get(choice).setPhone_num(phone);
            cust.get(choice).setCust_type(custt);
            cust.get(choice).setMeter_type(metert);
            FileHandling f=new FileHandling();
            f.saveCustomers(cust);
            cust.clear();
            f.loadCustomers(cust);
            JOptionPane.showMessageDialog(null,"Meter information updated successfully!");
        }
    }

    public Object[][] displayCustomers(ArrayList<Customer> cust)
    {
        if(cust.size()<1)
        {
            JOptionPane.showMessageDialog(null,"No customer information found!:");
            return null;
        }
        Object[][] data=new Object[cust.size()][10];
        for(int i=0;i<cust.size();i++)
        {
            data[i][0]=cust.get(i).getCust_id();
            data[i][1]=cust.get(i).getCNIC();
            data[i][2]=cust.get(i).getName();
            data[i][3]=cust.get(i).getAddress();
            data[i][4]=cust.get(i).getPhone_num();
            data[i][5]=cust.get(i).getCust_type();
            data[i][6]=cust.get(i).getMeter_type();
            data[i][7]=cust.get(i).getConnectionDate();
            data[i][8]=cust.get(i).getRegUnits();
            data[i][9]=cust.get(i).getPeakHourUnits();
        }
        return data;
    }
}
