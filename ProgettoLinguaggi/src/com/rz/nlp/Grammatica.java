package com.rz.nlp;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 
 * @authors Marco Zanghi', Tommaso Ruscica
 *
 */


public enum Grammatica{
	
	SOSTANTIVO_MASCHILE("o","i"),
	SOSTANTIVO_MASCHILE_INVARIANTE(),
	SOSTANTIVO_MASCHILE_FEMMINILE("e","i"),
	SOSTANTIVO_FEMMINILE("a","e"),
	SOSTANTIVO_FEMMINILE_INVARIANTE(),
	VERBO_1("are","o","i","a","iamo","ate","ano","avo","avi","ava","avamo","avate","avano","ai","asti","�","ammo","aste","arono","er�","erai","er�","eremo","erete","eranno","erei","eresti","erebbe","eremmo","ereste","erebbero","i","iamo","iate","ino","assi","asse","assimo","aste","assero","a","i","iamo","ate","ino","ando","ante","ato"),
	VERBO_2("ere","o","i","e","iamo","ete","ono","evo","evi","eva","evamo","evate","evano","ei","etti","esti","�","ette","emmo","este","erono","ettero","er�","erai","er�","eremo","erete","eranno","r�","rai","r�","remo","rete","ranno","erei","eresti","erebbe","eremmo","ereste","erebbero","a","iamo","iate","ano","essi","esse","essimo","este","essero","i","a","iamo","ete","ano","endo","ente","uto"),
	VERBO_2_Plus("rre"),
	VERBO_3("ire","o","i","e","iamo","ite","ono","isco","isci","isce","iscono","ivo","ivi","iva","ivamo","ivate","ivano","ii","isti","i","immo","iste","irono","ir�","irai","ir�","iremo","irete","iranno","irei","iresti","irebbe","iremmo","ireste","irebbero","a","isca","iamo","iate","ano","iscano","issi","isse","issimo","iste","issero","i","isci","a","isca","iamo","ite","ano","iscano","endo","ente","iente","ito"),
	LOCUZIONE(),
	LOCUZIONE_AVVERBIALE(),
	AGGETTIVO("o","i","a","e"),
	PREPOSIZIONE(),
	PRONOME();
	
	private final List<String> flessioni;
	
	public List<String> getFlessioni(){
        return new ArrayList<String>(flessioni);
    }
	
	private Grammatica(String... flessioni) {
	        this.flessioni = Arrays.asList(flessioni);
	       
	    }
}
