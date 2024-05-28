import java.util.Random;
import java.util.Scanner;

public class RockPaperScissors {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        //scanner is used to accept the players response to the prompts
        //scanner does not like being placed in the while loop
        // because it will be executed multiple times

        while (true) {

            String[] rps = {"r", "p", "s"};
            //we need to create an array called rps (Rock Paper Scissors) which we will use
            //to store the values

            String computerMove = rps[new Random().nextInt(rps.length)];
            //we define the computerMove as a random value r,p or s (i.e. 0,1 or 2)

            String playerMove;
            //we declare the playerMove attribute as a string
            // ,so it can be compared to the computerMove

            while (true) {
                //while loop remains true if user has not selected a response

                System.out.println("Please enter your move: (r,p, or s)! ");
                playerMove = scanner.nextLine();
                // .nextLine means that the players response has been recorded
                // and the next code block will be executed

                if (playerMove.equals("r") || playerMove.equals("p") || playerMove.equals("s")) {
                    break;
                    //if player's response is valid we break i.e. finish the while loop
                }
                System.out.println(playerMove + " Is not a valid move.");
                //if the user returns a response that is unexpected they will recieve the print line statement
                // and will still be prompted to enter their move again
            }

            System.out.println("Computer played: " + computerMove);

            //after user inputs their move, the computerMove is revealed

            //then we begin to compare the moves to see who won

            if (playerMove.equals(computerMove)){
                System.out.println("The game was a tie- you both win ");
            }
            //basically- if the playerMove is the same as the computerMove then
            //its a draw and the print line statement is shown

            else if (playerMove.equals("r")){
                //if the playerMove is rock and..
                if (computerMove.equals("p")){
                    //the computerMove is paper then..
                    System.out.println("You lose!");
                    //paper beats rock and you lose!

                } else if (computerMove.equals("s")){
                    //if computerMove is scissors..
                    System.out.println("You win!! ");
                    // rock beats scissors so you win!
                }

            }
            //the following else if blocks are the same logic for different cases
            //of player and computer moves:

            else if (playerMove.equals("p")){
                if (computerMove.equals("r")){
                    System.out.println("You win!! ");
                } else if (computerMove.equals("s")){
                    System.out.println("You lose! ");
                }

            }
            else if (playerMove.equals("s")){
                if (computerMove.equals("p")){
                    System.out.println("You win!! ");
                } else if (computerMove.equals("r")){
                    System.out.println("You lose! ");
                }

            }

            System.out.println("Would you like to Play Again?  (y/n)");
            String playAgain = scanner.nextLine();

            // give the user option to exit out of program

            if (!playAgain.equals("y")){
                break;
            }
            //very concise code, if the user's response is yes then the while loop
            //continues. If response is not yes then while loop exits.

            scanner.close();
            //always make sure to close scanner after exit

        }

        //all our code can function without while loop
        // however the while loop at the very top allows user to continue playing
        // until specified that they want to exit




    }


}