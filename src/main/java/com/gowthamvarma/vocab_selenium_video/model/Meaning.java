package com.gowthamvarma.vocab_selenium_video.model;

import java.util.List;

public class Meaning {

	private String partsOfSpeech;
	private List<Meaning> subMeaning;

	@Override
	public String toString() {
		return "Meaning [partsOfSpeech=" + partsOfSpeech + ", subMeaning=" + subMeaning + "]";
	}
	
	public String getPartsOfSpeech() {
		return partsOfSpeech;
	}

	public void setPartsOfSpeech(String partsOfSpeech) {
		this.partsOfSpeech = partsOfSpeech;
	}

	public List<Meaning> getSubMeaning() {
		return subMeaning;
	}

	public void setSubMeaning(List<Meaning> subMeaning) {
		this.subMeaning = subMeaning;
	}

}
