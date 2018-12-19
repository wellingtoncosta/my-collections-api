# bankslips-api

A Spring Boot REST API to register books and movies.

### Requirements

- JDK 8+
- Gradlew 5.0+ (You can use Gradle Wrapper)

### How to run?

#### Development

First, you need to clone this git bookRepository:

```git clone https://github.com/WellingtonCosta/my-collections-api.git```

You can open the project in any IDE that supports Gradle projects. 

You also can run this project just with Gradle:

- For UNIX-based systems

```./gradlew spring-boot:run```

- For Windows

```gradlew spring-boot:run```

#### Test

To execute the automated tests, you just need to run the following command:

- For UNIX-based systems

```./gradlew test```

- For Windows

```gradlew test```

#### Production

This project is also availabe on [Heroku](https://my-collections-api.herokuapp.com/).

The application on Heroku may take a while to respond due to [Heroku Free Dynos](https://devcenter.heroku.com/articles/free-dyno-hours) limitations.

### Technologies

This project was built with the following stack of technologies:

- Gradle
- PostgreSQL
- Jackson
- Lombok
- Spring Boot (Actuator, Data JPA, , Security, Tests, Web)
- Swagger

### License

    Copyright 2018 Wellington Costa

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.