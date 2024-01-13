package Applications.Tictactoe;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import AppInterfaces.BoardServices.ICheckSetableRuleService;
import AppInterfaces.BoardServices.IDistinctEndGameService;
import AppInterfaces.BoardServices.IGetBoardService;
import AppInterfaces.CommandService.IProcessCommandService;
import Commons.PlayerType;
import Entities.TicTacToeBoard;
import Repository.IBoardRepository;
import Repository.ICommandRepository;
import Tictactoe.CheckSetableStoneTictactoeService;
import Tictactoe.GetGameStateTictactoeService;
import Usecases.GetBoardService;
import Usecases.ProcessCommandService;
import Usecases.SetStoneCommand;

class TestTictactoe {
	IBoardRepository board_repo;
	ICommandRepository cmd_repo;
	IDistinctEndGameService distinct_service;
	ICheckSetableRuleService check_service;
	IProcessCommandService process_service;
	IGetBoardService get_board_service;

	@BeforeEach
	void setUp() throws Exception {
		board_repo = new TestBoardStorage(new TicTacToeBoard());
		cmd_repo = new TestCommandStorage(null);
		check_service = new CheckSetableStoneTictactoeService(board_repo);
		distinct_service = new GetGameStateTictactoeService(board_repo);
		process_service = new ProcessCommandService(cmd_repo);
		get_board_service = new GetBoardService(board_repo);
	}

	@AfterEach
	void tearDown() throws Exception {
	}

	@Test
	public void testcheck_resultNoWinner() {
		// -------
		// |O|X|O|
		// -------
		// |X|O|X|
		// -------
		// |X|O|X|
		// -------

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
		process_service.process(new SetStoneCommand(1, 1, PlayerType.P1, check_service, board_repo));
		assertEquals(PlayerType.None, distinct_service.get_winner());
		process_service.process(new SetStoneCommand(1, 2, PlayerType.P2, check_service, board_repo));
		assertEquals(PlayerType.None, distinct_service.get_winner());
		process_service.process(new SetStoneCommand(2, 0, PlayerType.P2, check_service, board_repo));
		assertEquals(PlayerType.None, distinct_service.get_winner());
		process_service.process(new SetStoneCommand(2, 1, PlayerType.P1, check_service, board_repo));
		assertEquals(PlayerType.None, distinct_service.get_winner());
		process_service.process(new SetStoneCommand(2, 2, PlayerType.P2, check_service, board_repo));
		// The game is a tie, the result should be PlayerType.None
		assertEquals(PlayerType.None, distinct_service.get_winner());
		assertTrue(distinct_service.check_end());
	}

	@Test
	public void testcheck_resultNoWinner1() {
		// -------
		// |O|O|X|
		// -------
		// |X|O|O|
		// -------
		// |O|X|X|
		// -------

		// No winner, the result should be PlayerType.None
		assertEquals(PlayerType.None, distinct_service.get_winner());

		// ?áæ?åÅ?ìÖ?Å∫Ôø?
		process_service.process(new SetStoneCommand(0, 0, PlayerType.P1, check_service, board_repo));
		assertEquals(PlayerType.None, distinct_service.get_winner());
		process_service.process(new SetStoneCommand(0, 1, PlayerType.P1, check_service, board_repo));
		assertEquals(PlayerType.None, distinct_service.get_winner());
		process_service.process(new SetStoneCommand(0, 2, PlayerType.P2, check_service, board_repo));
		assertEquals(PlayerType.None, distinct_service.get_winner());
		process_service.process(new SetStoneCommand(1, 0, PlayerType.P2, check_service, board_repo));
		assertEquals(PlayerType.None, distinct_service.get_winner());
		process_service.process(new SetStoneCommand(1, 1, PlayerType.P1, check_service, board_repo));
		assertEquals(PlayerType.None, distinct_service.get_winner());
		process_service.process(new SetStoneCommand(1, 2, PlayerType.P1, check_service, board_repo));
		assertEquals(PlayerType.None, distinct_service.get_winner());
		process_service.process(new SetStoneCommand(2, 0, PlayerType.P1, check_service, board_repo));
		assertEquals(PlayerType.None, distinct_service.get_winner());
		process_service.process(new SetStoneCommand(2, 1, PlayerType.P2, check_service, board_repo));
		assertEquals(PlayerType.None, distinct_service.get_winner());
		process_service.process(new SetStoneCommand(2, 2, PlayerType.P2, check_service, board_repo));
		// The game is a tie, the result should be PlayerType.None
		assertEquals(PlayerType.None, distinct_service.get_winner());
		assertTrue(distinct_service.check_end());
	}

