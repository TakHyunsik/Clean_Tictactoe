package Domains;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import Commons.PlayerType;
import Entities.TicTacToeBoard;

class TestTicTacToeBoard {
	TicTacToeBoard board;

	@BeforeEach
	void setUp() throws Exception {
		board = new TicTacToeBoard();
	}

	@Test
	void test_size() {
		assertEquals(3, board.get_size());
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
		// �� �����϶� �Ѽ� �ִ��� Ȯ���Ѵ�.
		for (int i = 0; i < board.get_size(); i++) {
			for (int j = 0; j < board.get_size(); j++) {
				assertEquals(PlayerType.None, board.get_in_board(i, j));
				assertTrue(board.check_setable_in_board(i, j));
			}
		}

		// Ȯ���ϰ� 1,1�� �д�.
		assertTrue(board.set_in_board(1, 1, PlayerType.P1));

		// �̹� �а��� �Ѽ� ����.
		assertTrue(!board.check_setable_in_board(1, 1));
		assertTrue(!board.set_in_board(1, 1, PlayerType.P1));

		// ������ ������ �Ѽ� ����.
		// - ���� ���� �׽�Ʈ
		for (int i = -10; i < 0; i++) {
			for (int j = -10; j < 0; j++) {
				assertTrue(!board.check_setable_in_board(i, j));
			}
		}
		// - �翡 ���� �׽�Ʈ
		for (int i = Math.min(board.get_size(), 50); i < 50; i++) {
			for (int j = Math.min(board.get_size(), 50); j < 50; j++) {
				assertTrue(!board.check_setable_in_board(i, j));
			}
		}
	}

