package com.keyword.revenue;
import org.junit.Test;
import junit.framework.TestCase;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.text.SimpleDateFormat;
import java.util.Date;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

/**
 * Created by Maria on 4/5/18.
 */
public class KeywordUtilTest {
   String domain = "http://www.google.com/search?hl=en&client=firefox-a&rls=org.mozilla%3Aen-US%3Aofficial&hs=ZzP&q=Ipod&aq=f&oq=&aqi=";
    String query = "Ipod";

    @Test
    public void test_domainName() {

        KeywordUtil keywordUtil = new KeywordUtil();
        assertEquals("www.google.com", keywordUtil.domainAndSearchName(domain, "domain"));
    }
    @Test
    public void test_search_keyworkd() {

        KeywordUtil keywordUtil = new KeywordUtil();
        assertEquals("Ipod", keywordUtil.domainAndSearchName(domain, "search"));
    }

    @Test
    public void test_integration_test_SearchRevenueSuccess () throws IOException {
        Date date = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        KeywordUtil keywordUtil = new KeywordUtil();
        File file1 = new File(dateFormat.format(date) + "_SearchKeywordPerformance.tab");
        FileWriter fileWriter = new FileWriter(file1, true);
        try (BufferedReader br = keywordUtil.getBufferedReader("data.sql")) {

            keywordUtil.SearchRevenue(br).forEach(r -> {
                try {
                    System.out.print(r);
                    Files.write(Paths.get(dateFormat.format(date) + "_SearchKeywordPerformance.tab"), r.getBytes(), StandardOpenOption.APPEND);

                } catch (IOException e) {
                    assertFalse(true);
                }
            });
        } catch (IOException e) {
            assertFalse(true);

        } finally {
            fileWriter.close();
        }
    }
}



