import javax.swing.*;
import java.awt.*;
import java.awt.datatransfer.StringSelection;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class Encrypter {

    public String encryptInfo(String readable) {

        String hidden1 = "";
        String hidden2 = "";
        String encryptedMessage = "";

        for (int i = 0; i < readable.length(); i++) {

            if (i % 2 == 1) {
                hidden1 += readable.charAt(i);
            } else {
                hidden2 += readable.charAt(i);
            }
        }
        encryptedMessage = hidden1 + hidden2;
        return encryptedMessage;
    }

    public String decryptInfo(String secret) {

        String storage1 = "";
        String storage2 = "";
        String end = "";

        if (secret.length() % 2 == 0) {
            for (int i = 0; i < secret.length() / 2; i++) {
                storage1 += secret.charAt(i);
            }

            for (int i = secret.length() / 2; i < secret.length(); i++) {
                storage2 += secret.charAt(i);
            }

            for (int i = 0; i < secret.length() / 2; i++) {
                end += storage2.charAt(i);
                end += storage1.charAt(i);
            }
        } else {
            //HIER IST IRGENDWO DER FEHLER
            for (int i = 0; (i < secret.length() / 2 ); i++) {
                storage1 += secret.charAt(i);
            }

            for (int i = (secret.length() / 2 ); i < secret.length(); i++) {
                storage2 += secret.charAt(i);
            }

            for (int i = 0; (i < secret.length() / 2 ); i++) {
                end += storage2.charAt(i);
                end += storage1.charAt(i);
            }

            //ChatGPT start
            if (storage2.length() > storage1.length()) {
                end += storage2.charAt(storage2.length() - 1);
            }
            //ChatGPT stop
        }

        //System.out.println("1: " + storage1);
        //System.out.println("2: " + storage2);

        return end;
    }


    public Encrypter() {
        JFrame choiceBoard = new JFrame("EncryptDecrypt");
        JPanel choicePanel = new JPanel();

        JButton opt1 = new JButton("Encrypt Secret Info");
        JButton opt2 = new JButton("Decrypt encrypted Info");

        choicePanel.setBackground(new Color(130, 130, 130));
        choicePanel.setLayout(new GridLayout(1, 2));
        choicePanel.add(opt1);
        choicePanel.add(opt2);

        choiceBoard.setSize(340, 225);
        choiceBoard.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        choiceBoard.setLocationRelativeTo(null);
        choiceBoard.add(choicePanel);
        choiceBoard.setResizable(false);
        choiceBoard.setVisible(true);

        ActionListener actionListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                if (e.getSource() == opt1) {
                    String readable = JOptionPane.showInputDialog("Type in your secret Message");
                    String end = encryptInfo(readable);

                    JOptionPane.showMessageDialog(null, "Encrypted: " + end);

                    int askForClipboard = JOptionPane.showOptionDialog(
                            null,
                            "Would you like to copy it to the clipboard?",
                            "Save as File",
                            JOptionPane.YES_NO_OPTION,
                            JOptionPane.QUESTION_MESSAGE,
                            null,
                            null,
                            null
                    );

                    if (askForClipboard == JOptionPane.YES_OPTION) {
                        Toolkit.getDefaultToolkit()
                                .getSystemClipboard()
                                .setContents(new StringSelection(end), null);
                        JOptionPane.showMessageDialog(null, "Copied to Clipboard. You can paste it anywhere now");
                    }

                    if (askForClipboard == JOptionPane.NO_OPTION) {
                        System.exit(0);
                    }
                }

                if (e.getSource() == opt2) {
                    String encrypted = JOptionPane.showInputDialog("Paste your encrypted message");
                    String end = decryptInfo(encrypted);
                    JOptionPane.showMessageDialog(null, "Decrypted: " + end);

                    int askForClipboard = JOptionPane.showOptionDialog(
                            null,
                            "Would you like to copy it to the clipboard?",
                            "Save as File",
                            JOptionPane.YES_NO_OPTION,
                            JOptionPane.QUESTION_MESSAGE,
                            null,
                            null,
                            null
                    );

                    //ChatGPT start
                    if (askForClipboard == JOptionPane.YES_OPTION) {
                        Toolkit.getDefaultToolkit()
                                .getSystemClipboard()
                                .setContents(new StringSelection(end), null);
                        JOptionPane.showMessageDialog(null, "Copied to Clipboard. You can paste it anywhere now");
                    }
                    //ChatGPT end

                    if (askForClipboard == JOptionPane.NO_OPTION) {
                        System.exit(0);
                    }
                }


            }
        };

        opt1.addActionListener(actionListener);
        opt2.addActionListener(actionListener);

    }

    public static void main(String[] args) {
        new Encrypter();
    }
}