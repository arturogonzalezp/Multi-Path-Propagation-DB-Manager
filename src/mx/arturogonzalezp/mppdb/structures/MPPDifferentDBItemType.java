package mx.arturogonzalezp.mppdb.structures;

public class MPPDifferentDBItemType extends Exception{
	public MPPDifferentDBItemType(){
		super("The database you are trying to open is a different type of item class");
	}
}
