# Problem Statement
How much revenue is the client getting from external Search Engines, such as Google, Yahoo and MSN, and which keywords are performing the best based on revenue?

# Development Requirements
¥	Create a Java application that executes from the command line.
¥	 The Java application needs to be broken up into a runtime script and at least 1 class.
¥	 The runtime script needs to accept a single argument, which is the file that needs to be processed.
¥	 The class can be whatever you want it to be but it needs to be used by the runtime script

# Deliverable Requirements:
Final output needs to be a tab delimited file with the following data points
¥	Search Engine Domain (i.e. google.com)
¥	Search Keyword (i.e. ͞Laffy Taffy͟)
¥	Revenue (i.e. 12.95)
The output file should have the following naming convention: [Date]_SearchKeywordPerformance.tab

# Application:
¥	KeywordReveneue.java
¥	KeywordUtil.java
  KeywordUtil.test

# Scaling:
Scaling can be done with MapReduce Program
¥	Mapper: Generates key value pairs(Key here being the search engine and the search keyword and value being the revenue)
¥	Reducer: Sum up the revenue based on the aggregating the keys.The number of reducers are specified to scale the application based on the file size.

# Logic Used:
Referrer column values were used to extract the Search Keyword and the Search Engines.The search keyword consisted of the product which was searched.Revenue for the searched product was derived from the product list.

# Findings from the sample data file :
There are some records which had a referrer did not have product list.
Some records which had a product list with revenue , had no referrer values , but had event list which does not have a search keyword.
So ,for this purpose I did some data checks which involved not printing records which did not have a referrer.

# To Execute the Program
Run 
java -jar out/artifacts/Keyword_jar/Keyword.jar data.sql

# Limitations:
Revenue has not been sorted in descending order
