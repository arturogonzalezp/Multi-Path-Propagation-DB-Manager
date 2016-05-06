import mx.arturogonzalezp.mppdb.structures.EmptyDBException;
import mx.arturogonzalezp.mppdb.structures.MPPGraph;
import mx.arturogonzalezp.mppdb.structures.MPPManager;
import mx.arturogonzalezp.mppdb.structures.MPPNode;

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
		
		/*System.out.println("Test MinHeap:\n");
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
		/*MPPGraph<NodeItemTest> graph = new MPPGraph<NodeItemTest>(new MPPNode<NodeItemTest>(new NodeItemTest(12342,"Padre")));
		graph.addChildInActualNode(new MPPNode<NodeItemTest>(new NodeItemTest("5305943", "Hijo 1")));
		graph.moveToChildAtPos(0);
		graph.addChildInActualNode(new NodeItemTest(34543,"Nieto 1"));
		graph.addChildToParent(new MPPNode<NodeItemTest>(new NodeItemTest(99848,"Hijo 2")), graph.getRootNode());
		graph.addChildToParentID(new NodeItemTest(12345,"Nieto 2"),5305943);
		//graph.addChildToParentID(new NodeItemTest(3232,"BisNieto 1"), 12345);
		graph.printGraphPreOrder();
		System.out.println(graph.getNodesCount() + " nodes");
		graph.getAdjacencyMatrix().printMatrix();*/
		
		//MPPManager test
		MPPManager<NodeItemTest> db = new MPPManager<NodeItemTest>();
		System.out.println(db + "\n");
		try {
			db.saveDB();
		} catch (EmptyDBException e) {
			System.err.println(e.getMessage() + "\n");
		}
		db.newDB(new NodeItemTest(12342,"Padre"), "MyDb");
		System.out.println(db + "\n");
		NodeItemTest item = db.searchByID(12342);
		System.out.println(item + "\n");
		NodeItemTest item2 = db.searchByID(1242);
		System.out.println(item2 + "\n");
		System.out.println("All items: ");
		for (NodeItemTest tempItem : db.getAllItems()) {
			System.out.println(tempItem);
		}
		System.out.println();
		db.addItem(new NodeItemTest(123,"Hijo 1"), 12342);
		db.addItem(new NodeItemTest("1236", "Nieto 1"),"123");
		db.addItem(new NodeItemTest(321, "Nieto 2"), 123);
		db.addItem(new NodeItemTest(321, "Hijo 2"), 12342);
		for (NodeItemTest itemOfGraph : db.getAllItems()) {
			System.out.println(itemOfGraph);
		}
		System.out.println();
		System.out.println("\nChildren of Hijo 1:\n");
		for (NodeItemTest nodeItem : db.getChildrenOf(123)) {
			System.out.println(nodeItem);
		}
		System.out.println();
		try {
			db.saveDB();
		} catch (EmptyDBException e) {
			e.printStackTrace();
		}
		db.closeDB();
		System.out.println(db + "\n");
		
		
		// Load file
		/*Path filePath = Paths.get("the-file-name.json");
		
		// Test open file
		String jsonText = "";
		try {
			if(Files.notExists(filePath)){
				Files.createFile(filePath);
			}
			jsonText = new String(Files.readAllBytes(filePath));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		// Test save file
		/*List<String> lines = Arrays.asList("The first line", "The second line");	
		try {
			//Files.write(file, lines, Charset.forName("UTF-8"));
			lines = Files.readAllLines(file, Charset.forName("UTF-8"));
		} catch (IOException e) {
			e.printStackTrace();
		}*/
		
		//System.out.println(jsonText);
		
		
		//Memory test
		/*NodeItemTest nodeItem1 = new NodeItemTest(1,"Node 1");
		NodeItemTest nodeItem2 = new NodeItemTest(2,"Node 2");
		NodeItemTest nodeItem3 = new NodeItemTest(3,"Node 3");
		NodeItemTest nodeItem4 = new NodeItemTest(4,"Node 4");
		MPPNode<NodeItemTest> nodo1 = new MPPNode<NodeItemTest>(nodeItem1);
		MPPNode<NodeItemTest> nodo2 = new MPPNode<NodeItemTest>(nodeItem2);
		MPPNode<NodeItemTest> nodo3 = new MPPNode<NodeItemTest>(nodeItem3);
		MPPNode<NodeItemTest> nodo4 = new MPPNode<NodeItemTest>(nodeItem4);
		List<MPPNode> lista1 = new ArrayList<MPPNode>();
		lista1.add(nodo1);
		lista1.add(nodo2);
		lista1.add(nodo3);
		lista1.add(nodo4);
		List<MPPNode> lista2 = new ArrayList<MPPNode>();
		lista2.add(nodo1);
		lista2.add(nodo2);*/
		
		// Memory Analisis 
		System.gc();
	    Runtime rt = Runtime.getRuntime();
	    long usedBytes = (long) Math.ceil((rt.totalMemory() - rt.freeMemory()));
	    long usedKB = (long) Math.ceil((rt.totalMemory() - rt.freeMemory()) / 1024);
	    long usedMB = (long) Math.ceil((rt.totalMemory() - rt.freeMemory()) / 1024 / 1024);
	    System.out.println("Memory usage: " + usedBytes + " bytes");
	    System.out.println("Memory usage: " + usedKB + " KB");
	    System.out.println("Memory usage: " + usedMB + " MB");
		
	}

}
