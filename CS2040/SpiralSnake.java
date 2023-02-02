import java.util.Scanner;

/**
 * Name : Wang Hongyanyan
 * Matric. No : A0239012R
 */
import java.io.*;
import java.util.*;

public class SpiralSnake {
  public static void main(String args[]) {
    Kattio io = new Kattio(System.in);
    int step = 0;
    int n = io.getInt();//vertical height
    int m = io.getInt();//horizontal length
    int [][] direction = {{0,1},{1,0},{0,-1},{-1,0}};//corresponding to right,down,left,up
    int numOfDirectionChange = 0;
    int cRow = 0;
    int cColumn = 0;
    char [][] matrix = new char[n][m];
    for (int i=0; i<n;i++){ //create the matrix to store grids
      String line = io.getWord();
      for (int j = 0;j<m;j++) {
        matrix[i][j] = line.charAt(j);
      }}

    for (int k =0;k<n*m;k++){
      step++;
      if (matrix[cRow][cColumn] == 'X'){
        System.out.println("Apple at (" + cColumn +", "+cRow+") eaten at step "+step);
      }
      matrix[cRow][cColumn] = '#';
      int directionV = direction[numOfDirectionChange%4][0];
      int directionH = direction[numOfDirectionChange%4][1];
      int nextR = cRow+directionV;
      int nextC = cColumn+directionH;
      if (nextC>=m || nextR>=n || nextC<0 ||nextR<0){
        numOfDirectionChange++;
        directionV = direction[numOfDirectionChange%4][0];
        directionH = direction[numOfDirectionChange%4][1];
        cRow = cRow+directionV;
        cColumn = cColumn+directionH;
      } else if (matrix[nextR][nextC]=='#') {
        numOfDirectionChange++;
        directionV = direction[numOfDirectionChange%4][0];
        directionH = direction[numOfDirectionChange%4][1];
        cRow = cRow+directionV;
        cColumn = cColumn+directionH;
      } else{
        cRow = nextR;
        cColumn = nextC;}
      }

io.close();
    }


    }

