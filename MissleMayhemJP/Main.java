package MissleMayhemJP;

import java.io.FileInputStream;
import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.image.ImageView;
import javafx.scene.image.Image;
import javafx.scene.Scene;
import javafx.stage.Stage;

/********************************************************************************************************************************************************
 * Designing a simple game for use in the streaming world
 * 
 * Title: Missle Mayhem
 * 
 * About:
 * Missle Mayhem is a simple 2D game that has been adapted for the streaming world. 
 * The players need to survive missiles strikes on random locations on the map. 
 * The last surviving player or popularly known as the last man standing is the winner and gets VBucks as a reward 
 * If the player, wins and Victory Crown the prize is 1500 VBucks, if just win then it's 750 VBucks
 * 
 * 
 * There are four files that serve as the core pillars by which this game is built
 *
 * 1. Main(the class that you're reading this note from)
 *      This is the main class (javafx class) of the Package.
 *      It is where the scene and stage are instatiated.
 *      Running this class will start the program containing the calculator.
 *      
 * 2. Control
 *      This is the 'controller' class (java class) of the MissleMayhemJP package.
 *      It essentialy contains all necessary methods required for the functionality of the calculator
 *      
 * 3. Struct
 *      This is a FXML document. It is similar to the standard HTML documents used for web development in terms of schema and structure.
 *      It primarily serves as the 'skeleton' of the game. This means it holds the structure by which the calculator is built upon
 *      
 * 4. Style
 *      This is a cascading stylesheet or populary known as CSS sheet. As the name implies, it's soley made for styling the game 
 *      and giving it the unique design seen once the program has been started.
 *      
 * NB: 
 * 1. The Style and Struct files are located in the 'src' folder.
 * 2. Images used in the creation of this project are not created or in any manner affiliated with us, except if stated otherwise. 
 *    All credits got to their respective owners.
 * 3. This project was made using the blueJ IDE
 * 
 * 
 * 
 *  ------------------------------------------------------------->  Main Class  <-------------------------------------------------------------------
 * 
 *
 * @author (Anyanwu Benedict Chukwuemeka | 3753658, Fady Elgohary | 3762991, Kidus Gashaw | 3767966)
 * @version (v1)
 ********************************************************************************************************************************************************/
public class Main extends Application
{
    // Instance data
    // They have all been set to final as they will never change throughout the entire duration of the program
    
    // Game Title
    private final String GAME_TITLE = "Missle Mayhem";
    
    // This are the links to the FXML file (Game structure) , CSS Sheet (Game's styling) and the application's Icon
    private final String GAME_STRUCT = "src/struct.fxml";
    private final String GAME_STYLE = "src/style.css";
    private final String GAME_ICON = "images/icon.jpg";
    
    // These are the dimensions of the game's window.
    private final int WIN_HEIGHT = 900;
    private final int WIN_WIDTH = 1600;

    /*********************************************************************************
     * Method Name: start
     * Purpose: 1. Instantiates the application
     *          2. Throws Exception for all files loaded in this project in case of load errors
     *          3. Instantiates and creates the scene and stage of this project
     ********************************************************************************/
    @Override
    public void start(Stage stage) throws Exception
    {
        // FXML File
        // The Parent class is used to locate and load the FXML document
        Parent root = FXMLLoader.load(getClass().getResource(GAME_STRUCT));
        
        // Scene
        // Creating the scene for the calculator
        Scene myScene = new Scene(root);
        
        // StyleSheet
        // We need to load the stylesheet for the calculator
        myScene.getStylesheets().add(getClass().getResource(GAME_STYLE).toExternalForm());
        
        // Application Title
        // This is the data that will appear in the title bar located at the top of the window
        stage.setTitle(GAME_TITLE);
        
        // Application Icon
        // This the visual logo that uniquely identifies this application. It is a image file of type jpg
        stage.getIcons().add(new Image(Main.class.getResourceAsStream(GAME_ICON)));
        
        // Application Window Size
        
        // Minimum Window Size - This is done to prevent window overlap with the calculator itself
        stage.setMinHeight(WIN_HEIGHT);
        stage.setMinWidth(WIN_WIDTH);
        
        // Resizing Window - This is set to false as changing the window's size can ruin the aspect ratio of the game.
        stage.setResizable(false);
        
        // Starting Window Size - When the application first runs, the window is set to fit the complete size of the current device which it is run from
        stage.setMaximized(false);
        
        // Selecting the scene - Sets the scene for the stage
        stage.setScene(myScene);

        // Shows the Stage - It shows the currently selected stage
        stage.show();
    }
}
