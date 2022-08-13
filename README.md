# Blog Site

## Description

A simple web application for blog site created using SpringBoot for backend as Maven project which integrates MySQL for database connection and React.js for frontend.

People can create account to add blogs.

- The app is created following Uncle Bob's clean code philosophy as much as possible.
- implemented Spring MVC architecture.
- Spring Security features for login/signup.
- Also uses JWT token based authorization feature.
  <br/>

## Flowchart of app architecture

![App architecture flowchart](src/main/resources/images/App_flowchart.jpg)
<br/>

## Use Case Diagram

![Use Case Diagram](src/main/resources/images/Use_case_diagram_for_blogsite.jpg)

A use case diagram is a graphical depiction of a user's possible interactions with a system. A use case diagram shows various use cases and different types of users the system has and will often be accompanied by other types of diagrams as well. The use cases are represented by either circles or ellipses. The user's interactions with the system are represented by arrows.

A use case diagram doesn't go into a lot of detail—for example, don't expect it to model the order in which steps are performed. Instead, a proper use case diagram depicts a high-level overview of the relationship between use cases, actors, and systems.
<br/>

## DFD-0 and DFD-1 with ERD & data schema and annotations

- **Database schema** is the structure of a database described in a formal language supported by the database management system.

- **Entity relationship diagram (ERD)**, also known as an entity relationship model, is a graphical representation that depicts relationships among people, objects, places, concepts or events within an information technology (IT) system.

- **Data Flow Diagram (DFD)** provides a visual representation of the flow of information (i.e. data) within a system. By drawing a Data Flow Diagram, you can tell the information provided by and delivered to someone who takes part in system processes, the information needed to complete the processes and the information needed to be stored and accessed.

  - DFD-0 (Context DFD):

    A context diagram is a data flow diagram that only shows the top level, otherwise known as Level 0. At this level, there is only one visible process node that represents the functions of a complete system in regards to how it interacts with external entities.

    Some of the benefits of a Context Diagram are:

    - Shows the overview of the boundaries of a system
    - No technical knowledge is required to understand with the simple notation
    - Simple to draw, amend and elaborate as its limited notation.
      <br/><br/>

  - DFD-1 (System DFD):

    In a level 1 data flow diagram, the single process node from the context diagram is broken down into subprocesses. As these processes are added, the diagram will need additional data flows and data stores to link them together.
    <br/><br/>

- DFD-0 of blog site is shown in the image below.
  ![DFD-0]()
  <br/><br/>

- DFD-0 of blog site is shown in the image below.
  ![DFD-1]()
  <br/><br/>

## Prerequisites

- Java version 8+
- SpringBoot 2.2.x
- MySQL 8.0.18
- Spring version 5.2.x
- React version 16.8.x
- Node version 10.x
- NPM version 6.x
- Git version 2.x
- [Maven Dependencies](https://github.com/sthsuyash/Blog-Site/blob/main/pom.xml) for _pom.xml_

- Application.properties

 ```application.properties
    security.enable.csrf=false
    spring.main.allow-circular-references=true
    
    spring.datasource.url=jdbc:mysql://localhost:3306/blog  
    spring.datasource.username=${USERNAME}
    spring.datasource.password=${PASSWORD}
    
    spring.jpa.hibernate.ddl-auto=update
    spring.jpa.show-sql=true
    spring.jpa.properties.hibernate.format_sql=true
    
    server.error.include-message=always
 ```

 <br/>

## Running the Application

- Clone the repository to your local device.

  ```git
  git clone https://github.com/sthsuyash/Blog-Site.git
  ```

- Configure the Application.properties file.

  _in the place of_ **USERNAME** _and_ **PASSWORD** _, put your database username and password_

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

  <br/>

## Contributing

To contribute to this app

- First, fork the repository. Now there will be a copy of this repo in your account.
- Clone the repository in your account and make changes to your local repo

  ```git
  git clone ${your_repo_url}
  ```

- To add features to the main repository, open Pull Request.
  <br/>

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
<br/>

[Comment]: <> (## Citation)

## Contact

- sthasuyash11@gmail.com
