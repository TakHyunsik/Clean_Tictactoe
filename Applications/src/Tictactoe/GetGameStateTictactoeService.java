package Tictactoe;

import AppInterfaces.IDistinctEndGameService;
import Commons.PlayerType;
import DomainInterfaces.IGameBoard;
import Repository.IBoardRepository;

public class GetGameStateTictactoeService implements IDistinctEndGameService {

	IBoardRepository repo;

	public GetGameStateTictactoeService(IBoardRepository repo) {
		this.repo = repo;
	}

	@Override
	public PlayerType get_winner() {
		IGameBoard board = repo.load_board();
		if (board.get_in_board(0, 0) == PlayerType.P1 && board.get_in_board(0, 1) == PlayerType.P1
				&& board.get_in_board(0, 2) == PlayerType.P1) {
			return PlayerType.P1;
		} else if (board.get_in_board(1, 0) == PlayerType.P1 && board.get_in_board(1, 1) == PlayerType.P1
				&& board.get_in_board(1, 2) == PlayerType.P1) {
			return PlayerType.P1;
		} else if (board.get_in_board(2, 0) == PlayerType.P1 && board.get_in_board(2, 1) == PlayerType.P1
				&& board.get_in_board(2, 2) == PlayerType.P1) {
			return PlayerType.P1;
		}

		else if (board.get_in_board(0, 0) == PlayerType.P1 && board.get_in_board(1, 0) == PlayerType.P1
				&& board.get_in_board(2, 0) == PlayerType.P1) {
			return PlayerType.P1;
		} else if (board.get_in_board(0, 1) == PlayerType.P1 && board.get_in_board(1, 1) == PlayerType.P1
				&& board.get_in_board(2, 1) == PlayerType.P1) {
			return PlayerType.P1;
		} else if (board.get_in_board(0, 2) == PlayerType.P1 && board.get_in_board(1, 2) == PlayerType.P1
				&& board.get_in_board(2, 2) == PlayerType.P1) {
			return PlayerType.P1;
		}

		else if (board.get_in_board(0, 0) == PlayerType.P1 && board.get_in_board(1, 1) == PlayerType.P1
				&& board.get_in_board(2, 2) == PlayerType.P1) {
			return PlayerType.P1;
		} else if (board.get_in_board(0, 2) == PlayerType.P1 && board.get_in_board(1, 1) == PlayerType.P1
				&& board.get_in_board(2, 0) == PlayerType.P1) {
			return PlayerType.P1;
		}
		if (board.get_in_board(0, 0) == PlayerType.P2 && board.get_in_board(0, 1) == PlayerType.P2
				&& board.get_in_board(0, 2) == PlayerType.P2) {
			return PlayerType.P2;
		} else if (board.get_in_board(1, 0) == PlayerType.P2 && board.get_in_board(1, 1) == PlayerType.P2
				&& board.get_in_board(1, 2) == PlayerType.P2) {
			return PlayerType.P2;
		} else if (board.get_in_board(2, 0) == PlayerType.P2 && board.get_in_board(2, 1) == PlayerType.P2
				&& board.get_in_board(2, 2) == PlayerType.P2) {
			return PlayerType.P2;
		}

		else if (board.get_in_board(0, 0) == PlayerType.P2 && board.get_in_board(1, 0) == PlayerType.P2
				&& board.get_in_board(2, 0) == PlayerType.P2) {
			return PlayerType.P2;
		} else if (board.get_in_board(0, 1) == PlayerType.P2 && board.get_in_board(1, 1) == PlayerType.P2
				&& board.get_in_board(2, 1) == PlayerType.P2) {
			return PlayerType.P2;
		} else if (board.get_in_board(0, 2) == PlayerType.P2 && board.get_in_board(1, 2) == PlayerType.P2
				&& board.get_in_board(2, 2) == PlayerType.P2) {
			return PlayerType.P2;
		}

		else if (board.get_in_board(0, 0) == PlayerType.P2 && board.get_in_board(1, 1) == PlayerType.P2
				&& board.get_in_board(2, 2) == PlayerType.P2) {
			return PlayerType.P2;
		} else if (board.get_in_board(0, 2) == PlayerType.P2 && board.get_in_board(1, 1) == PlayerType.P2
				&& board.get_in_board(2, 0) == PlayerType.P2) {
			return PlayerType.P2;
		}
		// ºñ±è
		if (board.check_be_full()) {
			return PlayerType.None;
		}
		return PlayerType.None;
	}

	@Override
	public boolean check_end() {
		// TODO Auto-generated method stub
		return repo.load_board().check_be_full();
	}

}
