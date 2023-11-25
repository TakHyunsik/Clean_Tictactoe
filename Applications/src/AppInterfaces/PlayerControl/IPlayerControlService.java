package AppInterfaces.PlayerControl;

import Commons.PlayerType;

public interface IPlayerControlService {
	public void change_player();

	public PlayerType get_current_player();

	public IPlayerAction get_action();
}
