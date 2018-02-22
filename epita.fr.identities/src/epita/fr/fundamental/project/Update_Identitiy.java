package epita.fr.fundamental.project;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Update_Identitiy {

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

			
		   //DB update
		        String updateTableSQL = "UPDATE IDENTITIES SET displayName = ? AND email = ?" + " WHERE uid = ?";
		        

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
		  	
					preparedStatement = dbConnection.prepareStatement(updateTableSQL);			
					preparedStatement.setString(1,displayName);
//					preparedStatement.setString(2,uid);
					preparedStatement.setString(3,email);
				
		  //Execute update SQL statement
			      	preparedStatement.executeUpdate();
					

					dbConnection.commit();
					
		//			System.out.println("Record is updated in the DB table!");
					LOGGER.info("Record is updated in the DB table!");

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
        //System.out.println("Class Update execution complete");
				   LOGGER.info("Class Update execution complete");
				   
				}//end main
		

	}


