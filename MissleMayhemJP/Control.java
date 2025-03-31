package MissleMayhemJP;

import javafx.fxml.FXML;
import javafx.animation.*;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.image.ImageView;
import javafx.scene.image.Image;
import javafx.geometry.Insets;
import javafx.scene.paint.Color;
import javafx.scene.layout.VBox;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Button;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.ComboBox;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Circle;
import java.text.DecimalFormat;
import java.io.File;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.util.Duration;
import javafx.scene.Node;
import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import java.util.HashMap;
import java.util.Map;
import javafx.collections.ObservableList;

/********************************************************************************************************************************************************
 * Missle Mayhem is a simple 2D game that has been adapted for the streaming world. 
 * The players need to survive missiles strikes on random locations on the map. 
 * The last surviving player or popularly known as the last man standing is the winner and gets VBucks as a reward 
 * If the player, wins and Victory Crown the prize is 1500 VBucks, if just win then it's 750 VBucks
 * 
 * ------------------------------------------------------------>  Control  <------------------------------------------------------------------------
 * This is the control class of the project. Here all methods that make the game functional are declared and used
 * 
 * NB: The term "user" may appear frequently and represents the "host streamer(Admin)" who operates the game
 *
 * @author (Anyanwu Benedict Chukwuemeka | 3753658, Fady Elgohary | 3762991, Kidus Gashaw | 3767966)
 * @version (v1)
 ********************************************************************************************************************************************************/
public class Control
{
    // instance data
    @FXML
    private final int WIN_HEIGHT = 900;
    private final int WIN_WIDTH = 1600;
    
    @FXML
    private String videoPath = "MissleMayhemJP/images/startpagevid.mp4";
    
    @FXML
    private Media startScreenVid;
    
    @FXML
    private MediaPlayer startScreenMediaPlayer;
    
    @FXML
    private MediaView startScreenMediaView;
    
    @FXML
    private VBox welcomeBox;

    @FXML
    private VBox playerSelectionBox;

    @FXML
    private Button startGameBtn;

    @FXML
    private Label numberOfPlayersLabel;

    @FXML
    private TextField numberOfPlayersField;
    
    @FXML
    private Label noOfPlayerWarning;

    @FXML
    private Button submitButton;
    
    @FXML
    private int numberOfPlayers;
    
    @FXML
    private VBox playerNamesRequestBox;
    
    @FXML
    private Button backToPLNoButton;
    
    @FXML
    private Button yesButton;
    
    @FXML
    private Button noButton;
    
    @FXML
    private VBox playerNameSettingsBox;
    
    @FXML
    private Label plNameWarn;
    
    @FXML
    private Button submitPLBtn;
    
    @FXML
    private List<String> listOfPLNames;
    
    @FXML
    private AnchorPane gameInterfaceBox;
    
    @FXML
    private String gameMapPicPath = "";
    
    @FXML
    private ScrollPane playerCardBdr;
    
    @FXML
    private VBox playerCardBox;
    
    @FXML
    private Label playersLeft;
    
    @FXML
    private Label locationsLeft;
    
    @FXML
    private AnchorPane gameMapLayout;
    
    @FXML
    private List<String> addPLNamesList;
    
    @FXML
    private List<String> addPLLocList;

    @FXML
    private ComboBox<String> playerDropdown;

    @FXML
    private ComboBox<String> locationDropdown;

    @FXML
    private VBox addPlayerBox;
    
    @FXML
    private AnchorPane addPLToLocBdr;
    
    @FXML
    private List<String> playerClrCardList = new ArrayList<>(Arrays.asList("#ff0000", "#e6ff04", "#00ff0d", "#00fff2", "#006eff",
                                                            "#0026ff", "#a200ff", "#ff00f2", "#6200ff", "#23800c", "#801111"));
    
    @FXML
    private Map<String, Color> playerColorMap;
    
    @FXML
    private Map<String, String> playerLocations = new HashMap<>();

    @FXML
    private Label addPLToLocWarn;

    @FXML
    private Button addPLToLocBtn;
    
    @FXML
    private AnchorPane removePLToLocBdr;
    
    @FXML
    private List<String> removePLNamesList = new ArrayList<>();
            
    @FXML
    private List<String> removePLLocList = new ArrayList<>();
    
    @FXML
    private Map<String, String> playerUsedMap;

    @FXML
    private ComboBox<String> removePlDropdown;

    @FXML
    private Label removePLToLocWarn;

    @FXML
    private Button removePLToLocBtn;
    
    @FXML
    private AnchorPane reqEndGameBdr;
    
    @FXML
    private AnchorPane reqStartGameBdr;

    @FXML
    private Label reqStartGameWarn;
                                                            
    @FXML
    private int numberOfRoundsPlayed = 0;
    
    @FXML
    private AnchorPane callRoundBdr;

    @FXML
    private Button addPLBtn;

    @FXML
    private Button removePLBtn;

    @FXML
    private Button startRnDBtn;

    @FXML
    private Button endGameBtn;
    
    /**********************************************************************************************************************************************************
     * Method Name: initialize
     * Purpose: First method ran from the start of the program. Initializes and loads on files, methods and attributes
     **********************************************************************************************************************************************************/
    @FXML
    public void initialize()
    {
        // This sets the video on autoplay and looped in background on the start screen
        startScreenVid = new Media(new File(videoPath).toURI().toString());
        startScreenMediaPlayer = new MediaPlayer(startScreenVid);
        startScreenMediaPlayer.setAutoPlay(true);
        startScreenMediaPlayer.setCycleCount(MediaPlayer.INDEFINITE);
        startScreenMediaView.setMediaPlayer(startScreenMediaPlayer);
        
        // Puts the Welcome Box infront
        welcomeBox.toFront();
        
        // Initially hides playerSelectionBox
        playerSelectionBox.setVisible(false);
        playerSelectionBox.toBack();
        
        // Initially hides playerNameSettings
        playerNameSettingsBox.setVisible(false);
        playerNameSettingsBox.toBack();
        
        // Game Interface is hidden
        gameInterfaceBox.setVisible(false);
        gameInterfaceBox.toBack();
    }

