package mx.arturogonzalezp.mppdb.structures;

import java.util.ArrayList;
import java.util.List;

public class MPPGraph<T extends MPPItemInterface>{
	private MPPNode<T> rootNode;
	private MPPNode<T> pointerNode;
	private List<MPPNodeInsertionPair<T>> insertionStack;
	private MPPHashMap<T> hashMap;
	private String graphTitle;
	private int nodesCount;
	public MPPGraph(){
		this.setInsertionStack(new ArrayList<MPPNodeInsertionPair<T>>());
		this.setRootNode(null);
		this.setPointerNode(null);
		this.setHashMap(new MPPHashMap<T>());
		this.setGraphTitle("MPP Graph");
		this.setNodesCount(0);
	}
	public MPPGraph(String title){
		this();
		this.setGraphTitle(title);
	}
	public MPPGraph(MPPNode<T> rootNode){
		this();
		this.setRootNode(rootNode);
		this.setPointerNode(rootNode);
		this.getHashMap().put(rootNode);
		this.setNodesCount(this.getNodesCount()+1);
	}
	public MPPGraph(T rootItem){
		this(new MPPNode<T>(rootItem));
	}
	public MPPGraph(MPPNode<T> rootNode, String title){
		this(rootNode);
		this.setGraphTitle(title);
	}
	public MPPGraph(T rootItem, String title){
		this(rootItem);
		this.setGraphTitle(title);
	}
	public MPPNode<T> getRootNode() {
		return rootNode;
	}
	public boolean isEmpty(){
		if(this.getRootNode() == null){
			return true;
		}else{
			return false;
		}
	}
	public void setRootNode(MPPNode<T> rootNode){
		this.rootNode = rootNode;
		if(this.rootNode != null){
			this.rootNode.setLevel(0);
		}
	}
	public MPPNode<T> getPointerNode() {
		return pointerNode;
	}
	public void setPointerNode(MPPNode<T> pointerNode) {
		this.pointerNode = pointerNode;
	}
	public List<MPPNodeInsertionPair<T>> getInsertionStack() {
		return insertionStack;
	}
	public void setInsertionStack(List<MPPNodeInsertionPair<T>> insertionStack) {
		this.insertionStack = insertionStack;
	}
	public String getGraphTitle() {
		return graphTitle;
	}
	public void setGraphTitle(String graphTitle) {
		this.graphTitle = graphTitle;
	}
	public MPPHashMap<T> getHashMap() {
		return hashMap;
	}
	public void setHashMap(MPPHashMap<T> hashMap) {
		this.hashMap = hashMap;
	}
	public int getNodesCount() {
		return nodesCount;
	}
	public void setNodesCount(int nodesCount) {
		this.nodesCount = nodesCount;
	}
	public void resetPointer(){
		this.setPointerNode(this.getRootNode());
	}
	public void moveToParent(){
		if(this.getPointerNode() != this.getRootNode()){
			this.setPointerNode(this.getPointerNode().getParentNode());
		}
	}
	public void moveToChildAtPos(int index){
		try {
			this.setPointerNode(this.getPointerNode().getChildNodeAt(index));
		} catch (IndexOutOfBoundsException e) {
			e.printStackTrace();
		}
	}
	public int getChildNodesCount(){
		return this.getPointerNode().getChildNodesCount();
	}
	public MPPNode<T> searchNodeByID(Number ID){
		return this.getHashMap().get(ID);
	}
	public MPPNode<T> searchNodeByID(String ID){
		return this.getHashMap().get(ID);
	}
	public void addChildInActualNode(MPPNode<T> newNode){
		this.getPointerNode().addChildNode(newNode);
		this.getHashMap().put(newNode);
		this.setNodesCount(this.getNodesCount()+1);
	}
	public void addChildInActualNode(T childNodeItem){
		MPPNode<T> tempNode = new MPPNode<T>(childNodeItem);
		this.getPointerNode().addChildNode(tempNode);
		this.getHashMap().put(tempNode);
		this.setNodesCount(this.getNodesCount()+1);
	}
	public void addChildToParent(MPPNode<T> childNode, MPPNode<T> parentNode){
		parentNode.addChildNode(childNode);
		this.getHashMap().put(childNode);
		this.setNodesCount(this.getNodesCount()+1);
	}
	public void addChildToParent(T childNodeItem, MPPNode<T> parentNode){
		MPPNode<T> tempNode = new MPPNode<T>(childNodeItem);
		parentNode.addChildNode(tempNode);
		this.getHashMap().put(tempNode);
		this.setNodesCount(this.getNodesCount()+1);
	}
	public void addChildToParentID(MPPNode<T> childNode, Number parentID){
		this.getInsertionStack().add(0,new MPPNodeInsertionPair<T>(parentID, childNode));
		MPPNode<T> tempParentNode = null;
		for (int i = 0; i < this.getInsertionStack().size(); i++) {
			tempParentNode = this.searchNodeByID(this.getInsertionStack().get(i).getParentID());
			if(tempParentNode != null){
				this.addChildToParent(this.getInsertionStack().get(i).getNode(), tempParentNode);
				this.getInsertionStack().remove(i);
			}
			tempParentNode = null;
		}
	}
	public void addChildToParentID(T childNodeItem, Number parentID){
		this.getInsertionStack().add(0,new MPPNodeInsertionPair<T>(parentID, new MPPNode<T>(childNodeItem)));
		MPPNode<T> tempParentNode = null;
		for (int i = 0; i < this.getInsertionStack().size(); i++) {
			tempParentNode = this.searchNodeByID(this.getInsertionStack().get(i).getParentID());
			if(tempParentNode != null){
				this.addChildToParent(this.getInsertionStack().get(i).getNode(), tempParentNode);
				this.getInsertionStack().remove(i);
			}
			tempParentNode = null;
		}
	}
	public void addChildToParentID(T childNodeItem, String parentID){
		this.getInsertionStack().add(0,new MPPNodeInsertionPair<T>(parentID, new MPPNode<T>(childNodeItem)));
		MPPNode<T> tempParentNode = null;
		for (int i = 0; i < this.getInsertionStack().size(); i++) {
			tempParentNode = this.searchNodeByID(this.getInsertionStack().get(i).getParentID());
			if(tempParentNode != null){
				this.addChildToParent(this.getInsertionStack().get(i).getNode(), tempParentNode);
				this.getInsertionStack().remove(i);
			}
			tempParentNode = null;
		}
	}
	public MPPAdjacencyMatrix<T> getAdjacencyMatrix(){
		return new MPPAdjacencyMatrix<T>(this.getPreorderList());
	}
	public List<MPPNode<T>> getPreorderList(){
		List<MPPNode<T>> tempPreorderList = new ArrayList<MPPNode<T>>();
		this.getPreorderListRecursive(this.getRootNode(),tempPreorderList);
		return tempPreorderList;
	}
	private void getPreorderListRecursive(MPPNode<T> node,List<MPPNode<T>> preorderList){
		if(node != null){
			preorderList.add(node);
			for(MPPNode<T> n : node.getChildNodes()){
				this.getPreorderListRecursive(n, preorderList);
			}
		}
	}
	public void printGraphPreOrder(){
		this.printGraphPreOrderRecursive(this.getRootNode());
	}
	private void printGraphPreOrderRecursive(MPPNode<T> node){
		if(node != null){
			System.out.println(node);
			for(MPPNode<T> n : node.getChildNodes()){
				this.printGraphPreOrderRecursive(n);
			}
		}
	}
}
