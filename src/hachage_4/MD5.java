package hachage_4;

public class MD5 {
	
	
	private static int[] k;
	private static int[] r = {
				7, 12, 17, 22,  7, 12, 17, 22,  7, 12, 17, 22,  7, 12, 17, 22,
				5, 9, 14, 20,  5, 9, 14, 20,  5, 9, 14, 20,  5, 9, 14, 20,
				4, 11, 16, 23,  4, 11, 16, 23,  4, 11, 16, 23,  4, 11, 16, 23, 
				6, 10, 15, 21,  6, 10, 15, 21,  6, 10, 15, 21,  6, 10, 15, 21
			};
	
	private static int h0 = 0x67452301;
	private static int h1 = 0xEFCDAB89;
	private static int h2 = 0x98BADCFE;
	private static int h3 = 0x10325476;
	
	private static boolean flag = false;
	
	public static String MD5ValueOf(String message) {
		if (!flag) {
			definirK();
			flag = true;
		}
		return null;
	}
	
	private static void definirK() {
		k = new int[64];
		for(int i=0; i<64; i++)
			k[i] = (int) Math.floor(Math.abs(Math.sin(i + 1)) * Math.pow(2, 32));
	}
}
