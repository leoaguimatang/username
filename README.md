# Username Tester


#### Overview
The application is designed to check the availability of the username input and also checks for explicit words.


#### Technologies
The application is developed in Java and open source technologies like Spring, SpringBoot, Spring Data JPA, Hibernate, Maven.

#### How to run the integration tests.
On terminal or command line, run the command:

```ssh
$ mvn clean verify
```

#### How to run the application.
On terminal or command line, run the command:

```ssh
$ mvn spring-boot:run
```

Once the application is running, you can issue curl commands like:

#### For User:

To list all users:

```ssh
$ curl -XGET 'http://localhost:8080/users'
```

To search users:

```ssh
$ curl -XGET 'http://localhost:8080/users?search=YOUR_INPUT'
```

To test username availability:

```ssh
$ curl -XGET 'http://localhost:8080/users/checkUsername?username=YOUR_INPUT'
```

To add a user and tests username availability:

```ssh
$ curl -XPOST -H "Content-Type: application/json" 'http://localhost:8080/users' -d '{ "username": "YOUR_INPUT" }'
```


#### For Dictionary:

To list all words:

```ssh
$ curl -XGET 'http://localhost:8080/dictionary'
```

To search a words:

```ssh
$ curl -XGET 'http://localhost:8080/dictionary?search=YOUR_INPUT'
```

To add a word:

```ssh
$ curl -XPOST -H "Content-Type: application/json" 'http://localhost:8080/dictionary' -d '{ "word": "YOUR_INPUT" }'
```

To update a word:

```ssh
$ curl -XPUT -H "Content-Type: application/json" 'http://localhost:8080/dictionary/YOUR_ID' -d '{ "word": "YOUR_INPUT" }'
```

To delete a word:

```ssh
$ curl -XDELETE 'http://localhost:8080/dictionary/YOUR_ID'
```