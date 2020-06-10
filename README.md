# rest-api
Spring Bootを使用したRest APIのサンプルプロジェクトです。

## Getting Started

### Installing
IntelliJなどのIDEで実行可能ですが、下記の方法でjarをビルドして実行することも可能です。  
```
mvn clean package spring-boot:repackage
java -jar rest-api-1.0-SNAPSHOT.jar
```

## Running the tests
テスト方法は２種類あります。  
１つはブラウザからAPIへアクセスする方法です。こちらについては下記の方法で実施可能です。  
* [SpringBootStarterを実行します。](src/main/java/com/github/fukugit/restapi/SpringBootStarter.java)  

|  テスト対象API  |  API内容  |  メソッド  |  テスト内容  |  パラメータ  |  期待値  |
| ---- | ---- | ---- | ---- | ---- | ---- |
|  http://localhost:8080/books/  |  全件取得  |  GET  |  正常終了  |  -  |  JSON  |
|  http://localhost:8080/books/1  |  ID検索  |  GET  |  正常終了  |  -  |  JSON  |
|  http://localhost:8080/books/  |  登録  |  POST  |  正常終了  |  `{ "title":"test" }`  |  -  |
|  http://localhost:8080/books/  |  登録  |  POST  |  バリデーションエラー  |  `{ }`  |  -  |
|  http://localhost:8080/books/  |  更新  |  PUT  |  正常終了  |  `{  "id": 1, "title":"test" }`  |  -  |
|  http://localhost:8080/books/  |  更新  |  PUT  |  バリデーションエラー  |  `{ "title":"test" }`  |  -  |
|  http://localhost:8080/books/1  |  削除  |  DELETE  |  正常終了  |  -  |  -  |

| Status | Response  |
| ------ | --------- |
| 200    | `json`                          |
|        | `   {`                          |
|        | ` "id": 10,`                    |
|        | ` "username": "alanpartridge",` |
|        | ` more code...`                 |
|        | `}`                             |
| 400    |                                 |

もう一つはJUnitから実行する方法です。 MockMvcを使用しているのでブラウジングテストと同様のテスト（結合テスト）が可能です。  
* [BookControllerTestを実行します。](src/test/java/com/github/fukugit/restapi/endpoint/BookControllerTest.java)  

## Built With
* [Maven](https://maven.apache.org/) - Dependency Management

## Dependency 
DependencyについてはPOMを参照して下さい。  
* [pom.xml](pom.xml)  

## Acknowledgments
RestAPIの設定は [Zalando RESTful API と イベントスキーマのガイドライン](https://restful-api-guidelines-ja.netlify.app/)
を参考に設計しました。  
