package environment;

public class DbScripts {
/*	
	CREATE TABLE RunIDDetails(
			RunID  INT IDENTITY(1,1) PRIMARY KEY,
			STARTTIME DATETIME,
			ENDTIME DATETIME,
			OS VARCHAR(30),
			SOLUTION VARCHAR(100),
			BROWSER VARCHAR(100)
			)

		CREATE TABLE TESTRESULT(
			RUNID INT FOREIGN KEY REFERENCES RunIDDetails(RUNID),
			SRLNO INT IDENTITY(1,1),
			TCID VARCHAR(100),
			TCNAME VARCHAR(255),
			RESULT VARCHAR(50),
			ERRORDESCRIPTION NVARCHAR(MAX),
			EXECUTEDON DATETIME,
			RUNNINGTIME VARCHAR(50),
			BATCH VARCHAR(100),
			SCREENSHOT NVARCHAR(MAX)
			)
			
			Get Port number of SQL Server for JDBC Connection URL
			Help: https://www.sqlshack.com/overview-of-sql-server-ports/
			
			DECLARE @portNumber NVARCHAR(10);
			EXEC xp_instance_regread 
			     @rootkey = 'HKEY_LOCAL_MACHINE', 
			     @key = 'Software\Microsoft\Microsoft SQL Server\MSSQLServer\SuperSocketNetLib\Tcp\IpAll', 
			     @value_name = 'TcpDynamicPorts', 
			     @value = @portNumber OUTPUT;
			SELECT [Port Number] = @portNumber;
			GO
*/
}
