/**
 * Medicine object
 * @author Brendan Yeong
 */
public class Medicine {
    private String mediName;
    private int amount;

    /**
     * Creates a medicine object with just the name and with an amount of 0
     * @param name name of the medicine
     */
    public Medicine(String name){
        mediName = name;
        amount = 0;
    }

    /**
     * Creates a medicine object with just the name and with an amount
     * @param name name of the medicine
     * @param num amount that is to be added
     */
    public Medicine(String name, int num){
        mediName = name;
        amount = num;
    }

    /**
     * Get medicine name
     * @return medicine name
     */
    public String getMediName(){
        return mediName;
    }

    /**
     * Get amount
     * @return amount
     */
    public int getAmount(){
        return amount;
    }

    /**
     * Add amount
     * @param num amount to be added
     */
    public void addAmount(int num){
        amount += num;
    }

    /**
     * Remove amount
     * @param num amount to be removed
     */
    public void removeAmount(int num){
        amount -= num;
    }
}
