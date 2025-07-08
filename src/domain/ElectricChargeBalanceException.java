package domain;

import java.io.Serializable;

public class ElectricChargeBalanceException extends Exception implements Serializable {

	public static final String ARCHIVO_NO_ENCONTRADO = "Archivo no encontrado.";
	
	public ElectricChargeBalanceException(String message) {
		super(message);
	}
	
    
}
