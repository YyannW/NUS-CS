/**
 * Name: Wang Hongyanyan
 * Matric. No: A0239012R
 */

public class Constellations {
  public static void main(String[] args) {
    Kattio io = new Kattio(System.in);
    int totalNumOfStars = io.getInt();
    int minNum = io.getInt(); //minimum size of one constellation
    int maxNum = io.getInt(); // maximum size of one constellation
    int constant = 1000000007; //use %constant to address the issue of overflow
    long result = constellations(totalNumOfStars, minNum, maxNum, maxNum)%constant;
    System.out.println(result);
    io.close();
  }


  //pre condition: [max(1, totalNum/10) <= minSize <= max <= totalNum <= 50] && maxSize – minSize <= 5 && totalNum, maxSize, minSize are positive integers
  public static long constellations(int totalNum, int minSize, int curMaxSize, int max) {
    int constant = 1000000007; //use %constant to address the issue of overflow
    if (curMaxSize == minSize) { // base case: current maximum size == minimum size
      if (totalNum - curMaxSize < minSize) { // if num of stars - maxsize is less than minSize, then the remaining stars cannot form another constellation
        return choose(totalNum, minSize)%constant;
      } else if (totalNum - curMaxSize >= max) { // the remaining stars can form other constellations with max size being the original max size
        return (choose(totalNum, minSize)%constant * constellations(totalNum - curMaxSize, minSize, max, max) % constant)%constant;
      } else { // minSize <= totalNum-curMax <= max: the remaining stars can form other constellations with max size (being totalNum - curMax)
        return (choose(totalNum, minSize)%constant * constellations(totalNum - curMaxSize, minSize, totalNum-curMaxSize, max) % constant)%constant;
      }
    } else { // haven't reach base case
      if (totalNum - curMaxSize <= minSize) { //remaining stars cannot form another constellations or there is only 1 possible way to arrange the remaining stars
        return (constellations(totalNum, minSize, curMaxSize - 1, max)%constant + choose(totalNum, curMaxSize)%constant)%constant;
      } else { //remaining stars can form other constellations
        if (totalNum - curMaxSize < max) { 
          return (constellations(totalNum, minSize, curMaxSize - 1, max)%constant + choose(totalNum, curMaxSize)%constant * constellations(totalNum - curMaxSize, minSize, totalNum - curMaxSize, max) % constant)%constant;
        } else {
          return (constellations(totalNum, minSize, curMaxSize - 1, max)%constant + choose(totalNum, curMaxSize)%constant * constellations(totalNum - curMaxSize, minSize, max, max) % constant)%constant;
        }
      }
    }
  }
//post condition: return number of arrangements of all possible constellations


//pre condition: 0<=r<=n, r and n are positive integers
  public static long choose(int n, int r) {
    if (r > n) {
      return 0;
    } else {
      if (n - r < r) {
        r = n - r; // by the symmetry of binomial coefficient that nCr = nCn-r, which helps to reduce the iterative step to max operations = n/2
      }
      long result = 1;
      for (int i = 1, j = 0; i <= r; i++, j++) {
        result = result * (n-j)/ i;// to formulate n!/(r!(n-r)!) = n(n-1)(n-2)..(n-r+1)/(1*2*3...*r)
      }
      return result;
    }
  }
  //post condition: return the number of ways to choose r distinct objects from n distinct objects
}
