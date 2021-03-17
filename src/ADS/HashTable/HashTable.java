package ADS.HashTable;

public class HashTable {
    public int size;
    public int step;
    public String[] slots;

    public HashTable(int sz, int stp) {
        size = sz;
        step = stp;
        slots = new String[size];
        for (int i = 0; i < size; i++) slots[i] = null;
    }

    public int hashFun(String value) {
        // всегда возвращает корректный индекс слота
        //остаток от деления длины строки на длину массива
        return value.length() % this.size;
    }

    public int seekSlot(String value) {
        int index = hashFun(value);
        for (int i = 0; i < this.size; i++) {
            if (slots[index] == null) {
                return index;
            } else {
                index = (index + this.step) % this.size;
            }
        }
        return -1;
    }

    public int put(String value) {
        int index = seekSlot(value);
        if (index == -1) {
            return -1;
        } else {
            slots[index] = value;
            return index;
        }
        // записываем значение по хэш-функции
        // возвращается индекс слота или -1
        // если из-за коллизий элемент не удаётся разместить
    }

    public int find(String value) {
        int index = hashFun(value);
        for (int i = 0; i < this.size; i++) {
            if (slots[index] != null) {
                if (slots[index].equals(value)) {
                    return index;
                }
                else {
                    index = (index + this.step) % this.size;
                }
            }
        }
        // находит индекс слота со значением, или -1
        return -1;

    }
}