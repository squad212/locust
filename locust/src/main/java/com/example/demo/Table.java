package com.example.demo;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Table {
	
		public static void main(String args[]) {
			Connection conn=null;
			try {
				conn = DatabaseConnection.getDBConnection();
				conn.setAutoCommit(false);

				//registerUser("a", "a", conn);
				//deleteUser("a", conn);
				boolean res=validateLogin("jane", "jane123", conn);
				System.out.println(res);
				conn.commit();
			} catch (Exception e) {
				e.printStackTrace();
				try {
					conn.rollback();
				} catch (SQLException e1) {
				}
			}finally {
				try {
					conn.close();
				}catch(Exception ee) {}
			}
			
		}
		
		
		
		/*
		 Connection conn =null;
			try{
					Class.forName("com.mysql.jdbc.Driver");
					// connection string
				    conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/website","root","root"); 
			  return conn;
			}
			try {
				conn = DatabaseConnection.getDBConnection();
				conn.setAutoCommit(false);
				registerUser("newuser","newpwd",  conn) 
				conn.commit();
			} catch (Exception e) {
					conn.rollback();
			}finally {
					conn.close();
			}
			*/
		public static void registerUser(String uid,String pwd,  Connection conn) throws  SQLException
	    {
	        PreparedStatement stmt = null;
	        try
	        {
	            stmt = conn.prepareStatement
	            		("insert into login (userid,password) values ( ?,? )");
	            stmt.setString(1, uid); // binding
	            stmt.setString(2, pwd);
	            int count= stmt.executeUpdate();
	            System.out.println("records created :"+count);
	        }
	        catch (SQLException e)
	        {
	        	throw e;
	        }
	        finally
	        {
	            try{
	            	stmt.close();} catch(SQLException s){}
	        }
	    }

		
		//updateOrder("john","new_pasword")
		public static void updatePwd(String uid,String pwd, Connection conn) throws  SQLException
	    {
	        PreparedStatement stmt = null;
	        try
	        {
	            stmt = conn.prepareStatement("update login set password=? where userid=?");
	            stmt.setString(1, pwd);
	            stmt.setString(2, uid);
	            stmt.executeUpdate();
	        }
	        catch (SQLException e)
	        {
	        	throw e;
	        }
	        finally
	        {
	            try{
	            	stmt.close();} catch(SQLException s){}
	        }
	     
	    }
		

		public static void deleteUser(String delUid,Connection conn) throws  SQLException
	    {
	        PreparedStatement stmt = null;
	        try
	        {
	            stmt = conn.prepareStatement("delete from login where userid=?");
	            stmt.setString(1, delUid);
	            int rowsupdated=stmt.executeUpdate();
	            System.out.println("records deleted:"+rowsupdated);
	        }
	        catch (SQLException e)
	        {
	        	throw e;
	        }
	        finally
	        {
	            try{
	            	stmt.close();} catch(SQLException s){}
	        }
	     
	    }
	 

		public static boolean validateLogin(String userid,String pwd,Connection conn) throws SQLException 
	    {
			//date="03/20/15"
	        PreparedStatement stmt = null;
	        ResultSet resultSet = null;
	        boolean result=false;
	        try
	        {
	        	stmt = conn.
	        			prepareStatement
	        			("select userid from login where userid =? and password=?");
	        	stmt.setString(1, userid);
	        	stmt.setString(2, pwd);
	            resultSet = stmt.executeQuery();
	            if(resultSet.next())
	            {
	            	result=true;
	            }/* sample code to read multiple records
	            ArrayList<product> prductList=new ArrayList<product> (); 
	            while(resultSet.next())
	            {
	            	Product p=new Product();
	            	p.setProdName(resultSet.getString("product_name"));
	            	p.setEnroStatus(resultSet.getString("enrollment_status"));
	            	productList.add(p);
	            }
	            */
	            return result;
	        }
	        catch (SQLException e)
	        {
	        	System.out.println("Exception occired");
	        	e.printStackTrace();
	            throw e;
	        }
	        finally
	        {
	        	try{
	        		resultSet.close(); } catch(SQLException s){}
	            try{
	            	stmt.close();} catch(SQLException s){}
	            
	        }
	        
	    }
		

	}








