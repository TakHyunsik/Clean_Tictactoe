package Usecases;

import AppInterfaces.PlayerControl.IPlayerAction;
import AppInterfaces.PlayerControl.IPlayerControlService;
import Commons.PlayerType;

public class TurnBasePlayerControlService implements IPlayerControlService {
	IPlayerAction p1, p2;
	PlayerType current_player;

	public TurnBasePlayerControlService(IPlayerAction p1, IPlayerAction p2, PlayerType frist=PlayerType.P1) {
		this.p1 = p1;
		this.p2 = p2;
		this.current_player = frist;
	}

	@Override
	public void change_player() {
		// TODO Auto-generated method stub
		if (current_player == PlayerType.P1) {
			current_player = PlayerType.P2;
		} else {
			current_player = PlayerType.P1;
		}
	}

	@Override
	public PlayerType get_current_player() {

	}

	@Override
	public IPlayerAction get_action() {
		// TODO Auto-generated method stub
		return null;
	}
}
