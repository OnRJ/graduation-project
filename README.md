# Graduation
[![Codacy Badge](https://app.codacy.com/project/badge/Grade/4ba3014c01324de793cdebd8147b1bd8)](https://www.codacy.com/gh/OnRJ/graduation-project/dashboard?utm_source=github.com&amp;utm_medium=referral&amp;utm_content=OnRJ/graduation-project&amp;utm_campaign=Badge_Grade) 

## Get All meals
Access for roles: ADMIN, USER <br/>
Request type: GET <br/>
Example request: http://localhost:8080/rest/meals <br/>
```
curl -u admin@gmail.com:admin http://localhost:8080/rest/meals
```

## Get All meals From Menu
Access for roles: ADMIN, USER <br/>
Request type: GET <br/>
Example request: http://localhost:8080/rest/meals/menuId={menuId} <br/>

Attribute | Data type | Size | Required | Description
--------- | --------- | ---- | -------- | -----------
menuId    | string    | 10   | yes      | menu id


## Create meal for menu
Access for roles: ADMIN <br/>
Request type: POST <br/>
Example request: http://localhost:8080/rest/meals <br/>
```json
{
  "menu": {
    "id": "100009"
  },
  "name": "Meal2",
  "price": "19.3"
}
```

Attribute | Data type | Size | Required | Description
--------- | --------- | ---- | -------- | -----------
name | string | 2-100 | yes | name meal
price | String | - | yes | price meal
menu.id | string | 10 | yes | menu id

## Delete meal
Access for roles: ADMIN <br/>
Request type: DELETE <br/>
Example request: http://localhost:8080/rest/meals/{mealId}/menuId={menuId} <br/>

Attribute | Data type | Size | Required | Description
--------- | --------- | ---- | -------- | -----------
mealId | string | 10 | yes | meal id
menuId | string | 10 | yes | menu id


## Get all restaurants
Access for roles: ADMIN, USER <br/>
Request type: GET <br/>
Example request: http://localhost:8080/rest/restaurants <br/>

## Get restaurant
Access for roles: ADMIN, USER <br/>
Request type: GET <br/>
Example request: http://localhost:8080/rest/restaurants/{restaurantId} <br/>

Attribute | Data type | Size | Required | Description
--------- | --------- | ---- | -------- | -----------
restaurantId | string | 10 | yes | restaurant id

## Create restaurant
Access for roles: ADMIN <br/>
Request type: POST <br/>
Example request: http://localhost:8080/rest/restaurants <br/>
```json
{
    "name": "Good place"
}
```
Attribute | Data type | Size | Required | Description
--------- | --------- | ---- | -------- | -----------
name | string | 2-100 | yes | name restaurant

## Update restaurant information
Access for roles: ADMIN <br/>
Request type: POST <br/>
Example request: http://localhost:8080/rest/restaurants/{restaurantId} <br/>
```json
{
    "name": "Interesting place"
}
```
Attribute | Data type | Size | Required | Description
--------- | --------- | ---- | -------- | -----------
restaurantId | string | 10 | yes | restaurant id
name | string | 2-100 | yes | name restaurant

## Delete restaurant
Access for roles: ADMIN <br/>
Request type: DELETE <br/>
Example request: http://localhost:8080/rest/restaurants/{restaurantId} <br/>

Attribute | Data type | Size | Required | Description
--------- | --------- | ---- | -------- | -----------
restaurantId | string | 10 | yes | restaurant id


## Get all votes by date
Access for roles: ADMIN, USER <br/>
Request type: GET <br/>
Example request: http://localhost:8080/rest/votes/100005/date?date={yyyy-MM-dd} <br/>

## Create vote
Access for roles: ADMIN, USER <br/>
Request type: POST <br/>
Example request: http://localhost:8080/rest/votes <br/>
```
curl -X POST http://localhost:8080/rest/votes  -H "Content-Type: application/json"   -d '{"restaurant" : {"id": 100005}, "user" : {"id": 100002}}' --user "admin@gmail.com:admin"
```
Attribute | Data type | Size | Required | Description
--------- | --------- | ---- | -------- | -----------
restaurant.id | string | 10 | yes | restaurant id
user.id | string | 10 | yes | user id

## Delete vote
Access for roles: ADMIN, USER <br/>
Request type: DELETE <br/>
Example request: http://localhost:8080/rest/votes/{voteId} <br/>

Attribute | Data type | Size | Required | Description
--------- | --------- | ---- | -------- | -----------
voteId| string | 10 | yes | vote id

## Get all menus
Access for roles: ADMIN, USER <br/>
Request type: GET <br/>
Example request: http://localhost:8080/rest/menus <br/>

## Get all menu by date
Access for roles: ADMIN, USER <br/>
Request type: GET <br/>
Example request: http://localhost:8080/rest/menus/date?date={yyyy-MM-dd} <br/>

## Create menu
Access for roles: ADMIN <br/>
Request type: POST <br/>
Example request: http://localhost:8080/rest/menus/menu/ <br/>
```json
{
  "restaurant" : {
    "id" : "100004"
  }
}
```

Attribute | Data type | Size | Required | Description
--------- | --------- | ---- | -------- | -----------
restaurantId| string | 10 | yes | restaurant id

## Update menu
Access for roles: ADMIN <br/>
Request type: POST <br/>
Example request: http://localhost:8080/rest/menus/menu/ <br/>
```json
{
  "id" : "100016",
  "restaurant" : {
    "id" : "100004"
  },
  "meals" : null,
  "date" : "2021-09-01"
}
```
Attribute | Data type | Size | Required | Description
--------- | --------- | ---- | -------- | -----------
id | string | 10 | yes | menu id
restaurantId | string | 10 | yes | restaurant id
date | date | - | yes | date menu

## Delete vote
Access for roles: ADMIN <br/>
Request type: DELETE <br/>
Example request: http://localhost:8080/rest/menus/100008/restaurantId={restaurantId} <br/>

Attribute | Data type | Size | Required | Description
--------- | --------- | ---- | -------- | -----------
restaurantId| string | 10 | yes | restaurant id