	@Test
	public void testcheck_resultHorizontalWinner1() {
		// -------
		// |O|O|O|
		// -------
		// |X|X| |
		// -------
		// | |X| |
		// -------
		process_service.process(new SetStoneCommand(0, 0, PlayerType.P1, check_service, board_repo));

		process_service.process(new SetStoneCommand(0, 1, PlayerType.P1, check_service, board_repo));

		process_service.process(new SetStoneCommand(0, 2, PlayerType.P1, check_service, board_repo));
		// Player 1 wins horizontally, the result should be PlayerType.P1
		assertEquals(PlayerType.P1, distinct_service.get_winner());
		process_service.process(new SetStoneCommand(1, 0, PlayerType.P2, check_service, board_repo));
		assertEquals(PlayerType.P1, distinct_service.get_winner());
		process_service.process(new SetStoneCommand(1, 1, PlayerType.P2, check_service, board_repo));
		assertEquals(PlayerType.P1, distinct_service.get_winner());
		process_service.process(new SetStoneCommand(2, 1, PlayerType.P2, check_service, board_repo));
		assertEquals(PlayerType.P1, distinct_service.get_winner());
	}

	@Test
	public void testcheck_resultNoWinner2() {
		// -------
		// |X|O|X|
		// -------
		// |X|O|X|
		// -------
		// |O|X|O|
		// -------

		// No winner, the result should be PlayerType.None
		assertEquals(PlayerType.None, distinct_service.get_winner());

		// ?áæ?åÅ?ìÖ?Å∫Ôø?
		process_service.process(new SetStoneCommand(0, 0, PlayerType.P2, check_service, board_repo));
		assertEquals(PlayerType.None, distinct_service.get_winner());
		process_service.process(new SetStoneCommand(0, 1, PlayerType.P1, check_service, board_repo));
		assertEquals(PlayerType.None, distinct_service.get_winner());
		process_service.process(new SetStoneCommand(0, 2, PlayerType.P2, check_service, board_repo));
		assertEquals(PlayerType.None, distinct_service.get_winner());
		process_service.process(new SetStoneCommand(1, 0, PlayerType.P2, check_service, board_repo));
		assertEquals(PlayerType.None, distinct_service.get_winner());
		process_service.process(new SetStoneCommand(1, 1, PlayerType.P1, check_service, board_repo));
		assertEquals(PlayerType.None, distinct_service.get_winner());
		process_service.process(new SetStoneCommand(1, 2, PlayerType.P2, check_service, board_repo));
		assertEquals(PlayerType.None, distinct_service.get_winner());
		process_service.process(new SetStoneCommand(2, 0, PlayerType.P1, check_service, board_repo));
		assertEquals(PlayerType.None, distinct_service.get_winner());
		process_service.process(new SetStoneCommand(2, 1, PlayerType.P2, check_service, board_repo));
		assertEquals(PlayerType.None, distinct_service.get_winner());
		process_service.process(new SetStoneCommand(2, 2, PlayerType.P1, check_service, board_repo));
		// The game is a tie, the result should be PlayerType.None
		assertEquals(PlayerType.None, distinct_service.get_winner());
		assertTrue(distinct_service.check_end());
	}

	@Test
	public void testcheck_resultHorizontalWinner2() {
		// -------
		// |X| |X|
		// -------
		// |O|O|O|
		// -------
		// |X|X| |
		// -------
		process_service.process(new SetStoneCommand(1, 0, PlayerType.P1, check_service, board_repo));

		process_service.process(new SetStoneCommand(1, 1, PlayerType.P1, check_service, board_repo));

		process_service.process(new SetStoneCommand(1, 2, PlayerType.P1, check_service, board_repo));
		// Player 1 wins horizontally, the result should be PlayerType.P1
		assertEquals(PlayerType.P1, distinct_service.get_winner());
		process_service.process(new SetStoneCommand(2, 2, PlayerType.P2, check_service, board_repo));
		assertEquals(PlayerType.P1, distinct_service.get_winner());
		process_service.process(new SetStoneCommand(2, 0, PlayerType.P2, check_service, board_repo));
		assertEquals(PlayerType.P1, distinct_service.get_winner());
		process_service.process(new SetStoneCommand(0, 1, PlayerType.P2, check_service, board_repo));
		assertEquals(PlayerType.P1, distinct_service.get_winner());
		process_service.process(new SetStoneCommand(0, 0, PlayerType.P2, check_service, board_repo));
		assertEquals(PlayerType.P1, distinct_service.get_winner());
	}

