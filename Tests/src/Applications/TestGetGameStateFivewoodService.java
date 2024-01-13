package Applications;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import AppInterfaces.BoardServices.IDistinctEndGameService;
import AppInterfaces.BoardServices.IGetBoardService;
import AppInterfaces.CommandService.IProcessCommandService;
import Commons.PlayerType;
import Entities.FivewoodBoard;
import Entities.TicTacToeBoard;
import Fivewood.GetGameStateFivewoodService;
import Repository.IBoardRepository;
import Repository.ICommandRepository;
import Tictactoe.GetGameStateTictactoeService;
import Usecases.GetBoardService;
import Usecases.ProcessCommandService;
import Usecases.SetStoneCommand;

class TestGetGameStateFivewoodService {
	IBoardRepository board_repo;
	ICommandRepository cmd_repo;
	GetGameStateFivewoodService distinct_service;
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
		
		board_repo.update_board(10, 0, PlayerType.P1);
		board_repo.update_board(10, 1, PlayerType.P1);
		board_repo.update_board(10, 2, PlayerType.P1);
		board_repo.update_board(10, 3, PlayerType.P1);
		board_repo.update_board(10, 4, PlayerType.P1);
		assertEquals(PlayerType.P1, distinct_service.get_가로(10, 0));
	}
	
	@Test
	public void testcheck_resultNoWinner1() {
		
		board_repo.update_board(10, 0, PlayerType.P1);
		board_repo.update_board(10, 1, PlayerType.P2);
		board_repo.update_board(10, 2, PlayerType.P1);
		board_repo.update_board(10, 3, PlayerType.P1);
		board_repo.update_board(10, 4, PlayerType.P1);
		assertEquals(PlayerType.None,distinct_service.get_가로(10, 0));
	}
	@Test
	public void testcheck_resultNoWinner6() {
		
		board_repo.update_board(10, 0, PlayerType.P1);
		board_repo.update_board(10, 1, PlayerType.P1);
		board_repo.update_board(10, 2, PlayerType.P2);
		board_repo.update_board(10, 3, PlayerType.P1);
		board_repo.update_board(10, 4, PlayerType.P1);
		assertEquals(PlayerType.None,distinct_service.get_가로(10, 0));
	}
	@Test
	public void testcheck_resultNoWinner2() {
		
		board_repo.update_board(10, 0, PlayerType.P2);
		board_repo.update_board(10, 1, PlayerType.P2);
		board_repo.update_board(10, 2, PlayerType.P2);
		board_repo.update_board(10, 3, PlayerType.P2);
		board_repo.update_board(10, 4, PlayerType.P2);
		assertEquals(PlayerType.P2, distinct_service.get_가로(10, 0));
	}
	
	@Test
	public void testcheck_resultNoWinner3() {
		
		board_repo.update_board(10, 0, PlayerType.P1);
		board_repo.update_board(10, 2, PlayerType.P1);
		board_repo.update_board(10, 3, PlayerType.P1);
		board_repo.update_board(10, 4, PlayerType.P1);
		assertEquals(PlayerType.None,distinct_service.get_가로(10, 0));
	}
	
	@Test
	public void testcheck_resultNoWinner10() {
		
		board_repo.update_board(10, 0, PlayerType.P1);
		assertNotEquals(PlayerType.P1,distinct_service.get_가로(10, 0));
	}
	@Test
	public void testcheck_resultNoWinner300() {
		
		board_repo.update_board(10, 11, PlayerType.P1);
		board_repo.update_board(10, 12, PlayerType.P1);
		board_repo.update_board(10, 13, PlayerType.P1);
		board_repo.update_board(10, 14, PlayerType.P1);
		assertEquals(PlayerType.None,distinct_service.get_가로(10, 11));
	}

}
