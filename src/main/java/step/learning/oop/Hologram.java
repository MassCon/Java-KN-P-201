package step.learning.oop;

import com.google.gson.JsonObject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.lang.reflect.Field;


@Serializable
public class Hologram extends Literature implements Printable{
    private Date date;
    @Required
    private String description;

    private static List<String> requiredFieldsNames;
    private static List<String> getRequiredFieldsNames() {
        if(requiredFieldsNames == null) {
            Field[] fields = Hologram.class.getDeclaredFields();   // getFields();
            Field[] fields2 = Hologram.class.getSuperclass().getDeclaredFields();

            requiredFieldsNames =
                    Stream.concat(
                                    Arrays.stream(fields),
                                    Arrays.stream(fields2))
                            .filter( field -> field.isAnnotationPresent(Required.class) )
                            .map(Field::getName)
                            .collect(Collectors.toList());
        }
        return requiredFieldsNames;
    }


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

    @ParseChecker
    public static boolean isParseableFromJson(JsonObject jsonObject) {
        String[] requiredFields = {"title", "description", "date"};
        //for (String field : requiredFields) {
        for(String field: getRequiredFieldsNames()) {

            if (!jsonObject.has(field)) {
                return false;
            }
        }
        return true;
    }


    public static Hologram fromJson(JsonObject jsonObject) throws ParseException {
        //String[] requiredFields = {"title", "description", "date"};
        String[] requiredFields = getRequiredFieldsNames().toArray(new String[0]);
        for (String field : requiredFields) {
            if (!jsonObject.has(field)) {
                throw new ParseException("Missing required field: " + field, 0);
            }
        }
        return new Hologram(jsonObject.get(requiredFields[2]).getAsString(), jsonObject.get(requiredFields[1]).getAsString(), jsonObject.get(requiredFields[0]).getAsString());

       /* return new Hologram(
                jsonObject.get(requiredFields[0]).getAsString(),
                jsonObject.get(requiredFields[1]).getAsString(),
                jsonObject.get(requiredFields[2]).getAsString()
        );*/
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
