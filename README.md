# Spring Boot Project

A simple Spring Boot service.

## 📌 Description
This project is a backend service built with Spring Boot.

Main purpose:
- Provide REST API
- Demonstrate simple service architecture
- Learning / practice project

---

## 🛠 Tech Stack

- Java
- Spring Boot
- Maven
- REST API

---

## 📂 Project Structure
```
SPRING-BOOT-001/
├── src/
│   └── main/
│       └── java/
│           └── com/
│               └── py/
│                   └── GitAutomation.java
├── .gitignore
├── pom.xml
└── README.md
```
---

## Commands

- mvn package
- mvn dependency:tree
- mvn spring-boot:run
- java -jar target/git.automation-0.0.1-SNAPSHOT.jar

---

### Steps to reproduce
0. export `$GITHUB_TOKEN=${有write permission的token}`
1. curl for get sha

  ```
  curl -L \
    -H "Accept: application/vnd.github+json" \
    -H "Authorization: Bearer ${token}" \
    https://api.github.com/repos/goish135/spring-boot-001/contents/samples/config.yaml
  ```

2. curl for update git content

  ```
  curl -X PUT "http://localhost:8080/api/github/goish135/spring-boot-001/commit" \          
  -H "Content-Type: application/json" \-d '{                          
    "filePath": "samples/config.yaml",                                                
    "content": "Hello GitHub from curl2!",
    "commitMessage": "Update README.md via API",
    "branch": "main",
    "sha":${sha}
  }'
  ```

## Reference

- [從無到有上手你的第一個 Spring Boot 應用程式](https://blog.miniasp.com/post/2022/09/19/Spring-Boot-Quick-Start-From-Scratch)
