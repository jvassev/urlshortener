package jvassev.urlshortener.impl;


public class NumberShortener {

	private static char[] DIGITS = "vamKUIjdXPiDT7cNVWJxFHwRSMO4028yZ9hfkb-q36l5pruEsLS1AQYgeoBzntG7hKkVqbGYm9"
			.toCharArray();

	public NumberShortener() {
		// List<Character> asList = new ArrayList<Character>();
		// for (char ch : DIGITS) {
		// asList.add(ch);
		// }
		// Collections.shuffle(asList);
		// for (Character character : asList) {
		// System.out.print(character);
		// }
	}

	public String shorten(long id) {
		StringBuilder sb = new StringBuilder();
		if (id == 0) {
			sb.append(DIGITS[0]);
		} else {

			while (id > 0) {
				long mod = id % DIGITS.length;
				id = id / DIGITS.length;
				sb.append(DIGITS[(int) mod]);
			}
		}

		return sb.toString();
	}
}