	@Test
	void test_full_empty() {
		// 시작은 비어있다.
		assertTrue(board.check_empty());
		assertFalse(board.check_be_full());

		for (int i = 0; i < board.get_size() * board.get_size() - 1; i++) {
			int y = i / board.get_size(), x = (i % board.get_size());
			if (i % 2 == 0) {
				assertTrue(board.set_in_board(y, x, PlayerType.P1));
			} else {
				assertTrue(board.set_in_board(y, x, PlayerType.P2));
			}
			assertFalse(board.check_empty());
			assertFalse(board.check_be_full());
		}
		assertTrue(board.set_in_board(2, 2, PlayerType.P1));
		assertFalse(board.check_empty());
		assertTrue(board.check_be_full());
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
		assertEquals(PlayerType.None, board.check_result());

		// 무승부
		board.set_in_board(0, 0, PlayerType.P1);
		assertEquals(PlayerType.None, board.check_result());
		board.set_in_board(0, 1, PlayerType.P2);
		assertEquals(PlayerType.None, board.check_result());
		board.set_in_board(0, 2, PlayerType.P1);
		assertEquals(PlayerType.None, board.check_result());
		board.set_in_board(1, 0, PlayerType.P2);
		assertEquals(PlayerType.None, board.check_result());
		board.set_in_board(1, 1, PlayerType.P1);
		assertEquals(PlayerType.None, board.check_result());
		board.set_in_board(1, 2, PlayerType.P2);
		assertEquals(PlayerType.None, board.check_result());
		board.set_in_board(2, 0, PlayerType.P2);
		assertEquals(PlayerType.None, board.check_result());
		board.set_in_board(2, 1, PlayerType.P1);
		assertEquals(PlayerType.None, board.check_result());
		board.set_in_board(2, 2, PlayerType.P2);
		// The game is a tie, the result should be PlayerType.None
		assertEquals(PlayerType.None, board.check_result());
		assertTrue(board.check_be_full());
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
		assertEquals(PlayerType.None, board.check_result());

		// 무승부
		board.set_in_board(0, 0, PlayerType.P1);
		assertEquals(PlayerType.None, board.check_result());
		board.set_in_board(0, 1, PlayerType.P1);
		assertEquals(PlayerType.None, board.check_result());
		board.set_in_board(0, 2, PlayerType.P2);
		assertEquals(PlayerType.None, board.check_result());
		board.set_in_board(1, 0, PlayerType.P2);
		assertEquals(PlayerType.None, board.check_result());
		board.set_in_board(1, 1, PlayerType.P1);
		assertEquals(PlayerType.None, board.check_result());
		board.set_in_board(1, 2, PlayerType.P1);
		assertEquals(PlayerType.None, board.check_result());
		board.set_in_board(2, 0, PlayerType.P1);
		assertEquals(PlayerType.None, board.check_result());
		board.set_in_board(2, 1, PlayerType.P2);
		assertEquals(PlayerType.None, board.check_result());
		board.set_in_board(2, 2, PlayerType.P2);
		// The game is a tie, the result should be PlayerType.None
		assertEquals(PlayerType.None, board.check_result());
		assertTrue(board.check_be_full());
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
		board.set_in_board(0, 0, PlayerType.P1);
		board.set_in_board(0, 1, PlayerType.P1);
		board.set_in_board(0, 2, PlayerType.P1);
		// Player 1 wins horizontally, the result should be PlayerType.P1
		assertEquals(PlayerType.P1, board.check_result());
		board.set_in_board(1, 0, PlayerType.P2);
		assertEquals(PlayerType.P1, board.check_result());
		board.set_in_board(1, 1, PlayerType.P2);
		assertEquals(PlayerType.P1, board.check_result());
		board.set_in_board(2, 1, PlayerType.P2);
		assertEquals(PlayerType.P1, board.check_result());
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
		assertEquals(PlayerType.None, board.check_result());

		// 무승부
		board.set_in_board(0, 0, PlayerType.P2);
		assertEquals(PlayerType.None, board.check_result());
		board.set_in_board(0, 1, PlayerType.P1);
		assertEquals(PlayerType.None, board.check_result());
		board.set_in_board(0, 2, PlayerType.P2);
		assertEquals(PlayerType.None, board.check_result());
		board.set_in_board(1, 0, PlayerType.P2);
		assertEquals(PlayerType.None, board.check_result());
		board.set_in_board(1, 1, PlayerType.P1);
		assertEquals(PlayerType.None, board.check_result());
		board.set_in_board(1, 2, PlayerType.P2);
		assertEquals(PlayerType.None, board.check_result());
		board.set_in_board(2, 0, PlayerType.P1);
		assertEquals(PlayerType.None, board.check_result());
		board.set_in_board(2, 1, PlayerType.P2);
		assertEquals(PlayerType.None, board.check_result());
		board.set_in_board(2, 2, PlayerType.P1);
		// The game is a tie, the result should be PlayerType.None
		assertEquals(PlayerType.None, board.check_result());
		assertTrue(board.check_be_full());
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
		board.set_in_board(1, 0, PlayerType.P1);
		board.set_in_board(1, 1, PlayerType.P1);
		board.set_in_board(1, 2, PlayerType.P1);
		// Player 1 wins horizontally, the result should be PlayerType.P1
		assertEquals(PlayerType.P1, board.check_result());
		board.set_in_board(2, 2, PlayerType.P2);
		assertEquals(PlayerType.P1, board.check_result());
		board.set_in_board(2, 0, PlayerType.P2);
		assertEquals(PlayerType.P1, board.check_result());
		board.set_in_board(0, 1, PlayerType.P2);
		assertEquals(PlayerType.P1, board.check_result());
		board.set_in_board(0, 0, PlayerType.P2);
		assertEquals(PlayerType.P1, board.check_result());
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
		board.set_in_board(2, 0, PlayerType.P1);
		board.set_in_board(2, 1, PlayerType.P1);
		board.set_in_board(2, 2, PlayerType.P1);
		// Player 1 wins horizontally, the result should be PlayerType.P1
		assertEquals(PlayerType.P1, board.check_result());
		board.set_in_board(1, 0, PlayerType.P2);
		assertEquals(PlayerType.P1, board.check_result());
		board.set_in_board(1, 1, PlayerType.P2);
		assertEquals(PlayerType.P1, board.check_result());
		board.set_in_board(0, 1, PlayerType.P2);
		assertEquals(PlayerType.P1, board.check_result());
		board.set_in_board(0, 2, PlayerType.P2);
		assertEquals(PlayerType.P1, board.check_result());
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
		board.set_in_board(0, 0, PlayerType.P2);
		board.set_in_board(0, 1, PlayerType.P2);
		board.set_in_board(0, 2, PlayerType.P2);
		// Player 1 wins horizontally, the result should be PlayerType.P1
		assertEquals(PlayerType.P2, board.check_result());
		board.set_in_board(1, 0, PlayerType.P1);
		assertEquals(PlayerType.P2, board.check_result());
		board.set_in_board(1, 1, PlayerType.P1);
		assertEquals(PlayerType.P2, board.check_result());
		board.set_in_board(2, 2, PlayerType.P1);
		assertEquals(PlayerType.P2, board.check_result());
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
		board.set_in_board(1, 0, PlayerType.P2);
		board.set_in_board(1, 1, PlayerType.P2);
		board.set_in_board(1, 2, PlayerType.P2);
		// Player 1 wins horizontally, the result should be PlayerType.P1
		assertEquals(PlayerType.P2, board.check_result());
		board.set_in_board(2, 1, PlayerType.P1);
		assertEquals(PlayerType.P2, board.check_result());
		board.set_in_board(2, 0, PlayerType.P1);
		assertEquals(PlayerType.P2, board.check_result());
		board.set_in_board(0, 2, PlayerType.P1);
		assertEquals(PlayerType.P2, board.check_result());
		board.set_in_board(0, 0, PlayerType.P1);
		assertEquals(PlayerType.P2, board.check_result());
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
		board.set_in_board(2, 0, PlayerType.P2);
		board.set_in_board(2, 1, PlayerType.P2);
		board.set_in_board(2, 2, PlayerType.P2);
		// Player 1 wins horizontally, the result should be PlayerType.P1
		assertEquals(PlayerType.P2, board.check_result());
		board.set_in_board(0, 0, PlayerType.P1);
		assertEquals(PlayerType.P2, board.check_result());
		board.set_in_board(0, 2, PlayerType.P1);
		assertEquals(PlayerType.P2, board.check_result());
		board.set_in_board(1, 1, PlayerType.P1);
		assertEquals(PlayerType.P2, board.check_result());
		board.set_in_board(1, 0, PlayerType.P1);
		assertEquals(PlayerType.P2, board.check_result());
		board.set_in_board(0, 1, PlayerType.P2);
		assertEquals(PlayerType.P2, board.check_result());
		board.set_in_board(1, 2, PlayerType.P2);
		assertEquals(PlayerType.P2, board.check_result());
		assertTrue(board.check_be_full());
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
		board.set_in_board(0, 0, PlayerType.P2);
		board.set_in_board(1, 0, PlayerType.P2);
		board.set_in_board(2, 0, PlayerType.P2);
		// Player 2 wins vertically, the result should be PlayerType.P2
		assertEquals(PlayerType.P2, board.check_result());
		board.set_in_board(0, 1, PlayerType.P1);
		assertEquals(PlayerType.P2, board.check_result());
		board.set_in_board(0, 2, PlayerType.P1);
		assertEquals(PlayerType.P2, board.check_result());
		board.set_in_board(1, 1, PlayerType.P1);
		assertEquals(PlayerType.P2, board.check_result());
		board.set_in_board(2, 2, PlayerType.P1);
		assertEquals(PlayerType.P2, board.check_result());
		board.set_in_board(1, 2, PlayerType.P2);
		assertEquals(PlayerType.P2, board.check_result());
		board.set_in_board(2, 1, PlayerType.P2);
		assertEquals(PlayerType.P2, board.check_result());
		assertTrue(board.check_be_full());
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
		board.set_in_board(0, 1, PlayerType.P2);
		board.set_in_board(1, 1, PlayerType.P2);
		board.set_in_board(2, 1, PlayerType.P2);
		// Player 2 wins vertically, the result should be PlayerType.P2
		assertEquals(PlayerType.P2, board.check_result());
		board.set_in_board(0, 0, PlayerType.P1);
		assertEquals(PlayerType.P2, board.check_result());
		board.set_in_board(0, 2, PlayerType.P1);
		assertEquals(PlayerType.P2, board.check_result());
		board.set_in_board(2, 0, PlayerType.P1);
		assertEquals(PlayerType.P2, board.check_result());
		board.set_in_board(2, 2, PlayerType.P1);
		assertEquals(PlayerType.P2, board.check_result());
		board.set_in_board(1, 0, PlayerType.P2);
		assertEquals(PlayerType.P2, board.check_result());
		assertFalse(board.check_empty());
		assertFalse(board.check_be_full());
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
		board.set_in_board(0, 2, PlayerType.P2);
		board.set_in_board(1, 2, PlayerType.P2);
		board.set_in_board(2, 2, PlayerType.P2);
		// Player 2 wins vertically, the result should be PlayerType.P2
		assertEquals(PlayerType.P2, board.check_result());
		board.set_in_board(0, 0, PlayerType.P1);
		assertEquals(PlayerType.P2, board.check_result());
		board.set_in_board(0, 1, PlayerType.P1);
		assertEquals(PlayerType.P2, board.check_result());
		board.set_in_board(1, 0, PlayerType.P1);
		assertEquals(PlayerType.P2, board.check_result());
		board.set_in_board(2, 1, PlayerType.P1);
		assertEquals(PlayerType.P2, board.check_result());
		board.set_in_board(2, 0, PlayerType.P2);
		assertEquals(PlayerType.P2, board.check_result());
		assertFalse(board.check_empty());
		assertFalse(board.check_be_full());
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
		board.set_in_board(0, 0, PlayerType.P1);
		board.set_in_board(1, 0, PlayerType.P1);
		board.set_in_board(2, 0, PlayerType.P1);
		// Player 2 wins vertically, the result should be PlayerType.P2
		assertEquals(PlayerType.P1, board.check_result());
		board.set_in_board(0, 1, PlayerType.P2);
		assertEquals(PlayerType.P1, board.check_result());
		board.set_in_board(1, 1, PlayerType.P2);
		assertEquals(PlayerType.P1, board.check_result());
		board.set_in_board(1, 2, PlayerType.P2);
		assertEquals(PlayerType.P1, board.check_result());
		board.set_in_board(2, 2, PlayerType.P2);
		assertEquals(PlayerType.P1, board.check_result());
		assertFalse(board.check_empty());
		assertFalse(board.check_be_full());
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
		board.set_in_board(0, 1, PlayerType.P1);
		board.set_in_board(1, 1, PlayerType.P1);
		board.set_in_board(2, 1, PlayerType.P1);
		// Player 2 wins vertically, the result should be PlayerType.P2
		assertEquals(PlayerType.P1, board.check_result());
		board.set_in_board(0, 2, PlayerType.P2);
		assertEquals(PlayerType.P1, board.check_result());
		board.set_in_board(0, 0, PlayerType.P2);
		assertEquals(PlayerType.P1, board.check_result());
		board.set_in_board(1, 2, PlayerType.P2);
		assertEquals(PlayerType.P1, board.check_result());
		board.set_in_board(2, 0, PlayerType.P2);
		assertEquals(PlayerType.P1, board.check_result());
		board.set_in_board(1, 0, PlayerType.P1);
		assertEquals(PlayerType.P1, board.check_result());
		assertFalse(board.check_empty());
		assertFalse(board.check_be_full());
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
		assertTrue(board.check_empty());
		board.set_in_board(0, 2, PlayerType.P1);
		board.set_in_board(1, 2, PlayerType.P1);
		board.set_in_board(2, 2, PlayerType.P1);
		// Player 2 wins vertically, the result should be PlayerType.P2
		assertEquals(PlayerType.P1, board.check_result());
		board.set_in_board(0, 0, PlayerType.P2);
		assertEquals(PlayerType.P1, board.check_result());
		board.set_in_board(0, 1, PlayerType.P2);
		assertEquals(PlayerType.P1, board.check_result());
		board.set_in_board(2, 0, PlayerType.P2);
		assertEquals(PlayerType.P1, board.check_result());
		board.set_in_board(2, 1, PlayerType.P2);
		assertEquals(PlayerType.P1, board.check_result());
		board.set_in_board(1, 0, PlayerType.P1);
		assertEquals(PlayerType.P1, board.check_result());
		assertFalse(board.check_empty());
		assertFalse(board.check_be_full());
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
		board.set_in_board(0, 0, PlayerType.P1);
		board.set_in_board(1, 1, PlayerType.P1);
		board.set_in_board(2, 2, PlayerType.P1);
		// Player 1 wins diagonally, the result should be PlayerType.P1
		assertEquals(PlayerType.P1, board.check_result());
		board.set_in_board(0, 2, PlayerType.P2);
		assertEquals(PlayerType.P1, board.check_result());
		board.set_in_board(1, 2, PlayerType.P2);
		assertEquals(PlayerType.P1, board.check_result());
		board.set_in_board(1, 0, PlayerType.P2);
		assertEquals(PlayerType.P1, board.check_result());
		board.set_in_board(2, 0, PlayerType.P2);
		assertEquals(PlayerType.P1, board.check_result());
		assertFalse(board.check_empty());
		assertFalse(board.check_be_full());
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
		board.set_in_board(0, 0, PlayerType.P2);
		board.set_in_board(1, 1, PlayerType.P2);
		board.set_in_board(2, 2, PlayerType.P2);
		// Player 1 wins diagonally, the result should be PlayerType.P1
		assertEquals(PlayerType.P2, board.check_result());
		board.set_in_board(1, 2, PlayerType.P1);
		assertEquals(PlayerType.P2, board.check_result());
		board.set_in_board(0, 1, PlayerType.P1);
		assertEquals(PlayerType.P2, board.check_result());
		board.set_in_board(1, 0, PlayerType.P1);
		assertEquals(PlayerType.P2, board.check_result());
		board.set_in_board(2, 1, PlayerType.P1);
		assertEquals(PlayerType.P2, board.check_result());
		assertFalse(board.check_empty());
		assertFalse(board.check_be_full());
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
		board.set_in_board(0, 2, PlayerType.P2);
		board.set_in_board(1, 1, PlayerType.P2);
		board.set_in_board(2, 0, PlayerType.P2);
		// Player 2 wins anti-diagonally, the result should be PlayerType.P2
		assertEquals(PlayerType.P2, board.check_result());
		board.set_in_board(0, 1, PlayerType.P1);
		assertEquals(PlayerType.P2, board.check_result());
		board.set_in_board(1, 0, PlayerType.P1);
		assertEquals(PlayerType.P2, board.check_result());
		board.set_in_board(2, 2, PlayerType.P1);
		assertEquals(PlayerType.P2, board.check_result());
		board.set_in_board(2, 1, PlayerType.P1);
		assertEquals(PlayerType.P2, board.check_result());
		board.set_in_board(0, 1, PlayerType.P2);
		assertEquals(PlayerType.P2, board.check_result());
		assertFalse(board.check_empty());
		assertFalse(board.check_be_full());
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
		board.set_in_board(0, 2, PlayerType.P1);
		board.set_in_board(1, 1, PlayerType.P1);
		board.set_in_board(2, 0, PlayerType.P1);
		// Player 2 wins anti-diagonally, the result should be PlayerType.P2
		assertEquals(PlayerType.P1, board.check_result());
		board.set_in_board(2, 2, PlayerType.P2);
		assertEquals(PlayerType.P1, board.check_result());
		board.set_in_board(0, 0, PlayerType.P2);
		assertEquals(PlayerType.P1, board.check_result());
		board.set_in_board(0, 1, PlayerType.P2);
		assertEquals(PlayerType.P1, board.check_result());
		board.set_in_board(1, 0, PlayerType.P2);
		assertEquals(PlayerType.P1, board.check_result());
		board.set_in_board(2, 1, PlayerType.P1);
		assertEquals(PlayerType.P1, board.check_result());
		board.set_in_board(1, 2, PlayerType.P1);
		assertEquals(PlayerType.P1, board.check_result());
		assertFalse(board.check_empty());
		assertTrue(board.check_be_full());
	}
}