package Fivewood;

import AppInterfaces.BoardServices.ICheckSetableRuleService;
import Commons.PlayerType;
import Repository.IBoardRepository;

public class CheckSetableStoneFivewoodService implements ICheckSetableRuleService {
	IBoardRepository repo;

	public CheckSetableStoneFivewoodService(IBoardRepository repo) {
		this.repo = repo;
	}

	@Override
	public boolean check_setable_point(int y, int x, PlayerType player) {
		System.out.println(y+","+x+"check_setable_point"+repo.load_board().check_setable_in_board(y, x));
		return repo.load_board().check_setable_in_board(y, x);
	}
}
