package com.company;

import java.io.*;
import java.util.*;

public class Main {



    public static boolean isConcat(String con, ArrayList<String> in) {
        int sz = in.size();
        int i = -1;
        boolean res = false;

        while (++i < sz && res == false && !in.get(i).equals("")) {
            if (con.equals(in.get(i)))
                res = true;
            if (con.length() > in.get(i).length() && con.startsWith(in.get(i))) {
                ArrayList<String> tmp = new ArrayList<>(in);
                /*
                 * if you want find unique concatenated word (without overlapping of the same word some times)
                 * just activate the next line.
                 */
//                tmp.remove(i);
                res = isConcat(con.substring(in.get(i).length()), tmp);
            }
        }
        return (res);
    }

    public static void main(String[] args) throws IOException{

        ArrayList<String> arr = new ArrayList<>();
        ArrayList<String> in = new ArrayList<>();
        ArrayList<String> res = new ArrayList<>();
        String tmp;


        File myFile = new File("words.txt");
        FileInputStream fIn = new FileInputStream(myFile);
        BufferedReader myReader = new BufferedReader(new InputStreamReader(fIn));
        while ((tmp = myReader.readLine()) != null)
        {
            arr.add(tmp);
        }

        Collections.sort(arr, Comparator.comparing(String::length));
        Collections.reverse(arr);

        int sz = arr.size();
        int i = -1;

        while (++i < sz - 1)
        {
            int j = i;
            while (j < sz && arr.get(j).length() == arr.get(i).length())
                j++;
            while (j < sz)
            {
                if (arr.get(i).contains(arr.get(j)))
                    in.add(arr.get(j));
                j++;
            }
            if (isConcat(arr.get(i), in))
                res.add(arr.get(i));
            in.clear();
        }

        File file = new File("concatenatedwords.txt");
        FileWriter writer = new FileWriter(file);
        writer.write("Total count of all the concatenated words - " + res.size() + "\n\n");
        for (String lst : res) {
            writer.write(lst + "\n");
        }
        writer.flush();
        writer.close();
    }
}
