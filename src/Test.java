import mx.arturogonzalezp.mppdb.structures.MPPNode;
import mx.arturogonzalezp.mppdb.structures.MinHeap;


public class Test {

	public static void main(String[] args) {
		MPPNode<String> nodoPadre = new MPPNode<String>("Padre", 310);
		nodoPadre.setLevel(0);
		nodoPadre.addChildNode(new MPPNode<String>("Hijo"));
		nodoPadre.getChildNodeAt(0).addChildNode(new MPPNode<String>("Nieto", 100));
		System.out.println(nodoPadre);
		System.out.println(nodoPadre.getChildNodeAt(0));
		System.out.println(nodoPadre.getChildNodeAt(0).getChildNodeAt(0));
		MinHeap<MPPNode<String>> heap = new MinHeap<MPPNode<String>>();
		heap.minHeapInsert(nodoPadre);
		heap.minHeapInsert(nodoPadre.getChildNodeAt(0));
		heap.minHeapInsert(nodoPadre.getChildNodeAt(0).getChildNodeAt(0));
		int heapSize = heap.size();
		System.out.println("Prueba heap:\n");
		for (int i = 0; i < heapSize; i++) {
			try {
				System.out.println(heap.heapExtractMin().toString());
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

}
