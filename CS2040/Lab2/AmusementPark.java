/**
 * Name : Wang Hongyanyan
 * Matric. No : A0239012R
 */

import java.util.*;
@SuppressWarnings("unchecked")
public class AmusementPark {
  public static void main(String[] arg){
    int dayCounter = 0; //keep track of number of days => also indicates the index of sub-linkedlist
    LinkedList<LinkedList> schedule = new LinkedList<LinkedList>();// create new linkedlist to store all days' schedule
    Kattio io = new Kattio(System.in);
    while (io.hasMoreTokens()){ //pre condition: continue the while loop if there is more input String that havent been recognised
      String cmd = io.getWord();  // read command word from input
      if (cmd.equals("NEXT")){
        String nextWord = io.getWord();
        if (nextWord.equals("RIDE:")){
          int ride = io.getInt();
          addRide(schedule,dayCounter,ride);// add the new ride to the back of one day's schedule
        }
      } //since the "NEXT DAY" command only indicates a new day, there is no need to specially address this method

      else if(cmd.equals("DELETE")){
        String nextWord = io.getWord();
        io.getWord();
        int index = io.getInt();
        if(nextWord.equals("FRONT")){
          deleteFrontRide(schedule,dayCounter,index);
        }
        else {
          deleteBackRide(schedule,dayCounter,index);
        }
      }
      else if(cmd.equals("CHANGE")){
        String nextWord = io.getWord();
        io.getWord();
        int index = io.getInt();
        int ride = io.getInt();
        if (nextWord.equals("FRONT")){
          changeFront(schedule,dayCounter,index,ride);
        }
        else {
          changeBack(schedule,dayCounter,index,ride);
        }
      }
      else if(cmd.equals("END")){
        end(schedule,dayCounter);
      }
      else{ //cmd.euqals("START")=> start a new day=>new linkedlist to store the rides
        dayCounter++;// increment number of days
        io.getWord();
        int ride = io.getInt();
        LinkedList<Integer> day = new LinkedList<Integer>(); //create a new linkedlist day to store that particular day's records
        schedule.add(day); // add the linkedlist day into the linkedlist schedule, which is the records for all days
        addRide(schedule,dayCounter,ride); // add ride to the linkedlist
      }
    }
    io.close();
  }
  static void addRide(LinkedList<LinkedList> list, int dayCounter, int ride){
    list.get(dayCounter-1).add(ride);
  } //post condition: add new ride to the back of current linkedlist


  //pre condition for delete&change methods:
  //the input index must not be larger than the current size of one day's schedule(i.e. schedule.get(dayCounter-1).size()>=index)

  static void deleteFrontRide(LinkedList<LinkedList> list, int dayCounter, int index){//precondition: cannot delete more items than current size of linkedlist
    LinkedList<Integer> sublist = list.get(dayCounter-1);
    if (sublist.size()<index || index<1){
      System.out.println("Invalid command");
    } else{
      for (int i = 0;i<index;i++){
        list.get(dayCounter-1).removeFirst();
      }
    }
  }  //post condition: first x items are removed from the linkedlist
  static void deleteBackRide(LinkedList<LinkedList> list, int dayCounter, int index){
    LinkedList<Integer> sublist = list.get(dayCounter-1);
    if (sublist.size()<index || index<1){
      System.out.println("Invalid command");
    } else{
      for (int i = 0;i<index;i++){
        list.get(dayCounter-1).removeLast();
      }
    }
  }//post condition: last x items are removed from the linkedlist
  static void changeFront(LinkedList<LinkedList> list, int dayCounter, int index, int ride){
    if (list.get(dayCounter-1).size()<index|| index<1){
      System.out.println("Invalid command");
    } else{
      deleteFrontRide(list,dayCounter,index);
      list.get(dayCounter-1).addFirst(ride);
    }
  }//post condition: first x items are removed from the linkedlist, ride A is added to the front of the linkedlist
  static void changeBack(LinkedList<LinkedList> list, int dayCounter, int index, int ride){
    if (list.get(dayCounter-1).size()<index||index<1){
      System.out.println("Invalid command");
    }
    else{
      deleteBackRide(list,dayCounter,index);
      list.get(dayCounter-1).addLast(ride);
    }
  } //post condition: last x items are removed from the linkedlist, ride A is added to the back of the linkedlist
  static void end(LinkedList<LinkedList> list, int dayCounter){
    for (int i=0; i<dayCounter;i++){
      int dayNum = i+1;
      System.out.println("Day "+dayNum+": "+list.get(i));
    } // post condition: print out each day's rides by accessing from the whole schedule
  }
}
