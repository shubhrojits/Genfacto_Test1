--Same thing can be done using


DECLARE @portNumber NVARCHAR(10);
EXEC xp_instance_regread 
     @rootkey = 'HKEY_LOCAL_MACHINE', 
     @key = 'Software\Microsoft\Microsoft SQL Server\MSSQLServer\SuperSocketNetLib\Tcp\IpAll', 
     @value_name = 'TcpDynamicPorts', 
     @value = @portNumber OUTPUT;
SELECT [Port Number] = @portNumber;
GO