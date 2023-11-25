package AppInterfaces;

import Commons.PlayerType;

public interface IDistinctEndGameService {
	public PlayerType get_winner();
	public boolean check_full_board();
}
