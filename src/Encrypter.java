import javax.swing.*;
import java.awt.*;
import java.awt.datatransfer.StringSelection;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

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

    public Encrypter() {
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
        }

        if (askForClipboard == JOptionPane.NO_OPTION) {
            System.exit(0);
        }

        JOptionPane.showMessageDialog(null, "Copied to Clipboard. You can paste it anywhere now");

    }

    public static void main(String[] args) {
        new Encrypter();
    }
}