package generics;

import java.lang.reflect.Array;

public class GenericArray<T> {

    private T[] array;

    public GenericArray(Class<T> tClass, int length) {
        array = (T[]) Array.newInstance(tClass, length);
    }

    public void put(int i, T t) {
        array[i] = t;
    }

    public T get(int i) {
        return array[i];
    }

    public T[] create() {
        return array;
    }
}
