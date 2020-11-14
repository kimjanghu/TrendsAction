import requests
import pandas as pd
from bs4 import BeautifulSoup
from datetime import datetime, timedelta

'''

# sort: 0->관련도순, 1->최신순
raw = requests.get("https://search.naver.com/search.naver?where=news&query=멀티페르소나&sort=0")
html = BeautifulSoup(raw.text, "html.parser")

articles = html.select("ul.list_news > li")

# img src
thumbnail = str(articles[0].select("a.dsc_thumb")[0].contents[0]['src'])
# press
press = str(articles[0].select("a.info")[0].contents[1])
# title
title = articles[0].select("a.news_tit")[0]['title']
# link
link = articles[0].select("a.news_tit")[0]['href']
# date
date = str(articles[0].select("span.info")[0].contents[0])
'''

keyword="브이노믹스"
df = pd.DataFrame(columns=['title', 'press', 'link', 'date', 'thumbnail'])
# start를 (1, 11, ~~스, 91)
for n in range(1, 100, 10):
    raw = requests.get("https://search.naver.com/search.naver?where=news&query="+keyword+"&sort=0&start="+str(n))
    html = BeautifulSoup(raw.text, "html.parser")

    articles = html.select("ul.list_news > li")

    for ar in articles:
        try:
            ar.select("a.dsc_thumb")[0].contents[0]['src']
            thumbnail = str(ar.select("a.dsc_thumb")[0].contents[0]['src'])

        except:
            thumbnail = "null"

        if len(ar.select("span.info")) == 1:
            date = str(ar.select("span.info")[0].contents[0])
        else:
            date = str(ar.select("span.info")[1].contents[0])

        if '전' in date:
            ago = int(date[0])
            date = datetime.today() - timedelta(ago)
            date = date.strftime("%Y.%m.%d.")

        dic = {'title': ar.select("a.news_tit")[0]['title'],
               'press': str(ar.select("a.info")[0].contents[1]),
               'link': ar.select("a.news_tit")[0]['href'],
               'date': date,
               'thumbnail': thumbnail}
        df = df.append(dict(dic), ignore_index=True)

df.to_json(f'/Users/donghwi/Desktop/Trend/python/{keyword}_news.json', force_ascii=False)