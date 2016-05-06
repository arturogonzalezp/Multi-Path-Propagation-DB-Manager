package mx.arturogonzalezp.mppdb.structures;

import java.util.List;

public class MPPAdjacencyMatrix<T extends MPPItemInterface>{
	private int[][] matrix;
	private int size;
	public MPPAdjacencyMatrix(int size){
		this.setSize(size);
		this.setMatrix(new int[this.getSize()][this.getSize()]);
		for (int i = 0; i < this.getSize(); i++) {
			for (int j = 0; j < this.getSize(); j++) {
				this.setValue(i, j, 0);
			}
		}
	}
	public MPPAdjacencyMatrix(List<MPPNode<T>> list){
		this(list.size());
		for (int i = 0; i < this.getSize(); i++) {
			if(list.get(i).getParentNode() != null){
				this.setValue(i, list.lastIndexOf(list.get(i).getParentNode()), 1);
			}
			for (MPPNode<T> node : list.get(i).getChildNodes()) {
				this.setValue(i, list.lastIndexOf(node), 1);
			}
		}
	}
	public int[][] getMatrix() {
		return matrix;
	}
	public void setMatrix(int[][] matrix) {
		this.matrix = matrix;
	}
	public int getValue(int row, int column){
		return this.getMatrix()[row][column];
	}
	public void setValue(int row, int column, int value){
		this.getMatrix()[row][column] = value;
	}
	public int getSize() {
		return size;
	}
	public void setSize(int size) {
		this.size = size;
	}
	public void printMatrix(){
		System.out.print("\n    ");
		for (int i = 0; i < this.getSize(); i++) {
			System.out.print(i + " ");
		}
		System.out.print("\n   ");
		for (int i = 0; i < this.getSize(); i++) {
			System.out.print("__");
		}
		System.out.println();
		for (int i = 0; i < this.getSize(); i++) {
			System.out.print(i + " | ");
			for (int j = 0; j < this.getSize(); j++) {
				System.out.print(this.getValue(i, j) + " ");
			}
			System.out.println();
		}
	}
}