    /**********************************************************************************************************************************************************
     * Method Name: welcomeGame
     * Purpose: Listens to mouseclick event and removes the welcome modal with fade and transition effects.
     * input parameters: event
     **********************************************************************************************************************************************************/
    @FXML
    private void welcomeGame(ActionEvent event)
    {
        // Disable Start button
        startGameBtn.setDisable(true);
        
        // Fade out welcomeBox
        FadeTransition fadeOutTransition = new FadeTransition(Duration.seconds(1), welcomeBox);
        fadeOutTransition.setFromValue(1);
        fadeOutTransition.setToValue(0);

        // Slide left
        TranslateTransition slideLeftTransition = new TranslateTransition(Duration.seconds(1), welcomeBox);
        slideLeftTransition.setToX(-800);

        // Combine fade out and slide left transitions
        ParallelTransition fadeAndSlideTransition = new ParallelTransition(fadeOutTransition, slideLeftTransition);

        // Set event handler to execute when transitions finish
        fadeAndSlideTransition.setOnFinished(e ->
        {
            welcomeBox.setVisible(false);
            fadeInPlayerSelectionBox();
        });

        fadeAndSlideTransition.play();
    }
    
    /**********************************************************************************************************************************************************
     * Method Name: fadeInPlayerSelectionBox
     * Purpose: Listens to mouseclick event and removes the welcome modal with fade and transition effects.
     * input parameters: event
     **********************************************************************************************************************************************************/
    @FXML
    private void fadeInPlayerSelectionBox()
    {
        // Fade in playerSelectionBox
        FadeTransition fadeInTransition = new FadeTransition(Duration.seconds(1), playerSelectionBox);
        fadeInTransition.setFromValue(0);
        fadeInTransition.setToValue(1);
        
        // Bring the player selection box to front
        welcomeBox.toBack();
        playerSelectionBox.toFront();
        playerSelectionBox.setVisible(true);
        numberOfPlayersField.setDisable(false);
        
        fadeInTransition.play();
    }

    /**********************************************************************************************************************************************************
     * Method Name: submitNumberOfPlayers
     * Purpose: Listens to mouseclick event and validates input in the textfield against the following conditions
     *          a) Number of players cannot be less than 2
     *          b) Number of players cannot be greater than 11
     *          It also (for adaptability and better User Interface(UI) experience) reduces the width of the borders when number of players are greater than 7
     *          This is done because numbers beyond 7 trigger the scrollbar of the scrollpane and the scrollbar possesses it's own  width causing unprecendented
     *          issues with the UI
     * input parameters: event
     **********************************************************************************************************************************************************/
    @FXML
    private void submitNumberOfPlayers(ActionEvent event)
    {
        try
        {
            numberOfPlayers = Integer.parseInt(numberOfPlayersField.getText());
            if (numberOfPlayers < 2 || numberOfPlayers > 11)
            {
                throw new PlayerNoRangeException();
            }
            if(numberOfPlayers > 7)
            {
                // decrease width of card box
                playerCardBox.setStyle("-fx-pref-width: 435px");
            }
            
            // Clear warning
            noOfPlayerWarning.setText("");
    
            // Disable TextField and button to prevent further input
            numberOfPlayersField.setDisable(true);
            submitButton.setDisable(true);
    
            // Fade out the playerSelectionBox
            FadeTransition fadeOutTransition = new FadeTransition(Duration.seconds(1), playerSelectionBox);
            fadeOutTransition.setFromValue(1);
            fadeOutTransition.setToValue(0);
    
            // Slide right transition
            TranslateTransition slideRightTransition = new TranslateTransition(Duration.seconds(1), playerSelectionBox);
            slideRightTransition.setToX(800);
    
            // Combine fade out and slide right transitions
            ParallelTransition fadeAndSlideTransition = new ParallelTransition(fadeOutTransition, slideRightTransition);
    
            // Set event handler to execute when transitions finish
            fadeAndSlideTransition.setOnFinished(e ->
            {
                // Hide the playerSelectionBox
                playerSelectionBox.setVisible(false);
    
                // Display the PlayerNamesRequestBox
                playerNamesRequestBox.setVisible(true);
            });
    
            // Start the fade-out and slide right animations
            fadeAndSlideTransition.play();
        }
        catch (NumberFormatException e)
        {
            // Input is not a valid integer
            noOfPlayerWarning.setText("Invalid character");
        }
        catch (PlayerNoRangeException e)
        {
            // Input is not a valid integer between 2 and 11
            noOfPlayerWarning.setText(e.getMessage());
        }
    }
    
    /**********************************************************************************************************************************************************
     * Method Name: goBackToSelectNoOfPL
     * Purpose: Allows user to return back to the previous modal for the user's own reason
     * input parameters: event
     **********************************************************************************************************************************************************/
    @FXML
    private void goBackToSelectNoOfPL(ActionEvent event)
    {
        // Hide the PlayerNamesRequestBox
        playerNamesRequestBox.setVisible(false);
        
        // Display the playerSelectionBox
        playerSelectionBox.setVisible(true);
        
        // Enable buttons
        yesButton.setDisable(false);
        noButton.setDisable(false);
        
        // Fade out the playerNameSettingsBox
        FadeTransition fadeOutTransition = new FadeTransition(Duration.seconds(1), playerSelectionBox);
        fadeOutTransition.setFromValue(0);
        fadeOutTransition.setToValue(1);
    
        // Slide right transition
        TranslateTransition slideRightTransition = new TranslateTransition(Duration.seconds(1), playerSelectionBox);
        slideRightTransition.setToX(0);
    
        // Combine fade out and slide right transitions
        ParallelTransition fadeAndSlideTransition = new ParallelTransition(fadeOutTransition, slideRightTransition);
        
        // Set event handler to execute when transitions finish
        fadeAndSlideTransition.setOnFinished(e ->
        {
            // Enable TextField and button
            numberOfPlayersField.setDisable(false);
            submitButton.setDisable(false);
        });
    
        // Start the fade-out and slide right animations
        fadeAndSlideTransition.play();
    }
    
