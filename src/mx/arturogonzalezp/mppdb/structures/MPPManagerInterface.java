package mx.arturogonzalezp.mppdb.structures;
import java.nio.file.Path;
import java.util.List;

import com.google.gson.Gson;

public interface MPPManagerInterface<T extends MPPItemInterface>{

	public boolean isOpen();
	public boolean isEmpty();
	public Gson getGson();
	public void setGson(Gson gson);
	public boolean saveDB() throws MPPEmptyDBException;
	public boolean openDB(String fileName) throws MPPNoDBInPathException,MPPOpenDBException,MPPDifferentDBItemType;
	public boolean openDB(Path filePath) throws MPPNoDBInPathException,MPPOpenDBException,MPPDifferentDBItemType;
	public boolean openDB(MPPLoaderInterface<T> loader);
	public void newDB(T rootItem, String dbName);
	public void closeDB();
	public T searchByID(String ID);
	public T searchByID(Number ID);
	public <V> List<T> searchBy(String property, V value);
	public List<T> getChildrenOf(String ID);
	public List<T> getChildrenOf(Number ID);
	public List<T> getChildrenOf(T item);
	public int size();
	public List<T> getAllItems();
	public boolean addItem(T item, String parentID);
	public boolean addItem(T item, Number parentID);
	public boolean addItem(T item, T parentItem);

	
	//public boolean removeItem(T item);
	//public boolean removeItem(String itemID);
	//public boolean removeItem(Number itemID);
	//public boolean saveDB(String filePathStr) throws EmptyDBException;
	
	/*public boolean prepareItemToAdd(T item);
	public boolean isItemToAdd();
	public boolean addItemToFather(T item);*/
}