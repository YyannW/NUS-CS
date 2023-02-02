/**
 * Name: Wang Hongyanyan
 * Matric. No: A0239012R
 */
import java.util.*;
@SuppressWarnings("unchecked")
public class Macarons {
  public static void main(String args[]) {
    Kattio io = new Kattio(System.in); // precondition: number of macarons, the number on each macarons, and divisor must be positive integer
    Long macarons = io.getLong(); // totle number of macarons in the sequence
    Long divisor = io.getLong(); // divisor to determine whether a subsequence of macarons can be eaten
    HashMap<Long, Long> map = new HashMap(); // hashmap to keep track the number of occurance of each remainder
    Long remainder = 0L; //to record the remainder of the prefix sum
    for (Long i=0L; i<macarons; i++) {
      remainder = (long)((remainder % divisor + io.getInt() % divisor) % divisor);
      if (map.containsKey(remainder)) { // if the hashmap contains the key, then value+1 to incremnet the number of occurance
        Long curCount = map.get(remainder)+1;
        map.remove(remainder);
        map.put(remainder, curCount);
      } else { // add the new remainder into the map with occurance of 1
        map.put(remainder, 1L);
      }
    }
    System.out.println(countingMacarons(map, divisor));
    io.close();
  }
  //precondition: the key and value of map are long type, and divisor must be positive integer that <=1000000
  public static Long countingMacarons(HashMap map, Long divisor) {
    Long counter = 0L; //keep track of number of subsequence
    for (Long j = 0L; j< divisor; j++) {
      if (j == 0L && map.get(0L) != null) { // if the prefix sum is divisble by divisor
        counter = counter +(long)map.get(j)*((long)map.get(j)+1)/2; // is equivalent to count the number of subarray of an arry size of map.get(0)
      } else {
        if (map.get(j) != null && (long)map.get(j)>=2) { // the difference any two prefix sum of same remainder will be divisible by divisor
          Long k = (long)map.get(j);
          counter = counter + k*(k-1)/2; // choose 2 from map.get(j) to form a difference
        }
      }
    }
    return counter;
  } // post condition: return the number of subsequence of macarons that can be eaten
}