	@Test
	public void testcheck_resultHorizontalWinner3() {
		// -------
		// | |X|X|
		// -------
		// |X|X| |
		// -------
		// |O|O|O|
		// -------
		process_service.process(new SetStoneCommand(2, 0, PlayerType.P1, check_service, board_repo));

		process_service.process(new SetStoneCommand(2, 1, PlayerType.P1, check_service, board_repo));

		process_service.process(new SetStoneCommand(2, 2, PlayerType.P1, check_service, board_repo));
		// Player 1 wins horizontally, the result should be PlayerType.P1
		assertEquals(PlayerType.P1, distinct_service.get_winner());
		process_service.process(new SetStoneCommand(1, 0, PlayerType.P2, check_service, board_repo));
		assertEquals(PlayerType.P1, distinct_service.get_winner());
		process_service.process(new SetStoneCommand(1, 1, PlayerType.P2, check_service, board_repo));
		assertEquals(PlayerType.P1, distinct_service.get_winner());
		process_service.process(new SetStoneCommand(0, 1, PlayerType.P2, check_service, board_repo));
		assertEquals(PlayerType.P1, distinct_service.get_winner());
		process_service.process(new SetStoneCommand(0, 2, PlayerType.P2, check_service, board_repo));
		assertEquals(PlayerType.P1, distinct_service.get_winner());
	}

	@Test
	public void testcheck_resultHorizontalWinner1_2() {
		// -------
		// |X|X|X|
		// -------
		// |O|O| |
		// -------
		// | | |O|
		// -------
		process_service.process(new SetStoneCommand(0, 0, PlayerType.P2, check_service, board_repo));

		process_service.process(new SetStoneCommand(0, 1, PlayerType.P2, check_service, board_repo));

		process_service.process(new SetStoneCommand(0, 2, PlayerType.P2, check_service, board_repo));

		// Player 1 wins horizontally, the result should be PlayerType.P1
		assertEquals(PlayerType.P2, distinct_service.get_winner());
		process_service.process(new SetStoneCommand(1, 0, PlayerType.P1, check_service, board_repo));
		assertEquals(PlayerType.P2, distinct_service.get_winner());
		process_service.process(new SetStoneCommand(1, 1, PlayerType.P1, check_service, board_repo));
		assertEquals(PlayerType.P2, distinct_service.get_winner());
		process_service.process(new SetStoneCommand(2, 2, PlayerType.P1, check_service, board_repo));
		assertEquals(PlayerType.P2, distinct_service.get_winner());
	}

	@Test
	public void testcheck_resultHorizontalWinner2_2() {
		// -------
		// |O| |O|
		// -------
		// |X|X|X|
		// -------
		// |O|O| |
		// -------
		process_service.process(new SetStoneCommand(1, 0, PlayerType.P2, check_service, board_repo));

		process_service.process(new SetStoneCommand(1, 1, PlayerType.P2, check_service, board_repo));

		process_service.process(new SetStoneCommand(1, 2, PlayerType.P2, check_service, board_repo));
		// Player 1 wins horizontally, the result should be PlayerType.P1
		assertEquals(PlayerType.P2, distinct_service.get_winner());
		process_service.process(new SetStoneCommand(2, 1, PlayerType.P1, check_service, board_repo));
		assertEquals(PlayerType.P2, distinct_service.get_winner());
		process_service.process(new SetStoneCommand(2, 0, PlayerType.P1, check_service, board_repo));
		assertEquals(PlayerType.P2, distinct_service.get_winner());
		process_service.process(new SetStoneCommand(0, 2, PlayerType.P1, check_service, board_repo));
		assertEquals(PlayerType.P2, distinct_service.get_winner());
		process_service.process(new SetStoneCommand(0, 0, PlayerType.P1, check_service, board_repo));
		assertEquals(PlayerType.P2, distinct_service.get_winner());
	}

