package AppInterfaces.BoardServices;

import Commons.PlayerType;

public interface ICheckSetableRuleService {
	/**
	 * ���� ��å�� ���� �Ѽ� �ִ��� �ƴ����� ��ȯ�Ѵ�.
	 * 
	 * @param y
	 * @param x
	 * @param player
	 * @return �Ѽ� ������ ���� ���θ� ������ ��ȯ�Ѵ�.
	 */
	public boolean check_setable_point(int y, int x, PlayerType player);
}
