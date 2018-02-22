package epita.fr.fundamental.project;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.sql.DriverManager;


public class Select_Identity {

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
				String uid;			  
  	
			   	
				Connection dbConnection = null;
				PreparedStatement preparedStatement = null;

				
		   //DB select
		        String selectTableSQL = "SELECT * FROM IDENTITIES WHERE uid = ?";
		        

		    	try {
		    		
		    		
		    		 MyLogger.setup();
					
		   //Register JDBC driver
		    	      Class.forName(DB_DRIVER);

		   //Open a connection
		    //	      System.out.println("Connecting to a selected database...");
		    	      LOGGER.info("Connecting to a selected database...");
		    	      
		    	      dbConnection = DriverManager.getConnection(DB_CONNECTION, DB_USER, DB_PASSWORD);
		   // 	      System.out.println("Connected database successfully...");
		    	      LOGGER.info("Connected database successfully...");
		  		
					preparedStatement = dbConnection.prepareStatement(selectTableSQL);			
				
		  //Execute select SQL statement
			      	preparedStatement.executeUpdate();
					

					dbConnection.commit();
					
		  //Getting multiple rows from the DB table			
					ResultSet rs= preparedStatement.executeQuery();
					dbConnection.commit();
					while(rs.next()){  
					System.out.println(rs.getString(1)+" "+rs.getString(2));  
					}  
					
 			//		System.out.println("Records selected from the DB table!");
					LOGGER.info("Records selected from the DB table!");

				 }catch(SQLException se){
		 //Handle errors for JDBC
				      se.printStackTrace();
				      LOGGER.severe("Error Log");           //Error Log messages
				   }catch(Exception e){
		//Handle errors for Class.forName
				      e.printStackTrace();
				      LOGGER.severe("Error Log");           //Error Log messages
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
				         LOGGER.severe("Error Log");           //Error Log messages
				         
				      }//end finally try
				      
				   }//end try
		//		   System.out.println("Class select execution complete");
				   LOGGER.severe("Class select execution complete");           //Information Log messages
				   
				}//end main
		
	}


