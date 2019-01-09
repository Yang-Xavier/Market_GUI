package test;

import java.math.BigDecimal;

public class FloatTest {
	public static void main(String[] args) {
		float a = 1000000000;
		
		System.out.println((new BigDecimal(a)).toPlainString());
	}
}
