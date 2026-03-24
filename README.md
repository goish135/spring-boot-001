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
spring-boot-001
├── samples/                     # Sample YAML config
│   └── config.yaml
│
├── src/main/java/com/py
│   ├── controller/              # REST API Layer
│   │   └── GitHubController.java
│   │
│   ├── service/                 # Business Logic
│   │   └── GitHubService.java
│   │
│   ├── model/                   # DTO / Data Model
│   │   └── GitCommitDto.java
│   │
│   └── GitHubAutomationApplication.java
│
├── README.md
├── .gitignore
└── pom.xml
```
---

## Commands

- mvn package
- mvn dependency:tree
- mvn spring-boot:run
- java -jar target/git.automation-0.0.1-SNAPSHOT.jar

---

### Steps to reproduce
Note: 請自行更換OOXX
0. export `$GITHUB_TOKEN=${有write permission的token}`
1. curl for get sha

  ```
  curl -L \
    -H "Accept: application/vnd.github+json" \
    -H "Authorization: Bearer OOXX" \
    https://api.github.com/repos/goish135/spring-boot-001/contents/samples/config.yaml
  ```

2. curl for update git content

  ```
curl -X PUT "http://localhost:8080/api/github/goish135/spring-boot-001/commit" \
-H "Content-Type: application/json" \
-d '{
  "filePath": "samples/config.yaml",
  "content": "id: getting-started-ip-updated\nuri: /ip\nupstream:\n  type: roundrobin\n  nodes:\n    httpbin.org:80: 1",
  "commitMessage": "Update README.md via API",
  "branch": "main",
  "sha": OOXX
}'
  ```

## Reference

- [從無到有上手你的第一個 Spring Boot 應用程式](https://blog.miniasp.com/post/2022/09/19/Spring-Boot-Quick-Start-From-Scratch)
