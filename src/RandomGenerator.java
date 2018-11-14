//  RandomGenerator.java
import java.util.Random;
/**
 * This class generates random integers between 2 bounds
 * @author Professor
 *
 */
class RandomGenerator {
/**
 * creates a random number ranging between lo and hi,  
 * @param lo lower bound number
 * @param hi upper bound number
 * @return random integer between lo and hi
 */
	int discrete(int lo, int hi)
	{
		if(lo >= hi){
			System.out.println("Error discrete, lo >= hi");
			System.exit(0);
		}
		
		Random r = new Random();
		int d = r.nextInt(hi - lo + 1) + lo;
		return d;
	}
	
}
