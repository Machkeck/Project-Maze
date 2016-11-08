import java.util.Random;

//class for generating random numbers with a uniform distribution
public final class UGenerator {

    private static Random random;    
    private static long seed;        
   
    static {
        seed = System.currentTimeMillis();
        random = new Random(seed);
    }
    public static int uniform(int n)//returns random int number 
    {
        if (n <= 0) throw new IllegalArgumentException("Parameter N must be positive");
        return random.nextInt(n);
    }
	
}