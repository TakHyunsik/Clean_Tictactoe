package Usecases;

import AppInterfaces.IGetBoardService;
import Commons.PlayerType;
import DomainInterfaces.IGameBoard;
import Repository.IBoardRepository;

public class GetBoardService implements IGetBoardService {
	IBoardRepository repo;
	int size;

	public GetBoardService(IBoardRepository repo) {
		this.repo = repo;
		this.size = repo.get_size();
	}

	public PlayerType[][] get_all() {
		IGameBoard board = repo.load_board();
		PlayerType[][] arr = new PlayerType[board.get_size()][board.get_size()];
		for (int i = 0; i < board.get_size(); i++) {
			for (int j = 0; j < board.get_size(); j++) {
				arr[i][j] = board.get_in_board(i, j);
			}
		}
		return arr;
	}

	@Override
	public int get_size() {
		return size;
	}
}