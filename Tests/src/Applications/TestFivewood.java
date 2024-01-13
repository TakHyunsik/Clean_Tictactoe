package Applications;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import AppInterfaces.BoardServices.IDistinctEndGameService;
import AppInterfaces.BoardServices.IGetBoardService;
import AppInterfaces.CommandService.IProcessCommandService;
import Commons.PlayerType;
import Entities.FivewoodBoard;
import Fivewood.GetGameStateFivewoodService;
import Repository.IBoardRepository;
import Repository.ICommandRepository;
import Usecases.GetBoardService;
import Usecases.ProcessCommandService;
import Usecases.SetStoneCommand;

class TestFivewood {
	IBoardRepository board_repo;
	ICommandRepository cmd_repo;
	IDistinctEndGameService distinct_service;
	IProcessCommandService process_service;
	IGetBoardService get_board_service;

	@BeforeEach
	void setUp() throws Exception {
		board_repo = new TestBoardStorage(new FivewoodBoard());
		cmd_repo = new TestCommandStorage(null);
		distinct_service = new GetGameStateFivewoodService(board_repo);
		process_service = new ProcessCommandService(cmd_repo);
		get_board_service = new GetBoardService(board_repo);
	}

	@AfterEach
	void tearDown() throws Exception {
	}

	@Test
	public void testcheck_resultNoWinner() {

		// No winner, the result should be PlayerType.None
		assertEquals(PlayerType.None, distinct_service.get_winner());

		// 臾댁듅遺�
		process_service.process(new SetStoneCommand(0, 0, PlayerType.P1, board_repo));
		assertEquals(PlayerType.None, distinct_service.get_winner());
		process_service.process(new SetStoneCommand(0, 1, PlayerType.P2, board_repo));
		assertEquals(PlayerType.None, distinct_service.get_winner());
		process_service.process(new SetStoneCommand(0, 2, PlayerType.P1, board_repo));
		assertEquals(PlayerType.None, distinct_service.get_winner());
		process_service.process(new SetStoneCommand(1, 0, PlayerType.P2, board_repo));
		assertEquals(PlayerType.None, distinct_service.get_winner());
	}
}
