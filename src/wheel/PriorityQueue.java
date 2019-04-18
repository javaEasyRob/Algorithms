package wheel;

import java.util.Arrays;

/**
 * ClassName PriorityQueue<br>
 * Function <br>
 * 最小堆实现的优先队列
 *
 * @author 辛江勇
 * @version 1.0.0
 */
public class PriorityQueue {
    private int size = 0;
    Integer[] queue;
    private static final int DEFAULT_INITIAL_CAPACITY = 11;
    private static final int MAX_ARRAY_SIZE = Integer.MAX_VALUE - 8;

    /**
     * 以默认容量初始化
     */
    public PriorityQueue() {
        queue = new Integer[DEFAULT_INITIAL_CAPACITY];
    }

    /**
     * 以指定容量初始化
     *
     * @param initCapacity 容器初始化容量
     */
    public PriorityQueue(int initCapacity) {
        if (initCapacity < 1) {
            throw new IllegalArgumentException();
        }
        queue = new Integer[initCapacity];
    }


    /**
     * 增加容量
     *
     * @param minCapCity 最小的容量
     */
    public void grow(int minCapCity) {
        int oldCapacity = queue.length;
        int newCapacity = oldCapacity + ((oldCapacity < 64) ?
                (oldCapacity + 2) :
                (oldCapacity >> 1));
        if (newCapacity - MAX_ARRAY_SIZE > 0) {
            newCapacity = minCapCity > MAX_ARRAY_SIZE ? Integer.MAX_VALUE : MAX_ARRAY_SIZE;
        }
        queue = Arrays.copyOf(queue, newCapacity);
    }


    public static void main(String[] args) {
        int[] numbers = new int[]{4, 5, 1, 6, 2, 7, 3, 8, 9};
        PriorityQueue queue = new PriorityQueue();
        for (int number : numbers) {
            queue.offer(number);
        }
        System.out.println(queue.pool());
        System.out.println(Arrays.toString(queue.queue));
    }

    /**
     * 插入元素
     */
    public void offer(int insertNumber) {
        if (size >= queue.length) {
            grow(size + 1);
        }
        int parent = size;
        size++;
        while (parent > 0) {
            if (queue[(parent - 1) >>> 1] > insertNumber) {
                queue[parent] = queue[(parent - 1) >>> 1];
                parent = (parent - 1) >>> 1;
            } else {
                break;
            }
        }
        queue[parent] = insertNumber;
    }

    /**
     * 删除堆顶元素
     * @return 返回堆顶最小元素
     */
    public Integer pool() {
        if (size == 0) {
            return null;
        }
        int min = queue[0];
        --size;
        int temp = queue[size];
        queue[size] = null;
        int child, current = 0;
        int half = size >> 1;
        //保证至少有一个左儿子(current << 1) + 1
        while (current < half) {
            child = (current << 1) + 1;
            if (child < size - 1 && queue[child] > queue[child + 1]) {
                child++;
            }
            if (queue[child] < temp) {
                queue[current] = queue[child];
            } else {
                break;
            }
            current = child;
        }
        queue[current] = temp;
        return min;
    }
}
