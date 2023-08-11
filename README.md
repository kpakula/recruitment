# Rest API

## How to run:
Execute:
1. ``docker-compose -f docker/docker-compose.yml up -d``
2. ```mvn clean install``` 
3. ```mvn spring-boot:run```

#### Note: In the case of 2 and 3, you can use the IDE.

---

### API Endpoints
| HTTP Verbs | Endpoints                  | Action                                                              |
|------------|----------------------------|---------------------------------------------------------------------|
| GET        | /users                     | To get all users (pagination - default - pageNo=0 & pageSize=10     |
| GET        | /users?pageNo=x&pageSize=y | To get all users (pagination - pageNo=x & pageSize=y)               |
| GET        | /users/{pesel}             | To get user by PESEL                                                |
| POST       | /users                     | To create user                                                      |
| POST       | /users/{id}/contact        | To create contact for user with specific id                         |
| POST       | /dumps/users               | To generate file with all users - filename **user-dump-[UUID].txt** |                


#### GET [/users](localhost:8080/users)
#### GET [/users?pageNo=0&pageSize=25](localhost:8080/users?pageNo=0&pageSize=25)
#### GET [/users/12345678910](localhost:8080/users/12345678910)
#### POST [/users](localhost:8080/users)
````
{
   "firstname": "xxxxx",
   "lastname": "xxxxx",
   "pesel": "12345678910"
}
````

#### POST [/users/1/contact](/users/1/contact )
type:
* BUSINESS_PHONE_NUMBER
* RESIDENCE_ADDRESS
* EMAIL
* PRIVATE_PHONE_NUMBER
* REGISTERED_ADDRESS
```
{
   "type": "EMAIL",
   "value": "email@email.com"
}
```

#### POST [/dumps/users](/dumps/users)