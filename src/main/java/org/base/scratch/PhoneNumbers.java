import java.util.ArrayList;
import java.util.List;

public class PhoneNumbers {


    public static String[][] map;

    public static void main(String[] args) {

        int[][] grid = new int[][]{{1,2},{2,4}};

        map = new String[10][];//{{"a", "b", "c"}, {"a", "b", "c"}, {"a", "b", "c"}, {"a", "b", "c"}, {"a", "b", "c"}, {"a", "b", "c"}, {"a", "b", "c"}, {"a", "b", "c"}, {"a", "b", "c"}, {"a", "b", "c"}};
        map[0] = new String[]{"a", "b", "c"};
        map[1] = new String[]{"d", "e", "f"};
        map[2] = new String[]{"g", "h", "i"};
        map[3] = new String[]{"j", "k"};
        map[4] = new String[]{"l", "m", "n"};
        map[5] = new String[]{"o", "p", "q"};
        map[6] = new String[]{"r", "s", "t"};
        map[7] = new String[]{"u", "v", "w"};
        map[8] = new String[]{"x"};
        map[9] = new String[]{"z"};


        printStr(numToString("1"));
        printStr(numToString("01"));

    }

    public static void printStr(List<String> stringList) {
        for (String s : stringList) {
            System.out.print(s + " ");
        }
        System.out.println();
    }

    public static List<String> numToString(String num) {

        List<String> ret = new ArrayList<>();

        for (int i = 0; i < num.length(); i++) {

            // get char and conver to int
            String s = num.charAt(i) + "";
            Integer index = Integer.parseInt(s);
            String[] charList = map[index];

            if (ret.size() == 0) {
                for (String str : charList) {
                    ret.add(str);
                }
            } else {

                List<String> newList = new ArrayList<>();

                for (String s1 : ret) {
                    for (String str : charList) {
                        newList.add(str+s1);
                    }
                }
                ret = newList;
            }


        }
        return ret;
    }

}
