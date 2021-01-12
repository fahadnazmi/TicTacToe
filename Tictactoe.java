package LHD;

import java.util.Scanner;
import java.util.Random;
import java.util.List;
import java.util.Arrays;
import java.util.ArrayList;

public class Tictactoe {

    static ArrayList<Integer> playerpositions = new ArrayList<Integer>();
    static ArrayList<Integer> comppositions = new ArrayList<Integer>();
    public static void main(String[] args) {
        
        char[][] GameBoard = {{' ', '|',' ','|',' '},
                              {'-', '+','-','+','-'},
                              {' ', '|',' ','|',' '},
                              {'-', '+','-','+','-'},
                              {' ', '|',' ','|',' '}
                            };
        printGameBoard(GameBoard);

        
        while(true) {
            Scanner scan = new Scanner(System.in);
        System.out.println("Enter your move(1-9): ");
        int PlayerPos = scan.nextInt();
        while(playerpositions.contains(PlayerPos) || comppositions.contains(PlayerPos)){
            System.out.println("Position Taken! Enter a correct position");
            PlayerPos = scan.nextInt();
        }

        placepiece(GameBoard, PlayerPos, "Player");
        String result = CheckWinner();
        if(result.length()>0){
            System.out.println(result);
            break;
        }

        Random rand= new Random();
        int CompPos = rand.nextInt(9) + 1; 
        while(playerpositions.contains(CompPos) || comppositions.contains(CompPos)){
            CompPos = rand.nextInt(9) + 1;
        }      
        placepiece(GameBoard, CompPos, "Comp");
        printGameBoard(GameBoard);

        result = CheckWinner();
        if(result.length()>0){
            System.out.println(result);
            break;
        }
       
        }
    }

    
 public static void printGameBoard(char[][] GameBoard)
 {
    for(char[] row : GameBoard) {
        for(char c : row)
        {
            System.out.print(c);
        }
        System.out.println();
    }
 }

 public static void placepiece(char[][] GameBoard, int pos, String user)
  {   char symbol = ' ';
      
  if(user.equals("Player")){
      symbol='X';
      playerpositions.add(pos);
  }
  else if(user.equals("Comp")){
         symbol = 'O';
      comppositions.add(pos);
     } 
    switch(pos)
    {
        case 1:
            GameBoard[0][0] = symbol;
            break;
        case 2:
            GameBoard[0][2] = symbol;
            break;
        case 3:
            GameBoard[0][4] = symbol;
            break;
        case 4:
            GameBoard[2][0] = symbol;
            break;
        case 5:
            GameBoard[2][2] = symbol;
            break;
        case 6:
            GameBoard[2][4] = symbol;
            break;
        case 7:
            GameBoard[4][0] = symbol;
            break;
        case 8:
            GameBoard[4][2] = symbol;
            break;
        case 9:
            GameBoard[4][4] = symbol;
            break;
        default: System.out.println("Invalid Position, Try Again");
                 break;
    }
  }

  public static String CheckWinner(){
      
    List toprow = Arrays.asList(1,2,3);
    List midrow = Arrays.asList(4,5,6);
    List bottomrow = Arrays.asList(7,8,9);
    List leftcol = Arrays.asList(1,4,7);
    List midcol = Arrays.asList(2,5,8);
    List rightcol = Arrays.asList(3,6,9);
    List tbdiag = Arrays.asList(1,5,9);
    List btdiag = Arrays.asList(3,5,7);

    List<List> winningConditions = new ArrayList<List>();
    winningConditions.add(toprow);
    winningConditions.add(midrow);
    winningConditions.add(bottomrow);
    winningConditions.add(leftcol);
    winningConditions.add(midcol);
    winningConditions.add(rightcol);
    winningConditions.add(tbdiag);
    winningConditions.add(btdiag);
    
    for(List l : winningConditions){
        if(playerpositions.containsAll(l)){
            return "Congratulations, You WON!";
        }
        else if(comppositions.containsAll(l)){
            return "CPU wins Better Luck Next Time :(";
        }
        else if (playerpositions.size()+comppositions.size() == 9){
            return "It's a TIE";
        }
    }

      return "";
  }
 }