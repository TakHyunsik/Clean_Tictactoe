package AppInterfaces;

import Commons.PlayerType;

public interface ICheckSetableRuleService {
	public boolean check_setable_point(int y, int x, PlayerType player);
}
