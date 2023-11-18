package Interfaces;

import Commons.PlayerType;
import java.util.Iterator;

public interface IGetFromBoard {
	public Iterator<Iterator<PlayerType>> get_all();
	public int get_size();
}
