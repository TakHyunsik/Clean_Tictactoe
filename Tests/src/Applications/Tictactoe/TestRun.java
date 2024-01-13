package Applications.Tictactoe;

import AppInterfaces.BoardServices.IDistinctEndGameService;
import AppInterfaces.BoardServices.IGetBoardService;
import AppInterfaces.CommandService.IProcessCommandService;
import Applications.Tictactoe.GetGameStateTictactoeService;
import Commons.PlayerType;
import Entities.TicTacToeBoard;
import Repository.IBoardRepository;
import Repository.ICommandRepository;
import Usecases.GetBoardService;
import Usecases.ProcessCommandService;
import Usecases.SetStoneCommand;

public class TestRun {
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		IBoardRepository board_repo;
		ICommandRepository cmd_repo;
		IDistinctEndGameService distinct_service;
		IProcessCommandService process_service;
		IGetBoardService get_board_service;

		board_repo = new TestBoardStorage(new TicTacToeBoard());
		cmd_repo = new TestCommandStorage(null);
		distinct_service = new GetGameStateTictactoeService(board_repo);
		process_service = new ProcessCommandService(cmd_repo);
		get_board_service = new GetBoardService(board_repo);

		process_service.process(new SetStoneCommand(0, 0, PlayerType.P2, board_repo));
		process_service.process(new SetStoneCommand(0, 1, PlayerType.P2, board_repo));
		process_service.process(new SetStoneCommand(0, 2, PlayerType.P2, board_repo));

		PlayerType[][] board = get_board_service.get_all();
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				System.out.print(board[i][j] + " ");
			}
			System.out.println();
		}
	}

}
