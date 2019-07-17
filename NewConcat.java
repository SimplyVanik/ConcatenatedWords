package com.javarush.test;

import java.io.*;
import java.util.*;

public class NewConcat
{
    public static boolean isConcat(String con, HashSet<String> data, HashSet<String> res)
    {
        int len = con.length();
        boolean result = false;
        String tmp;

        while (!result && --len > 0) {
            tmp = con.substring(len);
            if (data.contains(con.substring(0, len)) && ((!res.isEmpty() && res.contains(tmp)) ||
                    data.contains(tmp) || isConcat(tmp, data, res)))
                result = true;
        }
        return (result);
    }

    public static void main(String[] args) throws IOException
    {
        long startTime = System.currentTimeMillis();
        HashSet<String> data = new HashSet<>();
        HashSet<String> res = new HashSet<>();
        ArrayList<String> arr = new ArrayList<>();
        ArrayList<String> resArr = new ArrayList<>();
        String tmp;

        File myFile = new File("C:\\javastudy\\JavaRushHomeWork\\src\\com\\javarush\\test\\words.txt");
        FileInputStream fIn = new FileInputStream(myFile);
        BufferedReader myReader = new BufferedReader(new InputStreamReader(fIn));
        while ((tmp = myReader.readLine()) != null)
            if (tmp.length() > 0)
            {
                data.add(tmp);
                arr.add(tmp);
            }

        Comparator c = new Comparator<String>()
        {
            public int compare(String s1, String s2) {
                return Integer.compare(s1.length(), s2.length());
            }
        };

        Collections.sort(arr, c);
        int sz = arr.size();
        int i = 0;
        while (arr.get(i).length() < 2)
            i++;
        while (i < sz) {
            int j = arr.get(i).length();
            while (--j > 0) {
                tmp = arr.get(i).substring(j);
                if (data.contains(arr.get(i).substring(0, j)) && ((!res.isEmpty() && res.contains(tmp)) ||
                        data.contains(tmp) || isConcat(tmp, data, res)))
                {
                    res.add(arr.get(i));
                    resArr.add(arr.get(i));
                    j = 0;
                }
            }
            i++;
        }

        Collections.reverse(resArr);

        File file = new File("newConcatenatedwords.txt");
        FileWriter writer = new FileWriter(file);
        writer.write("Total count of all the concatenated words - " + resArr.size() + "\n\n");
        for (String lst : resArr) {
            writer.write(lst + "\n");
        }
        writer.flush();
        writer.close();
        System.out.println(System.currentTimeMillis() - startTime);
    }
}
