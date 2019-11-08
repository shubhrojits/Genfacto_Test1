package environment;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class Setup {
	public WebDriver driver;
	public String url;
	public String username;
	public String password;
	public String Browser;
	public String selectSql;
	public String RUNID;
	
	
	public void getdata()
	{
		url="http://172.20.0.11/genfacto.com/";
	}
	public void setup_browser()
	{
		String path = System. getProperty("user.dir");
		path = path + "\\src\\test\\java\\chromeDriver\\chromedriver.exe";
		//System.setProperty("WebDriver.Chrome.Driver", "D:\\Eclipse2\\art01\\src\\test\\java\\chromeDriver\\chromedriver.exe");
		System.setProperty("WebDriver.Chrome.Driver", path);
		driver = new ChromeDriver();
		driver.navigate().to("http://172.20.0.11/genfacto.com/");
		driver.manage().window().maximize();
	}
	public void db_connection(String SelectSql)
	{

        String connectionUrl =
                "jdbc:sqlserver://172.20.0.11\\SQLExpress:49718;"
                + "database=LTest19;"
                + "user=sa;"
                + "password=git@1234;"
                + "encrypt=false;"
                + "trustServerCertificate=false;"
                + "loginTimeout=30;";

        ResultSet resultSet = null;

        try (Connection connection = DriverManager.getConnection(connectionUrl);
                Statement statement = connection.createStatement();) {

            // Create and execute a SELECT SQL statement.
            selectSql = SelectSql;
            resultSet = statement.executeQuery(selectSql);
            String path = System. getProperty("os.name");
            System.out.println(path);
            System.out.println(resultSet.getRow());
            
            // Print results from select statement
            while (resultSet.next()) {
            	System.out.println("DB Query Result");
            	System.out.println(resultSet.getString(1));
                //System.out.println(resultSet.getString(1) + " || " + resultSet.getString(2) + " || " +resultSet.getString(3));
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    
	}
	
	public void exec_sp(String ExecuteSP) {
		
		try 
		{
			String connectionUrl =
	                "jdbc:sqlserver://172.20.0.11\\SQLExpress:49718;"
	                + "database=LTest19;"
	                + "user=sa;"
	                + "password=git@1234;"
	                + "encrypt=false;"
	                + "trustServerCertificate=false;"
	                + "loginTimeout=30;";

	        ResultSet resultSet = null;

	        try (Connection connection = DriverManager.getConnection(connectionUrl);
	                PreparedStatement prepsInsertProduct = connection.prepareStatement(ExecuteSP, Statement.RETURN_GENERATED_KEYS);) {

	        	prepsInsertProduct.execute();
	        	// Retrieve the generated key from the insert.
	            resultSet = prepsInsertProduct.getGeneratedKeys();

	            // Print the ID of the inserted row.
	            while (resultSet.next()) {
	                System.out.println("Generated: " + resultSet.getString(1));
	            }
	            //resultSet = prepsInsertProduct.executeQuery(ExecuteSP);
	            }
	        }
	        catch (SQLException e) {
	            e.printStackTrace();
	        }
		
		catch (Exception e)
		{
			System.out.println(e.getMessage());
		}
	
	}
	
public int insert_sql(String insertStatement) {
		
		try 
		{
			String connectionUrl =
	                "jdbc:sqlserver://172.20.0.11\\SQLExpress:49718;"
	                + "database=LTest19;"
	                + "user=sa;"
	                + "password=git@1234;"
	                + "encrypt=false;"
	                + "trustServerCertificate=false;"
	                + "loginTimeout=30;";

	        ResultSet resultSet = null;

	        try (Connection connection = DriverManager.getConnection(connectionUrl);
	                PreparedStatement prepsInsertProduct = connection.prepareStatement(insertStatement, Statement.RETURN_GENERATED_KEYS);) {

	        	prepsInsertProduct.execute();
	        	// Retrieve the generated key from the insert.
	            resultSet = prepsInsertProduct.getGeneratedKeys();

	            // Print the ID of the inserted row.
	            while (resultSet.next()) {
	                System.out.println("Generated: " + resultSet.getString(1));
	                RUNID = resultSet.getString(1);
	                System.out.println(RUNID);
	            }
	           
	            //resultSet = prepsInsertProduct.executeQuery(ExecuteSP);
	            }
	        }
	        catch (SQLException e) {
	            e.printStackTrace();
	        }
		
		catch (Exception e)
		{
			System.out.println(e.getMessage());
		}
		 return Integer.parseInt(RUNID);
	}

public int update_sql(String updateStatement) {
	
	try 
	{
		String connectionUrl =
                "jdbc:sqlserver://172.20.0.11\\SQLExpress:49718;"
                + "database=LTest19;"
                + "user=sa;"
                + "password=git@1234;"
                + "encrypt=false;"
                + "trustServerCertificate=false;"
                + "loginTimeout=30;";

        ResultSet resultSet = null;

        try (Connection connection = DriverManager.getConnection(connectionUrl);
                PreparedStatement prepsInsertProduct = connection.prepareStatement(updateStatement, Statement.RETURN_GENERATED_KEYS);) {

        	prepsInsertProduct.execute();
        	// Retrieve the generated key from the insert.
            resultSet = prepsInsertProduct.getGeneratedKeys();

            /* Print the ID of the inserted row.
            while (resultSet.next()) {
                System.out.println("Generated: " + resultSet.getString(1));
                RUNID = resultSet.getString(1);
                System.out.println(RUNID);
            }*/
                       
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
	
	catch (Exception e)
	{
		System.out.println(e.getMessage());
	}
	 return Integer.parseInt(RUNID);
}

}
