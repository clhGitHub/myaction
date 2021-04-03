

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;


/**
 * Create by longhchen on  2021-02-25 22:43
 */
public class Speed {
    public static void main(String[] args) {
       /* List<String> list = Speed.getContent();
        for (String s : list) {
            System.out.println(s);
        }*/
        List<String> list = Speed.getContent();
        //System.out.println("----"+list.size());  有数据
        Speed.getResult(list);
    }


    public static  List<String> getContent()  {
        String fileName = "H:\\longhchen_act\\mylife\\data\\2021-02-17.txt";
        //读取文件
        List<String> lineLists = null;
        try {
            System.out.println(fileName);
            lineLists = Files
                    .lines(Paths.get(fileName), Charset.defaultCharset())
                    .flatMap(line -> Arrays.stream(line.split("\n")))
                    .collect(Collectors.toList());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return  lineLists;
    }

    public static void getResult(List<String>  data){
        //System.out.println("---"+data.size());有数据
        ArrayList<ArrayList<String>> list4 = new ArrayList<ArrayList<String>>();
        for (int i = 0; i < data.size(); i++) {
            //System.out.println("是否进入");  进入了
            ArrayList<String> list3 = new ArrayList<>();
            if(data.get(i).equals("10")){
                list3.add(data.get(i+1));
                list3.add(data.get(i+2));
                list3.add(data.get(i+3));
                list3.add(data.get(i+4));
                list3.add(data.get(i+5));
                list3.add(data.get(i+6));
                list3.add(data.get(i+7));
                //System.out.println("---------"+list3.size());
            }
            if(!list3.isEmpty()){
                list4.add(list3);
            }
        }
        //System.out.println("===="+list4.size());

        //System.out.println("list4:"+java.util.Arrays.toString(new List[]{list4}));
        ArrayList<String> tmp2 = new ArrayList<String>();
        tmp2.add("3");
        tmp2.add("4");
        tmp2.add("6");
        tmp2.add("8");
        tmp2.add("9");
        //System.out.println("小集合"+":"+java.util.Arrays.toString(new List[]{tmp2}));
            int a = 0;
            for (int j = 0; j < list4.size(); j++) {
                //System.out.println("进入for循环");
                ArrayList<String> tmp1 = list4.get(j);
                //List<string> collect = tmp1.stream().filter(item -> tmp2.contains(item)).collect(toList());
                List<String> collect = tmp1.stream().filter(item -> item.contains(item)).collect(toList());
                System.out.println("小集合"+":"+java.util.Arrays.toString(new List[]{tmp1}));
                // 使用retainAll会改变list1的值，所以写一个替代

                List origin = new ArrayList<>();

                origin.addAll(tmp1);

                origin.retainAll(tmp2);

                // 有交集

                if(origin.size()<=0){

                    a+=1;

                }
                //System.out.println("小集合"+":"+java.util.Arrays.toString(new List[]{tmp1}));
            }

            System.out.println("第"+1+"种方案"+"爆了"+a+"次");

    }
}
