package DomainInterfaces;

import Commons.PlayerType;

public interface ISetableBoard {
	public boolean set_in_board(int y, int x, PlayerType player);
}
