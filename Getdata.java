package ifsc.csv;

import com.sun.org.apache.xpath.internal.SourceTree;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class Getdata {
    public static void main(String args[]) throws IOException {
        //open file input stream
        BufferedReader reader = new BufferedReader(new FileReader("IFSCdata.csv"));
        Scanner scan = new Scanner(System.in);
        Scanner scanIfsc = new Scanner(System.in);
//        Scanner scanTillEnter = new Scanner(System.in);
        String currentLine,searchString;
        String temp;
        String[] arr;
        String[] sarr,earr,farr;
        int i,j,index = 0;
        int z=0;
        int count=0;
        int count1=0;
        int count2=0;
        System.out.println("+----------------------------------------------------------------------+");
        System.out.println("|                            MySQL                                     |");
        System.out.println("+----------------------------------------------------------------------+");
       // System.out.println("|                                                                      |");

        List<Data> dataList = new ArrayList<Data>();

        while ((currentLine = reader.readLine()) != null) {
            Data data = new Data();
            scan = new Scanner(currentLine);
            arr = currentLine.split("\"");


            if(arr.length == 3){
                count1++;
                sarr = arr[0].split(",");
                earr = arr[2].split(",");
  //              System.out.println(earr[2]);
                //sarr0,1,2
                //earr1,2,3,4
                temp = sarr[1].toString();
//                System.out.println(temp);
                data.setBank(sarr[0].toString());
                data.setIfsc(sarr[1].toString());
                data.setBranch(sarr[2].toString());
                data.setAddress(arr[1].toString());
                data.setContact(earr[1].toString());
                data.setCity(earr[2].toString());
                data.setDistrict(earr[3].toString());
                data.setState(earr[4].toString());

            }

            else if(arr.length == 1){
                farr = arr[0].split(",");
                //System.out.println(farr[3]);
                count2++;
//                    temp = farr[1].toString();
//                    System.out.println(temp);
                    data.setBank(farr[0].toString());
                    data.setBranch(farr[2].toString());
                    data.setIfsc(farr[1].toString());
                    data.setAddress(farr[3].toString());
                    data.setContact(farr[4].toString());
                    data.setCity(farr[5].toString());
                    data.setDistrict(farr[6].toString());
                    data.setState(farr[7].toString());
            }

            dataList.add(data);
        }


        //close reader
        reader.close();

        System.out.println("|                                                                      |");
        System.out.println("| Data collection  ...............................................[OK] |");
        System.out.println("| Intializing data insertion into database .......................[OK] |");
        System.out.println("| Data insertion started  ........................................[OK] |");
//        System.out.println("| Total records entered = " + (count1+count2) + "                                          |");
//        System.out.println("+----------------------------------------------------------------------+");
        String db_name = "db";
        final String jdbcDriver = "com.mysql.jdbc.Driver";
        final String dbUrl = "jdbc:mysql://localhost:3306/" + db_name;
        final String user = "root";
        final String pass = "cnvbcnvb";
        Connection conn = null;
        Statement stmt = null;
        String query;
        try {
            Class.forName(jdbcDriver);
            conn = DriverManager.getConnection(dbUrl, user, pass);
            stmt = conn.createStatement();
            System.out.println("| Entering data ..................................................[OK] |");
            for(Data d: dataList) {
//                String temp1 = d.getAddress();
//                String temp[] = temp1.split(",");
//                System.out.println(d.getAddress());
                query = "insert into bankdata values ('" + d.getBank() + "','" + d.getIfsc() + "','" + d.getBranch() + "','" + d.getAddress() + "','" + d.getContact() + "','" + d.getCity() + "','" + d.getDistrict() + "','" + d.getState() + "')";
                stmt.executeUpdate(query);
//                System.out.print(z);
                //TimeUnit.MICROSECONDS.sleep(1);
//                System.out.print("\b")
//                System.out.println("..");
//                TimeUnit.SECONDS.sleep(1);
//                System.out.println("\b");
//               for(int x =0; x<5; x++) {
//                   System.out.print("\a");
//               }
//                for(int x =0; x<5; x++) {
//                    System.out.print("\b");
//                }

            }

            //stmt = conn.createStatement();
//            System.out.print("]");
            System.out.println("| Data entered ...................................................[OK] |");
            System.out.println("Enter the IFSC code : ");
            searchString = scanIfsc.next();
            query = "select * from bankdata where ifsc like \""  + searchString + "\"";
//            System.out.println(query);
            ResultSet res = stmt.executeQuery(query);
            while(res.next()){
                String bank = res.getString("BANK");
                String ifsc = res.getString("IFSC");
                String branch = res.getString("BRANCH");
                String address = res.getString("ADDRESS");
                String contact = res.getString("CONTACT");
                String city = res.getString("CITY");
                String district = res.getString("DISTRICT");
                String state = res.getString("STATE");

               System.out.println("| Searching for data .............................................[OK] |");
               System.out.println("|--------------------------------xx------------------------------------|");
                System.out.print("Bank = "+ bank + "\nIFSC = "+ ifsc + "\nBranch = "+ branch + "\nAddress = " +address+ "\nContact = " +contact+ "\nCity = "+city+ "\nDistrict = "+district+ "\nState = "+state );
            }
            res.close();

        } catch (SQLException se) {
            se.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
//        catch (InterruptedException e) {
//            e.printStackTrace();
//        }
        finally {
            //finally block to close conn
            try {
                if (stmt != null)
                    conn.close();
            } catch (SQLException se) {
                se.printStackTrace();
            }


        }
//        //System.out.println();
//        System.out.println("Enter the IFSC code : ");
//        searchString = scan.nextLine();
//
//        try{
//            Class.forName(jdbcDriver);
//            conn = DriverManager.getConnection(dbUrl, user, pass);
//            stmt = conn.createStatement();
//            query = "select * from bankdata where ifsc like \""  + searchString + "\"";
//            stmt.executeQuery(query);
//
//
//        }catch (SQLException se) {
//            se.printStackTrace();
//        }catch (ClassNotFoundException e) {
//            e.printStackTrace();
//        } finally {
//            //finally block to close conn
//            try {
//                if (stmt != null)
//                    conn.close();
//            } catch (SQLException se) {
//                se.printStackTrace();
//            }
//        }



    }
}

