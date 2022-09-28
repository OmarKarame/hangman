
import java.net.HttpURLConnection;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import java.net.URL;
import java.util.Random;
import java.util.Scanner;

public class WordList {
    private String wordToGuess = "";
    private String remainingLettersToGuess = "";
    private String currentWordState = "";

    private Random random = new Random();

    public WordList(){

        this.wordToGuess = generateWord();

        this.remainingLettersToGuess = this.wordToGuess;

        for(int i = 0; i<this.wordToGuess.length(); i++){
            this.currentWordState += "_";
        }
    }

    public String generateWord(){

        try{
            URL url = new URL("https://random-words-api.vercel.app/word\n");

            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.connect();

            //check of connection successful
            int responseCode = conn.getResponseCode();

            if (responseCode != 200){

                throw new RuntimeException("Can't connect to the API, HttpResponseCode: " + responseCode);
            }else{

                StringBuilder result = new StringBuilder();
                Scanner scanner = new Scanner(url.openStream());

                while (scanner.hasNext()){
                    result.append(scanner.nextLine());
                }

                JSONParser parser = new JSONParser();
                Object obj  = parser.parse(result.toString());
                JSONArray array = (JSONArray) obj;

                JSONObject jsonObject = (JSONObject)array.get(0);

                return (String) jsonObject.get("word");
            }
        }
        catch (Exception e) {
            System.out.println("SOMETHING WENT WRONG");
            throw new RuntimeException(e);
        }

    }
    public void updateWordToGuess(char letter){

        for(int i = 0; i<this.remainingLettersToGuess.length(); i++){
            if(this.remainingLettersToGuess.toLowerCase().charAt(i) == Character.toLowerCase(letter)){
                this.currentWordState = this.currentWordState.substring(0,i) + letter + this.currentWordState.substring(i+1);
                this.remainingLettersToGuess = this.remainingLettersToGuess.substring(0,i) + " " + this.remainingLettersToGuess.substring(i+1);
            }
        }
    }
    public String getWordToGuess(){
        return this.wordToGuess;
    }

    public boolean checkIfGuessed(){
        return this.wordToGuess.toLowerCase().equals(currentWordState.toLowerCase());
    }
    public String remainingLettersToGuess() {
        return this.remainingLettersToGuess;
    }
    public String getCurrentWordState() {
        return this.currentWordState;
    }
}
