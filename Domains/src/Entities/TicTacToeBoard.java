package Entities;

import Commons.PlayerType;
import DomainInterfaces.IDistinctBoard;
import DomainInterfaces.IEndCheckableBoard;
import DomainInterfaces.ISetableBoard;
import DomainInterfaces.ISquareBoard;

public class TicTacToeBoard implements IDistinctBoard, ISetableBoard, ISquareBoard, IEndCheckableBoard {
   private PlayerType[][] Board;

   public TicTacToeBoard() {
      Board = new PlayerType[this.get_size()][this.get_size()];
      for (int i = 0; i < this.get_size(); i++) {
         for (int j = 0; j < this.get_size(); j++) {
            Board[i][j] = PlayerType.None;
         }
      }
   }

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
      // 둘수 있는지 체크하고 둔다.
      if (check_setable_in_board(y, x)) {
         Board[y][x] = player;
         return true;
      }
      return false;
   }

   @Override
   public boolean check_setable_in_board(int y, int x) {
      // TODO Auto-generated method stub
      // size를 넘은 곳
      if (y < 0 || y >= get_size() || x < 0 || x >= get_size()) {
         return false;
      }
      // 이미 둔 곳
      if (Board[y][x] != PlayerType.None) {
         return false;
      }
      // 전부 성공
      return true;

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