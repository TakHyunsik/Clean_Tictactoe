package Domains;

import static org.junit.Assert.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import Entities.FivewoodBoard;

class TestFiveWoodBoard {
	FivewoodBoard board;

	@BeforeEach
	void setUp() throws Exception {
		board = new FivewoodBoard();
	}

	@Test
	void test_size() {
		assertEquals(15, board.get_size());
	}

}