import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class Main {
    
    // Class-level variables for accessibility across the class
    private static String currentDateAndTime;
    private static int[] hue = new int[3];
    
    public static void main(String[] args) {
        //Components 
        JFrame window = new JFrame("Critical Thinking 3");
        JPanel menuPanel = new JPanel(new BorderLayout());
        JPanel textBoxPanel = new JPanel();
        
        //MenuBar, Menu, & MenuItems
        JMenuBar menuBar = new JMenuBar();
        JMenu menu = new JMenu("Menu");
        JMenuItem i1, i2, i3, i4;
        
        //Menu Items
        i1 = new JMenuItem("Write");
        i2 = new JMenuItem("Save");
        i3 = new JMenuItem("Change Color");
        i4 = new JMenuItem("Exit");
        
        //Add Menu to MenuBar and MenuItems to Menu
        menuBar.add(menu);
        menu.add(i1);
        menu.add(i2);
        menu.add(i3);
        menu.add(i4);
        
        //Set the menu bar in the JFrame
        window.setJMenuBar(menuBar);
        
        //Text Box
        JTextArea textBox = new JTextArea(7, 25);
        JScrollPane scrollPane = new JScrollPane(textBox);
        textBox.setEditable(false);
        
        //Add ScrollPane to textBoxPanel
        textBoxPanel.add(scrollPane);
        
        //Add menuPanel and textBoxPanel to the content pane
        window.getContentPane().setLayout(new BorderLayout());
        window.getContentPane().add(menuPanel, BorderLayout.NORTH);
        window.getContentPane().add(textBoxPanel, BorderLayout.CENTER);
        
        // Set initial color for textBoxPanel
        randomRGBValue();
        textBoxPanel.setBackground(new Color(hue[0], hue[1], hue[2]));
        
        //Action Listeners
        	// MENU ITEM 1: Write current date/time to textBox
        i1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //Get current date and time (formatted)
                currentDateAndTime = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss").format(new Date());
                //Print current date and time to textBox
                textBox.setText(currentDateAndTime);
            }
        });
        
        	// MENU ITEM 2: Save textBox contents to file
        i2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //Save current date and time to file
                try {
                    FileWriter fileWriter = new FileWriter("log.txt", true);
                    PrintWriter printWriter = new PrintWriter(fileWriter);
                    printWriter.println(currentDateAndTime);
                    printWriter.close();
                    JOptionPane.showMessageDialog(scrollPane, "File saved to 'log.txt'");
                } catch (IOException ioException) {
                    JOptionPane.showMessageDialog(scrollPane, "An error occurred. Please try again.");
                    ioException.printStackTrace();
                }
            }
        });
        
        	// MENU ITEM 3: Change RGB values for textBoxPanel and i3 menu item
        i3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Change i3's background color using the current RGB values
                i3.setOpaque(true);
                i3.setBackground(new Color(hue[0], hue[1], hue[2]));
                
                // Generate new random RGB values
                randomRGBValue();
                
                // Apply the new color to the textBoxPanel
                textBoxPanel.setBackground(new Color(hue[0], hue[1], hue[2]));
            }
        });
        
        	// MENU ITEM 4: Close window and terminate application
        i4.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        
        //Set frame size, visibility, and default close operation
        window.setSize(400, 200);
        window.setVisible(true);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        //Initial message in text box
        textBox.setText("Welcome to the Critical Thinking 3 Demo");
    }
    
    // Get Random RGB Values
    public static void randomRGBValue() {
        // New Random Object
        Random rand = new Random();
        
        //Green range
        int g = 72 + rand.nextInt(183) + 1;
        //Red range
        int r = Math.max(0, rand.nextInt(200));
        //Blue range
        int b = Math.max(0, rand.nextInt(200));
        
        // Ensures red and blue values are not higher than green values
        if (r > g) {
        	r = Math.max(0, r/2);
        }
        if (b > g) {
        	b = Math.max(0, b/2);
        }
        
        //Get random ints for rgb
        hue[0] = r;
        hue[1] = g;
        hue[2] = b;
    }
}
