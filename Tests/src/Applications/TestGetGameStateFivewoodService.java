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
	public void testcheck_resultNoWinner301() {
		
		board_repo.update_board(10, 10, PlayerType.P1);
		board_repo.update_board(10, 11, PlayerType.P1);
		board_repo.update_board(10, 12, PlayerType.P1);
		board_repo.update_board(10, 13, PlayerType.P1);
		board_repo.update_board(10, 14, PlayerType.P1);
		assertEquals(PlayerType.P1,distinct_service.get_가로(10, 10));
	}
	@Test
	public void testcheck_resultNoWinner300() {
		
		board_repo.update_board(10, 11, PlayerType.P1);
		board_repo.update_board(10, 12, PlayerType.P1);
		board_repo.update_board(10, 13, PlayerType.P1);
		board_repo.update_board(10, 14, PlayerType.P1);
		assertEquals(PlayerType.None,distinct_service.get_가로(10, 11));
	}
	
	@Test
	public void testcheck_세로1() {
		
		board_repo.update_board(10, 10, PlayerType.P1);
		board_repo.update_board(9, 10, PlayerType.P1);
		board_repo.update_board(8, 10, PlayerType.P1);
		board_repo.update_board(7, 10, PlayerType.P1);
		board_repo.update_board(6, 10, PlayerType.P1);
		assertEquals(PlayerType.P1,distinct_service.get_세로(10, 10));
	}
	
	@Test
	public void testcheck_세로2() {
		
		board_repo.update_board(10, 10, PlayerType.P1);
		board_repo.update_board(9, 10, PlayerType.P2);
		board_repo.update_board(8, 10, PlayerType.P1);
		board_repo.update_board(7, 10, PlayerType.P1);
		board_repo.update_board(6, 10, PlayerType.P1);
		assertEquals(PlayerType.None,distinct_service.get_세로(10, 10));
	}
	
	@Test
	public void testcheck_세로3() {
		
		board_repo.update_board(10, 10, PlayerType.P1);
		board_repo.update_board(9, 10, PlayerType.P1);
		board_repo.update_board(8, 10, PlayerType.P2);
		board_repo.update_board(7, 10, PlayerType.P1);
		board_repo.update_board(6, 10, PlayerType.P1);
		assertEquals(PlayerType.None,distinct_service.get_세로(10, 10));
	}
	
	@Test
	public void testcheck_세로4() {
		
		board_repo.update_board(10, 10, PlayerType.P1);
		board_repo.update_board(9, 10, PlayerType.P1);
		board_repo.update_board(8, 10, PlayerType.P1);
		board_repo.update_board(7, 10, PlayerType.P1);
		
		assertEquals(PlayerType.None,distinct_service.get_세로(10, 10));
	}
	
	@Test
	public void testcheck_세로5() {
		
		board_repo.update_board(10, 10, PlayerType.P2);
		board_repo.update_board(9, 10, PlayerType.P2);
		board_repo.update_board(8, 10, PlayerType.P2);
		board_repo.update_board(7, 10, PlayerType.P2);
		board_repo.update_board(6, 10, PlayerType.P2);
		assertEquals(PlayerType.P2,distinct_service.get_세로(10, 10));
	}
	
	@Test
	public void testcheck_세로6() {
		
		board_repo.update_board(3, 10, PlayerType.P1);
		board_repo.update_board(2, 10, PlayerType.P1);
		board_repo.update_board(1, 10, PlayerType.P1);
		board_repo.update_board(0, 10, PlayerType.P1);
		assertEquals(PlayerType.None,distinct_service.get_세로(3, 10));
	}
	@Test
	public void testcheck_세로7() {
		board_repo.update_board(4, 10, PlayerType.P1);
		board_repo.update_board(3, 10, PlayerType.P1);
		board_repo.update_board(2, 10, PlayerType.P1);
		board_repo.update_board(1, 10, PlayerType.P1);
		board_repo.update_board(0, 10, PlayerType.P1);
		assertEquals(PlayerType.P1,distinct_service.get_세로(4, 10));
	}

	@Test
	public void testcheck_오위대1() {
		
		board_repo.update_board(6, 6, PlayerType.P1);
		board_repo.update_board(5, 7, PlayerType.P1);
		board_repo.update_board(4, 8, PlayerType.P1);
		board_repo.update_board(3, 9, PlayerType.P1);
		board_repo.update_board(2, 10, PlayerType.P1);
		assertEquals(PlayerType.P1,distinct_service.get_오른쪽위대각선(6, 6));
	}

	@Test
	public void testcheck_오위대2() {
		
		board_repo.update_board(6, 6, PlayerType.P1);
		board_repo.update_board(5, 7, PlayerType.P2);
		board_repo.update_board(4, 8, PlayerType.P1);
		board_repo.update_board(3, 9, PlayerType.P1);
		board_repo.update_board(2, 10, PlayerType.P1);
		assertEquals(PlayerType.None,distinct_service.get_오른쪽위대각선(6, 6));
	}

	@Test
	public void testcheck_오위대3() {
		
		board_repo.update_board(6, 6, PlayerType.P1);
		board_repo.update_board(5, 7, PlayerType.P1);
		board_repo.update_board(4, 8, PlayerType.P2);
		board_repo.update_board(3, 9, PlayerType.P1);
		board_repo.update_board(2, 10, PlayerType.P1);
		assertEquals(PlayerType.None,distinct_service.get_오른쪽위대각선(6, 6));
	}
	
	@Test
	public void testcheck_오위대4() {
		
		board_repo.update_board(6, 6, PlayerType.P1);
		board_repo.update_board(5, 7, PlayerType.P1);
		board_repo.update_board(4, 8, PlayerType.P1);
		board_repo.update_board(3, 9, PlayerType.P1);
		
		assertEquals(PlayerType.None,distinct_service.get_오른쪽위대각선(6, 6));
	}
	
	@Test
	public void testcheck_오위대5() {
		
		board_repo.update_board(6, 6, PlayerType.P2);
		board_repo.update_board(5, 7, PlayerType.P2);
		board_repo.update_board(4, 8, PlayerType.P2);
		board_repo.update_board(3, 9, PlayerType.P2);
		board_repo.update_board(2, 10, PlayerType.P2);
		assertEquals(PlayerType.P2,distinct_service.get_오른쪽위대각선(6, 6));
	}
	
	@Test
	public void testcheck_오위대6() {
		
		board_repo.update_board(3, 0, PlayerType.P1);
		board_repo.update_board(2, 1, PlayerType.P1);
		board_repo.update_board(1, 2, PlayerType.P1);
		board_repo.update_board(0, 3, PlayerType.P1);
		
		assertEquals(PlayerType.None,distinct_service.get_오른쪽위대각선(3, 0));
	}
	@Test
	public void testcheck_오위대8() {
		
		board_repo.update_board(3, 3, PlayerType.P1);
		board_repo.update_board(2, 4, PlayerType.P1);
		board_repo.update_board(1, 5, PlayerType.P1);
		board_repo.update_board(0, 6, PlayerType.P1);
		
		assertEquals(PlayerType.None,distinct_service.get_오른쪽위대각선(3, 3));
	}
	@Test
	public void testcheck_오위대9() {
		
		board_repo.update_board(6, 0, PlayerType.P1);
		board_repo.update_board(5, 1, PlayerType.P1);
		board_repo.update_board(4, 2, PlayerType.P1);
		board_repo.update_board(3, 3, PlayerType.P1);
		
		assertEquals(PlayerType.None,distinct_service.get_오른쪽위대각선(6, 0));
	}
	@Test
	public void testcheck_오위대10() {
		board_repo.update_board(4, 0, PlayerType.P1);
		board_repo.update_board(3, 1, PlayerType.P1);
		board_repo.update_board(2, 2, PlayerType.P1);
		board_repo.update_board(1, 3, PlayerType.P1);
		board_repo.update_board(0, 4, PlayerType.P1);
		
		assertEquals(PlayerType.P1,distinct_service.get_오른쪽위대각선(4, 0));
	}
	
	@Test
	public void testcheck_오위대7() {
		
		board_repo.update_board(4, 13, PlayerType.P1);
		board_repo.update_board(3, 14, PlayerType.P1);
		
		assertEquals(PlayerType.None,distinct_service.get_오른쪽위대각선(4, 13));
	}


	
	@Test
	public void testcheck_왼위대1() {
		
		board_repo.update_board(6, 6, PlayerType.P1);
		board_repo.update_board(5, 5, PlayerType.P1);
		board_repo.update_board(4, 4, PlayerType.P1);
		board_repo.update_board(3, 3, PlayerType.P1);
		board_repo.update_board(2, 2, PlayerType.P1);
		assertEquals(PlayerType.P1,distinct_service.get_왼쪽위대각선(6, 6));
	}
	
	@Test
	public void testcheck_왼위대2() {
		
		board_repo.update_board(6, 6, PlayerType.P1);
		board_repo.update_board(5, 5, PlayerType.P2);
		board_repo.update_board(4, 4, PlayerType.P1);
		board_repo.update_board(3, 3, PlayerType.P1);
		board_repo.update_board(2, 2, PlayerType.P1);
		assertEquals(PlayerType.None,distinct_service.get_왼쪽위대각선(6, 6));
	}
	
	@Test
	public void testcheck_왼위대3() {
		
		board_repo.update_board(6, 6, PlayerType.P1);
		board_repo.update_board(5, 5, PlayerType.P1);
		board_repo.update_board(4, 4, PlayerType.P1);
		board_repo.update_board(3, 3, PlayerType.P2);
		board_repo.update_board(2, 2, PlayerType.P1);
		assertEquals(PlayerType.None,distinct_service.get_왼쪽위대각선(6, 6));
	}

	@Test
	public void testcheck_왼위대4() {
		
		board_repo.update_board(6, 6, PlayerType.P1);
		board_repo.update_board(5, 5, PlayerType.P1);
		board_repo.update_board(4, 4, PlayerType.P1);
		board_repo.update_board(3, 3, PlayerType.P1);
		
		assertEquals(PlayerType.None,distinct_service.get_왼쪽위대각선(6, 6));
	}

	@Test
	public void testcheck_왼위대5() {
		
		board_repo.update_board(6, 6, PlayerType.P2);
		board_repo.update_board(5, 5, PlayerType.P2);
		board_repo.update_board(4, 4, PlayerType.P2);
		board_repo.update_board(3, 3, PlayerType.P2);
		board_repo.update_board(2, 2, PlayerType.P2);
		assertEquals(PlayerType.P2,distinct_service.get_왼쪽위대각선(6, 6));
	}
	
	@Test
	public void testcheck_왼위대6() {
		
		board_repo.update_board(3, 4, PlayerType.P1);
		board_repo.update_board(2, 3, PlayerType.P1);
		board_repo.update_board(1, 2, PlayerType.P1);
		board_repo.update_board(0, 1, PlayerType.P1);
		
		assertEquals(PlayerType.None,distinct_service.get_왼쪽위대각선(3, 4));
	}
	
	@Test
	public void testcheck_왼위대7() {
		
		board_repo.update_board(10, 3, PlayerType.P1);
		board_repo.update_board(9, 2, PlayerType.P1);
		board_repo.update_board(8, 1, PlayerType.P1);
		board_repo.update_board(7, 0, PlayerType.P1);
		
		assertEquals(PlayerType.None,distinct_service.get_왼쪽위대각선(10, 3));
	}@Test
	public void testcheck_왼위대8() {
		
		board_repo.update_board(4, 4, PlayerType.P1);
		board_repo.update_board(3, 3, PlayerType.P1);
		board_repo.update_board(2, 2, PlayerType.P1);
		board_repo.update_board(1, 1, PlayerType.P1);
		board_repo.update_board(0, 0, PlayerType.P1);
		assertEquals(PlayerType.P1,distinct_service.get_왼쪽위대각선(4, 4));
	}
	
}
