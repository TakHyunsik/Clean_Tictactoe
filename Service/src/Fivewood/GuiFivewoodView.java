package Fivewood;

import javax.swing.*;

import AppInterfaces.BoardServices.IDistinctEndGameService;
import AppInterfaces.BoardServices.IGetBoardService;
import AppInterfaces.CommandService.IProcessCommandService;

import java.awt.*;
import java.awt.event.*;
import Commons.PlayerType;
import Entities.FivewoodBoard;
import Repository.IBoardRepository;
import Repository.ICommandRepository;
import Usecases.GetBoardService;
import Usecases.ProcessCommandService;
import Usecases.SetStoneCommand;

public class GuiFivewoodView extends JFrame implements MouseListener {
	IBoardRepository board_repo;
	ICommandRepository cmd_repo;
	IDistinctEndGameService distinct_service;
	IProcessCommandService process_service;
	IGetBoardService get_board_service;

	private static final long serialVersionUID = 1L;
	private int SIZE;

	JLabel title = new JLabel("Fivewood | ");
	JLabel dispCurrentPlayer = new JLabel("Player 0");
	private int score1 = 0;
	private int score2 = 0;
	JLabel scoreLabel = new JLabel(" | " + score1 + " : " + score2);
	JButton startNewGame = new JButton("�� ���� ����");

	JPanel titleBar = new JPanel();
	JPanel AllRoom = new JPanel();

	private int START_PLAYER = 1;

	private boolean isGameEnd = false;

	public GuiFivewoodView() {
		super("Fivewood");
		this.resetGame(1);

		// ���� ��ġ��
		board_repo = new FivewoodBoardStorage(new FivewoodBoard());
		cmd_repo = new FivewoodCommandStorage(null);
		distinct_service = new GetGameStateFivewoodService(board_repo);
		process_service = new ProcessCommandService(cmd_repo);
		get_board_service = new GetBoardService(board_repo);

		SIZE = board_repo.get_size();

		// ȭ�� ������ �����ϰ� �����ϱ�

		this.setSize(800, 800);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);

		this.setWindow();
		this.setVisible(true);
		this.setActionOfAllRoom();
		this.setActionOfStartNewGame();
	}

	public void setWindow() {
		titleBar.add(title);
		titleBar.add(dispCurrentPlayer);
		titleBar.add(scoreLabel);
		titleBar.add(startNewGame);

		dispCurrentPlayer.setText("Player " + START_PLAYER);
		add(titleBar, BorderLayout.NORTH);

		AllRoom.setLayout(new GridLayout(SIZE, SIZE));
		for (int i = 0; i < SIZE * SIZE; i++) {
			// ȭ�� ũ�� �����ϱ�
			BoardButton tempButton = new BoardButton(i, i, "");
			tempButton.setFont(new Font("Impact", Font.PLAIN, 22));
			AllRoom.add(tempButton);

		}

		add(AllRoom, BorderLayout.CENTER);

	}

	public void setActionOfStartNewGame() {
		startNewGame.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// ttt.resetGame(START_PLAYER);
				isGameEnd = false;
				// �����ǿ� �°� ����
				for (int i = 0; i < AllRoom.getComponents().length; i++) {
					((BoardButton) AllRoom.getComponent(i)).setText("");
				}
			}
		});
	}

	public void setActionOfAllRoom() {
		for (Component c : AllRoom.getComponents()) {
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
		System.out.println("123");
		BoardButton tempButton = (BoardButton) e.getComponent();
		if (isGameEnd) {
			return;
		}
		if (tempButton.getText().equals("O") || tempButton.getText().equals("X")) {
			JOptionPane.showMessageDialog(AllRoom, "�̹� �� ���Դϴ�.");
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
		// SetStoneCommand�� process_service�� ����ؼ� ���� �����Ҽ� �ֵ��� y, x,
		// PlayerType �˾Ƴ���

		// y,x,type ä���
		int y = tempButton.y;
		int x = tempButton.x;
		PlayerType type = PlayerType.P1;

		// ������� �˾Ƴ���
		this.process_service.process(new SetStoneCommand(y, x, type, this.board_repo));
		PlayerType[][] board = this.get_board_service.get_all();
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				System.out.println(board[i][j]);
			}
		}
		PlayerType result = distinct_service.get_winner();
		System.out.println("result: " + result);
		if (result == PlayerType.P1 || result == PlayerType.P2) {
			JOptionPane.showMessageDialog(AllRoom, "�÷��̾� " + result + "�� �¸��Դϴ�.");
			if (result == PlayerType.P1) {
				score1++;
			} else {
				score2++;
			}
			scoreLabel.setText(" | " + score1 + " : " + score2);
			isGameEnd = true;
		} else if (result == PlayerType.None && distinct_service.check_end()) {
			JOptionPane.showMessageDialog(AllRoom, "�����ϴ�.");
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