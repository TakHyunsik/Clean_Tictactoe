package Repository;

import Commons.PlayerType;
import DomainInterfaces.IGameBoard;

public interface IBoardRepository {
	/**
	 * 원본의 GameBoard를 반환한다. 반환된 GameBoard가 변해도 원본이 변해서는 안된다.
	 * 
	 * @return
	 */
	public IGameBoard load_board();

	/**
	 * 여러개를 한번에 바꿀때 사용합니다. 원본과 board는 별개의 것이 되도록 깊은 복사로 복사합니다.
	 * 
	 * @param board
	 */
	public void save_board(IGameBoard board);

	public void update_board(int y, int x, PlayerType type);

	public int get_size();
}