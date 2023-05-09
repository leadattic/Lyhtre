import java.util.ArrayList;
import java.util.Arrays;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    static String[] reserved = new String[]{"print", "str", "strend"};
    static String codeString = "print str Hello World strend str Hello Lythre strend ; string name str leadattic strend;";
    static String[] code;
    static int stringNum = 0;
    static ArrayList<String> strings = new ArrayList<String>();
    public static void main(String[] args) {
        code = codeString.split(" ");

        while(Arrays.stream(code).anyMatch("strend"::equals))
            code = deleteBetweenValues(code, "str", "strend", true);

        System.out.println(Arrays.toString(code));




        for (int i=0; i < code.length; i++){
            if (Arrays.stream(reserved).anyMatch(code[i]::equals)){
                use(code[i], i);
            }
        }

    }
    public static String[] deleteBetweenValues(String[] arr, String val1, String val2, boolean createString) {
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

        if (sb.length() > 0 && createString) {
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
        else if(str.equals("string"))
            print(i);
        else if(str.equals("int"))
            print(i);
        else if(str.equals("bool"))
            print(i);
        else if(str.equals("promptWithMessage"))
            print(i);
    }

    public static void string(int i) {
        //string varname content
        int semiColon = getNext(i, ";", code);
        String toPrint = "";
        for (int j = i+1; j<semiColon; j++){
            toPrint += code[j];
        }
        int count = 0;
        int index = toPrint.indexOf("str");

        while (index != -1){
            count++;
            index = toPrint.indexOf("str", index + 1);


        }
        System.out.println(count + stringNum);

        for(int j = 0; j < count; j++){
            System.out.println(j);
            toPrint = toPrint.replaceFirst("str", strings.get(stringNum));
            stringNum++;
        }
        System.out.println(toPrint);
    }
    public static void print(int i){


        int semiColon = getNext(i, ";", code);
        String toPrint = "";
        for (int j = i+1; j<semiColon; j++){
            toPrint += code[j];
        }
        int count = 0;
        int index = toPrint.indexOf("str");

        while (index != -1){
            count++;
            index = toPrint.indexOf("str", index + 1);


        }
        System.out.println(count + stringNum);

        for(int j = 0; j < count; j++){
            System.out.println(j);
            toPrint = toPrint.replaceFirst("str", strings.get(stringNum));
            stringNum++;
        }
        System.out.println(toPrint);




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
