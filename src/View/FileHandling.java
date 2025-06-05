package View;

import Controller.*;

import java.util.*;
import java.io.*;
import java.text.*;
import java.time.*;
public class FileHandling
{
    public final String employee_file="EmployeesData.txt";
    public final String customer_file="CustomersData.txt";
    public final String billing_file="BillingInfo.txt";
    public final String tariffTax_file="TariffTaxInfo.txt";
    public final String NADRA_file="NADRADB.txt";
    public FileHandling()
    {

    }
    public FileHandling(String firstTime)
    {
        try
        {
            File employee = new File(employee_file);
            if (!employee.createNewFile())
            {
                System.out.println("Employee file already exists.");
            }
            File customer = new File(customer_file);
            if (!customer.createNewFile())
            {
                System.out.println("Customer file already exists.");
            }
            File billing=new File(billing_file);
            if(!billing.createNewFile())
            {
                System.out.println("Billing file already exists.");
            }
            File taxing=new File(tariffTax_file);
            if(!taxing.createNewFile())
            {
                System.out.println("Taxing file already exists.");
            }
            File nadra=new File(NADRA_file);
            if(!nadra.createNewFile())
            {
                System.out.println("NADRA file already exists.");
            }
        }
        catch (IOException ex)
        {
            System.out.println("Error encountered while creating file!");
            ex.getMessage();
        }
    }

    public void loadEmployees(ArrayList<Employee> emp)
    {
        BufferedReader br=null;
        try
        {
            br= new BufferedReader(new FileReader(employee_file));
            String file_line;
            while ((file_line = br.readLine()) != null)
            {
                String[] data = file_line.split(",");
                Employee employee = new Employee(data[0], data[1],emp);
                emp.add(employee);
            }
        }
        catch (IOException ex)
        {
            System.out.println("Error encountered while reading file!");
            ex.getMessage();
        }
        finally
        {
            try {
                if (br != null) {
                    br.close();
                }
            } catch (IOException e)
            {
                System.out.println("Error: File not closed");
                System.out.println(e.getMessage());
            }
        }
    }

    public void saveEmployees(ArrayList<Employee> emp)
    {
        BufferedWriter bw=null;
        try
        {
            bw = new BufferedWriter(new FileWriter(employee_file));
            for (Employee employee : emp)
            {
                bw.write(employee.getUsername() + "," + employee.getPassword());
                bw.newLine();
            }
        }
        catch (IOException ex)
        {
            System.out.println("Error occurred while saving employees to the file.");
            ex.getMessage();
        }
        finally
        {
            try {
                if (bw != null) {
                    bw.close();
                }
            } catch (IOException e)
            {
                System.out.println("Error: File not closed");
                System.out.println(e.getMessage());
            }
        }
    }

    public void loadCustomers(ArrayList<Customer> cust)
    {
        BufferedReader br=null;
        try
        {
            br= new BufferedReader(new FileReader(customer_file));
            String file_line;
            while ((file_line = br.readLine()) != null)
            {
                String[] data = file_line.split(",");
                Customer customer = new Customer(Integer.parseInt(data[0]), data[1], data[2], data[3], data[4], data[5], data[6], data[7], Integer.parseInt(data[8]), Integer.parseInt(data[9]));
                cust.add(customer);
            }
        }
        catch (IOException ex)
        {
            System.out.println("Error encountered while reading file!");
            ex.getMessage();
        }
        finally
        {
            try {
                if (br != null) {
                    br.close();
                }
            } catch (IOException e)
            {
                System.out.println("Error: File not closed");
                System.out.println(e.getMessage());
            }
        }
    }

    public void saveCustomers(ArrayList<Customer> cust)
    {
        BufferedWriter bw=null;
        try
        {
            bw= new BufferedWriter(new FileWriter(customer_file));
            for (Customer customer : cust)
            {
                bw.write(customer.getCust_id() + "," + customer.getCNIC()+","+customer.getName()+","+customer.getAddress()+","+customer.getPhone_num()+","+customer.getCust_type()+","+customer.getMeter_type()+","+customer.getConnectionDate()+","+customer.getRegUnits()+","+customer.getPeakHourUnits());
                bw.newLine();
            }
        }
        catch (IOException ex)
        {
            System.out.println("Error occurred while saving customers to the file.");
            ex.getMessage();
        }
        finally
        {
            try {
                if (bw != null) {
                    bw.close();
                }
            } catch (IOException e)
            {
                System.out.println("Error: File not closed");
                System.out.println(e.getMessage());
            }
        }
    }

