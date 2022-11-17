/* Super Smash Bros. Ultimate Character Roulette by Miki_Tellurium
 * v1.0.0
 *
 * I made this simple program to complete my World of Light Challenge
 * and keep track of what character I already used.
 * The program use a character.properties file to save the character
 * progress, if you move to a different directory remember to also
 * move the .properties file.
 * This code is probably not so good and a bit chaotic, but feel free
 * to use the program or modify if you want to.
 *
 * TO-DO:
 * -Implement text search: done
 * -Custom background
 * -Custom tooltip
 * -Character series icon: done
 */

package com.mikitellurium.smashcharacterreoulette;


import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Properties;
import java.util.Random;

public class SmashRoulette implements ActionListener, MouseListener, KeyListener {

    JFrame mainFrame = new JFrame("Super Smash Bros. Ultimate Characters Roulette");
    JPanel mainPanel = new JPanel();
    JFrame checkFrame = new JFrame("Characters Checklist");
    JPanel checkBoxPanel = new JPanel();
    GridLayout checkBoxLayout = new GridLayout(11, 9, 0, 0);
    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    final int width = (int) screenSize.getWidth();

    ArrayList<JCheckBox> boxes = new ArrayList<>();

    JButton rollButton = new JButton("Random");
    JButton refreshButton = new JButton();
    JButton checkListButton = new JButton("<html><center>" + "Characters" + "<br>" + "Checklist" + "</center></html>");
    JLabel textFieldHint = new JLabel("?");

    ImageIcon logo = new ImageIcon(Objects.requireNonNull(SmashRoulette.class.getResource("/resources/smash logo.png")));
    ImageIcon refreshIcon = new ImageIcon(Objects.requireNonNull(SmashRoulette.class.getResource("/resources/refresh button.png")));
    ImageIcon hoveringRefreshIcon = new ImageIcon(Objects.requireNonNull(SmashRoulette.class.getResource("/resources/hover refresh button.png")));
    Color notHover = new Color(255, 255, 255);
    Color hover = new Color(210, 255, 255);
    final int offset = refreshButton.getInsets().left - 14;  //This variable is chosen arbitrarily to make the refreshButton icon match

    JLabel character = new JLabel();
    JLabel credit = new JLabel("by Miki_Tellurium");
    JLabel render = new JLabel();
    JLabel symbol = new JLabel();
    Font font = character.getFont();

    HintTextField searchField = new HintTextField("Search");

    String charProp = "characters.properties";
    Properties prop = new Properties();

