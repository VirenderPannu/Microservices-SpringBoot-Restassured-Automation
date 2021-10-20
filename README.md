# microservicesSpringBootAndRestassuredAutomation
1. Locally creating micro services by using SpringBoot, MVC framework and lombok library
2. Just to verify the RestController comoponent by using Postman, we need to start the Apache Tomcat server at port 9003 i.e. http://localhost:9003/, so first of all run HrmsApplication class decorated with @SpringBootApplication annotation. In case of test automation of APIs, no need to run above class. 
3. Test Automation of created SpringBoot microservices by using "SpringBootTest Spring TestContext Framework"
    a. Before execute any automated script, need to start applicatioon Context as well as Tomcat server so need to apply the following option at the top of respective class - (webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT) 
4. Test Automation of created SpringBoot microservices by using "RestAssured library as BDD"
    a. Before execute any automated script, need to start applicatioon Context as well as Tomcat server so need to apply the following option at the top of respective class - (webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
5. Test Automation of created SpringBoot Controllers (RestController) by using "Spring boot MockMvc Mocking library" (Because we do Mocking till the real microservices implemented).
