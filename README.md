# CS 5010 Semester Project

This repo represents the coursework for CS 5010, the Fall 2022 Edition!

**Name:** Gowtham Potnuru

**Email:** potnuru.g@northeastern.edu

**Preferred Name:** Gowtham



### About/Overview

The problem statement is about representing a board game using a config file 
which has the ability any defined world which has spaces and items



### List of Features

The program can read a text file and based on the text file can initiate a game with a world,
spaces and items inside along with a target. 
Thg program can display the information of a specified space.
The program can display the number spaces visible or neighbours of a specified space.
The program can move the target around the world.



### How to Run

Run the jar file from the command line.
java -jar <jar file path> <config file path>



### How to Use the Program

The current model provides the ability to create a graphical representation of 
the world provided using the createGraphicalRepresentation() method.
List of neighbours of a specific space can be listed using the getNeighboursOf() method.
The information about a specific space can be viewed using the getInfoOf() method.
The target of the can be moved from the current position using the moveTarget() method.



### Example Runs

Milestone1_ExampleRun1.txt




### Design/Model Changes

Milestone1
- Removed the singleton class as I felt that multiple game be played simultaneously 
and there is no resource which causes a deadlock.
- I removed the characterIndices map field to track the position of character 
and other possible players in future as there is no requirement as of today.
- I moved the position of my target class from Game class to World class.
- I removed the Unit class which makes up the grid as there is no 
requirement applicable using this class and a simple integer in the grid works well.




### Assumptions

- A game is not singleton class.
- No two spaces can have the same name.
- The position of the target goes back to initial position at the end of last index.
- There has to be at least 1 row and col in the world.
- The damage of an item cannot be negative.
- A space can exist without an item in it.
- Item with same name can exist in different spaces.



### Limitations

As per the current requirements there are no limitations in the implemented model.



### Citations

None



