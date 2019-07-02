# MyBank-API

Basic API Rest for bank operations.
In this API you can create new bank account, deposit and withdraw money, see account balance and see account transaction movements. 

#### Technology stack

- Java 8
- Maven
- SpringBoot
- H2 Database (in memory)

#### Requirements

- Maven
- JDK 1.8

## Run the application

```
$ cd mybank-api
$ mvn spring-boot:run
```
SpringBoot application up in port 5000, check health endpoint:

```
http://localhost:5000/mybank-api/actuator/health
```

## Run the tests

```
$ cd mybank-api
$ mvn test
```


## API

#### Bank account endpoints index:

  - [Create Bank Account](#create_bank_account)
  - [Get Bank Account Balance](#get_bank_account_balance)
  - [Get Bank Account Detail](#get_bank_account_detail)
  - [Get All Bank Accounts](#get_all_bank_accounts)
  
#### Bank transaction endpoints index:
  - [Create Bank Transaction](#create_bank_transaction)
  - [Get All Bank Account Transactions](#get_all_bank_account_transactions)


### Bank Account endpoints

<a name="create_bank_account"></a>
#### Create Bank Account

    POST http://localhost:5000/mybank-api/bank_accounts
    
##### Request

``` json
{
    "alias" : "liomessi",
    "type" : "STANDARD"
}
```

##### Response

``` json
{
    "id": 1,
    "alias": "liomessi",
    "type": "BLACK",
    "balance": 0
}
```

<a name="get_bank_account_balance"></a>
#### Get Bank Account Balance

    GET http://localhost:5000/mybank-api/bank_accounts/{account_id}/balance
    
##### Response

``` json
{
  "balance": 80999.45
}
```

<a name="get_bank_account_detail"></a>
#### Get Bank Account Detail

    GET http://localhost:5000/mybank-api/bank_accounts/{account_id}
    
##### Response

``` json
{
    "id": 1,
    "alias": "liomessi",
    "type": "BLACK",
    "balance": 80999.45
}
```

<a name="get_all_bank_accounts"></a>
#### Get All Bank Accounts

    GET http://localhost:5000/mybank-api/bank_accounts
    
##### Response

``` json
[
  {
    "id": 1,
    "alias": "liomessi",
    "type": "BLACK",
    "balance": 80999.45
  },
  {
    "id": 2,
    "alias": "ronaldo",
    "type": "STANDARD",
    "balance": 0
  },
  {
    "id": 3,
    "alias": "pogba",
    "type": "PLATINUM",
    "balance": 3007.77
  }
]
```

### Bank Transaction endpoints

<a name="create_bank_transaction"></a>
#### Create Bank Transaction

    POST http://localhost:5000/mybank-api/bank_accounts/{account_id}/transactions
    
##### Request for DEPOSIT

``` json
{
	"type":"DEPOSIT",
	"amount": 10
}
```

##### Request for WITHDRAW

``` json
{
	"type":"WITHDRAW",
	"amount": 10
}
```

##### Response

``` json
{
    "id": 1,
    "date": "2019-07-01T20:59:48Z",
    "type": "DEPOSIT",
    "amount": 10
}
```

<a name="get_all_bank_account_transactions"></a>
#### Get All Bank Account Transactions

    GET http://localhost:5000/mybank-api/bank_accounts/{account_id}/transactions
    
##### Response

``` json
[
  {
    "id": 1,
    "date": "2010-01-01T16:00:00Z",
    "type": "DEPOSIT",
    "amount": 80000
  },
  {
    "id": 2,
    "date": "2010-01-01T16:00:00Z",
    "type": "DEPOSIT",
    "amount": 990
  },
  {
    "id": 3,
    "date": "2010-01-01T16:00:00Z",
    "type": "WITHDRAW",
    "amount": 90
  }
]
```

## Todo

- [ ] Securize API with user authentication
- [ ] Write unit tests for all classes
- [ ] Building a deployment process
- [ ] Caché implementation


## Documentation

Baeldung is my best friend.

- [Entity to DTO using ModelMapper](https://www.baeldung.com/entity-to-and-from-dto-for-a-java-spring-application)
- [H2 Database in memory](https://www.baeldung.com/spring-boot-h2-database)
- [Restful basics](https://www.baeldung.com/building-a-restful-web-service-with-spring-and-java-based-configuration)
- [Reentrant Locking](https://www.baeldung.com/java-concurrent-locks)