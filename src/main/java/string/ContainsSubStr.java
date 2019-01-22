package string;

public class ContainsSubStr {

    public void main(String[] args) {

        String s = "afsdfsdfobnnnznafdsfdsf";
        char[] chars = s.toCharArray();
        String s1 = "dsdfobnalsld";
        char[] subchar = s1.toCharArray();

        int value = indexOf(chars, subchar);

    }

    private int indexOf(char[] chars, char[] subchar) {

        char[] origin = chars.length > subchar.length ? subchar : chars;
        int result = 0;
        int i = 0;
        while (i < origin.length) {
            char c = origin[i];
//            while ()

            i++;
        }

        return -1;
    }

}
