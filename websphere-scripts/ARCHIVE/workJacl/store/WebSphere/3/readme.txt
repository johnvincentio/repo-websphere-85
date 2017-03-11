## @(#) 1.4 ws/code/security/scripts/readme.txt, WAS.security, ASV50X 12/17/02 16:07:10 [12/17/02 16:07:42]

Summary:
	These sample wsadmin jacl scripts provide methods to configure WebSphere security settings.  
        There are two modes available:  menu mode and batch mode.
	Menu mode will take user inputs interactively, while the batch mode 
        will read parameters from the property files.  Some basic validation will
        be performed to ensure that the configuration information specified
        is correct in both batch and interactive mode.
        
        These sample scripts can be used to configure most aspects of WebSphere security.
        However, these scripts don't allow one to map users/groups to security roles.
        Mapping users to roles can be done with the AdminApp wsadmin script object.  The
        AdminApp object allows users/groups to be mapped to roles when installing an
        application, or after application installation with an edit option.  Please
        see the InfoCenter for information on the AdminApp scripting object and
        the related MapRolesToUsers and MapRunAsRolesToUsers subtasks.
        
        The reader should have some knowledge and understanding of WebSphere security
        before using these scripts.  Please see the WebSphere InfoCenter for security configuration
        information and requirements, especially the requirements related to the user-id that 
        must be specified when configuring the user registry.  There are certain requirements
        for Windows systems on the privileges of the user must have that are often overlooked.
        The user-id specified for local operating system user registries on the Windows system
        must be an administrator, must have "act as operating system" privileges, and cannot
        be the same name as the hostname of the Windows system. If the Windows machine is a member
        of a Windows domain, then the user-id must also be a administrative user of the domain.
        The Windows "act as operating system" privilege can be set via:
        Start->Settings->Control Panel->Administrative Tools->Local Security Policy, then
        selecting Local Policies->User Rights Assignment->Act as part of the operating system.

How to run the scripts:

        Note that these scripts require the application server to be running. 

	Menu Mode:
	    wsadmin -profile SecConfigProcs.jacl -f SecConfigMenu.jacl
	
	Batch Mode:
		wsadmin -profile SecConfigProcs.jacl -f SecConfigBatch.jacl <property file name>

	With security enabled:
		wsadmin -profile SecConfigProcs.jacl -f SecConfigMenu.jacl -user <your-userid> -password <your-password>
		
	SecConfigProcs.jacl can also be loaded as a profile by adding SecConfigProcs.jacl as one of the
	com.ibm.ws.scripting.profile property values in $WAS_HOME/properties/wsadmin.properties. This will
        remove the need to specify the "-f SecConfigProcs.jacl" when using wsadmin to run the 
        SecConfigBatch.jacl and/or SecConfigMenu.jacl scripts.
	
Examples:
	1)  How to enable security using the interactive SecConfigMenu.jacl script.
		There are two ways to enable security:
			1.  From the main menu, choose option 1, "Enable Security Quick Start Wizard".  
                            This option will guide the user through basic minimum steps necessary 
                            to enable security.  This is recommended for the first time configuration
			    of security.
			2.  From the main menu, choose option 2, "Configure Security", then option 1, 
                            "Configure Global Security", then option 1, "Set Enabled", and set it to true.  
                            Additional setup is required if security has not been setup previously.  
                            
                            Configuring security typically involves the following minimal tasks:
                            a. Configuring the user registry that WAS security system
                               will use.  The user registry can usually be either the local operating
                               system accounts repository, LDAP, or custom.
                            b. Configuring the authentication mechanism that 
                               WAS security system will use.  The current authentication mechanisms are 
                               LTPA and SWAM (see the WebSphere InfoCenter for descriptions).
                            c. Finally, configuring Global Security which is where you enable security,
                               specify the user registry and authentication mechanism (previously
                               configured) that will be used by security.
			    
	2)  How to disable security
		Start wsadmin with -user and -password option (please see "How to 
                run the script" for more details).
		Security can be turned off from main menu using option 3, "Disable Security".
		
Files description:
	 SecConfigProcs.jacl - Contains the low level procedures necessary to configure security.  
                               This should be set as the profile when invoking wsadmin. 
                               Both SecConfigMenu.jacl and SecConfigBatch.jacl call procedures in 
                               this file.
	 
	 SecConfigMenu.jacl - Contains text menu choices for interactive user mode.  The menus
                              more or less follow the same security configuration choices found
                              on the web based WebSphere administrative console.
	 
	 SecConfigBatch.jacl - Contains configuration procedures to read and parse the properties 
                               from a property file.  The properties specify the security configuration
                               values to set in batch mode.
	 
	 SecConfigProps.properties - Sample security property file which contains all available properties.This
	                             includes the cipher suites which are used in SSL config.Security levels 
				     (HIGH, MEDIUM and LOW) correspond to a predefined set of cipher suites.  
				     In case the user wants to override the default settings, then a Menu is
				     provided to choose the cipher suites. A user has to define a custom property
				     named com.ibm.ssl.cipherSuites and the value of this property can be one or 
				     more cipher suites defined in the SecConfigProps.properties file.
				     
				     Parts of SecConfigProps.properties file can be extracted out to created a new
				     properties file.  When properties file is passed in as parameter for SecConfigBatch.jacl,
				     only the attributes specified in the properties file will be updated. 

In Batch Mode, we are configuring Global Security settings similar the Admin Console web based GUI:

The procedures being executed are:

configGlobalSecurity
configSSL
configAdditionalSSLProps
configLTPA
configSSO
configTrustAssociation
configLocalOS
configLDAP
configAdvancedLDAP
configCustomReg
configApplicationLogin
configJ2CAuthData
configCSIia
configCSIoa
configCSIic
configCSIoc
configSASic
configSASoc

1) configGlobalSecurity procedure sets the following attributes:

