import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Hangman {
    public static void main(String[] args) throws FileNotFoundException {

        Scanner scanner = new Scanner(new File("words_alpha.txt"));

        Scanner keyboard = new Scanner(System.in);

        List<String> words = new ArrayList<>();
        while (scanner.hasNext()){
            words.add(scanner.nextLine());
        }

        Random random = new Random();
        String chosenWord = words.get(random.nextInt(words.size()));

        // create a selected word variable
        // that uses the words arrayList and gets a random word, within the
        // number of words in the txt file

        System.out.println(chosenWord);

        List<Character> playerGuesses = new ArrayList<>();

        printWordState(chosenWord, playerGuesses);


        int wrongCount = 0;

        while(true) {

            System.out.println("  +---+");
            System.out.println("  |   |");

            if (wrongCount >= 1){
                System.out.println("  O   |");
            }
            if (wrongCount >= 2){
                System.out.print("\\ ");
                if (wrongCount >= 3) {
                    System.out.print("/");
                }
                else {
                    System.out.println("");
                }
            }
            if (wrongCount >= 4){
                System.out.println("   |");
            }
            if (wrongCount >= 5) {
                System.out.print("/ ");
                if (wrongCount >= 6) {
                    System.out.println("\\");
                } else {
                    System.out.println("");
                }


            }

            printWordState(chosenWord, playerGuesses);
            if (!getPlayerGuess(keyboard, chosenWord, playerGuesses)) {
                wrongCount++;
            }

            if (printWordState(chosenWord, playerGuesses)){
                System.out.println("You Win!");
                break;
            }
            System.out.println("Please enter your guess for the complete word: ");
            if (keyboard.nextLine().equals(chosenWord)){
                System.out.println("You Win!");
                break;
            }
            else{
                System.out.println("Nope! Try Again");
            }
        }

        //if a letter occurs more than once in a word, all instances of that
        // letter guessed will be exposed


    }

    private static boolean getPlayerGuess(Scanner keyboard, String chosenWord, List<Character> playerGuesses) {
        System.out.println("Please enter a letter");
        String letterGuess = keyboard.nextLine();
        playerGuesses.add(letterGuess.charAt(0));
        //charAt allows you specify a characters position.
        return chosenWord.contains(letterGuess);
    }

    private static boolean printWordState(String chosenWord, List<Character> playerGuesses) {
        int correctCount = 0;
        for (int i = 0; i < chosenWord.length(); i++){
            if (playerGuesses.contains(chosenWord.charAt(i))){
                System.out.print(chosenWord.charAt(i));
                correctCount++;
            }
            else{
                System.out.print("-");
            }

        }
        System.out.println();
        return (chosenWord.length() == correctCount);
        //returns true or false
    }
}
