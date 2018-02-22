package epita.fr.fundamental.project;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Delete_Identity {

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
		           
	        
	     System.out.println("Enter userid : ");		
	     Scanner a = new Scanner(System.in);
	                 
     
	    a.close(); 
	   	
	   	
	   	
		Connection dbConnection = null;
		PreparedStatement preparedStatement = null;

	
   //DB delete
        String deleteTableSQL = "DELETE IDENTITIES WHERE uid = ?";
        

    	try {
    		
    		 MyLogger.setup();
			
   //Register JDBC driver
    	      Class.forName(DB_DRIVER);

   //Open a connection
//    	      System.out.println("Connecting to a selected database...");
    	      LOGGER.info("Connecting to selected DB");                       
    	      
    	      dbConnection = DriverManager.getConnection(DB_CONNECTION, DB_USER, DB_PASSWORD);
//    	      System.out.println("Connected database successfully...");
    	      LOGGER.info("Connected database successfully...");                       
			
  //Creating the DB Table		
			preparedStatement = dbConnection.prepareStatement(deleteTableSQL);			
			
  //Execute delete SQL statement
	      	preparedStatement.executeUpdate();
			

			dbConnection.commit();
			
//			System.out.println("Record is deleted in the DB table!");
			LOGGER.info("Record is deleted in the DB table!");                  
			

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
//		   System.out.println("Class Delete execution complete");
		   LOGGER.info("Class Delete execution complete");           
		   
		}//end main

	}


