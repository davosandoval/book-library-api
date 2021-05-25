# **Read Me First**

Please read the following steps to setup the project in your local machine

# **Getting Started**

The following project is deployed with the following features:

- Java 11
- Spring Boot 2.4
- Spring Security with Bearer token
- Spring Data to persistence the data with H2 as database.
- Apache POI to generate the excel file
- Spring Fox with Swagger to generate the API documentation
- ModelMapper library to parse from Entities to DTO and vice-versa
- Docker as a container

# **Reference Documentation**

For reference, please follow the next steps:

- Pull the project from GIT: [https://github.com/davosandoval/book-library-api.git](https://github.com/davosandoval/book-library-api.git)
- In Eclipse, import the project as Maven project from the Check the **Build Path** of the project is compiled with Java 11
- Open the Terminal and go inside the **project book-library-api/**. Then execute the following command to package the project:

`mvn package`

- Then, to run the project, execute the following command

`mvn docker:start`

This command will show you the starter log with the Starter CONTAINER ID
- To view the log trace, copy the CONTAINER ID and execute the following command:

`docker logs --follow {CONTAINER_ID}`

If everything is fine, open the Swagger API documentation of this project located in [http://localhost:8090/swagger-ui.html](http://localhost:8090/swagger-ui.html)

- To stop the project, use the next command:
`mvn docker:stop`

# **Structure of the API**

The project has 2 controllers:

- The main one, it&#39;s the **library-controller-impl** that manages the book and families collections
- And the authenticator one, it&#39;s the **authentication-controller-impl** that manages the login and the logout of the application
- The **/api/v1/login** is the method that generates the **token** to be used to invoke the other API methods

Also, inside the folder **src/main/resources,** you can find the following files:

- **application.properties** : Here is the data base string connection and the JSON web token (JWT) properties.
- **data.sql** : You have example populated data for the project.

Additionally, you have 2 choices to test it. Manually with SWAGGER and automatically with POSTMAN

# **Manual Test with SWAGGER - login into the API**

The following guides illustrate how to login using the Swagger interface [http://localhost:8090/swagger-ui.html/](http://localhost:8090/swagger-ui.html#/):

- The project manages 2 roles: **OWNER** that can read and write. And **READER** that can just read the data.
- Also this project has 2 predefined users:
- The **first user** has the following properties: _username:_ **super\_user** _password:_ **password1** and this one has the **OWNER** role
- The **second user** has the following properties: _username:_ **reader\_user** _password:_ **password2** and this one has the **READER** role
- In the Swagger documentation, please expand the **authentication-controller** tab. Go to **/api/v1/login** and enter the username and password provided in the previous step.
- Try out this login and you&#39;ll receive a **token** with this format: **Bearer XXXXXXXXXXXXX** in the **authorization** header. Please copy it inclusive with the Bearer word.
- Click on **Authorize** button and paste the **token** provided in the previous step in the **value** field. Click on **Authorize** and then on **Close**
- Later, you can begin to test the API.

# **Manual Test with SWAGGER - Manage the authentication-controller-impl API**

The following guides illustrate how login and logout in the API:

- **/api/v1/login** - It&#39;s a **POST** method that generates the token login. It needs an username and password

# **Manual Test with SWAGGER - Manage the library-controller-impl API**

The following guides illustrate how to manage the to-do objects:

- **/api/v1/library/books** - It&#39;s a **GET** method that gets all the books as a collection. Use it with the header &quot;Accept: application/json&quot;
- **/api/v1/library/books?format=XLS** - It&#39;s a **GET** method that gets all the books in an excel file . Use it with the header &quot;Accept: application/octet-stream&quot;
- **/api/v1/library/books?format=TXT** - It&#39;s a **GET** method that gets all the books in a text file . Use it with the header &quot;Accept: application/octet-stream&quot;

---

- **/api/v1/library/books/{id}** - It&#39;s a **GET** method that returns a single book. Use it with the header &quot;Accept: application/json&quot;
- **/api/v1/library/books/{id}?format=XLS** - It&#39;s a **GET** method that returns a single book in an excel file. Use it with the header &quot;Accept: application/octet-stream&quot;
- **/api/v1/library/books/{id}?format=TXT** - It&#39;s a **GET** method that returns a single book in a text file. Use it with the header &quot;Accept: application/octet-stream&quot;

---

- **/api/v1/library/families** - It&#39;s a **GET** method that returns the book families collection

---

- **/api/v1/library/families** - It&#39;s a **POST** method that creates a new book family.

---

- **/api/v1/library/families/{familyId}/books** - It&#39;s a **GET** method that returns a book collection by a family id. Use it with the header &quot;Accept: application/json&quot;
- **/api/v1/library/families/{familyId}/books?format=XLS** - It&#39;s a **GET** method that returns a book collection as excel file by a family id. Use it with the header &quot;Accept: application/octet-stream&quot;
- **/api/v1/library/families/{familyId}/books?format=TXT** - It&#39;s a **GET** method that returns a book collection as txt file by a family id. Use it with the header &quot;Accept: application/octet-stream&quot;

---

- **/api/v1/library/families/{familyId}/books** - It&#39;s a **POST** method that creates a new book inside a family

---

- **/api/v1/library/families/{familyId}/books/{id}** - It&#39;s a **PUT** method that updates an existing book

---

- **/api/v1/library/families/{id}** - It&#39;s a **PUT** method that updates an exisiting book family

# **Automatic Test with POSTMAN**

To begin to test, please execute the following steps:

1. Make sure the project is running
2. Open POSTMAN an import the file **book-library-api/BookLibraryPostmanColllection.json**
3. Inside the root folder **Book library REST API Documentation** , click in the **Run** button. Then click on the blue button **Run**** Book library REST API Documentation.** You will see a screen showing all the endpoints executed automatically with status code 200

**Note:** The Authentication Token is managed automatically with an script I created in POSTMAN


