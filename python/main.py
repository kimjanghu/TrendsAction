import urllib.request
import pandas as pd
import sys
import re
import requests


def crawling(keyword):
    client_id = "c1qLOQri0J79gp9juouv"
    client_secret = "06vcHY0hfX"

    search_word = keyword
    max_display = 100  # 출력 뉴스 수
    sort = 'sim'  # 결과값의 정렬기준 시간순 date, 관련도 순 sim
    start = 1
    url = "https://openapi.naver.com/v1/search/news.json?query=" + search_word + "&display=" + str(int(max_display)) + "&start=" + str(int(start)) + "&sort=" + sort
    request = urllib.request.Request(url)
    request.add_header('X-Naver-Client-Id', client_id)
    request.add_header('X-Naver-Client-Secret', client_secret)
    result = requests.get(url=url, headers={'X-Naver-Client-Id':"c1qLOQri0J79gp9juouv", 'X-Naver-Client-Secret':"06vcHY0hfX"})
    print(result)

    df = pd.DataFrame(result.json()['items'])

    df['title'] = df['title'].apply(lambda x: clean_html(x))
    df['description'] = df['description'].apply(lambda x: clean_html(x))

    df.to_json('C:/Users/multicampus/git/s03p31a408/python/' + search_word + '.json', force_ascii=False)


def clean_html(x):
    x = re.sub("\&\w*\;", "", x)
    x = re.sub("<.*?>", "", x)
    return x


if __name__ == '__main__':
    crawling(sys.argv[1])

# See PyCharm help at https://www.jetbrains.com/help/pycharm/