	@Test
	public void testcheck_resultHorizontalWinner3_3() {
		// -------
		// |O|X|O|
		// -------
		// |O|O|X|
		// -------
		// |X|X|X|
		// -------
		process_service.process(new SetStoneCommand(2, 0, PlayerType.P2, check_service, board_repo));

		process_service.process(new SetStoneCommand(2, 1, PlayerType.P2, check_service, board_repo));

		process_service.process(new SetStoneCommand(2, 2, PlayerType.P2, check_service, board_repo));
		// Player 1 wins horizontally, the result should be PlayerType.P1
		assertEquals(PlayerType.P2, distinct_service.get_winner());
		process_service.process(new SetStoneCommand(0, 0, PlayerType.P1, check_service, board_repo));
		assertEquals(PlayerType.P2, distinct_service.get_winner());
		process_service.process(new SetStoneCommand(0, 2, PlayerType.P1, check_service, board_repo));
		assertEquals(PlayerType.P2, distinct_service.get_winner());
		process_service.process(new SetStoneCommand(1, 1, PlayerType.P1, check_service, board_repo));
		assertEquals(PlayerType.P2, distinct_service.get_winner());
		process_service.process(new SetStoneCommand(1, 0, PlayerType.P1, check_service, board_repo));
		assertEquals(PlayerType.P2, distinct_service.get_winner());
		process_service.process(new SetStoneCommand(0, 1, PlayerType.P2, check_service, board_repo));
		assertEquals(PlayerType.P2, distinct_service.get_winner());
		process_service.process(new SetStoneCommand(1, 2, PlayerType.P2, check_service, board_repo));
		assertEquals(PlayerType.P2, distinct_service.get_winner());
		assertTrue(distinct_service.check_end());
	}

	@Test
	public void testcheck_resultVerticalWinner1() {
		// -------
		// |X|O|O|
		// -------
		// |X|O|X|
		// -------
		// |X|X|O|
		// -------
		process_service.process(new SetStoneCommand(0, 0, PlayerType.P2, check_service, board_repo));

		process_service.process(new SetStoneCommand(1, 0, PlayerType.P2, check_service, board_repo));

		process_service.process(new SetStoneCommand(2, 0, PlayerType.P2, check_service, board_repo));
		// Player 2 wins vertically, the result should be PlayerType.P2
		assertEquals(PlayerType.P2, distinct_service.get_winner());
		process_service.process(new SetStoneCommand(0, 1, PlayerType.P1, check_service, board_repo));
		assertEquals(PlayerType.P2, distinct_service.get_winner());
		process_service.process(new SetStoneCommand(0, 2, PlayerType.P1, check_service, board_repo));
		assertEquals(PlayerType.P2, distinct_service.get_winner());
		process_service.process(new SetStoneCommand(1, 1, PlayerType.P1, check_service, board_repo));
		assertEquals(PlayerType.P2, distinct_service.get_winner());
		process_service.process(new SetStoneCommand(2, 2, PlayerType.P1, check_service, board_repo));
		assertEquals(PlayerType.P2, distinct_service.get_winner());
		process_service.process(new SetStoneCommand(1, 2, PlayerType.P2, check_service, board_repo));
		assertEquals(PlayerType.P2, distinct_service.get_winner());
		process_service.process(new SetStoneCommand(2, 1, PlayerType.P2, check_service, board_repo));
		assertEquals(PlayerType.P2, distinct_service.get_winner());
		assertTrue(distinct_service.check_end());
	}

	@Test
	public void testcheck_resultVerticalWinner2() {
		// -------
		// |O|X|O|
		// -------
		// |X|X| |
		// -------
		// |O|X|O|
		// -------
		process_service.process(new SetStoneCommand(0, 1, PlayerType.P2, check_service, board_repo));

		process_service.process(new SetStoneCommand(1, 1, PlayerType.P2, check_service, board_repo));

		process_service.process(new SetStoneCommand(2, 1, PlayerType.P2, check_service, board_repo));
		// Player 2 wins vertically, the result should be PlayerType.P2
		assertEquals(PlayerType.P2, distinct_service.get_winner());
		process_service.process(new SetStoneCommand(0, 0, PlayerType.P1, check_service, board_repo));
		assertEquals(PlayerType.P2, distinct_service.get_winner());
		process_service.process(new SetStoneCommand(0, 2, PlayerType.P1, check_service, board_repo));
		assertEquals(PlayerType.P2, distinct_service.get_winner());
		process_service.process(new SetStoneCommand(2, 0, PlayerType.P1, check_service, board_repo));
		assertEquals(PlayerType.P2, distinct_service.get_winner());
		process_service.process(new SetStoneCommand(2, 2, PlayerType.P1, check_service, board_repo));
		assertEquals(PlayerType.P2, distinct_service.get_winner());
		process_service.process(new SetStoneCommand(1, 0, PlayerType.P2, check_service, board_repo));
		assertEquals(PlayerType.P2, distinct_service.get_winner());
		assertFalse(distinct_service.check_end());
	}

