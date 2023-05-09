import java.util.ArrayList;
import java.util.Arrays;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    static String[] reserved = new String[]{"print", "str", "strend"};
    static String codeString = "print str Hello World strend ; ";
    static String[] code;
    static int stringNum = 0;
    static ArrayList<String> strings = new ArrayList<String>();
    public static void main(String[] args) {
        code = codeString.split(" ");
        code = deleteBetweenValues(code, "str", "strend");

        System.out.println(Arrays.toString(code));
        System.out.println(strings.get(0));




        for (int i=0; i < code.length; i++){
            if (Arrays.stream(reserved).anyMatch(code[i]::equals)){
                use(code[i], i);
            }
        }

    }
    public static String[] deleteBetweenValues(String[] arr, String val1, String val2) {
        int start = -1, end = -1;
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < arr.length; i++) {
            if (arr[i].equals(val1)) {
                start = i+1;
            } else if (arr[i].equals(val2)) {
                end = i;
                break;
            } else if (start != -1 && end == -1) {
                sb.append(arr[i]);
                if (i != arr.length - 1) {
                    sb.append(" ");
                }
            }
        }
        if (sb.length() > 0) {
            strings.add(sb.toString());
        }
        if (start != -1 && end != -1) {
            String[] result = new String[arr.length - (end - start) - 1];
            int index = 0;
            for (int i = 0; i < arr.length; i++) {
                if (i < start || i > end) {
                    result[index++] = arr[i];
                }
            }
            return result;
        }else{
            System.out.println("ERROR in deleteBetweenValues");
            return new String[]{"ERROR in deleteBetweenValues"};
        }


    }


    public static void use(String str, int i){
        if(str.equals("str") || str.equals("strend") || str.equals(";"))
            return;
        else if(str.equals("print"))
            print(i);
    }

    public static void print(int i){

        String[] outer = deleteBetweenValues(code, "print", ";");
        String toPrint = "";

        //System.out.println(Arrays.toString(inner));

    }


    public static int getNext(int start, String end, String[] array){
        for (int i = 0; i<array.length;i++){
            if(array[i].equals(";") && i >= start){
                return i;
            }
        }
        System.out.println("ERROR: missing semicolon from operator "+start);
        return -1;
    }
}
