# asapD Server

This project is carried out with an offer from Pablo Airlines. It consists of three parts: server, Android, and Arduino under the theme of building a user authentication-based door lock system.

Progress period : 2022.09.04 ~ 2022.09.28

Hosted by: kusitsm

## 😎 Getting Started

1. Install server application.

```` bash
  git clone https://github.com/asapD/server.git
````

2. Execute docker-compose to run db server.

```` bash
  docker-compose up
````
3. Run spring boot project in IntelliJ.


## 🧾 What we use

- Server

Java 11 <br/>
Spring boot 2.7.x

- Secure

Spring Security <br/>
JWT

- Database

MySQL <br/>
Redis

- Environment 

docker-compose <br/>
IntelliJ <br/>
Swagger

- etc

SMS Service

- Deply

AWS EC2

## 📐 Architecture of system

![image](https://user-images.githubusercontent.com/61505572/192551252-42686611-7934-4076-9bed-fb7c19d45b60.png)

![image](https://user-images.githubusercontent.com/61505572/192551385-8c4ae192-cacf-4b6d-805a-2d03581d7ec8.png)

## ⬇️ Flow Scenario

1. 회원가입한 사용자가 asapD 어플을 통해 상품을 주문한다.
2. 서버에서 주문ID와 사용자ID, `prefix` 값을 통해 serialNum을 생성하고 이를 Redis에 저장한다. 그 후 사용자에게 serialNum을 QR코드로 보여준다.
3. 사용자가 도어락 기기 ( 아두이노 )와 블루투스 연결을 한다.
4. 블루투스 연결 성공 후에 사용자가 도어락 기기의 QR 코드 리더기에 QR 코드를 보여준다.
5. 도어락 기기에서 QR 코드 값을 읽은 뒤, serialNum을 추출하여 사용자에게 블루투스 통신으로 값을 전달한다.
6. 안드로이드에서 이 값을 바로 서버에 보내어, 검증 과정을 거친다. 
7. 검증 결과로 200 status일 경우 안드로이드는 도어락 기기에 블루투스 통신은로 `open` 키워드를 전송하고, 400 status일 경우 `close` 키워드를 전송한다.
8. 도어락 기기는 전달받은 키워드에 따라 서보 모터를 움직여 문을 열거나 열지 않는다.

