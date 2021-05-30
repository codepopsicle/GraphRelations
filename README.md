# GraphRelations
Simple API framework which exposes APIs to determine relations between nodes in a graph

## API 1

* All edges in the graph are parsed to create an adjacency map of the graph
* All keys of the map which aren't present the list of vertices in the input graph will have zero parents
* All keys who have only one item in their respective vertex list have only one parent

## API 2

* All edges in the graph are parsed to create an adjacency map of the graph
* For each vertex in the input edge, do a DFS search using a stack to create two traversal lists for both
* If both traversal lists contain more than one common element, then return true.


### Run and install the program

* Clone the repository
* Navigate to path '/tasks' and run the command mvn spring-boot:run, which will start the server on the localhost on port 8080.
* To test task1, send a GET request to 'http://localhost:8080/task1'. The body of the request is in JSON format, and the contents of /apitest/task1-input.json can be used to test.
* To test task2, send a GET request to 'http://localhost:8080/task2'. The body of the request is in JSON format, and the contents of /apitest/task2-input.json can be used to test.
* The Swagger powered API documentation is available at 'http://localhost:8080/swagger-ui.html'
