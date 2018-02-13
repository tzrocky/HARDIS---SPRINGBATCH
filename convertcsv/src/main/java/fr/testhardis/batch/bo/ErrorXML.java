package fr.testhardis.batch.bo;
import javax.xml.bind.annotation.XmlAttribute; 
import javax.xml.bind.annotation.XmlElement; 
import javax.xml.bind.annotation.XmlRootElement;  

@XmlRootElement(name = "error") 
public class ErrorXML {  
   private String line; 
   private String message;
   private String ligneErronee;  
 
   @XmlAttribute(name = "line") 
   public String getLine() { 
      return line; 
   }  
 
   public void setLine(String pLine) { 
      this.line = pLine; 
   }  
 
   @XmlAttribute(name = "message") 
   public String getMessage() { 
      return message; 
   }  
   public void setMessage(String pMessage) { 
      this.message = pMessage; 
   }  
      
   @XmlElement(name = "ligneErronee") 
   public String getLigneErronee() { 
      return ligneErronee; 
   }  
 
   public void setLigneErronee(String pLigneErronee) { 
      this.ligneErronee = pLigneErronee; 
   }  
  
}  