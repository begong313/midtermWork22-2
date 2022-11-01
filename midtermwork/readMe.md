실행환경 : android 12.0 google play / arm64

-javaFile-
###MainActivity.java
* onCreate()    
    - signInFragment 생성
    - ItemShowFragment 생성
    - navigationbar 동작 구현
        + signInFragment,ItemShowFragment간의 화면전환
    - preference에 저장되어있는 로그인 정보 유무 확인 후 첫화면 지정 및 개인정보 반영
        - 있다면 : 첫화면 itemlist화면, 회원정보 클릭시 개인정보 화면
        - 없다면 : 첫화면 login화면, 회원정보 클릭 시 login화면으로 이동
* onFragmentChanged(int index)
    프래그먼트 전환 매서드

Item.java
    -ItemShowFragment에 보여줄 것들 형식 지정

ItemAdapter.java
    -recycler view 를 이용하기위한 item adapter
    -요소들을 받아와 화면에 배치

ItemShowFragment.java
    -item list 화면 구현
    onCreateView()
        -recyclerView Control
        -recyclerview를 가져온뒤 adapter를 통해 형식 할당
        -test data를 통해서 화면이 잘 나오나 확인. 

LoginFragment.java
    -login 화면 구현
    -login 버튼 클릭 시
        -textView에 있는 입력되어있는 id, pw를 가져옴
        -database에 저장되어있는는 id,pw정보와 일치하는지 확인
        -일치 시 database에서 회원정보를 가져와 preference에 저장 후 화면전환 => 추후 앱 재실행 시 preference에 정보가있으면 자동로그인
        -로그인 시 mainActivity 재실행을 통한 회원정보 반영
        -불일치 시 'login 실패' toast 띄움
    -signIn 버튼 클릭시
        -signInfragment로 화면전환

SignInFragment.java
    -회원가입 화면 구현
    -database이용
    회원가입버튼 눌렀을때
        -각각의 textView와 radioButton의 값 가져옴
        -id중복검사, pw 조건 검사, pw 일치확인
        -data에 문제없으면 database에 탑제 후 loginFragment로 화면전환
        -data 조건이 문제있으면 toast로 문제 알려줌.

UserInformation.java
    -preference에 저장된 user정보를 가져와 화면에 띄음
    logOutButton
        -preference에 있는 user정보를 지움
        -mainActivity 재실행 =>회원정보가 없기때문에 login화면으로 시작됨.

DatabaseHelper.java
    -database만들기위한 tool
    -테이블 생성 및 id, pw, name, address, phoneNum 저장하기위한 구조 구현. 

UserProvider.java
    -database이용을 위한 메서드 구헌
    -query() : 검색, insert(): 등록, delete():삭제, update():수정



-xml file-
activity_main.xml
    -bottom_navigation : 하단 네비게이션
    -container : fragment를 보여줄 창

fragment_itemshow.xml
    -itemRecycler : 품목을 보여줄 창

fragment_login.xml
    -importId : 아이디 입력창
    -importPassword : 비번 입력창
    -signInButton : 회원가입버튼
    -loginButton : 로그인 버튼

fragment_sign_in.xml
    -importRegitId : 아이디 입력창
    -importRegitPW : 비밀번호 입력창
    -checkImportRegitPW :비밀번호 확인 입력창
    -importName : 이름 입력창
    -importLocate : 주소입력창
    -importPhone : 번호입력창
    -agreePaper 개인정보 동의약관
    -usingAgreeButton 개인정보동의 버튼
    -registUserButton : 제출 버튼

fragment_user_information.xml
    -textView 5,6,7,8 : 'name' , 'userid', 'address' , 'phone'
    -username : 저장된 name data 불러오기
    -userId : 저장된 id data 불러오기
    -useraddress : 저장된 address data 불러오기
    -userphonenum : 저장된 phoneNum data 불러오기
    -logout : 로그아웃 버튼

itemview.xml
    -아이템을 보여줄 형식
    -item_board_title : item 제목
    -item_board_content :item 설명
    -menuImage : 사진

menu_bottom.xml
    -home : ItemlistFragment로 이동
    -setting : 상황에 따라 userInfofragment, loginfragment로 이동
    - <item/> : 요소가 2개면 사이드에 빈공간이 생겨 보기싫어서 채우기 위해 임시로 만들어놓은 요소들



