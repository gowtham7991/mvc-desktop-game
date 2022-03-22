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
The program lets user add a normal player
The programm lets user to add a computer controlled player
The program allows the user to move around the world.
The program allows the user to pick up an item from the current space.
The program allows the user to look around the current space.
The program allows the user to read a specific player's description
The program allows the user to generate a layout of the game



### How to Run

Run the jar file from the command line.
java -jar <jar file path> <config file path> <Max turns per game>


### How to Use the Program

The current model provides the ability to create a graphical representation of
the world provided using the createGraphicalRepresentation() method.
List of neighbours of a specific space can be listed using the getNeighboursOf() method.
The information about a specific space can be viewed using the getInfoOf() method.
The target of the can be moved from the current position using the moveTarget() method.

The game can be played from any command line by running the jar file.
Once the game starts, the program allows the user to add players to the game.
Once all the players are added, the game can be started by using the start command.

Once the game starts, based on the order of players, each player can choose from a set of commands
available.
If the player is a computer controlled player, the program automatically runs the player.

The program also allows the players to quit the game.

After the max turns are completed the game is automatically stopped.


### Example Runs

Milestone1_ExampleRun1.txt
Milestone2_ExampleRun2.txt

### Design/Model Changes

Milestone1
- Removed the singleton class as I felt that multiple game be played simultaneously
  and there is no resource which causes a deadlock.
- I removed the characterIndices map field to track the position of character
  and other possible players in future as there is no requirement as of today.
- I moved the position of my target class from Game class to World class.
- I removed the Unit class which makes up the grid as there is no
  requirement applicable using this class and a simple integer in the grid works well.

Milestone2
- I removed the character interface and made seperate interface for target and player.



### Assumptions

- A game is not singleton class.
- No two spaces can have the same name.
- The position of the target goes back to initial position at the end of last index.
- There has to be at least 1 row and col in the world.
- The damage of an item cannot be negative.
- A space can exist without an item in it.
- Item with same name can exist in different spaces.

Milestone2
- Player with the same name can be added to the game.
- Computer player has an max item limit 5
- Game cannot start without a single human player
- Name is not needed for a computer player.
- If no neighbours and players makes a move, then the turn is up.
- If no items are found in the space and the user picksup, the turn is completed.
- The user cannot move to the same space he is in currently.


### Limitations

As per the current design, I have not provided the ability to enter a name for computer players.
There is no way for a user to reenter a new command once the command is chosen.
There is re prompt if an invalid action takes place.



### Citations

None


