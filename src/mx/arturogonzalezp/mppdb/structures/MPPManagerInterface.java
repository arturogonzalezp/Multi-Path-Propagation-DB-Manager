package mx.arturogonzalezp.mppdb.structures;

import java.nio.file.Path;
import java.util.List;

public interface MPPManagerInterface<T extends MPPItemInterface>{

	public boolean isOpen();
	public boolean isEmpty();
	
	public boolean saveDB() throws EmptyDBException;
	//public boolean saveDB(String filePathStr) throws EmptyDBException;
	public boolean openDB(Path filePath);
	public boolean openDB(MPPLoaderInterface<T> loader);
	public void newDB(T rootItem, String dbName);
	public void closeDB();
	
	public T searchByID(String ID);
	public T searchByID(Number ID);
	public T searchBy(String property, String value);
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
	
	
	/*public boolean prepareItemToAdd(T item);
	public boolean isItemToAdd();
	public boolean addItemToFather(T item);*/
}