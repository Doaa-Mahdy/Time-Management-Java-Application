package test;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import javax.sound.sampled.*;
import static test.Static.customizeButton;

public class StudyWithMe extends JPanel {
    private final JButton resetButton;
    private final JButton startButton;
    private final JButton pauseButton;
    private final JButton continueButton;
    private int studyTime = 50; // in minutes
    private int breakTime = 10; // in minutes
    private JLabel titleLabel;
    private JLabel timerLabel;
    private javax.swing.Timer timer; // Explicitly import javax.swing.Timer
    private int timeRemaining;
    private int breakRemaining = breakTime * 60;

    public StudyWithMe() {
        File file = new File("studyWithMeSoundStart.wav");
        AudioInputStream startSound=null;
        Clip clip=null;
        try {
           startSound = AudioSystem.getAudioInputStream(file);
           clip = AudioSystem.getClip();

        } catch (UnsupportedAudioFileException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (LineUnavailableException e) {
            throw new RuntimeException(e);
        }
        JPanel rootPanel = new JPanel();
        rootPanel.setLayout(new BorderLayout());
        rootPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        titleLabel = new JLabel("Study Time");
        titleLabel.setHorizontalAlignment(JLabel.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 34));

        timerLabel = new JLabel("50:00");
        timerLabel.setHorizontalAlignment(JLabel.CENTER);
        timerLabel.setFont(new Font("Arial", Font.PLAIN, 32));

        JPanel titlePanel = new JPanel(new BorderLayout());
        titlePanel.add(titleLabel, BorderLayout.NORTH);
        titlePanel.add(timerLabel, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel(new FlowLayout());
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        this.startButton = new JButton("Start");
       customizeButton(this.startButton);
        Clip finalClip = clip;
        AudioInputStream finalStartSound = startSound;
        try {
            finalClip.open(finalStartSound);
        } catch (LineUnavailableException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                finalClip.setMicrosecondPosition(0);
                finalClip.start();


                startStudy();
            }
        });

        this.resetButton = new JButton("Reset");
        customizeButton(this.resetButton);
        resetButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                resetTimer();
            }
        });

        this.pauseButton = new JButton("Pause");
        customizeButton(this.pauseButton);
        pauseButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                pauseTimer();
            }
        });

        this.continueButton = new JButton("Continue");
        customizeButton(this.continueButton);
        continueButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                continueTimer();
            }
        });

        buttonPanel.add(startButton);
        buttonPanel.add(resetButton);
        buttonPanel.add(pauseButton);
        buttonPanel.add(continueButton);

        rootPanel.add(titlePanel, BorderLayout.NORTH);
        rootPanel.add(buttonPanel, BorderLayout.CENTER);
        add(rootPanel);
    }

    private void startStudy() {
        timeRemaining = studyTime * 60; // Convert minutes to seconds
        timer = new javax.swing.Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int minutes = timeRemaining / 60;
                int seconds = timeRemaining % 60;
                String timeStr = String.format("%02d:%02d", minutes, seconds);
                timerLabel.setText(timeStr);
                if (timeRemaining <= 0) {
                    timer.stop();
                    timerLabel.setText("Break Time: 10:00");
                    startBreak();
                }
                timeRemaining--;
            }
        });
        timer.start();
    }


    private void startBreak() {
        // Start break time countdown here
        javax.swing.Timer breakTimer = new javax.swing.Timer(1000, new ActionListener() { // Use javax.swing.Timer
            @Override
            public void actionPerformed(ActionEvent e) {
                int minutes = breakRemaining / 60;
                int seconds = breakRemaining % 60;
                String timeStr = String.format("%02d:%02d", minutes, seconds);
                timerLabel.setText(timeStr);
                if (breakRemaining <= 0) {
                    timerLabel.setText("Study Time: 50:00");
                    ((javax.swing.Timer) e.getSource()).stop(); // Stop the timer
                }
                breakRemaining--;
            }
        });
        breakTimer.start();
    }

    private void resetTimer() {
        if (timer != null) {
            timer.stop();
        }
        timerLabel.setText("50:00");
    }

    private void pauseTimer() {
        if (timer != null && timer.isRunning()) {
            timer.stop();
        }
    }

    private void continueTimer() {
        if (timer != null && !timer.isRunning()) {
            timer.start();
        }
    }

}
