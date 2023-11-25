package Interfaces;

import Commons.PlayerType;
import java.util.Iterator;

public interface IGetBoardService {
	public Iterator<Iterator<PlayerType>> get_all();
	public int get_size();
}
