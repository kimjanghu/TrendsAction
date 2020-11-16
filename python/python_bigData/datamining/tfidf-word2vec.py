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


# 꼬꼬마 형태소 분석기
from konlpy.tag import Kkma
kkma = Kkma()

# Word2Vec 불러오기
from gensim.models.word2vec import Word2Vec

print('abc')
# Input data 정하기
# article_data = pd.read_csv('./Article_세계/Article_세계_202001_202002.csv', encoding='euc-kr', header= None)
# article_data = pd.read_csv('./Article_IT과학/Article_IT과학_202001_202002.csv', encoding='euc-kr', header= None)
# article_data = pd.read_csv('./Article_세계/Article_세계_202001_202010.csv', encoding='euc-kr', header= None)
# article_data = pd.read_csv('./Article_사회_202007_202010.csv', encoding='euc-kr', header= None)
article_data = pd.read_csv('../../self_nogada/IT과학/Article_IT과학_10월5주.csv', encoding='euc-kr', header= None)

# 약간의 설명
# row[0] : 뉴스날짜 row[1] : 뉴스 카테고리 row[2] : 뉴스원본사 row[3] : 뉴스제목 row[4] : 뉴스 내용 row[5] : 네이버뉴스 링크
# print(article_data.iloc[86]) 그냥 article_data[1][10000]로 출력할 시 결측치 제거한 후 인덱스가 비어버렸기 때문에 키값 에러뜸. 접근 안돼서
# article_data[1] IT과학 세로로 쭉 / article_data[1][10000] 1만번 째의 뉴스 IT과학 / article_data[3][4] 4번째 뉴스의 뉴스 내용

#뉴스의 개수
news_size = article_data[0].size
# print("뉴스 개수" + str(news_size))
# print(article_data.iloc[86])

# NAN 결측치 제거 (euc-kr~~~ 로 적힌 잘못된 뉴스정보가 있다면 제거)
article_data = article_data.dropna(axis=0)

#결측치 제거 후 뉴스의 개수
news_size = article_data[0].size
print("뉴스 개수" + str(news_size))
# print(article_data.iloc[86])
# print(article_data.iloc[86][3])

#하루 단위로 기사 내용을 stringLine에 다 합치고 list_whenDayChange에 append
list_whenDayChange = []
stringLine = ""
nowDays = 0

# print(article_data.iloc[0][0] + " " + article_data.iloc[1][0])

# 오늘 날짜
today = datetime.date.today()

# 11월 1일
november = datetime.date(2020, 11, 1)
# 10월까지
new_year = datetime.date(2020, 1, 1)

diff = int(str(november - new_year).split(' ')[0])
print(str(diff))

now_day_diff = 0
for i in range(0, news_size):
    if now_day_diff >= (diff - 7):
        stringLine += article_data.iloc[i][3]
        if i == (news_size - 1):
            list_whenDayChange.append(stringLine)
    else:
        if article_data.iloc[nowDays][0] == article_data.iloc[i][0]:
            stringLine += " " + article_data.iloc[i][3]
        else:
            # print("날짜가 달라졌음 " + str(i))
            # print("날짜는 " + str(article_data.iloc[i][0]))
            now_date = str(article_data.iloc[i][0])
            # print(now_date[0:4] + " " + now_date[4:6] + " " + now_date[6:8])
            now_day_diff = int(str(datetime.date(int(now_date[0:4]), int(now_date[4:6]), int(now_date[6:8])) - new_year).split(' ')[0])
            # print(now_date + " >>> " + str(now_day_diff))
            nowDays = i
            list_whenDayChange.append(stringLine)
            stringLine = ""

print("List Size >> " + str(len(list_whenDayChange)))

#리스트 내 하루단위로 쪼개진 문자열들 확인해보기
# for i in range(len(list_whenDayChange)):
#     print(list_whenDayChange[i])
#     print(" ")
#     print(i)

#단어 인덱싱 및 빈도세기
# as_one = ''
# for document in list_whenDayChange:
#     as_one = as_one + ' ' + document
# words = as_one.split()
# print(words[0:10])

# words = []
# for i in range(len(list_whenDayChange)):
#     words.append(kkma.nouns(list_whenDayChange[i]))
#
# words = [[y for y in x if not len(y) == 1] for x in words]
# words = [[y for y in x if not y.isdigit()] for x in words]
#
# words_sum = ''
# for i in range(len(words)):


#단어들의 연속적인 시리즈로 된 리스트를 입력으로 Counter를 사용하면 각 단어들의 빈도를 dictionary로 반환해 줌
# counts = Counter(words)
# print(counts)

#단어빈도(counts.get)를 기준으로 내림차순(reverse=True) 정렬
# vocab = sorted(counts, key=counts.get, reverse=True)
# print(vocab)

