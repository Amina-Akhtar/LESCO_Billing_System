package Controller;

import View.*;

import javax.swing.*;
import java.io.*;
import java.text.*;
import java.util.*;
import java.time.*;
import java.time.format.*;

public class Billing {
    Scanner input=new Scanner(System.in);
    private int customerID;
    private String month;
    private int currentMeterReadingReg;
    private int currentMeterReadingPeak;
    private String readingEntryDate;
    private int costOfElectricity;
    private int salesTax;
    private int fixedCharges;
    private int totalBillingAmount;
    private String dueDate;
    private String status;
    private String paymentDate;

    public Billing()
    {

    }

    public Billing(int customerID, String month, int currentMeterReadingReg, int currentMeterReadingPeak, String readingEntryDate, int costOfElectricity, int salesTax, int fixedCharges, int totalBillingAmount, String dueDate, String status, String paymentDate) {
        this.customerID = customerID;
        this.month = month;
        this.currentMeterReadingReg = currentMeterReadingReg;
        this.currentMeterReadingPeak = currentMeterReadingPeak;
        this.readingEntryDate = readingEntryDate;
        this.costOfElectricity = costOfElectricity;
        this.salesTax = salesTax;
        this.fixedCharges = fixedCharges;
        this.totalBillingAmount = totalBillingAmount;
        this.dueDate = dueDate;
        this.status = status;
        this.paymentDate = paymentDate;
    }

    public int getCustomerID() {
        return customerID;
    }

