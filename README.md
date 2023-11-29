# SpringProducts
Spring boot app with basic auth and logic for general products

# Requests:
<br><b>
Create an API that acts as a store management tool

- Create a GitHub profile if you don't have one

- Use git in a verbose manner, push even if you wrote only one class

- Create a Java, maven based project, Spring Boot for the web part

- No front-end, you can focus on backend, no need to overcomplicate the structure

- Implement basic functions, for example: add-product, find-product, change-price or others

- Optional: Implement a basic authentication mechanism and role based endpoint access

- Design error mechanism and handling plus logging

- Write unit tests, at least for one class

- Use Java 9+ features

- Add a small Readme to document the project </b>

# How to use:
- Add your DB to application.yml configurations
 ```ruby
 datasource:
    url: jdbc:mysql://INSERT_MYSQL_DB
    username: YOUR_USERNAME
    password: YOUR_PASSWORD
```
- Basic authentication is required in the app, by default there are 2 roles: USER and ADMIN <br>
  <b>USER  - Password: user , Username: user <br>
  ADMIN - Password: admin , Username: admin <br> </b>
- The app can be used only if authenticated, as a User you are authorized to use ```/product/all ``` and ```/product/{id} ``` , in order to populate the DB you need to be authorized as Admin:
- Delete method for deleting a product: ```/product/admin/{id} ```
- Put method for modifying an existing product: ```/product/admin/modify ``` with product body
- Put method : ```/product/admin/modify-price ``` to only modify the price
  ```/product/admin/add ``` POST METHOD with body example :
   ```ruby
  {
    "name": "product 1",
    "price": "13 RON",
    "description": "produs"
   }
   ```
   <br>