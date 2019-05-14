# Spring Boot - RESTful Web Service

[![Build Status](https://travis-ci.org/iphayao/spring-boot-restful-service.svg?branch=master)](https://travis-ci.org/iphayao/spring-boot-restful-service)

This is code snipped of [medium](https://medium.com/@phayao/%E0%B8%A1%E0%B8%B2%E0%B8%AA%E0%B8%A3%E0%B9%89%E0%B8%B2%E0%B8%87-restful-web-service-%E0%B8%94%E0%B9%89%E0%B8%A7%E0%B8%A2-spring-boot-%E0%B8%81%E0%B8%B1%E0%B8%99%E0%B8%94%E0%B8%B5%E0%B8%81%E0%B8%A7%E0%B9%88%E0%B8%B2-8518284e5922) post.

## Build Application
Clone this repo and to build and test application run following command.
```
mvn clean package
```

Run application with Java to start web service
```
java -jar .\target\rest-service-0.1.0.jar
```

## Test Application 
Test the Applicatin with these HTTP request.

Request:
```
http://localhost:8080/greeting
```

Respose:
```javascript
{
    "id": 1,
    "content": "Hello, World!"
}
```

Request:
```
http://localhost:8080/greeting?name=User
```

Respose:
```javascript
{
    "id": 2,
    "content": "Hello, User!"
}
```

