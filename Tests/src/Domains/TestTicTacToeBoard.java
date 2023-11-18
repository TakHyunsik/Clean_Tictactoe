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
		System.out.println()
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

}

// assertArrayEquals(a,b) : ?�� a??b?? ??????? ???
// assertEquals(a,b) : ??? a??b?? ???? ?????? ???
// assertSame(a,b) : ??? a??b?? ???? ??????? ???
// assertTrue(a) : a?? ?????? ???
// assertNotNull(a) : a????? null?? ????? ???
// fail("Not yet implemented");