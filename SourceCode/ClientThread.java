//Name: Chenglin Jing, Student ID: 909134

import java.io.BufferedWriter;
import java.io.OutputStreamWriter;
import java.util.HashMap;
import java.net.Socket;



public class ClientThread implements Runnable{

    private Operation o;
    private HashMap<String, String> d;
    private Socket s;

    ClientThread(Operation o, HashMap<String, String> d, Socket s){
        this.o = o;
        this.d = d;
        this.s = s;
    }

    private synchronized String search(String word){
        return d.get(word);
    }

    private synchronized void add(String word, String meaning){
            d.put(word, meaning);
    }

    private synchronized void remove(String word){
        d.remove(word);
    }

    public void run(){

        try {

            BufferedWriter writer = new BufferedWriter(
                    new OutputStreamWriter(s.getOutputStream()));



            switch (o.getOperation()) {

                case "search":
                    if (!d.containsKey(o.getWord())) {

                        writer.write("Word " + o.getWord() + " does not exist.");
                        writer.newLine();
                        writer.flush();

                    } else {

                        writer.write("Word " + o.getWord() + " means: " + search(o.getWord()));
                        writer.newLine();
                        writer.flush();

                    }

                    break;

                case "add":
                    if (d.containsKey(o.getWord())) {

                        writer.write("Word " + o.getWord() + " has already existed.");
                        writer.newLine();
                        writer.flush();

                    } else {

                        add(o.getWord(), o.getMeaning());
                        writer.write("Word " + o.getWord() + " and its meaning(s) has been added.");
                        writer.newLine();
                        writer.flush();

                    }

                    break;

                case "remove":
                    if (!d.containsKey(o.getWord())) {

                        writer.write("Word " + o.getWord() + " does not exits.");
                        writer.newLine();
                        writer.flush();

                    } else {

                        remove(o.getWord());
                        writer.write("Word " + o.getWord() + " and its meaning has been removed.");
                        writer.newLine();
                        writer.flush();

                    }

                    break;

            }

            writer.close();

        }

        catch (Exception e){

            e.printStackTrace();

        }



    }
}
