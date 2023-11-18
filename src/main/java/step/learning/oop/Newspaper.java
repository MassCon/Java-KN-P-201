package step.learning.oop;

import com.google.gson.JsonObject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Newspaper extends Literature implements Periodic{
    private Date date;
    private static final SimpleDateFormat sqlDateFormat = new SimpleDateFormat("yyyy-MM-dd");
    private static final SimpleDateFormat cardDateFormat = new SimpleDateFormat("dd.MM.yyyy");

    public Newspaper(String title, Date date) {
        super.setTitle(title);
        this.setDate(date);
    }

    public Newspaper(String title, String date) throws ParseException {
        this(title, sqlDateFormat.parse(date));
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public String getCard() {
        return String.format(
                "Newspaper: '%s' from '%s' ",
                super.getTitle(),
                cardDateFormat.format( this.getDate() )
        );
    }

    @Override
    public String getPeriod() {
        return "daily";
    }

    public static Newspaper fromJson(JsonObject jsonObject) throws ParseException {
        String[] requiredFields = {"title", "date"};
        for (String field : requiredFields){
            if(!jsonObject.has(field)){
                throw new ParseException("Missing required field: " + field, 0);
            }
        }

        return new Newspaper(
                jsonObject.get(requiredFields[0]).getAsString(),
                jsonObject.get(requiredFields[1]).getAsString()
        );
    }

    public static boolean isParseableFromJson(JsonObject jsonObject){
        String[] requiredFields = {"title", "date"};
        for (String field : requiredFields){
            if(!jsonObject.has(field)){
                return false;
            }
        }
        return true;
    }
}
