package com.rz.nlp;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectOutputStream;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Properties;
import java.util.Scanner;
import java.util.StringTokenizer;

import javax.swing.event.HyperlinkListener;
/**
 * 
 * @authors Marco Zanghi', Tommaso Ruscica
 *
 */

public class ParserDizionario {
	
	 final static Charset ENCODING = StandardCharsets.UTF_8;
	
	 public ParserDizionario() {}
	 
	 /**
	  * Dato un vocabolo e la sua definizione estraggo la tipologia di parola ( es. sostantivo, verbo ecc)
	  * */
	 public static Grammatica tipoVocabolo(String word,String definizione){
		 StringTokenizer tokenizer = new StringTokenizer(definizione);
		 while(tokenizer.hasMoreTokens()){
			 switch (tokenizer.nextToken()) {
			 	case "v.":
					 String sub="";
					 if(word.length()>3){
						 sub = word.substring(word.length()-3, word.length());
					 }
					 switch (sub){
					 	case "are":
					 		return Grammatica.VERBO_1;
					 	case "ere":
					 		return Grammatica.VERBO_2;
					 	case "rre":
					 		return Grammatica.VERBO_2_Plus;
					 	case "ire":
					 		return Grammatica.VERBO_3;
					 	default: return null;
					 }
			 	case "s.f.":
					if(word.endsWith("e")){
						return Grammatica.SOSTANTIVO_MASCHILE_FEMMINILE;
					}else{
						return Grammatica.SOSTANTIVO_FEMMINILE;
					}
					
				case "s.m.":
					if(word.endsWith("e")){
						return Grammatica.SOSTANTIVO_MASCHILE_FEMMINILE;
					}else{
						return Grammatica.SOSTANTIVO_MASCHILE;
					}
				case "prep.":
					return Grammatica.PREPOSIZIONE;
				case "agg.":
					return Grammatica.AGGETTIVO;
				case "loc.":
					if (definizione.contains("avv.")){
						return Grammatica.LOCUZIONE_AVVERBIALE;
					}else{
						return Grammatica.LOCUZIONE;
					}
				default:
					break;
				}
		 	}
		 return null;
	 }
	 
	 /**
	  * Dato un vocabolo e la tipologia di parola estraggo la possibile radice
	  * */
	 public static String estraiRadice(String word,Grammatica type,String definizione){
		 StringTokenizer tokenizer = new StringTokenizer(definizione);
		 switch (type) {
		 
		 	case SOSTANTIVO_MASCHILE_FEMMINILE:
				if(word.length()==1){
						return word;
				}else{
					return word.substring(0, word.length()-1);
				}
			case SOSTANTIVO_FEMMINILE:
				if(word.length()==1){
						return word;
				}else{
					return word.substring(0, word.length()-1);
				}
			case SOSTANTIVO_MASCHILE:
				if(word.length()==1){
						return word;
				}
				else{
						return word.substring(0, word.length()-1);
				
				}
			case PREPOSIZIONE:
				return word;
			case SOSTANTIVO_FEMMINILE_INVARIANTE:
				return word;
			case SOSTANTIVO_MASCHILE_INVARIANTE:
				return word;
			case AGGETTIVO:
				if(word.length()==1){
					return word;
				}else{
					return word.substring(0, word.length()-1);
				}
			case LOCUZIONE_AVVERBIALE:
				return word;
			case VERBO_1:
				return word.substring(0, word.length()-3);
			case VERBO_2:
				return word.substring(0, word.length()-3);
			case VERBO_2_Plus:
				return word.substring(0, word.length()-3);
			case VERBO_3:
				return word.substring(0, word.length()-3);
			default:
				break;
			}
		 return null;
	 }
	 
