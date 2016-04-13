package mx.arturogonzalezp.mppdb.structures;
import java.util.ArrayList;
import java.util.List;

public class MPPHashMap<T extends MPPNodeItemInterface> {
	private final static int TABLE_SIZE = 1000;
	private List<MPPNode<T>>[] table;
	public MPPHashMap(){
		this.setTable(new List[MPPHashMap.TABLE_SIZE]);
        for (int i = 0; i < MPPHashMap.TABLE_SIZE; i++){
        	this.getTable()[i] = new ArrayList<MPPNode<T>>();
        }
	}
	public List<MPPNode<T>>[] getTable() {
		return table;
	}
	public void setTable(List<MPPNode<T>>[] table) {
		this.table = table;
	}
	private int getKeyForSearch(Number ID){
    	return (int) (ID.longValue() % MPPHashMap.TABLE_SIZE);
    }
	public MPPNode<T> get(Number ID) {
    	int hash = getKeyForSearch(ID);
    	if(!this.getTable()[hash].isEmpty()){
    		for(int i = 0; i < this.getTable()[hash].size(); i++){
    			if(this.getTable()[hash].get(i).getItem().getID().longValue() == ID.longValue()){
    				return this.getTable()[hash].get(i);
    			}
    		}
    	}
    	return null;
    }
	public MPPNode<T> get(String ID){
		int hash = getKeyForSearch(Long.parseLong(ID));
    	if(!this.getTable()[hash].isEmpty()){
    		for(int i = 0; i < this.getTable()[hash].size(); i++){
    			if(this.getTable()[hash].get(i).getItem().getID().toString().equals(ID)){
    				return this.getTable()[hash].get(i);
    			}
    		}
    	}
    	return null;
	}
    public void put(MPPNode<T> node){
    	int hash = node.getStorageKey();
    	if(!this.getTable()[hash].isEmpty()){
    		for(int i = 0; i < this.getTable()[hash].size(); i++){
    			if(this.getTable()[hash].get(i).getItem().getID().equals(node.getItem().getID())){
    				return;
    			}
    		}
    	}
    	this.getTable()[node.getStorageKey()].add(node);
    }
    public boolean remove(Number ID){
    	int hash = getKeyForSearch(ID);
    	if(!this.getTable()[hash].isEmpty()){
    		for(int i = 0; i < this.getTable()[hash].size(); i++){
    			if(this.getTable()[hash].get(i).getItem().getID().equals(ID)){
    				this.getTable()[hash].remove(i);
    				return true;
    			}
    		}
    	}
    	return false;
    }
    public boolean remove(String ID){
    	int hash = getKeyForSearch(Long.parseLong(ID));
    	if(!this.getTable()[hash].isEmpty()){
    		for(int i = 0; i < this.getTable()[hash].size(); i++){
    			if(this.getTable()[hash].get(i).getItem().getID().toString().equals(ID)){
    				this.getTable()[hash].remove(i);
    				return true;
    			}
    		}
    	}
    	return false;
    }
    public void printAllHashMap(){
    	for (int i = 0; i < MPPHashMap.TABLE_SIZE; i++) {
    		if(!this.getTable()[i].isEmpty()){
    			System.out.println("Hash: " + i);
				for (int j = 0; j < this.getTable()[i].size(); j++) {
					System.out.println(this.getTable()[i].get(j).getItem().getID());
				}
    		}
		}
    }
}
