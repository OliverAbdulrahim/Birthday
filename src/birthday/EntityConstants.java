package birthday;

import java.awt.Point;
import java.time.LocalDateTime;
import static utilities.functions.Utilities.randomInteger;

/**
 * This interface defines a set of constants related to the {@code birthday}
 * package, allowing for easy tuning of variables.
 * 
 * @author Oliver Abdulrahim
 */
public interface EntityConstants {
    
    /**
     * Defines the person whose birthday it is.
     */
    String BIRTHDAY_PERSON = "John Cena";
    
    /**
     * Stores the birth date of the {@link #BIRTHDAY_PERSON}.
     */
    LocalDateTime BIRTH_DATE = LocalDateTime.of(1977, 4, 23, 0, 0, 0);
    
    /**
     * Defines a random starting point for an entity on an arbitrary x-y grid.
     */
    Point STARTING_LOCATION = new Point(randomInteger(0, 100), randomInteger(0, 100));
    
    /**
     * Defines the minimum speed for an entity.
     */
    int V_MIN = 1;
    
    /**
     * Defines the maximum speed for an entity.
     */
    int V_MAX = 5;
    
    /**
     * Defines the default amount of entities present in this container.
     */
    int DEFAULT_ENTITY_COUNT = 75;
    
    /**
     * Defines the maximum allowed number of entities.
     */
    int MAX_ENTITY_COUNT = DEFAULT_ENTITY_COUNT << 4;
    
    /**
     * Defines the time step between translations and image repaints.
     */
    int TIME_STEP_MILLIS = 10;
}
