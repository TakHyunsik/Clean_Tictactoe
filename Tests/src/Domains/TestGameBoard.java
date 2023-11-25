package Domains;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import Commons.PlayerType;
import DomainInterfaces.IGameBoard;
import Entities.TicTacToeBoard;

class TestGameBoard {
	IGameBoard board;

	@BeforeEach
	void setUp() throws Exception {
		board = new TicTacToeBoard();
	}

	@Test
	void test_set_and_get() {
		assertTrue(board.set_in_board(1, 1, PlayerType.P1));
		assertEquals(PlayerType.P1, board.get_in_board(1, 1));

		assertEquals(PlayerType.None, board.get_in_board(0, 0));
		assertEquals(PlayerType.None, board.get_in_board(1, 0));
		assertEquals(PlayerType.None, board.get_in_board(1, 2));
		assertEquals(PlayerType.None, board.get_in_board(2, 2));
	}

	@Test
	void test_check_setable_in_board() {
		// 빈보드인지 체크
		for (int i = 0; i < board.get_size(); i++) {
			for (int j = 0; j < board.get_size(); j++) {
				assertEquals(PlayerType.None, board.get_in_board(i, j));
				assertTrue(board.check_setable_in_board(i, j));
				assertTrue(board.get_count() == 0);
			}
		}

		// 세팅 후 체크
		assertTrue(board.set_in_board(1, 1, PlayerType.P1));

		assertTrue(!board.check_setable_in_board(1, 1));
		assertTrue(!board.set_in_board(1, 1, PlayerType.P1));
		assertTrue(board.get_count() == 1);

		// 범위 밖 위치 체크
		for (int i = -10; i < 0; i++) {
			for (int j = -10; j < 0; j++) {
				assertTrue(!board.check_setable_in_board(i, j));
				assertTrue(board.get_count() == 1);
			}
		}
		int size = board.get_size();
		int size10 = size + 10;
		for (int i = size; i < size10; i++) {
			for (int j = size; j < size10; j++) {
				assertTrue(!board.check_setable_in_board(i, j));
				assertTrue(board.get_count() == 1);
			}
		}
	}

	@Test
	void test_full_empty() {
		assertTrue(board.check_empty());
		assertFalse(board.check_be_full());
		assertTrue(board.get_count() == 0);

		for (int i = 0; i < board.get_size() * board.get_size() - 1; i++) {
			int y = i / board.get_size(), x = (i % board.get_size());
			if (i % 2 == 0) {
				assertTrue(board.set_in_board(y, x, PlayerType.P1));
			} else {
				assertTrue(board.set_in_board(y, x, PlayerType.P2));
			}
			assertFalse(board.check_empty());
			assertFalse(board.check_be_full());
			assertTrue(board.get_count() == i + 1);
		}
		assertTrue(board.set_in_board(2, 2, PlayerType.P1));
		assertFalse(board.check_empty());
		assertTrue(board.check_be_full());
		assertTrue(board.get_count() == board.get_size() * board.get_size());
	}
}
