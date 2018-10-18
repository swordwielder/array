public class GameBoard
{
    private Tile [][] a;

    public GameBoard()
    {
        
    }
    
    public int count()
    {
        
        return 1;
    }
    
    public boolean move()
    {
        
        return false;
    }
    
    public int winning()
    {
        return 0;
    }
    
    public String toString()
    {
        String r="";
        for (int i = 0; i <a.length; i++){
            for (int j =0; j <a[i].length; j++)
                r+= a[i][j]+"\n";
        }
        return r;
    }
}
