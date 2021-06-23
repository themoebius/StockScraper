# StockScraper
a simple scraper project utilizing googleSearch and pushshift to get Stock data
and reddit-mentions for user-defined stocks

- no setup needed- just clone and set your stocks in the run-config
- (ie: tsla aapl gme)



  the resulting Stock Object holds:
  - ticker name
  - datetime
  - volume (in million $)
  - 60 days average volume (in million $)
  - daily high
  - daily low
  - number of social-media-mentions
  - comments from scraped social-media


the data for each stock will be saved/appended as a/ to a .txt-file 
in Json format with the according name in './generatedData'.

run once per day preferably after market-close.
the generated data can be used to be analyzed, visualized and modelled.


Outlook:
- a call to the 'real' reddit-api will be integrated
- a call to the twitter-api will be integrated
- scraping google will be replaced with a call to a finance-api  
- social-media-mentions will be stored in a more fine-grained way
- maybe: measuring sentiment in the gathered posts/ comments will be integrated (Stanford CoreNLP code library)  
- 



