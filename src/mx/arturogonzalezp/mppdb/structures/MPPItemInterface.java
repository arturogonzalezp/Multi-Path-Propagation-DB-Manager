package mx.arturogonzalezp.mppdb.structures;

public interface MPPItemInterface extends Comparable<MPPItemInterface>{
	public Number getID();
	public void setID(Number ID);
	public <V> boolean valueEquals(String property, V value);
}