#단어들에 번호를 매겨 그 번호와 그 단어를 dictionary로 저장 e.g. {단어 : index}
# word2idx = {word.encode("utf8").decode("utf8"): ii for ii, word in enumerate(vocab,1)}
# print(word2idx)

#index가 key가 되도록 순서를 바꿈
# idx2word = {ii: word for ii, word in enumerate(vocab)}
# print(idx2word)

#Term Frequency
# V = len(word2idx)
N = len(list_whenDayChange)
tf = CountVectorizer()
onlyLast = [list_whenDayChange[N - 1]]
X = tf.fit_transform(onlyLast)
onlyLastByWord = ""

# for i in range(0, len(tf.get_feature_names())):
#     if X.toarray()[0][i] >= 7:
#         for j in range(0, int(X.toarray()[0][i] / 7)):
#             onlyLastByWord += tf.get_feature_names()[i] + ' '

for i in range(0, len(tf.get_feature_names())):
        for j in range(0, X.toarray()[0][i]):
            onlyLastByWord += tf.get_feature_names()[i] + ' '

print(onlyLastByWord)
previous_last_list = list_whenDayChange[N - 1]
list_whenDayChange[N-1] = onlyLastByWord

stopwords = ['']
#TF-IDF
tfidf = TfidfVectorizer(max_features=5000, max_df=0.95, min_df=0,stop_words=stopwords)
#generate tf-idf term-document matrix
A_tfidf_sp = tfidf.fit_transform(list_whenDayChange)  #size D x V

#tf-idf dictionary
tfidf_dict = tfidf.get_feature_names()
# print(tfidf_dict)

#TF-IDF score Top n개 단어 시각화
data_array = A_tfidf_sp.toarray()
data = pd.DataFrame(data_array, columns=tfidf_dict)
# print(data.shape)
print(data.tail(1))

feature_array = np.array(tfidf.get_feature_names())
tfidf_sorting = np.argsort(A_tfidf_sp[N-1].toarray()).flatten()[::-1]

n = 20
top_n = feature_array[tfidf_sorting][:n]
i = 0
for text in top_n:
    top_n[i] = text.upper()
    i = i + 1

print(top_n)

        # 이전 작업 내용


# # 단어 인덱싱 및 빈도세기
# as_one = ''
# as_one = previous_last_list.split()
# print("우리의 테스트@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@")
# print("바이든이 있다 없다?! >> " + str(as_one.__contains__('바이든')))
# print(as_one)
#
# list_whenDayChange_split = []
#
# for i in range(0, N - 1):
#     # print(str(i) + "번째 문장!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!")
#     list_whenDayChange_split.append(list_whenDayChange[i].split(' '))
#     list_whenDayChange_split[i].remove('')
#     # print(list_whenDayChange_split[i])
# list_whenDayChange_split.append(as_one)
#
# print(type(as_one))
# print(type(list_whenDayChange_split))
# print('flag1')
# # 모형 구축
# model = Word2Vec([as_one],
#                  sg=1,          # Skip-grqm 적용 : 중심 단어로 주변 단어를 예측
#                  window=5,      # 중심 단어로부터 좌우 5개 단어까지 학습에 적용
#                  min_count=1    # 전체문서에서 최소 1회 이상 출현단어로 학습 진행
#                  )
# model.init_sims(replace=True)
#
# print('flag2')
# print("바이든과 대선 단어간 유사도는 %.2f 입니다." % (model.wv.similarity('바이든', '대선')))
# print('flag3')
#
# for i in range(1, N):
#     print(top_n[0] + " " + top_n[i])
#     print("%s과 %s 단어간 유사도는 %.2f 입니다." % (top_n[0], top_n[i], model.wv.similarity(top_n[0], top_n[i])))
#
# vocab_frame = pd.DataFrame(model.wv.most_similar('바이든', topn=10), columns=['단어', '유사도'])
# print(vocab_frame)

########################

        #새 작업
dataset = []
for i in range(len(list_whenDayChange)):
        dataset.append(kkma.nouns(list_whenDayChange[i]))
# print(dataset[0])

dataset = [[y for y in x if not len(y) == 1] for x in dataset]
dataset = [[y for y in x if not y.isdigit()] for x in dataset]

model = Word2Vec(dataset, sg=1, window=5, min_count=1)
model.init_sims(replace=True)

print(top_n[0])
vocab_frame = pd.DataFrame(model.wv.most_similar(top_n[0], topn=2000), columns=['단어', '유사도'])
print(vocab_frame.head(10))

vocab_frame = vocab_frame[vocab_frame['단어'].isin(top_n)]
print(vocab_frame)
# for i in range(0,5):
#     print(vocab_frame.loc[i]['단어'])

add_frame = pd.Series([top_n[0],1], index = ['단어','유사도'])
vocab_frame = vocab_frame.append(add_frame, ignore_index = True)

vocab_frame.to_csv("C:\ssafy\project_3\python_bigData\datamining\multi_mining\IT5.csv", mode='w', encoding='euc-kr')
