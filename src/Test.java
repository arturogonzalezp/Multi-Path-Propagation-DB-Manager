import mx.arturogonzalezp.mppdb.structures.MPPNode;


public class Test {

	public static void main(String[] args) {
		MPPNode<String> nodoPadre = new MPPNode<String>("Padre", 310);
		nodoPadre.setLevel(0);
		nodoPadre.addChildNode(new MPPNode<String>("Hijo"));
		nodoPadre.getChildNodeAt(0).addChildNode(new MPPNode<String>("Nieto", 100));
		System.out.println(nodoPadre);
		System.out.println(nodoPadre.getChildNodeAt(0));
		System.out.println(nodoPadre.getChildNodeAt(0).getChildNodeAt(0));
	}

}
