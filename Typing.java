import java.io.File;
import java.io.FileNotFoundException;

import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;
import java.util.Collections;

public class Typing {

    public static void main(String[] args) {
        Typing typing = new Typing();

        if (args.length == 1) {
            typing.run(Integer.parseInt(args[0]));
        } else {
            typing.run(20);
        }
    }

    private void run(int question) {
        List<String> wordList = new ArrayList<>();
        long start = System.currentTimeMillis();
        int countBad = 0;

        fileScan(wordList);

        fileCut(wordList, question);

        for (String word : wordList) {
            boolean flag = true;
            
            System.out.printf("次の文字列を入力してください:%s%n", word);
            System.out.print("入力:");

            String str = strScan();

            while (flag == true) {
                if (str.equals(word)) {
                    flag = false;
                    System.out.printf("正解です:%s%n", str);
                } else {
                    System.out.print("間違いです、もう一度入力してください:");
                    countBad++;
                    str = strScan();
                }
            }
        }
        long end = System.currentTimeMillis();

        System.out.printf("あなたは%d回間違えました%n", countBad);
        System.out.printf("問題数:%s問%n", question);
        System.out.printf("経過時間:%s秒", (end - start) / 1000);
    }

    private String strScan() {
        Scanner strScan = new Scanner(System.in);
        String str = strScan.next();
        return str;
    }
    
    private List<String> fileScan(List<String> list) {
        try {
            File file = new File("test.txt");
            Scanner fileScan = new Scanner(file);

            while (fileScan.hasNext()) {
                list.add(fileScan.next());
            }

            Collections.shuffle(list);

        } catch (FileNotFoundException e) {
            System.out.println(e);
        }
        
        return list;
    }

    private List<String> fileCut(List<String> list, int question) {
        boolean judge = true;
        while (judge == true) {
            if (list.size() <= question) {
                judge = false;
            } else {
                list.remove(question);
            }
        }
        return list;
    }
}
