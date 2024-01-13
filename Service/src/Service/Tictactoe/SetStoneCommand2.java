package Service.Tictactoe;

import AppInterfaces.BoardServices.ICheckSetableRuleService;
import AppInterfaces.CommandService.ICommand;
import Applications.Tictactoe.CheckSetableStoneTictactoeService;
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
		_repo = repo;
		board = new TicTacToeBoard();
		for(int i=0; i<3; i++) {
			for(int j=0; j<3; j++) {
				if(ticArr[i][j] == 1) {
					board.set_in_board(i, j, PlayerType.P1);
				}
				else if(ticArr[i][j] == 2) {
					board.set_in_board(i, j, PlayerType.P2);
				}
				else {
					board.set_in_board(i, j, PlayerType.None);
				}
			}
		}
		
		result = true;
	}

	/**
	 * ó�� ������ y,x ��ġ�� type�� board�� �д�.
	 * 
	 * @throws check_executable�� Ȯ������ �ʰ� �����ϸ� Exception�� �߻���ŵ�ϴ�. Ȯ���ϰ� ���ּ���.
	 */
	@Override
	public void execute() throws Exception {
		if (result) {
			_repo.save_board(board);
		} else {
			throw new Exception("Exception execute SetStoneCommand");
		}
	}

	/**
	 * ó�� ������ y,x ��ġ�� type�� board�� �д�.
	 * 
	 * @throws undo���� throws�� �߻��ߴٴ� ���� ���� ū�ϳ� ���Դϴ�. �̰Ŵ� ������ ��ɿ� ġ�����Դϴ�.
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
