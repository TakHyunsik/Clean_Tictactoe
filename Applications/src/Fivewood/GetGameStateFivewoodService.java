package Fivewood;

import AppInterfaces.BoardServices.IDistinctEndGameService;
import Commons.PlayerType;
import DomainInterfaces.IGameBoard;
import Repository.IBoardRepository;

public class GetGameStateFivewoodService implements IDistinctEndGameService {

	IBoardRepository repo;

	public GetGameStateFivewoodService(IBoardRepository repo) {
		this.repo = repo;
	}

	@Override
	// 5개가 연속된다. /,\,ㅡ,ㅣ
	// /, \ 와 ㅣ, ㅡ 를 구분짓기
	// /, \ 는 xy 축을 하나씩 더하나거나 빼기
	
	// /는 x축 값이 높고 y축 값이 낮은애가 기준!
	// 기준을 기준으로 x축 - 1 y축 + 1 4반복
	
	// ㅡ는 y축이 젤 작은애가 기준 y축 하나씩 늘리기
	
	// ㅣ는 x축 젤 큰애가 기준 x축 하나씩 줄이기
	
	// \는 x축 y축이 젤 큰애가 기준
	// x y 축 하나씩 줄이기
	public PlayerType get_winner() {
		IGameBoard board = repo.load_board();
		 int size = board.get_size();
		 System.out.println("get_winner");
		 for (int y = 0; y < size; y++) {
		        for (int x = 0; x < size; x++) {
		        	if(board.get_in_board(y, x)!=PlayerType.None) {
		        		System.out.println("x,y,p "+" "+x+" "+y+" "+board.get_in_board(y, x));
		        		PlayerType winner = 패턴_확인(y, x);
			            if (winner != PlayerType.None) {
			                return winner;
			            }
		        	}
		        }
		    }

		    return PlayerType.None;
		}

		private PlayerType 패턴_확인(int y, int x) {
		    PlayerType winner = get_가로(y, x);
		    if (winner != PlayerType.None) {
		        return winner;
		    }

		    winner = get_세로(y, x);
		    if (winner != PlayerType.None) {
		        return winner;
		    }

		    winner = get_오른쪽위대각선(y, x);
		    if (winner != PlayerType.None) {
		        return winner;
		    }

		    winner = get_왼쪽위대각선(y, x);
		    if (winner != PlayerType.None) {
		        return winner;
		    }

		    return PlayerType.None;
		}

	@Override
	public boolean check_end() {
		// TODO Auto-generated method stub
		return repo.load_board().check_be_full();
	}
	public PlayerType get_가로(int y, int x) {
		IGameBoard board = repo.load_board();
		PlayerType type = board.get_in_board(y, x);
		
		if(x >= board.get_size() - 4) {
			return PlayerType.None;
		}
		for(int i = 1; i < 5; i++) {
			//사이즈 제한 두기
			if(board.get_in_board(y, x) == type && board.get_in_board(y, x + i) != type) {
				return PlayerType.None;
		
			}
		}
		
		return type;
		

	}
	public PlayerType get_세로(int y, int x) {
		IGameBoard board = repo.load_board();
		PlayerType type = board.get_in_board(y, x);
		
		if(y <= board.get_size() - 12) {
			return PlayerType.None;
		}
		
		for(int q = 1; q < 5; q++) {
			if(board.get_in_board(y, x) == type && board.get_in_board(y - q, x) != type) {
				return PlayerType.None;
			}
		}
		return type;
	}
	public PlayerType get_오른쪽위대각선(int y, int x) {
		IGameBoard board = repo.load_board();
		PlayerType type = board.get_in_board(y, x);
		
		if(y <= board.get_size() - 12 || x >= board.get_size() - 4) {
			return PlayerType.None;
		}
		
		for(int w = 1; w < 5; w++) {
			if(board.get_in_board(y, x) == type && board.get_in_board(y - w, x + w) != type) {
				return PlayerType.None;
			}
		}
		return type;
	}
	public PlayerType get_왼쪽위대각선(int y, int x) {
		IGameBoard board = repo.load_board();
		PlayerType type = board.get_in_board(y, x);
		
		if(y <= board.get_size() - 12 || x <= board.get_size() - 12) {
			return PlayerType.None;
		}
		
		for(int a = 1; a < 5; a++) {
			if(board.get_in_board(y, x) == type && board.get_in_board(y - a, x - a) != type) {
				return PlayerType.None;
			}
		}
		return type;
	}
	
}
