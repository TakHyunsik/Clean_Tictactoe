package Fivewood;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import AppInterfaces.BoardServices.IDistinctEndGameService;
import AppInterfaces.BoardServices.IGetBoardService;
import AppInterfaces.CommandService.IProcessCommandService;
import Commons.PlayerType;
import Entities.TicTacToeBoard;
import Repository.IBoardRepository;
import Repository.ICommandRepository;
import Tictactoe.GetGameStateTictactoeService;
import Usecases.GetBoardService;
import Usecases.ProcessCommandService;
import Usecases.SetStoneCommand;

public class GuiFivewoodView extends JFrame implements MouseListener {
	IBoardRepository board_repo;
	ICommandRepository cmd_repo;
	IDistinctEndGameService distinct_service;
	IProcessCommandService process_service;
	IGetBoardService get_board_service;

	// 1. ƽ������ �����ִ� �� ���� �������� �ٲٱ�
	// 2.

	private static final long serialVersionUID = 1L;
	private int SIZE;

	JLabel title = new JLabel("TicTacToe | ");
	JLabel dispCurrentPlayer = new JLabel("Player 0");
	private int score1 = 0;
	private int score2 = 0;
	JLabel scoreLabel = new JLabel(" | " + score1 + " : " + score2);
	JButton startNewGame = new JButton("�� ���� ����");

	JPanel titleBar = new JPanel();
	JPanel allRoom = new JPanel();

	private int START_PLAYER = 1;

	private boolean isGameEnd = false;

	public GuiFivewoodView() {
		super("TicTacToe");
		this.resetGame(1);

		// ���� ���Ŀ���
		board_repo = new FivewoodBoardStorage(new TicTacToeBoard());
		cmd_repo = new FivewoodCommandStorage(null);
		distinct_service = new GetGameStateTictactoeService(board_repo);
		process_service = new ProcessCommandService(cmd_repo);
		get_board_service = new GetBoardService(board_repo);

		SIZE = board_repo.get_size();

		// ȭ�� ������ �����ϰ� �����ϱ�
		this.setSize(400, 300);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);

		this.setWindow();
		this.setVisible(true);
		this.setActionOfNineRoom();
		this.setActionOfStartNewGame();
	}

	public void setWindow() {
		titleBar.add(title);
		titleBar.add(dispCurrentPlayer);
		titleBar.add(scoreLabel);
		titleBar.add(startNewGame);

		dispCurrentPlayer.setText("Player " + START_PLAYER);
		add(titleBar, BorderLayout.NORTH);

		allRoom.setLayout(new GridLayout(SIZE, SIZE));
		for (int i = 0; i < SIZE * SIZE; i++) {
			// ȭ�� ũ�� �����ϱ�
			JButton tempButton = new JButton("");
			tempButton.setFont(new Font("Impact", Font.PLAIN, 22));
			allRoom.add(tempButton);
		}

		add(allRoom, BorderLayout.CENTER);

	}

	public void setActionOfStartNewGame() {
		startNewGame.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
//				ttt.resetGame(START_PLAYER);
				isGameEnd = false;
				// �����ǿ� �°� ���� -
			}
		});
	}

	public void setActionOfAllRoom() {
		for (Component c : nineRoom.getComponents()) {
			c.addMouseListener(this);
		}
	}

	public void changeTurn() {
		currentPlayerNum = (currentPlayerNum == 1) ? 2 : 1;
		this.countTurn();
	}

	public void resetGame(int currentPlayerNum) {
		this.resetTurn();
		this.setCurrentPlayerNum(currentPlayerNum);
	}

	public int getCurrentPlayerNum() {
		return currentPlayerNum;
	}

	public void setCurrentPlayerNum(int currentPlayerNum) {
		this.currentPlayerNum = currentPlayerNum;
	}

	public void countTurn() {
		this.currentTurn++;
	}

	public void resetTurn() {
		this.currentTurn = 0;
	}

	public boolean isFullMap() {
		return (this.currentTurn == 16);
	}

	private int currentTurn = 1;

	private int currentPlayerNum;

	@Override
	public void mousePressed(MouseEvent e) {
		JButton tempButton = (JButton) e.getComponent();
		if (isGameEnd) {
			return;
		}
		if (tempButton.getText().equals("O") || tempButton.getText().equals("X")) {
			JOptionPane.showMessageDialog(allRoom, "�̹� �� ���Դϴ�.");
			return;
		} else if (getCurrentPlayerNum() == 1) {
			tempButton.setText("O");
			dispCurrentPlayer.setText("Player " + 2);
		} else {
			tempButton.setText("X");
			dispCurrentPlayer.setText("Player " + 1);
		}
		changeTurn();

		System.out.println("(" + e.getX() + ", " + e.getY() + ") ");

		// SetStoneCommand�� process_service�� ����ؼ� ���� �����Ҽ� �ֵ��� y, x, PlayerType �˾Ƴ���
		// y,x,type ä������
		int y, x;
		PlayerType type;

		// ������� �˾Ƴ���
		this.process_service.process(new SetStoneCommand(y, x, type, this.board_repo));

		PlayerType result = distinct_service.get_winner();
		System.out.println("result: " + result);
		if (result == PlayerType.P1 || result == PlayerType.P2) {
			JOptionPane.showMessageDialog(nineRoom, "�÷��̾� " + result + "�� �¸��Դϴ�.");
			if (result == PlayerType.P1) {
				score1++;
			} else {
				score2++;
			}
			scoreLabel.setText(" | " + score1 + " : " + score2);
			isGameEnd = true;
		} else if (result == PlayerType.None && distinct_service.check_end()) {
			JOptionPane.showMessageDialog(nineRoom, "�����ϴ�.");
			isGameEnd = true;
		}
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

	}
}