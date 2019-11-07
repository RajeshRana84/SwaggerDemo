We will use the below technologies for this demo.
---------------------------------------------------
-IDE (Eclipse , IDEA etc)

-Maven as build tool
-Spring Boot as application framework
-Spring Rest as REST API framework
-Swagger2 as REST documentation framework
-Java 1.8


Exposed Rest End-points
------------------------
http://localhost:8080/swagger2-demo/getStudents
http://localhost:8080/swagger2-demo/getStudent/sajal
http://localhost:8080/swagger2-demo/getStudentByCountry/india
http://localhost:8080/swagger2-demo/getStudentByClass/v


Swagger2 JSON Format Docs
---------------------------
http://localhost:8080/swagger2-demo/v2/api-docs


Swagger UI Interface
-------------------------
http://localhost:8080/swagger2-demo/swagger-ui.html


Enable Swagger 2 in Application
---------------------------------
@EnableSwagger2 
- Swagger 2 is enabled through the @EnableSwagger2 annotation.


Configuring Swagger 2 in the Application
------------------------------------------
For our application, we will create a Docket bean in a Spring Boot configuration to configure Swagger 2 for the application. A Springfox Docket instance provides the primary API configuration with sensible defaults and convenience methods for configuration. 


Swagger2 Annotations
------------------------
The default generated API docs are good but they lack detailed API level information. Swagger has provided few annotations to add this detailed information to the APIs. e.g.

@Api 
– We can add this Annotation to the controller to add basic information regarding the controller.

@ApiOperation and @ApiResponses 
– We can add these annotations to any rest method in the controller to add basic information related to that method

@ApiModelProperty 
– This annotation is used in the Model property to add some description to the Swagger output for that model attribute



Swagger2 Maven Dependencies
--------------------------------
To use swagger following dependencies are required

<dependency>
    <groupId>io.springfox</groupId>
    <artifactId>springfox-swagger2</artifactId>
    <version>2.6.1</version>
</dependency>
 
<dependency>
    <groupId>io.springfox</groupId>
    <artifactId>springfox-swagger-ui</artifactId>
    <version>2.6.1</version>
</dependency>


Filtering API for Swagger’s Response
-----------------------------------------
It is not always desirable to expose the documentation for your entire API. You can restrict Swagger’s response by passing parameters to the apis() and paths() methods of the Docket class.

As seen above, RequestHandlerSelectors allows using the any or none predicates, but can also be used to filter the API according to the base package, class annotation, and method annotations.

PathSelectors provides additional filtering with predicates which scan the request paths of your application. You can use any(), none(), regex(), or ant().

In the example below, we will instruct Swagger to include only controllers from a particular package, with specific paths, using the ant() predicate.

@Bean
public Docket api() {                
    return new Docket(DocumentationType.SWAGGER_2)          
      .select()                                       
      .apis(RequestHandlerSelectors.basePackage("com.example.controller"))
      .paths(PathSelectors.ant("/foos/*"))                     
      .build();
}


Custom Information
---------------------
Swagger also provides some default values in its response which you can customize, such as “Api Documentation”, “Created by Contact Email”, “Apache 2.0”.

To change these values, you can use the apiInfo(ApiInfo apiInfo) method. The ApiInfo class that contains custom information about the API.


A Quick Overview of Swagger-Core Annotations
----------------------------------------------
@Api	             -Marks a class as a Swagger resource.
@ApiModel	         -Provides additional information about Swagger models.
@ApiModelProperty	 -Adds and manipulates data of a model property.
@ApiOperation	     -Describes an operation or typically an HTTP method against a specific path.
@ApiParam	         -Adds additional meta-data for operation parameters.
@ApiResponse	     -Describes a possible response of an operation.
@ApiResponses	     -A wrapper to allow a list of multiple ApiResponse objects.



Converting Swagger JSON document to Java Rest Client
------------------------------------------------------

https://github.com/swagger-api/swagger-codegen

http://central.maven.org/maven2/io/swagger/swagger-codegen-cli/2.4.9/swagger-codegen-cli-2.4.9.jar
	
java -jar swagger-codegen-cli.jar generate \
  -i http://localhost:8080/swagger2-demo/v2/api-docs.json \
  -l java \
  -o samples/client/store/java