	 /**
	  * Dato un vocabolo e la tipologia di parola estraggo la possibile radice
	  * */
	 public static void getAltreFlessioni(String word,Grammatica type,String definizione,HashMap<String,LinkedList<Info>> voc){
		 StringTokenizer tokenizer = new StringTokenizer(definizione);
		 switch (type) {
		 
		 	case SOSTANTIVO_MASCHILE_FEMMINILE:
				if(word.length()==1){
					break;
				}else{
					break;
				}
			case SOSTANTIVO_FEMMINILE:
				if(word.length()==1){
					break;
				}else{
					break;
				}
			case SOSTANTIVO_MASCHILE:
				if(word.length()==1){
					
				}
				else{
					if(definizione.contains("[f.")){
						
						while(definizione.lastIndexOf("-")>definizione.lastIndexOf("]")){
							definizione = definizione.substring(0,definizione.lastIndexOf("-"));
						}
						String des =definizione.substring(definizione.lastIndexOf("-")+1,definizione.lastIndexOf("]"));
						if(des.indexOf(" ")!=-1){
							return;
						}
						
						String finale;
						String radixfinale;
						if(des.length()==1){
							String w = word.substring(0,word.length()-1);
							finale = w.concat(des);
							radixfinale = finale.substring(0,finale.length()-1);
						}else{
							System.out.println(des);
							char s = des.charAt(0);
							if(word.lastIndexOf(s)==-1){
								return;
							}
							String w = word.substring(0,word.lastIndexOf(s));
							finale = w.concat(des);
							radixfinale = finale.substring(0,finale.length()-1);
						}
						
						
						Info info;
						if(finale.endsWith("a")){
							info = new Info(finale, Grammatica.SOSTANTIVO_FEMMINILE, radixfinale);
						}else{
							info = new Info(finale, Grammatica.SOSTANTIVO_MASCHILE_FEMMINILE, radixfinale);
						}
						if(voc.containsKey(radixfinale)){
							//se già c'è questa radice aggiungo le altre possibili info
							LinkedList<Info> templist = voc.get(radixfinale);
							templist.add(info);
							voc.put(radixfinale,templist);
						}else{
							//se non esiste la radice faccio un inserimento semplice nell'hashmap
							LinkedList<Info> templist = new LinkedList<Info>();
							templist.add(info);
							voc.put(radixfinale,templist);	
						}
						
					}		
				}
				
				break;
			case PREPOSIZIONE:
				break;
			case SOSTANTIVO_FEMMINILE_INVARIANTE:
				break;
			case SOSTANTIVO_MASCHILE_INVARIANTE:
				break;
			case AGGETTIVO:
				if(word.length()==1){
					break;
				}else{
					break;
				}
			case LOCUZIONE_AVVERBIALE:
				break;
			case VERBO_1:
				break;
			case VERBO_2:
				break;
			case VERBO_2_Plus:
				break;
			case VERBO_3:
				break;
			default:
				break;
			}
		 
	 }
	 
	 
	 
	 public static void main(String[] args) {
		 
		 HashMap<String, LinkedList<Info>> vocabolario = new HashMap<String,LinkedList<Info>>();
		 
		 try {
			 
			Scanner scanner = new Scanner(new File("C:\\Users\\Marco\\WorkSpace\\workspaceandroid\\ProgettoLinguaggi\\src\\files\\vocabolario.txt"),ENCODING.name());
			scanner.useDelimiter("\n");
			
			
			while(scanner.hasNext()) {
				
				String entry = scanner.next();
				System.out.println(entry);
						
				String[] result = entry.split("\t");
				String vocabolo = result[0];

				//controllo che ogni entry sia composto da vocabolo e da definizione
				if(result.length>1){
					
					//estraggo l'informazione del tipo di vocabolo
					Grammatica type = tipoVocabolo(result[0],result[1]);
					//controllo che l'estrazione ha avuto successo
					if(type==null){
						// scarto tutte quelle parole che non ho considerato nell'analisi
						// o per cui l'analisi non ha avuto successo
					}else{
					
						String radice = estraiRadice(vocabolo, type,result[1]);
						System.out.println(" "+radice+" "+type+" "+vocabolo+" ");
					
						Info temp = new Info(vocabolo, type, radice);
						getAltreFlessioni(vocabolo, type,result[1],vocabolario);
						if(vocabolario.containsKey(radice)){
							//se già c'è questa radice aggiungo le altre possibili info
							LinkedList<Info> templist = vocabolario.get(radice);
							templist.add(temp);
							vocabolario.put(radice,templist);
						}else{
							//se non esiste la radice faccio un inserimento semplice nell'hashmap
							LinkedList<Info> templist = new LinkedList<Info>();
							templist.add(temp);
							vocabolario.put(radice,templist);	
						}
					}
				}
				
			}
			//salvo su file l'intero nuovo dizionario
			FileOutputStream fout = new FileOutputStream("C:\\Users\\Marco\\WorkSpace\\workspaceandroid\\ProgettoLinguaggi\\src\\files\\DIZIONARIO.dat");
			ObjectOutputStream oos;
			try {
				oos = new ObjectOutputStream(fout);
				oos.writeObject(vocabolario);
				oos.flush();
				oos.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
			
			// InputStreamReader reader = new InputStreamReader (System.in);
		 //    BufferedReader in = new BufferedReader (reader);
		 //    while(true){
		 //    	try{
		 //   	    System.out.println("Inserisci parola: ");
		 //   	    String x = (in.readLine());
	             
		 //   	 for(Info info : vocabolario.get(x)){
			// 		System.out.println(info.getTipo().name());
			// 		if(info.getFlessioni()!=null){
			// 			System.out.println(" flessioni altre:  "+ info.getFlessioni()[0]+" ," + info.getFlessioni()[1] );
			// 		}
		 //   	 	}
			// 	}catch(Exception e){
			// 		e.printStackTrace();
			// 	}
		 //    }
			
				
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		 
	 }
	 
}
