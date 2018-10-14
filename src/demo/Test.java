package demo;

public class Test {

    public static void main(String[] args) {

        System.out.println("长方形的周长 ：" + calcRectPerimeter(10,20));
        System.out.println("长方形的面积 ： " + calcRectArea(10 ,20));

        System.out.println("圆的周长 ： " + calcCirclePerimeter(10));
        System.out.println("圆的面积 ： " + calcCircleArea(10));
    }

    public static double calcRectPerimeter(double length ,double width){
        return 2 * length * width;
    }

    public static double calcRectArea(double length,double width) {
        return length * width;
    }

    public static double calcCircleArea(double radius){
        return Math.PI * radius * radius;
    }

    public static double calcCirclePerimeter(double radius) {
        return Math.PI * 2 * radius;
    }

}