    public void loadBilling(ArrayList<Billing> bill)
    {
        BufferedReader br=null;
        try
        {
            br= new BufferedReader(new FileReader(billing_file));
            String file_line;
            while ((file_line = br.readLine()) != null)
            {
                String[] data = file_line.split(",");
                Billing billing = new Billing(Integer.parseInt(data[0]), data[1],Integer.parseInt(data[2]),Integer.parseInt(data[3]),
                        data[4],Integer.parseInt(data[5]),Integer.parseInt(data[6]),Integer.parseInt(data[7]),Integer.parseInt(data[8]),
                        data[9],data[10],data[11]);
                bill.add(billing);
            }
        }
        catch (IOException ex)
        {
            System.out.println("Error encountered while reading file!");
        }
        finally
        {
            try {
                if (br != null) {
                    br.close();
                }
            } catch (IOException e)
            {
                System.out.println("Error: File not closed");
                System.out.println(e.getMessage());
            }
        }
    }

    public void saveBilling(ArrayList<Billing> bill, boolean append)
    {
        BufferedWriter bw = null;
        try {
            bw = new BufferedWriter(new FileWriter(billing_file, append));

            for (Billing billing : bill) {
                bw.write(billing.getCustomerID() + "," + billing.getMonth() + "," + billing.getCurrentMeterReadingReg() + "," +
                        billing.getCurrentMeterReadingPeak() + "," + billing.getReadingEntryDate() + "," + billing.getCostOfElectricity() + "," +
                        billing.getSalesTax() + "," + billing.getFixedCharges() + "," + billing.getTotalBillingAmount() + "," +
                        billing.getDueDate() + "," + billing.getStatus() + "," + billing.getPaymentDate());
                bw.newLine();
            }
        } catch (IOException ex) {
            System.out.println("Error occurred while saving billing to the file.");
            ex.getMessage();
        } finally {
            try {
                if (bw != null) {
                    bw.close();
                }
            } catch (IOException e) {
                System.out.println("Error: File not closed");
                System.out.println(e.getMessage());
            }
        }
    }

    public void loadTariff(ArrayList<TariffTax> tarifftax)
    {
        BufferedReader br=null;
        try
        {
            br= new BufferedReader(new FileReader(tariffTax_file));
            String file_line;
            while ((file_line = br.readLine()) != null)
            {
                String[] data = file_line.split(",");
                TariffTax tax = new TariffTax(data[0], Integer.parseInt(data[1]),Integer.parseInt(data[2]),
                        Integer.parseInt(data[3]),
                        Integer.parseInt(data[4]));
                tarifftax.add(tax);
            }
        }
        catch (IOException ex)
        {
            System.out.println("Error encountered while reading file!");
            ex.getMessage();
        }
        finally
        {
            try {
                if (br != null) {
                    br.close();
                }
            } catch (IOException e)
            {
                System.out.println("Error: File not closed");
                System.out.println(e.getMessage());
            }
        }
    }

    public void saveTariff(ArrayList<TariffTax> tarifftax)
    {
        BufferedWriter bw=null;
        try
        {
            bw=new BufferedWriter(new FileWriter(tariffTax_file));
            for (TariffTax  tax: tarifftax)
            {
                bw.write(tax.getMeterType()+ "," + tax.getRegUnitPrice()+","+tax.getPeakHourUnitPrice()+","+
                        tax.getTax()+","+tax.getFixedCharges());
                bw.newLine();
            }
        }
        catch (IOException ex)
        {
            System.out.println("Error occurred while saving tariff tax to the file.");
            ex.getMessage();
        }
        finally
        {
            try {
                if (bw != null) {
                    bw.close();
                }
            } catch (IOException e)
            {
                System.out.println("Error: File not closed");
                System.out.println(e.getMessage());
            }
        }
    }

    public void loadNADRADB(ArrayList<NADRA> nadra)
    {
        BufferedReader br=null;
        try
        {
            br= new BufferedReader(new FileReader(NADRA_file));
            String file_line;
            while ((file_line = br.readLine()) != null)
            {
                String[] data = file_line.split(",");
                NADRA n = new NADRA(data[0],data[1],data[2]);
                nadra.add(n);
            }
        }
        catch (IOException ex)
        {
            System.out.println("Error encountered while reading file!");
            ex.getMessage();
        }
        finally
        {
            try {
                if (br != null) {
                    br.close();
                }
            } catch (IOException e)
            {
                System.out.println("Error: File not closed");
                System.out.println(e.getMessage());
            }
        }
    }

    public void saveNADRADB(ArrayList<NADRA> nadra)
    {
        BufferedWriter bw=null;
        try
        {
            bw=new BufferedWriter(new FileWriter(NADRA_file));
            for (NADRA  NDB: nadra)
            {
                bw.write(NDB.getCNIC()+","+NDB.getIssuanceDate()+","+NDB.getExpiryDate());
                bw.newLine();
            }
        }
        catch (IOException ex)
        {
            System.out.println("Error occurred while saving NADRA record to the file.");
            ex.getMessage();
        }
        finally
        {
            try {
                if (bw != null) {
                    bw.close();
                }
            } catch (IOException e)
            {
                System.out.println("Error: File not closed");
                System.out.println(e.getMessage());
            }
        }

    }
}