    public SmashRoulette() throws IOException {
        mainFrame.getContentPane();
        mainFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        mainFrame.setIconImage(logo.getImage());
        mainFrame.setSize(500, 300);
        mainFrame.setResizable(false);
        mainFrame.setLocationRelativeTo(null);

        mainPanel.setBackground(Color.WHITE);
        mainPanel.setLayout(null);

        rollButton.setOpaque(true);
        rollButton.setSize(100, 30);
        rollButton.setBorder(new RoundedBorder(5));
        rollButton.setFocusPainted(false);
        rollButton.setBackground(notHover);
        rollButton.setLocation(mainFrame.getWidth() / 2 - rollButton.getWidth() / 2, 40);
        rollButton.setFont(new Font(font.getName(), Font.BOLD, 14));
        rollButton.addActionListener(this);
        rollButton.addMouseListener(this);

        refreshButton.setOpaque(true);
        refreshButton.setSize(rollButton.getHeight(), rollButton.getHeight());
        refreshButton.setBorder(new RoundedBorder(5));
        refreshButton.setFocusPainted(false);
        refreshButton.setLocation(rollButton.getX() + rollButton.getWidth() + 5, rollButton.getY());
        refreshButton.setIcon(resizeIcon(refreshIcon, refreshButton.getWidth() - offset, refreshButton.getHeight() - offset));

        checkListButton.setSize(75, 35);
        checkListButton.setBorder(new RoundedBorder(5));
        checkListButton.setFocusPainted(false);
        checkListButton.setBackground(notHover);
        checkListButton.setLocation(mainFrame.getWidth() - checkListButton.getWidth() - 20, mainFrame.getHeight() - checkListButton.getHeight() * 2 - 8);
        checkListButton.setFont(new Font(font.getName(), Font.BOLD, 11));
        checkListButton.addActionListener(this);
        checkListButton.addMouseListener(this);

        credit.setSize(credit.getPreferredSize());
        credit.setFont(new Font(font.getName(), Font.BOLD, 12));
        credit.setLocation(mainFrame.getWidth() - credit.getWidth() - 20, 1);

        mainPanel.add(rollButton);
        mainPanel.add(refreshButton);
        mainPanel.add(checkListButton);
        mainPanel.add(credit);
        mainFrame.add(mainPanel);
        mainFrame.setVisible(true);

        checkFrame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        checkFrame.setIconImage(logo.getImage());
        checkFrame.setSize(1100, 250);
        checkFrame.setResizable(false);
        checkFrame.setLocation(width / 2 - checkFrame.getWidth() / 2, mainFrame.getY());
        checkFrame.setAlwaysOnTop(true);
        checkFrame.setLayout(null);

        searchField.setSize(120, 25);
        searchField.setLocation(2, 0);
        searchField.addKeyListener(this);
        textFieldHint.setSize(searchField.getHeight(), searchField.getHeight());
        textFieldHint.setLocation(searchField.getX() + searchField.getWidth() + 2, searchField.getY());
        textFieldHint.setBorder(new RoundedBorder(7));
        textFieldHint.setFont(new Font(font.getName(), Font.BOLD, 16));
        textFieldHint.setVerticalTextPosition(JLabel.CENTER);
        textFieldHint.setHorizontalTextPosition(JLabel.CENTER);
        textFieldHint.setToolTipText("Search a character to highlight it on the list");

        checkBoxPanel.setLayout(checkBoxLayout);
        checkBoxPanel.setSize(checkFrame.getWidth(), checkFrame.getHeight() - searchField.getHeight() - 40);
        checkBoxPanel.setLocation(0, searchField.getY() + searchField.getHeight());

        checkFrame.add(searchField);
        checkFrame.add(textFieldHint);
        checkFrame.add(checkBoxPanel);
        makeCheckBoxes();
        checkBoxInit();
    }

    /* Generate a random int and return the string corresponding the character name */
    public String getCharacterName() {
        int random;
        do {
            random = new Random().nextInt(86);
        } while (isCharacterSelected(random));
        return CharacterList.getCharacter(random);
    }

    /* Check if the checkbox is already selected */
    public boolean isCharacterSelected(int i) {
        return boxes.get(i).isSelected();
    }

    /* Return the corresponding character render */
    public ImageIcon getCharacterRender(String name) throws IOException {
        BufferedImage img = ImageIO.read(Objects.requireNonNull(SmashRoulette.class.getResource("/resources/renders/" + name + ".png")));
        Image resized = img.getScaledInstance(mainFrame.getWidth() / 2 + rollButton.getWidth() / 2, mainFrame.getHeight() - 10, Image.SCALE_DEFAULT);
        return new ImageIcon(resized);
    }

    /* Resize the icon image for the buttons */
    public Icon resizeIcon(ImageIcon icon, int resizedWidth, int resizedHeight) {
        Image img = icon.getImage();
        Image resizedImage = img.getScaledInstance(resizedWidth, resizedHeight, java.awt.Image.SCALE_SMOOTH);
        return new ImageIcon(resizedImage);
    }

    /* Fill the ArrayList with checkboxes, one for character */
    public void makeCheckBoxes() {
        for (int character = 0; character < 86; character++) {
            JCheckBox checkBox = new JCheckBox(CharacterList.getCharacter(character));
            checkBox.addActionListener(this);
            boxes.add(checkBox);
            checkBoxPanel.add(checkBox);
        }
    }

    /* Check if the properties file already exist */
    public void checkBoxInit() throws IOException {
        File file = new File(charProp);
        if (file.exists()) {
            readCheckBoxState(boxes);
        } else {
            saveCheckBoxState(boxes);
        }
    }

    /* Read the properties file to set the state of all checkboxes */
    public void readCheckBoxState(ArrayList<JCheckBox> arrayList) throws IOException {
        InputStream inputProp = new FileInputStream(charProp);
        prop.load(inputProp);
        for (JCheckBox box : arrayList) {
            box.setSelected(Boolean.parseBoolean(prop.getProperty(box.getText())));
        }
    }