    /**********************************************************************************************************************************************************
     * Method Name: useDefaultNames
     * Purpose: Fills up the player List for a default set of names i.e "player1", "player2" e.t.c.
     **********************************************************************************************************************************************************/
    @FXML
    private void useDefaultNames()
    {
        // Disable button
        backToPLNoButton.setDisable(true);
        yesButton.setDisable(true);
        noButton.setDisable(true);
        
        // Fade out the playerNamesRequestBox
        FadeTransition fadeOutTransition = new FadeTransition(Duration.seconds(1), playerNamesRequestBox);
        fadeOutTransition.setFromValue(1);
        fadeOutTransition.setToValue(0);
    
        // Slide left transition
        TranslateTransition slideLeftTransition = new TranslateTransition(Duration.seconds(1), playerNamesRequestBox);
        slideLeftTransition.setToX(-800);
    
        // Combine fade out and slide left transitions
        ParallelTransition fadeAndSlideTransition = new ParallelTransition(fadeOutTransition, slideLeftTransition);
    
        // Set event handler to execute when transitions finish
        fadeAndSlideTransition.setOnFinished(e ->
        {
            // Hide the playerNamesRequestBox
            playerNamesRequestBox.setVisible(false);
            
            listOfPLNames = new ArrayList<>();
            for (int i = 1; i <= numberOfPlayers; i++)
            {
                listOfPLNames.add("player" + i);
            }
            instantiateGameInterface(listOfPLNames);
        });
    
        // Start the fade-out and slide left animations
        fadeAndSlideTransition.play();
    }

    /**********************************************************************************************************************************************************
     * Method Name: useCustomNames
     * Purpose: Calls the method to allow user enter preferred names for players
     * input parameters: event
     **********************************************************************************************************************************************************/
    @FXML
    private void useCustomNames(ActionEvent event)
    {
        // Disable button
        backToPLNoButton.setDisable(true);
        yesButton.setDisable(true);
        noButton.setDisable(true);
        
        // Fade out the playerNamesRequestBox
        FadeTransition fadeOutTransition = new FadeTransition(Duration.seconds(1), playerNamesRequestBox);
        fadeOutTransition.setFromValue(1);
        fadeOutTransition.setToValue(0);
    
        // Slide left transition
        TranslateTransition slideLeftTransition = new TranslateTransition(Duration.seconds(1), playerNamesRequestBox);
        slideLeftTransition.setToX(-800);
    
        // Combine fade out and slide left transitions
        ParallelTransition fadeAndSlideTransition = new ParallelTransition(fadeOutTransition, slideLeftTransition);
    
        // Set event handler to execute when transitions finish
        fadeAndSlideTransition.setOnFinished(e ->
        {
            // Hide the playerNamesRequestBox
            playerNamesRequestBox.setVisible(false);
    
            // Create and display the playerNameSettingsBox
            playerNameSettingsBox.setVisible(true);
    
            // Create and populate the playerNameSettingsBox
            createPlayerNameSettings();
        });
    
        // Start the fade-out and slide left animations
        fadeAndSlideTransition.play();
    }
    
    /**********************************************************************************************************************************************************
     * Method Name: createPlayerNameSettings
     * Purpose: Creates a custom sized modal containing textfields to allow user enter their preferred choice of names for the players
     **********************************************************************************************************************************************************/
    @FXML
    private void createPlayerNameSettings()
    {
        // Calculating the required topAnchor position based on total number of players participating\
        // Variables
        int initialTopAnchorPosition = 420;
        int baseheightOfBox = 306;
        double newHeightOfBox = 0;
        int initialNumberOfPlayers = 2;
        int heightChangePerPlayer = 55;
        double topAnchorPosition = 0;
        
        // Calculate the top anchor position based on the number of players
        newHeightOfBox = baseheightOfBox + ((numberOfPlayers - initialNumberOfPlayers) * heightChangePerPlayer);
        topAnchorPosition = initialTopAnchorPosition - (newHeightOfBox / 2);
        
        
        // Unhide the playerNameSettings
        playerNameSettingsBox.toFront();
        playerNameSettingsBox.setVisible(true);
        playerNameSettingsBox.setOpacity(1);
        
        // Set the top anchor position for the createPlayerNameSettingsBox
        AnchorPane.setTopAnchor(playerNameSettingsBox, topAnchorPosition);
                
        // Create the nameSettingsTitleBox
        HBox nameSettingsTitleBox = new HBox();
        nameSettingsTitleBox.getStyleClass().add("nameSettingsTitleBox");
        
        Label playerLabel = new Label("Player");
        playerLabel.getStyleClass().add("nameSettingsTitle");
        
        Label nameLabel = new Label("Name");
        nameLabel.getStyleClass().add("nameSettingsTitle");
        
        nameSettingsTitleBox.getChildren().addAll(playerLabel, nameLabel);
    
        // Add title box to playerNameSettingsBox
        playerNameSettingsBox.getChildren().add(nameSettingsTitleBox);
    
        // Create and add player name input fields
        for(int i = 1; i <= numberOfPlayers; i++)
        {
            HBox playerRow = new HBox();
            playerRow.getStyleClass().add("playerNameBox");
            
            Label playerNumberLabel = new Label("Player " + i);
            playerNumberLabel.getStyleClass().add("playerName");
            
            TextField playerNameField = new TextField();
            playerNameField.getStyleClass().add("genTextField");
            playerNameField.setPromptText("Enter player " + i + "'s name");
            
            playerRow.getChildren().addAll(playerNumberLabel, playerNameField);
            playerNameSettingsBox.getChildren().add(playerRow);
        }
        
        // warning text
        plNameWarn = new Label("Please fill out all the spaces above");
        plNameWarn.setId("plNameWarn");
        plNameWarn.getStyleClass().add("noOfPlayerWarning");
        playerNameSettingsBox.getChildren().add(plNameWarn);
        
        // Add the action btn box
        HBox actionBtnBox = new HBox();
        actionBtnBox.getStyleClass().add("genModalBtnBox");
        
        // Create the buttons
        Button closeBtn = new Button("Go back");
        closeBtn.getStyleClass().add("genButton");
        closeBtn.setOnAction(event -> goBackToSelectNameType());
        
        Button updateBtn = new Button("Continue");
        updateBtn.getStyleClass().add("genButton");
        updateBtn.setOnAction(event -> validatePLnames());
        
        actionBtnBox.getChildren().addAll(closeBtn, updateBtn);
        
        // Add action btn box to VBox
        playerNameSettingsBox.getChildren().add(actionBtnBox);
    }
    
