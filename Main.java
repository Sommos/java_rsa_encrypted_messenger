package encryption;

import java.math.BigInteger;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

// Don't get mad at this code - it was written by an idiot //
public class Main {
	public static void main(String[] args) {
		// Saves the current system time to a variable // 
		long start = System.currentTimeMillis();
		
		demonstration();
		// Prints the time to run out to the console //
		println("\nTime to run = " + (System.currentTimeMillis() - start) + "ms.");
		
		client();
	}
	// Methods for the Main class //
 
	// Method for showing a demonstration of the encryption algorithm //
	private static void demonstration() {
		// This is the start of the basic RSA encryption demonstration //
		println("--------------------| Demonstration Program - Build 0.1.57 |--------------------");
		// Instantiates the RSAEncryption class //
		RSAEncryption rsa = new RSAEncryption();
		
		// Gets the message from the user //
		String userInput = JOptionPane.showInputDialog("Enter your message");
		// Prompts the user for their desired bit length of keys //
		rsa.setBitLength();
	
		println("Original String = " + userInput);
		
		// Sets the values of the two prime keys that will be used for our encryption //
        rsa.setPrimeP(rsa.getBitLength());
        rsa.setPrimeQ(rsa.getBitLength());
        // Gets the values of primes and assigns memory addresses //
        BigInteger p = rsa.getPrimeP();
        BigInteger q = rsa.getPrimeQ();   
        // Sets the value of prime p totient //
        rsa.setPrimePTotient(p);        
        // Sets the value of prime q totient //
        rsa.setPrimeQTotient(q);        
        // Sets the value of e //
        rsa.setE();        
        // Sets the value of d //
        rsa.setD(rsa.getE(), p, q);
        // Gets the value of e //
        BigInteger e = rsa.getE();       
        // Sets the value of pq //
        rsa.setPQ(p, q);    
        // Gets the value of pq from our entered values of p and q (Our original keys) //
        BigInteger pq = rsa.getPQ(); 
       
        // Prints the generated keys to the console as proof of concept //
        println("\nThese are the original RSA encryption keys (using " + rsa.getBitLength() + " encryption):\n");
        println("-----BEGIN RSA PRIVATE KEY-----");
        println("Prime P = " + p);
        println("Prime Q = " + q);
        println("-----END RSA PRIVATE KEY-----\n");        
       
        // Takes the values generated and the users original message, encrypts it and returns it as a byte array //
        byte[] encrypted = rsa.returnEncryptedMessage(userInput.getBytes(), p, q, e, pq);        
        // May change this back to decrypted if byte values fail but we should be good //
        println("Byte Value (ENCRYPTED) = " + rsa.bytesToString(encrypted));        
        
        // Decrypts the values from the keys given (p and q) //
        byte[] decrypted = rsa.returnDecryptedMessage(encrypted, rsa.getD(), pq);        
        // Prints out the simplistic byte value to the console - May be useful later //
        println("Byte Value (DECRYPTED) = " + rsa.bytesToString(decrypted) + "\n");        
        
        // Converts the byte array to a String //
        String finalDecryptedString = new String(decrypted);        
        // Prints the decrypted String out to the console //
        println("Decrypted String = " + finalDecryptedString);
		// Prints exit code out to the console - More for error checking than anything else //
        println("\nDemonstration ran without any issues - GG WP.\nEXIT - FINAL TRANSMISSION. (M83 - INTRO).");
	}
	
	// Method that connects to the server and launches a client JFrame //
	private static void client() {
		// Instantiates the Client class, with the local IP address (This should be changed when in actual use) //
		Client client = new Client("127.0.0.1");
    
		// Sets up the default close situation (Which is when the JFrame is closed by the user) //
		client.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	
		// Starts the running of the Client class as a thread //
		client.startRunning();
	}
	
    // Method to print to the console in white text // 
	public static void println(Object x) {
		System.out.println(x);
	}
	
	// Method to print to the console in red text //
	public static void errorPrintln(Object x) {
		System.err.println(x);
	}
	
	// Method to exit the program and return 0 // 
	public static void exit(int x) {
		System.exit(x);
	}
}
