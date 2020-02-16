package com.trabalho.cursomc.services;

import java.util.Calendar;
import java.util.Date;

import org.springframework.stereotype.Service;

import com.trabalho.cursomc.domain.PagamentoBoleto;

@Service
public class BoletoService {
	
	//Situação real substituir para o código webService que gera o boleto.

	public static void preencherPagtoBoleto(PagamentoBoleto pagto, Date instanteDoPedido) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(instanteDoPedido);
		cal.add(Calendar.DAY_OF_MONTH, 7);
		pagto.setDataVencimento(cal.getTime());
	}
}
