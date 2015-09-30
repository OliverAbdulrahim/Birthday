package birthday;

import static birthday.EntityConstants.STARTING_LOCATION;
import static birthday.EntityConstants.V_MAX;
import static birthday.EntityConstants.V_MIN;
import java.awt.AlphaComposite;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.image.BufferedImage;
import static utilities.functions.ImageUtilities.copyImage;
import static utilities.functions.Utilities.randomColor;
import static utilities.functions.Utilities.randomInteger;

/**
 * The {@code Entity} class is an arbitrary object in a physics simulation. This
 * object encapsulates various parameters which define its properties within the 
 * simulation.
 *
 * @author Oliver Abdulrahim
 */
public class Entity {
    
    /**
     * Stores the visible component of this object.
     */
    private BufferedImage image;
    
    /**
     * Stores the point location of this object on an x-y grid.
     */
    private Point location;
    
    /**
     * Stores the velocity vector of this object.
     */
    private Point vector;
    
    /**
     * Creates an entity with a random image.
     */
    public Entity() {
        this(EntityImages.random());
    }
    
    public Entity(Point location) {
        this();
        this.location = location;
    }
    
    /**
     * Creates an entity with the given argument.
     * 
     * @param entityImage The image to use for this object.
     */
    public Entity(EntityImages entityImage) {
        this.image = filter(entityImage.getImage());
        location = STARTING_LOCATION;
        vector = new Point(randomInteger(V_MIN, V_MAX), randomInteger(V_MIN, V_MAX));
    }
    
    /**
     * Returns a filtered version of the given image using a random color as the
     * composite.
     * 
     * @param image The image to filter.
     * @return A filtered version of the given image.
     */
    private BufferedImage filter(BufferedImage image) {
        BufferedImage filtered = copyImage(image);
        int width = filtered.getWidth();
        int height = filtered.getHeight();
        
        Graphics2D g = (Graphics2D) filtered.getGraphics();
        g.setColor(randomColor());
        g.fillRect(0, 0, width, height);
        g.setComposite(AlphaComposite.DstIn);
        g.drawImage(image, 0, 0, width, height, 0, 0, width, height, null);
        
        return filtered;
    }
    
    /**
     * Sets the image for this {@code Entity}.
     * 
     * @param image The image for this object.
     */
    public void setImage(BufferedImage image) {
        this.image = image;
    }
    
    /**
     * Sets the point location of this {@code Entity}.
     * 
     * @param location The point location of this object.
     */
    public void setLocation(Point location) {
        this.location = location;
    }
    
    /**
     * Sets the speed of this {@code Entity}.
     * 
     * @param vector The speed of this object.
     */
    public void setSpeed(Point vector) {
        this.vector = vector;
    }
    
    /**
     * Returns the image for this {@code Image}.
     * 
     * @return The image for this object.
     */
    public BufferedImage getImage() {
        return image;
    }
    
    /**
     * Returns the point location of this object.
     * 
     * @return The point location of this object.
     */
    public Point getLocation() {
        return location;
    }
    
    /**
     * Returns the speed vector of this object.
     * 
     * @return The speed vector of this object.
     */
    public Point getSpeed() {
        return vector;
    }
    
    /**
     * Returns the width of this entity.
     * 
     * @return The width of this entity.
     */
    public int getWidth() {
        return image.getWidth();
    }
    
    /**
     * Returns the height of this entity.
     * 
     * @return The height of this entity.
     */
    public int getHeight() {
        return image.getHeight();
    }
    
    /**
     * Updates this entity onto the given object using its current image and 
     * point location.
     * 
     * @param g The {@code Graphics} object to protect.
     */
    protected void translate(Graphics2D g) {
        Point p = getLocation();
        if (p != null) {
            g.drawImage(image, p.x, p.y, null);
        }
    }
    
}