enabled=false
enforceJava2Security=false
useDomainQualifiedUserNames=true
cacheTimeout=600
issuePermissionWarning=true
activeProtocol=CSI
activeAuthMechanism=SWAM
activeUserRegistry=LocalOS

**************************************************

2) configLTPA procedure sets the following attributes:

LTPAPassword=password
LTPATimeout=30

********************************************************

3) configSSO procedure sets the following attributes:

SSOEnabled=false
SSORequiresSSL=false
SSODomainName=mydomain.com

***************************************************

4) configLocalOS procedure sets the following attributes:

LocalOSServerID=
LocalOSServerpassword=

*******************************************************

5) configLDAP procedure sets the following attributes:

LDAPServerId=
LDAPPassword=
LDAPServerType=IBM_DIRECTORY_SERVER
LDAPHostName=
LDAPPort=389
LDAPBaseDN=
LDAPBindDN=
LDAPBindPassword=
LDAPsearchTimeout=
LDAPreuseConnection=true
LDAPIgnoreCase=true
LDAPsslEnabled=false
LDAPsslConfig=

********************************************************

6)configAdvancedLDAP proedure sets the following attributes

LDAPUserFilter=
LDAPGroupFilter=
LDAPUserIdMap=
LDAPGroupIdMap=
LDAPGroupMemberIdMap=
LDAPCertificateFilter=EXACT_DN
LDAPCertificateMapMode=

********************************************************

7) configCustomReg procedure sets the following attributes:

CUServerId=
CUServerPasword=
CUClassName=

*******************************************************

8) configCSIia procedure sets the following attributes:
 
CSIiaBasicAuth=Supported
CSIiaClientCert=Supported
CSIiaIdentityAssertion=false
CSIiaTrustedServers=
CSIiaisStateful=true

*************************************************************

9) configCSIoa procedure sets the following attributes:

CSIoaBasicAuth=Supported
CSIoaClientCert=Supported
CSIoaIdentityAssertion=false
CSIoaisStateful=true

******************************************************************

10) configCSIic procedure sets the following attributes:

CSIicTransport=SSL-Supported
CSIicSSLSetting=

*********************************************************

11) configCSIoc procedure sets the following attributes:

CSIocTransport=SSL-Supported
CSIocSSLSetting=

********************************************************

12) configSASic procedure sets the following attributes:

SASicSSLSetting=

*********************************************************

13) configSASoc procedure sets the following attributes:

SASocSSLSetting=

*************************************************************

14) configSSL procedure creates a SSL Configuration Repertoire. It sets
    the following attributes:
	
alias=mySSLAlias
keyFileName=${USER_INSTALL_ROOT}/etc/myKeyFile.jks
keyFilePassword=WebAS
keyFileFormat=JKS
trustFileName=${USER_INSTALL_ROOT}/etc/myTrustFile.jks
trustFilePassword=WebAS
trustFileFormat=JKS
clientAuthentication=false
securityLevel=HIGH
enableCryptoHardwareSupport=false

************************************************

15) configJ2CAuthData procedure sets the following fields:

j2cAlias=myJ2CAlias
j2cUserid=myJ2CId
j2cPassword=myJ2Cpwd
j2cDescription=Defining a J2C Alias

*************************************************************

16) configTA procedure sets the following attributes:

enabled=
interceptorClassName=
trustProperties=

The interceptorClassName is the name of the class that implements the Trust Association Interceptor.
For example:to enable the WebSEAL Trust Association Interceptor, set the attributes as follows:

enabled=true
interceptorClassName=com.ibm.ws.security.web.WebSealTrustAssociationInterceptor
trustProperties are as follows:
name=com.ibm.websphere.security.webseal.id
value=iv-user
required=true

name=com.ibm.websphere.security.webseal.hostnames
value=yourhost.yourdomain.ibm.com
required=false

name=com.ibm.websphere.security.webseal.ports
value=443
required=false

name=com.ibm.websphere.security.webseal.loginId
value=youruserid
required=false

name= com.ibm.websphere.security.webseal.mutualSSL
value=true
required=false 

