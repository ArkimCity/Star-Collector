# 오늘의 단어(World of Words) 🗃️
국립국어원의 [표준국어대사전](https://stdict.korean.go.kr/main/main.do)에 등재된 모든 단어를 임의로 생성해주는 사이트입니다.

## 📖Content
---
* [1. 주제 선정(Motivation of selecting topic)](#🎶Motivation-of-selecting-topic)
* [2. 사용된 기술 스택(Stack)](#🛠️Stack)
* [3. 기능 구현(Implementation)](#🧩Implementation)
* [4. 데이터 구조(Structure)](#📊Structure)
* [5. 사용법(How to USE?)](#🔰How-to-use?)
* [6. 마무리(Conclusion)](#📝Conclusion)

## 🎶Motivation of selecting topic
---
아이디어가 너무 나오지 않아서 키워드를 얻고자 '랜덤 단어 생성 사이트'를 찾아보던 중, [영어로 된 사이트](https://randomwordgenerator.com/)는 상당히 완성도가 높았으나, 한국어도 된 사이트에서는 그다지 완성도가 높지 않은 듯하여 직접 만들어보게 되었습니다.

여기에 단순히 랜덤한 단어를 보여주는 것에서 그치지 않고 로그인을 통한 저장 및 연관되는 단어(연관 검색어)와 이미지를 통한 브레인스토밍을 하는 과정 등, 다양한 기능을 추가함으로써 <u>브레인스토밍을 통해 아이디어를 얻기 위한 용도로 사용할 수 있는 하나의 수단이 될 수 있을 것으로 기대됩니다</u>.

## 🛠️Stack
---
* Java
* JavaScript
* CSS
* OracleDB

## 🧩Implementation
---
* 사용자는 랜덤으로 추출하고 싶은 단어의 갯수(기본값 : 10, 최댓값 : 500000)를 지정한다.

* 사용자는 로그인 후에 모든 기능을 이용할 수 있다.

* 사용자는 랜덤으로 출력된 단어를 저장할 수 있으며, 필요에 따라서는 수동으로 단어를 저장할 수도 있다.

* 사용자는 단어를 여러 개 저장한 후, 연관 검색어와 이미지를 통해 시각적으로 확인할 수 있으며, 브레인스토밍을 위한 준비를 도와준다.

## 📊Structure
---
<details>
<summary>🗂️ ER Diagram</summary>
<div markdown="1">
<br>

대략적인 테이블 구조는 다음과 같습니다.

* 사용자(유저)가 단어(word)를 추가할 때 마다 'userword' 테이블에 단어를 넣고 참조하는 단어에 'userword' 테이블에 넣는 구조로 설계.

* 사용자는 여러 개의 단어를 참조하고 있는데 이것을 관계로 표현하면 "다대일(ManytoOne)"로 풀 수 있음.

![WorldofWords](https://user-images.githubusercontent.com/17983434/103254522-fc4b1d00-49c8-11eb-9fc0-42f6bd3de6f4.png)

</div>
</details>
<br>

<details>
<summary>📂 Folder Structure</summary>
<div markdown="1">

```
* 오늘의 단어(WorldOfWords)
|
├── www.controller
|      └── WorldOfWordsController.java
├── www.exception
|      ├── MessageException.java
|      └── NotExistException.java
├── www.model
|      ├── Crawler.java
|      ├── LoginService.java
|      ├── UserCommunityDAO.java
|      ├── UserWordDAO.java
|      └── WorldOfWordsCRUDServic e.java
├── www.model.dto
|      ├── CommunityEntity.java
|      ├── UserEntity.java
|      └── UserWordEntity.java
├── www.model.util
|      ├── DBUtil.java
|      └── PublicCommon.java
├── sql
|    ├── worldOfWordsDDL.sql
|    └── worldOfWordsDML.sql
├── views
|    ├── about.jsp
|    ├── brainStorm.jsp
|    ├── brainStormResult.jsp
|    ├── CommunityList.jsp
|    ├── login-page.css
|    ├── login-page.jsp
|    ├── NavigationBar.jsp
|    ├── showError.jsp
|    ├── UserDetail.jsp
|    ├── UserInsert.html
|    ├── UserUpdate.jsp
|    └── wordlist.jsp
└── index.html
```

</div>
</details>
<br>

## 🔰How to USE?
---
* 랜덤으로 추출하고 싶은 단어의 갯수(기본값 : 10, 최댓값 : 500000)를 지정한다.
  * 예를 들어, 20개(갯수가 많아질 수록 불러오는 속도 저하됨)의 단어를 출력하고 싶다면 다음과 같이 입력한다.

  <img src="https://user-images.githubusercontent.com/17983434/103252914-2816d480-49c2-11eb-945d-63e111fe504a.PNG" width="50%" height="50%" title="1" alt="1" />

* 로그인 후에 모든 기능을 이용할 수 있다.
  * 로그인을 하지 않으면 모든 기능을 이용할 수 없음.
  * 다음은 **로그인을 하지 않은 상태에서 저장을 시도**한 상황이다.

  <img src="https://user-images.githubusercontent.com/17983434/103252850-e6862980-49c1-11eb-97c9-83008e583f28.PNG" width="50%" height="50%" title="2" alt="2" />

* 랜덤으로 출력된 단어를 저장할 수 있으며, **필요에 따라서는 수동으로 단어를 저장할 수도 있음.**
<img src="https://user-images.githubusercontent.com/17983434/103252519-8fcc2000-49c0-11eb-8812-8875ef722830.gif" width="50%" height="50%" title="3" alt="3" />

* 단어를 여러 개 저장한 후, 연관 검색어와 이미지를 통해 시각적으로 확인할 수 있으며, 브레인스토밍을 위한 준비를 도와준다.

## 📝Conclusion
---
이번 프로젝트를 통해 OracleDB부터 WEB상에 화면까지 통합적으로 개발하는 프로젝트를 수행하였습니다. 

특히 JSP를 사용하여 MVC 패턴을 적용했기 때문에 사용자에게 보여지는 화면인 View, 실제 비즈니스로 로직이 들어가는 Model,

그리고 View와 Model을 연결시켜주는 Controller에서 View와 Model 부분의 분업으로 훨씬 더 효율적인 개발이 가능했습니다.

이는 공통되는 로직의 재사용(비슷한 내용의 프로젝트 등)이 가능하여 생산성이 높아지기 때문입니다.

추가로 크롤링 사용을 통해 외부 API를 크롤링하는 방법을 터득할 수 있었습니다.
차후에 배울 Spring에서 'Jsoup 라이브러리'를 활용한 웹 크롤링에 도움이 될 것으로 기대되는 프로젝트였습니다.

---
여담입니다만, 아래의 영상은 개발을 하면서 공감되는 예시를 잘 나타내고 있어서 가져와봤습니다.

오늘도 화이팅입니다. :)

<img src="https://user-images.githubusercontent.com/17983434/103256334-3ec42800-49d0-11eb-93cd-0181e2c9de19.gif" width="20%" height="20%" title="BUG!!!" alt="BUG!!!" />

<a name="footnote">내가 버그를 잡는건지, 버그가 나를 잡는건지...</a>