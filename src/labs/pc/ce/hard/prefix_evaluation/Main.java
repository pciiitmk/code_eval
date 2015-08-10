package labs.pc.ce.hard.prefix_evaluation;

import java.io.*;
import java.util.*;

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
				String[] elements = line.split(" ");
				Stack stack = new Stack ();
				for (int i = elements.length - 1; i >= 0; --i) {
					String input = elements[i];
					if (Operator.isOperator(input)) {
						int a = (Integer) stack.pop();
						int b = (Integer) stack.pop();
						Integer result = Operator.getOperator(input).performOperation(a, b);
						stack.push(result);
					} else {
						stack.push(Integer.parseInt(input));
					}
				}
				System.out.println(stack.pop());
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
	
	private static enum Operator {
		OPERATOR_ADD ("+") {
			@Override
			public int performOperation (int a, int b) {
				return a + b;
			}
		},
//		OPERATOR_SUBTRACT ("-") {
//			@Override
//			public int performOperation (int a, int b) {
//				return a - b;
//			}
//		},
		OPERATOR_DIVIDE ("/") {
			@Override
			public int performOperation (int a, int b) {
				return a / b;
			}
		},
		OPERATOR_MULTIPLY ("*") {
			@Override
			public int performOperation (int a, int b) {
				return a * b;
			}
		};
//		OPERATOR_UNDEFINED ("") {
//			@Override
//			public int performOperation (int a, int b) throws UnsupportedOperationException {
//				throw new UnsupportedOperationException();
//			}
//		};
		
		private String operator;
		
		private Operator (String operator) {
			this.operator = operator;
		}
		
		public abstract int performOperation (int a, int b);
		
		public static boolean isOperator (String input) {
			if (input.length() != 1) {
				return false;
			}
			char operator = input.charAt(0);
			return operator == '+' 
					|| operator == '*'
					|| operator == '/';
		}
		
		public static Operator getOperator (String input) {
			for (Operator operator : values ()) {
				if (operator.operator.equals(input)) {
					return operator;
				}
			}
			return null;
		}
	}
}