package ADS.PowerSet;

import java.util.Hashtable;

public class PowerSet {
    Hashtable<String, String> ht;

    public PowerSet() {
        ht = new Hashtable(20000);
        // ваша реализация хранилища
    }

    public int size() {
        // количество элементов в множестве
        return this.ht.size();
    }


    public void put(String value) {
        if (!ht.contains(value)) {
            ht.put(value, value);
        }
        // всегда срабатывает
    }

    public boolean get(String value) {
        // возвращает true если value имеется в множестве,
        // иначе false
        return ht.contains(value);
    }

    public boolean remove(String value) {
        // возвращает true если value удалено
        // иначе false
        if (ht.contains(value)) {
            ht.remove(value);
            return true;
        }
        return false;
    }

    public PowerSet intersection(PowerSet set2) {
        // пересечение текущего множества и set2
        PowerSet set3 = new PowerSet();
        if (set2.size() < size()) {
            for (String element : set2.ht.values()) {
                if (this.ht.contains(element))
                    set3.put(element);
            }
        } else {
            for (String element : this.ht.values()) {
                if (set2.ht.contains(element))
                    set3.put(element);
            }
        }
        return set3;
    }

    public PowerSet union(PowerSet set2) {
        // объединение текущего множества и set2
        PowerSet set3 = new PowerSet();
        if (size() > set2.size()) {
            set3.ht = (Hashtable<String, String>) this.ht.clone();
            for (String element : set2.ht.values()) {
                set3.put(element);
            }
        } else {
            set3.ht = (Hashtable<String, String>) set2.ht.clone();
            for (String element : this.ht.values()) {
                set3.put(element);
            }
        }
        return set3;
    }

    public PowerSet difference(PowerSet set2) {
        // разница текущего множества и set2
        PowerSet set3 = new PowerSet();
        for (String element : this.ht.values()) {
            if (!set2.ht.contains(element))
              set3.put(element);

        }

        return set3;
    }

    public boolean isSubset(PowerSet set2) {
        // возвращает true, если set2 есть
        // подмножество текущего множества,
        // иначе false
        boolean flag = true;
        for (String element : set2.ht.values()) {
            if (!this.ht.contains(element)) {
                flag = false;
                break;
            }
        }
        return flag;
    }


}