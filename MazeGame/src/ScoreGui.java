
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class ScoreGui extends JDialog implements ActionListener {

    private JLabel mainLabel;
    private ArrayList<String> list = new ArrayList<String>();

    public ScoreGui() {
        super();
    }

    public void showHighScores() {
        Container cp = getContentPane();
        JButton ok = new JButton("OK");
        ok.setActionCommand("OK");
        ok.addActionListener(this);

        cp.add(ok, BorderLayout.SOUTH);

        String[] scoreArray = new String[100];
        for (int i = 0; i < scoreArray.length; i++) {
            scoreArray[i] = " ";
        }

        File file = new File();
        list = file.readFile("scores.txt");

        ArrayList<Player> players = convertToPlayer();

        getHighScore(players);

        ArrayList<Player> orderedScores = getHighScore(players);

        JPanel scorePanel = new JPanel();
        scorePanel.setLayout(new GridLayout(orderedScores.size(), orderedScores.size()));
        for (int m = 0; m < orderedScores.size(); m++) {
            mainLabel = new JLabel(players.get(m).toString(), JLabel.LEFT);
            System.out.println(players.get(m));
            scorePanel.add(mainLabel);
        }
        cp.add(scorePanel);
        pack();
        setVisible(true);
        setLocationRelativeTo(null);

    }

    public ArrayList<Player> getHighScore(ArrayList<Player> players) {
        Collections.sort(players, new Comparator<Player>() {
            @Override
            public int compare(Player o1, Player o2) {
                return o1.getMinutes().compareTo(o2.getMinutes());
            }
        });

        return players;
    }

    public ArrayList<Player> convertToPlayer() {
        ArrayList<Player> players = new ArrayList<>();

        for (int i = 0; i < list.size(); i++) {
            String[] sentence = list.get(i).split("/");

            if (sentence.length == 3) {
                String nameString = sentence[0];
                String namePlayer = splitName(nameString);

                String timeString = sentence[1];
                int minutes = splitTime(timeString, 0);
                int seconds = splitTime(timeString, 1);

                String levelString = sentence[2];
                int level = splitLevel(levelString);

                players.add(new Player(namePlayer, minutes, seconds, level));
            }
        }

        return players;
    }

    public String splitName(String nameString) {
        String[] sentence = nameString.split(":");
        String namePlayer = "";

        if (sentence.length == 2) {
            namePlayer = sentence[1];
        }
        return namePlayer;
    }

    public int splitTime(String timeString, int part) {
        String[] sentence = timeString.split(":");
        int time = 0;

        if (sentence.length == 4) {
            if (part == 0) {
                time = Integer.parseInt(sentence[1].trim());
            } else {
                String number1 = sentence[2].charAt(0) + "";
                String number2 = sentence[2].charAt(1) + "";

                time = Integer.parseInt(number1.concat(number2));
            }
        }

        return time;
    }

    public int splitLevel(String levelString) {
        String[] sentence = levelString.split(":");
        int level = 0;

        if (sentence.length == 2) {
            level = Integer.parseInt(sentence[1].trim());
        }

        return level;
    }

    public void actionPerformed(ActionEvent e) {
        dispose();
    }
}
