package Fivewood;

import Commons.PlayerType;
import DomainInterfaces.IGameBoard;
import Entities.FiveWoodBoard;
import Repository.IBoardRepository;

public class FivewoodBoardStorage implements IBoardRepository {

	IGameBoard board;

	public FivewoodBoardStorage(FiveWoodBoard board) {
		this.board = board;
	}

	@Override
	public IGameBoard load_board() {
		return board.copy();
	}

	@Override
	public void save_board(IGameBoard board) {
		for (int i = 0; i < board.get_size(); i++) {
			for (int j = 0; j < board.get_size(); j++) {
				this.board.set_in_board(i, j, board.get_in_board(i, j));
			}
		}
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
