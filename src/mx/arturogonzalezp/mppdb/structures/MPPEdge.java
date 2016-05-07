package mx.arturogonzalezp.mppdb.structures;

public class MPPEdge{
	private Number itemID;
	private Number parentID;
	public MPPEdge(Number itemID, Number parentID){
		this.setItemID(itemID);
		this.setParentID(parentID);
	}
	public Number getItemID() {
		return itemID;
	}
	public void setItemID(Number itemID) {
		this.itemID = itemID;
	}
	public Number getParentID() {
		return parentID;
	}
	public void setParentID(Number parentID) {
		this.parentID = parentID;
	}
}
