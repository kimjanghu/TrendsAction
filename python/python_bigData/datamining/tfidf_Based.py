# -*- coding: utf-8 -*-

import pandas as pd
import matplotlib.font_manager as fm
import matplotlib.pyplot as plt
from future.utils import iteritems
from collections import Counter
from sklearn.manifold import TSNE
from sklearn.feature_extraction.text import TfidfVectorizer
from sklearn.feature_extraction.text import CountVectorizer


# article_data = pd.read_csv('./Article_IT과학/Article_IT과학_202001_202010.csv', encoding='euc-kr', header= None)
article_data = pd.read_csv('./Article_IT과학/Article_IT과학_202001_202002.csv', encoding='euc-kr', header= None)
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
# print("뉴스 개수" + str(news_size))
# print(article_data.iloc[86])
# print(article_data.iloc[86][3])



#하루 단위로 기사 내용을 stringLine에 다 합치고 list_whenDayChange에 append
list_whenDayChange = []
stringLine = ""
nowDays = 0

# print(article_data.iloc[0][0] + " " + article_data.iloc[1][0])

for i in range(0, news_size):
    if i >- (news_size -7):
        stringLine += article_data.iloc[i][3]
        if i == (news_size -1):
            list_whenDayChange.append(stringLine)
    else:
        if article_data.iloc[nowDays][0] == article_data.iloc[i][0]:
            stringLine += " " + article_data.iloc[i][3]
        else:
            # print("날짜가 달라졌음 " + str(i))
            # print("날짜는 " + str(article_data.iloc[i][0]))
            nowDays = i
            list_whenDayChange.append(stringLine)
            stringLine = ""

#리스트 내 하루단위로 쪼개진 문자열들 확인해보기
# for i in range(len(list_whenDayChange)):
#     print(list_whenDayChange[i])
#     print(" ")
#     print(i)

# print(len(list_whenDayChange))


#단어 인덱싱 및 빈도세기
as_one = ''
for document in list_whenDayChange:
    as_one = as_one + ' ' + document
words = as_one.split()
# print(words[0:10])

#단어들의 연속적인 시리즈로 된 리스트를 입력으로 Counter를 사용하면 각 단어들의 빈도를 dictionary로 반환해 줌
counts = Counter(words)
# print(counts)

#단어빈도(counts.get)를 기준으로 내림차순(reverse=True) 정렬
vocab = sorted(counts, key=counts.get, reverse=True)
# print(vocab)

#단어들에 번호를 매겨 그 번호와 그 단어를 dictionary로 저장 e.g. {단어 : index}
word2idx = {word.encode("utf8").decode("utf8"): ii for ii, word in enumerate(vocab,1)}
# print(word2idx)

#index가 key가 되도록 순서를 바꿈
idx2word = {ii: word for ii, word in enumerate(vocab)}
# print(idx2word)

#Term Frequency
V = len(word2idx)
N = len(list_whenDayChange)
tf = CountVectorizer()

onlyLast = [list_whenDayChange[N-1]]
# print(onlyLast)
X = tf.fit_transform(onlyLast)
print(X)

#이게 가장 최근 문서 하나의 각 문자의 tf 개수 행렬
print(X.toarray()[0])

#이게 가장 최근 문서 하나의 각 index를 단어로 바꾼 것.
print(tf.get_feature_names())

print(len(X.toarray()[0]))
print(len(tf.get_feature_names()))


#TF-IDF
# tfidf = TfidfVectorizer(max_features = 1000, max_df=0.95, min_df=0)

#generate tf-idf term-document matrix
# A_tfidf_sp = tfidf.fit_transform(list_whenDayChange)  #size D x V

# print(A_tfidf_sp)

#tf-idf dictionary
# tfidf_dict = tfidf.get_feature_names()
# print(tfidf_dict)

# data_array = A_tfidf_sp.toarray()
# data = pd.DataFrame(data_array, columns=tfidf_dict)
# print(data.shape)
# print(data)


#TF-IDF score Top n개 단어 시각화
# tsne = TSNE(n_components=2, n_iter=10000, verbose=1)
# print(data_array.shape)
# print(data_array.T.shape)

# Z = tsne.fit_transform(data_array.T)

#시각화
# path = './gabia_solmee.ttf'
# fontprop = fm.FontProperties(fname=path, size=10)
# plt.scatter(Z[:,0], Z[:,1])
# for i in range(len(tfidf_dict)):
#     plt.annotate(text=tfidf_dict[i].encode("utf8").decode("utf8"), xy=(Z[i,0], Z[i,1]),fontProperties =fontprop)
#     plt.pause(0.05)

# plt.draw() 는 pycharm에서 아무반응 없고 stackoverflow는 아래 2줄 + 위의 plt.pause로 시간지연 추천함
# plt.ioff()
# plt.show()