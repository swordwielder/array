
/**
 * Program runs a text-based version of Go-Fish
 * 
 * @author Andrew DiMotta & Qi Fen Chen
 * @version 3/1/12
 */
import java.util.*;
public class GoFish 
{

    private static Scanner s = new Scanner(System.in);
    private Player [] allPlayers;
    private int win;
    private int callTurn;
    private int turn = 0, numPlayers;
    private int player;
    private Deck d = new Deck();

    //GoFish constructor
    public GoFish ()
    {
        initializePlayers();
        // start a new game
        startingPoint();
        start();
    }
    //starting the game by adding cards to the players
    public void startingPoint()
    {
        //shuffles the deck, gives each player 5 cards
        d.shuffle();
        for (int i =0; i<numPlayers; i++)
        {
            for (int j=0; j<5; j++)
            {
                allPlayers[i].add(d.draw());
            }
        }
        System.out.println();
        d.numCardLeft();
        System.out.println("It is " + allPlayers[0] +"'s turn");
        allPlayers[0].printCards();
    }
    //creates the players, taking their names and how many of them are there
    public void initializePlayers()
    {
        System.out.println("Welcome to a game of GoFish!");
        System.out.println("----------------------------");
        System.out.println();
        int count = 1;

        do 
        {
            System.out.println("How many players are there? Only enter 3-6");
            numPlayers = s.nextInt();
        } while (numPlayers > 6 || numPlayers < 3);

        allPlayers = new Player [numPlayers];

        for (int i=0; i<numPlayers; i++)
        {
            System.out.println("Enter a name for player " + count);
            if (count == 1)
            {
                String name = s.nextLine();
                allPlayers[0] = new Player(name);
            }
            String name = s.nextLine();
            allPlayers[i] = new Player(name);
            count++;
        }

        for (int i=0; i<numPlayers; i++)
        {
            System.out.println("Player " + (i+1) + " is " + allPlayers[i] );
        }
        d.shuffle();
    }
    //determines who won by checking who had the most books
    public void isWinning(Player c)
    {
        int winner=0;
        int maxBooks=0;
        for (int i=0; i<allPlayers.length; i++)
        {
            if (allPlayers[i].numBooks > maxBooks)
            {
                maxBooks = allPlayers[i].numBooks;
                winner=i+1;
            }
        }

        System.out.println("Player " + winner + " has won!" + " with "+ maxBooks + " books!");

        for (int i=allPlayers.length-1; i>=0; i--)
        {
            if (allPlayers[i].numBooks == maxBooks)
            {
                System.out.println("Player " + (i+1) + " has won!" + " with "+ maxBooks + " books!");
            }
        }
    }
    //checks if the card is a valid card
    public boolean checkCardValid (Player p, int cardRank)
    {
        for(int j = 0; j < p.numCards; j++)
        {    
            if(p.playerCards[j] != null && p.playerCards[j].getRank() == cardRank)
            {
                return true;
            }
        }
        return false;
    }
    //method so player can take turn
    public void takeTurn(Player p)
    {
        Scanner s = new Scanner(System.in);
        int cardRank = 0;
        boolean temp=false;
        System.out.println();
        //checks if the player tries to ask for a card they do not have in their own hand
        do
        {
            System.out.println("What card rank are you calling for?" + "\n"+ "11 for jack, 12 for queen, 13 for king, 14 for ace");
            cardRank = s.nextInt();
            temp = checkCardValid(p,cardRank);
            if(!temp)
            {
                System.out.println("You can't ask for a card you don't have or an invalid card");
            }
        }while ( !temp);
        //checks if the player tries to ask for a card from a player that does not exist in the game, or if he/she asks for a card from their own hand
        do
        {
            System.out.println("Which player would you like to take it from?" + " 1 for player 1, 2 for player 2, etc.");
            player = s.nextInt();
            player = player-1;
            System.out.println("--------------------");

            if(player >= allPlayers.length || player < 0)
            {
                System.out.println("Nonexistent player");
            }
            if (player >=0 && player <allPlayers.length)
            {
                if(allPlayers[player].equals(p))
                {
                    System.out.println("YOU CANNOT ASK YOURSELF FOR CARDS!");
                    player = 10;
                }
            }

        } while (player >= allPlayers.length || player < 0);

        temp = false;
        for(int j = 0; j < p.numCards; j++)
        {    
            if(allPlayers[player].playerCards[j] != null && allPlayers[player].playerCards[j].getRank() == cardRank)
            {
                temp = true;
            }
        }
        System.out.println();
        //if the player doesn't have the card, go fish, if they do, transfer the card from the other player's hand to the one asking for the card
        if(!temp)
        {
            System.out.println("******* " + allPlayers[player] + " does not have that card! *******");
            System.out.println("Go fish!");
            Card c = d.draw();
            System.out.println("You drew a " + c +"\n");
            p.add(c);
            if(c.rank != cardRank)
            {
                turn = player;
            }
        }
        else
        {
            System.out.println("******* " + allPlayers[player] + " has that card! *******");
            allPlayers[player].transfer(cardRank, p);
        }
        p.removeBooks();
    }
    //method to check if the game is over
    public boolean checkGameOver()
    {
        //if the deck is empty, the game ends
        if(d.numCards == 0)
        {
            System.out.println("No Cards remaining, game over!");
            return true;
        }
        //if a player runs out of cards, the game ends
        for(int i = 0; i < allPlayers.length; i++)
        {
            if(allPlayers[i].isEmpty())
            {
                System.out.print("Player has no cards left in hand . . . game over!");
                return true;
            }
        }
        return false;    
    }
    //to start the game
    public void start()
    {
        //starts the game, letting each player take a turn and determines who has won at the end
        do
        {
            takeTurn(allPlayers[turn]);
            System.out.println();
            d.numCardLeft();
            System.out.println();
            System.out.println("it is " + allPlayers[turn] +"'s turn");
            System.out.println("You currently have " + allPlayers[turn].numBooks + " books.");
            allPlayers[turn].printCards();
        }while(!checkGameOver());
        for (int i =0; i< allPlayers.length; i++)
        {
            isWinning(allPlayers[i]);
        }
    }
    //main method to create the Gofish object so we can rush the game.
    public static void main (String [] args)
    {
        GoFish game = new GoFish();
    }
}
