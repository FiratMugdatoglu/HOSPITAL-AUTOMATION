package Helper;
import java.sql.*;

public class DBConnection {

	Connection c = null;
	
	
	public DBConnection() {
		
	}
	
	public Connection connDb() {
		try {
			this.c=DriverManager.getConnection("jdbc:mariadb://localhost:3306/hospital?user=root&password=12345");
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return c;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
