package mx.arturogonzalezp.mppdb.structures;

public class MPPNoDBInPathException extends Exception{
	public MPPNoDBInPathException() {
		super("The database you are trying to open does not exist");
	}
}
