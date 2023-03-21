import java.util.*;
import com.mysql.cj.protocol.Resultset;
import java.sql.*;

class login
{
    String Username;
    String Password;
    void check()
    {
         Scanner out = new Scanner(System.in);
            while(true){
                System.out.println("Enter Username :");
                Username = out.nextLine();
                System.out.println("Enter Password :");
                Password = out.nextLine();
   
                if(Username.equals("Admin")&&Password.equals("Admin"))
                {
                    System.out.println("Welcome "+Username);
                    break;
                }

                else
                {
                    System.out.println("Wrong Username or Password");
                    
                }
            }
     
   
    }
}

class Customer
{
    Connection con;
    void createCon()
    {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/Library_Management_System", "root", "Kunal#2003");
        } 
        catch (Exception e) 
        {
            e.printStackTrace();
        }
    }
    void insertCust()
    {
        try 
        {
            Scanner in = new Scanner(System.in);
            String Q = "INSERT INTO CUSTOMER(CustomerName,CustomerPhone,CustomerArea) VALUES(?,?,?);";
            PreparedStatement stmt = con.prepareStatement(Q);
            System.out.println("Enter your Name :");
            String cname = in.nextLine();
            System.out.println("Enter your Phone Number :");
            String cphone = in.nextLine();
            System.out.println("Enter your Area :");
            String carea = in.nextLine();
            stmt.setString(1, cname);
            stmt.setString(2, cphone);
            stmt.setString(3, carea);

            stmt.executeUpdate();

        } 
        catch (Exception e) 
        {
            e.printStackTrace();
        }
    }

    void ShowCust()
    {
        try {
            String Q = "SELECT * FROM Customer";
            PreparedStatement pstmt = con.prepareStatement(Q);
            ResultSet rs = pstmt.executeQuery();

            System.out.println("CustomerID\tCustomerName\tCustomerPhone\tCustomerArea");
            while(rs.next())
            {
                int cid = rs.getInt(1);
                String cname = rs.getString(2);
                String cphone = rs.getString(3);
                String carea = rs.getString(4);

                System.out.println(cid + "\t\t" + cname + "\t\t" + cphone + "\t\t" + carea);
            }
        } 
        catch (Exception e)
        {
            
        }
    }
    void DelCust()
    {
        try {
            Scanner in = new Scanner(System.in);
            String Q = "DELETE FROM CUSTOMER WHERE CUSTOMERID=?";
            PreparedStatement pstmt = con.prepareStatement(Q);
            System.out.println("Enter ID: ");
            int id = in.nextInt();
            pstmt.setInt(1,id);
            pstmt.executeUpdate();

        } catch (Exception e) {
        
        }
    }

}

class Book_Deatils
{
    Connection con;
    void createCon()
    {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/Library_Management_System", "root", "Kunal#2003");
        } 
        catch (Exception e) 
        {
            e.printStackTrace();
        }
    }
    void insert_book()
    {
        try 
        {
            Scanner in = new Scanner(System.in);
            String Q = "INSERT INTO BOOK(BookName,BookAuthor,BookQuantity) VALUES(?,?,?);";
            PreparedStatement stmt = con.prepareStatement(Q);
            System.out.println("Enter Book name :");
            String bname = in.nextLine();
            System.out.println("Enter Author name :");
            String aname = in.nextLine();
            System.out.println("Enter your Quantity :");
            int Quant = in.nextInt();
            stmt.setString(1, bname);
            stmt.setString(2, aname);
            stmt.setInt(3, Quant);

            stmt.executeUpdate();

        } 
        catch (Exception e) 
        {
            e.printStackTrace();
        }

    }
    void ShowBook()
    {
        try {
            String Q = "SELECT * FROM Book";
            PreparedStatement pstmt = con.prepareStatement(Q);
            ResultSet rs = pstmt.executeQuery();

            System.out.println("BookID\t\tBookName\tAuthorName\tQuantity");
            while(rs.next())
            {
                int bid = rs.getInt(1);
                String bname = rs.getString(2);
                String aname = rs.getString(3);
                String Quant = rs.getString(4);

                System.out.println(bid + "\t\t" + bname + "\t" + aname + "\t" + Quant);
            }
        } 
        catch (Exception e)
        {
            
        }
    }

    void Delbook()
    {
        try {
            Scanner in = new Scanner(System.in);
            String Q = "DELETE FROM Book WHERE BookID=?";
            PreparedStatement pstmt = con.prepareStatement(Q);
            System.out.println("Enter ID: ");
            int id = in.nextInt();
            pstmt.setInt(1,id);
            pstmt.executeUpdate();

        } catch (Exception e) {
        
        }
    }


}

