package Applications.Tictactoe.Fivewood;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import AppInterfaces.BoardServices.ICheckSetableRuleService;
import AppInterfaces.BoardServices.IGetBoardService;
import AppInterfaces.CommandService.IProcessCommandService;
import Applications.Tictactoe.TestBoardStorage;
import Applications.Tictactoe.TestCommandStorage;
import Commons.PlayerType;
import Entities.FivewoodBoard;
import Fivewood.CheckSetableStoneFivewoodService;
import Fivewood.GetGameStateFivewoodService;
import Repository.IBoardRepository;
import Repository.ICommandRepository;
import Usecases.GetBoardService;
import Usecases.ProcessCommandService;

class TestGetGameStateFivewoodService {
	IBoardRepository board_repo;
	ICommandRepository cmd_repo;
	GetGameStateFivewoodService distinct_service;
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

		board_repo.update_board(10, 0, PlayerType.P1);
		board_repo.update_board(10, 1, PlayerType.P1);
		board_repo.update_board(10, 2, PlayerType.P1);
		board_repo.update_board(10, 3, PlayerType.P1);
		board_repo.update_board(10, 4, PlayerType.P1);
		assertEquals(PlayerType.P1, distinct_service.check_width(10, 0));
	}

	@Test
	public void testcheck_resultNoWinner1() {

		board_repo.update_board(10, 0, PlayerType.P1);
		board_repo.update_board(10, 1, PlayerType.P2);
		board_repo.update_board(10, 2, PlayerType.P1);
		board_repo.update_board(10, 3, PlayerType.P1);
		board_repo.update_board(10, 4, PlayerType.P1);
		assertEquals(PlayerType.None, distinct_service.check_width(10, 0));
	}

	@Test
	public void testcheck_resultNoWinner6() {

		board_repo.update_board(10, 0, PlayerType.P1);
		board_repo.update_board(10, 1, PlayerType.P1);
		board_repo.update_board(10, 2, PlayerType.P2);
		board_repo.update_board(10, 3, PlayerType.P1);
		board_repo.update_board(10, 4, PlayerType.P1);
		assertEquals(PlayerType.None, distinct_service.check_width(10, 0));
	}

	@Test
	public void testcheck_resultNoWinner2() {

		board_repo.update_board(10, 0, PlayerType.P2);
		board_repo.update_board(10, 1, PlayerType.P2);
		board_repo.update_board(10, 2, PlayerType.P2);
		board_repo.update_board(10, 3, PlayerType.P2);
		board_repo.update_board(10, 4, PlayerType.P2);
		assertEquals(PlayerType.P2, distinct_service.check_width(10, 0));
	}

	@Test
	public void testcheck_resultNoWinner3() {

		board_repo.update_board(10, 0, PlayerType.P1);
		board_repo.update_board(10, 2, PlayerType.P1);
		board_repo.update_board(10, 3, PlayerType.P1);
		board_repo.update_board(10, 4, PlayerType.P1);
		assertEquals(PlayerType.None, distinct_service.check_width(10, 0));
	}

	@Test
	public void testcheck_resultNoWinner10() {

		board_repo.update_board(10, 0, PlayerType.P1);
		assertNotEquals(PlayerType.P1, distinct_service.check_width(10, 0));
	}

	@Test
	public void testcheck_resultNoWinner301() {

		board_repo.update_board(10, 10, PlayerType.P1);
		board_repo.update_board(10, 11, PlayerType.P1);
		board_repo.update_board(10, 12, PlayerType.P1);
		board_repo.update_board(10, 13, PlayerType.P1);
		board_repo.update_board(10, 14, PlayerType.P1);
		assertEquals(PlayerType.P1, distinct_service.check_width(10, 10));
	}

	@Test
	public void testcheck_resultNoWinner300() {

		board_repo.update_board(10, 11, PlayerType.P1);
		board_repo.update_board(10, 12, PlayerType.P1);
		board_repo.update_board(10, 13, PlayerType.P1);
		board_repo.update_board(10, 14, PlayerType.P1);
		assertEquals(PlayerType.None, distinct_service.check_width(10, 11));
	}

	@Test
	public void testcheck_check_height1() {

		board_repo.update_board(10, 10, PlayerType.P1);
		board_repo.update_board(9, 10, PlayerType.P1);
		board_repo.update_board(8, 10, PlayerType.P1);
		board_repo.update_board(7, 10, PlayerType.P1);
		board_repo.update_board(6, 10, PlayerType.P1);
		assertEquals(PlayerType.P1, distinct_service.check_height(10, 10));
	}

	@Test
	public void testcheck_check_height2() {

		board_repo.update_board(10, 10, PlayerType.P1);
		board_repo.update_board(9, 10, PlayerType.P2);
		board_repo.update_board(8, 10, PlayerType.P1);
		board_repo.update_board(7, 10, PlayerType.P1);
		board_repo.update_board(6, 10, PlayerType.P1);
		assertEquals(PlayerType.None, distinct_service.check_height(10, 10));
	}

	@Test
	public void testcheck_check_height3() {

		board_repo.update_board(10, 10, PlayerType.P1);
		board_repo.update_board(9, 10, PlayerType.P1);
		board_repo.update_board(8, 10, PlayerType.P2);
		board_repo.update_board(7, 10, PlayerType.P1);
		board_repo.update_board(6, 10, PlayerType.P1);
		assertEquals(PlayerType.None, distinct_service.check_height(10, 10));
	}

	@Test
	public void testcheck_check_height4() {

		board_repo.update_board(10, 10, PlayerType.P1);
		board_repo.update_board(9, 10, PlayerType.P1);
		board_repo.update_board(8, 10, PlayerType.P1);
		board_repo.update_board(7, 10, PlayerType.P1);

		assertEquals(PlayerType.None, distinct_service.check_height(10, 10));
	}

	@Test
	public void testcheck_check_height5() {

		board_repo.update_board(10, 10, PlayerType.P2);
		board_repo.update_board(9, 10, PlayerType.P2);
		board_repo.update_board(8, 10, PlayerType.P2);
		board_repo.update_board(7, 10, PlayerType.P2);
		board_repo.update_board(6, 10, PlayerType.P2);
		assertEquals(PlayerType.P2, distinct_service.check_height(10, 10));
	}

	@Test
	public void testcheck_check_height6() {

		board_repo.update_board(3, 10, PlayerType.P1);
		board_repo.update_board(2, 10, PlayerType.P1);
		board_repo.update_board(1, 10, PlayerType.P1);
		board_repo.update_board(0, 10, PlayerType.P1);
		assertEquals(PlayerType.None, distinct_service.check_height(3, 10));
	}

	@Test
	public void testcheck_check_height7() {
		board_repo.update_board(4, 10, PlayerType.P1);
		board_repo.update_board(3, 10, PlayerType.P1);
		board_repo.update_board(2, 10, PlayerType.P1);
		board_repo.update_board(1, 10, PlayerType.P1);
		board_repo.update_board(0, 10, PlayerType.P1);
		assertEquals(PlayerType.P1, distinct_service.check_height(4, 10));
	}

	@Test
	public void testcheck_check_right_diagonal1() {

		board_repo.update_board(6, 6, PlayerType.P1);
		board_repo.update_board(5, 7, PlayerType.P1);
		board_repo.update_board(4, 8, PlayerType.P1);
		board_repo.update_board(3, 9, PlayerType.P1);
		board_repo.update_board(2, 10, PlayerType.P1);
		assertEquals(PlayerType.P1, distinct_service.check_right_diagonal(6, 6));
	}

	@Test
	public void testcheck_check_right_diagonal2() {

		board_repo.update_board(6, 6, PlayerType.P1);
		board_repo.update_board(5, 7, PlayerType.P2);
		board_repo.update_board(4, 8, PlayerType.P1);
		board_repo.update_board(3, 9, PlayerType.P1);
		board_repo.update_board(2, 10, PlayerType.P1);
		assertEquals(PlayerType.None, distinct_service.check_right_diagonal(6, 6));
	}

	@Test
	public void testcheck_check_right_diagonal() {

		board_repo.update_board(6, 6, PlayerType.P1);
		board_repo.update_board(5, 7, PlayerType.P1);
		board_repo.update_board(4, 8, PlayerType.P2);
		board_repo.update_board(3, 9, PlayerType.P1);
		board_repo.update_board(2, 10, PlayerType.P1);
		assertEquals(PlayerType.None, distinct_service.check_right_diagonal(6, 6));
	}

	@Test
	public void testcheck_check_right_diagonal4() {

		board_repo.update_board(6, 6, PlayerType.P1);
		board_repo.update_board(5, 7, PlayerType.P1);
		board_repo.update_board(4, 8, PlayerType.P1);
		board_repo.update_board(3, 9, PlayerType.P1);

		assertEquals(PlayerType.None, distinct_service.check_right_diagonal(6, 6));
	}

	@Test
	public void testcheck_check_right_diagonal5() {

		board_repo.update_board(6, 6, PlayerType.P2);
		board_repo.update_board(5, 7, PlayerType.P2);
		board_repo.update_board(4, 8, PlayerType.P2);
		board_repo.update_board(3, 9, PlayerType.P2);
		board_repo.update_board(2, 10, PlayerType.P2);
		assertEquals(PlayerType.P2, distinct_service.check_right_diagonal(6, 6));
	}

	@Test
	public void testcheck_check_right_diagonal6() {

		board_repo.update_board(3, 0, PlayerType.P1);
		board_repo.update_board(2, 1, PlayerType.P1);
		board_repo.update_board(1, 2, PlayerType.P1);
		board_repo.update_board(0, 3, PlayerType.P1);

		assertEquals(PlayerType.None, distinct_service.check_right_diagonal(3, 0));
	}

	@Test
	public void testcheck_check_right_diagonal8() {

		board_repo.update_board(3, 3, PlayerType.P1);
		board_repo.update_board(2, 4, PlayerType.P1);
		board_repo.update_board(1, 5, PlayerType.P1);
		board_repo.update_board(0, 6, PlayerType.P1);

		assertEquals(PlayerType.None, distinct_service.check_right_diagonal(3, 3));
	}

	@Test
	public void testcheck_check_right_diagonal9() {

		board_repo.update_board(6, 0, PlayerType.P1);
		board_repo.update_board(5, 1, PlayerType.P1);
		board_repo.update_board(4, 2, PlayerType.P1);
		board_repo.update_board(3, 3, PlayerType.P1);

		assertEquals(PlayerType.None, distinct_service.check_right_diagonal(6, 0));
	}

	@Test
	public void testcheck_check_right_diagonal10() {
		board_repo.update_board(4, 0, PlayerType.P1);
		board_repo.update_board(3, 1, PlayerType.P1);
		board_repo.update_board(2, 2, PlayerType.P1);
		board_repo.update_board(1, 3, PlayerType.P1);
		board_repo.update_board(0, 4, PlayerType.P1);

		assertEquals(PlayerType.P1, distinct_service.check_right_diagonal(4, 0));
	}

	@Test
	public void testcheck_check_right_diagonal7() {

		board_repo.update_board(4, 13, PlayerType.P1);
		board_repo.update_board(3, 14, PlayerType.P1);

		assertEquals(PlayerType.None, distinct_service.check_right_diagonal(4, 13));
	}

	@Test
	public void testcheck_check_left_diagonal1() {

		board_repo.update_board(6, 6, PlayerType.P1);
		board_repo.update_board(5, 5, PlayerType.P1);
		board_repo.update_board(4, 4, PlayerType.P1);
		board_repo.update_board(3, 3, PlayerType.P1);
		board_repo.update_board(2, 2, PlayerType.P1);
		assertEquals(PlayerType.P1, distinct_service.check_left_diagonal(6, 6));
	}

	@Test
	public void testcheck_check_left_diagonal2() {

		board_repo.update_board(6, 6, PlayerType.P1);
		board_repo.update_board(5, 5, PlayerType.P2);
		board_repo.update_board(4, 4, PlayerType.P1);
		board_repo.update_board(3, 3, PlayerType.P1);
		board_repo.update_board(2, 2, PlayerType.P1);
		assertEquals(PlayerType.None, distinct_service.check_left_diagonal(6, 6));
	}

	@Test
	public void testcheck_check_left_diagonal3() {

		board_repo.update_board(6, 6, PlayerType.P1);
		board_repo.update_board(5, 5, PlayerType.P1);
		board_repo.update_board(4, 4, PlayerType.P1);
		board_repo.update_board(3, 3, PlayerType.P2);
		board_repo.update_board(2, 2, PlayerType.P1);
		assertEquals(PlayerType.None, distinct_service.check_left_diagonal(6, 6));
	}

	@Test
	public void testcheck_check_left_diagonal4() {

		board_repo.update_board(6, 6, PlayerType.P1);
		board_repo.update_board(5, 5, PlayerType.P1);
		board_repo.update_board(4, 4, PlayerType.P1);
		board_repo.update_board(3, 3, PlayerType.P1);

		assertEquals(PlayerType.None, distinct_service.check_left_diagonal(6, 6));
	}

	@Test
	public void testcheck_check_left_diagonal() {

		board_repo.update_board(6, 6, PlayerType.P2);
		board_repo.update_board(5, 5, PlayerType.P2);
		board_repo.update_board(4, 4, PlayerType.P2);
		board_repo.update_board(3, 3, PlayerType.P2);
		board_repo.update_board(2, 2, PlayerType.P2);
		assertEquals(PlayerType.P2, distinct_service.check_left_diagonal(6, 6));
	}

	@Test
	public void testcheck_check_left_diagonal6() {

		board_repo.update_board(3, 4, PlayerType.P1);
		board_repo.update_board(2, 3, PlayerType.P1);
		board_repo.update_board(1, 2, PlayerType.P1);
		board_repo.update_board(0, 1, PlayerType.P1);

		assertEquals(PlayerType.None, distinct_service.check_left_diagonal(3, 4));
	}

	@Test
	public void testcheck_check_left_diagonal7() {

		board_repo.update_board(10, 3, PlayerType.P1);
		board_repo.update_board(9, 2, PlayerType.P1);
		board_repo.update_board(8, 1, PlayerType.P1);
		board_repo.update_board(7, 0, PlayerType.P1);

		assertEquals(PlayerType.None, distinct_service.check_left_diagonal(10, 3));
	}

	@Test
	public void testcheck_check_left_diagonal8() {

		board_repo.update_board(4, 4, PlayerType.P1);
		board_repo.update_board(3, 3, PlayerType.P1);
		board_repo.update_board(2, 2, PlayerType.P1);
		board_repo.update_board(1, 1, PlayerType.P1);
		board_repo.update_board(0, 0, PlayerType.P1);
		assertEquals(PlayerType.P1, distinct_service.check_left_diagonal(4, 4));
	}

}
