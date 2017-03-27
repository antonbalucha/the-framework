# The-Framework

Structure, set of Java libraries and basic functionality which help me to build simple web applications.

## General idea about The Framework

For understanding of the idea of The Framework and why to use it, we have to go to the history. 
On my early beginning as a programmer I learned many stuff and (as many) I thought that with the knowledge I gained I can build some awesome framework that I will use on many projects and maybe many other will use it. Then I started to go to regular work and I saw that other developers are trying to build their frameworks which will be used on the project later. After some time, I realized that many times they were focused on framework itself instead of the project. Learning of a framework which many times have no documentation/was not open source/only a few people knew what certain library was doing was also not pleasant. Sometimes deadlines were postponed because of problems with framework. It was not right. After all, the idea of a framework is to help to the developer to build projects quickly and easily. 

Besides of a frameworks used on a projects I also learned some third party libraries. I started to use it more also on my projects. I realized that the wheel I am trying to invent in the form of my custom awesome framework was useless, because on the Internet we have many libraries/projects with large community and support. Subsequently, I deleted every my code which can be found in some third party open source library; created some directory and project structure and basic structure of The Framework was built. Later I made some haircut of The Framework and pushed it here on the GitHub. 

So, what is The Framework now? Simple said, it is a project/directory structure linked together with third party libraries with some basic functionality - authorization, authentication, changing of language, support for translations and connection to the database. When you want to make some Java web application with mentioned functionalities, you may download The Framework and start with programming. Do not have to speculate how directory structure should looks like, which libraries you should use, how to build it. Everything is done and ready to use.

## Technologies and libraries used in The Framework

* frontend
    * [HTML5](https://www.w3schools.com/html/)
    * [CSS](https://www.w3schools.com/css/)
    * [JavaScript](https://www.w3schools.com/js/)
    * [JQuery](https://jquery.com/)

* backend
    * web API - [Jersey](https://jersey.java.net/)
    * database layer - [Hibernate](http://hibernate.org/)
    * database - [PostgreSQL](https://www.postgresql.org/), [MySQL](https://www.mysql.com/)
    * logging - [Logback](https://logback.qos.ch/)
    * configuration - [Apache Commons Configuration](https://commons.apache.org/proper/commons-configuration/)
    * security - [Apache Shiro](https://shiro.apache.org/)
    * json processing - [Jackson](https://github.com/FasterXML/jackson)
    * object mapper - [Dozer](http://dozer.sourceforge.net/)
    * utilities - [Apache Commons Lang](https://commons.apache.org/proper/commons-lang/)
    * testing - [JUnit](http://junit.org)
    * build - [Apache Maven](https://maven.apache.org/)
    * server - [Apache Tomcat](https://tomcat.apache.org/)

* other
    * web API calls - [Postman](https://www.getpostman.com/)

# Procedure how to run The Framework

In following paragraphs you will find step by step procedure how to download, set up and run The Framework.

## Prerequisities

You have to install and set up:
* [Postman](https://www.getpostman.com/) - for web API calls and testing
* one of supported database:
    * [PostgreSQL](https://www.postgresql.org/)
    * [MySQL](https://www.mysql.com/)

## Getting The Framework

Run ```git clone git@github.com:antonbalucha/the-framework.git``` command from your console, which supports GIT (e.g. https://git-scm.com/downloads)

## Initialization of database and database tables

Definitions of database tables are located in:
* [the-framework/framework/sql/postgresql](https://github.com/antonbalucha/the-framework/tree/master/framework/sql/postgresql)
* [the-framework/framework/sql/mysql](https://github.com/antonbalucha/the-framework/tree/master/framework/sql/mysql)

You may choose which database you want to use. You have to run SQL scripts according their ordering.

## Set up of The Framework

Before building The Framework by Apache Maven you have to configure some properties. Template for configuration file is located:
* for development purposes in ```buildconfig.devel.properties.template``` - you have to update it and rename it to ```buildconfig.devel.properties```
* for production purposes in ```buildconfig.prod.properties.template``` - you have to update it and rename it to ```buildconfig.prod.properties```

When you subsequently run build commands mentioned below, configuration properties will be correctly copied to configuration files  ```shiro.ini```,``` persistence.xml```, ```configuration.properties``` and ```logback.xml```.

## Build The Framework

For development purposes:
* if you wish to build it without running of tests:
    * ```mvn clean -Pdevel resources:resources install -Dmaven.test.skip=true```
    * or (since devel profile is the default one):  ```mvn clean resources:resources install -Dmaven.test.skip=true```
* if you wish to build and run tests after build:
    * ```mvn clean -Pdevel resources:resources resources:testResources install -Dmaven.test.skip=false```
    * or (since devel profile is the default one): ```mvn clean resources:resources resources:testResources install -Dmaven.test.skip=false```

For production purposes:
* if you wish to build it without running of tests:
    * ```mvn clean -Pprod resources:resources install -Dmaven.test.skip=true```
* if you wish to build and run tests after build:
    * ```mvn clean -Pprod resources:resources resources:testResources install -Dmaven.test.skip=false```

When you build The Framework you may deploy it on Apache Tomcat and start it.

## Learn, how to use The Framework

Maybe later I will write or describe more detailly structure of The Framework, but for now, you can learn very easily The Framework by yourself - by debugging. In directory [the-framework/framework/postman-collection](https://github.com/antonbalucha/the-framework/tree/master/framework/postman-collection) is present Postman collection. You import it into your Postman and choose the web API you want to test/learn on it. Then you find annotation of selected API you want to test in Java source code and appropriate methods mark as breakpoint. When you send request via Postman, your IDE should start with debugging and you can see by which classes and methods flow runs. 

# In conclusion

## License

I provide this project under [Apache License 2.0](https://github.com/antonbalucha/the-framework/blob/master/LICENSE).

## Contact

In case of any questions about The Framework or suggestions for improvements or some feedback or whatever is in your mind about The Framework you may contact me on ```projects@tonyb.technology```.

## Keywords

Java, The Framework, simple, basic working structure, examples, third party libraries
