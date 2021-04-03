/**
 * Create by longhchen on  2021-02-28 14:52
 */
public class Reverse {
    public static void main(String[] args) {
        String data1 = "abcde";
        System.out.println(data1);
        //System.out.println(getReverse(data1));
        System.out.println(getReverse2(data1));

    }

    public static String getReverse(String s){
        char[] data = s.toCharArray();
        String tmp = "";
        for (int i = data.length-1; i >= 0 ; i--) {
            tmp += data[i];
        }
        return tmp;

    }

    public static String getReverse2(String s){
        return new StringBuffer(s).reverse().toString();

    }
}
