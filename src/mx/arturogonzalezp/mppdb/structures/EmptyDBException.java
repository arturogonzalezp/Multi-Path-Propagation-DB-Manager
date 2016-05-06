package mx.arturogonzalezp.mppdb.structures;

public class EmptyDBException extends Exception{
	public EmptyDBException(){
		super("The database is empty");
	}
}