    /**********************************************************************************************************************************************************
     * Method Name: goBackToSelectNameType
     * Purpose: Allows user to return back to the previous modal for the user's own reason.
     **********************************************************************************************************************************************************/
    @FXML
    private void goBackToSelectNameType()
    {
        // Hide the playerNameSettingsBox and Clear all data inside
        playerNameSettingsBox.setVisible(false);
        playerNameSettingsBox.getChildren().clear();
        
        // unhide playerNamesRequestBox
        playerNamesRequestBox.setVisible(true);
        
        // Fade in the playerNameSettingsBox
        FadeTransition fadeOutTransition = new FadeTransition(Duration.seconds(1), playerNamesRequestBox);
        fadeOutTransition.setFromValue(0);
        fadeOutTransition.setToValue(1);
    
        // Slide right transition
        TranslateTransition slideRightTransition = new TranslateTransition(Duration.seconds(1), playerNamesRequestBox);
        slideRightTransition.setToX(0);
    
        // Combine fade in and slide right transitions
        ParallelTransition fadeAndSlideTransition = new ParallelTransition(fadeOutTransition, slideRightTransition);
        
        // Set event handler to execute when transitions finish
        fadeAndSlideTransition.setOnFinished(e ->
        {
            // Enable buttons
            backToPLNoButton.setDisable(false);
            yesButton.setDisable(false);
            noButton.setDisable(false);
        });
    
        // Start the fade-in and slide right animations
        fadeAndSlideTransition.play();
    }
    
    /**********************************************************************************************************************************************************
     * Method Name: validatePLnames
     * Purpose: A validation method that validates the inputed values in textfield for the player names against three conditions.
     *          a) Textfield cannot be empty
     *          b) Characters must not be less than 3
     *          c) Characters must not be greater than 20
     * input parameters: playerNames
     **********************************************************************************************************************************************************/
    @FXML
    private void validatePLnames()
    {
        boolean isValid = true;
        listOfPLNames = new ArrayList<>();
    
        // Iterate through all player name text fields
        for (Node node : playerNameSettingsBox.getChildren())
        {
            if (node instanceof HBox)
            {
                HBox playerRow = (HBox) node;
                for (Node innerNode : playerRow.getChildren())
                {
                    if (innerNode instanceof TextField)
                    {
                        TextField playerNameField = (TextField) innerNode;
                        String playerName = playerNameField.getText().trim();
                        
                        // Check if the player name field is empty
                        if (playerName.isEmpty())
                        {
                            plNameWarn.setText("Please fill out all fields");
                            isValid = false;
                            break;
                        }
                        
                        // Check if player name is less than three(3) characters
                        if (playerName.length() < 3)
                        {
                            plNameWarn.setText("Ensure that a player's name is not less than '3' characters");
                            isValid = false;
                            break;
                        }
                        
                        // Check if player name is greater than twenty(20) characters
                        if (playerName.length() > 20)
                        {
                            plNameWarn.setText("Ensure that a player's name is not greater than '20' characters");
                            isValid = false;
                            break;
                        }
                    }
                }
            }
        }
    
        // If all inputs are valid,add the names to the List and call instantiateGameInterface
        if (isValid)
        {
            plNameWarn.setText("");
            for (Node node : playerNameSettingsBox.getChildren())
            {
                if (node instanceof HBox)
                {
                    HBox playerRow = (HBox) node;
                    for (Node innerNode : playerRow.getChildren())
                    {
                        if (innerNode instanceof TextField)
                        {
                            TextField playerNameField = (TextField) innerNode;
                            playerNameField.setDisable(true);
                            
                            String playerName = playerNameField.getText().trim();
                            listOfPLNames.add(playerName);
                        }
                    }
                }
            }
            instantiateGameInterface(listOfPLNames);
        }
    }
    
    /**********************************************************************************************************************************************************
     * Method Name: instantiateGameInterface
     * Purpose: Creates the gameInterface where user can add and remove players, start and end the game.
     * input parameters: playerNames
     **********************************************************************************************************************************************************/
    @FXML
    private void instantiateGameInterface(List<String> playerNames)
    {
        // Fade out playerNameSettingsBox
        FadeTransition fadeOutTransition = new FadeTransition(Duration.seconds(0.5), playerNameSettingsBox);
        fadeOutTransition.setFromValue(1);
        fadeOutTransition.setToValue(0);
        
        // Slide left
        TranslateTransition slideLeftTransition = new TranslateTransition(Duration.seconds(1.0), playerNameSettingsBox);
        slideLeftTransition.setToX(-800);
        
        // Combine fade out and slide left transitions
        ParallelTransition fadeAndSlideTransition = new ParallelTransition(fadeOutTransition, slideLeftTransition);
        
        // Set event handler to execute when transitions finish
        fadeAndSlideTransition.setOnFinished(e ->
        {
            // Hide playerNameSettingsBox and clear entries
            playerNameSettingsBox.setVisible(false);
            playerNameSettingsBox.setOpacity(0);
            playerNameSettingsBox.getChildren().clear();
            
            // Stop playing the media
            startScreenMediaPlayer.stop();
            
            // Clear the media player and media objects
            startScreenMediaPlayer.dispose();
            startScreenVid = null;
            
            // Clear existing player cards
            playerCardBox.getChildren().clear();
            
            // Get the list of colors from playerClrCardList
            List<String> tempClrCardList = new ArrayList<>();
            tempClrCardList.addAll(playerClrCardList);
            
            // Add list of players to add list
            addPLNamesList = new ArrayList<>();
            addPLNamesList.addAll(playerNames);;
            
            // Add the locations to add list
            addPLLocList = new ArrayList<>();
            gameMapLayout.getChildren().forEach(node -> 
            {
                if (node instanceof AnchorPane) 
                {
                    addPLLocList.add(node.getId());
                }
            });
            
            // Add player cards with names  
            playerColorMap = new HashMap<>();
            for (String playerName : playerNames)
            {
                // Create a card for each player
                AnchorPane playerCard = new AnchorPane();
                playerCard.getStyleClass().add("player_Card");
                playerCard.setId(playerName + "_id");
                
                // Add the player's color
                Color playerCLr = getRandomColor(tempClrCardList);
                Circle playerCircle = new Circle(10);
                playerCircle.setId(playerName + "_clrId");
                playerCircle.setFill(playerCLr);
                
                // Add the player's name
                Label cardName = new Label(playerName);
                cardName.getStyleClass().add("cardName");
                
                // Add location
                Label cardLocation = new Label("Not Set");
                cardLocation.getStyleClass().add("cardLocation");
                
                // Add Nodes to the player card
                playerCard.getChildren().addAll(playerCircle, cardName, cardLocation);
                AnchorPane.setTopAnchor(playerCircle, 17.0);
                AnchorPane.setLeftAnchor(playerCircle, 0.0);
                playerCard.setTopAnchor(cardName, 15.5);
                playerCard.setLeftAnchor(cardName, 25.0);
                playerCard.setTopAnchor(cardLocation, 15.0);
                playerCard.setRightAnchor(cardLocation, 0.0);
                
                // Add player Crd to the player Card box
                playerCardBox.getChildren().add(playerCard);
                
                // Add name and color to colormap
                playerColorMap.put(playerName, playerCLr);
                
                // Add name and location to locationmap
                playerLocations.put(playerName, cardLocation.getText());
            }
            
            // Set the number of Players in the title Bar
            playersLeft.setText("[" + numberOfPlayers +"]");
            
            // Fade in game interface
            gameInterfaceBox.setVisible(true);
            FadeTransition fadeInTransition = new FadeTransition(Duration.seconds(1), gameInterfaceBox);
            fadeInTransition.setFromValue(0);
            fadeInTransition.setToValue(1);
            fadeInTransition.play();
            gameInterfaceBox.toFront();
        });
        
        // Play the fade and slide transition
        fadeAndSlideTransition.play();
    }
    
