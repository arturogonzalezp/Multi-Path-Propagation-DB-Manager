package mx.arturogonzalezp.mppdb.structures;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Array;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.net.URI;
import java.nio.charset.Charset;
import java.nio.file.FileSystem;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.WatchKey;
import java.nio.file.WatchService;
import java.nio.file.WatchEvent.Kind;
import java.nio.file.WatchEvent.Modifier;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.internal.LinkedTreeMap;
import com.google.gson.reflect.TypeToken;

public class MPPManager<T extends MPPItemInterface> implements MPPManagerInterface<T>{
	private MPPGraph<T> innerGraph;
	private Class<T> itemClass;
	private Class<T[]> itemClassIterator;
	private Gson gson;
	private final String defaultSavePath = "files/db/";
	private String dbName;
	public MPPManager(Class<T> itemClass, Class<T[]> itemClassIterator){
		this.setInnerGraph(null);
		this.setGson(new Gson());
		this.setItemClass(itemClass);
		this.setItemClassIterator(itemClassIterator);
	}
	public MPPManager(MPPLoaderInterface<T> loader){
		// Constructor with loader
	}
	public MPPGraph<T> getInnerGraph() {
		return innerGraph;
	}
	public void setInnerGraph(MPPGraph<T> innerGraph) {
		this.innerGraph = innerGraph;
	}
	@Override
	public Gson getGson() {
		return gson;
	}
	@Override
	public void setGson(Gson gson) {
		this.gson = gson;
	}
	public Class<T> getItemClass() {
		return itemClass;
	}
	public void setItemClass(Class<T> itemClass) {
		this.itemClass = itemClass;
	}
	public Class<T[]> getItemClassIterator() {
		return itemClassIterator;
	}
	public void setItemClassIterator(Class<T[]> itemClassIterator) {
		this.itemClassIterator = itemClassIterator;
	}
	public String getDbName() {
		return dbName;
	}
	public void setDbName(String dbName) {
		this.dbName = dbName;
	}
	@Override
	public boolean isOpen() {
		if(this.getInnerGraph() != null){
			return true;
		}else{
			return false;
		}
	}
	@Override
	public boolean isEmpty() {
		if(this.isOpen()){
			if(this.getInnerGraph().isEmpty()){
				return true;
			}else{
				return false;
			}
		}else{
			return true;
		}
	}
	@Override
	public void newDB(T rootItem, String dbName){
		this.setDbName(dbName);
		this.setInnerGraph(new MPPGraph<T>(rootItem, this.getDbName()));
	}
	/*@Override
	public boolean saveDB(String filePathStr) throws EmptyDBException{
		if(this.isEmpty()){
			throw new EmptyDBException();
		}else{
			// Save database
			return true;
		}
	}*/
	@Override
	public boolean saveDB() throws MPPEmptyDBException {
		if(this.isEmpty()){
			throw new MPPEmptyDBException();
		}else{
			List<String> textToSave = new ArrayList<String>();
			Type type = new TypeToken<T>(){}.getType();
			textToSave.add(this.getItemClass().getSimpleName());
			textToSave.add(this.getGson().toJson(this.getAllItems(),type));
			textToSave.add(this.getGson().toJson(this.getInnerGraph().getEdgeList()));
			Path filePath = Paths.get(this.defaultSavePath);
			try{
				if(Files.notExists(filePath)){
					Files.createDirectories(filePath);
				}
				Path file = filePath.resolve(this.getInnerGraph().getGraphTitle() + ".json");
				if(Files.notExists(file)){
					Files.createFile(file);
				}
				Files.write(file, textToSave, Charset.forName("UTF-8"));
			}catch(IOException e){
				e.printStackTrace();
				return false;
			}
			return true;
		}
	}
	@Override
	public boolean openDB(String fileName) throws MPPNoDBInPathException, MPPOpenDBException, MPPDifferentDBItemType{
		if(!this.isOpen()){
			List<String> textFromFile = new ArrayList<String>();
			Path filePath = Paths.get(this.defaultSavePath).resolve(fileName + ".json");
			if(Files.notExists(filePath)){
				throw new MPPNoDBInPathException();
			}else{
				try {
					textFromFile = Files.readAllLines(filePath, Charset.forName("UTF-8"));
					if(this.getItemClass().equals(Class.forName(textFromFile.get(0)))){
						List<T> itemList = new ArrayList<T>(Arrays.asList(this.getGson().fromJson(textFromFile.get(1), this.getItemClassIterator())));
						List<MPPEdge> edgeList = new ArrayList<MPPEdge>(Arrays.asList(this.getGson().fromJson(textFromFile.get(2),MPPEdge[].class)));
						for (T item : itemList) {
							if(this.isEmpty()){
								this.setInnerGraph(new MPPGraph<T>(item,this.getDbName()));
							}
							for (MPPEdge edge : edgeList) {
								if(item.getID().equals(edge.getItemID())){
									this.getInnerGraph().addChildToParentID(item, edge.getParentID());
									edgeList.remove(edge);
									break;
								}
							}
						}
						
					}else{
						throw new MPPDifferentDBItemType();
					}
				} catch (IOException e) {
					e.printStackTrace();
				} catch (ClassNotFoundException e) {
					throw new MPPDifferentDBItemType();
				}
			}
			return false;
		}else{
			throw new MPPOpenDBException();
		}
	}
	@Override
	public boolean openDB(Path filePath) throws MPPNoDBInPathException, MPPOpenDBException, MPPDifferentDBItemType{
		if(!this.isOpen()){
			List<String> textFromFile = new ArrayList<String>();
			if(Files.notExists(filePath)){
				throw new MPPNoDBInPathException();
			}else{
				try {
					textFromFile = Files.readAllLines(filePath, Charset.forName("UTF-8"));
					if(this.getItemClass().equals(Class.forName(textFromFile.get(0)))){
						List<T> itemList = new ArrayList<T>(Arrays.asList(this.getGson().fromJson(textFromFile.get(1), this.getItemClassIterator())));
						List<MPPEdge> edgeList = new ArrayList<MPPEdge>(Arrays.asList(this.getGson().fromJson(textFromFile.get(2),MPPEdge[].class)));
						for (T item : itemList) {
							if(this.isEmpty()){
								this.setInnerGraph(new MPPGraph<T>(item,this.getDbName()));
							}
							for (MPPEdge edge : edgeList) {
								if(item.getID().equals(edge.getItemID())){
									this.getInnerGraph().addChildToParentID(item, edge.getParentID());
									edgeList.remove(edge);
									break;
								}
							}
						}
						
					}else{
						throw new MPPDifferentDBItemType();
					}
				} catch (IOException e) {
					e.printStackTrace();
				} catch (ClassNotFoundException e) {
					throw new MPPDifferentDBItemType();
				}
			}
			return false;
		}else{
			throw new MPPOpenDBException();
		}
	}
	@Override
	public boolean openDB(MPPLoaderInterface<T> loader) {
		// Loader
		return false;
	}
	@Override
	public T searchByID(String ID){
		if(this.isEmpty()){
			return null;
		}else{
			MPPNode<T> tempNode = this.getInnerGraph().searchNodeByID(ID);
			if(tempNode != null){
				return tempNode.getItem();
			}else{
				return null;
			}
		}
	}
	@Override
	public T searchByID(Number ID){
		if(this.isEmpty()){
			return null;
		}else{
			MPPNode<T> tempNode = this.getInnerGraph().searchNodeByID(ID);
			if(tempNode != null){
				return tempNode.getItem();
			}else{
				return null;
			}
		}
	}
	@Override
	public <V> List<T> searchBy(String property, V value){
		List<T> tempItemList = new ArrayList<T>();
		for (MPPNode<T> node : this.getInnerGraph().searchNodesBy(property, value)) {
			tempItemList.add(node.getItem());
		}
		return tempItemList;
	}
	@Override
	public int size() {
		if (this.isEmpty()) {
			return 0;
		}else{
			return this.getInnerGraph().getNodesCount();
		}
	}
	@Override
	public List<T> getAllItems(){
		if(this.isEmpty()){
			return null;
		}else{
			List<T> tempList = new ArrayList<T>();
			List<MPPNode<T>> tempNodeList = this.getInnerGraph().getPreorderList();
			Collections.sort(tempNodeList, new MPPNodeLevelComparator<T>());
			for(MPPNode<T> node : tempNodeList){
				tempList.add(node.getItem());
			}
			return tempList;
		}
	}
	@Override
	public boolean addItem(T item, String parentID) {
		if(this.isOpen()){
			this.getInnerGraph().addChildToParentID(item, parentID);
			return true;
		}else{
			return false;
		}
	}
	@Override
	public boolean addItem(T item, Number parentID) {
		if(this.isOpen()){
			this.getInnerGraph().addChildToParentID(item, parentID);
			return true;
		}else{
			return false;
		}
	}
	@Override
	public boolean addItem(T item, T parentItem) {
		if(this.isOpen()){
			this.getInnerGraph().addChildToParentID(item, parentItem.getID());
			return true;
		}else{
			return false;
		}
	}
	@Override
	public List<T> getChildrenOf(String ID) {
		MPPNode<T> tempNode = this.getInnerGraph().searchNodeByID(ID);
		if(tempNode != null){
			List<T> tempItems = new ArrayList<T>();
			for (MPPNode<T> node : tempNode.getChildNodes()) {
				tempItems.add(node.getItem());
			}
			return tempItems;
		}else{
			return null;
		}
	}
	@Override
	public List<T> getChildrenOf(Number ID) {
		MPPNode<T> tempNode = this.getInnerGraph().searchNodeByID(ID);
		if(tempNode != null){
			List<T> tempItems = new ArrayList<T>();
			for (MPPNode<T> node : tempNode.getChildNodes()) {
				tempItems.add(node.getItem());
			}
			return tempItems;
		}else{
			return null;
		}
	}
	@Override
	public List<T> getChildrenOf(T item) {
		MPPNode<T> tempNode = this.getInnerGraph().searchNodeByID(item.getID());
		if(tempNode != null){
			List<T> tempItems = new ArrayList<T>();
			for (MPPNode<T> node : tempNode.getChildNodes()) {
				tempItems.add(node.getItem());
			}
			return tempItems;
		}else{
			return null;
		}
	}
	public String toString(){
		if(this.isOpen()){
			return "DB Name: " + this.getInnerGraph().getGraphTitle() + "\nDB Items: " + this.size();
		}else{
			return "DB is not opened";
		}
	}
	@Override
	public void closeDB(){
		this.setInnerGraph(null);
		System.gc();
	}
}