class Issue_Book
{
    Scanner in = new Scanner(System.in);
    Connection con;
    void createCon()
    {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/Library_Management_System", "root", "Kunal#2003");
        } 
        catch (Exception e) 
        {
            e.printStackTrace();
        }
    }
    void InsertIssue()
    {
       

        try 
        {
            String Q = "INSERT INTO Records(CustomerID,BookID,BookQuant,CurrentDate,ReturnDate) VALUES(?,?,?,CURDATE(),?)";
            PreparedStatement stmt = con.prepareStatement(Q);

            System.out.println("Enter Your Customer ID :");
            int Cust_ID = in.nextInt();
            System.out.println("Enter Your Book ID :");
            int Book_ID = in.nextInt();
            System.out.println("Enter Quantity Of Book :");
            int Book_Quantity = in.nextInt();
            in.nextLine();
            // System.out.println("Enter Current Date(YYYY-MM-DD) :");
            // String Issued_Date = in.nextLine();
            System.out.println("Enter Return Date(YYYY-MM-DD) :");
            String Due_Date = in.nextLine();
            Scanner in = new Scanner(System.in);


            stmt.setInt(1, Cust_ID);
            stmt.setInt(2, Book_ID);
            stmt.setInt(3, Book_Quantity);
            stmt.setString(4, Due_Date);

            stmt.executeUpdate();

        } 
        catch (Exception e) 
        {
            e.printStackTrace();
        }
    }
    void ShowRecords()
    {
        try {
            String Q = "SELECT * FROM Records";
            PreparedStatement pstmt = con.prepareStatement(Q);
            ResultSet rs = pstmt.executeQuery();

            while(rs.next())
            {   

                int Record_ID = rs.getInt(1);
                int Cust_ID = rs.getInt(2);
                int Book_ID = rs.getInt(3);
                int Book_Quantity = rs.getInt(4);
                String Issued_Date = rs.getString(5);
                String Due_Date = rs.getString(6);
                String Status = rs.getString(7);

                System.out.println("\nRecord ID: " + Record_ID);
                System.out.println("Customer ID: " + Cust_ID);
                System.out.println("Book ID: " + Book_ID);
                System.out.println("Book Quantity: " + Book_Quantity);
                System.out.println("Issued Date: " + Issued_Date);
                System.out.println("Reutrn Date: " + Due_Date);
                System.out.println("Reutrn Status: " + Status);
            }
        } 
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }
    void returnBook()
    {
        try 
        {
            String Q = "Update records set rstatus = 'Returned' where RID  = ?";
            PreparedStatement stmt = con.prepareStatement(Q);

            System.out.println("Enter Your Record ID :");
            int Record_ID = in.nextInt();


            stmt.setInt(1, Record_ID);

            stmt.executeUpdate();

        } 
        catch (Exception e) 
        {
            e.printStackTrace();
        }
    }

}



class Library_Management_System {
    public static void main(String[] args)
    {
        int a;
        int b;
        login obj = new login();
        Customer obj2 = new Customer();
        Book_Deatils obj3 = new Book_Deatils();
        Issue_Book obj4 = new Issue_Book();
        obj2.createCon();
        obj3.createCon();

        
        Scanner in = new Scanner(System.in);
        
        obj.check();


        
        while(true){
            System.out.println("Press <1> For Customer Details");
            System.out.println("Press <2> For Books Details");
            System.out.println("Press <3> For Issue Book");
            System.out.println("Press <4> For Return the Book");
            System.out.println("Press <5> For Records");
            System.out.println("Press <6> For exit");
            
            System.out.println("Enter your Choice :");
            
            int choice = in.nextInt();
            switch(choice){
                case 1:
                System.out.println("If you want to add Customer press<1>");
                System.out.println("If you want to Show Customer list press<2>");
                System.out.println("If you want to Delete Customer press<3>");
                System.out.println("Enter Your Choice :");
                a=in.nextInt();
                if(a == 1)
                {
                    obj2.insertCust();
                    break;
                }
                else if(a == 2)
                {
                    obj2.ShowCust();
                    break;
                }
                else if(a == 3)
                {
                    obj2.DelCust();
                    break;
                }
                case 2:
                System.out.println("If you want to add Book press<1>");
                System.out.println("If you want to Show Book list press<2>");
                System.out.println("If you want to Delete Book press<3>");
                System.out.println("Enter Your Choice :");
                b = in.nextInt();

                if(b == 1)
                {
                    obj3.insert_book();
                    break;
                }
                else if (b == 2)
                {
                    obj3.ShowBook();
                    break;
                }
                else if(b == 3)
                {
                    obj3.Delbook();
                    break;
                }
                case 3:
                {
                    obj4.createCon();
                    obj4.InsertIssue();
                    break;
                }
                case 4:
                {
                    obj4.createCon();
                    obj4.returnBook();
                    break;
                }

                case 5:
                {
                    obj4.createCon();
                    obj4.ShowRecords();
                    break;
                }
                
                case 6: 
                String exit = "";
                while(true)
                {
                    System.out.println("Do you want to Exit? [Y/N]");
                    exit = in.next();
                    if(exit.equals("Y"))
                    {
                        System.exit(1);
                    }
                    else if(exit.equals("N"))
                    {
                        break;
                    }
                    else
                    {
                        System.out.println("Invalid! Try again!");
                    }
                }
                
                
            }
            
        }
        
    }
    
}
