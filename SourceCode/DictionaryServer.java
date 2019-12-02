//Name: Chenglin Jing, Student ID: 909134

import java.net.*;
import java.io.*;
import java.util.HashMap;



public class DictionaryServer {

    private Socket socket;
    private static HashMap<String, String> map = new HashMap< >();

    private void setSocket(Socket socket) {
        this.socket = socket;
    }

    public static void main(String[] args) throws Exception{

        int port = 8888;
        if (args.length == 1) {
            try {
                port = Integer.parseInt(args[0]);
            }
            catch(Exception e){
                e.printStackTrace();
            }
        }

        if (args.length == 2) {
            try {
                port = Integer.parseInt(args[0]);
                String filePath = args[1];

                BufferedReader fileReader = new BufferedReader(new
                        InputStreamReader(new FileInputStream(new File(filePath))));
                String txtLine;

                while ((txtLine = fileReader.readLine()) != null){
                    String[] txtElement = txtLine.split("~");
                    map.put(txtElement[0], txtElement[1]);
                }

                System.out.println("Dictionary file loaded from path: " + filePath);

                fileReader.close();
            }
            catch(Exception e){
                e.printStackTrace();
            }

        }



        ServerSocket serverSocket = new ServerSocket(port);

        System.out.println("Now dictionary server is running. port: " + port);

        while (true){

            try{

                DictionaryServer server = new DictionaryServer();

                server.setSocket(serverSocket.accept());

                BufferedReader reader = new BufferedReader(
                        new InputStreamReader(server.socket.getInputStream()));
                BufferedWriter writer = new BufferedWriter(
                        new OutputStreamWriter(server.socket.getOutputStream()));

                String line = reader.readLine();

                String[] element = line.split("~");

                Operation opr;

                if ((element.length == 3) && (element[0].equals("add"))) {
                    opr = new Operation(element[0], element[1], element[2]);
                    new Thread(new ClientThread(opr, map, server.socket)).start();
                }
                else if ((element.length == 2) && ((element[0].equals("search")) || (element[0].equals("remove")))) {
                    opr = new Operation(element[0], element[1]);
                    new Thread(new ClientThread(opr, map, server.socket)).start();
                }
                else {
                    if (element[0].equals("add") && element.length < 3) {
                        writer.write("Illegal input! You must enter both the word and its meaning you want to add.");
                        writer.newLine();
                        writer.flush();
                    }
                    else if(element[0].equals("search") && element.length < 2){
                        writer.write("Illegal input! You must enter the word you want to search.");
                        writer.newLine();
                        writer.flush();
                    }
                    else if(element[0].equals("remove") && element.length < 2){
                        writer.write("Illegal input! You must enter the word you want to remove.");
                        writer.newLine();
                        writer.flush();
                    }
                }

            }

            catch (Exception e){

                e.printStackTrace();

            }

        }

    }

}
