package ADS.NativeCache;

import java.lang.reflect.Array;

class NativeCache<T> {
    public int size;
    public String[] slots;
    public T[] values;
    public int[] hits;
    private int step = 1;

    // ...
    public NativeCache(int sz, Class clazz) {
        size = sz;
        slots = new String[size];
        hits = new int[size];
        values = (T[]) Array.newInstance(clazz, this.size);
        for (int el : hits
        ) {
            el = 0;
        }
    }

    public int hashFun(String key) {
        int index = key.length() % this.size;
        for (int i = 0; i < this.size; i++) {
            if (slots[index] == null || slots[index].equals(key)) {
                return index;
            } else {
                index = (index + 1) % this.size;
            }
        }
        return -1;
        // всегда возвращает корректный индекс слота
//при вбивании существующего ключа, значение перезаписывается
    }

    public boolean isKey(String key) {
        int index = key.length() % this.size;
        for (int i = 0; i < this.size; i++) {
            if (slots[index] != null) {
                if (slots[index].equals(key)) {
                    return true;
                } else {
                    index = (index + 1) % this.size;
                }
            }
        }

        // возвращает true если ключ имеется,
        // иначе false
        return false;
    }

    public void put(String key, T value) {
        int index = hashFun(key);
        if (index == -1) {
            deleteExcess();
            index=hashFun(key);
        }
        if (index > -1) {
            slots[index] = key;
            Array.set(values, index, value);
            hits[index]++;
        }

        //увеличиввем кол-во вхождений в любом случае
        // гарантированно записываем
        // значение value по ключу key
    }

    public void deleteExcess() {
        int min = hits[0];
        int minIndex = 0;
        for (int i = 0; i < this.size; i++) {
            if (hits[i] < min) {
                min = hits[i];
                minIndex = i;
            }
        }

        hits[minIndex] = 0;
        slots[minIndex] = null;
    }

    public T get(String key) {
        int index = hashFun(key);

        if (isKey(key) == true) {

            return (T) Array.get(values, hashFun(key));
        }
        // возвращает value для key,
        // или null если ключ не найден
        return null;
    }
}