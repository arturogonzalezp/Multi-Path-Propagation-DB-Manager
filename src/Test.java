import mx.arturogonzalezp.mppdb.structures.MPPNode;
import mx.arturogonzalezp.mppdb.structures.MinHeap;


public class Test {

	public static void main(String[] args) {
		MPPNode<NodeItemTest> nodoPadre = new MPPNode<NodeItemTest>(new NodeItemTest(1,"Padre"));
		nodoPadre.setLevel(0);
		nodoPadre.addChildNode(new MPPNode<NodeItemTest>(new NodeItemTest(Long.parseLong("3432542654325435"),"Hijo")));
		nodoPadre.getChildNodeAt(0).addChildNode(new MPPNode<NodeItemTest>(new NodeItemTest(324366456,"Nieto")));
		System.out.println(nodoPadre);
		System.out.println(nodoPadre.getChildNodeAt(0));
		System.out.println(nodoPadre.getChildNodeAt(0).getChildNodeAt(0));
		MinHeap<MPPNode<NodeItemTest>> heap = new MinHeap<MPPNode<NodeItemTest>>();
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
