# Learning Spring Boot 3.0

## Chapter 3
`ORM - Object Relational Mapping`

`JPA - Java Persistance Api`
JPA is not a tool or framework; rather, it defines a set of concepts that guide implementers.
These concepts can be declared as JPQL

`EntityManager`
JPA provides a construct to build up queries known as EntityManager.

JPQL - Java Persistence Query Language
Spring Data JPA parses methods from the repository method and talks to EntityManager on your behalf.
You can also declare your own flavor of JPQL api methods based on a @Query annotation.
EntityManager provides APIs to assemble queries from JPQL.

SQL - Structured Query Language
You can also declare custom api methods in your repository and be annotated as @Query which is marked as pure SQL.

`Persistant Context`
A persistent context represents the entities which hold data and are qualified to be persisted in some persistent storage like a database.
The data persists and outlives a session.

## Chapter 2

This chapter covers the basics of annotations used for dependency injection.
Here I learned the most basic type of injection is based on Beans. Other types of injections are based on Beans.. That's why in the first chapter I could track the bean added to the application context based on `context.getBean("nameOfBean")`


I like the fact this chapter shows how to to see videos and add others based on html and also through json.
What I don't like is going beyond with React. As for me my goal is to move business logic from the front-end to the back-end. I will concentrate in applying restful network calls and requests.

Listing all videos
```
curl localhost:8080/api/videos 
```

response:
```
[{"name":"Need HELP with your SPRING BOOT 3 App?"},{"name":"Don't do THIS to your own CODE!"}]
```

Adding a new video
```
curl -v -X POST localhost:8080/api/videos -d '{"name": "Learning Spring Boot 3"}' -H 'Content-type:application/json'
```

response
```
{"name":"Learning Spring Boot 3"}
```

Html client

<img width="694" alt="image" src="https://github.com/juanmendez/spring-boot-essentials/assets/3371622/e58a10d4-e1d6-437a-8cbf-9e190bf5c858">



# Getting Started

## For data storage
* [https://spring.io/projects/spring-data-jpa](Official documentation)
* [What is JPA, highlighy recommended](https://www.youtube.com/watch?v=otinfgwkMbY&list=LL&index=1&pp=gAQBiAQB)
* [Introduction to JPA](https://www.infoworld.com/article/3379043/what-is-jpa-introduction-to-the-java-persistence-api.html)


## Book
* [Learning Spring Boot 3.0 by Greg L. Turnquist](https://learning.oreilly.com/library/view/learning-spring-boot/)
* [Book samples](https://github.com/PacktPublishing/Learning-Spring-Boot-3.0-Third-Edition)

### Reference Documentation
For further reference, please consider the following sections:

* [Official Gradle documentation](https://docs.gradle.org)
* [Spring Boot Gradle Plugin Reference Guide](https://docs.spring.io/spring-boot/docs/3.2.2/gradle-plugin/reference/html/)
* [Create an OCI image](https://docs.spring.io/spring-boot/docs/3.2.2/gradle-plugin/reference/html/#build-image)
* [Spring Web](https://docs.spring.io/spring-boot/docs/3.2.2/reference/htmlsingle/index.html#web)
* [Spring Initializer](https://start.spring.io/)

### Guides
The following guides illustrate how to use some features concretely:

* [Building a RESTful Web Service](https://spring.io/guides/gs/rest-service/)
* [Serving Web Content with Spring MVC](https://spring.io/guides/gs/serving-web-content/)
* [Building REST services with Spring](https://spring.io/guides/tutorials/rest/)

### Additional Links
These additional references should also help you:

* [Gradle Build Scans – insights for your project's build](https://scans.gradle.com#gradle)

