package com.mvcvideoplayer.utils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class FormataData {

	public static String retornaData(long pData){
	
	    DateFormat formatData = new SimpleDateFormat("dd/MM/yyyy");  
	    String data = formatData.format(new Date(pData));
	    
	    return data;
    
	}

	public static String retornaHora(long pData){
		
	    SimpleDateFormat formatHora = new SimpleDateFormat("HH:mm:ss");  
	    String hora = formatHora.format(new Date(pData));  
	    
	    return hora;
	    
	}
	
}
