# Blog Site

## Description

This is a repository for blog site created using SpringBoot for backend

## Prerequisites
- Maven Dependencies for pom.xml<br/>
  ```xml
    <dependencies>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-web</artifactId>
        </dependency>

        <dependency>
            <groupId>mysql</groupId>
            <artifactId>mysql-connector-java</artifactId>
            <scope>runtime</scope>
        </dependency>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-test</artifactId>
            <scope>test</scope>
        </dependency>
    </dependencies>
  ```
  
 - Application.properties
 ```properties
    spring.datasource.url=jdbc:mysql://localhost:3306/blog  
    spring.datasource.username=${USERNAME} // instead of ${USERNAME} you may use a generic one as well, like 'root' but then it will be pushed to github with the app so anyone can see you username and password.
    spring.datasource.password=${PASSWORD}
    spring.jpa.hibernate.ddl-auto=create-drop //running after the first time (when the tables are created in the database) or later when you want the data remain for later tests you have to change the key word 'create-drop' to 'update' so that the data remains in place. Oops! the database itself without the tables needs to be created manually first.
    spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.MySQL5Dialect
    spring.logging.level.org.hibernate.SQL=debug
    spring.jpa.show-sql=true
 ```

## Running the Application

- Clone the repository to your local device. 
  ```git
  git clone https://github.com/sthsuyash/Blog-Site.git
  ```

- Configure the Application.properties file.
  in the place of USERNAME and PASSWORD, put your database username and password
  ```properties
  spring.datasource.username=${USERNAME} 
  spring.datasource.password=${PASSWORD}
  ```
  
- Run the Application using IntelliJ IDEA or STS.
  ```mvn
  mvn clean package  
  ```
  
  ```mvn
  ./mvnw spring-boot:run
  ```
  

## License
Copyright (c) 2012-2022 Scott Chacon and others

Permission is hereby granted, free of charge, to any person obtaining
a copy of this software and associated documentation files (the
"Software"), to deal in the Software without restriction, including
without limitation the rights to use, copy, modify, merge, publish,
distribute, sublicense, and/or sell copies of the Software, and to
permit persons to whom the Software is furnished to do so, subject to
the following conditions:

The above copyright notice and this permission notice shall be
included in all copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND,
EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF
MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND
NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE
LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION
OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION
WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.

[Comment]: <> (## Citation)

## Contact
- sthasuyash11@gmail.com
