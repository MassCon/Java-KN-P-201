package step.learning.oop;

import java.util.LinkedList;
import java.util.List;

public class Library {
    private final List<Literature> funds;

    public Library() {
        funds = new LinkedList<>();
    }

    public void add(Literature literature){
        funds.add(literature);
    }

    public void printAllCards(){
        for (Literature literature : funds){
            System.out.println(literature.getCard());
        }
    }
}
