**install tools **

install maven >>

https://maven.apache.org/install.html

install Java8

https://www.java.com/es/download/

** build and run tests **

`mvn clean install`

** if everything is ok. run it like **

`mvn spring-boot:run`


**Calling the services**


`curl -i -X POST    -H "Content-Type:multipart/form-data"    -F "csv=@\"./matrix.csv\";type=text/csv;filename=\"matrix.csv\""  'http://localhost:8080/echo'`

`curl -i -X POST    -H "Content-Type:multipart/form-data"    -F "csv=@\"./matrix.csv\";type=text/csv;filename=\"matrix.csv\""  'http://localhost:8080/sum'`

`curl -i -X POST    -H "Content-Type:multipart/form-data"    -F "csv=@\"./matrix.csv\";type=text/csv;filename=\"matrix.csv\""  'http://localhost:8080/multiy'`

`curl -i -X POST    -H "Content-Type:multipart/form-data"    -F "csv=@\"./matrix.csv\";type=text/csv;filename=\"matrix.csv\""  'http://localhost:8080/flatten'`

`curl -i -X POST    -H "Content-Type:multipart/form-data"    -F "csv=@\"./matrix.csv\";type=text/csv;filename=\"matrix.csv\""  'http://localhost:8080/inverse'`


** Structure **


the main entrance is the package controller. we have a controller called **MatrixController** . (front controller pattern by spring)
that controller is calling a util class called **Matrixtransformer ** to transform and get a Object Domain Matrix  (inside com.league.matrix.domain package)
from a csv file , it looks like a parser. and also a validator object to make sure if that object is Valid (in this case if the matrix is a square)


Then I had two possibilities , define all the functions related to the matrix inside the matrix object or for some functions like sum, multiply and flatten
I could have defined an interface which defines that behaviour that implement it from a class. But for this easy case and first iteration I've decided to
do it like the first way mentioned.

So the core business in this case is all inside the Matrix domain object.

There are junit tests for Validator, Matrix, and parser. to test controllers over Java I should create integration tests.


** Other things **

I've used java 8 lambda expresions in some cases, but I could have resolved with two fors or one for iterating over the array of array called data
inside the matrix


I've used spring boot,  in order to make the things easier to run and build the project. log4j for logs and there is an Application class and
application.properties in order to initialize beans.

There is no service or dao layers. I though it was not necessary .


** Errors **

if the matrix if not valid or the file is empty or there is some problem at parser time with the file. the application will return a bad request http verb.
but I could have customize exception for every cases. It should be a good practice.



