package sort;

import java.util.Arrays;

/**
 * ClassName HeapSort<br>
 * Function 对简单选择排序的优化。<br>
 * 1.将序列构建成大顶堆。<br>
 * 2.将根节点与最后一个节点交换，然后断开最后一个节点。<br>
 * 3.重复第一、二步，直到所有节点断开。<br>
 *
 * @author 辛江勇<br>
 * @version 1.0.0
 * @date 2019/3/4 21:14<br>
 */
public class HeapSort {

    private static void heapSort(int[] numbers) {
        int len = numbers.length;
        //从倒数第二层开始，每层将所有结点调整为最大堆，然后再调整上一层，因为最后一层只有一个元素因此无需调整
        for (int i = (len - 1) / 2; i >= 0; i--) {
            adjustHeap(numbers, i, numbers.length - 1);
        }
        //每次将最大元素，根元素和最后一个元素更换位置，然后在[0-根元素所在位置)调整堆结构
        while (len-- > 0) {
            swap(numbers, 0, len);
            adjustHeap(numbers, 0, len - 1);
        }
    }

    private static void adjustHeap(int[] numbers, int index, int range) {
        //当index位置还存在子节点时
        while ((index * 2 + 1) <= range) {
            //左孩子索引位置
            int child = index * 2 + 1;
            if (child < range && numbers[child] < numbers[child + 1]) child++;
            if (numbers[child] < numbers[index]) break;
            swap(numbers, index, child);
            index = child;
        }
    }

    private static void swap(int[] numbers, int i, int j) {
        int temp = numbers[i];
        numbers[i] = numbers[j];
        numbers[j] = temp;
    }

    public static void main(String[] args) {
        int[] numbers = new int[]{12, 34, 77, 34, 35, 88, 123, 43, 65, 43, 123, 12, 43, 65};
        int length = numbers.length;
        //每次找到最大的數放到最后，然后将剩余的数继续建立堆
        heapSort(numbers);
        System.out.println(Arrays.toString(numbers));
    }

}
