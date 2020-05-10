package com.gowthamvarma.vocab_selenium_video.model;

import java.util.List;

public class Word {

	private String original;
	private String pronounciation;
	private String transaltion;
	private List<Translation> transaltions;
	private List<Meaning> meanings;
	private List<String> usages;

	@Override
	public String toString() {
		return "Word [original=" + original + ", pronounciation=" + pronounciation + ", transaltion=" + transaltion
				+ ", transaltions=" + transaltions + ", meanings=" + meanings + ", usages=" + usages + "]";
	}

	public String getOriginal() {
		return original;
	}

	public void setOriginal(String original) {
		this.original = original;
	}

	public String getPronounciation() {
		return pronounciation;
	}

	public void setPronounciation(String pronounciation) {
		this.pronounciation = pronounciation;
	}

	public String getTransaltion() {
		return transaltion;
	}

	public void setTransaltion(String transaltion) {
		this.transaltion = transaltion;
	}

	public List<Translation> getTransaltions() {
		return transaltions;
	}

	public void setTransaltions(List<Translation> transaltions) {
		this.transaltions = transaltions;
	}

	public List<Meaning> getMeanings() {
		return meanings;
	}

	public void setMeanings(List<Meaning> meanings) {
		this.meanings = meanings;
	}

	public List<String> getUsages() {
		return usages;
	}

	public void setUsages(List<String> usages) {
		this.usages = usages;
	}
}
