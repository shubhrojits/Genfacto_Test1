package environment;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class SqlConnection {

    // Connect to your database.
    // Replace server name, username, and password with your credentials
    public static void main(String[] args) throws UnknownHostException {
        String connectionUrl =
                "jdbc:sqlserver://172.20.0.11\\SQLExpress:49718;"
                + "database=Git_Master;"
                + "user=sa;"
                + "password=git@1234;"
                + "encrypt=false;"
                + "trustServerCertificate=false;"
                + "loginTimeout=30;";

        ResultSet resultSet = null;

        try (Connection connection = DriverManager.getConnection(connectionUrl);
                Statement statement = connection.createStatement();) {

            // Create and execute a SELECT SQL statement.
            String selectSql = "SELECT top 5 * from Countries order by CountryId";
            resultSet = statement.executeQuery(selectSql);
            String OS_Name = System. getProperty("os.name");
            
            String OS_Ver = System.getProperty("java.vm.name");
            String computername=InetAddress.getLocalHost().getHostName();
            
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");  
            LocalDateTime now = LocalDateTime.now(); 
            String currenttime = dtf.format(now);
            //String date = gGetDate()
            //System.out.println(getdate());
            System.out.println(OS_Name +": "+ computername + ": "+currenttime );
            System.out.println(resultSet.getRow());
            
            // Print results from select statement
            while (resultSet.next()) {
            	
                System.out.println(resultSet.getString(1) + " || " + resultSet.getString(2) + " || " +resultSet.getString(3));
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }
}