package demo;

public class Circle implements Action {

    // 圆的半径

    public Circle(float radius) {
        this.radius = radius;
    }

    private float radius;

    @Override
    public float calcPerimeter() {
        return (float) Math.PI * 2 * radius;
    }

    @Override
    public float calcArea() {
        return (float) Math.PI * radius * radius;
    }
}
