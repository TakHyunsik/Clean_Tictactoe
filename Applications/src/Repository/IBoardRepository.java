package Repository;

import Commons.PlayerType;
import DomainInterfaces.IGameBoard;

public interface IBoardRepository {
	/**
	 * ������ GameBoard�� ��ȯ�Ѵ�. ��ȯ�� GameBoard�� ���ص� ������ ���ؼ��� �ȵȴ�.
	 * 
	 * @return
	 */
	public IGameBoard load_board();

	/**
	 * �������� �ѹ��� �ٲܶ� ����մϴ�. ������ board�� ������ ���� �ǵ��� ���� ����� �����մϴ�.
	 * 
	 * @param board
	 */
	public void save_board(IGameBoard board);

	public void update_board(int y, int x, PlayerType type);

	public int get_size();
}