	@Test
	public void testcheck_resultVerticalWinner3() {
		// -------
		// |O|O|X|
		// -------
		// |O| |X|
		// -------
		// |X|O|X|
		// -------
		process_service.process(new SetStoneCommand(0, 2, PlayerType.P2, check_service, board_repo));

		process_service.process(new SetStoneCommand(1, 2, PlayerType.P2, check_service, board_repo));

		process_service.process(new SetStoneCommand(2, 2, PlayerType.P2, check_service, board_repo));
		// Player 2 wins vertically, the result should be PlayerType.P2
		assertEquals(PlayerType.P2, distinct_service.get_winner());
		process_service.process(new SetStoneCommand(0, 0, PlayerType.P1, check_service, board_repo));
		assertEquals(PlayerType.P2, distinct_service.get_winner());
		process_service.process(new SetStoneCommand(0, 1, PlayerType.P1, check_service, board_repo));
		assertEquals(PlayerType.P2, distinct_service.get_winner());
		process_service.process(new SetStoneCommand(1, 0, PlayerType.P1, check_service, board_repo));
		assertEquals(PlayerType.P2, distinct_service.get_winner());
		process_service.process(new SetStoneCommand(2, 1, PlayerType.P1, check_service, board_repo));
		assertEquals(PlayerType.P2, distinct_service.get_winner());
		process_service.process(new SetStoneCommand(2, 0, PlayerType.P2, check_service, board_repo));
		assertEquals(PlayerType.P2, distinct_service.get_winner());
		assertFalse(distinct_service.check_end());
	}

	@Test
	public void testcheck_resultVerticalWinner1_1() {
		// -------
		// |O|X| |
		// -------
		// |O|X|X|
		// -------
		// |O| |X|
		// -------
		process_service.process(new SetStoneCommand(0, 0, PlayerType.P1, check_service, board_repo));

		process_service.process(new SetStoneCommand(1, 0, PlayerType.P1, check_service, board_repo));

		process_service.process(new SetStoneCommand(2, 0, PlayerType.P1, check_service, board_repo));
		// Player 2 wins vertically, the result should be PlayerType.P2
		assertEquals(PlayerType.P1, distinct_service.get_winner());
		process_service.process(new SetStoneCommand(0, 1, PlayerType.P2, check_service, board_repo));
		assertEquals(PlayerType.P1, distinct_service.get_winner());
		process_service.process(new SetStoneCommand(1, 1, PlayerType.P2, check_service, board_repo));
		assertEquals(PlayerType.P1, distinct_service.get_winner());
		process_service.process(new SetStoneCommand(1, 2, PlayerType.P2, check_service, board_repo));
		assertEquals(PlayerType.P1, distinct_service.get_winner());
		process_service.process(new SetStoneCommand(2, 2, PlayerType.P2, check_service, board_repo));
		assertEquals(PlayerType.P1, distinct_service.get_winner());
		assertFalse(distinct_service.check_end());
	}

