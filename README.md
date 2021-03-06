### Starting the spring-boot application and MySQL Server
- Project contains two docker-compose files, one for development/testing etc. and another for deployment.
- The docker-compose file for development uses the official docker image from MySQL and can be started as follows.
  ```
  $ cd todo-backend/mysql-server-for-testing
  $ docker-compose up
  ```
- The docker-compose file at the root of the project builds the app docker container and the MySQL server. Once maven is used to build the jar file, the following command at the root of the project can be issued to start the application.
  ```
  $ docker-compose up
  ```

### EXAMPLE API REQUESTS AND RESPONSES
#### GET ALL TODOS
##### Request
<pre>
curl -X GET http://localhost:5000/todos
</pre>
##### Response
<pre>
[
  {"id":1,"title":"title-1","description":"description-1","completed":false},
  {"id":2,"title":"title-2","description":"description-2","completed":false},
  {"id":3,"title":"title-3","description":"description-3","completed":false},
  {"id":4,"title":"title-4","description":"description-4","completed":false},
  {"id":5,"title":"title-5","description":"description-5","completed":false}
]
</pre>

#### GET COMPLETED TODOS
##### Request
<pre>
curl -X GET http://localhost:5000/todos/completed
</pre>
##### Response
<pre>
[
  {"id":1,"title":"title-1","description":"description-1","completed":true},
  {"id":2,"title":"title-2","description":"description-2","completed":true},
]
</pre>

#### GET NOT COMPLETED TODOS
##### Request
<pre>
curl -X GET http://localhost:5000/todos/not-completed
</pre>
##### Response
<pre>
[
  {"id":3,"title":"title-3","description":"description-3","completed":false},
  {"id":4,"title":"title-4","description":"description-4","completed":false},
  {"id":5,"title":"title-5","description":"description-5","completed":false}
]
</pre>


#### GET TODO BY ID
##### Request
<pre>
curl -X GET http://localhost:5000/todos/1
</pre>
##### Response
<pre>
{"id":1,"title":"title-1","description":"description-1","completed":false}
</pre>

#### CREATE TODO
##### Request
<pre>
curl -X POST http://localhost:5000/todos \
   -H 'Content-Type: application/json' \
   -d '{"title":"title-1","description":"description-1", "completed": false}'
</pre>
##### Response
<pre>
{"id":1,"title":"title-1","description":"description-1","completed":false}
</pre>

#### UPDATE TODO BY ID
##### Request
<pre>
curl -X PUT http://localhost:5000/todos/1 \
   -H 'Content-Type: application/json' \
   -d '{"title":"title-1","description":"description-1-finished", "completed": true}'
</pre>

#### DELETE TODO BY ID
##### Request
<pre>
curl -X DELETE http://localhost:5000/todos/2
</pre>

#### DELETE ALL TODOS
##### Request
<pre>
curl -X DELETE http://localhost:5000/todos
</pre>

