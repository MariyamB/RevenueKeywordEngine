/**
 * Created by Mariyam Rajeev George on 4/5/18.
 */
package com.keyword.revenue;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Stream;

public class KeywordUtil {
    private static final Pattern p = Pattern.compile("(\\?|\\&)(q|p|k)\\=([^&]+)"); //Regex to extract Search Keyword
    public BufferedReader getBufferedReader(String fileName) {
        ClassLoader cl = KeywordReveneue.class.getClassLoader();
        return new BufferedReader(new InputStreamReader(cl.getResourceAsStream(fileName)));
    }

    public Stream<String> SearchRevenue(BufferedReader br) throws IOException {
        String[] tableName = br.readLine().split("\t");
        ArrayList<String> columnNames = new ArrayList<>();
        do {
            br.mark(256);  // max length of column name or first data column
            String line = br.readLine();
            if (line.indexOf("\t") >= 0) {
                // End of header; this is a row of data
                br.reset();
                break;
            }
            columnNames.add(line);
        } while (true);
        System.out.println(String.format("%s\t%s\t %s",
                "Search Engine Domain", "Search Keyword", "Revenue"));
        return br.lines()
                //.map(row -> row.replace(";", " "))
                .map(row -> {
                    if (!domainAndSearchName(row.split("\t")[11], "search").isEmpty()) { //Splitting the Referrer column from the columns
                        //System.out.println(row.split("\t")[10]);
                        return formattedOutPut(row.split("\t")[11], row.split("\t")[10]);
                    } else
                        return "";
                });
    }

    public  String formattedOutPut (String domainAndSearch, String revenueString)
    {

        String revenue="";
        if (revenue.split(";").length>=4)
            revenue= revenueString.split(";")[3];
        return String.format("\n%s\t %s \t %s",
                domainAndSearchName(domainAndSearch, "domain"), domainAndSearchName(domainAndSearch, "search"),revenue );
    }
    /**
    Function to return Domain and Search Query
    **/
    public  String domainAndSearchName(String s, String type) {
        String domain = "";
        URL aURL = null;
        try {
            aURL = new URL(s);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        if ("domain".equalsIgnoreCase(type))
            return aURL.getHost();
        else
            return findSearchQuery(aURL.toString());
    }
    /**
     Function to return Search keyword based on regex supplied
     **/
    public  String findSearchQuery(String url) {
        Matcher m = p.matcher(url);
        if (m.find())
            return m.group(3);
        else
            return "";
    }

    public static String productRevenue(String productList) {

        return productList;
    }
}
