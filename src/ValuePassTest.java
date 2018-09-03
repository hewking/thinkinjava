public class ValuePassTest {

    static class A {
        int a = 0;
    }

    public static void main(String[] args) {
        A a = new A();
        System.out.println(a.a);
        test(a);
        System.out.println(a.a);
        test2(a);
        System.out.println(a.a);


    }

    public static void test(A a) {
        a.a = 1;
    }

    public static void test2(A a){
        A b = new A();
        b.a = 2;
        a = b;
    }
}
