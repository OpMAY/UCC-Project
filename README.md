#Project Setting
##First Gitignore Checking
1. .gitignore의 파일에 각자 알맞게 세팅 되어있는지 확인한다.
2. 기타 불필요한 세팅 파일(.idea,.iml etc...)이 있는지 확인하고 .gitignore에 적어준다.
##Second Application.properties
application.properties 파일을 만들어 아래 경로에 삽입 시켜주고 Contents에 대한 부분을 적어준다.
###Path
src/main/resources/application.properties
###Contents
uploadPath=C:/Users/zlzld/OneDrive/Desktop/projects/server_test/Restfull-API-Server/target/Restfull-API-Server-0.0.1-SNAPSHOT/WEB-INF/api
deployPath=/www/phonething_gabia_io/www/api
cdnPath=https://okiwi-ldy-vod.s3.ap-northeast-2.amazonaws.com/api/
spring.datasource.hikari.jdbc-url=jdbc:mysql://211.47.74.38/dbphonething?serverTimezone=UTC&allowMultiQueries=true
spring.datasource.hikari.username=phonething
spring.datasource.hikari.password=12345qwert
spring.datasource.hikari.driver-class-name=com.mysql.cj.jdbc.Driver
spring.datasource.hikari.pool-name=apipool
api.access_key=AKIAJLBYKVWCC3IPIINQ;

server.servlet.encoding.charset=utf-8
spring.mvc.view.prefix=/WEB-INF/views/
spring.mvc.view.suffix=.jsp
server.port=8080

spring.servlet.multipart.max-file-size=200MB
spring.servlet.multipart.max-request-size=200MB

spring.datasource.local.jdbc-url=jdbc:mysql://127.0.0.1:8080/flowtest?serverTimezone=UTC&allowMultiQueries=true
spring.datasource.local.username=root
spring.datasource.local.password=root
spring.datasource.local.driver-class-name=com.mysql.cj.jdbc.Driver
spring.datasource.local.pool-name=apipool

##Description
uploadPath = 로컬의 서버 이미지가 업로드되는 패스
deployPath = AWS CDN 서버 업로드 패스
api.access_key = AWS Access Key
spring.datasource.hikari.* = 서버의 데이터베이스 설정
spring.datasource.local.* = 로컬의 데이터베이스 설정"# vodappserver" 
