package domain;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.List;

public class ElectricChargeBalance{
    
    private Board board;

    public ElectricChargeBalance(){
        board = new Board();
    }

    public Double[][] getBoard(){
       return this.board.getBoard();
    }

    public Double getValues(int posx, int posy){
        Double value = board.getValues(posx, posy);
        return value;
    }

    public void changeValue(int x, int y , String number){
        Double newValue = Double.parseDouble(number);
        board.changeValue(x,y,newValue);
    }

    public void calculateValuePower(String number){
        Double value  = Double.parseDouble(number);
        board.calculateValuePower(value);
    }

    public Phase getPhase(int id){
        return board.getPhase(id);
    }

    public String getColorTotalPercentage(){
        return board.getColorTotalPercentage();
    }

    public ElectricChargeBalance getProgram(){
        return this;
    }

    /**
	 * Guarda el estado del programa
	 * @param archivo Archivo donde se guardara
	 * @throws ElectricChargeBlanaceException ARCHIVO_NO_ENCONTRADO En caso de que no se encuentre el archivo en la raiz.
	 */
    public void guarde(File archivo) throws ElectricChargeBalanceException {
    	try {
    		ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(archivo));
    		out.writeObject(this);
    		out.close();
		} catch (Exception e) {
            e.printStackTrace();
		}
    }

    /**
     * Abre el archivo donde esta el estado del programa
     * @param archivo Archivo para leer.
     * @return Programa guardado
     * @throws  ARCHIVO_NO_ENCONTRADO En caso de que no se encuentre el archivo en la raiz.
     */
    public static ElectricChargeBalance abra(File file) throws ElectricChargeBalanceException {
        ElectricChargeBalance program = null;
    	try {
            if(!file.exists()){
            	throw new ElectricChargeBalanceException(ElectricChargeBalanceException.ARCHIVO_NO_ENCONTRADO);
            }
    		ObjectInputStream in = new ObjectInputStream(new FileInputStream(file));
    		program = (ElectricChargeBalance) in.readObject();
    		in.close();
		} catch (Exception e) {
			throw new ElectricChargeBalanceException(ElectricChargeBalanceException.ARCHIVO_NO_ENCONTRADO);
		}
    	return program;
    }	
}