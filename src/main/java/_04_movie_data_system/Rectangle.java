package _04_movie_data_system;

public class Rectangle {
    private int left;
    private int top;
    private int right;
    private int bottom;

    public Rectangle(final int left, final int top, final int right, final int bottom) {
        this.left = left;
        this.top = top;
        this.right = right;
        this.bottom = bottom;
    }

    public int getLeft() {
        return left;
    }

    public void setLeft(final int left) {
        this.left = left;
    }

    public int getTop() {
        return top;
    }

    public void setTop(final int top) {
        this.top = top;
    }

    public int getRight() {
        return right;
    }

    public void setRight(final int right) {
        this.right = right;
    }

    public int getBottom() {
        return bottom;
    }

    public void setBottom(final int bottom) {
        this.bottom = bottom;
    }

    // 변경하는 주체를 외부의 객체에서 Rectangle로 이동 (책임을 이동)
    public void enlarge(int multiple) {
        right *= multiple;
        bottom *= multiple;
    }
}

class AnyClass {
    void anyMethod(Rectangle rectangle, int multiple) {
        rectangle.setRight(rectangle.getRight() * multiple);
        rectangle.setBottom(rectangle.getBottom() * multiple);
        /**
         * 추가적인 필드에 다한 set 바인딩
         */
    }
}
