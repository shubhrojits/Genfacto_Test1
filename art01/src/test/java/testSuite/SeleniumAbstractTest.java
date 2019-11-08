package testSuite;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import environment.Setup;
import pages.Landing_page;

public abstract class SeleniumAbstractTest extends Setup{
	
	public String exec_result;
	public String TC_name;
	public int RUNID;
	public String TCID;
	public String TCNAME;
	public String RESULT;
	public String ERRORDESCRIPTION;
	public String EXECUTEDON;
	public String RUNNINGTIME;
	public String BATCH;
	public String SCREENSHOT;
	public String insertSql1;
	public String insertSql2;
	public String updateSQL;
	
	@BeforeTest
	public void beforeTest() throws UnknownHostException
	{
		setup_browser();
		//selectSql = "SELECT RunID from RunIdDetails";
		String OS_Name = System. getProperty("os.name"); 
        //String OS_Ver = System.getProperty("java.vm.name");
        String computername=InetAddress.getLocalHost().getHostName();
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");  
        LocalDateTime now = LocalDateTime.now(); 
        String currenttime = dtf.format(now);
        System.out.println(OS_Name +": "+ computername );
        insertSql1 = "INSERT INTO RUNIDDETAILS (STARTTIME,OS,SOLUTION,BROWSER,HOSTNAME) VALUES ('"+currenttime+ "','"+OS_Name+"','GENERIC','CHROME','"+computername+"')";
	    System.out.println(insertSql1);
	    RUNID = insert_sql(insertSql1);
	    //selectSql=insertSql1;
		//db_connection(selectSql);
		
	}
	
	@AfterTest
	public void afterTest()
	{
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");  
        LocalDateTime now = LocalDateTime.now(); 
        String currenttime = dtf.format(now);
		updateSQL = "update RunIDDetails set ENDTIME = '"+currenttime+"' where RunID= "+RUNID;
		System.out.println(updateSQL);
		driver.quit();
		update_sql(updateSQL);
		
		
		
	}
	
	@BeforeMethod
	public void beforeMethod()
	{
		exec_result="Not Executed";
		TCID = null;
		TC_name = null;
		selectSql=null;
		insertSql2 = null;
		
	}
	
	@AfterMethod
	public void afterMethod(ITestResult tr)
	{
		System.out.println("TC ID = "+TCID);
		System.out.println("TC Name = "+TC_name);
		System.out.println("TC Execution result = "+exec_result);
		//TC Status Update in DBpublic void getRunTime(ITestResult tr) {
	    long time = tr.getEndMillis() - tr.getStartMillis();
	    
	    System.out.println("TC Execution time = "+time);
	    insertSql2 = "exec add_testresult "+RUNID+",'"+TCID+"','"+TC_name+"','"+exec_result+"','','','"+time+"','Login',''";
	    System.out.println(insertSql2);
		exec_sp(insertSql2);
	    
	    
	}
	
}
