import java.sql.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.StringTokenizer;

public class ExcelToDb {
 // JDBC driver name and database URL
 static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";  
 static final String DB_URL = "jdbc:mysql://localhost:3306/Lab06";

 //  Database credentials
 static final String USER = "root";
 static final String PASS = "seecs@123";
 
 public static void main(String[] args) {
 Connection conn = null;
 Statement stmt = null;
 try{
    //STEP 2: Register JDBC driver
    Class.forName("com.mysql.jdbc.Driver");

    //STEP 3: Open a connection
    System.out.println("Connecting to database...");
    conn = DriverManager.getConnection(DB_URL,USER,PASS);

    //STEP 4: Execute a query
    System.out.println("Creating statement...");
    stmt = conn.createStatement();
    String sql;
    sql = "use `Lab06`;";
   String sql2 = "CREATE table IF NOT exists `GeoCity`( `locId` bigint,`country` varchar(10) not null,`region` varchar(60) not null,`city` varchar(30) not null,`postalCode` varchar(10) not null,`latitude` double,`longitude` double, `metroCode`  int ,`areaCode` int ,primary key (`locId`));";
   String sql3 = "CREATE database IF NOT EXISTS `Lab06`;";
   System.out.println("Creating Database..");
   stmt.executeUpdate(sql3);
   
   System.out.println("Selecting Database..");
    stmt.executeUpdate(sql);
    
    System.out.println("Creating table...");
    stmt.executeUpdate(sql2);

    String splitBy = ",";
BufferedReader read = new BufferedReader(new FileReader("C://Users//Maryam Attiq//Desktop//AP//GeoLiteCity-Location.csv"));
		String line = null;
		while ((line = read.readLine()) != null) {
		String[] items= line.split(",",-1);
                            for(int i = 0;i<9;i++){
                            if(items[i].length() == 0){
                            items[i] = "0";
                            }
                            }

String insertSql = "INSERT INTO GeoCity Values(" + items[0] + ",'" +items[1]+" ','" +items[2]+"','" +items[3]+"','" +items[4]+"','" +items[5]+"','" +items[6]+"','" +items[7]+"','" +items[7]+"');";

stmt.executeUpdate(insertSql);

 }
		stmt.close();
		conn.close(); 
 }catch(SQLException se){
    //Handle errors for JDBC
    se.printStackTrace();
 }catch(Exception e){
    //Handle errors for Class.forName
    e.printStackTrace();
 }finally{
    //finally block used to close resources
    try{
       if(stmt!=null)
          stmt.close();
    }catch(SQLException se2){
    }// nothing we can do
    try{
       if(conn!=null)
          conn.close();
    }catch(SQLException se){
       se.printStackTrace();
    }//end finally try
 }//end try
 System.out.println("\nGoodbye!");
}//end main
}//end FirstExample
