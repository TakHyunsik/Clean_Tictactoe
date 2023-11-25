package Domains;

import static org.junit.Assert.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

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

}