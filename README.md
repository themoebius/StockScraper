# StockScraper
a simple scraper project utilizing the free MarketWatch.com API and pushshift to get Stock data
and reddit-mentions for user-defined stocks / user defined subreddits

- setup needed- set your stocks and subreddits in the run-config:
  [stock1 stock2 stock3 stockX , subreddit1 subreddit2 subredditX]
- head over to https://marketstack.com/ to get your (free) API-key
  set an environment variable MARKETSTACK_KEY=your_key in the run-config
- and then just run...


  the resulting Stock Object holds:
- ticker name
- datetime
- volume
- opening price
- closing price
- daily high
- daily low
- number of social-media-mentions
- comments from reddit


the data for each stock will be saved/appended as a/ to a .txt-file 
in Json format with the according name in './generatedData'.

run once per day preferably after market-close.
the generated data can be used to be analyzed, visualized and modelled.


Outlook:
- a call to the 'real' reddit-api will be integrated
- a call to the twitter-api will be integrated
- social-media-mentions will be stored in a more fine-grained way
- maybe: measuring sentiment in the gathered posts/ comments will be integrated (Stanford CoreNLP code library)  




