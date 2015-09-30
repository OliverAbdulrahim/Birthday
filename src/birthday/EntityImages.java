package birthday;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.util.EnumSet;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import static utilities.functions.Utilities.randomInteger;

/**
 * The {@code EntityImages} enumeration is a structure for constant values 
 * relating to the {@code EntityImages} class, including standard objects with
 * pre-defined physical properties.
 * 
 * @author Oliver Abdulrahim
 */
public enum EntityImages {
    
    BLOB("Blob", getImage("blob")),
    
    BALLOON("Balloon", getImage("balloon")),
    
    BIRTYDAY_CAKE("Birthday Cake", getImage("birthdaycake")),
    
    CONFETTI("Confetti", getImage("confetti")),
    
    GIFT_BOX("Gift Box", getImage("giftbox")),
    
    PARTY_HAT("Party Hat", getImage("partyhat")),
    
    RIBBON("Ribbon", getImage("ribbon")),
    
    SHORTCAKE("Shortcake", getImage("shortcake"));
    
    /**
     * Provides for a set of all entities enumerated in this class.
     */
    public static final EnumSet<EntityImages> ALL = EnumSet.allOf(EntityImages.class);
    
    /**
     * Used for identifying the object and for display purposes in graphical 
     * interfaces.
     */
    private final String name;
    
    /**
     * Contains this object's image for use in a graphical interface.
     */
    private final BufferedImage image;
    
    /**
     * Returns the name of this {@code EntityImages}.
     * 
     * @return The name of this object.
     */
    public String getName() {
        return name;
    }
    
    /**
     * Returns the image for this {@code EntityImages}.
     * 
     * @return The image for this object.
     */
    public BufferedImage getImage() {
        return image;
    }
    
    /**
     * Creates an object of this class with the given arguments.
     * 
     * @param name The name of this entity.
     * @param image The image associated with this entity.     
     */
    private EntityImages(String name, BufferedImage image) {
        this.name = name;
        this.image = image;
    }

    /**
     * Returns the image for the given enumeration.
     * 
     * @param name The name of the enumeration whose image to retrieve.
     * @return The image for the given enumeration.
     */
    private static BufferedImage getImage(String name) {
        BufferedImage image = null;
        try {
            InputStream is = EntityImages.class.getResourceAsStream(
                    "/resources/images/" + name + ".png");
            image = ImageIO.read(is);
        } 
        catch (IOException ex) {
            Logger.getLogger(EntityImages.class.getName()).log(Level.SEVERE, 
                    "Could not read image for name \"" + name + '.', ex);
        }
        return image;
    }
    
    /**
     * Returns a random enumeration from this class.
     * 
     * <p> This method is not particularly random, but still creates a cool
     * deterministic effect.
     * 
     * @return A random enumeration defined in this class.
     */
    public static final EntityImages random() {
        return ALL.stream()
                  .skip(randomInteger(0, ALL.size() - 1))
                  .findAny()
                  .get();
    }
    
}
