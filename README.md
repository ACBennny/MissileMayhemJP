# MissileMayhemJP

### Summary
A simple JavaFX game for my school project.
This project involved the production of a simple game that can be efficiently used on a stream. This was done with Java, JavaFX, FXML and CSS, and built with the blueJ IDE.


### Game progress updates
I have finished developing the base of the game. There are functionalities to allow selection of number of players and add custom usernames (optional).
Player spawn locations can be set and are marked by the color codes. However, the mechanics for in-game rounds are still in development.







## 
## Game Description
Title: Missle Mayhem

Author: Anyanwu Benedict Chukwuemeka (https://github.com/ACBennny)

Minor Contributors(initial game idea): Fady Elgohary, Kidus Gashaw

Version: 1

About: Missle Mayhem is a simple 2D game that has been adapted for the streaming world. The players need to survive missiles strikes on random locations on the map. The last surviving player or popularly known as the last man standing is the winner and gets VBucks as a reward. If the player, wins and Victory Crown the prize is 1500 VBucks, if just win then it's 750 VBucks.

Web version: https://github.com/ACBennny/MissileMayhem

Components:
1. Main:
This is the main class (javafx class) of the Package.
It is where the scene and stage are instatiated.
Running this class will start the program containing the calculator.

2. Control:
This is the 'controller' class (java class) of the MissleMayhemJP package.
It essentialy contains all necessary methods required for the functionality of the calculator

3. Struct:
This is a FXML document. It is similar to the standard HTML documents used for web development in terms of schema and structure.
It primarily serves as the 'skeleton' of the game. This means it holds the structure by which the calculator is built upon

4. Style:
This is a cascading stylesheet or populary known as CSS sheet. As the name implies, it's soley made for styling the game 
and giving it the unique design seen once the program has been started.
      
NB: 
- The Style and Struct files are located in the 'src' folder.
- Media(images, videos) used in the creation of this project are not created or in any manner affiliated with us, except if stated otherwise. All credits got to their respective owners.
- This project was made using the blueJ IDE


## 
## Game setup
1. You need to download all the files. It doesn't run on the web (that's a different project: https://github.com/ACBennny/MissileMayhem).
2. Once downloaded, open the package.bluej file (Kindly note that you may need to install the blueJ IDE to run this as it's the one I used).
3. Locate the **Main** and *right click* on it. In the context menu that appears, hit "Run JavaFX Application" (should be the first option).
4. A small pop-up window should appear asking if you would like BlueJ to run all future method invocations in this project on the JavaFX thread to avoid threading exceptions. Select "Use JavaFX thread".
5. It should take a few seconds to initialize the virtual machine, after which the game opens.

Note:
- Sometimes, the background video fails to load leaving a black background instead. In this scenario, close the window and repeat steps 3 - 5 till it loads.
- The game design is not responsive and is fixed at 1600px by 900px. You might need to adjust your screen resolution to fit the game screen.




## 
## Loading Game
1. Select the number of players you want from the modal that appear. Minimum of two (2) and Max eleven (11)
2. You can choose to create custom names for the players (names of 3 to 20 characters), or you can go with default names (Player 1, Player 2, etc.).
3. You should be taken to the game interface. On the right side is the game map, while the left contains the list of players and some action buttons at the bottom.
4. To add a player to the map, click the "Add Player" button then select the location and the player you want to be added to that location
5. To remove a player, click the "Remove Player" button and select the player you want to be removed.
6. To end the game, click the "End Game" button and select yes on the confirmation that appears after. "Game has Ended.." will be logged in the terminal window (solely for testing)
7. To start a game round, click the "Start Game" and select yes on the confirmation that appears after. The game only starts after ALL players have been added to a location. "Starting Round.." will be logged in the terminal window (solely for testing).

Further steps will be added as game development progresses.
