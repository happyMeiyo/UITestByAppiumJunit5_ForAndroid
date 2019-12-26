package util;

public class KeyBoardLayout {
    private int left;
    private int top;
    private int length;
    private int width;

    public KeyBoardLayout setLeft(int left) {
        this.left = left;
        return this;
    }

    public void setTop(int top) {
        this.top = top;
    }

    int getLeft() {
        return left;
    }

    int getTop() {
        return top;
    }


    public int getX_Coordinate(char keyValue) {
        int x_crd = 0;
        switch (keyValue) {
            case '0':
                x_crd = getLeft() + 102;
                break;
            case '1':
            case '4':
            case '7':
                x_crd = getLeft() + 53;
                break;
            case '2':
            case '5':
            case '8':
                x_crd = getLeft() + 151;
                break;
            case '3':
            case '6':
            case '9':
            case '.':
                x_crd = getLeft() + 250;
                break;
            case 'X':
            case 'C':
            case 'Y':
                x_crd = getLeft() + 356;
                break;

            default:
                System.out.println("Input Invalid: " + keyValue);
        }
        return x_crd;
    }


    public int getY_Coordinate(char keyValue) {
        int y_crd = 0;
        switch (keyValue) {
            case '0':
            case '.':
                y_crd = getTop() + 207;
                break;
            case '1':
            case '2':
            case '3':
                y_crd = getTop() + 147;
                break;
            case '4':
            case '5':
            case '6':
            case 'C':
                y_crd = getTop() + 95;
                break;
            case '7':
            case '8':
            case '9':
            case 'X':
                y_crd = getTop() + 43;
                break;
            case 'Y':
                y_crd = getTop() + 185;
                break;
            default:
                System.out.println("Input Invalid: " + keyValue);
        }

        return y_crd;
    }
}
