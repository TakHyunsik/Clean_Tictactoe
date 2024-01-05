package Entities;

import Commons.PlayerType;
import DomainInterfaces.IGameBoard;

public class FiveWoodBoard implements IGameBoard {
	private PlayerType[][] Board;

	public FiveWoodBoard() {
		Board = new PlayerType[this.get_size()][this.get_size()];
		for (int i = 0; i < this.get_size(); i++) {
			for (int j = 0; j < this.get_size(); j++) {
				Board[i][j] = PlayerType.None;
			}
		}
	}

	public FiveWoodBoard(PlayerType[][] board) {
		this.Board = board;
	}

	@Override
	public PlayerType get_in_board(int y, int x) {
		// TODO Auto-generated method stub
		return Board[y][x];
	}

	@Override
	public int get_size() {
		// TODO Auto-generated method stub
		return 15;
	}

	@Override
	public boolean set_in_board(int y, int x, PlayerType player) {
		// 둘수 있는지 체크하고 둔다.
		if (check_setable_in_board(y, x)) {
			Board[y][x] = player;
			return true;
		}
		return false;
	}

	@Override
	public boolean check_setable_in_board(int y, int x) {
		// TODO Auto-generated method stub
		// size를 넘은 곳
		if (y < 0 || y >= get_size() || x < 0 || x >= get_size()) {
			return false;
		}
		// 이미 둔 곳
		if (Board[y][x] != PlayerType.None) {
			return false;
		}
		// 전부 성공
		return true;
	}

	public PlayerType check_result() {
		if (Board[0][0] == PlayerType.P1 && Board[0][1] == PlayerType.P1 && Board[0][2] == PlayerType.P1) {
			return PlayerType.P1;
		} else if (Board[1][0] == PlayerType.P1 && Board[1][1] == PlayerType.P1 && Board[1][2] == PlayerType.P1) {
			return PlayerType.P1;
		} else if (Board[2][0] == PlayerType.P1 && Board[2][1] == PlayerType.P1 && Board[2][2] == PlayerType.P1) {
			return PlayerType.P1;
		}

		else if (Board[0][0] == PlayerType.P1 && Board[1][0] == PlayerType.P1 && Board[2][0] == PlayerType.P1) {
			return PlayerType.P1;
		} else if (Board[0][1] == PlayerType.P1 && Board[1][1] == PlayerType.P1 && Board[2][1] == PlayerType.P1) {
			return PlayerType.P1;
		} else if (Board[0][2] == PlayerType.P1 && Board[1][2] == PlayerType.P1 && Board[2][2] == PlayerType.P1) {
			return PlayerType.P1;
		}

		else if (Board[0][0] == PlayerType.P1 && Board[1][1] == PlayerType.P1 && Board[2][2] == PlayerType.P1) {
			return PlayerType.P1;
		} else if (Board[0][2] == PlayerType.P1 && Board[1][1] == PlayerType.P1 && Board[2][0] == PlayerType.P1) {
			return PlayerType.P1;
		}
		if (Board[0][0] == PlayerType.P2 && Board[0][1] == PlayerType.P2 && Board[0][2] == PlayerType.P2) {
			return PlayerType.P2;
		} else if (Board[1][0] == PlayerType.P2 && Board[1][1] == PlayerType.P2 && Board[1][2] == PlayerType.P2) {
			return PlayerType.P2;
		} else if (Board[2][0] == PlayerType.P2 && Board[2][1] == PlayerType.P2 && Board[2][2] == PlayerType.P2) {
			return PlayerType.P2;
		}

		else if (Board[0][0] == PlayerType.P2 && Board[1][0] == PlayerType.P2 && Board[2][0] == PlayerType.P2) {
			return PlayerType.P2;
		} else if (Board[0][1] == PlayerType.P2 && Board[1][1] == PlayerType.P2 && Board[2][1] == PlayerType.P2) {
			return PlayerType.P2;
		} else if (Board[0][2] == PlayerType.P2 && Board[1][2] == PlayerType.P2 && Board[2][2] == PlayerType.P2) {
			return PlayerType.P2;
		}

		else if (Board[0][0] == PlayerType.P2 && Board[1][1] == PlayerType.P2 && Board[2][2] == PlayerType.P2) {
			return PlayerType.P2;
		} else if (Board[0][2] == PlayerType.P2 && Board[1][1] == PlayerType.P2 && Board[2][0] == PlayerType.P2) {
			return PlayerType.P2;
		}
		// 비김
		if (this.check_be_full()) {
			return PlayerType.None;
		}
		return PlayerType.None;
	}

	@Override
	public int get_count() {
		int cnt = 0;
		for (int u = 0; u < get_size(); u++) {
			for (int y = 0; y < get_size(); y++) {
				if (Board[u][y] != PlayerType.None) {
					cnt++;
				}
			}
		}
		return cnt;
	}

	@Override
	public boolean check_empty() {
		// TODO Auto-generated method stub

		for (int u = 0; u < get_size(); u++) {
			for (int y = 0; y < get_size(); y++) {
				if (Board[u][y] != PlayerType.None) {
					return false;
				}
			}
		}
		return true;
	}

	@Override
	public boolean check_be_full() {
		// TODO Auto-generated method stub
		for (int i = 0; i < get_size(); i++) {
			for (int y = 0; y < get_size(); y++) {
				if (Board[i][y] == PlayerType.None) {
					return false;
				}
			}
		}

		return true;
	}

	@Override
	public IGameBoard copy() {
		PlayerType[][] new_board = new PlayerType[get_size()][get_size()];

		for (int i = 0; i < get_size(); i++) {
			new_board[i] = Board[i].clone();
		}
		return new FiveWoodBoard(new_board);
	}
}