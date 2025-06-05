package Controller;

import View.*;

import javax.swing.*;
import java.io.*;
import java.text.*;
import java.util.*;
import java.time.*;
import java.time.format.*;

public class TariffTax
{
    Scanner input=new Scanner(System.in);
    String meterType;
    int regUnitPrice;
    int peakHourUnitPrice;
    int tax;
    int fixedCharges;

    public TariffTax() {
    }

    public TariffTax(String meterType, int regUnitPrice, int peakHourUnitPrice, int tax, int fixedCharges) {
        this.meterType = meterType;
        this.regUnitPrice = regUnitPrice;
        this.peakHourUnitPrice = peakHourUnitPrice;
        this.tax = tax;
        this.fixedCharges = fixedCharges;
    }

    public String getMeterType() {
        return meterType;
    }

    public void setMeterType(String meterType) {
        this.meterType = meterType;
    }

    public int getRegUnitPrice() {
        return regUnitPrice;
    }

    public void setRegUnitPrice(int regUnitPrice) {
        this.regUnitPrice = regUnitPrice;
    }

    public int getPeakHourUnitPrice() {
        return peakHourUnitPrice;
    }

    public void setPeakHourUnitPrice(int peakHourUnitPrice) {
        this.peakHourUnitPrice = peakHourUnitPrice;
    }

    public int getTax() {
        return tax;
    }

    public void setTax(int tax) {
        this.tax = tax;
    }

    public int getFixedCharges() {
        return fixedCharges;
    }

    public void setFixedCharges(int fixedCharges) {
        this.fixedCharges = fixedCharges;
    }
    public void addTariffTax(ArrayList<TariffTax> tax)
    {
        if(tax.isEmpty())
        {tax.add(new TariffTax("1 Phase",5,-1,17,150));
            tax.add(new TariffTax("1 Phase",15,-1,20,250));
            tax.add(new TariffTax("3 Phase",8,12,17,150));
            tax.add(new TariffTax("3 Phase",18,25,20,250));
            FileHandling f=new FileHandling();
            f.saveTariff(tax);
            tax.clear();
            f.loadTariff(tax);
        }
    }

    public Object[][] displayTariff(ArrayList <TariffTax> tax)
    {
        if(tax.size()<1)
        {
            JOptionPane.showMessageDialog(null,"Error! No tariff tax information found!:");
            return null;
        }
        Object[][] taxdata=new Object[tax.size()][6];
        int ind=0;
        for(int i=0;i<tax.size();i++)
        {
            taxdata[ind][0] = tax.get(i).getMeterType();
            taxdata[ind][1] = tax.get(i).getRegUnitPrice();
            taxdata[ind][2] = tax.get(i).peakHourUnitPrice;
            taxdata[ind][3] = tax.get(i).getTax();
            taxdata[ind][4] = tax.get(i).getFixedCharges();
            taxdata[ind][5] = null;
            ind++;
        }
        return taxdata;
    }

    public void updateTax(ArrayList<TariffTax> tax,int choice,String meter,int reg,int peak,int percent,int fc)
    {
        tax.get(choice).setMeterType(meter);
        tax.get(choice).setRegUnitPrice(reg);
        tax.get(choice).setPeakHourUnitPrice(peak);
        tax.get(choice).setTax(percent);
        tax.get(choice).setFixedCharges(fc);
        FileHandling f=new FileHandling();
        f.saveTariff(tax);
        JOptionPane.showMessageDialog(null,"Tariff tax updated successfully");
    }
}
