public interface Dequeable {
    int nextSlot(int k);
    void enqueueFront(int n);
    void enqueueRear(int n);
    int dequeueFront();
    int dequeueRear();
    int size();
    boolean isEmpty();
    void showDetails();
}