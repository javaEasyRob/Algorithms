package datastructure.wheel;

/**
 * ClassName Queue<br>
 * Function <br>
 *
 * @author 辛江勇
 * @version 1.0.0
 */
public class SqQueue<T> {

    public T[] getDatas() {
        return datas;
    }

    private T[] datas;
    private int maxSize;
    private int rear;
    private int front;

    /*
     * getter/setter
     */
    private int getMaxSize() {
        return maxSize;
    }

    private void setMaxSize(int maxSize) {
        this.maxSize = maxSize;
    }

    private int getRear() {
        return rear;
    }

    private void setRear(int rear) {
        this.rear = rear;
    }

    private int getFront() {
        return front;
    }

    private void setFront(int front) {
        this.front = front;
    }

    /**
     * 清空队列
     */
    public void clearAll() {
        if (isEmpty()) {
            return;
        }
        rear = 0;
        front = 0;
    }

    /**
     * 构造方法，因为循环链表空间牺牲一个，所以创建数组时，长度为指定size+1。
     *
     * @param maxSize 当前队列的最大容量值+1
     */
    public SqQueue(int maxSize) {
        if (maxSize < 1) {
            this.maxSize = 1;
        } else {
            //向下转型为T[]
            this.datas = (T[]) new Object[maxSize + 1];
            this.maxSize = maxSize + 1;
        }
    }

    /**
     * 无参构造方法，默认构造为容量100
     */
    public SqQueue() {
        this.datas = (T[]) new Object[100 + 1];
        this.maxSize = 101;
    }

    /**
     * 队列是否为空
     *
     * @return 如果对列为空返回true，如果为假返回false。
     */
    public boolean isEmpty() {
        return rear == front;
    }

    /**
     * 解决假满，使用循环队列，但是需要牺牲一个单位存储空间用作队满记录。
     *
     * @return 如果队满返回true，否则返回false。
     */
    public boolean isFull() {
        return (rear + 1) % maxSize == front;
    }

    /**
     * 指定元素入队
     *
     * @param data 要入队的元素
     * @return 返回是否添加成功，成功返回true。
     */
    public boolean enqueue(T data) {
        // 如果队满
        if (isFull()) {
            return false;
        } else {
            datas[rear] = data;
            rear = (rear + 1) % maxSize;
            return true;
        }
    }

    /**
     * 先入先出
     *
     * @return 如果队列为空返回null，否则返回队头元素
     */
    public T dequeue() {
        // 如果队列为空
        if (isEmpty()) {
            return null;
        } else {
            T data = datas[front];
            datas[front] = null;
            front = (front + 1) % maxSize;
            return data;
        }
    }
}
