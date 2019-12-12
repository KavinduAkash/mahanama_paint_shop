package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.io.*;  

public class DBConnection {
	private static DBConnection dbconnection;
	private Connection connection;

	private DBConnection() throws SQLException, ClassNotFoundException {
		Class.forName("com.mysql.jdbc.Driver");
		connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/mahanamaPaintShop", "root", "ijse");
	}

	public Connection getConnection() {
		return connection;
	}

	public static DBConnection getInstance() throws SQLException, ClassNotFoundException {
		if (null == dbconnection) {
			dbconnection = new DBConnection();
		}
		return dbconnection;
	}
}
//
//    public static void main(String[] args) {
//			try{
//			  //Creating the object
//			  Student s1 =new Student(211,"ravi");
//			  //Creating stream and writing the object
//			  FileOutputStream fout=new FileOutputStream("C:\\Users\\kavindu\\Desktop\\serialization.txt");
//			  ObjectOutputStream out=new ObjectOutputStream(fout);
//			  out.writeObject(s1);
//			  out.flush();
//			  //closing the stream
//			  out.close();
//			  System.out.println("success");
//			  }catch(Exception e){System.out.println(e);}
//			}
//	}
//
//
//class Student{
//		int id;
//		String name;
//
//		Student(int id, String name){
//			this.id = id;
//			this.name = name;
//		}
//}
