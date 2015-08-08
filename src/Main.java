import java.io.*;

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