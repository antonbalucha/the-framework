# The-Framework

Structure and set of Java libraries which help me to build simple web applications.

### General idea which is behind The Framework
<p>
For understanding of the idea of The Framework and why to use it, we have to go to the history. 
On my early beginning as a programmer I learned many stuff and (as many) I thought that with the knowledge I gained I can build some awesome framework that I will use on many projects and with luck, many will use. Then I started to go to regular work and I saw that other developers are trying to build their frameworks which will be used on the project later. After some time, I realized that many times they were focused on framework itself instead of the project. Learning of a framework which many times have no documentation; was not open source; only a few people knew what certain library was doing was also not pleasant :cold_sweat: Sometimes deadlines were postponed because of problems with framework. It was not right. After all, the idea of a framework is to help to the developer to build projects quickly and easily. 
</p>
<p>
Besides of a frameworks used on a projects I also learned some third party libraries. I started to use it more also on my projects. I realized that the wheel I am trying to invent in the form of my custom awesome framework was useless, because on the Internet we have many libraries/projects with large community and support. Subsequently I deleted everything on which exist some third party open source library; created some directory and project structure and basic structure of The Framework was built. Later I made some haircut of The Framework and pushed it on the GitHub. 
</p>
<p>
So, what is The Framework now? Simple said, it is a project/directory structure linked together with third party libraries with some basic functionality - authorization, authentication, changing of language, support for translations and connection to the database. When you want to make some Java web application with mentioned functionalities, you may download The Framework and start with programming. Do not have to speculate how directory structure should looks like, which libraries you should use, how to build it. Everything is done and ready to use. 
</p>

### Which technologies/libraries are used in The Framework?
* frontend
   * presentation
      * HTML5
	  * CSS
   * scripting
      * JQuery

* Backend
   * web API
      * Jersey (https://jersey.java.net/)
   * database layer
      * PostgreSQL
   * logging
      * Logback
   * configuration
      * Apache Commons Configuration
   * security
      * Apache Shiro
   * Json processing
      * Jackson
   * object mapper
      * Dozer
   * utilities
      * Apache Commons Lang3
   * build
      * Apache Maven (https://maven.apache.org/)
   * server
      * Apache Tomcat (https://tomcat.apache.org/)

### How to get The Framework?
Run ```git clone git@github.com:antonbalucha/the-framework.git``` command from your console, which supports GIT (e.g. https://git-scm.com/downloads)

### How to set up The Framework?
Before building The Framework by Apache Maven you have to configure some properties. Template for configuration file is located:
* for development purposes in the-framework\framework\configuration\buildconfig.devel.properties.template - you have to update it and rename it to buildconfig.devel.properties
* for production purposes in the-framework\framework\configuration\buildconfig.prod.properties.template - you have to update it and rename it to buildconfig.prod.properties
When you subsequently run build command, configuration properties will be copied to configuration files like shiro.ini, persistence.xml, configuration.properties and logback.xml

### How to build The Framework?

* For development purposes: *
* if you wish to build it without running of tests:
   * ```mvn clean -Pdevel resources:resources install -Dmaven.test.skip=true``` 
   * or (since devel profile is the default one):  ```mvn clean resources:resources install -Dmaven.test.skip=true```
* if you wish to build and run tests after build:
   * ```mvn clean -Pdevel resources:resources resources:testResources install -Dmaven.test.skip=false```
   * or (since devel profile is the default one): ```mvn clean resources:resources resources:testResources install -Dmaven.test.skip=false```
* For production purposes: *
* if you wish to build it without running of tests:
   * ```mvn clean -Pprod resources:resources install -Dmaven.test.skip=true```
* if you wish to build and run tests after build:
   * ```mvn clean -Pprod resources:resources resources:testResources install -Dmaven.test.skip=false```

### Contact
In case of any questions about The Framework or suggestions for improvements or some feedback or whatever is in your mind about The Framework you may contact me on projects@tonyb.technology.

### Keywords
Java, The Framework, simple, basic working structure, examples, third party libraries