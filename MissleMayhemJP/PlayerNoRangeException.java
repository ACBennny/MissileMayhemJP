package MissleMayhemJP;

/********************************************************************************************************************************************************
 * Missle Mayhem is a simple 2D game that has been adapted for the streaming world. 
 * The players need to survive missiles strikes on random locations on the map. 
 * The last surviving player or popularly known as the last man standing is the winner and gets VBucks as a reward 
 * If the player, wins and Victory Crown the prize is 1500 VBucks, if just win then it's 750 VBucks
 * 
 *  This class is a custom built exception class which is called to the condition: "Players selected must be between 2 and 11"
 *
 * @author (Anyanwu Benedict Chukwuemeka | 3753658, Fady Elgohary | 3762991, Kidus Gashaw | 3767966)
 * @version (v1)
 ********************************************************************************************************************************************************/
public class PlayerNoRangeException extends Exception
{
    public PlayerNoRangeException()
    {
        super("Please enter a number between 2 and 11");
    }
    
    public PlayerNoRangeException(String message)
    {
        super(message);
    }
}
