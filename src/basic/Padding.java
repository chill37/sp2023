package basic;

public class Padding {

	public static void main(String[] args) {
		System.out.println(padRight("123", 5));
		System.out.println(padLeft("123", 5));

	}
	
	static String padRight(String s, int n) {
	     return String.format("%-" + n + "s", s).replace(' ', '0');
	}

	static String padLeft(String s, int n) {
	    return String.format("%" + n + "s", s).replace(' ', '0');
	}

}
