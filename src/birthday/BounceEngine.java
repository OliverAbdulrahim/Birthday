package birthday;

import static birthday.EntityConstants.TIME_STEP_MILLIS;
import java.awt.Point;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.SwingUtilities;

/**
 * The {@code BounceEngine} class contains a simple two-dimensional physics
 * simulation. Objects contained within this class can move across a 2D plane 
 * and bounce off the edges of the plane.
 * 
 * @author Oliver Abdulrahim
 */
public class BounceEngine
    implements Runnable
{
    
    /**
     * The parent that contains the entities of this engine.
     */
    private final EntityContainer parent;

    /**
     * Attaches a new BounceEngine to the given container of entities.
     * 
     * @param parent The parent of this engine.
     */
    public BounceEngine(EntityContainer parent) {
        this.parent = parent;
    }

    /**
     * Infinitely translates all entities associated with the parent of this
     * object depending on their current speed.
     */
    @Override
    public void run() {
        int width = getWidth();
        int height = getHeight();
        parent.getEntities().stream().forEach((e) -> {
            int x = random(width);
            int y = random(height);
            if (x + e.getWidth() > width) {
                x = width - e.getWidth();
            }
            if (y + e.getHeight() > height) {
                y = height - e.getHeight();
            }
            e.setLocation(new Point(x, y));
        });
        while (parent.isVisible()) {
            SwingUtilities.invokeLater(parent::repaint);
            parent.getEntities().stream().forEach((ball) -> {
                move(ball);
            });
            try {
                Thread.sleep(TIME_STEP_MILLIS);
            } 
            catch (InterruptedException ex) {
                Logger.getLogger(Birthday.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    public static int random(int maxRange) {
        return (int) Math.round((Math.random() * maxRange));
    }
    
    /**
     * Returns the width of the parent this object is attached to.
     *
     * @return The width of this object's parent.
     */
    public int getWidth() {
        return parent.getWidth();
    }

    /**
     * Returns the height of the parent this object is attached to.
     *
     * @return The height of this object's parent.
     */
    public int getHeight() {
        return parent.getHeight();
    }

    /**
     * Translates the given entity using its current speed vector, point
     * location, and image.
     *
     * @param e The entity to translate.
     */
    public void move(Entity e) {
        Point p = e.getLocation();
        Point speed = e.getSpeed();
        int dx = speed.x;
        int dy = speed.y;
        int x = p.x;
        int y = p.y;
        // Bounce if at the edge of the panel
        if (x + dx < 0 || x + e.getWidth() + dx > getWidth()) {
            dx *= -1;
        }
        if (y + dy < 0 || y + e.getHeight() + dy > getHeight()) {
            dy *= -1;
        }
        x += dx;
        y += dy;
        e.setSpeed(new Point(dx, dy));
        e.setLocation(new Point(x, y));
    }

}