	@Test
	public void testcheck_resultVerticalWinner2_1() {
		// -------
		// |X|O|X|
		// -------
		// |O|O|X|
		// -------
		// |X|O| |
		// -------
		process_service.process(new SetStoneCommand(0, 1, PlayerType.P1, check_service, board_repo));

		process_service.process(new SetStoneCommand(1, 1, PlayerType.P1, check_service, board_repo));

		process_service.process(new SetStoneCommand(2, 1, PlayerType.P1, check_service, board_repo));
		// Player 2 wins vertically, the result should be PlayerType.P2
		assertEquals(PlayerType.P1, distinct_service.get_winner());
		process_service.process(new SetStoneCommand(0, 2, PlayerType.P2, check_service, board_repo));
		assertEquals(PlayerType.P1, distinct_service.get_winner());
		process_service.process(new SetStoneCommand(0, 0, PlayerType.P2, check_service, board_repo));
		assertEquals(PlayerType.P1, distinct_service.get_winner());
		process_service.process(new SetStoneCommand(1, 2, PlayerType.P2, check_service, board_repo));
		assertEquals(PlayerType.P1, distinct_service.get_winner());
		process_service.process(new SetStoneCommand(2, 0, PlayerType.P2, check_service, board_repo));
		assertEquals(PlayerType.P1, distinct_service.get_winner());
		process_service.process(new SetStoneCommand(1, 0, PlayerType.P1, check_service, board_repo));
		assertEquals(PlayerType.P1, distinct_service.get_winner());
		assertFalse(distinct_service.check_end());
	}

	@Test
	public void testcheck_resultVerticalWinner3_1() {
		// -------
		// |X|X|O|
		// -------
		// |O| |O|
		// -------
		// |X|X|O|
		// -------
		process_service.process(new SetStoneCommand(0, 2, PlayerType.P1, check_service, board_repo));

		process_service.process(new SetStoneCommand(1, 2, PlayerType.P1, check_service, board_repo));

		process_service.process(new SetStoneCommand(2, 2, PlayerType.P1, check_service, board_repo));
		// Player 2 wins vertically, the result should be PlayerType.P2
		assertEquals(PlayerType.P1, distinct_service.get_winner());
		process_service.process(new SetStoneCommand(0, 0, PlayerType.P2, check_service, board_repo));
		assertEquals(PlayerType.P1, distinct_service.get_winner());
		process_service.process(new SetStoneCommand(0, 1, PlayerType.P2, check_service, board_repo));
		assertEquals(PlayerType.P1, distinct_service.get_winner());
		process_service.process(new SetStoneCommand(2, 0, PlayerType.P2, check_service, board_repo));
		assertEquals(PlayerType.P1, distinct_service.get_winner());
		process_service.process(new SetStoneCommand(2, 1, PlayerType.P2, check_service, board_repo));
		assertEquals(PlayerType.P1, distinct_service.get_winner());
		process_service.process(new SetStoneCommand(1, 0, PlayerType.P1, check_service, board_repo));
		assertEquals(PlayerType.P1, distinct_service.get_winner());
		assertFalse(distinct_service.check_end());
	}

	@Test
	public void testcheck_resultDiagonalWinner1() {
		// -------
		// |O| |X|
		// -------
		// |X|O|X|
		// -------
		// |X| |O|
		// -------
		process_service.process(new SetStoneCommand(0, 0, PlayerType.P1, check_service, board_repo));

		process_service.process(new SetStoneCommand(1, 1, PlayerType.P1, check_service, board_repo));

		process_service.process(new SetStoneCommand(2, 2, PlayerType.P1, check_service, board_repo));

		// Player 1 wins diagonally, the result should be PlayerType.P1
		assertEquals(PlayerType.P1, distinct_service.get_winner());
		process_service.process(new SetStoneCommand(0, 2, PlayerType.P2, check_service, board_repo));
		assertEquals(PlayerType.P1, distinct_service.get_winner());
		process_service.process(new SetStoneCommand(1, 2, PlayerType.P2, check_service, board_repo));
		assertEquals(PlayerType.P1, distinct_service.get_winner());
		process_service.process(new SetStoneCommand(1, 0, PlayerType.P2, check_service, board_repo));
		assertEquals(PlayerType.P1, distinct_service.get_winner());
		process_service.process(new SetStoneCommand(2, 0, PlayerType.P2, check_service, board_repo));
		assertEquals(PlayerType.P1, distinct_service.get_winner());
		assertFalse(distinct_service.check_end());
	}

