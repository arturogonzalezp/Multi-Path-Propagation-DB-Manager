package mx.arturogonzalezp.mppdb.structures;

public class MPPNodeInsertionPair<T extends MPPItemInterface> {
	private Number parentID;
	private MPPNode<T> node;
	public MPPNodeInsertionPair(Number parentID, MPPNode<T> node){
		this.setParentID(parentID);
		this.setNode(node);
	}
	public MPPNodeInsertionPair(String parentID, MPPNode<T> node){
		this.setParentID(Long.parseLong(parentID));
		this.setNode(node);
	}
	public Number getParentID() {
		return parentID;
	}
	private void setParentID(Number ID) {
		this.parentID = ID;
	}
	public MPPNode<T> getNode() {
		return node;
	}
	private void setNode(MPPNode<T> node) {
		this.node = node;
	}
}
