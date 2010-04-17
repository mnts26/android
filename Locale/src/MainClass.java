import java.util.Locale;


public class MainClass {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		for (String s: Locale.getISOCountries()) {
			
			System.out.println(s);
		}
		
		
		System.out.println(Locale.FRENCH.getDisplayName());
	}

}
