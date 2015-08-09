package labs.pc.ce.medium.reverse_and_add;

import java.io.*;

/**
 * Reverse and add.
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
				int number = Integer.parseInt(line);
				int count = 0;
				while (!isPalendrome(number)) {
					count++;
					number = number + reverse(number);
				}
				System.out.println(count + " " + number);
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
	
	public static int reverse (int number) {
		return Integer.parseInt(new StringBuilder (Integer.toString(number)).reverse().toString());
	}
	
	public static boolean isPalendrome (int number) {
		return number == reverse (number);
	}
}