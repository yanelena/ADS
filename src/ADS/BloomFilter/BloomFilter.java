package ADS.BloomFilter;

public class BloomFilter {
    public int filter_len;
    byte[] bits;

    public BloomFilter(int f_len) {
        filter_len = f_len;
        bits = new byte[filter_len];
        // создаём битовый массив длиной f_len ...
    }

    // хэш-функции
    public int hash1(String str1) {
        // 17
        int randomNum = 17;
        int result = 0;
        for (int i = 0; i < str1.length(); i++) {
            result *= randomNum;
            int code = (int) str1.charAt(i);
            result += code;
            result %= filter_len;

        }
        // реализация ...
        return result;
    }

    public int hash2(String str1) {
        // 223
        int randomNum = 223;
        int result = 0;
        for (int i = 0; i < str1.length(); i++) {
            result *= randomNum;
            int code = (int) str1.charAt(i);
            result += code;
            result %= filter_len;

        }
        // реализация ...
        return result;
    }

    public void add(String str1) {
        bits[hash1(str1)] = 1;
        bits[hash2(str1)] = 1;
        // добавляем строку str1 в фильтр
    }

    public boolean isValue(String str1) {
        if (bits[hash1(str1)] == 1 && bits[hash2(str1)] == 1) {
            return true;
        }
        // проверка, имеется ли строка str1 в фильтре
        return false;
    }
}
