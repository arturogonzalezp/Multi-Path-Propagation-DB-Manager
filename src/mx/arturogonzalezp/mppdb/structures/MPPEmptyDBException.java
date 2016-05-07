package mx.arturogonzalezp.mppdb.structures;

public class MPPEmptyDBException extends Exception{
	public MPPEmptyDBException(){
		super("The database is empty");
	}
}
