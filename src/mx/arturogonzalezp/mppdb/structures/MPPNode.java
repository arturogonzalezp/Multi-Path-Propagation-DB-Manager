package mx.arturogonzalezp.mppdb.structures;

import java.util.ArrayList;
import java.util.List;

public class MPPNode <T extends MPPNodeItemInterface> implements Comparable<MPPNode<T>>{
	private List<MPPNode<T>> childNodes;
	private T item;
	private int storageKey;
	private int level;
	public MPPNode(){
		this.setItem(null);
		this.setChildNodes(new ArrayList<MPPNode<T>>());
		this.setStorageKey(0);
		this.setLevel(-1);
	}
	public MPPNode(T nodeItem){
		this();
		this.setItem(nodeItem);
		this.setStorageKey(createStorageKey());
	}
	public List<MPPNode<T>> getChildNodes() {
		return childNodes;
	}
	public void setChildNodes(List<MPPNode<T>> childNodes) {
		this.childNodes = childNodes;
	}
	public T getItem() {
		return item;
	}
	public void setItem(T item) {
		this.item = item;
	}
	public int getStorageKey() {
		return storageKey;
	}
	public void setStorageKey(int storageKey) {
		this.storageKey = storageKey;
	}
	public int getLevel() {
		return level;
	}
	public void setLevel(int level) {
		this.level = level;
	}
	private int createStorageKey(){
		return (int) (this.getItem().getID().longValue() % 1000);
	}
	public boolean addChildNode(MPPNode<T> childNode){
		if(this.getLevel() > -1){
			childNode.setLevel(this.getLevel()+1);
			this.getChildNodes().add(childNode);
			return true;
		}
		return false;
	}
	public MPPNode<T> getChildNodeAt(int index){
		return this.getChildNodes().get(index);
	}
	public int getChildNodesCount(){
		return this.getChildNodes().size();
	}
	public void removeChildNodeAt(int index){
		this.getChildNodes().remove(index);
	}
	public String toString(){
		return "Storage Key: " + this.getStorageKey() + "\nLevel: " + this.getLevel() + "\nChild Nodes Count: " + this.getChildNodesCount() + "\nItem:\n " + this.getItem().toString() + "\n";
	}
	@Override
	public int compareTo(MPPNode<T> node) {
		if(this.getItem().compareTo(node.getItem()) < 0){
			return -1;
		}else if(this.getItem().compareTo(node.getItem()) > 0){
			return 1;
		}
		return 0;
	}
}
