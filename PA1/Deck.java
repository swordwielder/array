
/**
 * Write a description of class Deck here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Deck
{
    protected Card[] theCards = new Card[52];

    // This variable keeps track of where the "top" card in the deck is.
    protected int topIndex = 0;
    protected int numCards =52;

    // Constructor
    public Deck()
    {
        // add the standard 52 cards into the array
        int c = 0;  // this counter keeps track of where we are in the array
        for (int s = 0; s < 4; s++) {           // loop through the suits
            for (int r = 2; r <= 14; r++) {     // ... and loop through the ranks
                theCards[c] = new Card(s, r);
                c++;
            }
        }

    }
    
    public int getNumCards()
    {
        return numCards;
    }

    public void numCardLeft ()
    {
        System.out.println("--------------------");
        System.out.println("The deck has " + numCards + " cards left");
    }

    // Randomly rearranges the cards in the deck.
    public void shuffle()
    {
        for (int i = 0; i < theCards.length; i++) {
            // swap the card at position i with the card at some random index
            int randomIndex = (int)(52*Math.random());

            Card temp = theCards[i];
            theCards[i] = theCards[randomIndex];
            theCards[randomIndex] = temp;
        }
    }

    // Draws the "top" card from the deck and returns it.
    public Card draw()
    {
        topIndex++;
        numCards--;
        return theCards[topIndex-1];

    }

    public String toString()
    {
        String s = "";
        for (int i = 0; i < theCards.length; i++)
            s += theCards[i] + "\n";    // each individual Card object's toString is called here
        return s;
    }

    // Test main - creates a Deck object, shuffles it, and draws the top 4 cards
    public static void main(String[] args)
    {
        Deck d = new Deck();
        d.shuffle();
        System.out.println(d.draw());
        System.out.println(d.draw());
        System.out.println(d.draw());
        System.out.println(d.draw());
    }
}
