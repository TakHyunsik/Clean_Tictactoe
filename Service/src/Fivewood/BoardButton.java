package Fivewood;

public class BoardButton extends javax.swing.JButton {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public int x, y;

	public BoardButton(int y, int x, String title) {
		super(title);
		this.x = x;
		this.y = y;

	}

}
