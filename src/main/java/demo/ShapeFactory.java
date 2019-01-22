package demo;

public class ShapeFactory {

    public static Rect buildRect(float length, float width) {
        return new Rect(length, width);
    }

    public static Circle buildCircle(float radius) {
        return new Circle(radius);
    }

}
