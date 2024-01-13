package Tictactoe;

import AppInterfaces.BoardServices.ICheckSetableRuleService;
import Commons.PlayerType;
import Repository.IBoardRepository;

public class CheckSetableStoneTictactoeService implements ICheckSetableRuleService {
	IBoardRepository repo;

	public CheckSetableStoneTictactoeService(IBoardRepository repo) {
		this.repo = repo;
	}

	@Override
	public boolean check_setable_point(int y, int x, PlayerType player) {
		return repo.load_board().check_setable_in_board(y, x);
	}
}
