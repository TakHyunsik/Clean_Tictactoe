package Tictactoe;

import AppInterfaces.BoardServices.ICheckSetableRuleService;
import AppInterfaces.CommandService.ICommand;
import Commons.PlayerType;
import Entities.TicTacToeBoard;
import Repository.IBoardRepository;

public class SetStoneCommand2 implements ICommand {
	TicTacToeBoard board;
	IBoardRepository _repo;
	ICheckSetableRuleService rule;
	boolean result;

	public SetStoneCommand2(int[][] ticArr, IBoardRepository repo) {
		rule = new CheckSetableStoneTictactoeService(repo);
		board = new TicTacToeBoard();
		for(int i=0; i<3; i++) {
			for(int j=0; j<3; j++) {
				System.out.print(ticArr[i][j]+" ");
			}
			System.out.println();
		}
		
		result = true;
	}

	/**
	 * 처음 세팅한 y,x 위치에 type을 board에 둔다.
	 * 
	 * @throws check_executable을 확인하지 않고 실행하면 Exception을 발생시킵니다. 확인하고 써주세요.
	 */
	@Override
	public void execute() throws Exception {
		if (result) {
			_repo.save_board(board);;
		} else {
			throw new Exception("Exception execute SetStoneCommand");
		}
	}

	/**
	 * 처음 세팅한 y,x 위치에 type을 board에 둔다.
	 * 
	 * @throws undo에서 throws가 발생했다는 것은 정말 큰일난 일입니다. 이거는 무르기 기능에 치명적입니다.
	 */
	@Override
	public void undo() throws Exception {
	}

	@Override
	public boolean check_executable() {
		// TODO Auto-generated method stub
		return result;
	}
}
