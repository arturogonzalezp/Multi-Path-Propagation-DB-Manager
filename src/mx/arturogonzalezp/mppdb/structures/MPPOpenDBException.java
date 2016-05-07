package mx.arturogonzalezp.mppdb.structures;

public class MPPOpenDBException extends Exception{
	public MPPOpenDBException(){
		super("A DB is already opened in this manager");
	}
}
