import java.util.Scanner;
import java.util.List;
public class Interaction {
    Scanner scanner = new Scanner(System.in);
    final String introduction = "Welcome to Hangman";
    public String getIntroduction(){
        return this.introduction;
    }

    public String askForLetter(String currentWordState, List lettersGuessed){

        System.out.println("Guess a letter: " + currentWordState);
        System.out.println("Already guessed letters: " + lettersGuessed.toString().toLowerCase());
        String letter = scanner.nextLine().toLowerCase();

        while(!checkIfValidInput(letter, lettersGuessed)){
            System.out.println("Guess different letter: "+ currentWordState);
            System.out.println("Already guessed letters: " + lettersGuessed.toString().toLowerCase());
            letter = scanner.nextLine();
        }

        return letter.toLowerCase();
    }

    public boolean checkIfValidInput(String s, List lettersGuessed){

        if(s.length() > 1){
            System.out.println("Invalid input - letter must have 1 character");
            return false;}

        if(s.isEmpty()){
            System.out.println("you can't enter an empty letter");
            return false;
        }
        if(lettersGuessed.contains(s)){
            System.out.println("letter" + " '" + s +"' " + "already guessed!");
            return false;
        }else{
            if (s.matches("[0-9]")) {
                System.out.println("You can only pick alphabet characters!");
                return false;
            }
        }
        return true;

    }
    public String correctCharGuessMessage(String guessedChar){
        return guessedChar + " is a CORRECT guess";
    }
    public String wrongCharGuessMessage(String guessedChar){
        return guessedChar + " is a WRONG guess";
    }
    public String wonMessage (String word){
        return "You WON! You guessed the word " + word + " correctly!";
    }
    public String lostMessage (String word){
        return "You LOST! You did NOT guess the word correctly! The word was " + word;
    }

    public void updateHangManLives(int lives){

        switch(lives){

            case 6:
                System.out.println("You have 6 lives left!");
                System.out.println("  O  ");
                System.out.println("/ | \\ ");
                System.out.println("  |  ");
                System.out.println(" / \\ ");
                break;
            case 5:
                System.out.println("You have 5 lives left!");
                System.out.println("  O  ");
                System.out.println("  | \\ ");
                System.out.println("  |  ");
                System.out.println(" / \\ ");
                break;
            case 4:
                System.out.println("You have 4 lives left!");
                System.out.println("  O  ");
                System.out.println("  |  ");
                System.out.println("  |  ");
                System.out.println(" / \\ ");
                break;
            case 3:
                System.out.println("You have 3 lives left!");
                System.out.println("  O  ");
                System.out.println("  |  ");
                System.out.println("  |  ");
                System.out.println("   \\ ");
                break;
            case 2:
                System.out.println("You have 2 lives left!");
                System.out.println("  O  ");
                System.out.println("  |  ");
                System.out.println("  |  ");
                System.out.println("      ");
                break;
            case 1:
                System.out.println("You have 1 life left!");
                System.out.println("  O  ");
                System.out.println("  |  ");
                System.out.println("     ");
                System.out.println("     ");
                break;
            case 0:
                System.out.println("You have NO lives left!");
                System.out.println("  O  ");
                System.out.println("     ");
                System.out.println("     ");
                System.out.println("      ");
                break;
        }
    }
}