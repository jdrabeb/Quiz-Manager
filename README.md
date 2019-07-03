# Quiz-Manager

This is a tool for creating and generating a quiz. It was created as a project for the Java fundamental class project in the Epita 2019 Fundamental master of computer science cursus.

## Database connection

Databse used is h2 database.
The default identifiants of database used in this projects are:
```
  jdbc.url=jdbc:h2:~/test
  jdbc.username=sa
  jdbc.password=
```

If you have different database identifiants, put them in the properties.config file.

If the h2 database doesn't work, add the h2 .jar file inside the /lib folder to the classpath.
Follow this [tutorial](http://www.oxfordmathcenter.com/drupal7/node/44) to add an external jar to the classpath of your project.

Do the same for the itextpdf jar inside the /lib folder in order to export quiz to pdf.


## Prerequiste

Before starting you need to run the sql queries in the "database_init" file inside the h2 databse. This will create all the tables needed to stores the quesitons and users. 

The queries will also add a student user and an admin user with the identifiants shown after. The tables created are empty so taking a quiz as student will not be possible until you add questions as an admin.

## Running the program 

To run the program, run the Main in the package com.epita.quimanager.main. You can use Eclipse, Netbeans or Intellij Ideas to run the project easily. Otherwise you need to compile all the classes.