    /**********************************************************************************************************************************************************
     * Method Name: getRandomColor
     * Purpose: It takes a random color from the list of colors, removes it from the list and returns it to where it is called
     **********************************************************************************************************************************************************/
    @FXML
    private Color getRandomColor(List<String> colors)
    {
        Random rand = new Random();
        int index = rand.nextInt(colors.size());
        String colorStr = colors.remove(index);
        return Color.web(colorStr);
    }
    
    /**********************************************************************************************************************************************************
     * Method Name: openAddPlBox
     * Purpose: It creates the box to allow user select the location the player's will be added to
     **********************************************************************************************************************************************************/
    @FXML
    private void openAddPlBox()
    {
        // Create anchorpane which servers as the background
        addPLToLocBdr = new AnchorPane();
        addPLToLocBdr.getStyleClass().add("genModalBdr");
        
        // Create and configure the VBox
        
        // Select Player
        VBox addPLToLocBox = new VBox();
        addPLToLocBox.getStyleClass().add("genModalBox");

        // Add text labels and dropdown menus
        Label selectPlayerLabel = new Label("Select player to be Added");
        selectPlayerLabel.getStyleClass().add("genModalTitle");
        
        // Dropdown menu for players
        playerDropdown = new ComboBox<>();
        playerDropdown.getStyleClass().add("genComboBox");
        playerDropdown.getItems().addAll(addPLNamesList);

        // Select Location
        Label selectLocationLabel = new Label("Select location");
        selectLocationLabel.getStyleClass().add("genModalTitle");
        
        // Dropdown menu for locations
        locationDropdown = new ComboBox<>();
        locationDropdown.getStyleClass().add("genComboBox");
        
        // Populate location dropdown menu from the ids of the Panes inside gameMapLayout
        locationDropdown.getItems().addAll(addPLLocList);
        
        // Add warning label
        addPLToLocWarn = new Label("");
        addPLToLocWarn.getStyleClass().add("noOfPlayerWarning");
        
        // Add the action btn box
        HBox actionBtnBox = new HBox();
        actionBtnBox.getStyleClass().add("genModalBtnBox");
        
        // Create the buttons
        Button closeBtn = new Button("Close");
        closeBtn.getStyleClass().add("genButton");
        closeBtn.setOnAction(event -> closeAddPlBox());
        
        addPLToLocBtn = new Button("Add");
        addPLToLocBtn.getStyleClass().add("genButton");
        addPLToLocBtn.setOnAction(event -> addPlayerToLocation());
        
        // Add Buttons to HBox
        actionBtnBox.getChildren().addAll(closeBtn, addPLToLocBtn);

        // Add components to the VBox
        addPLToLocBox.getChildren().addAll(selectPlayerLabel, playerDropdown, selectLocationLabel, locationDropdown, addPLToLocWarn, actionBtnBox);

        // Add the VBox to the anchor pane
        AnchorPane.setTopAnchor(addPLToLocBox, 250.0);
        AnchorPane.setLeftAnchor(addPLToLocBox, 400.0);
        AnchorPane.setRightAnchor(addPLToLocBox, 400.0);
        addPLToLocBdr.getChildren().add(addPLToLocBox);
        
        // Add anchorpane to game interface
        AnchorPane.setTopAnchor(addPLToLocBdr, 0.0);
        AnchorPane.setLeftAnchor(addPLToLocBdr, 0.0);
        AnchorPane.setRightAnchor(addPLToLocBdr, 0.0);
        gameInterfaceBox.getChildren().add(addPLToLocBdr);

        // Bring to front
        addPLToLocBdr.toFront();
    }
    
    /**********************************************************************************************************************************************************
     * Method Name: closeAddPlBox
     * Purpose: Closes and clears the openAddPlBox
     **********************************************************************************************************************************************************/
    @FXML
    private void closeAddPlBox()
    {
        // Clear the bdr and its elements from screen
        gameInterfaceBox.getChildren().remove(addPLToLocBdr);
    }
    
