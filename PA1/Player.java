
/**
 * Write a description of class Player here.
 * 
 * @author Andrew DiMotta & Qi Fen Chen
 * @version 3/1/12
 */
public class Player
{

    protected String name;
    protected Card c;
    protected Card [] playerCards = new Card [52]; protected Card [] cardSets2 = new Card [52];
    protected int books, numBooks, numCards = 0;
    //constructor
    public Player (String name)
    {
        this.name = name;
    }
    //add method for adding a card to the hard
    public void add(Card c1)
    {
        playerCards[numCards] = c1;
        numCards++;
    }
    //prints what cards the player has in his/her hand
    public void printCards ()
    {
        System.out.println();
        System.out.println("You have the following cards: ");
        System.out.println();
        for (int i=0; i<numCards; i++)
        {
            System.out.println(playerCards[i]);
        }
    }
    //checks if the hand is empty or not
    public boolean checkCard ()
    {
        int count =0;
        for( int i=0; i<numCards; i++)
        {
            if (playerCards[i] == null)
            {
                count ++;
            }
        }
        if (count == 52)
        {
            return true;
        }
        return false;
    }
    //the loop that removes however many cards match what the player asks for
    public void removeAllCards(int rank)
    {
        for(int i = 0; i < numCards; i++)
        {
            if(playerCards[i].rank == rank)
            {
                removeOne(i);
                i--;
            }
        }
    }
    //removes a book of 4 cards, and increments the number of books gained
    public void removeBooks()
    {
        int cardCounter = 0;
        for(int rank = 2; rank<=14; rank++)
        {
            cardCounter =0;
            for(int j = 0; j < numCards; j++)
            {
                if(playerCards[j] !=null && playerCards[j].rank == rank) 
                {
                    cardCounter++;
                }
            }

            if(cardCounter == 4)
            {
                removeAllCards(rank);
                numBooks++;
            }
        }
    }
    //removes one card if it matches the one asked for, this is looped however many times is needed
    public Card removeOne (int c)
    {
        Card temp;
        temp = playerCards[c];
        for (int i =c; i<numCards; i++)
        {
            playerCards[i] = playerCards[i+1];
        }
        numCards--;
        return temp;
    }
    //transfers a card or cards from one hand to the other
    public void transfer(int rank, Player p1)
    {
        Card temp;
        for(int i = 0; i<numCards; i++)
        {
            if (rank == playerCards[i].rank && playerCards[i] != null)
            {
                temp = removeOne(i);
                p1.add(temp);
            }
        }
    }
    //gives each player their name
    public String toString ()
    {
        return name;
    }
    //checks if the player has an empty hand
    public boolean isEmpty()
    {
        for(int i = 0; i<numCards; i++)
        {
            if(playerCards[i] != null)
                return false;
        }
        return true;
    }
    //various tests to ensure methods run within the class
    public static void main (String [] args)
    {
        Deck r = new Deck();
        Player playa = new Player("something");
        Card one = new Card(0,4);
        Card two = new Card(1,6);
        Card three = new Card(2,3);
        Card four = new Card(3,3);
        Card five = new Card(1,4);
        Card seven = new Card(1,9);
        Card eight = new Card(0,3);
        Card nine = new Card(1,3);
        Card six = new Card(0,13);
        
        playa.add(one);
        playa.add(two);
        playa.add(three);
        playa.add(four);
        playa.add(eight);
        playa.add(nine);
        playa.add(five);
        playa.add(six);
        playa.add(seven);
        
        playa.printCards();
        playa.removeBooks();
        playa.printCards();

    }
}
