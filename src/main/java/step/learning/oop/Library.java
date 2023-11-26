package step.learning.oop;

import com.google.gson.*;
import com.sun.org.apache.xerces.internal.impl.xpath.regex.ParseException;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

public class Library {
    private final List<Literature> funds;

    public Library() {
        funds = new LinkedList<>();
    }

    public void add(Literature literature){
        funds.add(literature);
    }

    public List<Literature> GetFunds()
    {
        return funds;
    }

    public List<Literature> getSerializableFunds()
    {
        List<Literature> serializableFunds = new ArrayList<>();
        for (Literature literature : GetFunds()) {
            if(literature.getClass().isAnnotationPresent(Serializable.class)){
                serializableFunds.add(literature);
            }
        }
        return serializableFunds;
    }

    public void save() throws IOException {
        Gson gson = new GsonBuilder().
                setDateFormat("yyyy-MM-dd")
                .setPrettyPrinting()
                .create();
        FileWriter writer = new FileWriter( "./src/main/resources/library.json" ) ;
        writer.write( gson.toJson( this.getSerializableFunds() ) ) ;
        writer.close() ;
    }

    public void load() throws RuntimeException{
        try(InputStreamReader reader = new InputStreamReader(
                Objects.requireNonNull(
                        this.getClass().getClassLoader().getResourceAsStream("library.json")))){
            this.funds.clear();
            for(JsonElement item: JsonParser.parseReader( reader ).getAsJsonArray()){
                this.funds.add(this.fromJson(item.getAsJsonObject()));
            }
        }
        catch (IOException  ex)
        {
            throw new RuntimeException( ex );
        }
        catch (NullPointerException ignored) {
            throw new RuntimeException("Resource not found");
        }
        catch (java.text.ParseException e) {
            throw new RuntimeException(e);
        }

    }

    private List<Class<?>> getSerializableClasses(){

        List<Class<?>> literatures = new LinkedList<>();
        String className = Literature.class.getName();
        String packageName = className.substring(0, className.lastIndexOf('.'));
        String packagePath = packageName.replace(".", "/");
        String absolutePath = Literature.class.getClassLoader()
                .getResource(packagePath).getPath();
        File[] files = new File(absolutePath).listFiles();
        if(files ==null){
            throw new RuntimeException("Class path inaceptable");
        }
        for (File file : files){
            if(file.isFile()){
                String filename = file.getName();
                if(filename.endsWith(".class")){
                    String fileClassName = filename.substring(0, filename.lastIndexOf('.')  );
                    try {
                        Class<?> fileClass = Class.forName(packageName + "." + fileClassName);
                        if(fileClass.isAnnotationPresent(Serializable.class)){
                            literatures.add(fileClass);
                        }
                    }
                    catch (ClassNotFoundException ignored) {
                        continue;
                    }
                }
            }
            else if(file.isDirectory()){
                continue;
            }
        }
        return literatures;
    }

    private Literature fromJson(JsonObject jsonObject) throws java.text.ParseException {

        List<Class<?>> literatures = this.getSerializableClasses();

        //Class<?>[] literatures = {Book.class, Journal.class, Newspaper.class, Hologram.class};
        try {
            for(Class<?> literature: literatures)
            {
                //Method isParseableFromJson = literature.getMethod("isParseableFromJson", JsonObject.class);
                Method  isParseableFromJson = null;
                for (Method method: literature.getDeclaredMethods()){
                    if(method.isAnnotationPresent(ParseChecker.class)){
                        if(isParseableFromJson != null){
                            throw new java.text.ParseException("Multiple ParseCheker annotations", 0);
                        }
                        isParseableFromJson = method;
                    }
                }
                if (isParseableFromJson == null) {
                    continue;
                }
                isParseableFromJson.setAccessible(true);
                boolean res = (boolean) isParseableFromJson.invoke(null, jsonObject);
                if(res)
                {
                    Method fromJson = literature.getMethod("fromJson", JsonObject.class);
                    fromJson.setAccessible(true);
                    return (Literature) fromJson.invoke(null, jsonObject);
                }
            }

        } catch (Exception ex) {
            throw new ParseException("Reflection error:" + ex.getMessage(), 0);
        }


       /* if(Book.isParseableFromJson(jsonObject)){
            return Book.fromJson(jsonObject);
//            return new Book(
//                    jsonObject.get("title").getAsString(),
//                    jsonObject.get("author").getAsString()
//            );
        }
        else if(Journal.isParseableFromJson(jsonObject))
        {
            return Journal.fromJson(jsonObject);
//            return new Journal(
//                    jsonObject.get("title").getAsString(),
//                    jsonObject.get("number").getAsInt()
//            );
        }
        else if(Newspaper.isParseableFromJson(jsonObject))
        {
            return Newspaper.fromJson(jsonObject);
//            return new Newspaper(
//                    jsonObject.get("title").getAsString(),
//                    jsonObject.get("date").getAsString()
//            );
        }*/
        throw new ParseException("Literature type unrecognized", 0);
    }

    public List<Literature> getFunds()
    {
        return funds;
    }

    public void printAllCards(){
        for (Literature literature : funds){
            System.out.println(literature.getCard());
            System.out.println(literature.getClass().getName());
        }
    }

    public void printCopyable(){
        for(Literature literature: funds)
        {
            if(isCopyable(literature))
            {
                System.out.println(literature.getCard());
            }
        }
    }
    public void printNonCopyable(){
        for(Literature literature: funds)
        {
            if(!isCopyable(literature))
            {
                System.out.println(literature.getCard());
            }
        }
    }

    public boolean isCopyable(Literature literature)
    {
        return literature instanceof Copyable;
    }

    public void printPeriodic()
    {
        for(Literature literature: funds)
        {
            if(isPeriodic(literature))
            {
                Periodic listAsPeriodic = (Periodic) literature;
                System.out.println(listAsPeriodic.getPeriod() + " " + literature.getCard());
            }
        }
    }

    public void printPeriodicDuck()
    {
        for(Literature literature: funds)
        {
            try{
                Method getPeriodMethod = literature.getClass().getDeclaredMethod("getPeriod");
                System.out.println(getPeriodMethod.invoke(literature) + " " + literature.getCard());
            }
            catch (Exception ignored)
            {

            }
        }
    }


    public void printNonPeriodic()
    {
        for(Literature literature: funds)
        {
            if(!isPeriodic(literature))
            {
                System.out.println(literature.getCard());
            }
        }
    }
    public boolean isPeriodic(Literature literature)
    {
        return literature instanceof Periodic;
    }


    public void printPrintable(){
        for( Literature literature : funds){
            if(isPrintable( literature)){
                System.out.println(literature.getCard());
            }
        }
    }

    public void printNonPrintable(){
        for( Literature literature : funds){
            if(!isHologram( literature)){
                System.out.println(literature.getCard());
            }
        }
    }

    public boolean isPrintable(Literature literature){
        return literature instanceof Printable;
    }

    public boolean isHologram(Literature literature){
        return literature instanceof Hologram;
    }

    public void printMultiple(){
        for( Literature literature : funds){
            if(isMultiple( literature)){
                System.out.println(literature.getCard());
            }
        }
    }

    public void printNonMultiple(){
        for( Literature literature : funds){
            if(!isMultiple( literature)){
                System.out.println(literature.getCard());
            }
        }
    }

    public boolean isMultiple(Literature literature){
        return literature instanceof Multiple;
    }
}