    /**********************************************************************************************************************************************************
     * Method Name: addPlayerToLocationBox
     * Purpose: It will add players to a Location on the map by creating a circle with the same color as that of the player's card
     **********************************************************************************************************************************************************/
    @FXML
    private void addPlayerToLocation()
    {
        String selectedPlayer = playerDropdown.getValue();
        String selectedLocation = locationDropdown.getValue();
        
        // Check if player comboBox is empty
        if (playerDropdown.getItems().isEmpty())
        {
            // Disable the addPLBtn to prevent adding more players
            addPLToLocWarn.setText("All players have been added");
            addPLToLocBtn.setDisable(true);
            
            return;
        }
    
        // Check if both comboBoxes have been selected
        if (selectedPlayer == null || selectedLocation == null)
        {
            addPLToLocWarn.setText("Please make a selection");
            return;
        }
        
        // Check the color of the selected player
        Circle playerCircle = new Circle(20);
        playerCircle.setId(selectedPlayer + "_PlMapId");
        Color playerColor = getColorForPlayer(selectedPlayer);
        
        // Create a circle with the player's col
        playerCircle.setFill(playerColor);
    
        // Find the location pane by ID
        AnchorPane selectedPane = null;
        for (Node node : gameMapLayout.getChildren())
        {
            if (node instanceof AnchorPane && node.getId().equals(selectedLocation))
            {
                selectedPane = (AnchorPane) node;
                AnchorPane playerCard = (AnchorPane) node;
                break;
            }
        }
    
        // Check if the location pane was found
        if (selectedPane == null)
        {
            addPLToLocWarn.setText("Error: Location not found");
            return;
        }
        
        // Add the player's circle to the location pane
        AnchorPane.setTopAnchor(playerCircle, 30.0);
        AnchorPane.setLeftAnchor(playerCircle, 30.0);
        selectedPane.getChildren().add(playerCircle);
    
        // Update warnText Label
        String message = selectedPlayer + " has been added to " + selectedLocation;
        addPLToLocWarn.setText(message);
        
        // Update playercard location
        updatePlayerLocationsInMap(selectedPlayer, selectedLocation);
        
        // add to remove list and Remove from added lists
        removePLNamesList.add(selectedPlayer);
        removePLLocList.add(selectedLocation);
        addPLNamesList.remove(selectedPlayer);
        addPLLocList.remove(selectedLocation);
    
        // Remove the player and location from the respective comboBoxes
        playerDropdown.getItems().remove(selectedPlayer);
        locationDropdown.getItems().remove(selectedLocation);
    }
    
    /**********************************************************************************************************************************************************
     * Method Name: getColorForPlayer
     * Purpose: Retrieves and returns the color associated with the given player name
     * input parameters: playerName
     * output parameters: Color
     **********************************************************************************************************************************************************/
    @FXML
    private Color getColorForPlayer(String playerName)
    {
        // Loop through the player cards to find the color for the given player name
        return playerColorMap.getOrDefault(playerName, Color.TRANSPARENT);
    }
    
    /**********************************************************************************************************************************************************
     * Method Name: removePlayerFromLocation
     * Purpose: Creates the RemovePlBox Pane for removing players from the map
     **********************************************************************************************************************************************************/
    @FXML
    private void removePlayerFromLocation()
    {
        // Create anchorpane which servers as the background
        removePLToLocBdr = new AnchorPane();
        removePLToLocBdr.getStyleClass().add("genModalBdr");
        
        // Create and configure the VBox
        // Select Player
        VBox removePLToLocBox = new VBox();
        removePLToLocBox.getStyleClass().add("genModalBox");

        // Add text labels and dropdown menus
        Label selectPlayerLabel = new Label("Select player to be Removed");
        selectPlayerLabel.getStyleClass().add("genModalTitle");
        
        // Dropdown menu for players
        removePlDropdown = new ComboBox<>();
        removePlDropdown.getStyleClass().add("genComboBox");
        removePlDropdown.getItems().addAll(removePLNamesList);
        
        // Add warning label
        removePLToLocWarn = new Label("");
        removePLToLocWarn.getStyleClass().add("noOfPlayerWarning");
        
        // Add the action btn box
        HBox actionBtnBox = new HBox();
        actionBtnBox.getStyleClass().add("genModalBtnBox");
        
        // Create the buttons
        Button closeBtn = new Button("Close");
        closeBtn.getStyleClass().add("genButton");
        closeBtn.setOnAction(event -> closeRemovePLBox());
        
        removePLToLocBtn = new Button("Remove");
        removePLToLocBtn.getStyleClass().add("genButton");
        removePLToLocBtn.setOnAction(event -> updateRemoveLocation());
        
        // Add Buttons to HBox
        actionBtnBox.getChildren().addAll(closeBtn, removePLToLocBtn);

        // Add components to the VBox
        removePLToLocBox.getChildren().addAll(selectPlayerLabel, removePlDropdown, removePLToLocWarn, actionBtnBox);

        // Add the VBox to the anchor pane
        AnchorPane.setTopAnchor(removePLToLocBox, 300.0);
        AnchorPane.setLeftAnchor(removePLToLocBox, 400.0);
        AnchorPane.setRightAnchor(removePLToLocBox, 400.0);
        removePLToLocBdr.getChildren().add(removePLToLocBox);
        
        // Add anchorpane to game interface
        AnchorPane.setTopAnchor(removePLToLocBdr, 0.0);
        AnchorPane.setLeftAnchor(removePLToLocBdr, 0.0);
        AnchorPane.setRightAnchor(removePLToLocBdr, 0.0);
        gameInterfaceBox.getChildren().add(removePLToLocBdr);

        // Bring to front
        removePLToLocBdr.toFront();
    }
    
    /**********************************************************************************************************************************************************
     * Method Name: closeRemovePlBox
     * Purpose: Closes and clears the openRemovePlBox
     **********************************************************************************************************************************************************/
    @FXML
    private void closeRemovePLBox()
    {
        // Clear the bdr and its elements from screen
        gameInterfaceBox.getChildren().remove(removePLToLocBdr);
    }
    
