package lab12;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

public class Encrypter extends EncryptionTester {

    private int shift;
    private String encrypted;

    /**
     * Default Constructor
     */
    public Encrypter() {
        this.shift = 1;
        this.encrypted = "";
    }

    /**
     * Non-default Constructor
     * @param s - custom shift amount
     */
    public Encrypter(int s) {
        this.shift = s;
        this.encrypted = "";
    }

    /**
     * Encrypts the content of a file and writes the result to another file.
     *
     * @param inputFilePath      the path to the file containing the text to be encrypted
     * @param encryptedFilePath the path to the file where the encrypted text will be written
     * @throws Exception if an error occurs while reading or writing the files
     */
    public void encrypt(String inputFilePath, String outputFilePath) throws Exception {
        //TODO: Call the read method, encrypt the file contents, and then write to new file
    	Scanner scanner = new Scanner(new File(inputFilePath));
    	
        ArrayList<Character> characters = new ArrayList<>();

        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            for (int i = 0; i < line.length(); i++) {
                characters.add(line.charAt(i));
            }
        }

        scanner.close();

        for (int i = 0; i < characters.size(); i++) {
            characters.set(i, (char) (characters.get(i) - 4));
        }
        
        StringBuilder encryptedTextBuilder = new StringBuilder();
        boolean isWord = false;

        for (Character character : characters) {
            if (Character.isLetterOrDigit(character)) {
                encryptedTextBuilder.append(character);
                isWord = true;
            } else if (isWord) {
                encryptedTextBuilder.append(" ");
                isWord = false;
            }
        }


        PrintWriter writer = new PrintWriter(new File(outputFilePath));

        for (Character character : characters) {
            writer.write(character);
        }
        writer.println(encryptedTextBuilder.toString().trim());
        writer.close();
    	
    }

    /**
     * Decrypts the content of an encrypted file and writes the result to another file.
     *
     * @param messageFilePath    the path to the file containing the encrypted text
     * @param decryptedFilePath the path to the file where the decrypted text will be written
     * @throws Exception if an error occurs while reading or writing the files
     */
    public void decrypt(String encryptedFilePath, String decryptedFilePath) throws Exception {
        //TODO: Call the read method, decrypt the file contents, and then write to new file
    	
    	Scanner scanner = new Scanner(new File(encryptedFilePath));
    	
        ArrayList<Character> characters = new ArrayList<>();

        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            for (int i = 0; i < line.length(); i++) {
                characters.add(line.charAt(i));
            }
        }

        scanner.close();

        for (int i = 0; i < characters.size(); i++) {
        	char decryptedChar = (char) (characters.get(i) - 4);
            
            
            if (characters.get(i) >= 'a' && characters.get(i) <= 'z' && decryptedChar < 'a') {
                decryptedChar += 26;
            }

            characters.set(i, decryptedChar);
        }
        
        StringBuilder decryptedTextBuilder = new StringBuilder();
        boolean isWord = false;

        for (Character character : characters) {
            if (Character.isLetterOrDigit(character)) {
                decryptedTextBuilder.append(character);
                isWord = true;
            } else if (isWord) {
                decryptedTextBuilder.append(" ");
                isWord = false;
            }
        }

        String decryptedText = decryptedTextBuilder.toString().trim();
    	
        PrintWriter decryptedWriter = new PrintWriter(new File(decryptedFilePath));
        decryptedWriter.println(decryptedText);
        decryptedWriter.close();
    }

    /**
     * Reads the content of a file and returns it as a string.
     *
     * @param filePath the path to the file to be read
     * @return the content of the file as a string
     * @throws Exception if an error occurs while reading the file
     */
    private static String readFile(String filePath) throws Exception {
        String message = "";
        //TODO: Read file from filePath
        try (Scanner fileScanner = new Scanner(Paths.get(filePath))) {
        	while(fileScanner.hasNextLine()) {
        		String line = fileScanner.nextLine();
        		message += line;
        	}
        	fileScanner.close();
        } catch (Exception e) {
        	System.out.println("Error: " + e.toString());
        }
        
        return message;
    }

    /**
     * Writes data to a file.
     *
     * @param data     the data to be written to the file
     * @param filePath the path to the file where the data will be written
     */
    private static void writeFile(String data, String filePath) {
        //TODO: Write to filePath
    	try(PrintWriter output = new PrintWriter(filePath)){
    	output.println();
    			output.close();
    	} catch(Exception e) {
    		System.out.println("Error: " + e.toString());
    	}
    }

    /**
     * Returns a string representation of the encrypted text.
     *
     * @return the encrypted text
     */
    @Override
    public String toString() {
        return encrypted;
    }
}
