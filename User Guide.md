
## User guide


Running the program will get you to a menu where you can register or login. 

```
Welcome to the Quiz Manager..


1) Register 
2) Login
3) Exit
```
In order to select an action you have to type the associated number.

For example : 1

On registering you will have to enter your username, a password, your id. You will then be asked if you are an admin or not.

```
Are you an admin ? y/n
```

- A teacher is an admin (y)
- A student is not (n)

The program will then re-display the Register/Login menu.

When logging in, the program checks the user's rights : 
- If it is an admin the user is considered as a teacher and accesses the Admin console.
- Else he is considered as a student and accesses the Student console.

By default the database contains the following users :

- An admin with the following ids:
```
  username: admin
  password: admin
```

- A student with the following ids:
```
  username: johndoe
  password: password
```

You can also exit which will stop the program.

### Admin Console

When logged in as an admin, you will have the following menu that allows you to do the following actions :

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

- To create a question you wil have to enter the question, its difficulty and its topic.
  - if it is an MCQ Question you will have to enter the number of choices possible and then enter the choices one by one. 
- To search for a question you will have to enter a certain topic and all the questions covering this topic will be displayed.
- To update a question you will have displayed all the questions in the databse with their ids. You will have to enter :
  The id of the question you want to update.
- To delete a question you will have displayed all the questions in the databse with their ids. You will have to enter: 
  The id of the question you want to delete.



### Student Console

When logged in as a student, you will have displayed all the topics of questions available, for example :

```
The topics available are:

java
python
c++

Enter the number of topics you want.

```
You have to first enter the 
number of topics you want your quiz to be about and then write all the topics of interest.

A quiz will then start.

You will have to enter the number of the answer you think is correct in case of an MCQ question, for example:

```
Question: A process that involves recognizing and focusing on the important characteristics 
of a situation or object is known as?
Difficulty: 1
Topic: java
1) Encapsulation
2) Polymorphism
3) Abstraction
4) Inheritance

Enter your answer: 3
```
If the number you entered is not valid (e.g: entering 5 while there are only 4 choices available) the program will keep retrying to get from you a valid answer number.

In case of an open question you will have to write your full answer, for example:

```
Question: Define inheritance?
Difficulty: 1
Topic: java

Enter your answer: It is the mechanism in java by which one class is allow to inherit the 
features(fields and methods) of another class.
```

At the end of the evaluation you will have the grade calculated based on your answers to the MCQ questions and will be asked if you want to export your evaluation in pdf.

```
  Your grade is : x
  Do you want to export your quiz in pdf ? y/n
```

If you chose to export your quiz along with your answers and grade, you will be asked to enter the name you want to give your pdf. On successful pdf generation you will get a log telling you so.

```
  PDF Created successfully!
```

The pdf will be generated in the main directory of the program.
