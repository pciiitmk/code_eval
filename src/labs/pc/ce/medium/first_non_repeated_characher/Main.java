package labs.pc.ce.medium.first_non_repeated_characher;

import java.io.*;
import java.util.*;

/**
 * Default file template for code eval.
 * @author Prashant Chaturvedi
 *
 */
public class Main {
	private static final String INPUT_FILE_NAME = "input_file";
	
	public static void main (String[] args) {
		/*
		 * If the application is running locally, use this input file name. On the server,
		 * the server passes the name of the file as a command line argument when executing
		 * the code.
		 */
		File file = new File (args.length != 1? INPUT_FILE_NAME : args[0]);
		BufferedReader reader = null;
		try {
			reader = new BufferedReader (new FileReader(file));
			String line = null;
			while ((line = reader.readLine()) != null) {
				Map<Character, Integer> charFreq = new HashMap<> ();
				for (int i = 0; i <line.length(); ++i) {
					char character = line.charAt(i);
					if (charFreq.containsKey(character)) {
						charFreq.put(character, charFreq.get(character) + 1);
					} else {
						charFreq.put(character, 1);
					}
				}
				for (int i = 0; i < line.length(); ++i) {
					if (charFreq.get(line.charAt(i)) == 1) {
						System.out.println(line.charAt(i));
						break;
					}
				}
			}
		} catch (IOException ioe) {
			ioe.printStackTrace();
		} finally {
			if (reader != null) {
				try {
					reader.close();
				} catch (IOException ioe) {
					ioe.printStackTrace();
				}
			}
		}
	}
}