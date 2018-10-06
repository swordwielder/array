/**
 * Qi Fen Chen
 * 3/28/12
 * PA2
 * 
 */

import java.util.InputMismatchException;
import java.util.Scanner;
public class GameClient
{
    public static void main(String[] args){

        int tokens = 0, stile = 0, choice=0,numSpin=0;
        boolean repeat = true;
        Scanner input = new Scanner(System.in);
        
        System.out.println("Welcome to the Top Spin game (version 0.91)");
        System.out.println("------------------------------------------");
        do{  //repeats if tokens is not a good value
            try{  //catches input mismatch exceptions
                System.out.println("How many tokens would you like to play? (must be 3-50)");
                tokens = input.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("Error! not an integer!");
                input.nextLine();
            }
        } while (tokens < 3 || tokens > 50);
        do{  //repeats if stile is not a good value
            try{  // catches mismatch exceptions
                System.out.println("What is the turnstile size? (must be less than tokens & at least 2)");
                stile = input.nextInt();
                if (stile >=tokens)
                    System.out.println("Error! stile has to be less than tokens");
            } catch (InputMismatchException e) {
                System.out.println("Error! not an integer!");
                input.nextLine();
            }
        }while(stile < 2 || stile > 49 || stile >=tokens);

        TopSpinGame a = new TopSpinGame(tokens, stile);

        for (int i=0; i<stile/2; i++)
            a.randomize();

        System.out.println(a);
        do {  //repeats if the game is not over
            while(repeat) {
                try{  //handles exceptions
                    System.out.println("Press 1 to shift, 2 to spin, 3 to quit");
                    choice = input.nextInt();
                    repeat = false;
                }catch (InputMismatchException r) {
                    System.out.println("Error! not an integer!");
                    input.nextLine();
                }
            }
            repeat = true;
            if (choice == 1)  
            {
                while (repeat) {
                    try {  //handles exceptions
                        System.out.println("How much would you like to shift? (negative for left & positive for right)");
                        numSpin = input.nextInt();
                        repeat=false;
                        a.shift(numSpin);
                    }catch (InputMismatchException r) {
                        System.out.println("Error! not an integer!");
                        input.nextLine();
                    }
                }
            }
            repeat = true;
            if (choice ==2)
                a.spin();
            System.out.println(a);
        }while(choice !=3 && a.gameNotOver());

        if (choice ==3)
            System.out.println("kthxbye!");

        if (a.gameNotOver() ==false)
            System.out.println("You beat this game! |-.-| ");

    }
}
