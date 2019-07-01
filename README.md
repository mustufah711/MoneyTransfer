# Money Transfer
Simple API to send money between two users

The API is exposed on port 4567. 

API Endpoints:
 
/users - GET
  -Retrieve all users
  
/users - POST
  -Create new user
  schema: 
    {
      "userName": "John_Smith12", 
      "name": "John Smith", 
      "age": "25",
      "bankAmount": "1000"
  }
  
/user/:userName - GET
  -Retrieve single user
  
/sendMoney - POST
  -Send money between two users
  schema:
    {
      "sender": "John_Smith12",
      "receiver": "Tim_Sanchez_34",
      "sendAmount": "200"
    } 


  
