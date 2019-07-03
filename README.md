# Quiz-Manager

This is a tool for creating and generating a quiz.

## Database connection

Databse used is h2 database.
In order to set up different database configuration change the file properties.config

If the h2 database doesn't work, add the h2 .jar file inside the /lib folder to the classpath.


## User guide

Before starting you need to run the sql queries in the "database_init" file inside the h2 databse. This will create all the tables needed to stores the quesitons and users. 

The queries will also add a student user and an admin user with the identifiants shown after. The tables created are empty so taking a quiz as student will not be possible until you add questions as an admin.

#### Starting the program 

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
- To update a question you will have displayed all the questions in the databse with their ids. You will have to enter 
the id of the question you want to update.
- To delete a question you will have displayed all the questions in the databse with their ids. You will have to enter 
the id of the question you want to delete.



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

You will have to enter the number of question you think is correct in case of an MCQ question and write your full answer
in case of an open question.
At the end of the evaluation you will have the grade calculated based on the MCQ questions displayed and will be asked if you 
want to export your evaluation in pdf.
