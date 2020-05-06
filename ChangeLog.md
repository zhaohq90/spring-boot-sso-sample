### changeLog
- 启用EnableResourceServer注解，将client同时作为资源服务器，允许前端通过token鉴权，进而实现前后端分离

### 测试步骤
- 浏览器中输入http://localhost:8080/sso/oauth/authorize?client_id=client-user&redirect_uri=http://localhost:8081/view/index&response_type=code&state=DO8jM1d（其中/view/**设置为允许匿名访问，用来接收所有前端请求），
页面会重定向到sso登录页面，登录并授权后，会重定向到最初访问的路径并携带上code，web端将code参数提交给后端服务，后端调用sso server提供的接口换取凭证并返回给web端，然后web端就可以使用token访问受保护的资源。

```aidl  
# 换取凭证
# 注意： redirect_uri一定要与最初在浏览器输入的uri一致
curl --location --request POST 'http://localhost:8080/sso/oauth/token' \
--header 'Authorization: Basic Y2xpZW50LXVzZXI6MTIzNDU2' \
--header 'Content-Type: application/x-www-form-urlencoded' \
--header 'Cookie: JSESSIONID=5D1DD9435D83E4DD784B8C2D87C36B0B; OAUTH2CLIENTSESSION=D648B85B2DA235857CFC9487D4A88B77' \
--data-urlencode 'grant_type=authorization_code' \
--data-urlencode 'code=EBmCYR' \
--data-urlencode 'client_id=client-user' \
--data-urlencode 'redirect_uri=http://localhost:8081/view/index'

# 使用token访问受限资源
curl --location --request GET 'http://localhost:8081/login2' \
--header 'Authorization: bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJleHAiOjE1ODg4NTE3MDUsInVzZXJfbmFtZSI6IndheW5lIiwiYXV0aG9yaXRpZXMiOlsiUk9MRV9NRURJVU0iLCJST0xFX05PUk1BTCJdLCJqdGkiOiI1YjRhOTc0YS03ODAwLTQzMWQtODcxNS1mM2JlZTZlMDRhNDAiLCJjbGllbnRfaWQiOiJjbGllbnQtdXNlciIsInNjb3BlIjpbImFsbCJdfQ.05yjnXbCr8GM7sqvfEDwNgepv46T7voyZIi8gHwedtU' \
--header 'Cookie: OAUTH2CLIENTSESSION=BEE9BBB0339E2741A553E775C113528F'
```


### tips

### todo
- 如何实现前后端部署分离？