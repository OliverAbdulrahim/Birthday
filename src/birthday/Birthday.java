package birthday;

import static birthday.EntityConstants.BIRTHDAY_PERSON;
import static birthday.EntityConstants.BIRTH_DATE;
import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.EventObject;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BorderFactory;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JSeparator;
import javax.swing.LayoutStyle;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.WindowConstants;
import static utilities.functions.Utilities.diffBetweenNow;

/**
 * 
 * @author Oliver Abdulrahim
 */
public class Birthday 
    extends JFrame
{
    
    /**
     * I really did mash the keyboard to get this value.
     */
    private static final long serialVersionUID = 189273091L;
    
    private EntityContainer entities;
    private final JLabel birthdayLabel;
    private final JMenuBar menuBar;
    private final JMenu fileMenu;
    private final JMenuItem fileMenuReset;
    private final JMenuItem fileMenuClear;
    private final JSeparator fileMenuSeparator;
    private final JMenuItem fileMenuExit;
    private final JMenu helpMenu;
    private final JMenuItem helpMenuItem;
    private final JFrame helpFrame;
    private final JPanel helpContainer;
    private final JLabel helpLabel;
    private final JButton helpExitButton;

    public static void main(String[] args) {
        // Sets the system look and feel
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } 
        catch (ClassNotFoundException | InstantiationException
             | IllegalAccessException | UnsupportedLookAndFeelException ex) {
        Logger.getLogger(Birthday.class.getName())
            .log(Level.SEVERE,
                    "Error with look and feel settings. "
                  + "Check if look and feels are installed correctly",
                    ex);
        }
        new Birthday();
    }

    public Birthday() {
        helpFrame = new JFrame();
        helpContainer = new JPanel();
        helpLabel = new JLabel();
        helpExitButton = new JButton();
        birthdayLabel = new JLabel("", JLabel.CENTER);
        entities = new EntityContainer();
        fileMenu = new JMenu();
        fileMenuReset = new JMenuItem();
        fileMenuClear = new JMenuItem();
        fileMenuSeparator = new JPopupMenu.Separator();
        fileMenuExit = new JMenuItem();
        helpMenu = new JMenu();
        helpMenuItem = new JMenuItem();
        menuBar = new JMenuBar();
        
        setTitle("Happy Birthday, " + BIRTHDAY_PERSON + '!');
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        
        birthdayLabel.setFont(new Font("Tahoma", Font.BOLD, 16));

        entities.setLayout(new GridLayout());
        entities.add(birthdayLabel, BorderLayout.CENTER);
        add(entities);
        
        fileMenuReset.setText("Reset");
        fileMenuReset.addActionListener(e -> entities.reset());
        fileMenu.add(fileMenuReset);
        
        fileMenuClear.setText("Clear");
        fileMenuClear.addActionListener(e -> entities.clear());
        fileMenu.add(fileMenuClear);
        
        fileMenu.add(fileMenuSeparator);
        
        fileMenuExit.setText("Exit");
        fileMenuExit.addActionListener(e -> System.exit(0));
        fileMenu.setText("File");
        fileMenu.add(fileMenuExit);
        
        helpMenu.setText("Help");
        helpMenuItem.setText("Help");
        helpMenuItem.addActionListener(e -> helpFrame.setVisible(true));
        helpMenu.add(helpMenuItem);
        
        menuBar.add(fileMenu);
        menuBar.add(helpMenu);
        setJMenuBar(menuBar);
        
        helpFrame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        helpFrame.setTitle("Help");
        helpFrame.setLocationByPlatform(true);
        helpFrame.setResizable(false);

        helpContainer.setBorder(BorderFactory.createTitledBorder("About This Program"));

        helpLabel.setText("<html><p>Welcome to the birthday program, " 
                + BIRTHDAY_PERSON + "! I wish you a really happy birthday! "
                + "<br><br>Some features: <ol>"
                + "<li>Left click to get a gift"
                + "<li>Right click to get rid of a gift"
                + "<li>Middle click to get rid of all your gifts :("
                + "</ol></p></html>");

        helpExitButton.setText("OK");
        helpExitButton.setToolTipText("P.S. : You're getting old!");
        helpExitButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent evt) {
                helpFrame.dispose();
            }
        });

        GroupLayout helpContainerLayout = new GroupLayout(helpContainer);
        helpContainer.setLayout(helpContainerLayout);
        helpContainerLayout.setHorizontalGroup(
            helpContainerLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(helpContainerLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(helpLabel, GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(helpContainerLayout.createSequentialGroup()
                .addGap(106, 106, 106)
                .addComponent(helpExitButton)
                .addContainerGap(111, Short.MAX_VALUE))
        );
        helpContainerLayout.setVerticalGroup(
            helpContainerLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(helpContainerLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(helpLabel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(helpExitButton)
                .addContainerGap(25, Short.MAX_VALUE))
        );

        GroupLayout helpFrameLayout = new GroupLayout(helpFrame.getContentPane());
        helpFrame.getContentPane().setLayout(helpFrameLayout);
        helpFrameLayout.setHorizontalGroup(
            helpFrameLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(helpFrameLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(helpContainer, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        helpFrameLayout.setVerticalGroup(
            helpFrameLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(helpFrameLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(helpContainer, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        helpFrame.pack();
        
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent evt) {
                if (SwingUtilities.isRightMouseButton(evt)) {
                    removeRandomEntity(evt);
                }
                else if (SwingUtilities.isMiddleMouseButton(evt)) {
                    clearEntities(evt);
                }
                else {
                    spawnNewEntity(evt);
                }
            }
        });
        
        setSize(1000, 800);
        setVisible(true);

        SwingUtilities.invokeLater(() -> {
            new Thread((() -> updateBirthdayLabel())).start();
            new Thread(new BounceEngine(entities)).start();
        });
    }
    
    /**
     * Updates the birthday label to reflect the current time.
     */
    private void updateBirthdayLabel() {
        while (true) {
            String birthday = 
                    "<html>"
                      + "<h2><center>Happy Birthday, " + BIRTHDAY_PERSON + "!</center></h2>"
                      + "<p>You are " + diffBetweenNow(BIRTH_DATE) + " old!</p>"
                  + "</html>";
            birthdayLabel.setText(birthday);
        }
    }
    
    /**
     * Attempts to remove a random entity if possible at call time.
     * 
     * @param evt The originating remove request.
     */
    private void removeRandomEntity(MouseEvent evt) {
        if (!entities.remove()) {
            invalidInputReceived(evt);
        }
    }
    
    /**
     * Spawns a new entity if the maximum has not already been reached.
     * 
     * @param evt The event containing the coordinates for the new entity.
     */
    private void spawnNewEntity(MouseEvent evt) {
        Point clickLocation = new Point(evt.getX(), evt.getY());
        boolean spawned = entities.spawn(clickLocation);
        if (!spawned) {
            invalidInputReceived(evt);
        }
    }
    
    /**
     * Removes all entities from the interface.
     */
    private void clearEntities(MouseEvent evt) {
        entities.clear();
    }
    
    /**
     * Handles an invalid input event.
     */
    private void invalidInputReceived(EventObject evt) {
        Toolkit.getDefaultToolkit().beep();
    }
    
}
