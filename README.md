### Usage
From the browser you can hit the following APIs

localhost:8080/users/1  
localhost:8080/users/2  
localhost:8080/api/dynamic  
localhost:8080/api/error

To shutdown the server do the following from the terminal  
curl -X POST http://localhost:8080/__admin/shutdown

### Todo:
1. Custom returning of the body based on the parameters
2. Dockerising
3. Multiple Domains & ports
4. Better example for errors