import java.sql.*; 
import java.util.*; 
import java.lang.*;
public class jdbceg{
    public static void main(String[] args) throws Exception{
        Scanner sc=new Scanner(System.in);
        List<String> tblist=new ArrayList<String>();
        String db_url="jdbc:mysql://localhost/db_practise_jdbc"; 
        String userid="root";
        String pswd=""; 
        System.out.println("Make a choice: \n 1. Create a table \n 2.Display the table \n 3.Display particular column \n 4.Insert value into table \n 5.Delete value from table \n 6.Drop table");
        int ch=sc.nextInt();
        Class.forName("com.mysql.jdbc.Driver");  //loads the register
        Connection con=DriverManager.getConnection(db_url, userid, pswd);
        Statement stmt=con.createStatement(); 
        switch(ch){
            case 1:   //Create table with different table-name for pre-defined variables
                System.out.println("Enter the table name: ");
                String tablename=sc.next();
                tblist.add(tablename);
                if(tblist.contains(tablename)){
                    System.out.print("Table name already exist");
                }else{
                    stmt.executeUpdate("CREATE TABLE "+tablename+"(id int,name char(25), age int, mailid varchar(18))");
                    System.out.println("Table created successfully!!"); 
                    tblist.add(tablename);
                }    
                break;
            case 2:   //Display all values from table
                ResultSet set=stmt.executeQuery("select * from jdbc_practise");
                if(set.getRow()==0){
                    System.out.print("Table is empty");
                }else{
                    while(set.next()){
                        System.out.println(set.getInt(1)+"     "+set.getString(2)+"    "+set.getString(3));
                    }
                }    
                break; 
            case 3:
                System.out.print("Enter the name of specific column: ");
                String colname=sc.next(); 
                System.out.print("Enter the table name: ");
                String tblname=sc.next();
                ResultSet rs=stmt.executeQuery("select " + colname +" from " + tblname);
                if(rs.getRow()==0){
                    System.out.print("Table is empty");
                }else{
                    while(rs.next()){
                        System.out.println(rs.next());
                    }    
                }     
                break;
            case 4:  //Insert values into table
                System.out.println("Enter name,age and mailid to be inserted: ");
                String name1=sc.next();
                int age=sc.nextInt();
                String mailid=sc.next();
                System.out.println("Enter the table-name in which the data has to be inserted: ");
                String name=sc.next();
                // int id += 1;
                String query="INSERT INTO "+ name +" (name,age,mailid) VALUES(?,?,?)"; 
                PreparedStatement prepstmt = con.prepareStatement(query);
                prepstmt.setString(1, name1);
                prepstmt.setInt(2, age);
                prepstmt.setString(3, mailid);
                prepstmt.executeUpdate();
                System.out.print("Data Inserted successfully!");
                break; 
            case 5:  //delete value from table   
                System.out.println("Enter the table for which the datas in the table has to be deleted: ");
                String deltable=sc.next();
                stmt.executeUpdate("DELETE from "+deltable+" where id>=1");
                System.out.println("Data has been deleted successfully!!");
                break;
            case 6:  //delete table
                System.out.println("Enter the table to be deleted: ");
                String name2=sc.next();
                stmt.execute("DROP table "+name2);   
                System.out.println("Table has been deleted successfully!!");  
        }        
    }
}