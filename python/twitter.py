# -*- coding: utf-8 -*-

import sys
import io
import pandas as pd
import tweepy
import re

# 한글 인코딩 처리
sys.stdout = io.TextIOWrapper(sys.stdout.detach(), encoding='utf-8')
sys.stderr = io.TextIOWrapper(sys.stderr.detach(), encoding='utf-8')

# 트위터 API
consumer_key = "zQiqzPjoNk7oooKDi6yl8z1XW"
consumer_secret = "p5JEhtcEMXRXnx8OI9VG4XZDHtmRKrmfY7aoJWHgGpKyu4b527"
access_token = "1320540295658795010-NYVfhq1GPWSmLQSUefquQ6yYbuktOd"
access_secret = "ruhSGYbTPD3lx4EiHyMKHOqwOkbQc0veazDvyqz3VIXQh"

# 계정 승인
auth = tweepy.OAuthHandler(consumer_key=consumer_key, consumer_secret=consumer_secret)
auth.set_access_token(access_token, access_secret)
twitter_api = tweepy.API(auth)

# 검색 키워드정의
# keyword = "유재석"
api_result = []

# text, name, created_at


def search_by_keyword(keyword):
    search_word = "멀티 페르소나"
    print(search_word)

    df = pd.DataFrame(columns=['author', 'contents', 'date', 'categoryId', 'trendId'])

    # 키워드 검색 및 결과
    tweet = twitter_api.search(search_word, count=20, result_type='date')
    for tw in tweet:
        print('turn!!')
        # print(tw)
        # api_result.append(tw.text)  # 텍스트 결과만 담기
        # print('글쓴이 >> ' + str(tw.user.name))
        # print('내용 >> ' + str(tw.text))
        # print('작성일 >> ' + str(tw.created_at))
        new_date = {'author': str(tw.user.name), 'contents': str(tw.text), 'date': str(tw.created_at)}
        # print(new_date)
        df = df.append(dict(new_date), ignore_index=True)

    print('출력')
    # print(str(df['author']) + ' ' + str(df['contents']) + ' ' + str(df['date']))
    # df.head(5)
    # print(tweet[0])
    # print(len(api_result))
    # print(json.dump(tweet[0].created_at))
    # print(tweet[0].created_at)

    df['author'] = df['author'].apply(lambda x: clean_html(x))
    df['contents'] = df['contents'].apply(lambda x: clean_html(x))

    df.to_json(f'/Users/donghwi/Desktop/{search_word}.json', force_ascii=False)


def clean_html(x):
    x = re.sub("\&\w*\;", "", x)
    x = re.sub("<.*?>", "", x)
    return x


if __name__ == '__main__':
    search_by_keyword(sys.argv[1])
