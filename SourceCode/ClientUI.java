//Name: Chenglin Jing, Student ID: 909134

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.net.Socket;



public class ClientUI {
    private JButton Search;
    private JButton Add;
    private JButton Remove;
    private JPanel UI;
    private JTextField Word;
    private JTextField Meaning;
    private JTextArea Output;
    private JLabel LabelWord;
    private JLabel LabelAddMeaning;
    private JLabel LabelOutput;
    private String operation;
    private Socket socket;
    private String hostname;
    private int port;



    public ClientUI() {

        Search.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                try {

                    socket = new Socket(hostname, port);

                    BufferedWriter writer = new BufferedWriter(
                            new OutputStreamWriter(socket.getOutputStream()));

                    operation = "search";
                    String input = operation + '~' + Word.getText();

                    writer.write(input);
                    writer.newLine();
                    writer.flush();


                    BufferedReader reader = new BufferedReader(
                            new InputStreamReader(socket.getInputStream()));

                    Output.setText(reader.readLine());

                    writer.close();
                    reader.close();

                }
                catch (java.io.IOException exc) {

                    exc.printStackTrace();

                }

            }
        });
        Add.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                try {

                    socket = new Socket(hostname, port);

                    BufferedWriter writer = new BufferedWriter(
                            new OutputStreamWriter(socket.getOutputStream()));

                    operation = "add";
                    String input = operation + '~' + Word.getText() + '~' + Meaning.getText();

                    writer.write(input);
                    writer.newLine();
                    writer.flush();


                    BufferedReader reader = new BufferedReader(
                            new InputStreamReader(socket.getInputStream()));

                    Output.setText(reader.readLine());

                    writer.close();
                    reader.close();

                }
                catch (java.io.IOException exc) {

                    exc.printStackTrace();

                }

            }
        });
        Remove.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                try {

                    socket = new Socket(hostname, port);

                    BufferedWriter writer = new BufferedWriter(
                            new OutputStreamWriter(socket.getOutputStream()));

                    operation = "remove";
                    String input = operation + '~' + Word.getText();

                    writer.write(input);
                    writer.newLine();
                    writer.flush();


                    BufferedReader reader = new BufferedReader(
                            new InputStreamReader(socket.getInputStream()));

                    Output.setText(reader.readLine());

                    writer.close();
                    reader.close();

                }
                catch (java.io.IOException exc) {

                    exc.printStackTrace();

                }
            }
        });
    }

    public static void main(String[] args) {

        ClientUI c = new ClientUI();

        if (args.length != 2) {
            c.hostname = "localhost";
            c.port = 8888;
            System.out.println("Use the default setting...");
        }
        else {
            try {
                c.hostname = args[0];
                c.port = Integer.parseInt(args[1]);
            }
            catch(Exception e){
                e.printStackTrace();
            }
        }

        System.out.println("host: " + c.hostname);
        System.out.println("port: " + c.port);

        JFrame frame = new JFrame("ClientUI");
        frame.setContentPane(c.UI);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);

    }
}
