package step.learning.oop;

import com.google.gson.JsonObject;

import java.text.ParseException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.lang.reflect.Field;



@Serializable
public class Journal extends Literature
                    implements Copyable, Periodic, Multiple{
    @Required
    private int number;
    @Required
    private int count;

    private transient String title;

    private static List<String> requiredFieldsNames;
    private static List<String> getRequiredFieldsNames() {
        if(requiredFieldsNames == null) {
            Field[] fields = Journal.class.getDeclaredFields();   // getFields();
            Field[] fields2 = Journal.class.getSuperclass().getDeclaredFields();

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

    public Journal(String title, int number) {
        this.setNumber(number);
        super.setTitle(title);
        this.count=1;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    @Override
    public String getCard() {
        return String.format(
                "Journal:˚ '%s' № %s",
                super.getTitle(),
                this.getNumber()
        );
    }

    @Override
    public String getPeriod() {
        return "monthly";
    }

    @Override
    public int getCount() {
        return count;
    }

    public static Journal fromJson(JsonObject jsonObject) throws ParseException {
        //String[] requiredFields = {"title", "number"};
        String[] requiredFields = getRequiredFieldsNames().toArray(new String[0]);
        for (String field : requiredFields){
            if(!jsonObject.has(field)){
                throw new ParseException("Missing required field: " + field, 0);
            }
        }
        return new Journal(jsonObject.get(requiredFields[1]).getAsString(), jsonObject.get(requiredFields[0]).getAsInt() );

       /* return new Journal(
                jsonObject.get(requiredFields[0]).getAsString(),
                jsonObject.get(requiredFields[1]).getAsInt()
        );*/
    }

    @ParseChecker
    public static boolean isParseableFromJson(JsonObject jsonObject){
        String[] requiredFields = {"title", "number"};
        //for (String field : requiredFields){
        for(String field: getRequiredFieldsNames()) {

            if(!jsonObject.has(field)){
                return false;
            }
        }
        return true;
    }
}
