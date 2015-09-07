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
import java.math.BigInteger;
import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

/**
 * @author Marcelo Criscuolo - criscuolo (dot) marcelo (at) gmail (dot) com
 *
 */
public class CurrencyWriter {

	private static final BigInteger THOUSAND = new BigInteger("1000");
	private static final BigInteger HUNDRED = new BigInteger("100");
	private static final String CENTO = "cento";
	private static final String CEM = "cem";
	/**
	 * Nomes das grandezas numéricas no plural. O mapa a chave do mapa é o
	 * expoente de dez do número e o valor é o seu nome no plural. Por exemplo:
	 * para chave 3 (10^3) o valor é "mil", para a chave 6 (10^6) o valor é
	 * "milhões", e assim por diante. Os nomes foram obtidos de um artigo
	 * publicado na seção Dois Mais Dois na revista SuperInteressante nº. 15, de
	 * dezembro de 1988 (Editora Abril, São Paulo/SP), disponível em
	 * http://www.novomilenio.inf.br/idioma/19881200.htm.
	 */
	private final Map<Integer, String> grandezasPlural = new HashMap<Integer, String>();
	private final Map<Integer, String> grandezasSingular = new HashMap<Integer, String>();
	
	/** Nomes dos números. */
	private final Map<Integer, String> nomes = new HashMap<Integer, String>();
	
	private static final String MOEDA_SINGULAR = "real";
	private static final String MOEDA_PLURAL = "reais";
	
	private static final String FRACAO_SINGULAR = "centavo";
	private static final String FRACAO_PLURAL = "centavos";
	
	private static final String PARTICULA_ADITIVA = "e";
	private static final String PARTICULA_DESCRITIVA = "de";
	
	/**
	 * O conversor reconhece números até a ordem dos setilhões, portanto, o
	 * maior valor suportado atualmente é o representado abaixo.
	 */
	private static final BigDecimal MAX_SUPPORTED_VALUE = new BigDecimal("999999999999999999999999999.99");
	
	
	private static CurrencyWriter instance = null;
	
	public static CurrencyWriter getInstance() {
		if (instance == null) {
			instance = new CurrencyWriter();
		}

		return instance;
	}
	
	private CurrencyWriter() {
		preencherGrandezasPlural();
		preencherGrandezasSingular();
		preencherNomes();
	}

	public String write(final BigDecimal amount) {
		if (null == amount) {throw new IllegalArgumentException();}
		
		/*
		 * TODO substituir o método setScale, abaixo, pela versão cujo
		 * parâmetro de arredondamento é um enum
		 */
		BigDecimal value = amount.setScale(2, BigDecimal.ROUND_HALF_EVEN);
		
		if (value.compareTo(BigDecimal.ZERO) <= 0) {return "";}
		
		if (MAX_SUPPORTED_VALUE.compareTo(value) < 0) {
			throw new IllegalArgumentException("Valor acima do limite suportado.");
		}

		Stack<Integer> decomposed = decompose(value);

		/* Se o número estiver, digamos, na casa dos milhões, a pilha 
		 * deverá conter 4 elementos sendo os dois últimos os das 
		 * centenas e dos centavos, respectivamente. Assim, o expoente de
		 * dez que representa a grandeza no topo da pilha é o número de 
		 * (elementos - 2) * 3 */
		int expoente = 3 * (decomposed.size() - 2); // TODO usar um índice de grupos em vez do expoente 
		
		StringBuffer sb = new StringBuffer();
		int lastNonZeroExponent = -1;
		
		while (!decomposed.empty()) {
			int valor = decomposed.pop();
			
			if (valor > 0) {
				sb.append(" ").append(PARTICULA_ADITIVA).append(" ");
				sb.append(comporNomeGrupos(valor));
				String nomeGrandeza = obterNomeGrandeza(expoente, valor);
				if (nomeGrandeza.length() > 0) {
					sb.append(" ");	
				}
				sb.append(nomeGrandeza);
				
				lastNonZeroExponent = expoente;
			}
			
			switch (expoente) { // TODO ao invés desses switches e ifs, partir para a idéia das "Pendências"; talvez implementá-las com enum
			case 0:
				BigInteger parteInteira = value.toBigInteger();

				if (BigInteger.ONE.equals(parteInteira)) {
					sb.append(" ").append(MOEDA_SINGULAR);
				} else if (parteInteira.compareTo(BigInteger.ZERO) > 0) {
					if (lastNonZeroExponent >= 6) {
						sb.append(" ").append(PARTICULA_DESCRITIVA);
					}
					sb.append(" ").append(MOEDA_PLURAL);
				}
				break;
				
			case -3:
				if (1 == valor) {
					sb.append(" ").append(FRACAO_SINGULAR);
				} else if (valor > 1) {
					sb.append(" ").append(FRACAO_PLURAL);
				}
				break;
			}
			
			expoente -= 3;
		}
		
		return sb.substring(3);
	}
	
