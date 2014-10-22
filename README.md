PorExtenso
==========

PorExtenso foi desenvolvida para escrever valores monetários por extenso automaticamente e pode escrever valores da ordem de setilhões

O principal objetivo dessa micro-biblioteca é gerar expressões em português gramaticalmente correto, de maneira que possam ser usadas em documentos formais, como contratos.  

*Since this library is intended only for Portuguese speakers, there's no documentation in English.*

## Quick Start

```

	CurrencyWriter cw = CurrencyWriter.getInstance();
	
	String extenso = cw.write(new BigDecimal("234.89"));
	System.out.println(extenso);
	
	extenso = cw.write(new BigDecimal("130000000000"));
	System.out.println(extenso);
```

Resultado:

```

	duzentos e trinta e quatro reais e oitenta e nove centavos
	cento e trinta bilhões de reais
```

Veja mais exemplos no diretório `samples`.


## Licença

PorExtenso é distribuída como software livre (licença MIT).
