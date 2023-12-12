# SpringProducts
Spring boot app with basic auth and logic for general products

# Demo app:
<br><b>
API with basic auth</b>

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
- Put method : ```/product/admin/modify-price ```
- To modify the price
  ```/product/admin/add ``` POST METHOD with body example :
   ```ruby
  {
    "name": "product 1",
    "price": "13 RON",
    "description": "produs"
   }
   ```
   <br>
