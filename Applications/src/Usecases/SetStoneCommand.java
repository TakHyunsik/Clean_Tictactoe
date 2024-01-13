package Usecases;

import AppInterfaces.BoardServices.ICheckSetableRuleService;
import AppInterfaces.CommandService.ICommand;
import Commons.PlayerType;
import Repository.IBoardRepository;

public class SetStoneCommand implements ICommand {
	int _y, _x;
	PlayerType _type;
	IBoardRepository _repo;
	ICheckSetableRuleService rule;
	boolean result;

	public SetStoneCommand(int y, int x, PlayerType type, ICheckSetableRuleService rule, IBoardRepository repo) {
		_y = y;
		_x = x;
		_type = type;
		_repo = repo;
		this.rule = rule;
		result = rule.check_setable_point(_y, _x, _type);
	}

	/**
	 * 처음 세팅한 y,x 위치에 type을 board에 둔다.
	 * 
	 * @throws check_executable을 확인하지 않고 실행하면 Exception을 발생시킵니다. 확인하고 써주세요.
	 */
	@Override
	public void execute() throws Exception {
		System.out.println("execute 1" + result);
		if (result) {
			System.out.println("execute 2");
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