	private StringBuffer comporNomeGrupos(int valor) {
		StringBuffer nome = new StringBuffer();

		int centenas = valor - (valor % 100);
		int unidades = valor % 10;
		int dezenas = (valor - centenas) - unidades;
		int duasCasas = dezenas + unidades;
		
		if (centenas > 0) {
			nome.append(" ").append(PARTICULA_ADITIVA).append(" ");
			
			if (100 == centenas) {
				if (duasCasas > 0) {
					nome.append(CENTO);
				} else {
					nome.append(CEM);
				}
			} else {
				nome.append(nomes.get(centenas));
			}
		}
		
		if (duasCasas > 0) {
			nome.append(" ").append(PARTICULA_ADITIVA).append(" ");
			if (duasCasas < 20) {
				nome.append(nomes.get(duasCasas));
			} else {
				if (dezenas > 0) {
					nome.append(nomes.get(dezenas));
				}
				
				if (unidades > 0) {
					nome.append(" ").append(PARTICULA_ADITIVA).append(" ");
					nome.append(nomes.get(unidades));
				}
			}
		}
		
		return nome.delete(0, 3);
	}

	private String obterNomeGrandeza(int exponent, int value) {
		if (exponent < 3) {return "";}
		
		if (1 == value) {
			return grandezasSingular.get(exponent);
		} else {
			return grandezasPlural.get(exponent);
		}
	}

	private Stack<Integer> decompose(BigDecimal value) {
		BigInteger intermediate = value.multiply(new BigDecimal(100)).toBigInteger();
		Stack<Integer> decomposed = new Stack<Integer>();
		
		BigInteger[] result = intermediate.divideAndRemainder(HUNDRED);
		intermediate = result[0];
		decomposed.add(result[1].intValue());
		
		while (intermediate.compareTo(BigInteger.ZERO) > 0) {
			result = intermediate.divideAndRemainder(THOUSAND);
			intermediate = result[0];
			decomposed.add(result[1].intValue());
		}
		
		/*
		 * Se o valor for apenas em centavos, adicionar zero para a casa dos
		 * reais inteiros
		 */
		if (decomposed.size() == 1) {
			decomposed.add(0);
		}
		
		return decomposed;
	}

	private void preencherGrandezasPlural() {
		grandezasPlural.put(3, "mil");
		grandezasPlural.put(6, "milhões");
		grandezasPlural.put(9, "bilhões");
		grandezasPlural.put(12, "trilhões");
		grandezasPlural.put(15, "quatrilhões");
		grandezasPlural.put(18, "quintilhões");
		grandezasPlural.put(21, "sextilhões");
		grandezasPlural.put(24, "setilhões");
	}

	private void preencherGrandezasSingular() {
		grandezasSingular.put(3, "mil");
		grandezasSingular.put(6, "milhão");
		grandezasSingular.put(9, "bilhão");
		grandezasSingular.put(12, "trilhão");
		grandezasSingular.put(15, "quatrilhão");
		grandezasSingular.put(18, "quintilhão");
		grandezasSingular.put(21, "sextilhão");
		grandezasSingular.put(24, "setilhão");
	}

	private void preencherNomes() {
		nomes.put(1, "um");
		nomes.put(2, "dois");
		nomes.put(3, "três");
		nomes.put(4, "quatro");
		nomes.put(5, "cinco");
		nomes.put(6, "seis");
		nomes.put(7, "sete");
		nomes.put(8, "oito");
		nomes.put(9, "nove");
		nomes.put(10, "dez");
		nomes.put(11, "onze");
		nomes.put(12, "doze");
		nomes.put(13, "treze");
		nomes.put(14, "quatorze");
		nomes.put(15, "quinze");
		nomes.put(16, "dezesseis");
		nomes.put(17, "dezessete");
		nomes.put(18, "dezoito");
		nomes.put(19, "dezenove");
		nomes.put(20, "vinte");
		nomes.put(30, "trinta");
		nomes.put(40, "quarenta");
		nomes.put(50, "cinquenta");
		nomes.put(60, "sessenta");
		nomes.put(70, "setenta");
		nomes.put(80, "oitenta");
		nomes.put(90, "noventa");
		nomes.put(200, "duzentos");
		nomes.put(300, "trezentos");
		nomes.put(400, "quatrocentos");
		nomes.put(500, "quinhentos");
		nomes.put(600, "seiscentos");
		nomes.put(700, "setecentos");
		nomes.put(800, "oitocentos");
		nomes.put(900, "novecentos");
	}
}
