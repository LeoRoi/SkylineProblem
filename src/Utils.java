public class Utils {
	// are the strings null V empty? if so return true
	public static boolean stringCheck(String s1, String s2, String s3, String s4) {
		int stat = 0;

		if (s1 != null && !s1.isEmpty())
			stat++;
		//if (s2 != null && !s2.isEmpty())
			stat++;
		if (s3 != null && !s3.isEmpty())
			stat++;
		if (s4 != null && !s4.isEmpty())
			stat++;

		if (stat == 4)
			return false;
		return true;
	}
}
