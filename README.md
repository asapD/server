# asapD Server

This project is carried out with an offer from Pablo Airlines. It consists of three parts: server, Android, and Arduino under the theme of building a user authentication-based door lock system.

Progress period : 2022.09.04 ~ 2022.09.28

Hosted by: kusitsm

## ๐ Getting Started

1. Install server application.

```` bash
  git clone https://github.com/asapD/server.git
````

2. Execute docker-compose to run db server.

```` bash
  docker-compose up
````
3. Run spring boot project in IntelliJ.


## ๐งพ What we use

- Server

<img src="https://img.shields.io/badge/Java-000000?style=for-the-badge&logo=Java&logoColor=white"> <br/>
<img src="https://img.shields.io/badge/Spring boot-6DB33F?style=for-the-badge&logo=Springboot&logoColor=white"> <br/>

- Secure

<img src="https://img.shields.io/badge/Json web tokens-000000?style=for-the-badge&logo=JSON Web Tokens&logoColor=white"> <br/>
<img src="https://img.shields.io/badge/Spring Security-6DB33F?style=for-the-badge&logo=SpringSecurity&logoColor=white"> <br/>

- Database

<img src="https://img.shields.io/badge/Redis-DC382D?style=for-the-badge&logo=Redis&logoColor=white"> <br/>
<img src="https://img.shields.io/badge/MySQL-4479A1?style=for-the-badge&logo=MySQL&logoColor=white"> <br/>

- Environment 

<img src="https://img.shields.io/badge/Dockercompose-2496ED?style=for-the-badge&logo=Docker&logoColor=white"> <br/>
<img src="https://img.shields.io/badge/Swagger-85EA2D?style=for-the-badge&logo=Swagger&logoColor=white"> <br/>
<img src="https://img.shields.io/badge/IntelliJ IDEA-000000?style=for-the-badge&logo=IntelliJ IDEA&logoColor=white"> <br/>
<img src="https://img.shields.io/badge/Postman-FF6C37?style=for-the-badge&logo=Postman&logoColor=white"> <br/>

- etc

<img src="https://img.shields.io/badge/Sms Service-CC6699?style=for-the-badge&logo=Sms service&logoColor=white"> <br/>

## ๐ Architecture of system

![image](https://user-images.githubusercontent.com/61505572/192551252-42686611-7934-4076-9bed-fb7c19d45b60.png)

![image](https://user-images.githubusercontent.com/61505572/192551385-8c4ae192-cacf-4b6d-805a-2d03581d7ec8.png)

## โฌ๏ธ Flow Scenario

![image](https://user-images.githubusercontent.com/61505572/193457172-c9f7ae59-1f2d-4e21-aaa1-7a8c5d23d610.png)


1. ํ์๊ฐ์ํ ์ฌ์ฉ์๊ฐ asapD ์ดํ์ ํตํด ์ํ์ ์ฃผ๋ฌธํ๋ค.
2. ์๋ฒ์์ ์ฃผ๋ฌธID์ ์ฌ์ฉ์ID, `prefix` ๊ฐ์ ํตํด serialNum์ ์์ฑํ๊ณ? ์ด๋ฅผ Redis์ ์?์ฅํ๋ค. ๊ทธ ํ ์ฌ์ฉ์์๊ฒ serialNum์ QR์ฝ๋๋ก ๋ณด์ฌ์ค๋ค.
3. ์ฌ์ฉ์๊ฐ ๋์ด๋ฝ ๊ธฐ๊ธฐ ( ์๋์ด๋ธ )์ ๋ธ๋ฃจํฌ์ค ์ฐ๊ฒฐ์ ํ๋ค.
4. ๋ธ๋ฃจํฌ์ค ์ฐ๊ฒฐ ์ฑ๊ณต ํ์ ์ฌ์ฉ์๊ฐ ๋์ด๋ฝ ๊ธฐ๊ธฐ์ QR ์ฝ๋ ๋ฆฌ๋๊ธฐ์ QR ์ฝ๋๋ฅผ ๋ณด์ฌ์ค๋ค.
5. ๋์ด๋ฝ ๊ธฐ๊ธฐ์์ QR ์ฝ๋ ๊ฐ์ ์ฝ์ ๋ค, serialNum์ ์ถ์ถํ์ฌ ์ฌ์ฉ์์๊ฒ ๋ธ๋ฃจํฌ์ค ํต์?์ผ๋ก ๊ฐ์ ์?๋ฌํ๋ค.
6. ์๋๋ก์ด๋์์ ์ด ๊ฐ์ ๋ฐ๋ก ์๋ฒ์ ๋ณด๋ด์ด, ๊ฒ์ฆ ๊ณผ์?์ ๊ฑฐ์น๋ค. 
7. ๊ฒ์ฆ ๊ฒฐ๊ณผ๋ก 200 status์ผ ๊ฒฝ์ฐ ์๋๋ก์ด๋๋ ๋์ด๋ฝ ๊ธฐ๊ธฐ์ ๋ธ๋ฃจํฌ์ค ํต์?์๋ก `open` ํค์๋๋ฅผ ์?์กํ๊ณ?, 400 status์ผ ๊ฒฝ์ฐ `close` ํค์๋๋ฅผ ์?์กํ๋ค.
8. ๋์ด๋ฝ ๊ธฐ๊ธฐ๋ ์?๋ฌ๋ฐ์ ํค์๋์ ๋ฐ๋ผ ์๋ณด ๋ชจํฐ๋ฅผ ์์ง์ฌ ๋ฌธ์ ์ด๊ฑฐ๋ ์ด์ง ์๋๋ค.

