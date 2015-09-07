package br.com.jsti.porextenso;

import java.math.BigDecimal;

public class SimpleUsage {
	public static void main(String[] args) {
		
		CurrencyWriter cw = CurrencyWriter.getInstance();
		
		String extenso = cw.write(new BigDecimal("234.89"));
		System.out.println(extenso);

		extenso = cw.write(new BigDecimal("130000000000"));
		System.out.println(extenso);
	}
}
