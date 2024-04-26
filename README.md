# Comtact
디스코드 앱 디자인을 기반으로 한 연락처 앱

## 프로젝트 소개
* 안드로이드의 각 기능을 익히고 깔끔한 UI를 보여주기 위해 제작하였습니다.
* 또한 연락처 목록에 회사 고객센터를 넣었기 때문에 Company + Contact를 합쳐서 작명하게 되었습니다.

## 개발 기간
* 2024년 4월 22일 ~ 2024년 4월 28일

## 개발자
### 팀장
* [공지훈](https://github.com/Cosine-A) [ContactDetailFragment 개발, 전반적인 코드 유지보수]
### 팀원
* [서해윤](https://github.com/SeoHeaYun) [ContactListFragment 개발]
* [전주원](https://github.com/wndnjs00) [MyPageFragment 개발]
* [박민수](https://github.com/eddy-PMS) [ContactDialogFragment 개발]

## 와이어프레임
<img width="200" alt="리스트페이지" src="https://github.com/CosineAndroid/Contact/assets/100404990/32b3aea0-af2f-4ea4-859e-4f6998d8c61c">

<img width="200" alt="디테일페이지" src="https://github.com/CosineAndroid/Contact/assets/100404990/76c87c83-9a7b-485b-9535-d061b9bf52d8">

<img width="200" alt="마이페이지" src="https://github.com/CosineAndroid/Contact/assets/100404990/42ba5c2a-b50e-4724-8f6a-a29e6c03dbbc">

<img width="200" alt="다이얼로그" src="https://github.com/CosineAndroid/Contact/assets/100404990/4ec534b7-0c2b-44e6-b518-4776bbf337f2">

## Git 전략
* 기능별로 브랜치를 나누고 커밋(풀 리퀘스트)을 할 땐 아래 사진과 같이 작성하였습니다. 
* 완성 후 최종적으로 main 브랜치로 옮겨 배포하였습니다.  
![스크린샷 2024-04-26 111850](https://github.com/CosineAndroid/Contact/assets/100404990/996088fd-6ac5-425e-8671-a7b1945ce258)
![스크린샷 2024-04-26 111821](https://github.com/CosineAndroid/Contact/assets/100404990/57c66da7-c35a-4aa5-ad1a-e34bf42a6a8e)

## 트러블 슈팅
### 서해윤
* 문제: 이미지가 타입이 drawble과 url타입으로 서로 포맷이달라 set해줄 때 트러블 발생
* 해결법: adapter에서 tpye 별로 분기처리하여 다른 방식으로 set 해주기
* 알게된 점: 어렵게 생각하지말고 간단하게 생각하자.
### 전주원
* 문제: 처음에 clone해온 파일을 수정한뒤, git push 했을때 push가 안되는 문제 발생
* 해결법: git push --set-upstream origin mypage 를 터미널에 입력하여 브랜치 설정 해준후, 맥의 키체인암호 입력한뒤 다시 푸시해서 해결
* 알게된 점: 처음에 clone했을때 원격저장소에 기본 브랜치 설정을 안해주면 push할때 오류가 뜬다는 점을 알게됨
### 박민수
* 문제: 실시간 유효성 검사를 위해 TextWatcher 인터페이스를 사용하는데 오류가 발생
* 해결법: afterTextChanged 함수를 추가하여 해결
* 알게된 점: TextWatcher 인터페이스는 3가지 함수(beforeTextChanged, onTextChanged, afterTextChanged)를 모두 재정의해야 한다는점

## 시연 영상
[유튜브 바로가기](https://www.youtube.com/watch?v=-o12O4055DI)
<img width="200" alt="시연" src="https://github.com/CosineAndroid/Contact/assets/100404990/5ef65218-ab3c-4248-99e2-685ff2e79e56">
