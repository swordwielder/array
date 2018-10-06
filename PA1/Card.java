
/**
 * Write a description of class Card here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Card
{
    protected int suit, rank;
    //mutators
    public void setSuit(int suit)
    {
        if (suit >= 0 && suit <= 3)
            this.suit = suit;
    }
    //setters
    public void setRank(int rank)
    {
        if (rank >= 2 && rank <= 14)
            this.rank = rank;
    }

    // Constructor
    public Card(int suit, int rank)
    {
        setSuit(suit);
        setRank(rank);
    }
    //accessors
    public int getRank()
    {
        return rank;
    }
    //to string
    public String toString()
    {
        String rankString = "", suitString = "";
        if (rank >= 2 && rank <= 10)
            rankString = "" + rank;
        else if (rank == 11)
            rankString = "jack";
        else if (rank == 12)
            rankString = "queen";
        else if (rank == 13)
            rankString = "king";
        else if (rank == 14)
            rankString = "ace";

        if (suit == 0)
            suitString = "spades";
        else if (suit == 1)
            suitString = "hearts";
        else if (suit == 2)
            suitString = "clubs";
        else if (suit == 3)
            suitString = "diamonds";

        return rankString + " of " + suitString;
    }
}
