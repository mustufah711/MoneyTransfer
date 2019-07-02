# Money Transfer
API to send money between two users

The API is exposed on port 4567. 

## API Endpoints:
 
### /users - GET 
Retrieve all users
  
### /users - POST 
- Create new user
- Schema Example: { "userName": "John_Smith12", "name": "John Smith", "age": "25", "bankAmount": "1000" }
  
### /user/:userName - GET
Retrieve single user
  
### /send-money - POST
- Send money between two users
- Schema Example: { "sender": "John_Smith12", "receiver": "Tim_Sanchez_34", "sendAmount": "200" } 

### /user/:userName - DELETE
Delete single user


  
