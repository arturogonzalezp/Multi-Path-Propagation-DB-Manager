package mx.arturogonzalezp.mppdb.structures;

import java.util.Comparator;

public class MPPNodeLevelComparator<T extends MPPItemInterface> implements Comparator<MPPNode<T>>{

	@Override
	public int compare(MPPNode<T> nodeOne, MPPNode<T> nodeTwo) {
		if(nodeOne.getLevel() > nodeTwo.getLevel()){
			return 1;
		}else if(nodeOne.getLevel() < nodeTwo.getLevel()){
			return -1;
		}
		return 0;
	}

}
