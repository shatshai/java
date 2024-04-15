# User Management with Publish-Subscribe
This project implements user management functionalities using a publish-subscribe messaging system, built with the powerful Spring Boot framework. Spring Boot offers a streamlined approach to developing microservices and simplifies the configuration process.

## Features
* Create User: Creates a new user account. Upon successful creation, a message is published to the queue notifying other interested parties.
* User Creation Consumer: Listens to the user creation queue and performs any necessary actions after a new user is created (e.g., sending a welcome email).
* Get User by Username: Retrieves a specific user based on their username.
* Get All Users: Retrieves a list of all registered users.

## Benefits of using Publish-Subscribe:
* Decoupled architecture:
* Services are loosely coupled, improving maintainability and scalability.
* Asynchronous processing: User creation doesn't block other operations, enhancing responsiveness.
* Extensible: New functionalities can be added by subscribing to the user creation queue.

## Note:
* This description assumes some basic understanding of publish-subscribe messaging.

## Additional Information (Optional):
* You can add details about the specific messaging system used (e.g., RabbitMQ, Kafka).
* Include instructions on setting up and running the project (if applicable).

## Running and Testing
Can run and test this by docker-compose

```sh
docker-compose build
docker-compose up
```

## Api Test
```sh
curl -X 'POST' \
  'http://192.168.1.190:8080/api/users' \
  -H 'accept: application/json' \
  -H 'Content-Type: application/json' \
  -d '{
  "username": "user8",
  "email": "user8@example.com"
}'

curl -X 'GET' \
  'http://192.168.1.190:8080/api/users' \
  -H 'accept: application/json'
```

## Api Response
```
{
  "id": 6,
  "username": "user8",
  "email": "user8@example.com"
}

[
  {
    "id": 1,
    "username": "user1",
    "email": "user1@example.com"
  },
  {
    "id": 2,
    "username": "user2",
    "email": "user2@example.com"
  }
]
```

## Screen short
![Alt text](images/swagger.png?raw=true "Swagger")
![Alt text](images/create-user-response.png?raw=true "Create User")
![Alt text](images/queue-consumer.png?raw=true "Queue consumer response")
![Alt text](images/test-converage.png?raw=true "Code coverage")