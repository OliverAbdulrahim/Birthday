package birthday;

import static birthday.EntityConstants.DEFAULT_ENTITY_COUNT;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.RenderingHints;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import javax.swing.JPanel;
import static utilities.functions.Utilities.r;

/**
 * Stores an arbitrary amount of entities.
 * 
 * @author Oliver Abdulrahim
 * @see Entity
 */
public class EntityContainer 
    extends JPanel
{
    
    private static final long serialVersionUID = 12639012L;
    
    /**
     * Stores all entities contained within this object.
     */
    private List<Entity> entities;
    
    /**
     * Constructs an object of this class with the default amount of entities.
     */
    public EntityContainer() {
        this(DEFAULT_ENTITY_COUNT);
    }
    
    /**
     * Constructs an object of this class with the specified amount of entities.
     * 
     * @param count The amount of entities present in this container.
     */
    public EntityContainer(int count) {
        reset(count);
    }
    
    /**
     * Adds an entity to this container if possible at this time, returning 
     * {@code true} if an entity was added, {@code false} otherwise.
     * 
     * @param location The location to add the new entity.
     * @return {@code true} if an entity was added, {@code false} otherwise.
     */
    public boolean spawn(Point location) {
        return entities.size() < EntityConstants.MAX_ENTITY_COUNT
             ? entities.add(new Entity(location))
             : false;
    }
    
    /**
     * Attempts to remove a random entity from this container if possible at
     * this time, returning {@code true} if an entity was removed, {@code false}
     * otherwise.
     * 
     * @return {@code true} if an entity was removed, {@code false} otherwise.
     */
    public boolean remove() {
        if (entities.size() > 0) {
            entities.remove(r.nextInt(entities.size()));
            return true;
        }
        return false;
    }
    
    /**
     * Clears all entities contained within this object.
     */
    public void clear() {
        entities.clear();
    }
    
    /**
     * Resets the entities contained within this object to their default state.
     */
    public void reset() {
        reset(DEFAULT_ENTITY_COUNT);
    }
    
    /**
     * Resets the entities contained within this object to their default state.
     * 
     * @param count The desired amount of entities in the object after the reset
     *        operation completes.
     */
    public final void reset(int count) {
        entities = Stream.generate(Entity :: new)
                         .limit(count)
                         .collect(Collectors.toList());
    }
    
    /**
     * Updates all entities contained within this object.
     * 
     * @param g The {@code Graphics} object to protect
     */
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g.create();
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        entities.stream().forEach((e) -> {
            e.translate(g2d);
        });
        g2d.dispose();
    }
    
    /**
     * Returns a list containing the entities contained within this object.
     * 
     * @return A list containing the entities contained within this object.
     */
    public List<Entity> getEntities() {
        return Collections.unmodifiableList(entities);
    }
    
}
