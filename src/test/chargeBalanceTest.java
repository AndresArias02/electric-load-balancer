package test;
import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import domain.Data;
import domain.ElectricChargeBalance;
import domain.Phase;


public class chargeBalanceTest {
    private ElectricChargeBalance prueba;
	@Before
	public void setUp() {
		prueba = new ElectricChargeBalance();
	}

	@Test
	public void shouldBeTheSame() {
		Double[][] board = prueba.getBoard();
        String value = String.valueOf(board[2][0]);
		assertEquals(value,"5.0");
	}

	@Test
	public void shouldChangeTheValue() {
		prueba.changeValue(0, 2, "2.50");
		prueba.changeValue(6, 2, "0.05");
		prueba.changeValue(21, 2, "2.72");
		prueba.changeValue(24, 2, "0.04");
		prueba.changeValue(27, 2, "0.14");
		prueba.changeValue(30, 2, "0.16");
		Double [][] board = prueba.getBoard();
		String value1 = String.valueOf(board[45][7]);
		assertEquals(value1,"200.0");
		String value2 = String.valueOf(board[2][1]);
		assertEquals(value2,"3.0");
	}

	

   
}
