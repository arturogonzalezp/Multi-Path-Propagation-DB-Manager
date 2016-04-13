import mx.arturogonzalezp.mppdb.structures.MPPNodeItemInterface;


public class NodeItemTest implements MPPNodeItemInterface{
	private Number ID;
	private String title;
	public NodeItemTest(Number ID, String title){
		this.setID(ID);
		this.setTitle(title);
	}
	public NodeItemTest(String ID, String title){
		this.setID(Long.parseLong(ID));
		this.setTitle(title);
	}
	@Override
	public Number getID() {
		return this.ID;
	}
	@Override
	public void setID(Number ID) {
		this.ID = ID;
	}
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	@Override
	public int compareTo(MPPNodeItemInterface o) {
		NodeItemTest test = (NodeItemTest) o;
		if(this.getTitle().compareTo(test.getTitle()) > 0){
			return 1;
		}else if(this.getTitle().compareTo(test.getTitle()) < 0){
			return -1;
		}
		return 0;
	}
	public String toString(){
		return "ID: " + this.getID() + "\nTitle: " + this.getTitle();
	}
}
