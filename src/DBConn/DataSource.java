package DBConn;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DataSource {
	
	/*Static variable of type of the class
	 * which will be returned by the getInstance method*/
	private static DataSource instance;
	private String db = "jdbc:mysql://apontejaj.com:3306/customer";
	private String un = "cctstudent";
	private String pw = "Pass1234!";
	
	//Global variables due the will be used in three methods
	private Connection conn;
	private Statement stmt;
	private ResultSet rs = null;

	//constructor
	private DataSource() {

		try{

			// Get a connection to the database
			conn = DriverManager.getConnection( db, un, pw ) ;

			// Get a statement from the connection
			stmt = conn.createStatement() ;

		}
		catch( SQLException se ){
			System.out.println( "SQL Exception:" ) ;

			// Loop through the SQL Exceptions
			while( se != null ){
				System.out.println( "State  : " + se.getSQLState()  ) ;
				System.out.println( "Message: " + se.getMessage()   ) ;
				System.out.println( "Error  : " + se.getErrorCode() ) ;

				se = se.getNextException() ;
			}
		}
		catch( Exception e ){
			System.out.println( e ) ;
		}
	}

	//Method in charge of retrieve data from DB
	public ResultSet select(String query) {
		try {
			rs = stmt.executeQuery(query);

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return rs;
	}

	//MEthod to add entries(countries) to the DB
	public boolean save(String query) {

		try {
			stmt.execute(query);
			return true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}	
	}

	//Method for closing connection
	public void closing() {
		try {
			rs.close();
			stmt.close();
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	/*getInstance method to be able to initialized
	 * or DataSource object
	 * Note we are using synchronized method to add
	 * thread safety*/
	public static DataSource getInstance() {
		
		if(instance == null) {
			synchronized (DataSource.class) {
				if(instance == null) {
					instance = new DataSource ();
				}	
			}
		}
		
		return instance;
	}
	
}
