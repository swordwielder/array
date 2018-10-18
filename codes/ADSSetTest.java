import java.util.Random;

public class ADSSetTest
{
	public static void main(String[] args)
	{
		ADSSet set = new ADSSet(701);
		Random r = new Random(120);
		for(int i = 0; i < 2000; i++)
		{
		    int x = r.nextInt(1000000000);
			set.add(x);
			System.out.println(x);
		}
		System.out.println("# average collisions: " + set.printDistribution());
	}
}
