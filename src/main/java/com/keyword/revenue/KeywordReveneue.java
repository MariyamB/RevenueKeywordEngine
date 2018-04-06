
/**
 * Created by Mariyam Rajeev George on 4/5/18.
 */
package com.keyword.revenue;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Date;
import java.text.SimpleDateFormat;

public class KeywordReveneue {

    public static void main(String[] args) throws IOException {
        KeywordUtil keywordUtil = new KeywordUtil();
        Date date = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        File file1 = new File(dateFormat.format(date) + "_SearchKeywordPerformance.tab");
        FileWriter fileWriter = new FileWriter(file1, true);
        Files.write(Paths.get(dateFormat.format(date) + "_SearchKeywordPerformance.tab"), String.format("%s\t%s\t %s",
                "Search Engine Domain", "Search Keyword", "Revenue").getBytes(), StandardOpenOption.APPEND);
        try (BufferedReader br = keywordUtil.getBufferedReader(args[0])) {
            keywordUtil.SearchRevenue(br).forEach(r -> {
                try {
                    System.out.print(r);
                    Files.write(Paths.get(dateFormat.format(date) + "_SearchKeywordPerformance.tab"), r.getBytes(), StandardOpenOption.APPEND);


                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
        } finally {
            fileWriter.close();
        }
    }


}
