package step.learning.basics;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

public class FilesDemo {
    public void run(){
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
