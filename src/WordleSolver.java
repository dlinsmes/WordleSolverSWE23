import java.util.ArrayList;

public class WordleSolver {
		private static String answer;

		private static ArrayList<String> possibleWords = new ArrayList<>();;
		private static ArrayList<String> possibleAnswers = new ArrayList<>();
		private final static int GREEN = 2;
		private final static int YELLOW = 1;
		private final static int GREY = 0;



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

				System.out.println(answer + " was found in " + guessesAttempted + " guesses");
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
		char[] letters = guess.toCharArray();
		char[] answerLetters = answer.toCharArray();
		for(int i = 0; i < guess.length(); i++){
			if(guess.charAt(i)==answer.charAt(i)){
				feedback[i] = GREEN;
				letters[i] = '&';
			}

		}
		for(int i = 0; i < guess.length(); i++){
			if(answer.indexOf(guess.charAt(i)) == -1){
				//indexOf pass in a substring give you negative one if its not there
				feedback[i]= GREY;
				letters[i]= '&';
			}
		}
		//check for yellow
		for(int i=0; i< guess.length(); i++){
			for(int j=0; j< answer.length(); j++){
				if(letters[i] == answerLetters[j]){
					feedback[i] = YELLOW;
					answerLetters[j] = '-';
					j = answer.length();
				}
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
// if there's only one green e and one gray e delete everything with multiple es
//        make sure that you check that there is the amount of letters if there's two es remove
//        if there's a gray you know the exact number of characters but if there's no green then
		for(int i = 0; i < 5; i++){
			if(feedback[i] == GREEN){
				for (int k = 0; k < possibleAnswers.size(); k++){
					if(possibleAnswers.get(k).charAt(i) != guess.charAt(i)){
						possibleAnswers.remove(k);
						k--;
					}
				}
			}
		}
		for(int i = 0; i < 5; i++){
			if(feedback[i] == YELLOW){
				for (int k = 0; k < possibleAnswers.size(); k++) {
					boolean haveYellow = false;
					// get rid of them or something if they don't have any yellows
					for(int j = 0; j < 5; j++){
//                    if any of the letters is the same as guess that was yellow thats not at the same location then make sure we don't delete it from list
						if(possibleAnswers.get(k).charAt(j)==guess.charAt(i) && j != i)
							haveYellow = true;
					}
					if(!haveYellow){
						possibleAnswers.remove(k);
						k--;
					}

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
