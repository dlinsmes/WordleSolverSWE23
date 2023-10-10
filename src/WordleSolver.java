import java.util.ArrayList;

public class WordleSolver {
		private static String answer;

		private static ArrayList<String> possibleWords = new ArrayList<>();;
		private static ArrayList<String> possibleAnswers = new ArrayList<>();
		private final static int green = 2;
		private final static int yellow = 1;
		private final static int grey = 0;



		public static void main(String[] args) {

				int guessesAttempted = 0;

				importData("Words.csv");
				setAnswer();
				System.out.println("The answer is: " + answer);
				String guess = "Filler";
				int[] feedback;

				while(!guess.equals(answer)){
					guess = attemptGuess();
					System.out.println("Guessing: " + guess);
					feedback = giveFeedback(guess);
					narrowList(guess, feedback);
					System.out.println("There are " + possibleWords.size() + " possible words left");
					guessesAttempted++;
				}

				System.out.println(answer + " Was found in " + guessesAttempted + "guesses");

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

		public static int[] giveFeedback(String guess) {
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

		public static String attemptGuess(){
//				choose a random word from possible words and return it
				int index = (int)(Math.random()* possibleWords.size());
				return possibleWords.get(index);

		}
		public static void narrowList(String guess, int[] feedback){
			int GREEN = 2;
			int YELLOW = 1;
			int GREY = 0;

			for(int i = 0; i < 5; i++){
				if(feedback[i] == GREEN){
					for (String word:possibleAnswers) {
						if(word.charAt(i) != GREEN)
							possibleAnswers.remove(word);
					}
				}
			}
			for(int i = 0; i < 5; i++){
				if(feedback[i] == YELLOW){
					for (String word:possibleAnswers) {
						boolean haveYellow = false;
						for(int j = 0; j < 5; j++){
							if(word.charAt(j)==YELLOW && j != i)
								haveYellow = true;
						}
						if(!haveYellow)
							possibleAnswers.remove(word);

					}
				}
			}
		}



		public static int countLetter(String word, char letter) {
			int count = 0;
			for(int i = 0; i < word.length(); i++){
				if(word.charAt(i) == letter) {
						count++;
				}
			}
			return count;
		}



}
