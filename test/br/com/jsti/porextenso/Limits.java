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

import java.math.BigDecimal;

import org.junit.Test;

public class Limits {
	private CurrencyWriter cw = new CurrencyWriter();
	@Test
	public void limits() {
		cw.write(new BigDecimal(1000));
		
		cw.write(new BigDecimal(pow10(15)));
		cw.write(new BigDecimal(pow10(26)));
		cw.write(new BigDecimal(pow10(26) + ".57002"));
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void exception() {
		cw.write(new BigDecimal(pow10(27)));
	}
	
	/**
	 * Retorna uma String formada pelo número 1 seguindo de <code>exponent</code> zeros.
	 */
	private String pow10(int exponent) {
		StringBuffer sb = new StringBuffer("1");
		
		for (int i=0; i < exponent; i++) {
			sb.append('0');
		}
		
		return sb.toString();
	}
}
