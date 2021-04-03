


/**
 * Create by longhchen on  2021-02-10 20:39
 */
public class MergeSort {
    public static void main(String[] args) {

        int[] data = {-9, 78, 0,2,1, 23, -567, 70};


        int[] tmpArr = new int[data.length];

        System.out.println("排序之前：\n" + java.util.Arrays.toString(data));

        mergeSort(data, 0, data.length - 1, tmpArr); //调用归并排序法

        System.out.println("排序之后：\n" + java.util.Arrays.toString(data));



    }

    //是后一个归并排序
  /*
  1. data :待排序的数组
  2. left: 最初的左边的下标 0
  3. right: 最初的右边下标 length - 1
  4. tempArr：就是临时数组 , 事先就开辟好的，大小和 arr 一样
   */
    public static void mergeSort(int[] data, int left, int right, int[] tmpArr) {
        if (left < right) { //如果 left < right 就可以继续分
            int mid = (left + right) / 2;

            mergeSort(data, left, mid, tmpArr); // 递归将左边的数据做成有序列表
            mergeSort(data, mid + 1, right, tmpArr); //递归将右边的数据做成有序列表

            // merge 是合并的操作
            merge(data, left, mid, right, tmpArr);
        }
    }

    public static void merge(int[] data, int left, int mid, int right, int[] tempArr) {
        int i = left; // i 就是左边指针[索引]
        int j = mid + 1; //j 就是右边的指针
        int t = 0;// t 是temp 数组的索引
        while (i <= mid && j <= right) {
            // 如果是当前的左边的有序列表的值小于当前的右边有序列表的值
            // 就把这个值拷贝到 temp数组
            if (data[i] <= data[j]) {
                tempArr[t] = data[i];
                t += 1;
                i += 1;
            } else { // 如果是当前的右边的有序列表的值小于当前的左边有序列表的值，就把这个值拷贝到 temp数组
                tempArr[t] = data[j];
                t += 1;
                j += 1;
            }
        }
        while (i <= mid) { //如果左边有序列表还有剩余数据，就依次拷贝到temp数组
            tempArr[t] = data[i];
            t += 1;
            i += 1;
        }
        while (j <= right) { //如果右边有序列表还有剩余数据，就依次拷贝到temp数组
            tempArr[t] = data[j];
            t += 1;
            j += 1;
        }

        // 下面代码是完成将本次的temp 的数据拷贝到arr中
        t = 0;
        int tempLeft = left;
        while (tempLeft <= right) {
            data[tempLeft] = tempArr[t];
            t += 1;
            tempLeft += 1;
        }

    }
}