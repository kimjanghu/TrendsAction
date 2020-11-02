export default {
  URL: 'http://k3a408.p.ssafy.io:9090',
  ROUTES: {
    accounts: {
      kakaoLogin: '/users/login/kakao',
      user: '/users/user', // GET / 유저 정보 받아오기, PUT / 유저 생성
      deleteUser: '/users/user/', // DELETE / 유저 정보 삭제
      checkNickname: '/users/user/checknickname', // POST / 닉네임 중복 체크
    },
    trends: {
      trendCategories: '/trends/trend/', // GET / 카테고리별 트렌드 목록 조회
      trendNews: '/trends/trend/news/', // GET / 트렌드에 맞는 뉴스 목록 조회
      trendSns: '/trends/trend/twitter/', // GET / 트렌드에 맞는 트위터 목록 조회
      predictTrend: '/trends/trend/predictTrend' // GET / 예측 트렌드 키워드 목록 조회
    },
    comments: {
      rdComment: '/trends/comment/', // GET / 해당 트렌드의 댓글 목록 조회, DELETE / 해당 트렌드 댓글 삭제
      cuComment: '/trends/comment', // POST / 해당 트렌드에 댓글 생성, PUT / 해당 트렌드 댓글 수정
    },
    boards: {
      getBestNews: '/boards/board/news/scrapcount/',
      getBoardList: '/boards/board/',
      addNewBoard: '/boards/board',
      addNews: '/boards/board/news',
    }
  }
}