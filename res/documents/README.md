# CS 5010 Semester Project

This repo represents the coursework for CS 5010, the Spring 2022 Edition!

**Name:** Aashi Shrimal, Gowtham Potnuru

**Email:** shrimal.a@northeastern.edu, potnuru.g@northeastern.edu

**Preferred Name:** Aashi, Gowtham

### About/Overview

This is a full scale MVC application created using the Java Swing framework for the View, 
the model is configured using a text file. The application is built using good design patterns
and best practices. The functionality of the MVC application is tested end to end using the 
JUnit framework.

### List of Features

This MVC application is a desktop based game inspired by the Kill Dr Lucky. Below are some of the 
salient features:

1. The game is defined by a number of spaces which make a world.
2. Every space has a set of items.
3. The game has a Target and a Pet whose location changes after every turn a player takes.
4. A maximum of 10 players can be added to the game.
5. Every player gets a turn in the sequence they were added.
6. The objective of the game is kill the target using the items they carry.
7. The game is tied if no player is able to kill the target after the set number of turns.
8. The player visibility is affected based on the pet's location in the world.
9. A player's attack can nullified by other players if they can see each other.

### Player Activities

Below are the options a player can perform when their turn comes up:

1. **Move** :
   A player can move to the neighbouring space. If one does an invalid move, the operation fails.
2. **Look Around** :  
   The player is given an option to view the details of the current space they are in and the neighbouring space as well.
   Information about the items, pet presence and target presence is displayed.
3. **Pick Item** :  
   Allows a player to pick an item from the current space they occupy.
4. **Move Pet** :   
   Allows a player to move the pet to a desired space to assist them to attack the target.
5. **Attack Target** :  
   Allows a player to attack the target with an item they are carrying or choose to poke the target
   in the eye.
6. **Player Description**
   Allows a player lookup his details including the items they are carrying and the space they
   are currently in.

### How to Run

The application is a java program which comes packaged as a jar which has to be run to start the
game.

**JAR location**: ./res/cs5010-final-project-gowtham_aashi_team.jar

**Requirements to Run**:

1. The JAR file expects two command line arguments
   1. World configuration file : Contains the details of the world, including the list of spaces
      with their co-ordinates and the items in the space.
   2. The max number of turns the game allows.

**Commands to run**:  
Sample command to run the JAR file.

```aidl
java -jar cs5010-final-project-gowtham_aashi_team.jar layout1.txt 10 
```

A more generic version where `<filePath>` can be replaced by your own custom layout of the world
and `<max turns>` can be replaced by maximum allowable turn limit for the game, is shown below.

```aidl
java -jar cs5010-project-shellfish1.jar <filePath> <max turns> 
```

### Interactive Components of Program

This is a desktop game, different components of the game user can interact with are as follows:

1. **Welcome Screen** : This is the welcome screen displayed to the user, as soon as one starts the
   game.  
   ![Welcome Screen](res/documentss/WelcomeScreen.PNG)  

2. **Spawn Screen**: Once the user chooses to start the game, one has to add players. A maximum
   of 10 players (manual or computer) in any combination.
   ![Spawn Screen](res/documents/SpawnScreen.PNG)
3. **Gameplay Screen**: Once the players are added to the game, the user can begin the game
   and enter the game. The game screen has an interactive game layout where players can take turns
   to play the game. This screen also offers hints to the users and an instruction manual.
   ![Game Screen](res/documnets/GameScreen.PNG)

### How to Use the Program

1. **Start the game**: Launches the welcome screen:
2. Click on Menu
3. Select the option ***Start*** and ***Current Layout***.
4. You will be taken to the ***Add Player - spawn*** screen.
5. **Choosing a layout of your own**:  If the user wished to play the game with a custom layout, one can do so by:
   1. Click on Menu
   2. Select the option ***Start*** and ***New Layout***.
   3. A file chooser appears on screen, and you would have to select your custom layout file.
   4. You will then be taken to the ***Spawn Screen***.
6. **How can the players be added?**: Once the game starts, the players can be added. A player has to enter one's username, 
   starting location and the max item limit. Computer players can also be added. A max of 10 placcordingly.
   1. To add a new player fill the form and click ***Add***.
   2. To begin the game click on  ***Begin***
   3. You will be taken to the ***Game Screen***.
8. **How to take your turn**: All the rules for the game are displayed to the player on the gameplay
   screen.
9. **Pick Item** : Press **p**
10. **Attack Target**: Press **k**
11. **Move Player**: Click on the layout.
12. **Move Pet**: Press **m**
13. **Look Around**: Press **a**
14. **Show Player Description**: Click on player icon on the image.
16. Click on Menu
17. Select the option ***Quit Game***.
18. You will be shown the ***Welcome/Intro Screen***.

### Example Runs

A 10 minute video will be recorded and upload to canvas.

### Design/Model Changes

1. A Readonly interface is created to implement interface segregation so that the view has
   limited access to the model
2. The model now has a method re-initialize the world by providing a new layout.

### Assumptions

1. Two players cannot have the same name.
2. A computer player cannot have a custom name.
3. A player can still poke the target if they have items in their inventory.
4. The game should have atleast one player to start the game.
5. A minimum item limit 1 has to be set for the players.

### Limitations

1. The MVC application doesnot allow clickable icons of the players.
2. The application doesnot allow the players to quit in between the game.

### Citations

1. MVC Design Pattern : https://www.geeksforgeeks.org/mvc-design-pattern/.
2. JSwing Documentation : https://docs.oracle.com/javase/7/docs/api/javax/swing/package-summary.html




