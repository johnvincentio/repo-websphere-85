 
 Starting wsadmin for Jacl Capstone work
 
WASX7209I: Connected to process "server1" on node alphaNode11 using SOAP connector;  The type of process is: UnManagedProcess
INFO: mdb.jacl; Starting
INFO: Loading Properties file mdb.properties
INFO: Loaded Properties file
INFO: ---------------------------------------------------------------
INFO:  
INFO:  Scope:                                                        
INFO:     cell                alphaNode11Cell                            
INFO:     node                alphaNode11                            
INFO:     server              server1                          
INFO: ---------------------------------------------------------------
INFO: Handle JMS

---------------------------------------------------------------
              Create SI bus                                    
---------------------------------------------------------------
 Scope:                                                        
    node                alphaNode11                              
    server              server1                            
 SI bus:                                                       
    name                SimpleMessageBus                             
    description         Messaging bus for SimpleMessage                             
    secure              false (true or false)           
---------------------------------------------------------------

INFO: Create a new SI bus named SimpleMessageBus.

---------------------------------------------------------------
              Add SI bus member                                
---------------------------------------------------------------
 Scope:                                                        
    node                alphaNode11                              
    server              server1                            
 SI bus:                                                       
    name                SimpleMessageBus                                 
---------------------------------------------------------------


---------------------------------------------------------------
              Enable service                                   
---------------------------------------------------------------
 Scope:                                                        
    node                alphaNode11                              
    server              server1                            
 Service:                                                      
    name                SIBService                           
---------------------------------------------------------------

INFO: The SIBService service is already enabled.
INFO: Handle SimpleMessage JMS

---------------------------------------------------------------
              Create SIB JMS connection factory                
---------------------------------------------------------------
 Scope:                                                        
    node                alphaNode11                              
 Connection factory:                                           
    name                SimpleMessageConnectionFactory                             
    JNDI                jms/com/idc/mdb/SimpleMessageConnectionFactory                             
    description         SimpleMessageConnectionFactory                             
    type                queue                             
    auth alias                                       
 SI bus:                                                       
    name                SimpleMessageBus                             
---------------------------------------------------------------


---------------------------------------------------------------
              Create SIB JMS queue                             
---------------------------------------------------------------
 Scope:                                                        
    node                alphaNode11                              
 SIB JMS queue:                                                
    name                SimpleMessageQueue                              
    JNDI                jms/com/idc/mdb/SimpleMessageQueue                              
    description         SimpleMessageQueue                              
    SIB queue           SIBSimpleMessageQueue                              
---------------------------------------------------------------


---------------------------------------------------------------
              Create SIB queue                                 
---------------------------------------------------------------
 Scope:                                                        
    node                alphaNode11                              
    server              server1                            
 SIB queue:                                                    
    name                SIBSimpleMessageQueue                              
 SI bus:                                                       
    name                SimpleMessageBus                             
---------------------------------------------------------------


---------------------------------------------------------------
              Create SIB JMS activation spec                   
---------------------------------------------------------------
 Scope:                                                        
    node                alphaNode11                              
 Activation spec:                                              
    name                SimpleMessageActivationSpec                    
    JNDI                eis/SimpleMessageActivationSpec                
    destination JNDI    jms/com/idc/mdb/SimpleMessageQueue                              
    destination type    javax.jms.Queue                       
    auth alias                                       
 SI bus:                                                       
    name                SimpleMessageBus                             
---------------------------------------------------------------


---------------------------------------------------------------
              Save                                             
---------------------------------------------------------------

after save
