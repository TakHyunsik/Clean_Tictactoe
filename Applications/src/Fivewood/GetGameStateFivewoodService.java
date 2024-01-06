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
	// 5���� ���ӵȴ�. /,\,��,��
	// /, \ �� ��, �� �� ��������
	// /, \ �� xy ���� �ϳ��� ���ϳ��ų� ����
	
	// /�� x�� ���� ���� y�� ���� �����ְ� ����!
	// ������ �������� x�� - 1 y�� + 1 4�ݺ�
	
	// �Ѵ� y���� �� �����ְ� ���� y�� �ϳ��� �ø���
	
	// �Ӵ� x�� �� ū�ְ� ���� x�� �ϳ��� ���̱�
	
	// \�� x�� y���� �� ū�ְ� ����
	// x y �� �ϳ��� ���̱�
	public PlayerType get_winner() {
		IGameBoard board = repo.load_board();
		
	
	}

	@Override
	public boolean check_end() {
		// TODO Auto-generated method stub
		return repo.load_board().check_be_full();
	}
	public PlayerType get_����(int y, int x) {
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