    public void setCustomerID(int customerID) {
        this.customerID = customerID;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public int getCurrentMeterReadingReg() {
        return currentMeterReadingReg;
    }

    public void setCurrentMeterReadingReg(int currentMeterReadingReg) {
        this.currentMeterReadingReg = currentMeterReadingReg;
    }

    public int getCurrentMeterReadingPeak() {
        return currentMeterReadingPeak;
    }

    public void setCurrentMeterReadingPeak(int currentMeterReadingPeak) {
        this.currentMeterReadingPeak = currentMeterReadingPeak;
    }

    public String getReadingEntryDate() {
        return readingEntryDate;
    }

    public void setReadingEntryDate(String readingEntryDate) {
        this.readingEntryDate = readingEntryDate;
    }

    public int getCostOfElectricity() {
        return costOfElectricity;
    }

    public void setCostOfElectricity(int costOfElectricity) {
        this.costOfElectricity = costOfElectricity;
    }

    public int getSalesTax() {
        return salesTax;
    }

    public void setSalesTax(int salesTax) {
        this.salesTax = salesTax;
    }

    public int getFixedCharges() {
        return fixedCharges;
    }

    public void setFixedCharges(int fixedCharges) {
        this.fixedCharges = fixedCharges;
    }

    public int getTotalBillingAmount() {
        return totalBillingAmount;
    }

    public void setTotalBillingAmount(int totalBillingAmount) {
        this.totalBillingAmount = totalBillingAmount;
    }

    public String getDueDate() {
        return dueDate;
    }

    public void setDueDate(String dueDate) {
        this.dueDate = dueDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(String paymentDate) {
        this.paymentDate = paymentDate;
    }

    public String todaysDate()
    {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate today = LocalDate.now();
        return today.format(formatter);
    }
    public String dateAfterWeek() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate today = LocalDate.now();
        LocalDate futureDate = today.plusDays(7);
        return futureDate.format(formatter);
    }

    public void addBilling(ArrayList<Billing> bill,ArrayList<Customer> cust)
    {
        int reg = 0;
        int peak=-1;
        int index=cust.size()-1;
        if(cust.get(index).getMeter_type().equals("3 Phase"))
        {
            peak = 0;
        }
        Billing b=new Billing(cust.get(index).getCust_id()," ",reg,peak,
                " ",0,0,0,0," "," "," ");
        bill.add(b);
        FileHandling f=new FileHandling();
        f.saveBilling(bill,false);
        bill.clear();
        f.loadBilling(bill);
        //System.out.println("Bills added successfully!");
    }

    public void calculateBilling(ArrayList<Billing> bill,ArrayList<TariffTax> tax,ArrayList<Customer> cust,int ID,String mon,int reg,int peak,int row)
    {
            TariffTax meterTax=null;
            int consumed_reg=0;
            int consumed_peak=0;
            for(Customer customer:cust)
            {
                if(customer.getCust_id()==ID)
                {
                    if (customer.getMeter_type().equals("1 Phase"))
                    {
                        if (customer.getCust_type().equals("Regular"))
                        {
                            meterTax = new TariffTax(tax.get(0).getMeterType(), tax.get(0).getRegUnitPrice(), tax.get(0).getPeakHourUnitPrice()
                                    , tax.get(0).getTax(), tax.get(0).getFixedCharges());
                        }
                        else if (customer.getCust_type().equals("Commercial"))
                        {
                            System.out.println("C");
                            meterTax = new TariffTax(tax.get(1).getMeterType(), tax.get(1).getRegUnitPrice(), tax.get(1).getPeakHourUnitPrice()
                                    , tax.get(1).getTax(), tax.get(1).getFixedCharges());
                        }
                        consumed_reg = reg - cust.get(row).getRegUnits();
                    }
                    else if (customer.getMeter_type().equals("3 Phase"))
                    {
                        if (customer.getCust_type().equals("Regular"))
                        {
                            meterTax = new TariffTax(tax.get(2).getMeterType(), tax.get(2).getRegUnitPrice(), tax.get(2).getPeakHourUnitPrice()
                                    , tax.get(2).getTax(), tax.get(2).getFixedCharges());
                        }
                        else if (customer.getCust_type().equals("Commercial"))
                        {
                            meterTax = new TariffTax(tax.get(3).getMeterType(), tax.get(3).getRegUnitPrice(), tax.get(3).getPeakHourUnitPrice()
                                    , tax.get(3).getTax(), tax.get(3).getFixedCharges());
                        }
                        consumed_peak = peak - cust.get(row).getPeakHourUnits();
                    }
                    break;
                }
            }
            if(bill.get(row).getStatus().equals(" ")) {
                bill.get(row).setCurrentMeterReadingReg(reg);
                bill.get(row).setCurrentMeterReadingPeak(peak);
                int cost = (meterTax.getRegUnitPrice() * consumed_reg) + (meterTax.getPeakHourUnitPrice() * consumed_peak);
                bill.get(row).setCostOfElectricity(cost);
                bill.get(row).setSalesTax(meterTax.getFixedCharges());
                bill.get(row).setFixedCharges(meterTax.getFixedCharges());
                bill.get(row).setMonth(mon);
                bill.get(row).setTotalBillingAmount(cost + meterTax.getFixedCharges() + (cost * meterTax.getFixedCharges() / 100));
                bill.get(row).setReadingEntryDate(todaysDate());
                bill.get(row).setPaymentDate("0");
                bill.get(row).setStatus("Unpaid");
                bill.get(row).setDueDate(dateAfterWeek());
                JOptionPane.showMessageDialog(null,"Bill generated successfully");
            }
            else {
                JOptionPane.showMessageDialog(null,"Bill already generated");
            }
        FileHandling f=new FileHandling();
        if (bill.get(0).getMonth().equals(" "))
        {
            f.saveBilling(bill,true);
        }
        else
        {
            f.saveBilling(bill, false);
        }
    }

    public void payBilling(ArrayList<Billing>bill,ArrayList<Customer>cust,int choice,String status,int id)
    {
        if(bill.isEmpty())
        {
           JOptionPane.showMessageDialog(null,"Error: No bills found!");
        }
        else if(bill.get(choice).getPaymentDate().equals("0"))
        {
           JOptionPane.showMessageDialog(null,"Error! Bill not generated yet");
        }
        else if(bill.get(choice).status.equals("Unpaid"))
        {
            bill.get(choice).status="Paid";
            bill.get(choice).paymentDate  =todaysDate();
            for(Customer customer:cust)
            {
                if (bill.get(choice).customerID==customer.getCust_id())
                {
                    customer.setRegUnits(bill.get(choice).currentMeterReadingReg);
                    if (customer.getPeakHourUnits()!=-1)
                    {
                        customer.setPeakHourUnits(bill.get(choice).getCurrentMeterReadingPeak());
                    }
                }
            }
            FileHandling f=new FileHandling();
            f.saveBilling(bill,false);
            f.saveCustomers(cust);
            JOptionPane.showMessageDialog(null,"Bill paid successfully!");
        }
        else {
            JOptionPane.showMessageDialog(null,"Bill is already paid!");
        }

    }

    public Object[][] displayBilling(ArrayList<Billing> bill,ArrayList<Customer> cust)
    {
        if(bill.isEmpty())
        {
            JOptionPane.showMessageDialog(null,"Error! No billing information found!:");
            return null;
        }
        Object[][] data=new Object[bill.size()][13];
        for(int i=0;i<bill.size();i++) {
            data[i][0] = bill.get(i).getCustomerID();
            data[i][1] = bill.get(i).getMonth();
            data[i][2] = bill.get(i).getCurrentMeterReadingReg();
            data[i][3] = bill.get(i).getCurrentMeterReadingPeak();
            data[i][4] = bill.get(i).getReadingEntryDate();
            data[i][5] = bill.get(i).getCostOfElectricity();
            data[i][6] = bill.get(i).getSalesTax();
            data[i][7] = bill.get(i).getFixedCharges();
            data[i][8] = bill.get(i).getTotalBillingAmount();
            data[i][9] = bill.get(i).getStatus();
            data[i][10] = bill.get(i).getPaymentDate();
        }
        return data;
    }

    public String displayBillingSummary(ArrayList<Billing> bill)
    {
        String data="\n\n\n";
        if(bill.isEmpty())
        {
            data+="Error! No billing information found!";
            return data;
        }
        int paid=0;
        int unpaid=0;
        for(int i=0;i<bill.size();i++)
        {
            if(bill.get(i).getStatus().equals("Paid"))
            {
                paid++;
            }
            else if(bill.get(i).getStatus().equals("Unpaid"))
            {
                unpaid++;
            }
        }
        data+=("Total paid bills: "+paid);
        data+=("\nTotal unpaid bills: "+unpaid);
        return data;
    }

    public void displayBillingIndividual(ArrayList<Billing> bill,ArrayList<Customer> customer,String C_id)
    {
        if (bill.isEmpty() || customer.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Error! No billing information found");
            return;
        }
        String data = null;
        int count = -1;
        for (int i = 0; i < bill.size(); i++) {
            if ((bill.get(i).getCustomerID()) == Integer.parseInt(C_id)) {
                count = i;
            }
        }
        if (count == -1) {
            JOptionPane.showMessageDialog(null, "Error: Bill for this customer not found");
            return ;
        }
        else if (bill.get(count).getTotalBillingAmount() == 0) {
            JOptionPane.showMessageDialog(null, "Error: Bill not generated yet");
            return ;
        }
         else {
            data = "Customer Bill\n----------------------------------------------------------------------\n";
            for (Customer cust : customer) {
                if (cust.getCust_id() == bill.get(count).getCustomerID()) {
                    data += ("CNIC: " + cust.getCNIC() + "Name: " + cust.getName() + " Address: " + cust.getAddress()
                            + " Phone number: " + cust.getPhone_num() + " \nCustomer Type: " + cust.getCust_type() + " Meter Type: " + cust.getMeter_type()
                            + " Connection Date: " + cust.getConnectionDate() + " \nRegular Units: " + cust.getRegUnits() + " Peak Hour Units: " + cust.getPeakHourUnits());
                }
                break;
            }
            data += ("\nCustomer ID: " + bill.get(count).getCustomerID() + " Month: " + bill.get(count).getMonth() + " Current Regular Units: " + bill.get(count).getCurrentMeterReadingReg() + " Current Peak Units: " + bill.get(count).getCurrentMeterReadingPeak()
                    + " \nReading Entry Date: " + bill.get(count).getReadingEntryDate() + " Cost of electricity: " + bill.get(count).getCostOfElectricity() + " Sales Tax: " + bill.get(count).getSalesTax() + " Fixed Charges: " + bill.get(count).getFixedCharges()
                    + " \nTotal Amount: " + bill.get(count).getTotalBillingAmount() + " Due Date:" + bill.get(count).getDueDate() + " Status: " + bill.get(count).getStatus());
            if (!bill.get(count).getPaymentDate().equals(" ")) {
                data += (" Payment Date: " + bill.get(count).getPaymentDate()+"\n");
            }
        }
        JOptionPane.showMessageDialog(null, data);
    }

    public void updateBilling(ArrayList<Billing> bills, int row, int ID, String month, int reg, int peak, String entryDate, int cost, int tax, int fc, int amount, String status, String payDate) {
         if (!bills.get(row).getStatus().equals(" "))
         {
            bills.get(row).setMonth(month);
            bills.get(row).setCurrentMeterReadingReg(reg);
            bills.get(row).setCurrentMeterReadingPeak(peak);
            bills.get(row).setReadingEntryDate(entryDate);
            bills.get(row).setCostOfElectricity(cost);
            bills.get(row).setSalesTax(tax);
            bills.get(row).setFixedCharges(fc);
            bills.get(row).setTotalBillingAmount(amount);
            bills.get(row).setStatus(status);
            bills.get(row).setPaymentDate(payDate);
            JOptionPane.showMessageDialog(null, "Bill updated successfully!");
            FileHandling f = new FileHandling();
            f.saveBilling(bills, false);
        }
         else
         {
            JOptionPane.showMessageDialog(null, "Error! Bill not generated yet");
        }
    }

}