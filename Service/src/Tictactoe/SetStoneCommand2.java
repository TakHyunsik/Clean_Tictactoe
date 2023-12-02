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
	 * ó�� ������ y,x ��ġ�� type�� board�� �д�.
	 * 
	 * @throws check_executable�� Ȯ������ �ʰ� �����ϸ� Exception�� �߻���ŵ�ϴ�. Ȯ���ϰ� ���ּ���.
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
