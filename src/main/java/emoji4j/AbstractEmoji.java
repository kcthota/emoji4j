package emoji4j;

/**
 * 
 * @author chait
 *
 */
public abstract class AbstractEmoji {

	/**
	 * Helper to convert emoji characters to html entities in a string
	 * 
	 * @param text String to htmlify
	 * @param isHex isHex
	 * @return htmlified string
	 */
	protected static String htmlifyHelper(String text, boolean isHex) {

		StringBuffer sb = new StringBuffer();

		for (int i = 0; i < text.length(); i++) {
			int ch = text.codePointAt(i);

			if (ch <= 128) {
				sb.appendCodePoint(ch);
			} else if (ch > 128 && (ch < 159 || (ch >= 55296 && ch <= 57343))) {
				// don't write illegal html characters
				// refer
				// http://en.wikipedia.org/wiki/Character_encodings_in_HTML
				// Illegal characters section
				continue;
			} else {
				if (isHex) {
					sb.append("&#x" + Integer.toHexString(ch) + ";");
				} else {
					sb.append("&#" + ch + ";");
				}
			}

		}

		return sb.toString();
	}
}
