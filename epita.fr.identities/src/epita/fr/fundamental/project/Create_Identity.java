package epita.fr.fundamental.project;

import java.sql.Connection;
import epita.fr.fundamental.project.MyLogger;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;
import java.sql.DriverManager;
import java.util.logging.Level;
import java.util.logging.Logger;


public class Create_Identity  {
	
			 

	public static void main(String[] args) {
		
	//Implementing MyLogger Class 	
		   final Logger LOGGER = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
		   LOGGER.setLevel(Level.INFO);
	       
		
	//Declaring DB Connection constants	
		final String DB_DRIVER = "org.apache.derby.jdbc.EmbeddedDriver";
		final String DB_CONNECTION = "jdbc:derby:MyDB;create=true";
		final String DB_USER = "shreya";
		final String DB_PASSWORD = "amit05";
		
	//Declaring identity table fields	
		String displayName ;
		String uid;
		String email;
		  
	//Taking input from user	
		System.out.println("Enter display name : ");	
	    Scanner a = new Scanner(System.in);
	    displayName = a.nextLine();
	                  
	        
	     System.out.println("Enter userid : ");	           
	     Scanner b = new Scanner(System.in);
	     uid = b.nextLine();
	                 

	     System.out.println("Enter email : ");	     
	     Scanner c = new Scanner(System.in);
	     email = c.nextLine();
	     
	    a.close(); 
	   	b.close();
	   	c.close();

	   	
	   	
		Connection dbConnection = null;
		PreparedStatement preparedStatement = null;

	//DB create 	
		String createTableSQL = "CREATE TABLE IDENTITIES" + " (displayName VARCHAR(255)," + 
				                 " uid VARCHAR(255) PRIMARY KEY," + "email VARCHAR(255)";
		
   //DB insert
        String insertTableSQL = "INSERT INTO IDENTITIES (displayName, uid, email " + "values (?, ?, ?)";
        

    	try {
			
    		
    		 MyLogger.setup();
    		
    		
   //Register JDBC driver
    	      Class.forName(DB_DRIVER);

   //Open a connection
   // 	      System.out.println("Connecting to a selected database...");
    	      LOGGER.info("Connecting to a selected database...");
    	      dbConnection = DriverManager.getConnection(DB_CONNECTION, DB_USER, DB_PASSWORD);
    	      
   // 	      System.out.println("Connected database successfully...");
    	      LOGGER.info("Connected database successfully...");
    	      
  //Creating the DB Table		
			preparedStatement = dbConnection.prepareStatement(createTableSQL);			
			preparedStatement.setString(1,displayName);
			preparedStatement.setString(2,uid);
			preparedStatement.setString(3,email);
		
  //Execute insert SQL statement
	      	preparedStatement.executeUpdate();			

			dbConnection.commit();
			
	//		System.out.println("Record is inserted in the DB table!");
			LOGGER.info("Record is inserted in the DB table!");
			
		 }catch(SQLException se){
 //Handle errors for JDBC
		      se.printStackTrace();
		      LOGGER.severe("Error Log");           //Error Log messages
		      
		   }catch(Exception e){
//Handle errors for Class.forName
		      e.printStackTrace();
		      LOGGER.severe("Error Log");
		      
		   }finally{
 //finally block used to close resources
		      try{
		         if(preparedStatement!=null)
		            dbConnection.close();
		      }catch(SQLException se){
		      }// do nothing
		      try{
		         if(dbConnection!=null)
		            dbConnection.close();
		      }catch(SQLException se){
		         se.printStackTrace();
		         LOGGER.severe("Error Log");
		         
		      }//end finally try
		      
		   }//end try
//		   System.out.println("Class Insert execution complete");
		   LOGGER.info("Class Insert execution complete");
		   
		}//end main
}

	




