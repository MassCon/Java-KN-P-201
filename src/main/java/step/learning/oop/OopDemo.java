package step.learning.oop;

public class OopDemo {
    public void run(){

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
