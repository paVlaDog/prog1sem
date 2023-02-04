import java.util.Arrays;
import java.lang.Integer;

public class IntList {
    int[] list;
    int realLenght = 0;

    public IntList (int[] mas) {
        list = Arrays.copyOf(mas, mas.length);
        realLenght = mas.length;
    }

    public IntList () {
        list = new int[0];
    }

    public void append (int num) {
        if (list.length > realLenght) {
            list[realLenght] = num;
            realLenght++;
        }
        else {
            list = Arrays.copyOf(list, list.length*2 + 1);
            list[realLenght] = num;
            realLenght++;
        }
    }

    public void set (int index, int num) throws IndexOutOfBoundsException {
        list[index] = num;
    }

    public int size () {
        return realLenght;
    }

    public int get (int index) throws IndexOutOfBoundsException {
        return list[index];
    }

    public String to_string () {
        StringBuilder builder = new StringBuilder("");
        builder.append(Integer.toString(realLenght));
        for (int i = 0; i < realLenght; i++) {
            builder.append(" " + Integer.toString(list[i]));
        }
        return builder.toString();
    }

}