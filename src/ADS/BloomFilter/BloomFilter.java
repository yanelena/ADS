package ADS.BloomFilter;

public class BloomFilter {
    public int filter_len;


    public BloomFilter(int f_len) {
        filter_len = (int) Math.pow(2, (f_len - 2));

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
            result %= 30;

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
            result %= 30;

        }
        // реализация ...
        return result;
    }

    public void add(String str1) {
        String s = Integer.toBinaryString(filter_len);
        int frAdd = (int) Math.pow(2, 30 - hash1(str1));
        int scAdd = (int) Math.pow(2, 30 - hash2(str1));
        if (s.charAt(hash1(str1)) == '0' && s.charAt(hash2(str1)) == '0') {
            if (frAdd == scAdd) {
                filter_len += frAdd;
            } else {
                filter_len += frAdd + scAdd;
            }
        }
        if (s.charAt(hash1(str1)) == '0' && s.charAt(hash2(str1)) != '0') {
            filter_len += frAdd;
        }
        if (s.charAt(hash1(str1)) != '0' && s.charAt(hash2(str1)) == '0') {
            filter_len += scAdd;
        }
        // добавляем строку str1 в фильтр
    }

    public boolean isValue(String str1) {
        String s = Integer.toBinaryString(filter_len);
        if (s.charAt(hash1(str1)) == '1' && s.charAt(hash2(str1)) == '1') {
            return true;
        }
        return false;
    }
}