    /**********************************************************************************************************************************************************
     * Method Name: updateRemoveLocation
     * Purpose: This removes the selected player from the map and updates their current location.
     **********************************************************************************************************************************************************/
    @FXML
    private void updateRemoveLocation()
    {
        String selectedPlayer = removePlDropdown.getValue();
        String selectedLocation = "";
        
        // Check if the comboBox is empty
        if (removePlDropdown.getItems().isEmpty())
        {
            // Disable the addPLBtn to prevent adding more players
            removePLToLocWarn.setText("All players have been removed");
            removePLToLocBtn.setDisable(true);
            return;
        }
    
        // Check if the comboBox has been selected
        if (selectedPlayer == null)
        {
            removePLToLocWarn.setText("Please make a selection");
            return;
        }
        
        // Check the color of the selected player
        Circle selectedCircle = null;
    
        // Find the location pane by ID
        AnchorPane selectedPane = null;
        for (Node node : gameMapLayout.getChildren())
        {
            if (node instanceof AnchorPane)
            {
                selectedPane = (AnchorPane) node;
                for(Node selCircle : selectedPane.getChildren())
                {
                    if(selCircle instanceof Circle && selCircle.getId().equals(selectedPlayer + "_PlMapId"))
                    {
                        selectedCircle = (Circle) selCircle;
                        selectedPane.getChildren().remove(selectedCircle);
                        selectedLocation = selectedPane.getId();
                        break;
                    }
                }
            }
        }
    
        // Check if the location pane was found
        if (selectedPane == null)
        {
            addPLToLocWarn.setText("Error: Location not found");
            return;
        }
        
        // Remove circle
        selectedPane.getChildren().remove(selectedCircle);
    
        // Update warnText Label
        String message = selectedPlayer + " has been removed from " + selectedLocation;
        removePLToLocWarn.setText(message);
        
        // Update playercard location
        updatePlayerLocationsInMap(selectedPlayer, "Not Set");
        
        // Remove from remove lists and add to added lists
        addPLNamesList.add(selectedPlayer);
        addPLLocList.add(selectedLocation);
        removePLNamesList.remove(selectedPlayer);
        removePLLocList.remove(selectedLocation);
    
        // Remove the player and location from the respective comboBoxes
        removePlDropdown.getItems().remove(selectedPlayer);
    }
    
    /**********************************************************************************************************************************************************
     * Method Name: updatePlayerLocationsInMap
     * Purpose: Takes in the player name and location and updates it on the hashmap.
     * input parameters: thisPlyr, thisLoc
     **********************************************************************************************************************************************************/
    @FXML
    private void updatePlayerLocationsInMap(String thisPlyr, String thisLoc)
    {
        // Iterate over player cards in UI
        for (Node node : playerCardBox.getChildren())
        {
            if (node instanceof AnchorPane && node.getId().equals(thisPlyr + "_id") )
            {
                AnchorPane selectedPane = (AnchorPane) node;
                for(Node selLoc : selectedPane.getChildren())
                {
                    if(selLoc instanceof Label && selLoc.getStyleClass().contains("cardLocation"))
                    {
                        Label selectedLabel = (Label) selLoc;
                            
                        // Update location label in player card
                        selectedLabel.setText(thisLoc);
                        
                        // Update The location map
                        String location = playerLocations.getOrDefault(thisPlyr, "Not Set");
                        
                        break;
                    }
                }
            }
        }
    }
    
    /**********************************************************************************************************************************************************
     * Method Name: requestEndGame
     * Purpose: Creates a modal asking for confirmation from user to end the game.
     * input parameters: playerNames
     **********************************************************************************************************************************************************/
    @FXML
    private void requestEndGame()
    {
        // Create anchorpane which servers as the background
        reqEndGameBdr = new AnchorPane();
        reqEndGameBdr.getStyleClass().add("genModalBdr");
        
        // Create and configure the VBox
        VBox reqEndGameBox = new VBox();
        reqEndGameBox.getStyleClass().add("genModalBox");

        // Confirm user's request to end game
        Label qtnLabel = new Label("Are you sure you want to end game?");
        qtnLabel.getStyleClass().add("genModalTitle");
        
        // Add the action btn box
        HBox actionBtnBox = new HBox();
        actionBtnBox.getStyleClass().add("genModalBtnBox");
        
        // Create the buttons
        Button closeBtn = new Button("No");
        closeBtn.getStyleClass().add("genButton");
        closeBtn.setOnAction(event -> closeEndGameBox());
        
        Button grantBtn = new Button("Yes");
        grantBtn.getStyleClass().add("genButton");
        grantBtn.setOnAction(event -> performEndGame());
        
        // Add Buttons to HBox
        actionBtnBox.getChildren().addAll(closeBtn, grantBtn);

        // Add components to the VBox
        reqEndGameBox.getChildren().addAll(qtnLabel, actionBtnBox);

        // Add the VBox to the anchor pane
        AnchorPane.setTopAnchor(reqEndGameBox, 400.0);
        AnchorPane.setLeftAnchor(reqEndGameBox, 400.0);
        AnchorPane.setRightAnchor(reqEndGameBox, 400.0);
        reqEndGameBdr.getChildren().add(reqEndGameBox);
        
        // Add anchorpane to game interface
        AnchorPane.setTopAnchor(reqEndGameBdr, 0.0);
        AnchorPane.setLeftAnchor(reqEndGameBdr, 0.0);
        AnchorPane.setRightAnchor(reqEndGameBdr, 0.0);
        gameInterfaceBox.getChildren().add(reqEndGameBdr);

        // Bring to front
        reqEndGameBdr.toFront();
    }
    
    /**********************************************************************************************************************************************************
     * Method Name: closeEndGameBox
     * Purpose: Closes and clears the requestEndGame modal
     **********************************************************************************************************************************************************/
    @FXML
    private void closeEndGameBox()
    {
        // Clear the bdr and its elements from screen
        gameInterfaceBox.getChildren().remove(reqEndGameBdr);
    }
    
    /**********************************************************************************************************************************************************
     * Method Name: performEndGame
     * Purpose: Ends the game and displays results.
     **********************************************************************************************************************************************************/
    @FXML
    private void performEndGame()
    {
        // 
        System.out.println("Game has Ended..");
    }
    