	@Test
	public void testcheck_resultDiagonalWinner2() {
		// -------
		// |X|O| |
		// -------
		// |O|X|O|
		// -------
		// | |O|X|
		// -------
		process_service.process(new SetStoneCommand(0, 0, PlayerType.P2, check_service, board_repo));

		process_service.process(new SetStoneCommand(1, 1, PlayerType.P2, check_service, board_repo));

		process_service.process(new SetStoneCommand(2, 2, PlayerType.P2, check_service, board_repo));
		// Player 1 wins diagonally, the result should be PlayerType.P1
		assertEquals(PlayerType.P2, distinct_service.get_winner());
		process_service.process(new SetStoneCommand(1, 2, PlayerType.P1, check_service, board_repo));
		assertEquals(PlayerType.P2, distinct_service.get_winner());
		process_service.process(new SetStoneCommand(0, 1, PlayerType.P1, check_service, board_repo));
		assertEquals(PlayerType.P2, distinct_service.get_winner());
		process_service.process(new SetStoneCommand(1, 0, PlayerType.P1, check_service, board_repo));
		assertEquals(PlayerType.P2, distinct_service.get_winner());
		process_service.process(new SetStoneCommand(2, 1, PlayerType.P1, check_service, board_repo));
		assertEquals(PlayerType.P2, distinct_service.get_winner());
		assertFalse(distinct_service.check_end());
	}

	@Test
	public void testcheck_resultAntiDiagonalWinner1() {
		// -------
		// | |O|X|
		// -------
		// |O|X| |
		// -------
		// |X|O|O|
		// -------
		process_service.process(new SetStoneCommand(0, 2, PlayerType.P2, check_service, board_repo));

		process_service.process(new SetStoneCommand(1, 1, PlayerType.P2, check_service, board_repo));

		process_service.process(new SetStoneCommand(2, 0, PlayerType.P2, check_service, board_repo));
		// Player 2 wins anti-diagonally, the result should be PlayerType.P2
		assertEquals(PlayerType.P2, distinct_service.get_winner());
		process_service.process(new SetStoneCommand(0, 1, PlayerType.P1, check_service, board_repo));
		assertEquals(PlayerType.P2, distinct_service.get_winner());
		process_service.process(new SetStoneCommand(1, 0, PlayerType.P1, check_service, board_repo));
		assertEquals(PlayerType.P2, distinct_service.get_winner());
		process_service.process(new SetStoneCommand(2, 2, PlayerType.P1, check_service, board_repo));
		assertEquals(PlayerType.P2, distinct_service.get_winner());
		process_service.process(new SetStoneCommand(2, 1, PlayerType.P1, check_service, board_repo));
		assertEquals(PlayerType.P2, distinct_service.get_winner());
		process_service.process(new SetStoneCommand(0, 1, PlayerType.P2, check_service, board_repo));
		assertEquals(PlayerType.P2, distinct_service.get_winner());
		assertFalse(distinct_service.check_end());
	}

	@Test
	public void testcheck_resultAntiDiagonalWinner2() {
		// -------
		// |X|X|O|
		// -------
		// |X|O|O|
		// -------
		// |O|O|X|
		// -------
		process_service.process(new SetStoneCommand(0, 2, PlayerType.P1, check_service, board_repo));

		process_service.process(new SetStoneCommand(1, 1, PlayerType.P1, check_service, board_repo));

		process_service.process(new SetStoneCommand(2, 0, PlayerType.P1, check_service, board_repo));
		// Player 2 wins anti-diagonally, the result should be PlayerType.P2
		assertEquals(PlayerType.P1, distinct_service.get_winner());
		process_service.process(new SetStoneCommand(2, 2, PlayerType.P2, check_service, board_repo));
		assertEquals(PlayerType.P1, distinct_service.get_winner());
		process_service.process(new SetStoneCommand(0, 0, PlayerType.P2, check_service, board_repo));
		assertEquals(PlayerType.P1, distinct_service.get_winner());
		process_service.process(new SetStoneCommand(0, 1, PlayerType.P2, check_service, board_repo));
		assertEquals(PlayerType.P1, distinct_service.get_winner());
		process_service.process(new SetStoneCommand(1, 0, PlayerType.P2, check_service, board_repo));
		assertEquals(PlayerType.P1, distinct_service.get_winner());
		process_service.process(new SetStoneCommand(2, 1, PlayerType.P1, check_service, board_repo));
		assertEquals(PlayerType.P1, distinct_service.get_winner());
		process_service.process(new SetStoneCommand(1, 2, PlayerType.P1, check_service, board_repo));
		assertEquals(PlayerType.P1, distinct_service.get_winner());
		assertTrue(distinct_service.check_end());
	}
}
