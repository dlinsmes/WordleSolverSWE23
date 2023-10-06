import java.util.ArrayList;

public class WordleSolver {
		private static String answer;

		private static ArrayList<String> possibleWords;
		private static ArrayList<String> possibleAnswer;
		private final static int green = 2;
		private final static int yellow = 1;
		private final static int grey = 0;


		public static void main(String[] args) {

		}

		public static void setAnswer() {
//			set answer
		}

		public static void importData() {
//				import csv data for possible words
		}

		public int[] giveFeedback(String guess) {
			//check green: go through every letter of their guess and check if the letter at the index matches the final word
			int[] feedback = new int[guess.length()];
			for(int i = 0; i < guess.length(); i++){
				if(guess.charAt(i)==answer.charAt(i)){
					feedback[i] = green;
				}
			}

				return feedback;
		}

		public static void attemptGuess(){

		}
		public static void narrowList(){

		}




}
