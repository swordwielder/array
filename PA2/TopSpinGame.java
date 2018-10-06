/**
 * Qi Fen Chen
 * PA2
 * 3/28/12
 * 
 */
import java.util.*;
public class TopSpinGame 
{
    protected int numTokens, stileSize;
    protected CircularDoubleLinkedList<Integer> l = new CircularDoubleLinkedList<Integer>();
    
    //constructor
    public TopSpinGame(int tokens, int stile){
        initialize(tokens,stile);
        for (int i =1; i <=tokens ; i++)
            l.add(i);
    }
    
    //initialize tokens and stile
    public void initialize(int tokens, int stile)
    {
        this.numTokens = tokens;
        this.stileSize = stile;
    }
    
    //randomize the tokens by shift and spin numTokens amount of time
    public void randomize()
    {
        for (int i =0; i < numTokens; i++)
        {
            int r =  (int)(2*numTokens*Math.random()-4) ;
            shift(r);
            spin();
        }
    }
    
    //shift method to shift tokens
    public void shift(int numShift)
    {
        l.current = l.head;
        if (numShift >0)
        {
            for (int i=0; i<numShift ; i++)
                l.current = l.current.next;
        }
        if (numShift <0)
        {
            numShift = Math.abs(numShift);
            for (int i = 0; i <numShift;i++)
                l.current = l.current.prev;
        }
        l.head = l.current;
    }
    
    //checks if the game is over or not
    public boolean gameNotOver()
    {
        int count =0;
        l.current2 = l.head;
        for (int i =1; i <=numTokens;i++)
        {
            if (l.current2.data.equals(1))
                break;
            l.current2 = l.current2.next;
        }
        for (int i =1; i <=numTokens; i++)
        {
            if (l.current2.data.equals(i))
                count++;
            l.current2 = l.current2.next;
        }
        if (count == numTokens)
            return false;
        return true;
    }

    //spins the tokens inside the stile
    public void spin()
    {
        CircularDoubleLinkedList<Integer> a = new CircularDoubleLinkedList<Integer>();
        for (int i =stileSize-1; i>=0; i--)  //adds tokens in the stile in reverse order to another linked list
            a.add( l.nodeAt(i).data );
        for (int i = stileSize; i < l.size; i++)  //adds the rest of the tokens not in stile to another linked list
            a.add( l.nodeAt(i).data );
        l.clear();
        for (int i =0; i < a.size; i++)  //copies everything back to the original
            l.add( a.nodeAt(i).data );
    }

    //toString method
    public String toString()
    {
        String s = "// ";
        l.current = l.head;
        for (int i =0; i < stileSize; i++)
        {
            s += l.current.data + " ";
            l.current = l.current.next;
        }
        s+= "// ";
        for (int i=0; i < (numTokens-stileSize) ; i++)
        {
            s += l.current.data + " ";
            l.current = l.current.next;
        }
        return s;
    }
    
    public static void main (String [] args)
    {
        Scanner input = new Scanner (System.in);
        System.out.println("enter tokens");
        int t = input.nextInt();
        System.out.println("enter stile");
        int s = input.nextInt();
        TopSpinGame a = new TopSpinGame(t, s);
        System.out.println(a.numTokens-a.stileSize);
    }

}