    /**********************************************************************************************************************************************************
     * Method Name: instantiateGameInterface
     * Purpose: Creates a modal asking user for confirmation before starting the game
     **********************************************************************************************************************************************************/
    @FXML
    private void requestStartGame()
    {
        // Create anchorpane which servers as the background
        reqStartGameBdr = new AnchorPane();
        reqStartGameBdr.getStyleClass().add("genModalBdr");
        
        // Create and configure the VBox
        VBox reqStartGameBox = new VBox();
        reqStartGameBox.getStyleClass().add("genModalBox");

        // Confirm user's request to end game
        Label qtnLabel = new Label("Are you ready to start?");
        qtnLabel.getStyleClass().add("genModalTitle");
        
        // Confirm user's request to end game
        reqStartGameWarn = new Label("");
        reqStartGameWarn.getStyleClass().add("noOfPlayerWarning");

        // Add the action btn box
        HBox actionBtnBox = new HBox();
        actionBtnBox.getStyleClass().add("genModalBtnBox");
        
        // Create the buttons
        Button closeBtn = new Button("No");
        closeBtn.getStyleClass().add("genButton");
        closeBtn.setOnAction(event -> closeRequestStartGame());
        
        Button grantBtn = new Button("Yes");
        grantBtn.getStyleClass().add("genButton");
        grantBtn.setOnAction(event -> confirmStartGame());
        
        // Add Buttons to HBox
        actionBtnBox.getChildren().addAll(closeBtn, grantBtn);

        // Add components to the VBox
        reqStartGameBox.getChildren().addAll(qtnLabel, reqStartGameWarn, actionBtnBox);

        // Add the VBox to the anchor pane
        AnchorPane.setTopAnchor(reqStartGameBox, 400.0);
        AnchorPane.setLeftAnchor(reqStartGameBox, 400.0);
        AnchorPane.setRightAnchor(reqStartGameBox, 400.0);
        reqStartGameBdr.getChildren().add(reqStartGameBox);
        
        // Add anchorpane to game interface
        AnchorPane.setTopAnchor(reqStartGameBdr, 0.0);
        AnchorPane.setLeftAnchor(reqStartGameBdr, 0.0);
        AnchorPane.setRightAnchor(reqStartGameBdr, 0.0);
        gameInterfaceBox.getChildren().add(reqStartGameBdr);

        // Bring to front
        reqStartGameBdr.toFront();
    }
    
    /**********************************************************************************************************************************************************
     * Method Name: closeRequestStartGame
     * Purpose: Closes and clears the requestStartGame modal
     **********************************************************************************************************************************************************/
    @FXML
    private void closeRequestStartGame()
    {
        // Clear the bdr and its elements from screen
        gameInterfaceBox.getChildren().remove(reqStartGameBdr);
    }
    
    /**********************************************************************************************************************************************************
     * Method Name: confirmStartGame
     * Purpose: Calls the round method to show number of rounds played and closes the request box confirming if user wishes to start round.
     * input parameters: playerNames
     **********************************************************************************************************************************************************/
    @FXML
    private void confirmStartGame()
    {
        if(addPLNamesList.size() > 0)
        {
            reqStartGameWarn.setText("Please add all players to a location");
            return;
        }
        // Call method to close it's bdr from the 
        closeRequestStartGame();
        
        // Call method to start Game
        callRoundNumber();
    }
    
    /**********************************************************************************************************************************************************
     * Method Name: callRoundNumber
     * Purpose: Creates a modal showing the number of rounds played.
     **********************************************************************************************************************************************************/
    @FXML
    private void callRoundNumber()
    {
        // Create anchorpane which servers as the background
        callRoundBdr = new AnchorPane();
        callRoundBdr.getStyleClass().add("genModalBdr");
        
        // Create and configure the VBox
        VBox callRoundBox = new VBox();
        callRoundBox.getStyleClass().add("genModalBox");

        // Text to show Number of Rounds (Increments with each round)
        numberOfRoundsPlayed++;
        Label callLabel = new Label("ROUND " + numberOfRoundsPlayed);
        callLabel.getStyleClass().add("noOfGameRounds");

        // Add components to the VBox
        callRoundBox.getChildren().add(callLabel);

        // Add the VBox to the anchor pane
        AnchorPane.setTopAnchor(callRoundBox, 350.0);
        AnchorPane.setLeftAnchor(callRoundBox, 300.0);
        AnchorPane.setRightAnchor(callRoundBox, 300.0);
        callRoundBdr.getChildren().add(callRoundBox);
        
        // Add anchorpane to game interface
        AnchorPane.setTopAnchor(callRoundBdr, 0.0);
        AnchorPane.setLeftAnchor(callRoundBdr, 0.0);
        AnchorPane.setRightAnchor(callRoundBdr, 0.0);
        gameInterfaceBox.getChildren().add(callRoundBdr);

        // Bring to front
        callRoundBdr.toFront();
        
        // Create a fade transition to fade out the modal after 2 seconds
        FadeTransition fadeOut = new FadeTransition(Duration.seconds(1), callRoundBdr);
        fadeOut.setFromValue(1.0);
        fadeOut.setToValue(0.0);
        fadeOut.setOnFinished(e -> 
        {
            gameInterfaceBox.getChildren().remove(callRoundBdr);
            toggleCtrlBtnDisable(true);
            startRound();
        });
        
        // Schedule the fade-out transition to start after 2 seconds
        Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(2), e -> fadeOut.play()));
        timeline.setCycleCount(1);
        timeline.play();
    }
    
    /**********************************************************************************************************************************************************
     * Method Name: toggleCtrlBtnDisable
     * Purpose: toggles the disable state of the control buttons. Applied during an active game round to prevent any inputs
     * input parameters: btnState
     **********************************************************************************************************************************************************/
    @FXML
    private void toggleCtrlBtnDisable(boolean btnState)
    {
        addPLBtn.setDisable(btnState);
        removePLBtn.setDisable(btnState);
        startRnDBtn.setDisable(btnState);
        endGameBtn.setDisable(btnState);
    }
    
    /**********************************************************************************************************************************************************
     * Method Name: startRound
     * Purpose: Starts the game round. All activities ventured during the game will be called from here
     **********************************************************************************************************************************************************/
    @FXML
    private void startRound()
    {
        // Starting a round
        System.out.println("Starting round..");
    }
}

