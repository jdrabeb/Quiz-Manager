# Quiz-Manager

This is a tool for creating and generating a quiz.

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


## User guide

Before starting you need to run the sql queries in the "database_init" file inside the h2 databse. This will create all the tables needed to stores the quesitons and users. 

The queries will also add a student user and an admin user with the identifiants shown after. The tables created are empty so taking a quiz as student will not be possible until you add questions as an admin.

#### Starting the program 

To run the program, run the Main in the package com.epita.quimanager.main. You can use Eclipse, Netbeans or Intellij Ideas to run the project easily. Otherwise you need to compile all the classes.


Will get you to a menu where you can register or login. 

When logging in, based on a users table in the database, the program checks the user's rights (if it is an admin or not, if not the user is considered as a student).

You can also exit which will stop the program.

### Admin Console

To login as admin, use the following ids:

```
  username: admin
  password: admin
```

As an admin you will have the following menu that allows you to do the following actions: 

```
  1. Manipulate MCQ Questions
  2. Manipulate Open Questions
  3. Exit
```

Either choice will give you its proper menu that allows you to do the following actions on the type of question 
you selected.
Exiting will get you back to the register/login menu.

```
  1. Create a question
  2. Seach a question
  3. Update a question
  4. Delete a question
  5. Exit
```

- To search for a question you will have to enter a certain topic and all the questions covering this topic will be displayed.
- To update a question you will have displayed all the questions in the databse with their ids. You will have to enter :
  The id of the question you want to update.
- To delete a question you will have displayed all the questions in the databse with their ids. You will have to enter: 
  The id of the question you want to delete.



### Student Console

To login as admin, use the following ids:

```
  username: toto
  password: tata
```

When logged in a student you will have displayed all the topics of questions available. You have to first enter the 
number of topics you want to have in quiz
and then write all the topis of interest.

A quiz will then start.

You will have to enter the number of the question you think is correct in case of an MCQ question and write your full answer in case of an open question.

If the number you entered is not valid (e.g: entering 4 while there are only 3 choices available) the program will keep retrying to get from you a valid answer number.

At the end of the evaluation you will have the grade calculated based on the MCQ questions displayed and will be asked if you want to export your evaluation in pdf.

```
  Your grade is : x
  Do you want to export your quiz in pdf ? y/n
```

If you chose you chose to export your quiz along with your answers and grade, you will be asked to enter the name you want to give your pdf. On successful pdf generation you will get a log telling you so.

```
  PDF Created successfully!
```

The pdf will be generated in the main directory of the program.
