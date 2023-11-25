package DomainInterfaces;

import Commons.PlayerType;

public interface IGetableBoard {
	public PlayerType get_in_board(int y, int x);
	public boolean check_already_set_in_board(int y, int x);
}
