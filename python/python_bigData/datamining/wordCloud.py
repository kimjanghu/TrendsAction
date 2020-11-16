# -*- coding: utf-8 -*-

import pandas as pd
import numpy as np
import matplotlib.font_manager as fm
import matplotlib.pyplot as plt
from future.utils import iteritems
from collections import Counter
from sklearn.manifold import TSNE
from sklearn.feature_extraction.text import TfidfVectorizer
from sklearn.feature_extraction.text import CountVectorizer
import datetime
from wordcloud import WordCloud

# 꼬꼬마 형태소 분석기
from konlpy.tag import Kkma
kkma = Kkma()

print('시작스')

article_data = pd.read_csv('./forWordCloud/사회/society11-2.csv', encoding='euc-kr', header= None)
article_data = article_data.dropna(axis=0)
print(article_data)
news_size = article_data[0].size
print("뉴스 개수 = " + str(news_size))
stringLine = ""

for i in range(0, news_size):
    if i % 3 == 0 :
        stringLine += article_data.iloc[i][3] + " "

print("합치기 끝")

nouns = kkma.nouns(stringLine)

print("nouns 끝")

words = []
for n in nouns:
    if len(n) > 1:
        words.append(n)

print(words)

count = Counter(words)
print("abc4")
most = count.most_common(100)
print("abc5")
tags = {}

for n,c in most:
    tags[n] = c
print("abc6")
wc = WordCloud(font_path = "gabia_solmee.ttf", width = 1200, height = 800,
               scale = 2.0, max_font_size = 250)
gen = wc.generate_from_frequencies(tags)

plt.figure()
plt.imshow(gen, interpolation='bilinear')
wc.to_file("society11-2.png")
plt.close()