package com.gowthamvarma.vocab_selenium_video.model;

import java.util.List;

public class Translation {

	private String translation;
	private List<String> reverse;

	@Override
	public String toString() {
		return "Translation [translation=" + translation + ", reverse=" + reverse + "]";
	}

	public String getTranslation() {
		return translation;
	}

	public void setTranslation(String translation) {
		this.translation = translation;
	}

	public List<String> getReverse() {
		return reverse;
	}

	public void setReverse(List<String> reverse) {
		this.reverse = reverse;
	}

}
