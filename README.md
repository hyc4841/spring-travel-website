
<img src="https://capsule-render.vercel.app/api?type=waving&color=BDBDC8&height=150&section=header" />
<img src="https://capsule-render.vercel.app/api?type=waving&color=BDBDC8&height=150&section=footer" />

[![Top Langs](https://github-readme-stats.vercel.app/api/top-langs/?username=hyc4841)](https://github.com/anuraghazra/github-readme-stats)

출처: https://hulrud.tistory.com/3 [주독야독:티스토리]
구현해야할 기능들

게시판 : 본인이 작성한 사람 이외에 사람이 수정하는거 막기

게시판 : 사진 추가 및 삭제 기능


숙소 : 예약 취소, 결재 취소

숙소 : 방 기간에 따라 가격 다르게 하기(성수기, 비수기, 주말, 평일)

숙소 예약 기능 완성. 예약 내역에 따라 검색되는 것도 구현함. 

예약 취소 기능도 만들어야함. => 마이 페이지에서 예약 내역 확인 페이지 만들어야함



예약 페이지 디자인 손봐야함.

게시판도 다듬어서 마무리하면 될듯.

후순위

숙소 : 숙소 검색 리스트에서 페이징도 구현 해야함.

숙소 : 숙소 상세 페이지에서 모달로 띄우기도 시도해보자

상세 보기는 모달로 띄우기

오늘은 전체적으로 결재쪽 수정하는 쪽으로

- 마이바티스에서 select문 객체로 가져올 때 생성자도 영향이 있으니까 조심해야함....
엉뚱한 생성자 있으면 거기로 만들어져서 자꾸 타입 캐스팅 오류남...

- html 폼에서 컨트롤러에서 데이터가 담긴 객체를 넘긴다고 그게 다음 컨트롤러로 그대로 넘어가는게 아님. input 필드로 submit 되어야 데이터가 정상적으로 넘어감. 아니면 requestParam으로 넘기던가 해야함.





















