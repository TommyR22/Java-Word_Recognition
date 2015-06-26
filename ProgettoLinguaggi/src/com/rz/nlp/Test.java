package com.rz.nlp;
/**
 * 
 * @authors Marco Zanghi', Tommaso Ruscica
 *
 */
public class Test {
	
	public static void main(String[] args) {
		String st ="barone	barone (1) s.m.  [f. -essa]\n 1 nel medioevo, grande feudatario, dotato spesso di larga autonomia\n 2 titolo nobiliare, che nella gerarchia araldica segue quello di visconte. dim. baroncino, baronetto \n 3 chi detiene un grande potere, spec. economico: i baroni dell'industria, della finanza  | (estens.) chi usa in ambito professionale il proprio ruolo e la propria autorità a fini di potere o di tornaconto personale: i baroni della medicina, dell'università \n 4 (ant.) appellativo d'onore dato ai santi: mira, mira: ecco il barone / per cui là giù si vicita galizia, così dante (par.  xxv, 17-18) definisce san giacomo, venerato nel santuario di santiago de compostela, in galizia\n 5 (ant.) gioco dei dadi.";
		String []result  = st.split("\t");
		String definizione = result[1];
		String word = result[0];
				if(definizione.contains("[f.")){
					while(definizione.lastIndexOf("-")>definizione.lastIndexOf("]")){
						definizione = definizione.substring(0,definizione.lastIndexOf("-"));
					}
					//definizione
					String des = definizione.substring(definizione.lastIndexOf("-")+1,definizione.lastIndexOf("]"));
					System.out.println(des);
					char s = des.charAt(0);
					
					String w = word.substring(0,word.lastIndexOf(s));
					System.out.println(w);
					String finale = w.concat(des);
					System.out.println(finale);
				}
	}
}
