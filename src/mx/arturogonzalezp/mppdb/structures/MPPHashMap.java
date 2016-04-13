package mx.arturogonzalezp.mppdb.structures;
import java.util.ArrayList;
import java.util.List;

public class MPPHashMap {
	private final static int TABLE_SIZE = 1000;
	private List<MPPNode>[] table;
	public MPPHashMap(){
		this.table = new List[this.TABLE_SIZE];
        for (int i = 0; i < this.TABLE_SIZE; i++){
        	this.table[i] = new ArrayList<MPPNode>();
        }
	}
	private int getKeyForSearch(Number ID){
    	return (int) (ID.longValue() % this.TABLE_SIZE);
    }
	public MPPNode get(Number ID) {
    	int hash = getKeyForSearch(ID);
    	if(!this.table[hash].isEmpty()){
    		for(int i = 0; i < this.table[hash].size(); i++){
    			if(this.table[hash].get(i).getItem().getID().equals(ID)){
    				return this.table[hash].get(i);
    			}
    		}
    	}
    	return null;
    }
	public MPPNode get(String ID){
		int hash = getKeyForSearch(Long.parseLong(ID));
    	if(!this.table[hash].isEmpty()){
    		for(int i = 0; i < this.table[hash].size(); i++){
    			if(this.table[hash].get(i).getItem().getID().toString().equals(ID)){
    				return this.table[hash].get(i);
    			}
    		}
    	}
    	return null;
	}
    public void put(MPPNode node){
    	this.table[node.getStorageKey()].add(node);
    }
    public boolean remove(Number ID){
    	int hash = getKeyForSearch(ID);
    	if(!this.table[hash].isEmpty()){
    		for(int i = 0; i < this.table[hash].size(); i++){
    			if(this.table[hash].get(i).getItem().getID().equals(ID)){
    				this.table[hash].remove(i);
    				return true;
    			}
    		}
    	}
    	return false;
    }
    public boolean remove(String ID){
    	int hash = getKeyForSearch(Long.parseLong(ID));
    	if(!this.table[hash].isEmpty()){
    		for(int i = 0; i < this.table[hash].size(); i++){
    			if(this.table[hash].get(i).getItem().getID().toString().equals(ID)){
    				this.table[hash].remove(i);
    				return true;
    			}
    		}
    	}
    	return false;
    }
    public void printAllHashMap(){
    	for (int i = 0; i < this.TABLE_SIZE; i++) {
    		if(!this.table[i].isEmpty()){
    			System.out.println("Hash: " + i);
				for (int j = 0; j < this.table[i].size(); j++) {
					System.out.println(this.table[i].get(j).getItem().getID());
				}
    		}
		}
    }
}
