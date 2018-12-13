package Ex4_hachage;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.IntBuffer;

/**
 * Classe de réalisation du MD5.
 */
public class MD5
{
	/** Paramètre h0 de l'algorithme du MD5. */
	private static final int h0 = 0x67452301;
	
	/** Paramètre h1 de l'algorithme du MD5. */
	private static final int h1 = 0xEFCDAB89;
	
	/** Paramètre h2 de l'algorithme du MD5. */
	private static final int h2 = 0x98BADCFE;
	
	/** Paramètre h3 de l'algorithme du MD5. */
	private static final int h3 = 0x10325476;

	/** Paramètre r de l'algorithme du MD5. */
	private static final int[] r = {
			7, 12, 17, 22,  7, 12, 17, 22,  7, 12, 17, 22,  7, 12, 17, 22,
			5, 9, 14, 20,  5, 9, 14, 20,  5, 9, 14, 20,  5, 9, 14, 20,
			4, 11, 16, 23,  4, 11, 16, 23,  4, 11, 16, 23,  4, 11, 16, 23, 
			6, 10, 15, 21,  6, 10, 15, 21,  6, 10, 15, 21,  6, 10, 15, 21
		};

	/** Paramètre k de l'algorithme du MD5. */
	private static final int[] k = new int[64];
	
	/** flag indiquant si le tableau "k" est défini. */
	private static boolean kEstDefini = false;
	
	/**
	 * Définie les valeurs dans k.
	 * Doit être exécutée la premier fois que l'on génère un MD5.
	 */
	private static void definirK() {
		for (int i = 0; i < 64; i++)
			k[i] = (int)(long) Math.floor(Math.abs(Math.sin(i + 1)) * Math.pow(2, 32));		// Ici on cast en long avant de caster de nouveau en int afin d'éviter d'avoir des résultats éronnés.
	}

	/**
	 * Calcule le MD5 d'un message.
	 * @param message Le message pour lequel on cherche à calculer le MD5.
	 * @return L'empreinte MD5 du message sous forme hexadécimal.
	 */
	public static String genererMd5(String message) {
		// On vérifie si k est défini
		// Si ce n'est pas le cas, on le définie
		if (!kEstDefini) {
			definirK();
			kEstDefini = true;
		}
		
		// On va manipuler le message sous forme de tableau de byte.
		byte[] messageBytes = message.getBytes();

		// Il faut réserver une taille multiple de 64 bytes permettant de stocker le message ainsi que son padding.
		// Les 64 bytes réservés ici représentent les 512 bits nécessaires à l'algorithme du MD5.
		// L'utillisation du ByteBuffer nous permet d'ajouter facilement les informations de padding du MD5.
		int taille = (((messageBytes.length) / 64) + 1) * 64;
		ByteBuffer byteBufferMessage = ByteBuffer.allocate(taille).order(ByteOrder.LITTLE_ENDIAN);
		
		// On ajoute le message au buffer
		byteBufferMessage.put(messageBytes);
		
		// Les opérations bit à bit étant très compliqué sur des ByteBuffers, on utilise ici la technique suivante :
		// Le ByteBuffer que l'on alloue a toujours une taille multiple de 512 bits (soit 64 bytes).
		// L'ajout du bit à 1 et des bits restant à zero se fait via l'ajout du byte 0x80 (1000 0000 en binaire).
		// Cela ajoute un byte dont le premier bit est un '1' est les autres sont des '0'.
		// Les bits restants dans le ByteBuffer sont déjà tous des zéros, cela nous évite de boucler pour les ajouter.
		byteBufferMessage.put((byte)0x80);

		// Inutile, le ByteBuffer est initialement alloué avec des bytes de valeur 0x00.
		/*while(byteBufferMessage.position() < byteBufferMessage.capacity() - 8)
			byteBufferMessage.put((byte) 0x00);*/
		
		// Le type long est encodé sur 64bits.
		long tailleMessageEnBit = messageBytes.length * 8;
		
		// On ajoute la taille du message à la fin du ByteBuffer.
		// L'ajout se fait 8 bytes avant la fin du ByteBuffer car 64 bits correspondent à 8 bytes en mémoire :
		// 64/8 = 8.
		byteBufferMessage.putLong(byteBufferMessage.capacity() - 8, tailleMessageEnBit);
		
		// Remet la position du buffer à zero
		byteBufferMessage.rewind();
				
		
		int a = h0;
		int b = h1;
		int c = h2;
		int d = h3;
		int temp;
		while (byteBufferMessage.hasRemaining()) {
			// On récupère le contenu de nôtre ByteBuffer en tant que IntBuffer (donc avec chaque valeur codées sur 32 bits).
			// Cette récupération se fait à partir de la position actuelle de ByteBuffer qui n'est pas modifiée par cette opération.
			// Il faut donc modifier manuellement cette position pour la faire avancer de 512 bits.
			IntBuffer w = byteBufferMessage.slice().order(ByteOrder.LITTLE_ENDIAN).asIntBuffer();

			// On avance la position de 512 bits.
			// 64*8 = 512.
			byteBufferMessage.position(byteBufferMessage.position() + 64);
			
			int g;
			for (int i = 0; i < 64; i++) {
				int f = 0;
				if ((0 <= i) && (i <= 15)) {		// si 0 ≤ i ≤ 15 alors
					f = (b & c) | (~b & d);
					g = i;
				}
				else if ((16 <= i) && (i <= 31)) {	// sinon si 16 ≤ i ≤ 31 alors
					f = (d & b) | (~d & c);
					g = (5 * i + 1) % 16;
				}
				else if ((32 <= i) && (i <= 47)){	// sinon si 32 ≤ i ≤ 47 alors
					f = b ^ c ^ d;
					g = (3 * i + 5) % 16;
				}
				else {								// sinon si 48 ≤ i ≤ 63 alors
					f = c ^ (b | ~d);
					g = (7 * i) % 16;
				}
				
				temp = d;
				d = c;
				c = b;
				b = Integer.rotateLeft(a + f + k[i] + w.get(g), r[i]) + b;
				a = temp;
			}// For 64

			a += h0;
			b += h1;
			c += h2;
			d += h3;
		}// while

		// On alloue un ByteBuffer de taille 16.
		// 16 bytes correspondent à 128 bits soit la taille du résultat de l'algorithme du MD5.
		ByteBuffer md5 = ByteBuffer.allocate(16).order(ByteOrder.LITTLE_ENDIAN);
		
		// On concatène les résultats pour obtenir l'empreinte du MD5.
		md5.putInt(a);
		md5.putInt(b);
		md5.putInt(c);
		md5.putInt(d);
		
		// On retourne le résultat sous forme du String hexadécimale.
		return toHexadecimal(md5.array());
	}
	
	/**
	 * Convertie un tableau de byte en une string sous forme hexadécimal.
	 * @param message Le message à convertir. 
	 * @return La conversion hexadécimal du message.
	 */
	public static String toHexadecimal(byte[] message) {
		StringBuilder sb = new StringBuilder(message.length * 2);
		for(byte b: message)
			sb.append(String.format("%02x", b));
		return sb.toString();
	}
}
