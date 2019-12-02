//Name: Chenglin Jing, Student ID: 909134


public class Operation {

    private String operation;
    private String word;
    private String meaning;

    public Operation(String operation, String word, String meaning){
        this.operation = operation;
        this.word  = word;
        this.meaning = meaning;
    }

    public Operation(String operation, String word){
        this.operation = operation;
        this.word = word;
    }

    public String getOperation(){
        return this.operation;
    }

    public String getWord(){
        return this.word;
    }

    public String getMeaning(){
        return this.meaning;
    }

    public void setMeaning(String meaning){
        this.meaning = meaning;
    }

}
