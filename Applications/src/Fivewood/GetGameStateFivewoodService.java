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
		 int size = board.get_size();
		 System.out.println("get_winner");
		 for (int y = 0; y < size; y++) {
		        for (int x = 0; x < size; x++) {
		        	if(board.get_in_board(y, x)!=PlayerType.None) {
		        		System.out.println("x,y,p "+" "+x+" "+y+" "+board.get_in_board(y, x));
		        		PlayerType winner = ����_Ȯ��(y, x);
			            if (winner != PlayerType.None) {
			                return winner;
			            }
		        	}
		        }
		    }

		    return PlayerType.None;
		}

	private PlayerType����_Ȯ��(

	int y,
	int x)
	{
		    PlayerType winner = get_����(y, x);
		    if (winner != PlayerType.None) {
		        return winner;
		    }

		    winner = get_����(y, x);
		    if (winner != PlayerType.None) {
		        return winner;
		    }

		    winner = get_���������밢��(y, x);
		    if (winner != PlayerType.None) {
		        return winner;
		    }

		    winner = get_�������밢��(y, x);
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

	public PlayerType get_����(
	int y,
	int x)
	{
		IGameBoard board = repo.load_board();
		PlayerType type = board.get_in_board(y, x);

		if (x >= board.get_size() - 4) {
			return PlayerType.None;
		}
		for (int i = 1; i < 5; i++) {
			// ������ ���� �α�
			if (board.get_in_board(y, x) == type && board.get_in_board(y, x + i) != type) {
				return PlayerType.None;

			}
		}

		return type;

	}
	public PlayerType get_����(
	int y,
	int x)
	{
		IGameBoard board = repo.load_board();
		PlayerType type = board.get_in_board(y, x);

		if (y <= board.get_size() - 12) {
			return PlayerType.None;
		}

		for (int q = 1; q < 5; q++) {
			if (board.get_in_board(y, x) == type && board.get_in_board(y - q, x) != type) {
				return PlayerType.None;
			}
		}
		return type;
	}
	public PlayerType get_���������밢��(
	int y,
	int x)
	{
		IGameBoard board = repo.load_board();
		PlayerType type = board.get_in_board(y, x);

		if (y <= board.get_size() - 12 || x >= board.get_size() - 4) {
			return PlayerType.None;
		}

		for (int w = 1; w < 5; w++) {
			if (board.get_in_board(y, x) == type && board.get_in_board(y - w, x + w) != type) {
				return PlayerType.None;
			}
		}
		return type;
	}
	public PlayerType get_�������밢��(
	int y,
	int x)
	{
		IGameBoard board = repo.load_board();
		PlayerType type = board.get_in_board(y, x);

		if (y <= board.get_size() - 12 || x <= board.get_size() - 12) {
			return PlayerType.None;
		}

		for (int a = 1; a < 5; a++) {
			if (board.get_in_board(y, x) == type && board.get_in_board(y - a, x - a) != type) {
				return PlayerType.None;
			}
		}
		return type;
	}

}
