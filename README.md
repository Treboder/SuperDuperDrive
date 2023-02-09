# Super*Duper*Drive Cloud Storage

This project demonstrates basic [Spring Boot](https://spring.io/guides/gs/spring-boot/) features
along with an automated CI pipeline using [Docker](https://www.docker.com/) and [Github Actions](https://github.com/features/actions).

The application provides very simple Cloud Storage features, including personal information management features.
The project covers server, website, and tests, and deployment as docker image.
It offers three user-facing features:

1. **Simple File Storage:** Upload/download/remove files
2. **Note Management:** Add/update/remove text notes
3. **Password Management:** Save, edit, and delete website credentials.

README.md is also available via [Github Pages]()

The project features:
* [Spring MVC / Spring Boot](https://spring.io/guides/gs/spring-boot/)
* Authorization via [Spring Security](https://spring.io/guides/gs/securing-web/)
* [H2 databse](https://www.h2database.com/html/main.html) for data management
* [Thymeleaf](https://www.thymeleaf.org/) for html integration
* Web Frontend Testing with [Selenium](https://www.selenium.dev/)
* Containerization via Docker with latest images published to 
[Docker Hub]() and 
[Github Registry]()


## General Setup

It's a Maven project configured for all the dependencies the project requires, though you should feel free to add any additional dependencies you might require. [You can download or clone the starter repository here](https://github.com/udacity/nd035-c1-spring-boot-basics-project-starter/tree/master/starter/cloudstorage).

The project  covers three layers of interest:

1. The back-end with Spring Boot
2. The front-end with Thymeleaf
3. Application tests with Selenium

The database schema for the project and has added it to the `src/main/resources` directory.
HTML templates are placed in the `src/main/resources/templates` folder.
Thymeleaf is then used to fill the static templates with real data generated from the users.

You might observe that Selenium test results depend on your local browser settings.
In case of failing tests, we make sure that jars are build with:
````
mvn clean package -Dmaven.test.skip=true
````


## The Back-End

The back-end is all about security and connecting the front-end to database data and actions. 

1. Managing user access with Spring Security

- The system restricts unauthorized users from accessing pages other than the login and signup pages. 
To do this, we use a security configuration class that extends the `WebSecurityConfigurerAdapter` class from Spring. 

- Spring Boot has built-in support for handling calls to the `/login` and `/logout` endpoints. 
The security configuration overrides the default login page with one of our own, discussed in the front-end section.
 
- We also use a custom `AuthenticationProvider` which authorizes user logins by matching their credentials against those stored in the database.  

2. Handling front-end calls with controllers

 - Controllers for the application bind application data and functionality to the front-end. 
That means using Spring MVC's application model to identify the templates served for different requests and populating the view model with data needed by the template. 

 - The controllers are responsible for determining what, if any, error messages the application displays to the user. 
When a controller processes front-end requests, it delegates the individual steps and logic of those requests to other services in the application, interpreting the results to ensure a smooth user experience.

 - Consider the `HashService` and `EncryptionService` classes included in the starter code package `service`. 
These classes encapsulate simple, repetitive tasks and are available anywhere dependency injection is supported.

3. Making calls to the database with MyBatis mappers

 - Based on the provided database schema, the Java classes in the `model` package match the data in the database.
 For each data base table, there exists one POJO (Plain Old Java Objects) with fields that match the names and data types in the schema.

 - To connect these model classes with database data, we use a MyBatis mapper in the `mapper` packagefor each of the model types.
These have methods that represent specific SQL queries and statements required by the functionality of the application.
They support the basic CRUD (Create, Read, Update, Delete) operations for their respective models at the very least. 


## The Front-End

the HTML templates for the required application pages include fields, modal forms, success and error message elements, as well as styling and functional components using Bootstrap as a framework. 
Thymeleaf attributes in these templates supply the back-end data and functionality.

### Login/Signup

The app offers proper login and signup features.
Once a new user is signed up, the user can use login page login to the application.
The signup procedure validates that the username supplied does not already exist in the application, and show such signup errors on the page when they arise. 
The password is stored securely.


### Home page

The home page is the center of the application and hosts the three required pieces of functionality. 
The existing template presents them as three tabs that can be clicked through by the user:
The home page has a logout button that allows the user to logout of the application and keep their data private.

#### Files

The user is able to upload files and see any files they previously uploaded.
The user also able to view/download or delete previously-uploaded files.
Any errors related to file actions are displayed. 
For example, a user is not be able to upload two files with the same name, but they'll never know unless you tell them!

#### Notes
The user is able to create notes and see a list of the notes they have previously created.
The user is able to edit or delete previously-created notes.

#### Credentials
The user is able to store credentials for specific websites and see a list of the credentials they've previously stored.
Displayed passwords in this list, are encrypted!
The is able to view/edit or delete individual credentials. 
When the user views the credential, they are able to see the unencrypted password.


## Testing

Selenium tests verify user-facing functionality and prove that your code is feature-complete.
There are tests for user signup, login, and unauthorized access restrictions.
We also test for note creation, viewing, editing, and deletion.
Further tests cover credential creation, viewing, editing, and deletion.


## Password Security

The app does not save the plain text credentials of the application's users in the database. 
A hashing function to store a scrambled version instead, to be found in the `HashService`.
When the user signs up, a hashed version of their password is stored in the database, and on login, the password is hashed before comparing it with the hashed password in the database. 

For storing credentials in the main part of the application, we can't hash passwords because it's a one-way operation. 
The user needs access to the unhashed password, after all! 
So instead, we encrypt the passwords using the `EncryptionService` that can encrypt and decrypt passwords. 
When a user adds new credentials, encrypt the password before storing it in the database. 
When the user views those credentials, we decrypt the password before displaying it. 

For more reading on encryption follow the links below:

[Hash Function](https://en.wikipedia.org/wiki/Hash_function)
[Encryption](https://en.wikipedia.org/wiki/Encryption)