    /* Generate a new properties file to save character progress */
    public void saveCheckBoxState(ArrayList<JCheckBox> arrayList) throws IOException {
        OutputStream outputProp = new FileOutputStream(charProp);
        for (JCheckBox box : arrayList) {
            prop.setProperty(box.getText(), String.valueOf(box.isSelected()));
        }
        prop.store(outputProp, "This file register the checkboxes state");
    }

    /* Check if String text contain String substring */
    public static boolean textContains(String text, String substring) {
        if (text == null) {
            return false;
        }
        if (substring == null | Objects.equals(substring, "")) {
            return false;
        }
        char[] fullString = text.toCharArray();
        char[] sub = substring.toCharArray();
        int counter = 0;
        if (sub.length == 0) {
            return true;
        }
        for (char c : fullString) {
            if (c == sub[counter]) {
                counter++;
            } else {
                counter = 0;
            }
            if (counter == sub.length) {
                return true;
            }
        }
        return false;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == rollButton) {
            rollButton.removeMouseListener(this);
            rollButton.setEnabled(false);
            rollButton.setBackground(new Color(225, 225, 225));
            mainPanel.add(character);
            character.setText(getCharacterName());
            character.setFont(new Font(font.getName(), Font.BOLD, 32));
            character.setForeground(new Color(0, 0, 0));
            character.setSize(character.getPreferredSize());
            character.setLocation(mainFrame.getWidth() / 2 - character.getWidth() / 2, rollButton.getY() + rollButton.getHeight() + 20);
            mainPanel.add(render);
            mainPanel.add(symbol);
            try {
                render.setSize(300, 300);
                render.setLocation(-30, -10);
                render.setIcon(getCharacterRender(character.getText()));
                symbol.setSize(170, 170);
                symbol.setLocation(rollButton.getX() + rollButton.getWidth() + 20, 25);
                symbol.setIcon(resizeIcon(CharacterList.getSeriesSymbol(character.getText()), symbol.getWidth(), symbol.getHeight()));
            } catch (IOException ex) {
                ex.printStackTrace();
            }
            refreshButton.addActionListener(this);
            refreshButton.addMouseListener(this);
            refreshButton.setEnabled(true);
        } else if (e.getSource() == refreshButton) {
            rollButton.addMouseListener(this);
            rollButton.setEnabled(true);
            rollButton.setBackground(notHover);
            character.setText("");
            refreshButton.setIcon(resizeIcon(refreshIcon, refreshButton.getWidth() - offset, refreshButton.getHeight() - offset));
            refreshButton.removeActionListener(this);
            refreshButton.removeMouseListener(this);
            refreshButton.setEnabled(false);
        } else if (e.getSource() == checkListButton) {
            checkFrame.setVisible(true);
        } else if (e.getSource() instanceof JCheckBox box) {
            try {
                OutputStream outputProp = new FileOutputStream(charProp);
                prop.setProperty(box.getText(), String.valueOf(box.isSelected()));
                prop.store(outputProp, "This file register the checkboxes state");
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {
        if (e.getSource() == rollButton) {
            rollButton.setBackground(hover);
        } else if (e.getSource() == refreshButton) {
            refreshButton.setIcon(resizeIcon(hoveringRefreshIcon, refreshButton.getWidth() - offset, refreshButton.getHeight() - offset));
        } else if (e.getSource() == checkListButton) {
            checkListButton.setBackground(hover);
        }
    }

    @Override
    public void mouseExited(MouseEvent e) {
        if (e.getSource() == rollButton) {
            rollButton.setBackground(notHover);
        } else if (e.getSource() == refreshButton) {
            refreshButton.setIcon(resizeIcon(refreshIcon, refreshButton.getWidth() - offset, refreshButton.getHeight() - offset));
        } else if (e.getSource() == checkListButton) {
            checkListButton.setBackground(notHover);
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
    }

    @Override
    public void keyReleased(KeyEvent e) {
        String name = searchField.getText();
        for (JCheckBox box : boxes) {
            if (textContains(box.getText(), name)) {
                box.setForeground(Color.RED);
            } else {
                box.setForeground(Color.BLACK);
            }
        }
    }
}


