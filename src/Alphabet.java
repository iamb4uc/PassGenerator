public class Alphabet {
	public static final String UPPERCASE = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
	public static final String LOWERCASE = "abcdefghijklmnopqrstuvwxyz";
	public static final String NUMBERS = "1234567890";
	public static final String SYMBOLS = "!@#$%^&*()-_=+\\/~?";
	private final StringBuilder pool;


	public Alphabet(boolean upprInc, boolean lwrInc, boolean numbInc, boolean symInc) {
		pool = new StringBuilder();
		if (upprInc) pool.append(UPPERCASE);
		if (lwrInc) pool.append(LOWERCASE);
		if (numbInc) pool.append(NUMBERS);
		if (symInc) pool.append(SYMBOLS);
	}
	
	public String getAlphabet() { return pool.toString(); }
}
