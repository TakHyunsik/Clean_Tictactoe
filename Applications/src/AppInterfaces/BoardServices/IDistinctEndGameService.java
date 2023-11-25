package AppInterfaces.BoardServices;

import Commons.PlayerType;

public interface IDistinctEndGameService {
	/**
	 * ���ڰ� �������� ��ȯ�Ѵ�. check_end()�ʹ� ������ ����.
	 * 
	 * @return ���ڰ� �������� ��ȯ�Ѵ�. None�� ��� ���ڰ� ���� ���¶�� ���̴�.
	 */
	public PlayerType get_winner();

	/**
	 * ���� ��å�� ���� ������ ������ �� �ִ� ��Ȳ���� ��ȯ�Ѵ�. get_winner()�ʹ� ������ ����.
	 * 
	 * @return ����ؼ� ������ �� �� �ִ� ��Ȳ������ ��ȯ�Ѵ�.
	 */
	public boolean check_end();
}
