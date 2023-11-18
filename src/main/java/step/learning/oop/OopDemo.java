package step.learning.oop;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.text.ParseException;
import java.util.Objects;

public class OopDemo {

    public void run(){
        Library library = new Library();
        try {
            library.load();
        }
        catch (RuntimeException ex){
            System.err.println(ex.getMessage());
        }
        library.printAllCards();
    }

    public void run4(){
        String str = "{\"author\":\"D. Knuth\",\"title\":\"Art of programming\"}";
        JsonObject literatureObject = JsonParser.parseString(str).getAsJsonObject();
        Literature literature = null;
        if(literatureObject.has("author")){
            literature = new Book(
                    literatureObject.get("title").getAsString(),
                    literatureObject.get("author").getAsString()
            );
        }
        else if(literatureObject.has("number"))
        {
            literature = new Journal(
                    literatureObject.get("title").getAsString(),
                    literatureObject.get("number").getAsInt()
            );
        }
        else if(literatureObject.has("date"))
        {
            try{
                literature = new Newspaper(
                        literatureObject.get("title").getAsString(),
                        literatureObject.get("date").getAsString()
                );
            }
            catch (ParseException e)
            {
                throw new RuntimeException(e);
            }
        }
        System.out.println(literature.getCard());
    }

    public void run2(){
        Gson gson = new Gson();
        String str= "{\"author\": \"D. Knuth\", \"title\": \"Art of programming\"}";
        Book book = gson.fromJson(str, Book.class);

        System.out.println(book.getCard());

        System.out.println(gson.toJson(book));
        book.setAuthor(null);
        System.out.println(gson.toJson(book));

        Gson gson2 = new GsonBuilder().setPrettyPrinting()
                .setPrettyPrinting()
                .serializeNulls()
                .create();
        System.out.println(gson2.toJson(book));

        try (
                InputStream bookStream = this.getClass().getClassLoader().getResourceAsStream("book.json");
                InputStreamReader bookReader = new InputStreamReader(Objects.requireNonNull(bookStream));
        ){
            book = gson.fromJson(bookReader, Book.class);
            System.out.println(book.getCard());
        }
        catch (IOException ex){
            System.err.println(ex.getMessage());
        }

    }
    public void run1(){

        Library library = new Library();
        try {
            library.add(new Book("D.Knuth", "Art of Programming"));
            library.add(new Newspaper("Daily Mail", "2023-09-25"));
            library.add(new Journal("Quantum Mechanics", 157));

            library.add(new Book("Richter", "Platform .NET"));
            library.add(new Newspaper("Washington Post", "2023-09-25"));
            library.add(new Journal("Amogus Spawning", 32));

            library.add(new Hologram("holo1", "dec1", "2023-09-25"));
            library.add(new Hologram("holo2", "dec2", "2023-09-25"));

            //library.save();

        }
        catch(Exception ex){
            System.out.println("literature ");
        }
        //library.printAllCards();



        System.out.println("--------------COPYABLE--------------");
        library.printCopyable();
        System.out.println("--------------NONCOPYABLE--------------");
        library.printNonCopyable();

        System.out.println("--------------PERIODIC--------------");
        library.printPeriodicDuck();
        System.out.println("--------------NONPERIODIC--------------");
        library.printNonPeriodic();

        System.out.println("--------------Printable-----------------");
        library.printPrintable();
        System.out.println("---------------NonPrintable-------------");
        library.printNonPrintable();
        System.out.println("--------------Multiple-----------------");
        library.printMultiple();
        System.out.println("---------------NonMultiple-------------");
        library.printNonMultiple();
    }

}
