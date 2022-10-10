# About a project
### It's my own REST API that will help track the timing of replacement of consumables/materials in the car.
### *The API is working, there are plans to add DTO, several methods, and deploy everything to the cloud.*

#### App works with basic auth and doesn't have session management.

##### For user role:
* */api* - basic path
* */api/registration* - POST - add new user
  #### Important! - registration for new users works:
  * without any Basic Auth
  * or with login - "test", password - "test"
* */api/user_info* - GET - get information about authorized user
* */api/cons* - GET - return list of consumables of authorized user
* */api/cons* - POST - add one consumable for user. Accepts JSON of the following format

    *{
"name": "testCons",
"mileageNow": 100,
"replacementAfter": 100
}*
##### For admin role:
* */api/users* - GET - return list of users
* */api/user_info/{id}* - GET - return user by id
