package com.rz.nlp;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
/**
 * 
 * @authors Marco Zanghi', Tommaso Ruscica
 *
 */
public class Ricerca {

		 
		 HashMap<String, LinkedList<Info>> hashmap_vocabolario=new HashMap<String , LinkedList<Info>>();
    String parola_originale;

		 
		 final static Charset ENCODING = StandardCharsets.UTF_8;
		 
		 
		 public static void main(String[] args) {
			 
			 Ricerca m = new Ricerca();
			 
				m.FileInput();
				while(true){  	
					m.Ricerca();
				}
			 
		 }

		 
		 public void FileInput(){
				try {
					System.out.println("Inizializzazione...");
					FileInputStream fin = new FileInputStream("/Users/TommyR22/../DIZIONARIO.dat"); //TODO PATH FILE DIZIONARIO.dat
					ObjectInputStream ois = new ObjectInputStream(fin);
					hashmap_vocabolario = (HashMap<String, LinkedList<Info>>) ois.readObject();
					ois.close();	
					   
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		 }
		 
		 public void Ricerca(){
			 InputStreamReader reader = new InputStreamReader (System.in);
			    BufferedReader in = new BufferedReader (reader);
			    
				try{
		   	    System.out.println("Inserisci parola: ");
		   	    String x = (in.readLine());
                    
                parola_originale=x;
				ricercaParola(x);

				}catch(Exception e){
					e.printStackTrace();
				}
				
		 }
		 
		 
		 public void ricercaParola(String x){
             boolean b=true;
             
             if(hashmap_vocabolario.containsKey(x)){
                 
                 for(Info info : hashmap_vocabolario.get(x)){
//                	 if(x.equalsIgnoreCase(parola_originale)){
//                		  System.out.println("PAROLA TROVATA, RADICE: "+x+" del vocabolo: "+ info.getVocabolo() +" PAROLA CERCATA:"+parola_originale+" TIPO: "+info.getTipo().name());
//                	 }
                     //System.out.println(info.getTipo().name());
                     List<String> listaFlessioni=info.getTipo().getFlessioni();
                     
                     for(String l : listaFlessioni){
                         String s=x.concat(l);
                         //System.out.println(s);
                         if(s.equalsIgnoreCase(parola_originale)){
                             System.out.println("PAROLA TROVATA, RADICE: "+x+" del vocabolo: "+ info.getVocabolo() +" PAROLA CERCATA:"+parola_originale+" TIPO: "+info.getTipo().name());
                         }
                     }
                     if(info.getFlessioni()!=null){
	                     for(String l : info.getFlessioni()){
	                         String s=x.concat(l);
	                         //System.out.println(s);
	                         if(s.equalsIgnoreCase(parola_originale)){
	                             System.out.println("PAROLA TROVATA, RADICE: "+x+" del vocabolo: "+ info.getVocabolo() +" PAROLA CERCATA:"+parola_originale+" TIPO: "+info.getTipo().name());
	                         }
	                     }
                     }
                 }
                
             }
			            
				if(b){
						String delete_char=deleteLastChar(x);	
						if(delete_char==null){
							return;
						}
		        		System.out.println("Parola non trovata,elimino l'ultimo carattere e riprovo.. ["+delete_char+"]");
		        		ricercaParola(delete_char);
					}
				
			}

			public String deleteLastChar(String str) {
			    if (str.length() > 0) {
			      str = str.substring(0, str.length()-1);
			    }
			    else {
			    	System.out.println("Parola non trovata!");
			    	return null;
			    }
			    return str;
			}
		 

}
