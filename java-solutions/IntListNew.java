import java.util.Arrays;
import java.lang.Integer;

public class IntListNew {
    private int[] list;
    private int realLenght = 0;

    public IntListNew (int[] mas) {
        this.list = Arrays.copyOf(mas, mas.length);
        this.realLenght = mas.length;
    }

    public IntListNew () {
        this.list = new int[0];
    }

    public void append (int num) {
        if (this.list.length > this.realLenght) {
            this.list[this.realLenght] = num;
            this.realLenght++;
        }
        else {
            this.list = Arrays.copyOf(this.list, this.list.length*2 + 1);
            this.list[this.realLenght] = num;
            this.realLenght++;
        }
    }

    public void set (int index, int num) throws IndexOutOfBoundsException {
        this.list[index] = num;
    }

    public int size () {
        return this.realLenght;
    }

    public int get (int index) throws IndexOutOfBoundsException {
        return this.list[index];
    }

    public String to_string_mod () {
        StringBuilder builder = new StringBuilder("");
        builder.append(Integer.toString(this.realLenght / 2));
        int nowLine = this.list[0];
        int numInNowLine = 1;
        for (int i = 3; i < this.realLenght; i+=2) {
            if (this.list[i - 1] == nowLine) {
                numInNowLine++;
            } else {
                nowLine = this.list[i - 1];
                numInNowLine = 1;
            }
            if (numInNowLine % 2 == 0) {
                builder.append(" " + Integer.toString(this.list[i]));
            }
        }
        return builder.toString();
    }


    public String to_string () {
        StringBuilder builder = new StringBuilder("");
        builder.append(Integer.toString(this.realLenght / 2));
        for (int i = 0; i < this.realLenght; i++) {
            builder.append(" " + Integer.toString(this.list[i]));
        }
        return builder.toString();
    }
}