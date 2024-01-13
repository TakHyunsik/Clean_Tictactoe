package Applications.Fivewood;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import AppInterfaces.BoardServices.ICheckSetableRuleService;
import AppInterfaces.BoardServices.IDistinctEndGameService;
import AppInterfaces.BoardServices.IGetBoardService;
import AppInterfaces.CommandService.IProcessCommandService;
import Applications.Fivewood.CheckSetableStoneFivewoodService;
import Applications.Fivewood.GetGameStateFivewoodService;
import Applications.Tictactoe.TestBoardStorage;
import Applications.Tictactoe.TestCommandStorage;
import Commons.PlayerType;
import Entities.FivewoodBoard;
import Repository.IBoardRepository;
import Repository.ICommandRepository;
import Usecases.GetBoardService;
import Usecases.ProcessCommandService;
import Usecases.SetStoneCommand;

class TestFivewood {
	IBoardRepository board_repo;
	ICommandRepository cmd_repo;
	IDistinctEndGameService distinct_service;
	ICheckSetableRuleService check_service;
	IProcessCommandService process_service;
	IGetBoardService get_board_service;

	@BeforeEach
	void setUp() throws Exception {
		board_repo = new TestBoardStorage(new FivewoodBoard());
		cmd_repo = new TestCommandStorage(null);
		check_service = new CheckSetableStoneFivewoodService(board_repo);
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

		// ?áæ?åÅ?ìÖ?Å∫Ôø?
		process_service.process(new SetStoneCommand(0, 0, PlayerType.P1, check_service, board_repo));
		assertEquals(PlayerType.None, distinct_service.get_winner());
		process_service.process(new SetStoneCommand(0, 1, PlayerType.P2, check_service, board_repo));
		assertEquals(PlayerType.None, distinct_service.get_winner());
		process_service.process(new SetStoneCommand(0, 2, PlayerType.P1, check_service, board_repo));
		assertEquals(PlayerType.None, distinct_service.get_winner());
		process_service.process(new SetStoneCommand(1, 0, PlayerType.P2, check_service, board_repo));
		assertEquals(PlayerType.None, distinct_service.get_winner());
	}
}
