# Inside Baltimore (webrest)

Inside Baltimore allows users to visualize different datasets that are provided by Baltimore City via [Open Baltimore](https://data.baltimorecity.gov). This application was built primarily using Spring, Hibernate, AngularJS, and D3JS.

Currently, the application contains two datasets:
  - **Vacant housing** data from 2011-2016
  - **Crime** data from January to November 2016

### How to Run
*Note: Webrest was developed and tested on [Java 8](http://www.oracle.com/technetwork/java/javase/overview/java8-2100321.html).*

1) Pull the code from github
```sh
$ git clone git@github.com:charleshb417/webrest.git
```
2) Update /src/main/resources/localhost.mysql.properties and put your 
MySQL credentials there. **Do not commit this file.**

3) Run the MySql script at src/main/resources/createDB.sql
```sh
$ cd src/main/resources
$ mysql -u[user] < createDB.sql
```

4) In the directory that contains pom.xml, run Jetty. This will be the only step needed to re-run the application at a later date.
```sh
$ mvn clean jetty:run
```

5) Go to http://localhost:8080/webrest. Log in using the default credentials "user" and "password". You can also click the "signup" link and create your own account.
