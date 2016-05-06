package mx.arturogonzalezp.mppdb.structures;
import java.util.ArrayList;
import java.util.List;

public class MPPMinHeap<T extends Comparable<T>> {
	private int heapSize;
    private int length;
    private List<T> array;
    public MPPMinHeap() {
        this.length = 0;
        this.heapSize = 0;
        this.array = new ArrayList<T>();
    }
    public MPPMinHeap(int length) {
        this.length = length;
        this.heapSize = 0;
        this.array = new ArrayList<T>();
    }
    public int size(){
        return this.heapSize;
    }
    private void swap(List<T> array, int a, int b){
        T temp = array.get(a);
        array.set(a, array.get(b));
        array.set(b, temp);
    }
    private int parent(int i){
        return (i+1)/2 - 1;
    }
    private int left(int i){
        return 2*(i+1) - 1;
    }
    private int right(int i){
        return 2*(i+1) + 1 - 1;
    }
    private void minHeapify(int i){
        int l = left(i);
        int r = right(i);
        int smallest = 0;
        if (l < this.heapSize && (this.array.get(l).compareTo(this.array.get(i)) == -1)){
            smallest = l;
        }else{
            smallest = i;
        }
        if (r < this.heapSize && (this.array.get(r).compareTo(this.array.get(smallest))) == -1){
            smallest = r;
        }
        if (smallest != i){
            swap(this.array, i, smallest);
            this.minHeapify(smallest);
        }
    }
    public void buildMinHeap(){
        this.heapSize = this.length;
        for (int i = (this.length)/2; i >= 0; i--){
            this.minHeapify(i);
        }
    }
    public T heapMinimum(){
        return this.array.get(0);
    }
    public T heapExtractMin() throws Exception{
        if (this.heapSize <= 0){
            throw new Exception("Index invalid.");
        }
        T min = this.array.get(0);
        this.array.set(0, this.array.get(heapSize - 1));
        this.heapSize = this.heapSize - 1;
        this.minHeapify(0);
        return min;
    }
    private void heapIncreaseKey(int i, T key){
        this.array.add(i, key);
        while (i > 0 && this.array.get(this.parent(i)).compareTo(this.array.get(i)) == 1) {
            swap(this.array, i, this.parent(i));
            i = this.parent(i);
        }
    }
    public void minHeapInsert(T key){
        this.heapSize = this.heapSize + 1;
        this.heapIncreaseKey(this.heapSize - 1, key);
    }
}