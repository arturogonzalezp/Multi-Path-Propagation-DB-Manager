package mx.arturogonzalezp.mppdb.structures;

public interface MPPManagerInterface{
	public boolean saveInDisk(String filePath, String fileName);
	public int getUsedMemoryCount(int unit);
}
