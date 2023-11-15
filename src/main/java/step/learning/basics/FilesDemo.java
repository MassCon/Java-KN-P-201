package step.learning.basics;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;
import java.util.Scanner;
import java.util.concurrent.ThreadLocalRandom;

public class FilesDemo {

    public static String generateString(int min, int max) {
        StringBuilder randomString = new StringBuilder();

        int length = ThreadLocalRandom.current().nextInt(min, max + 1);

        for (int i = 0; i < length; i++) {
            int randomAscii = ThreadLocalRandom.current().nextInt(20, 127 + 1);
            char randomChar = (char) randomAscii;
            randomString.append(randomChar);
        }

        return randomString.toString();
    }

    public static void generateFile(int amountOfLines, String filename){


        Scanner kbscaner = new Scanner(System.in);
        System.out.print("Type min length: ");
        int minLength = Integer.parseInt(kbscaner.next()); // Минимальная длина строки
        System.out.print("Type max length: ");
        int maxLength = Integer.parseInt(kbscaner.next()); // Максимальная длина строки
        //String str = generateString(minLength, maxLength);
        //System.out.println(str);


        try(FileWriter writer = new FileWriter(filename, true)){
            for (int i = 0; i < amountOfLines; i++) {
                writer.write( generateString(minLength, maxLength));
            }
        }
        catch (IOException ex) {
            System.err.println(ex.getMessage());
        }
    }

    public static void readFile(String filename){
        try( InputStream reader = new FileInputStream(filename);
             Scanner scanner = new Scanner(reader)){
            while(scanner.hasNext()){
                System.out.println(scanner.next());
            }
        }
        catch (IOException ex) {
            System.err.println(ex.getMessage());
        }
    }

    public static void findMaxLine(String filename){

        int maxLineNumber=0;
        String maxLine = "1";
        int count=0;
        String currentLine = "1";

        try( InputStream reader = new FileInputStream(filename);
             Scanner scanner = new Scanner(reader)){
            while(scanner.hasNext()){
                currentLine = scanner.next();
                if(currentLine.compareTo(maxLine) > 0 ){
                    maxLine = currentLine;
                    maxLineNumber = count;
                    count++;
                }
                else{
                    count++;
                }
            }
        }
        catch (IOException ex) {
            System.err.println(ex.getMessage());
        }

        System.out.printf("Max line in %s is line number %s and it consists of %s%n", filename, maxLineNumber, maxLine);

    }

    public void run(){
        String filename = "HW.txt";
        generateFile(5,filename);
        readFile(filename);
        findMaxLine(filename);
    }
    
    public void run3(){  //run
        String filename = "test.txt";
        try (OutputStream writer = new FileOutputStream(filename , false)) {
            writer.write("Hello, World!".getBytes());
        }
        catch (IOException ex) {
            System.err.println(ex.getMessage());
        }

        try(FileWriter writer = new FileWriter(filename, true)){
            writer.write("\nNew Line");
        }
        catch (IOException ex) {
            System.err.println(ex.getMessage());
        }

        StringBuilder sb = new StringBuilder();
        try(InputStream reader = new FileInputStream(filename)) {
            int c;
            while ((c = reader.read()) != -1){
                sb.append((char) c);
            }
            System.out.println(sb.toString());
        }
        catch (IOException ex) {
            System.err.println(ex.getMessage());
        }

        System.out.println("-----------------------------------------");

        ByteArrayOutputStream byteBuilder = new ByteArrayOutputStream( 4096);
        byte [] buf = new byte[512];
        try(InputStream reader = new BufferedInputStream(
                new FileInputStream(filename))){
            int cnt;
            while((cnt = reader.read(buf)) >0) {
                byteBuilder.write(buf, 0, cnt);
            }

            String content = new String(
                    byteBuilder.toByteArray(),
                    StandardCharsets.UTF_8
            );
            System.out.println(content);
        }
        catch (IOException ex) {
            System.err.println(ex.getMessage());
        }

        System.out.println("-----------------------------------------");

        try( InputStream reader = new FileInputStream(filename);
             Scanner scanner = new Scanner(reader)){
            while(scanner.hasNext()){
                System.out.println(scanner.next());
            }
        }
        catch (IOException ex) {
            System.err.println(ex.getMessage());
        }

        //Scanner kbScanner = new Scanner(System.in);
        //System.out.print("your name: ");
        //String name = kbScanner.next();
        //System.out.printf("Hello, %s%n", name);

        //Random random = new Random();



    }
    public void run2(){
        File dir = new File("./uploads");
        if (dir.exists()){
            if(dir.isDirectory())
            {
                System.out.printf("'%s' already exists%n", dir.getName());
            }
            else{
                System.out.printf("'%s' already exists BUT NOT AS DIRECTORY%n", dir.getName());
            }
        }
        else{
            if (dir.mkdir()){
                System.out.printf("Directory %s created%n", dir.getName());
            }
            else{
                System.out.printf("Directory %s already exists%n", dir.getName());
            }
        }
        File file = new File("./uploads/whitelist.txt");
        if (file.exists()){
            if(file.isFile())
            {
                System.out.printf("'%s' already exists%n", file.getName());
            }
            else{
                System.out.printf("'%s' already exists BUT NOT AS FILE%n", file.getName());
            }
        }
        else{
            try {
                if(file.createNewFile()){
                    System.out.printf("File %s created%n", file.getName());
                }
                else{
                    System.out.printf("File %s creation error%n", file.getName());
                }
            }
            catch (IOException ex){
                System.err.println(ex.getMessage());
            }
        }
    }
    public void run1(){
        File dir = new File("./");

        if(dir.exists()){
            System.out.println("Path exists");
        }
        else{
            System.out.println("path doesnt exist");
        }
        System.out.printf("Path is %s %n",
                dir.isDirectory() ? "directory" : "file");
        System.out.println(dir.getAbsolutePath());

        for (String filename: dir.list()) {
            System.out.println(filename);
        }

        System.out.println("---------------------------------------");
        HW();
    }

    private void HW(){
        File currentDirectory = new File("./");

        File[] files = currentDirectory.listFiles();

        if (files != null) {
            for (File file : files) {
                String name = file.getName();
                String type = file.isDirectory() ? "d" : "a";
                String size = file.isDirectory() ? "" : String.valueOf(file.length());
                String lastModified = new SimpleDateFormat("dd.MM.yyyy HH:mm").format(new Date(file.lastModified()));

                System.out.printf("%s----  %s  %s  %s%n", type, lastModified, size, name);
            }
        } else {
            System.out.println("Error");
        }
    }
}
