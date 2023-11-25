package AppInterfaces.BoardServices;

import Commons.PlayerType;

public interface ICheckSetableRuleService {
	/**
	 * 게임 정책에 따라 둘수 있는지 아닌지를 반환한다.
	 * 
	 * @param y
	 * @param x
	 * @param player
	 * @return 둘수 있으면 참을 못두면 거짓을 반환한다.
	 */
	public boolean check_setable_point(int y, int x, PlayerType player);
}
