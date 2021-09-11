
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class GameGui extends JFrame implements ActionListener {

    private int ix;
    private int jx;
    private int timeLeft;
    private String fileMaze;
    private String fileImage;
    private File file;
    private int catFileName = 01;
    private Container cp;
    private Maze maze = new Maze();
    private JMenuBar menuBar;
    private JMenu newMenu;
    private JMenuItem itemEnterName;
    private JMenuItem itemHighScore;
    private JMenuItem itemSaveScore;
    private JMenuItem itemExit;
    private JLabel label;
    private JPanel timeBar;
    private JLabel[][] labelMatrix;
    private TimeCalculator timeCalc;
    private JProgressBar progressBar;
    private mazeObject mazeImages;
    private JPanel newPanel;
    private Control remoteControl = new Control();
    private String[][] backupMatrix;
    private Timer timely;
    private TimeKeeper timeKeeper;
    private String playerName;
    private int levelNum = 1;
    private JButton newGame;
    private JButton loadGame;
    private JButton exit;

    public GameGui() {

        super("Maze Game");
        fileMaze = "../laberintos/";
        fileImage = "../Imagenes/";
        cp = getContentPane();
        newGame = new JButton("Nueva Partida");
        loadGame = new JButton("Cargar Partida");
        exit = new JButton("Salir");
        itemSaveScore = new JMenuItem("Guardar Puntaje");
        itemHighScore = new JMenuItem("Tablero de puntajes");
        itemEnterName = new JMenuItem("Introducir nombre del jugador");
        itemExit = new JMenuItem("Salir");
        label = new JLabel("", new ImageIcon(fileImage + "yeababyyea.jpg"), JLabel.LEFT);
        GroupLayout labelLayout = new GroupLayout(label);
        label.setLayout(labelLayout);
        labelLayout.setHorizontalGroup(
                labelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(labelLayout.createSequentialGroup()
                                .addGap(160, 160, 160)
                                .addGroup(labelLayout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                                        .addComponent(exit)
                                        .addComponent(loadGame)
                                        .addComponent(newGame))
                                .addContainerGap(168, Short.MAX_VALUE))
        );
        labelLayout.setVerticalGroup(
                labelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(GroupLayout.Alignment.TRAILING, labelLayout.createSequentialGroup()
                                .addContainerGap(148, Short.MAX_VALUE)
                                .addComponent(newGame)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(loadGame)
                                .addGap(18, 18, 18)
                                .addComponent(exit)
                                .addGap(35, 35, 35))
        );
        cp.add(label);
        newGame.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                newGame();
            }
        });
        loadGame.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                loadGame();
            }
        });
        exit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                GameGui.super.dispose();
            }
        });
        itemSaveScore.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                saveScore();
            }
        });
        itemHighScore.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                highScore();
            }
        });
        itemEnterName.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                enterName();
            }
        });
        itemExit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                GameGui.super.dispose();
            }
        });
        itemSaveScore.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, KeyEvent.CTRL_MASK));//CTRL+S
        itemHighScore.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_H, KeyEvent.CTRL_MASK));//CTRL+H
        itemEnterName.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, KeyEvent.CTRL_MASK));//CTRL+N
        itemExit.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O, KeyEvent.CTRL_MASK));//CTRL+O
        newMenu = new JMenu("Opciones");
        newMenu.add(itemEnterName);
        newMenu.add(itemHighScore);
        newMenu.add(itemSaveScore);
        newMenu.add(itemExit);
        menuBar = new JMenuBar();
        menuBar.add(newMenu);
        setJMenuBar(menuBar);
        this.setResizable(false);
        newPanel = new JPanel();
        newPanel.setPreferredSize(new Dimension(500, 500));
        file = new File();
        timeKeeper = new TimeKeeper();
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        throw new UnsupportedOperationException("No compatible");
    }

    private class MyKeyHandler extends KeyAdapter {

        public void keyPressed(KeyEvent theEvent) {
            switch (theEvent.getKeyCode()) {
                case KeyEvent.VK_UP: {
                    remoteControl.playerMove(-1, 0, backupMatrix, maze.diamondCounter());
                    break;
                }
                case KeyEvent.VK_DOWN: {
                    remoteControl.playerMove(1, 0, backupMatrix, maze.diamondCounter());
                    break;
                }
                case KeyEvent.VK_LEFT: {
                    remoteControl.playerMove(0, -1, backupMatrix, maze.diamondCounter());
                    break;
                }
                case KeyEvent.VK_RIGHT: {
                    remoteControl.playerMove(0, 1, backupMatrix, maze.diamondCounter());
                    break;
                }
            }
            updateMatrix("updateLoad");
            levelCompleted();

            JLabel mainLabel = new JLabel("Diamantes faltantes por recoger: " + remoteControl.getDimonds() + "", JLabel.CENTER);
            JPanel dimondsPanel = new JPanel();
            dimondsPanel.add(mainLabel);
            cp.add(dimondsPanel, BorderLayout.SOUTH);
        }
    }

    public void levelCompleted() {
        if (remoteControl.getLevel() == true) {
            nextLevelLoad();
        }
    }

    public void newGame() {
        String path = fileMaze + "level1.maz";
        maze.createMaze(path);
        remoteControl.setExit(maze.getExitX(), maze.getExitY());
        updateMatrix("newLoad");
    }

    public void loadGame() {
        JFileChooser chooser = new JFileChooser();
        int returnVal = chooser.showOpenDialog(this);
        if (returnVal == JFileChooser.APPROVE_OPTION) {
            maze.createMaze(fileMaze + chooser.getSelectedFile().getName());
            remoteControl.setExit(maze.getExitX(), maze.getExitY());
            updateMatrix("newLoad");
        }
    }

    public void enterName() {
        JOptionPane optionPane = new JOptionPane();
        playerName = optionPane.showInputDialog("Please Enter your Earth Name");
    }

    public void highScore() {
        ScoreGui sg = new ScoreGui();
        sg.showHighScores();
    }

    public void saveScore() {
        String nameFile = "scores.txt";
        File file = new File();
        Player player = new Player(playerName, timeKeeper.getMinutes(), timeKeeper.getSeconds(), levelNum);
        ArrayList<String> list = new ArrayList<String>();

        list = file.readFile(nameFile);
        list.add(player.toString());
        file.writeFile(list);
    }

    public void updateMatrix(String event) {
        switch (event) {
            case "newLoad":
                remove(newPanel);
                existingTimeBar();
                copyMatrix();
                timeCalc = new TimeCalculator();
                timeCalc.TimeCalculatorForNevel(maze.diamondCounter(), maze.getRow(), maze.getColumn());
                timeLeft = timeCalc.getMinutes();
                ix = timeCalc.getSeconds();
                jx = 0;
                timely = new Timer(1000, updateCursorAction);
                timely.start();
                timeBar = new JPanel();
                progressBar = new JProgressBar(0, timeCalc.getMinutes() * 100);
                progressBar.setStringPainted(true);
                timeBar.add(progressBar);
                cp.add(timeBar, BorderLayout.NORTH);
                newPanel = new JPanel();
                newPanel.setLayout(new GridLayout(maze.getRow(), maze.getColumn()));
                labelMatrix = new JLabel[maze.getRow()][maze.getColumn()];
                newPanel.addKeyListener(new MyKeyHandler());
                break;

            case "updateLoad":
                backupMatrix = remoteControl.getUpdatedMatrix();
                remove(newPanel);
                newPanel = new JPanel();
                newPanel.setLayout(new GridLayout(maze.getRow(), maze.getColumn()));
                newPanel.addKeyListener(new MyKeyHandler());
                newPanel.grabFocus();
                break;
        }

        addImageToMaze();
        cp.add(newPanel);
        remove(label);
        System.gc();
        pack();
        setVisible(true);
        newPanel.grabFocus();
    }

    public void addImageToMaze() {
        for (int i = 0; i < labelMatrix.length; i++) {
            for (int j = 0; j < labelMatrix[i].length; j++) {
                labelMatrix[i][j] = mazeImages = new mazeObject(backupMatrix[i][j]);
            }
        }
    }

    public void existingTimeBar() {
        if (timeBar != null) {
            remove(timeBar);
        }
    }

    public void copyMatrix() {
        String[][] temp = maze.getGameMatrix();
        backupMatrix = new String[maze.getRow()][maze.getColumn()];
        for (int i = 0; i < backupMatrix.length; i++) {
            for (int j = 0; j < backupMatrix[i].length; j++) {
                backupMatrix[i][j] = temp[i][j];
            }
        }
    }

    public class mazeObject extends JLabel {

        private JLabel imageLabel;

        public mazeObject(String fileName) {
            String currentFile = fileImage + fileName + ".png";
            JLabel fancyLabel;
            fancyLabel = new JLabel("", new ImageIcon(currentFile), JLabel.LEFT);
            newPanel.add(fancyLabel);
        }
    }

    public void nextLevelLoad() {
        levelNum += 1;
        timeKeeper.TimeKeeper(timeLeft, ix);
        timely.stop();
        remoteControl = new Control();
        catFileName += 01;
        String fileName = fileMaze +"level" + catFileName + ".maz";
        System.gc();
        maze.createMaze(fileName);
        backupMatrix = maze.getGameMatrix();
        remoteControl.setExit(maze.getExitX(), maze.getExitY());
        updateMatrix("newLoad");
    }

    Action updateCursorAction = new AbstractAction() {
        public void actionPerformed(ActionEvent e) {
            ix -= 1;
            jx += 1;
            if (ix < 0) {
                ix = 60;
                timeLeft -= 1;
            }

            timeOut();

            progressBar.setValue(jx);
            progressBar.setString(timeLeft + ":" + ix);
        }
    };

    public void timeOut() {
        if (timeLeft == 0 && ix == 0) {
            timely.stop();
            JLabel yousuckLabel = new JLabel("", new ImageIcon("yousuck.jpg"), JLabel.LEFT);
            cp.add(yousuckLabel);
            remove(newPanel);
            remove(timeBar);
            pack();
            setVisible(true);
            timely.stop();
            catFileName -= 01;
            if (catFileName < 01) {
                saveScore();
                JFrame frame = new JFrame("Warning");
                JOptionPane.showMessageDialog(frame, "Tiempo agotado");
            } else {
                updateMatrix("newLoad");
            }
        }
    }

}
