/*
This file is part of PorExtenso.

    PorExtenso is free software: you can redistribute it and/or modify
    it under the terms of the GNU Lesser General Public License as published by
    the Free Software Foundation, either version 3 of the License, or
    (at your option) any later version.

    PorExtenso is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU Lesser General Public License for more details.

    You should have received a copy of the GNU Lesser General Public License
    along with PorExtenso.  If not, see <http://www.gnu.org/licenses/>.
    
    
Copyright 2008, Marcelo Criscuolo.    
*/

package br.com.jsti.porextenso;

import static org.junit.Assert.assertTrue;

import java.math.BigDecimal;

import org.junit.Test;

public class Results {
	
	private CurrencyWriter cw = new CurrencyWriter();
	
	@Test
	public void results() {
		assertTrue("".equals(cw.write(new BigDecimal(-1))));
		assertTrue("".equals(cw.write(new BigDecimal(0))));
		assertTrue("um centavo".equals(cw.write(new BigDecimal("0.01"))));
		assertTrue("dois centavos".equals(cw.write(new BigDecimal("0.02"))));
		assertTrue("cinco centavos".equals(cw.write(new BigDecimal("0.05"))));
		assertTrue("dez centavos".equals(cw.write(new BigDecimal("0.10"))));
		assertTrue("onze centavos".equals(cw.write(new BigDecimal("0.11"))));
		assertTrue("dezessete centavos".equals(cw.write(new BigDecimal("0.17"))));
		assertTrue("vinte centavos".equals(cw.write(new BigDecimal("0.20"))));
		assertTrue("vinte e um centavos".equals(cw.write(new BigDecimal("0.21"))));
		assertTrue("trinta e sete centavos".equals(cw.write(new BigDecimal("0.37"))));
		assertTrue("cinquenta centavos".equals(cw.write(new BigDecimal("0.50"))));
		assertTrue("cinquenta e um centavos".equals(cw.write(new BigDecimal("0.51"))));
		assertTrue("noventa e nove centavos".equals(cw.write(new BigDecimal("0.99"))));
		assertTrue("um real".equals(cw.write(new BigDecimal("1"))));
		assertTrue("um real e um centavo".equals(cw.write(new BigDecimal("1.01"))));
		assertTrue("um real e cinco centavos".equals(cw.write(new BigDecimal("1.05"))));
		assertTrue("um real e dez centavos".equals(cw.write(new BigDecimal("1.10"))));
		assertTrue("um real e cinquenta centavos".equals(cw.write(new BigDecimal("1.50"))));
		assertTrue("um real e noventa e nove centavos".equals(cw.write(new BigDecimal("1.99"))));
		assertTrue("dois reais".equals(cw.write(new BigDecimal("2"))));
		assertTrue("dois reais e um centavo".equals(cw.write(new BigDecimal("2.01"))));
		assertTrue("dois reais e dez centavos".equals(cw.write(new BigDecimal("2.10"))));
		assertTrue("cinco reais".equals(cw.write(new BigDecimal("5"))));
		assertTrue("nove reais".equals(cw.write(new BigDecimal("9"))));
		assertTrue("dez reais".equals(cw.write(new BigDecimal("10"))));
		assertTrue("onze reais".equals(cw.write(new BigDecimal("11"))));
		assertTrue("onze reais e onze centavos".equals(cw.write(new BigDecimal("11.11"))));
		assertTrue("dezenove reais e quinze centavos".equals(cw.write(new BigDecimal("19.15"))));
		assertTrue("vinte reais".equals(cw.write(new BigDecimal("20"))));
		assertTrue("vinte e um reais".equals(cw.write(new BigDecimal("21"))));
		assertTrue("vinte e um reais e vinte e um centavos".equals(cw.write(new BigDecimal("21.21"))));
		assertTrue("vinte e dois reais".equals(cw.write(new BigDecimal("22"))));
		assertTrue("trinta reais".equals(cw.write(new BigDecimal("30"))));
		assertTrue("quarenta reais".equals(cw.write(new BigDecimal("40"))));
		assertTrue("sessenta reais".equals(cw.write(new BigDecimal("60"))));
		assertTrue("noventa reais".equals(cw.write(new BigDecimal("90"))));
		assertTrue("noventa e um reais".equals(cw.write(new BigDecimal("91"))));
		assertTrue("noventa e nove reais".equals(cw.write(new BigDecimal("99"))));
		assertTrue("cem reais".equals(cw.write(new BigDecimal("100"))));
		assertTrue("cem reais e um centavo".equals(cw.write(new BigDecimal("100.01"))));
		assertTrue("cento e um reais".equals(cw.write(new BigDecimal("101"))));
		assertTrue("cento e dois reais".equals(cw.write(new BigDecimal("102"))));
		assertTrue("cento e um reais e um centavo".equals(cw.write(new BigDecimal("101.01"))));
		assertTrue("cento e trinta e sete reais".equals(cw.write(new BigDecimal("137"))));
		assertTrue("cento e trinta e sete reais e trinta e nove centavos".equals(cw.write(new BigDecimal("137.39"))));
		assertTrue("duzentos reais".equals(cw.write(new BigDecimal("200"))));
		assertTrue("duzentos e trinta e quatro reais".equals(cw.write(new BigDecimal("234"))));
		assertTrue("trezentos reais".equals(cw.write(new BigDecimal("300"))));
		assertTrue("quatrocentos reais".equals(cw.write(new BigDecimal("400"))));
		assertTrue("quinhentos reais".equals(cw.write(new BigDecimal("500"))));
		assertTrue("novecentos reais".equals(cw.write(new BigDecimal("900"))));
		assertTrue("um mil reais".equals(cw.write(new BigDecimal("1000"))));
		assertTrue("um mil e um reais".equals(cw.write(new BigDecimal("1001"))));
		assertTrue("um mil reais e um centavo".equals(cw.write(new BigDecimal("1000.01"))));
		assertTrue("um mil e cento e trinta e quatro reais e nove centavos".equals(cw.write(new BigDecimal("1134.09"))));
		assertTrue("dois mil reais".equals(cw.write(new BigDecimal("2000"))));
		assertTrue("onze mil reais".equals(cw.write(new BigDecimal("11000"))));
		assertTrue("vinte e dois mil reais".equals(cw.write(new BigDecimal("22000"))));
		assertTrue("cento e cinco mil reais".equals(cw.write(new BigDecimal("105000"))));
		assertTrue("cento e cinco mil reais e vinte e um centavos".equals(cw.write(new BigDecimal("105000.21"))));
		assertTrue("um milhão de reais".equals(cw.write(new BigDecimal("1000000"))));
		assertTrue("um milhão de reais e um centavo".equals(cw.write(new BigDecimal("1000000.01"))));
		assertTrue("dez milhões de reais".equals(cw.write(new BigDecimal("10000000"))));
		assertTrue("onze milhões e duzentos e trinta e quatro mil e setecentos e dezoito reais e noventa e sete centavos".equals(cw.write(new BigDecimal("11234718.97"))));
		assertTrue("cem milhões de reais".equals(cw.write(new BigDecimal("100000000"))));
		assertTrue("cem milhões e duzentos e trinta e quatro mil e um reais".equals(cw.write(new BigDecimal("100234001"))));
		assertTrue("cem milhões e duzentos e trinta e quatro mil e um reais e vinte e cinco centavos".equals(cw.write(new BigDecimal("100234001.25"))));
		assertTrue("um bilhão de reais".equals(cw.write(new BigDecimal("1000000000"))));
		assertTrue("um bilhão e um milhão e um mil e um reais".equals(cw.write(new BigDecimal("1001001001"))));
		assertTrue("um trilhão de reais".equals(cw.write(new BigDecimal("1000000000000"))));
		assertTrue("um quatrilhão de reais".equals(cw.write(new BigDecimal("1000000000000000"))));
		assertTrue("um quintilhão de reais".equals(cw.write(new BigDecimal("1000000000000000000"))));
		assertTrue("um sextilhão de reais".equals(cw.write(new BigDecimal("1000000000000000000000"))));
		assertTrue("um setilhão de reais".equals(cw.write(new BigDecimal("1000000000000000000000000"))));
		assertTrue("duzentos e trinta e quatro setilhões de reais".equals(cw.write(new BigDecimal("234000000000000000000000000"))));
		assertTrue("novecentos e noventa e nove setilhões e novecentos e noventa e nove sextilhões e novecentos e noventa e nove quintilhões e novecentos e noventa e nove quatrilhões e novecentos e noventa e nove trilhões e novecentos e noventa e nove bilhões e novecentos e noventa e nove milhões e novecentos e noventa e nove mil e novecentos e noventa e nove reais".equals(cw.write(new BigDecimal("999999999999999999999999999"))));
		assertTrue("novecentos e noventa e nove setilhões e novecentos e noventa e nove sextilhões e novecentos e noventa e nove quintilhões e novecentos e noventa e nove quatrilhões e novecentos e noventa e nove trilhões e novecentos e noventa e nove bilhões e novecentos e noventa e nove milhões e novecentos e noventa e nove mil e novecentos e noventa e nove reais e noventa e nove centavos".equals(cw.write(new BigDecimal("999999999999999999999999999.99"))));
	}
}
