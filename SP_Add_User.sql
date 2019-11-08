USE [Git_Accounting]
GO
/****** Object:  StoredProcedure [dbo].[Customers_Add]    Script Date: 23-10-2019 09:45:29 ******/ mod --1
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
/****** Object:  StoredProcedure [dbo].[Customers_Add]    Script Date: May 30 2019  2:12PM ******/

ALTER PROCEDURE [dbo].[Customers_Add]
 @Params xml
AS
--usage: [dbo].[Customers_Add] '<row></row>'
BEGIN

 DECLARE	@AuditUserId int
			,@AuditTimeStamp datetime
			,@AuditId uniqueidentifier
			,@CompanyId int
			,@BranchId int
			,@Guid uniqueidentifier
			,@AccountGroup int
			,@AccountNumber nvarchar(250)
			,@AddressStateId int
			,@AssociatedCompany nvarchar(250)
			,@BankName nvarchar(250)
			,@Blocked bit
			,@Catagory int
			,@FreightTerm int
			,@IFSE nvarchar(250)
			,@OwnershipTransfer int
			,@PaymentTerm int
			,@POD bit
			,@QMSystem int
			,@SupplyAgent nvarchar(250)
			,@Swift nvarchar(250)
			,@TrainStation nvarchar(250)
			,@CustomerCode nvarchar(250)
			,@CustomerName nvarchar(250)
			,@CustomerPhone nvarchar(250)
			,@CustomerReference nvarchar(250)
			,@ChartofAccountId int
			,@Qry nvarchar(250)
			,@CustomerType int
			,@CustomerId int


 SELECT @AuditUserId = @Params.value('(/row/@AuditUserId)[1]', 'int')
		,@AuditTimeStamp = @Params.value('(/row/@AuditTimeStamp)[1]', 'datetime')
		,@AuditId = @Params.value('(/row/@AuditId)[1]', 'uniqueidentifier')
		,@CompanyId = @Params.value('(/row/@CompanyId)[1]', 'int')
		,@BranchId = @Params.value('(/row/@BranchId)[1]', 'int')
		,@Guid = @Params.value('(/row/@Guid)[1]', 'uniqueidentifier')
		,@AccountGroup = @Params.value('(/row/@AccountGroup)[1]', 'int')
		,@AccountNumber = @Params.value('(/row/@AccountNumber)[1]', 'nvarchar(250)')
		,@AddressStateId = @Params.value('(/row/@AddressStateId)[1]', 'int')
		,@AssociatedCompany = @Params.value('(/row/@AssociatedCompany)[1]', 'nvarchar(250)')
		,@BankName = @Params.value('(/row/@BankName)[1]', 'nvarchar(250)')
		,@Blocked = @Params.value('(/row/@Blocked)[1]', 'bit')
		,@Catagory = @Params.value('(/row/@Catagory)[1]', 'int')
		,@FreightTerm = @Params.value('(/row/@FreightTerm)[1]', 'int')
		,@IFSE = @Params.value('(/row/@IFSE)[1]', 'nvarchar(250)')
		,@OwnershipTransfer = @Params.value('(/row/@OwnershipTransfer)[1]', 'int')
		,@PaymentTerm = @Params.value('(/row/@PaymentTerm)[1]', 'int')
		,@POD = @Params.value('(/row/@POD)[1]', 'bit')
		,@QMSystem = @Params.value('(/row/@QMSystem)[1]', 'int')
		,@SupplyAgent = @Params.value('(/row/@SupplyAgent)[1]', 'nvarchar(250)')
		,@Swift = @Params.value('(/row/@Swift)[1]', 'nvarchar(250)')
		,@TrainStation = @Params.value('(/row/@TrainStation)[1]', 'nvarchar(250)')
		,@CustomerCode = @Params.value('(/row/@CustomerCode)[1]', 'nvarchar(250)')
		,@CustomerName = @Params.value('(/row/@CustomerName)[1]', 'nvarchar(250)')
		,@CustomerPhone = @Params.value('(/row/@CustomerPhone)[1]', 'nvarchar(250)')
		,@CustomerReference = @Params.value('(/row/@CustomerReference)[1]', 'nvarchar(250)')
		,@CustomerType = @Params.value('(/row/@CustomerType)[1]', 'int')
   
	DECLARE @ErrorCode int
	BEGIN TRAN
 
 
	SELECT	@CustomerCode = NextSerialNumber
	FROM	[dbo].[NumberSystems] [NumberSystems] 
				JOIN [dbo].[GenericModels] [GenericModels]
				ON [NumberSystems].ModelId = [GenericModels].ModelId
	WHERE	ModelType = @CustomerType
			AND ModelName = 'Customer'

	SET @Qry = '<row ModelName="Customer" ModelType="' + CAST(@CustomerType AS nvarchar(100)) + '" FetchNext="1"></row>'
	exec [dbo].[NumberSystems_Update] @Qry
 
	INSERT INTO [dbo].[ChartOfAccounts]
	(
		[AuditUserId]
		,[AuditTimeStamp]
		,[AuditId]
		,[CompanyId]
		,[BranchId]
		,[Guid]
		,[AccountGroup]
		,[AccountName]
		,[AccountNumber]
	)
	SELECT	@AuditUserId
			,@AuditTimeStamp
			,@AuditId
			,@CompanyId
			,@BranchId
			,@Guid
			,(
				SELECT ChartOfAccountGroupId
				FROM  dbo.[ChartOfAccountGroups] [ChartOfAccountGroups]
					JOIN [dbo].[Lookups] [Lookups]
						ON ChartOfAccountGroups.[GroupType] = Lookups.LookupId
					WHERE LookupType = 'COAGroupType'
					AND LookupValue = 'Sundry debtor'
			)
			,@CustomerName
			,@CustomerCode
   
	SELECT @ErrorCode = @@ERROR
	IF (@ErrorCode <> 0) GOTO PROBLEM

     SET @ChartofAccountId = SCOPE_IDENTITY()

	 INSERT INTO [dbo].[Customers]
	 (
		[AuditUserId]
		,[AuditTimeStamp]
		,[AuditId]
		,[CompanyId]
		,[BranchId]
		,[Guid]
		,[AccountGroup]
		,[AccountNumber]
		,[AddressStateId]
		,[AssociatedCompany]
		,[BankName]
		,[Blocked]
		,[Catagory]
		,[FreightTerm]
		,[IFSE]
		,[OwnershipTransfer]
		,[PaymentTerm]
		,[POD]
		,[QMSystem]
		,[SupplyAgent]
		,[Swift]
		,[TrainStation]
		,[CustomerCode]
		,[CustomerName]
		,[CustomerPhone]
		,[CustomerReference]
		,[CustomerType]
		,ChartofAccountId
	 )
	 VALUES
	 (
		@AuditUserId
		,@AuditTimeStamp
		,@AuditId
		,@CompanyId
		,@BranchId
		,@Guid
		,@AccountGroup
		,@AccountNumber
		,@AddressStateId
		,@AssociatedCompany
		,@BankName
		,@Blocked
		,@Catagory
		,@FreightTerm
		,@IFSE
		,@OwnershipTransfer
		,@PaymentTerm
		,@POD
		,@QMSystem
		,@SupplyAgent
		,@Swift
		,@TrainStation
		,@CustomerCode
		,@CustomerName
		,@CustomerPhone
		,@CustomerReference
		,@ChartofAccountId
		,@CustomerType

	 )
  
	SELECT @ErrorCode = @@ERROR
    IF (@ErrorCode <> 0) GOTO PROBLEM
	SET @CustomerId = SCOPE_IDENTITY()

 COMMIT TRAN
 RETURN 0

 PROBLEM: IF (@ErrorCode <> 0) ROLLBACK TRAN
END