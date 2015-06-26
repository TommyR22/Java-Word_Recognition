package com.rz.nlp;
import java.io.Serializable;

/**
 * 
 * @authors Marco Zanghi', Tommaso Ruscica
 *
 */
public class Info implements Serializable{

	private String vocabolo;
	private Grammatica tipo;
	private String radice;
	private String[] flessioni;
	
	
	
	public String[] getFlessioni() {
		return flessioni;
	}

	public void setFlessioni(String[] flessioni) {
		this.flessioni = flessioni;
	}

	public Info(String vocabolo, Grammatica tipo, String radice) {
		this.vocabolo = vocabolo;
		this.tipo = tipo;
		this.radice = radice;
	}
	
	public String getVocabolo() {
		return vocabolo;
	}
	public void setVocabolo(String vocabolo) {
		this.vocabolo = vocabolo;
	}
	public Grammatica getTipo() {
		return tipo;
	}
	public void setTipo(Grammatica tipo) {
		this.tipo = tipo;
	}
	public String getRadice() {
		return radice;
	}
	public void setRadice(String radice) {
		this.radice = radice;
	}
	
	
	
}
