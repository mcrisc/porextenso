package br.com.jsti.porextenso;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;


public class BroaderVariety {
	public static void main(String[] args) {
		String[] valores = new String[] {"5", "0.10", "1232.50", "130000000000"};
		List<BigDecimal> quantias = new ArrayList<BigDecimal>();
		
		for (String v : valores) {
			quantias.add(new BigDecimal(v));
		}
		
		CurrencyWriter cw = CurrencyWriter.getInstance();
		
		for (BigDecimal quantia : quantias) {
			System.out.println(cw.write(quantia));
		}
	}
}
