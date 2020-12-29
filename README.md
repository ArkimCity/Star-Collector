# 오늘의 단어(World of Words) 🗃️
국립국어원의 [표준국어대사전](https://stdict.korean.go.kr/main/main.do)에 등재된 모든 단어를 임의로 생성해 주는 사이트입니다.
___
## 🎶 Motivation of selecting topic
아이디어가 너무 나오지 않아서 키워드를 얻고자 '랜덤 단어 생성 사이트'를 찾아보던 중, [영어로 된 사이트](https://randomwordgenerator.com/)는 상당히 완성도가 높았으나, 한국어도 된 사이트에서는 그다지 완성도가 높지 않은 듯하여 직접 만들어보게 되었습니다.

여기에 단순히 랜덤한 단어를 보여주는 것에서 그치지 않고 로그인을 통한 저장 및 커뮤니티 구현 등 다양한 기능을 추가함으로써 <u>단순히 아이디어를 얻기 위한 용도로 사용할 수 있으며, 나아가 오늘의 "나"를 보다 재미있게 표현할 수도 있는 하나의 수단이 될 수도 있을 것으로 기대됩니다.</u>

## 🛠️ Stack
* Java
* Python
* JavaScript
* CSS
* OracleDB

## 📊 Structure
<details>
<summary>🗂️ ER Diagram</summary>
<div markdown="1">

![WorldofWords](https://user-images.githubusercontent.com/17983434/103230869-d3e80200-4979-11eb-9e97-5f9d7725a2bc.png)

</div>
</details>

<details>
<summary>📂 Folder Structure</summary>
<div markdown="1">

```
* WorldOfWords(임시 작성)

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
|      └── WorldOfWordsCRUDService.java
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

## 🔰 How to USE?
발표 전까지 완성된 결과물을 바탕으로 작성하도록 하겠습니다.


## 📝 Conclusion
이번 프로젝트를 통해 OracleDB부터 WEB상에 화면까지 통합적으로 개발하는 프로젝트를 수행하였습니다. 

특히 JSP를 사용하여 MVC 패턴을 적용했기 때문에 사용자에게 보여지는 화면인 View, 실제 비즈니스로 로직이 들어가는 Model, 

그리고 View와 Model을 연결시켜주는 Controller에서 View와 Model 부분의 분업으로 훨씬 더 효율적인 개발이 가능했습니다.

이는 공통되는 로직의 재사용(비슷한 내용의 프로젝트 등)이 가능하여 생산성이 높아지기 때문입니다.

추가로 크롤링 사용을 통해 외부 API를 크롤링하는 방법을 터득할 수 있었습니다.
차후에 배울 Spring에서 'Jsoup 라이브러리'를 활용한 웹 크롤링에 도움이 될 듯 합니다.

이상으로 발표 마치겠습니다.

감사합니다. :)
