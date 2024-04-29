
package spam_detector;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SpamDetector {

	private List<String>	spamTerms;
	private double			spamThreshold;


	public SpamDetector() {
		this.spamTerms = List.of("sex", "viagra", "cialis", "one million", "you’ve won", "nigeria");
		this.spamThreshold = 0.1;
	}

	public SpamDetector(final String spamTerms, final double spamThreshold) {

		String[] spamArray = spamTerms.split(",\\s*");
		List<String> spamTermsList = Arrays.asList(spamArray);
		this.spamTerms = spamTermsList;
		this.spamThreshold = spamThreshold;
	}

	public boolean isSpam(String text) {
		text = text.trim().toLowerCase().replaceAll("\\s+", " ") + " ";
		System.out.println(text);
		String[] words = text.split("\\s+");
		int totalWords = words.length;
		int spamCount = 0;
		List<String> bannedWords = SpamDetector.removeContainedTerms(this.spamTerms);
		for (String term : bannedWords)
			if (text.contains(term.toLowerCase())) {
				int termCount = text.split(term.toLowerCase(), -1).length - 1;
				spamCount += termCount;
			}
		double spamPercentage = (double) spamCount / totalWords;
		System.out.println(spamPercentage);
		return spamPercentage > this.spamThreshold;
	}

	private static List<String> removeContainedTerms(final List<String> terms) {
		List<String> filteredTerms = new ArrayList<>();

		for (int i = 0; i < terms.size(); i++) {
			String term1 = terms.get(i);
			boolean containsOther = false;

			for (int j = 0; j < terms.size(); j++)
				if (i != j && term1.contains(terms.get(j))) {
					containsOther = true;
					break;
				}

			if (!containsOther)
				filteredTerms.add(term1 + " ");
		}

		System.out.print(filteredTerms);
		return filteredTerms;
	}

}
