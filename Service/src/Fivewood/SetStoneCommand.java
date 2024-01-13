package Fivewood;

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
	public String str;

	public SetStoneCommand(int y, int x, PlayerType type, ICheckSetableRuleService rule, IBoardRepository repo) {
		_y = y;
		_x = x;
		_type = type;
		_repo = repo;
		this.rule = rule;
		result = rule.check_setable_point(_y, _x, _type);
		String str_type = (type == PlayerType.P1) ? "PlayerType.P1" : "PlayerType.P2";
		str = String.format("process_service.process(new SetStoneCommand(%d, %d, %s, check_service, board_repo));", y,
				x, str_type);
	}

	@Override
	public String ToSting() {
		return str;
	}

	/**
	 * ó�� ������ y,x ��ġ�� type�� board�� �д�.
	 * 
	 * @throws check_executable�� Ȯ������ �ʰ� �����ϸ� Exception�� �߻���ŵ�ϴ�. Ȯ���ϰ� ���ּ���.
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
	 * ó�� ������ y,x ��ġ�� type�� board�� �д�.
	 * 
	 * @throws undo���� throws�� �߻��ߴٴ� ���� ���� ū�ϳ� ���Դϴ�. �̰Ŵ� ������ ��ɿ� ġ�����Դϴ�.
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
