package mx.arturogonzalezp.mppdb.structures;

import java.io.IOException;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class MPPManager<T extends MPPItemInterface> implements MPPManagerInterface<T>{
	private MPPGraph<T> innerGraph;
	public MPPManager(){
		this.setInnerGraph(null);
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
		this.setInnerGraph(new MPPGraph<T>(rootItem, dbName));
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
	public boolean saveDB() throws EmptyDBException {
		if(this.isEmpty()){
			throw new EmptyDBException();
		}else{
			// Save database
			Gson gson = new Gson();
			List<String> textToSave = new ArrayList<String>();
			Type type = new TypeToken<T>(){}.getType();
			textToSave.add(gson.toJson(this.getAllItems(),type));
			Path filePath = Paths.get("files/db/");
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
	public boolean openDB(Path filePath) {
		return false;
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
	public T searchBy(String property, String value){
		return null;
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
