package Fivewood;

import AppInterfaces.BoardServices.ICheckSetableRuleService;
import AppInterfaces.CommandService.ICommand;
import Commons.PlayerType;
import Repository.IBoardRepository;
import Tictactoe.CheckSetableStoneTictactoeService;

public class SetStoneCommand implements ICommand {
	int _y, _x;
	PlayerType _type;
	IBoardRepository _repo;
	ICheckSetableRuleService rule;
	boolean result;
	public String str;

	public SetStoneCommand(int y, int x, PlayerType type, IBoardRepository repo) {
		_y = y;
		_x = x;
		_type = type;
		_repo = repo;
		rule = new CheckSetableStoneTictactoeService(repo);
		result = rule.check_setable_point(_y, _x, _type);
		String str_type = (type == PlayerType.P1) ? "PlayerType.P1" : "PlayerType.P2";
		str = String.format("process_service.process(new SetStoneCommand(%d, %d, %s, board_repo));", y, x, str_type);
	}

	@Override
	public String ToSting() {
		return str;
	}

	/**
	 * 처음 세팅한 y,x 위치에 type을 board에 둔다.
	 * 
	 * @throws check_executable을 확인하지 않고 실행하면 Exception을 발생시킵니다. 확인하고 써주세요.
	 */
	@Override
	public void execute() throws Exception {
		if (result) {
			_repo.update_board(_y, _x, _type);
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
		if (result) {
			_repo.update_board(_y, _x, PlayerType.None);
		} else {
			throw new Exception("Exception undo SetStoneCommand");
		}
	}

	@Override
	public boolean check_executable() {
		// TODO Auto-generated method stub
		return result;
	}
}
