<h3 align="center">money manager</h3>

<div align="center">
 <p align="center">
 <img src="https://img.shields.io/static/v1?label=Lang&message=Java&color=8257E5&labelColor=000000" alt="java" />
 <img src="https://img.shields.io/static/v1?label=Framework&message=SpringBoot&color=8257E5&labelColor=000000" alt="Spring Boot" />
 <img src="https://img.shields.io/static/v1?label=&message=REST API&color=8257E5&labelColor=000000" alt="API REST" />
</p>
</div>

---

<p align="center"> REST API for clash flow management.
    <br> 
</p>

## 📝 Table of Contents
- [About](#about)
- [Getting Started](#getting_started)
- [Deployment](#deployment)
- [Usage](#usage)
- [Built Using](#built_using)

## 🧐 About <a name = "about"></a>
The Money Manager is a REST API that simplifies the recording of user's
financial activities, while also providing information about the 
avalible balance.

## 🏁 Getting Started <a name = "getting_started"></a>

### Prerequisites
You will need to install [Docker](https://docs.docker.com/get-docker/) and [Docker Compose](https://docs.docker.com/compose/install/)
to build a docker-compose file that will create a MySQL database container.

Make sure that you have installed [JRE or JDK](https://www.oracle.com/java/technologies/downloads/)



### Installing
**Clone the Money Manager**


```
https://github.com/lemuel-sousa/money-manager.git
```

Enter the ```backend/``` folder and then go up database

```
cd backend
docker-compose -f docker/docker-compose.yml up -d
```

**Build the project**

```
./gradlew clean build
```
**Start the application**

```
java -jar app/build/libs/app.jar
```

## 🎈 Usage <a name="usage"></a>
The API can be accessed at [localhost:8000](http://localhost:8000/).

### Endpoints
To make the HTTP requests below, the tool [HTTPie](https://httpie.io/cli) was used.

**Login unique user**

```
$ http POST :8000/auth/login email="user@example.com" password="123456"

{
    "token": "TOKEN"
}
```

**Validate token**

```
$ http POST :8000/auth/validate token="TOKEN"

{
    "is_valid": true|false
}
```

**Create a cash Activity**


```
http POST :8000/activities description="shopping" date="2023-10-04T15:30:00Z" value=80 type="expense" "Authorization: Bearer YOUR_TOKEN"

{
    "created_at": "2023-10-09T19:10:13.657174Z",
    "date": "2023-10-09T15:30:00Z",
    "description": "sale",
    "id": "3ef42472-36fe-42cf-8711-d13451b5b251",
    "type": "expense",
    "updated_at": "2023-10-09T19:10:13.657181Z",
    "value": 80.0
}

```

**List activities**

```
http GET :8000/activities  "Authorization: Bearer YOUR_TOKEN"

{
    {
        "date": "2023-10-09T15:30:00Z",
        "description": "sale",
        "id": "3ef42472-36fe-42cf-8711-d13451b5b251",
        "type": "revenue",
        "value": 250.0
    },
    {
        "date": "2023-10-04T15:30:00Z",
        "description": "shopping",
        "id": "ccd9c2d9-8108-494a-8ffb-f1cd2523a0fa",
        "type": "expense",
        "value": 80.0
    }
```

**Get balance**

```
http GET :8000/activities/balance  "Authorization: Bearer YOUR_TOKEN"

{
    "balance": 70.0
}
```

**Delete Activity**

```
http DELETE :8000/activities/ACTIVITY_ID "Authorization: Bearer YOUR_TOKEN"
```


## 🚀 Deployment <a name = "deployment"></a>
Development practices such as:
* SOLID and some principles of Clean Architecture
* REST API
* Tratment of error responses

## ⛏️ Built Using <a name = "built_using"></a>
- [Spring Boot](https://spring.io) - Server Framework
- [Java](https://www.java.com) - Programming Language
- [Mysql](https://www.mysql.com) - Database
- [Docker](https://docs.docker.com/get-started/) - Containerization



