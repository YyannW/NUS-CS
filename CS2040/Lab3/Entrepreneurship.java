/**
 * Name: Wang Hongyanyan
 * Matric. No:A0239012R
 */


import java.text.DecimalFormat;
import java.util.*;
@SuppressWarnings("unchecked")
public class Entrepreneurship {
  public static void main(String args[]) {
    Kattio io = new Kattio(System.in);
    int batchNum = io.getInt();
    int maxPizza = io.getInt();
    Stack pizzaCount = new Stack();
    Stack pizzaPrize = new Stack();
    Double totalRevenue = 0.0;
    Double pizzas = 0.0;
    for (int i = 0; i < batchNum; i++) {
      String cmd = io.getWord();
      if (cmd.equals("CANCEL")) {
        int n = io.getInt();
        for (int j = 0; j < n; j++) {
          Double num = (Double) pizzaCount.peek();
          pizzas = pizzas - num;
          pizzaCount.pop();
          pizzaPrize.pop();
        }
      } else {
        int numOrder = io.getInt();
        String direction = io.getWord();
        Double[] pNum = new Double[numOrder];
        Double [] pValue = new Double[numOrder];
        //create sublist
        for (int j = 0; j < numOrder; j++) {
          pNum[j] = io.getDouble();
          pValue[j] = io.getDouble();
        }
        if (direction.equals("L")) {
          Double cValue = 0.0;
          Double cCount = 0.0;
          for (int j = 0; j < numOrder; j++) {
            if (pizzas + pNum[j] <= maxPizza) {
              cCount = cCount + pNum[j];
              cValue = cValue + pNum[j] * pValue[j];
              pizzas = pizzas+pNum[j];
            }
            else{
            continue;
            }
          }
          pizzaCount.push(cCount);
          pizzaPrize.push(cValue);
        } else {
          Double cValue = 0.0;
          Double cCount = 0.0;
          for (int j = numOrder - 1; j >= 0; j--) {
            if (pizzas + pNum[j] <= maxPizza) {
              cCount = cCount + pNum[j];
              cValue = cValue + pNum[j] * pValue[j];
              pizzas = pizzas+pNum[j];
            }
            else{continue;}
          }
          pizzaCount.push(cCount);
          pizzaPrize.push(cValue);
        }
      }
    }
    while (pizzaPrize.size()>0) {
      totalRevenue = totalRevenue + (Double)pizzaPrize.pop();
    }
    String revenue = String.format("%.1f",totalRevenue);
    System.out.println(revenue);
  }
}
