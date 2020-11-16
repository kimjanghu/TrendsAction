from articlecrawler import ArticleCrawler

if __name__ == "__main__":
    Crawler = ArticleCrawler()
    Crawler.set_category("세계")  # 정치, 경제, 생활문화, IT과학, 사회, 세계 카테고리 사용 가능
    Crawler.set_date_range(2020, 1, 2020, 2)  # 2017년 12월부터 2018년 1월까지 크롤링 시작
    Crawler.start()


    # 정치, 경제는  빼고 하는걸로 메모,.



#세계는 중간정도
# 생활문화 IT 중간  생활문화에서 날씨 규칙기반 컷트

#칼럼 최상
    # {'정치': 100, '경제': 101, '사회': 102, '생활문화': 103, '세계': 104, 'IT과학': 105, '오피니언': 110,
    #  'politics': 100, 'economy': 101, 'society': 102, 'living_culture': 103, 'world': 104,
    #  'IT_science': 105, 'opinion': 110}
