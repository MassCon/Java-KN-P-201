package step.learning.oop;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;


public class Hologram extends Literature implements Printable{
    private Date date;
    private String description;
    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

    public Hologram(String title, String description, Date date) {
        this.description = description;
        super.setTitle(title);
        this.setDate(date);
    }

    public Hologram(String title, String description, String date) throws ParseException {
        this(title, description, dateFormat.parse(date));
    }



    @Override
    public String getCard() {
        return String.format("Hologram: %s from '%s' description: '%s'",
                getTitle(),
                dateFormat.format(this.getDate()),
                getDescription()
        );
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
