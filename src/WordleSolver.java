import java.util.ArrayList;

public class WordleSolver {
		private static String answer;

		private static ArrayList<String> possibleWords = new ArrayList<>();;
		private static ArrayList<String> possibleAnswers = new ArrayList<>();
		private final static int green = 2;
		private final static int yellow = 1;
		private final static int grey = 0;


		public static void main(String[] args) {
		}

		public static void setAnswer() {
//			set answer
			int index = (int)(Math.random()* possibleAnswers.size());
			answer = possibleAnswers.get(index);
		}

		public static void importData(String filename) {
				ArrayList<String[]> data = CSVImporter.importData(filename);
				for (int i = 1; i < data.size(); i++) {
						String word = data.get(i)[1];
						if(word.length() == 5) {
								possibleWords.add(word);
						}
				}
				for(int i = 1; i < data.size(); i++) {
						String word = data.get(i)[2];
						if(word.length() == 5) {
								possibleWords.add(word);
								possibleAnswers.add(word);
						}

				}
		}

		public int[] giveFeedback(String guess) {
			//check green: go through every letter of their guess and check if the letter at the index matches the final word
			int[] feedback = new int[guess.length()];
			for(int i = 0; i < guess.length(); i++){
				if(guess.charAt(i)==answer.charAt(i)){
					feedback[i] = green;
				}

			}
			for(int i = 0; i < guess.length(); i++){
				if(guess.indexOf(answer.charAt(i)) == -1){
					//indexOf pass in a substring give you negative one if its not there
					feedback[i]= grey;
				}
			}


				return feedback;
		}

		public static void attemptGuess(){


		}
		public static void narrowList(){

		}

		public int countLetter(String word) {
			int count = 0;
			char[] wordAsArray = word.toCharArray();
			for (int i = 0; i < wordAsArray.length; i++) {
				char currentLetter = word.charAt(i);
				for (int j = i+1; j < wordAsArray.length; j++) {
					if (currentLetter == wordAsArray[j]) {
						count = count+1;
						wordAsArray[j] = 0;
					}
				}
			}
			return count;
		}



}
