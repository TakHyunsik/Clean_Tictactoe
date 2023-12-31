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
		
	
	}

	@Override
	public boolean check_end() {
		// TODO Auto-generated method stub
		return repo.load_board().check_be_full();
	}
	public PlayerType get_가로(int y, int x) {
		IGameBoard board = repo.load_board();
		PlayerType type = board.get_in_board(y, x);
		for(int i = 1; i < 5; i++) {
			
			if(board.get_in_board(y, x) == type && board.get_in_board(y, x + i) != type) {
				return PlayerType.None;
		//		return PlayerType.P1;
			}
//			else if(board.get_in_board(y, x) == type && board.get_in_board(y, x + i) != type) {
//				return PlayerType.None;
		//		return PlayerType.P2;
			//}
			
				
			
			
			
			
		}
		
		return type;
	}
}
