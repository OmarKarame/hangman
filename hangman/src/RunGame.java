import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class RunGame {
    static Result result = new Result();
    static WordList wordList = new WordList();
    static Interaction interactions = new Interaction();

    public static void initiateGame(){
        System.out.println("Would you like to play hangman? type Y for yes or N for no.");
        Scanner scanner = new Scanner(System.in);
        String letter = scanner.nextLine().toLowerCase();
        while (!letter.equals("y") && !letter.equals("n")){
            System.out.println("Please type in the correct letter! type Y for yes or N for no.");
            scanner = new Scanner(System.in);
            letter = scanner.nextLine().toLowerCase();
        }
        if (!letter.equals("y")){
            System.out.println("Okay, see you next time!");
            System.exit(0);
        } else {
            result.setLives(6);
            startGame();
        }
    }
    public static void startGame(){

        List<String> lettersGuessed = new ArrayList<String>();

//        System.out.println(wordList.getWordToGuess());
        System.out.println(interactions.getIntroduction());
        interactions.updateHangManLives(result.getLives());

        while(!wordList.checkIfGuessed() && result.getLives()>0){

            String guessedLetter = interactions.askForLetter(wordList.getCurrentWordState(), lettersGuessed);

            if(!guessedLetter.equals("[a-z]")){
                lettersGuessed.add(guessedLetter);
            }
            if(wordList.remainingLettersToGuess().toLowerCase().contains(guessedLetter.toLowerCase())){
                System.out.println(interactions.correctCharGuessMessage(guessedLetter));
                wordList.updateWordToGuess(guessedLetter.charAt(0));
            }
            else{
                System.out.println(interactions.wrongCharGuessMessage(guessedLetter));
                result.reduceLives();
            }
            interactions.updateHangManLives(result.getLives());
        }
        if(wordList.checkIfGuessed()){
            System.out.println(interactions.wonMessage(wordList.getWordToGuess()));
        }else{
            System.out.println(interactions.lostMessage(wordList.getWordToGuess()));
        }
        result = new Result();
        wordList = new WordList();
        interactions = new Interaction();
        initiateGame();
    }
}
