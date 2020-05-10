package com.gowthamvarma.vocab_selenium_video.model;

import java.util.List;

public class SubMeaning {
	private String meaning;
	private String usage;
	private List<String> synonyms;

	@Override
	public String toString() {
		return "SubMeaning [meaning=" + meaning + ", usage=" + usage + ", synonyms=" + synonyms + "]";
	}

	public String getMeaning() {
		return meaning;
	}

	public void setMeaning(String meaning) {
		this.meaning = meaning;
	}

	public String getUsage() {
		return usage;
	}

	public void setUsage(String usage) {
		this.usage = usage;
	}

	public List<String> getSynonyms() {
		return synonyms;
	}

	public void setSynonyms(List<String> synonyms) {
		this.synonyms = synonyms;
	}
}
