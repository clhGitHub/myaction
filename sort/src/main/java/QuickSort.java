import java.lang.reflect.Array;
import java.util.ArrayList;

/**
 * Create by longhchen on  2021-02-12 10:09
 */
public class QuickSort {
    public static void main(String[] args) {

        int[] data = {9, -16, -15,21, 30};

        System.out.println ("排序之前：\n" + java.util.Arrays.toString (data));

        quickSort (data);

        System.out.println ("排序之后：\n" + java.util.Arrays.toString (data));
    }

    private static void quickSort(int[] data) {

        if (data.length != 0) {
            subSort (data, 0, data.length - 1);
        }
    }

    private static void subSort(int[] data, int start/*0*/, int end/*最后一个索引*/) {

        if (start < end) {

            int base = data[start];
            int i = start;
            int j = end + 1;

            while (true) {

                while (i < end && data[++i] <= base)//找到一个大于base的数
                    ;
                while (j > start && data[--j] >= base)//找到一个小于base的数
                    ;
                if (i < j){
                    swap (data, i, j);
                }
                else
                    break;
            }
            swap (data, start, j);
            System.out.println("内部排序后的数："+java.util.Arrays.toString (data));
            subSort (data, start, j - 1);
            subSort (data, j + 1, end);
        }
    }

    private static void swap(int[] data, int i, int j) {
        int temp = data[i];
        data[i] = data[j];
        data[j] = temp;

    }
}
