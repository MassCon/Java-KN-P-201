package step.learning.oop;
public class Journal extends Literature
                    implements Copyable, Periodic, Multiple{
    private int number;
    private int count;
    private String title;
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
}
