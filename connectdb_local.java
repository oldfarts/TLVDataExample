import java.sql.*;



public class connectdb_local {
   // JDBC driver name and database URL
   // static final String JDBC_DRIVER = "jtds.jdbc.Driver";  
   static final String DB_URL = "jdbc:sqlserver://OLDFART-PC\\MSSQLSERVER:1433; databaseName=Pelikanta; integratedSecurity=true";

   //  Database credentials
   static final String USER = "jarvas77@hotmail.com";	// Username
   static final String PASS = "***";				// Password
   
   public static void main(String[] args) {
   Connection conn = null; 
   Statement stmt = null;
   
   // Read data from TLV file
   String intoDB;
   readFile readContent = new readFile();
   intoDB =  readContent.openFileAndReadFromFile();


   // Try to insert the data into DB
   try{
      //STEP 2: Register JDBC driver
      Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");

      //STEP 3: Open a connection
      System.out.println("Connecting to database...");
      conn = DriverManager.getConnection(DB_URL,USER,PASS);

      //STEP 4: Execute a query
      System.out.println("Creating statement...");
      stmt = conn.createStatement();

	   // Insert the read values from file into DB
      String sqlInsertGrocery = null;
      String sqlInsertHardware = null;
      String sqlInsertComputer = null;
      String sqlInsertGun = null;
      String sqlInsertWallmart = null;
      
      // Select the correct product and deliver it to warehouse...
      String groceryValue = intoDB.substring(0,1);
      String hardwareValue = intoDB.substring(2,3);
      String computerValue = intoDB.substring(4,5);
      String gunValue = intoDB.substring(6,7);
      String wallmartValue = intoDB.substring(8,9);
      
      // Case grocery store
      //sqlInsertHardware = "INSERT INTO dbo.Hardware(Tools) VALUES (groceryValue)";
      PreparedStatement prep = conn.prepareStatement("INSERT INTO dbo.Foods(Foods) VALUES (?)");
      prep.setString(1,groceryValue);
      prep.executeUpdate();
      // Case hardware store
      //sqlInsertHardware = "INSERT INTO dbo.Hardware(Tools) VALUES (hardwareValue)";
      PreparedStatement prep2 = conn.prepareStatement("INSERT INTO dbo.Hardware(Tools) VALUES (?)");
      prep2.setString(1,hardwareValue);
      prep2.executeUpdate();
      // Case computer store
      //sqlInsertComputer = "INSERT INTO dbo.Computers(Computers) VALUES (computerValue)";
      PreparedStatement prep3 = conn.prepareStatement("INSERT INTO dbo.Computers(Computers) VALUES (?)");
      prep3.setString(1,computerValue);
      prep3.executeUpdate();
      // Case Gun store
      //sqlInsertGun = "INSERT INTO dbo.Guns(Guns) VALUES (gunValue)";
      PreparedStatement prep4 = conn.prepareStatement("INSERT INTO dbo.Guns(Guns) VALUES (?)");
      prep4.setString(1,gunValue);
      prep4.executeUpdate();
      // Case wallmart store
      //sqlInsertWallmart = "IINSERT INTO dbo.Wallmart(Ammo) VALUES (wallmartValue)";
      PreparedStatement prep5 = conn.prepareStatement("INSERT INTO dbo.Wallmart(Ammo, Guns) VALUES (?,?)");
      prep5.setString(1,wallmartValue);
      prep5.setString(2, gunValue);
      prep5.executeUpdate();
      
      
      // STEP 5. Execute the queries
	   ResultSet rs = stmt.executeQuery(sqlInsertGrocery);	   
	   rs.close();
	   ResultSet rs2 = stmt.executeQuery(sqlInsertHardware);
	   rs2.close();
	   ResultSet rs3 = stmt.executeQuery(sqlInsertComputer);
	   rs3.close();
	   ResultSet rs4 = stmt.executeQuery(sqlInsertGun);
	   rs4.close();
	   ResultSet rs5 = stmt.executeQuery(sqlInsertWallmart);	   
	   rs5.close();	   
	   
	   //STEP 6: Clean-up environment
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
   
   System.out.println("Goodbye!");
   }//end main

}



