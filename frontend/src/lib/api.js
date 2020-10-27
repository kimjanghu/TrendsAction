export default {
  URL: 'http://k3a408.p.ssafy.io:',
  BOARD: '8882',
  ROUTES: {
    accounts: {
      URL: 'http://k3a408.p.ssafy.io:8880',
      user: '/user', // GET / 유저 정보 받아오기, PUT / 유저 생성
      deleteUser: '/user/', // DELETE / 유저 정보 삭제
      checkNickname: "/user/checknickname/", // GET / 닉네임 중복 체크
    },
    trends: {
      URL: 'http://k3a408.p.ssafy.io:8881/api',
      trendCategories: '/trend/', // GET / 카테고리별 트렌드 목록 조회
      trendNews: '/trend/news/', // GET / 트렌드에 맞는 뉴스 목록 조회
      trendSns: '/trend/twitter/', // GET / 트렌드에 맞는 트위터 목록 조회
      predictTrend: '/trend/predictTrend' // GET / 예측 트렌드 키워드 목록 조회
    },
    comments: {
      URL: 'http://k3a408.p.ssafy.io:8881/api',
      rdComment: '/comment/', // GET / 해당 트렌드의 댓글 목록 조회, DELETE / 해당 트렌드 댓글 삭제
      cuComment: '/comment', // POST / 해당 트렌드에 댓글 생성, PUT / 해당 트렌드 댓글 수정
    },
  }
}