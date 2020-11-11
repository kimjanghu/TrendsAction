import urllib.request
import pandas as pd
import sys
import re
import requests

def crawling(keyword):
    client_id = "c1qLOQri0J79gp9juouv"
    client_secret = "06vcHY0hfX"

    search_word = '멀티 페르소나'
    max_display = 100  # 출력 뉴스 수
    sort = 'date'  # 결과값의 정렬기준 시간순 date, 관련도 순 sim
    start = 1
    url = f"https://openapi.naver.com/v1/search/news.json?query={search_word}&display={str(int(max_display))}&start={str(int(start))}&sort={sort}"
    request = urllib.request.Request(url)
    request.add_header('X-Naver-Client-Id', client_id)
    request.add_header('X-Naver-Client-Secret', client_secret)
    result = requests.get(url=url, headers={'X-Naver-Client-Id':"c1qLOQri0J79gp9juouv", 'X-Naver-Client-Secret':"06vcHY0hfX"})
    print(result)

    df = pd.DataFrame(result.json()['items'])

    df['title'] = df['title'].apply(lambda x: clean_html(x))
    del df['description']
    print(df)

    df.to_json(f'/Users/donghwi/Desktop/Trend/python/{search_word}.json', force_ascii=False)



def clean_html(x):
    x = re.sub("\&\w*\;", "", x)
    x = re.sub("<.*?>", "", x)
    return x


if __name__ == '__main__':
    crawling(sys.argv[1])
