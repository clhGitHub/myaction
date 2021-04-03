

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static java.util.stream.Collectors.toList;


/**
 * Create by longhchen on  2021-02-25 22:43
 */
public class Speed2 {
    public static void main(String[] args) {
        int[] data = {1,2,3,5,7,1,9,10,4,1,7,9,4,1,6,7,8,2};

        ArrayList<List<Integer>> list2 = new ArrayList<List<Integer>>();
        list2.add(Arrays.asList(1,2,3,5,7,1));
        list2.add(Arrays.asList(3,6));

        List<Integer> integers = Arrays.asList(1, 2, 3, 5, 7, 1);


        ArrayList<ArrayList<Integer>> list4 = new ArrayList<ArrayList<Integer>>();

        //System.out.println("list2:"+java.util.Arrays.toString(new List[]{list2}));

        for (int i = 0; i < data.length; i++) {
            ArrayList<Integer> list3 = new ArrayList<Integer>();
            if(data[i] == 1){
                list3.add(data[i+1]);
                list3.add(data[i+2]);
                list3.add(data[i+3]);
                list3.add(data[i+4]);
            }
            if(!list3.isEmpty()){
                list4.add(list3);
            }
        }

        //System.out.println("list4:"+java.util.Arrays.toString(new List[]{list4}));
        for (int i = 0; i < list2.size(); i++) {
            int a = 0;
            for (int j = 0; j < list4.size(); j++) {
                List<Integer> tmp1 = list2.get(i);
                List<Integer> tmp2 = list4.get(j);

                List<Integer> collect = tmp1.stream().filter(item -> tmp2.contains(item)).collect(toList());

                if(collect.isEmpty()){
                    a+=1;
                }
                System.out.println("小集合"+i+":"+java.util.Arrays.toString(new List[]{tmp1}));
                System.out.println("大集合"+i+":"+java.util.Arrays.toString(new List[]{tmp2}));
            }

            System.out.println("第"+i+"种方案"+"爆了"+a+"次");
        }

    }
}
