package ADS.BloomFilter;

public class BloomFilter
{
    public int filter_len;

    public BloomFilter(int f_len)
    {
        filter_len = f_len;
        // создаём битовый массив длиной f_len ...
    }

    // хэш-функции
    public int hash1(String str1)
    {
        // 17
        for(int i=0; i<str1.length(); i++)
        {
            int code = (int)str1.charAt(i);
        }
        // реализация ...
        return 0;
    }
    public int hash2(String str1)
    {
        // 223
        // реализация ...
        return 0;
    }

    public void add(String str1)
    {
        // добавляем строку str1 в фильтр
    }

    public boolean isValue(String str1)
    {
        // проверка, имеется ли строка str1 в фильтре
        return false;
    }
}
