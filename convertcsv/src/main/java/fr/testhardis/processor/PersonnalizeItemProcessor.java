package fr.testhardis.processor;

import org.springframework.batch.item.ItemProcessor;

import fr.testhardis.batch.bo.DocumentXML; 

public class PersonnalizeItemProcessor implements ItemProcessor<DocumentXML, DocumentXML> {  
   
   @Override 
   public DocumentXML process(DocumentXML item) throws Exception {  
      System.out.println("Processing..." + item); 
      return item; 
   } 
} 