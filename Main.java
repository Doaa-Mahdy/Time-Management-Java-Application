package test;

import javax.swing.*;
import java.awt.*;


public class Main extends JFrame {

    public static void main(String[] args) throws FontFormatException {

        SwingUtilities.invokeLater(() -> {

          try {
              LoadingScreen loading = new LoadingScreen();

            }
          catch (FontFormatException e) {
              throw new RuntimeException(e);
          }


        });
    }

    }

