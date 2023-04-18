/*
 The integration developer needs to create the method processData 
 This method takes Message object of package com.sap.gateway.ip.core.customdev.util 
which includes helper methods useful for the content developer:
The methods available are:
    public java.lang.Object getBody()
	public void setBody(java.lang.Object exchangeBody)
    public java.util.Map<java.lang.String,java.lang.Object> getHeaders()
    public void setHeaders(java.util.Map<java.lang.String,java.lang.Object> exchangeHeaders)
    public void setHeader(java.lang.String name, java.lang.Object value)
    public java.util.Map<java.lang.String,java.lang.Object> getProperties()
    public void setProperties(java.util.Map<java.lang.String,java.lang.Object> exchangeProperties) 
    public void setProperty(java.lang.String name, java.lang.Object value)
    public java.util.List<com.sap.gateway.ip.core.customdev.util.SoapHeader> getSoapHeaders()
    public void setSoapHeaders(java.util.List<com.sap.gateway.ip.core.customdev.util.SoapHeader> soapHeaders) 
       public void clearSoapHeaders() Test Raymond Adriaan from vs url-encoded test II
 */
import com.sap.gateway.ip.core.customdev.util.Message;
import java.util.HashMap;
def Message processData(Message message) {
   
       
       def map = message.getHeaders();
       
       String operationname = map.get("CamelHttpPath");
       
       if (operationname == "Inventory") {
            message.setProperty("operationName", "getCertificatesFromKeyStore");
       }
       else if (operationname ==  "removeCertificateFromKeyStore") {
            message.setProperty("operationName", "removeFromKeyStore");
       }
       else if (operationname ==  "upsertPFX") {
            message.setProperty("operationName", "upsertPFX");
       }
       else if (operationname ==  "upsertCert") {
            message.setProperty("operationName", "upsertCert");
       }
       else {
            //no valid operation
       }
       
       
       //keystore view
       String ksView = map.get("ksView");
       if ((ksView == "") || (ksView == null)) {
         ksView = "";
         message.setProperty("ksView", ksView);
       }
       else {
         message.setProperty("ksView", ksView);
       }
       
       
       //password pfx
       String pwd = map.get("pwd");
       if ((pwd == "") || (pwd == null)) {
         pwd = "none";
         message.setProperty("pwd", pwd);
       }
       else {
         message.setProperty("pwd", pwd);
       }
      
       
       //alias
       String alias = map.get("alias");
       if ((alias == "") || (alias == null)) {
         alias = "";
         message.setProperty("alias", alias);
       }
       else {
         message.setProperty("alias", alias);
       } 
       
       
       //extract de base64 encoded pfx deel in de body van het bericht
        def pfx = message.getBody(java.lang.String) as String; 
        
        if ((pfx == "") || (pfx == null)) {
            message.setProperty("emptyBody", "true");
            
        }
        else {
            message.setProperty("emptyBody", "false");
        }
        
        
        
      
       return message;
}
