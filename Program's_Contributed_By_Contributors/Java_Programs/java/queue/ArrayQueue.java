 // This is an improved version of a generic queue implemented using an array.
 // We've made several enhancements for better performance and usability.
 // 
 //  Changes Made:
 //  1. Generics are now used to ensure type safety when storing elements in the queue.
 //  2. We've implemented a circular buffer to enhance the efficiency of element removal.
 //  3. The array is dynamically resized when it reaches capacity to accommodate more elements.
 // 
 //  @param <T> The type of elements to be stored in the queue.




package queue;

public class ArrayQueue<T> implements Queue<T> {

    private int size;
    private int capacity;
    private T[] array;
    private int front;
    private int rear;

    public ArrayQueue(int capacity) {
        this.capacity = capacity;
        array = (T[]) new Object[capacity];
        front = rear = -1;
    }

    @Override
    public T first() {
        if (isEmpty()) {
            throw new IllegalStateException("Queue is empty!");
        }
        return array[front];
    }

    @Override
    public T remove() {
        if (isEmpty()) {
            throw new IllegalStateException("Queue is empty!");
        }
        T obj = array[front];
        front = (front + 1) % capacity;
        size--;
        return obj;
    }

    @Override
    public void add(T obj) {
        if (size == capacity) {
            resizeArray();
        }
        rear = (rear + 1) % capacity;
        array[rear] = obj;
        size++;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    public void resizeArray() {
        T[] newArray = (T[]) new Object[2 * capacity];
        for (int i = 0; i < size; i++) {
            newArray[i] = array[(front + i) % capacity];
        }
        array = newArray;
        front = 0;
        rear = size - 1;
        capacity *= 2;
    }

    public static void main(String[] args) {
        ArrayQueue<Integer> queue = new ArrayQueue<>(2);
        queue.add(5);
        queue.add(50);
        queue.add(10); // Adding one more element to trigger a resize
        System.out.println(queue.remove()); // Should print 5
        System.out.println(queue.remove()); // Should print 50
        System.out.println(queue.first()); // Should print 10
    }
}
