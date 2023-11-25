package AppInterfaces.BoardServices;

import Commons.PlayerType;

public interface IDistinctEndGameService {
	/**
	 * 승자가 누구인지 반환한다. check_end()와는 연관이 없다.
	 * 
	 * @return 승자가 누구인지 반환한다. None인 경우 승자가 없는 상태라는 뜻이다.
	 */
	public PlayerType get_winner();

	/**
	 * 게임 정책에 따라 게임을 진행할 수 있는 상황인지 반환한다. get_winner()와는 연관이 없다.
	 * 
	 * @return 계속해서 착수를 할 수 있는 상황인지를 반환한다.
	 */
	public boolean check_end();
}
