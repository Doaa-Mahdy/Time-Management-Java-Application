package test;

import java.awt.*;
import javax.swing.*;
import javax.swing.Timer;
import javax.swing.border.EtchedBorder;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;
import java.util.*;
import javax.sound.sampled.*;
import java.util.Random;


public class MyLoadingScreenContentPanel extends JPanel {

    ImageIcon bg;

    final int[] x_easter={-60,-100,-140,-120,-190};//start position of the Npcs



    MyLoadingScreenContentPanel() {
        setLayout(null);
        setSize(600, 400);
        //creating music


        File bg_music= new File("Elevator Music (Kevin MacLeod) - Gaming Background Music (HD).wav");

        Clip player=null;
        try {


            AudioInputStream bgMusic= AudioSystem.getAudioInputStream(bg_music);
            player = AudioSystem.getClip();
            player.open(bgMusic);
            player.start();
        } catch (UnsupportedAudioFileException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (LineUnavailableException e) {
            throw new RuntimeException(e);
        }
        // Create a layered pane
        JLayeredPane layeredPane = new JLayeredPane();
        layeredPane.setBounds(0, 0, 600, 400);//layer that contains all contents

        //images of NPCs
        Image sonicImage = new ImageIcon("sonic.gif").getImage();
        Image marioImage = new ImageIcon("mario run.gif").getImage();
        Image megaManImage = new ImageIcon("mega man run.gif").getImage();
        Image NpcImage = new ImageIcon("runNpc.gif").getImage();
        Image missleImage = new ImageIcon("missle.gif").getImage();


        JLabel sonicRun = new JLabel(new ImageIcon("sonic.gif"));
        JLabel marioRun = new JLabel(new ImageIcon("mario run.gif"));
        JLabel megaManRun = new JLabel(new ImageIcon("mega man run.gif"));
        JLabel NpcRun = new JLabel(new ImageIcon("runNpc.gif"));
        JLabel missle = new JLabel(new ImageIcon("missle.gif"));
        JLabel explosion = new JLabel(new ImageIcon("explosion.gif"));



        sonicRun.setBounds(-60, 0, 50, 50);
        marioRun.setBounds(-70, 10, 50, 50);
        megaManRun.setBounds(-70, 8, 50, 50);
        NpcRun.setBounds(-90, 7, 100, 100);
        missle.setBounds(-180, 5, 100, 100);
        explosion.setBounds(600, 10, 100, 100);
        layeredPane.add(sonicRun);
        layeredPane.add(marioRun);
        layeredPane.add(megaManRun);
        layeredPane.add(NpcRun);
        layeredPane.add(missle);
        layeredPane.add(explosion);

        ArrayList<String> facts = new ArrayList<>(Arrays.asList("Octopuses have three hearts.",
                "Bananas are berries, but strawberries aren't.",
                "The total weight of ants on Earth once rivaled the total weight of humans.",
                "The unicorn is the national animal of Scotland.",
                "The Great Wall of China is not visible from space with the naked eye.",
                "The world's oldest known recipe is for beer.",
                "The Hawaiian alphabet has only 12 letters.", "A \"googol\" is the number 1 followed by 100 zeros.",
                "The first computer virus was created in 1983 and was called the \"Elk Cloner.\" It infected Apple II systems via floppy disks.",
                "\"Hello, World!\" is often the first program written by beginners learning a new programming language.",

                "In Japan, there are cat cafés where you can sip coffee and hang out with cats. It's a purrfect combination!",
                "The national animal of Scotland is the unicorn. Who needs reality when you can have unicorns?",
                "Dolphins give each other names. They develop their own unique whistles to call one another, kind of like a dolphin version of a nickname.",
                "The word \"nerd\" was first coined by Dr. Seuss in his book \"If I Ran the Zoo\" in 1950. Looks like being a nerd is nothing to sneeze at!"
        ));

        JLabel funFact = new JLabel();
        Random rand = new Random();
        funFact.setText("<html>" + "Fun fact: " + facts.get(rand.nextInt(facts.size())) + "</html>");
        funFact.setFont(new Font("monospaced", Font.BOLD, 15));
        funFact.setForeground(new Color(0x00));
        funFact.setBounds(50, 300, 400, 100);

        JProgressBar loadingBar = new JProgressBar();
        loadingBar.setBounds(30, 280, 500, 20);
        loadingBar.setOpaque(false);
        loadingBar.setForeground(new Color(0xC909DE));
        loadingBar.setValue(10);
        loadingBar.setStringPainted(true);
        loadingBar.setFont(new Font("MS Mincho", Font.BOLD, 15));

        JLabel warning_message= new JLabel("<html>Finish your tasks before deadline or end like them!!!</html>");
        warning_message.setBounds(100,100,300,40);
        warning_message.setForeground(Color.RED);
        warning_message.setFont(new Font("MS Mincho", Font.BOLD, 15));
        layeredPane.add(warning_message);

        warning_message.setVisible(false);


        // Action to be performed
        ActionListener action = e -> {
            //TODO: here we will add our logic
            //move to login page
            LoginPage l = new LoginPage();
            l.setVisible(true);
            //get the parent of panel to close it
            JFrame parent = (JFrame) SwingUtilities.getWindowAncestor(MyLoadingScreenContentPanel.this);
            parent.dispose();
            //System.exit(0);

        };



        Clip finalPlayer = player;
        javax.swing.Timer move = new Timer(60, e1 -> {
            x_easter[0]+=10;
            x_easter[1]+=10;
            x_easter[2]+=10;
            x_easter[3]+=10;
            x_easter[4]+=9;

            if(x_easter[4]>630) {
                explosion.setLocation(510, explosion.getY());
                warning_message.setVisible(true);

                File explosion_sound= new File("explosion.wav");
                AudioInputStream Explosionsound=null;
                try {
                    Explosionsound= AudioSystem.getAudioInputStream(explosion_sound);
                } catch (UnsupportedAudioFileException e) {
                    throw new RuntimeException(e);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                finalPlayer.close();
                try {
                    finalPlayer.open(Explosionsound);
                    finalPlayer.start();
                } catch (LineUnavailableException e) {
                    throw new RuntimeException(e);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                explosion.setVisible(true);
                ((Timer)e1.getSource()).stop();
            }
            sonicRun.setLocation(x_easter[0],sonicRun.getY());
            marioRun.setLocation(x_easter[1],5);
            megaManRun.setLocation(x_easter[2],15);
            NpcRun.setLocation(x_easter[3],19);
            missle.setLocation(x_easter[4],13);

            repaint();

        });
        // Add mouse listener to the frame
        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if(!move.isRunning()) action.actionPerformed(null);
            }
        });

        // Add key listener to the frame
        this.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if(!move.isRunning()) action.actionPerformed(null);
            }
        });
        final int[] ctr = {0};
        javax.swing.Timer timer = new Timer(50, e1 -> {
            ctr[0] += 1;
            loadingBar.setValue(ctr[0]);
            if (ctr[0] == 100) {

                ((Timer) e1.getSource()).stop();
            } else if (ctr[0] == 1){
                move.start();

            }
        });
        timer.start();



        timer.start();

        // Add components to the layered pane
        layeredPane.add(funFact, JLayeredPane.PALETTE_LAYER); // Place funFact on top
        layeredPane.add(loadingBar, JLayeredPane.PALETTE_LAYER); // Place loadingBar on top

        // Add layeredPane to the panel
        add(layeredPane);
    }
    public void paintComponent(Graphics g) {
        super.paintComponent(g); // Always call super.paintComponent() first
        Graphics2D g2D = (Graphics2D) g;


        Image bg = new ImageIcon("backGround_of_loadingScreen.jpg").getImage();
        g2D.drawImage(bg, 0, 0, null);

    }

}
