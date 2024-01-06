package Fivewood;

import Commons.PlayerType;
import DomainInterfaces.IGameBoard;
import Entities.FivewoodBoard;
import Entities.TicTacToeBoard;
import Repository.IBoardRepository;

public class FivewoodBoardStorage implements IBoardRepository {

	IGameBoard board;

	public FivewoodBoardStorage(FivewoodBoard FivewoodBoard) {
		this.board = FivewoodBoard;
	}

	@Override
	public IGameBoard load_board() {
		return board.copy();
	}

	@Override
	public void save_board(IGameBoard board) {
		this.board = board;
	}

	@Override
	public void update_board(int y, int x, PlayerType type) {
		this.board.set_in_board(y, x, type);
	}

	@Override
	public int get_size() {
		return board.get_size();
	}

}
