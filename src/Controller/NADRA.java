package Controller;

import View.*;

import javax.swing.*;
import java.io.*;
import java.text.*;
import java.util.*;
import java.time.*;
import java.time.format.*;
import java.time.temporal.ChronoUnit;

public class NADRA{
    Scanner input=new Scanner(System.in);
    private String CNIC;
    private String IssuanceDate;
    private String expiryDate;
    public NADRA()
    {

    }

    public NADRA(String CNIC, String IssuanceDate, String expiryDate) {
        this.CNIC = CNIC;
        this.IssuanceDate = IssuanceDate;
        this.expiryDate = expiryDate;
    }

    public String getCNIC() {
        return CNIC;
    }

    public void setCNIC(String CNIC) {
        this.CNIC = CNIC;
    }

    public String getIssuanceDate() {
        return IssuanceDate;
    }

    public void setIssuanceDate(String IssuanceDate) {
        this.IssuanceDate = IssuanceDate;
    }

    public String getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(String expiryDate) {
        this.expiryDate = expiryDate;
    }

    public boolean isValidDate(String date) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        dateFormat.setLenient(false);

        try {
            dateFormat.parse(date);
            return true;
        } catch (ParseException e) {
            return false;
        }
    }

    public boolean isPastDate(String date)
    {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        try {
            LocalDate inputDate = LocalDate.parse(date, formatter);
            LocalDate today = LocalDate.now();
            return !inputDate.isBefore(today);
        } catch (DateTimeParseException e) {
            System.out.println("Invalid date format: " + e.getMessage());
            return false;
        }
    }

    public boolean addRecord(ArrayList<NADRA> NDB,String CNIC,String issue,String expiry)
    {
        int count=0;
        for(int i=0;i<NDB.size();i++)
        {
            if(NDB.get(i).getCNIC().equals(CNIC))
            {
                count++;
            }
        }
        if(count>3)
        {
            return false;
        }
        else
        {
            NADRA nadra=new NADRA(CNIC,issue,expiry);
            NDB.add(nadra);
            FileHandling f=new FileHandling();
            f.saveNADRADB(NDB);
            NDB.clear();
            f.loadNADRADB(NDB);
            return true;
        }
    }

    public void display(ArrayList<NADRA> nadra)
    {
        if(nadra.size()<1)
        {
            System.out.println("Error! No NADRA information found!:");
            return;
        }
        System.out.println("NADRA Information:");
        for(int i=0;i<nadra.size();i++)
        {
            System.out.println("CNIC: "+nadra.get(i).getCNIC()+" Issuance Date:"+nadra.get(i).getIssuanceDate()+" Expiry Date: "+nadra.get(i).getExpiryDate());
        }
        System.out.println(" ");
    }

    public static int checkCNICExpiry(String expiryDate)
    {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate expirationDate = LocalDate.parse(expiryDate, formatter);
        LocalDate currentDate = LocalDate.now();
        int daysUntilExpiry = (int) ChronoUnit.DAYS.between(currentDate, expirationDate);
        if (daysUntilExpiry <= 30) {
            return 1;
        }
        return 0;
    }

    public Object[][] viewExpiringCNICs(ArrayList<NADRA> nadra,ArrayList<Customer> customer)
    {
        if(nadra.isEmpty())
        {
            JOptionPane.showMessageDialog(null,"Error! No NADRA record found!");
            return null;
        }
        else
        {
            Object[][] dataExpire=new Object[nadra.size()][5];
            int ind=0;
            for (NADRA data : nadra)
            {
                int res = checkCNICExpiry(data.getExpiryDate());
                String status = " ";
                if (res == 1)
                {
                    status = "Expired";
                }
                else if(res==0)
                {
                    status= "Not close to expiry";
                }
                for(Customer cust:customer)
                {
                    if(cust.getCNIC().equals(data.getCNIC()))
                    {
                        dataExpire[ind][0] = cust.getName();
                        dataExpire[ind][1] = cust.getCust_id();
                        dataExpire[ind][2] = cust.getCNIC();
                        dataExpire[ind][3] = data.getExpiryDate();
                        dataExpire[ind][4] = status;
                        ind++;
                        break;
                    }
                }
            }
            return dataExpire;
        }
    }

    public void updateExpiry(ArrayList <NADRA> nadra,String id,String cnic,String expiry)
    {
        if(nadra.size()<1)
        {
            JOptionPane.showMessageDialog(null,"Error! No NADRA record found!");
            return;
        }
        int count=-1;

        for(int i=0;i<nadra.size();i++)
        {
            if(nadra.get(i).getCNIC().equals(cnic))
            {
                count=i;
                break;
            }
        }
        if(count==-1)
        {
            JOptionPane.showMessageDialog(null,"Error! CNIC not found");
        }
        else
        {
            nadra.get(count).setExpiryDate(expiry);
            JOptionPane.showMessageDialog(null,"Expiry date updated successfully!");
            FileHandling f=new FileHandling();
            f.saveNADRADB(nadra);
        }
    }
}
