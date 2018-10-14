package demo;

public class Rect implements Action {

    //长
    private float length;
    //宽
    private float width;

    public Rect(float length, float width) {
        this.length = length;
        this.width = width;
    }

    @Override
    public float calcPerimeter() {
        return (width + length) * 2;
    }

    @Override
    public float calcArea() {
        return length * width;
    }
}
