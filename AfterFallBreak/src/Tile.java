public class Tile
{
    String t;

    public Tile()
    {
        t = "e";
    }
    
    void change(Tile a)
    {
        if(a.t.equals("b"))
            a.t = "w";
        else if (a.t.equals("w"))
            a.t ="b";
    }

    public String toString()
    {
        return t;   
    }
}
