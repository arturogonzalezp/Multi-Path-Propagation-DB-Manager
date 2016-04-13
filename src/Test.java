import mx.arturogonzalezp.mppdb.structures.MPPGraph;
import mx.arturogonzalezp.mppdb.structures.MPPHashMap;
import mx.arturogonzalezp.mppdb.structures.MPPNode;
import mx.arturogonzalezp.mppdb.structures.MinHeap;


public class Test {

	public static void main(String[] args) {
		// TEST WITHOUT GRAPH
		/*System.out.println("Test MPPNode:\n");
		MPPNode<NodeItemTest> nodoPadre = new MPPNode<NodeItemTest>(new NodeItemTest(12342,"Padre"));
		nodoPadre.setLevel(0);
		nodoPadre.addChildNode(new MPPNode<NodeItemTest>(new NodeItemTest(Long.parseLong("3432542654325435"),"Hijo")));
		nodoPadre.getChildNodeAt(0).addChildNode(new MPPNode<NodeItemTest>(new NodeItemTest(324366456,"Nieto")));
		System.out.println(nodoPadre);
		System.out.println(nodoPadre.getChildNodeAt(0));
		System.out.println(nodoPadre.getChildNodeAt(0).getChildNodeAt(0));
		
		System.out.println("Test MinHeap:\n");
		MinHeap<MPPNode<NodeItemTest>> heap = new MinHeap<MPPNode<NodeItemTest>>();
		heap.minHeapInsert(nodoPadre);
		heap.minHeapInsert(nodoPadre.getChildNodeAt(0));
		heap.minHeapInsert(nodoPadre.getChildNodeAt(0).getChildNodeAt(0));
		int heapSize = heap.size();
		for (int i = 0; i < heapSize; i++) {
			try {
				System.out.println(heap.heapExtractMin().toString());
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		System.out.println("Test HashMap:\n");
		MPPHashMap<NodeItemTest> hashMap = new MPPHashMap<NodeItemTest>();
		hashMap.put(nodoPadre);
		hashMap.put(nodoPadre.getChildNodeAt(0));
		hashMap.put(nodoPadre.getChildNodeAt(0).getChildNodeAt(0));
		System.out.println(hashMap.get(32));
		System.out.println(hashMap.get(12342));
		//System.out.println(hashMap.remove("12342"));
		System.out.println(hashMap.get("12342"));
		hashMap.printAllHashMap();
		
		System.out.println("\nFinal Print:\n");
		System.out.println(nodoPadre);
		System.out.println(nodoPadre.getChildNodeAt(0));
		System.out.println(nodoPadre.getChildNodeAt(0).getChildNodeAt(0));*/
		
		// TEST WITH GRAPH 
		MPPGraph<NodeItemTest> graph = new MPPGraph<NodeItemTest>(new MPPNode<NodeItemTest>(new NodeItemTest(12342,"Padre")));
		graph.addChildInActualNode(new MPPNode<NodeItemTest>(new NodeItemTest("5305943", "Hijo 1")));
		graph.moveToChildAtPos(0);
		graph.addChildInActualNode(new NodeItemTest(34543,"Nieto 1"));
		graph.addChildToParent(new MPPNode<NodeItemTest>(new NodeItemTest(99848,"Hijo 2")), graph.getRootNode());
		graph.addChildToParentID(new NodeItemTest(12345,"Nieto 2"),5305943);
		graph.printGraphPreOrder();
	}

}
