package ADS.NativeDictionary;

import java.lang.reflect.Array;

class NativeDictionary<T> {
    public int size;
    public String[] slots;
    public T[] values;

    public NativeDictionary(int sz, Class clazz) {
        size = sz;
        slots = new String[size];
        values = (T[]) Array.newInstance(clazz, this.size);
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
        if (index > -1) {
            slots[index] = key;
            Array.set(values, index, value);
        }
        // гарантированно записываем
        // значение value по ключу key
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