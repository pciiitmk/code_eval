package labs.pc.ce.medium.prefix_evaluation;

import java.io.*;

/**
 * Solution to prefix evaluation.
 * @author Prashant Chaturvedi
 *
 */
public class Mainv1 {
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
					char input = elements[i].charAt(0);
					if (Operator.isOperator(input)) {
						int a = (Integer) stack.pop();
						int b = (Integer) stack.pop();
						Integer result = Operator.getOperator(input).performOperation(a, b);
						stack.push(result);
					} else {
						stack.push('0' - input);
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
	
	private static class Stack<T> {
		private static final int MAX_STACK_SIZE = Integer.MAX_VALUE;
		
		protected int currentSize = 0;
		protected Node<T> topNode = null;
		
		public Stack () {
			
		}
		
		public void push (T object) throws IndexOutOfBoundsException {
			if (currentSize == MAX_STACK_SIZE) {
				throw new IndexOutOfBoundsException();
			}
			Node<T> newNode = new Node (object);
			newNode.nextNode = topNode;
			this.topNode = newNode;
			currentSize++;
		}
		
		public T pop () throws IndexOutOfBoundsException {
			if (isEmpty()) {
				throw new IndexOutOfBoundsException();
			}
			Node<T> tempNode = topNode;
			topNode = topNode.nextNode;
			return tempNode.data;
		}
		
		public boolean isEmpty () {
			return currentSize == 0;
		}
		
		public int size () {
			return currentSize;
		}
		
		// DS used for single Node
		private class Node<T> implements Cloneable {
			T data;
			Node<T> nextNode;
			
			public Node (T data) {
				this.data = data;
			}
			
			@Override
			public Node<T> clone () {
				Node<T> node = new Node (this.data);
				node.nextNode = this.nextNode;
				return node;
			}
		}
	}
	
	private static enum Operator {
		OPERATOR_ADD ('+') {
			@Override
			public int performOperation (int a, int b) {
				return a + b;
			}
		},
		OPERATOR_SUBTRACT ('-') {
			@Override
			public int performOperation (int a, int b) {
				return a - b;
			}
		},
		OPERATOR_DIVIDE ('/') {
			@Override
			public int performOperation (int a, int b) {
				return a / b;
			}
		},
		OPERATOR_MULTIPLY ('*') {
			@Override
			public int performOperation (int a, int b) {
				return a * b;
			}
		},
		OPERATOR_UNDEFINED ('\u0000') {
			@Override
			public int performOperation (int a, int b) throws UnsupportedOperationException {
				throw new UnsupportedOperationException();
			}
		};
		
		private char operator;
		
		private Operator (char operator) {
			this.operator = operator;
		}
		
		public abstract int performOperation (int a, int b);
		
		public static boolean isOperator (char input) {
			return input == '+' || input =='-' || input == '/' || input == '*';
		}
		
		public static Operator getOperator (char input) {
			for (Operator operator : values ()) {
				if (operator.operator == input) {
					return operator;
				}
			}
			return OPERATOR_UNDEFINED;
		}
	}
}