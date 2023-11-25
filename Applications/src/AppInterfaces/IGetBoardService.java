package AppInterfaces;

import Commons.PlayerType;

public interface IGetBoardService {
	public PlayerType[][] get_all();

	public int get_size();
}
