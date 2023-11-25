package DomainInterfaces;

import Commons.PlayerType;

public interface IEndCheckableBoard {
	public PlayerType check_result();
	public boolean check_empty();
	public boolean check_be_full();
}
