package Entities;

import Commons.PlayerType;
import Interfaces.IDistinctBoard;
import Interfaces.ISetableBoard;
import Interfaces.ISquareBoard;
import Interfaces.IEndCheckableBoard;

public class TicTacToeBoard implements IDistinctBoard, ISetableBoard, ISquareBoard, IEndCheckableBoard {
   private PlayerType[][] Board = new PlayerType[3][3];
 
   @Override
   public PlayerType get_in_board(int y, int x) {
      // TODO Auto-generated method stub
      return Board[y][x];
   }

   @Override
   public int get_size() {
      // TODO Auto-generated method stub
      return 3;
   }

   @Override
   public boolean set_in_board(int y, int x, PlayerType player) {
      // TODO Auto-generated method stub
      Board[y][x] = player;
      return true;
   }

   @Override
   public boolean check_setable_in_board(int y, int x) {
      // TODO Auto-generated method stub
      //이미 둔 곳
      //size를 넘은 곳
      if(y < 0 || y >= 3 || x < 0 || x >= 3) {
         return false;
      }
      if(Board[y][x] != PlayerType.None) {
         return false;
      }
      return false;
      
   }

   @Override
   public PlayerType check_result() {
      // TODO Auto-generated method stub
      return PlayerType.None;
   }

   @Override
   public boolean check_empty() {
      // TODO Auto-generated method stub
      return false;
   }

   @Override
   public boolean check_be_full() {
      // TODO Auto-generated method stub
      return false;
   }

}