package Tictactoe;

import javax.swing.*;

import AppInterfaces.BoardServices.IDistinctEndGameService;
import AppInterfaces.BoardServices.IGetBoardService;
import AppInterfaces.CommandService.IProcessCommandService;

import java.awt.*;
import java.awt.event.*;
import Commons.PlayerType;
import Entities.TicTacToeBoard;
import Repository.IBoardRepository;
import Repository.ICommandRepository;
import Usecases.GetBoardService;
import Usecases.ProcessCommandService;


public class CliTicTacToeView extends JFrame implements MouseListener {
	IBoardRepository board_repo;
	ICommandRepository cmd_repo;
	IDistinctEndGameService distinct_service;
	IProcessCommandService process_service;
	IGetBoardService get_board_service;

	

	private static final long serialVersionUID = 1L;
	private static final int SIZE = 3;

	JLabel title = new JLabel("TicTacToe | ");
	JLabel dispCurrentPlayer = new JLabel("Player 0");
	private int score1 = 0;
	private int score2 = 0;
	JLabel scoreLabel = new JLabel(" | " + score1 + " : " + score2);
	JButton startNewGame = new JButton("새 게임 시작");

	JPanel titleBar = new JPanel();
	JPanel nineRoom = new JPanel();

	private int START_PLAYER = 1;
	 
	private boolean isGameEnd = false;

	public CliTicTacToeView() {
		super("TicTacToe");
		this.resetGame(1);
		
		board_repo = new TestBoardStorage(new TicTacToeBoard());
		cmd_repo = new TestCommandStorage(null);
		distinct_service = new GetGameStateTictactoeService(board_repo);
		process_service = new ProcessCommandService(cmd_repo);
		get_board_service = new GetBoardService(board_repo);
		
		this.setSize(400,300);
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

		nineRoom.setLayout(new GridLayout(SIZE,SIZE));
		for(int i = 0; i < SIZE*SIZE; i++) {
			JButton tempButton = new JButton("");
			tempButton.setFont(new Font("Impact", Font.PLAIN, 22));
			nineRoom.add(tempButton);

		}

		add(nineRoom, BorderLayout.CENTER);

	}

	public void setActionOfStartNewGame() {
		startNewGame.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
//				ttt.resetGame(START_PLAYER);
				isGameEnd = false;
				for(int i = 0; i < nineRoom.getComponents().length; i++) {
					((JButton)nineRoom.getComponent(i)).setText("");
				}

			}
		});
	}
	public void setActionOfNineRoom() {
		for(Component c : nineRoom.getComponents()) {
			c.addMouseListener(this);
		}
	}
	public void changeTurn() {
		currentPlayerNum = (currentPlayerNum == 1) ? 2 :
			1;
		this.countTurn();
	}
	public void resetGame(int currentPlayerNum) {
		this.resetTurn();
		this.setCurrentPlayerNum(currentPlayerNum);
	}

	public int getCurrentPlayerNum() {
		return currentPlayerNum;
	}

	public void setCurrentPlayerNum(int currentPlayerNum)
	{
		this.currentPlayerNum = currentPlayerNum;
	}
	
	public void countTurn() {
		this.currentTurn++;
	}
	public void resetTurn() {
		this.currentTurn=0;
	}
	public boolean isFullMap() {
		return (this.currentTurn==16);
	}
	
	private int currentTurn = 1;

	private int currentPlayerNum;


	@Override
	public void mousePressed(MouseEvent e) {
		JButton tempButton = (JButton)e.getComponent();
		if(isGameEnd) {
			return;
		}
		if(tempButton.getText().equals("O") || tempButton.getText().equals("X")) {
			JOptionPane.showMessageDialog(nineRoom, "이미 둔 곳입니다.");
			return;
		}
		else if(getCurrentPlayerNum() == 1) {
			tempButton.setText("O");
			dispCurrentPlayer.setText("Player " + 2);
		}
		else {
			tempButton.setText("X");
			dispCurrentPlayer.setText("Player " + 1);
		}
		changeTurn();
		
		System.out.println("(" + e.getX() + ", " + e.getY() + ") ");

		int[][] ticArr = new int[SIZE][SIZE];
		for(int i = 0; i < ticArr.length; i++) {
			for(int j = 0; j < ticArr[i].length; j++) {
				String pl = ((JButton)nineRoom.getComponent(j + i * SIZE)).getText();
				if(pl.equals("O"))   ticArr[i][j] = 1;
				else if(pl.equals("X"))   ticArr[i][j] = 2;
				else   ticArr[i][j] = 0;
			}               
		}
		
		this.process_service.process(new SetStoneCommand2(ticArr,this.board_repo));
		PlayerType[][] board = this.get_board_service.get_all();
		for(int i = 0; i < 3; i++) {
			for(int j = 0; j < 3; j++) {
				System.out.println(board[i][j]);
			}
		}
		PlayerType result = distinct_service.get_winner();
		System.out.println("result: " + result);
		if(result == PlayerType.P1 || result == PlayerType.P2) {
			JOptionPane.showMessageDialog(nineRoom, "플레이어 " + result + "의 승리입니다.");
			if(result == PlayerType.P1) {
				score1++;
			} else {
				score2++;
			}
			scoreLabel.setText(" | " + score1 + " : " + score2);
			isGameEnd = true;
		} else if (result == PlayerType.None && distinct_service.check_end()) {
			JOptionPane.showMessageDialog(nineRoom, "비겼습니다.");
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