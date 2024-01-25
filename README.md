# Learning Spring Boot 3.0

This chapter is about learning configurations.
Here we stared by undestanding how Spring Boot sets configuration based on application settings.

In this scenario we started reading our configurations set ins `resources/application.properties` and reading them in `MyController`

We started by running `LearningSpringBootApplication`, and went to `http://localhost:1974`.
In this way we saw the attributes being printed out.

This chapter also covers other application environments. So to test this out we set to run in `dev` environment, and apply a profile setting when running the application. We also used another application setting `resources/application-dev.properties`. In this setting the port is in 1800, so here are a few screenshots of how this worked.


<img width="1031" alt="Screenshot 2024-01-23 at 10 50 59 PM" src="https://github.com/juanmendez/spring-boot-essentials/assets/3371622/c4dff0a5-5c2c-4638-8821-9b6ea34c886a">


<img width="923" alt="image" src="https://github.com/juanmendez/spring-boot-essentials/assets/3371622/70b1bef5-89b2-4d35-b62f-b7431e14e932">


This chapter also talks about a few other configurations which can benefit dependency injection. Dependency injection in Spring Boot is implicit. Based on annotations, dependencies are wired and then available to others.

```
@SpringBootApplication
class LearningSpringBootApplication {

    @Bean
    @ConditionalOnProperty(prefix = "my.app", name = ["video"], havingValue = "youtube")
    fun youtubeService(): VideoService = YoutubeService()

    @Bean
    @ConditionalOnProperty(prefix = "my.app", name = ["video"], havingValue = "vimeo")
    fun vimeoService(): VideoService = VimeoService()
}
```

Here one of these methods is called and therefore the object returned is added to the dependency tree. For example `YoutubeService` is added to application context `youtubeService`.
This bean was injected instead of `VimeoService` because the configuration applies `youtube`.

`resources/application-dev.properties` has `my.app.video=youtube`





# Getting Started

### Reference Documentation
For further reference, please consider the following sections:

* [Official Gradle documentation](https://docs.gradle.org)
* [Spring Boot Gradle Plugin Reference Guide](https://docs.spring.io/spring-boot/docs/3.2.2/gradle-plugin/reference/html/)
* [Create an OCI image](https://docs.spring.io/spring-boot/docs/3.2.2/gradle-plugin/reference/html/#build-image)
* [Spring Web](https://docs.spring.io/spring-boot/docs/3.2.2/reference/htmlsingle/index.html#web)

### Guides
The following guides illustrate how to use some features concretely:

* [Building a RESTful Web Service](https://spring.io/guides/gs/rest-service/)
* [Serving Web Content with Spring MVC](https://spring.io/guides/gs/serving-web-content/)
* [Building REST services with Spring](https://spring.io/guides/tutorials/rest/)

### Additional Links
These additional references should also help you:

* [Gradle Build Scans – insights for your project's build](https://scans.gradle.com#gradle)

