import java.util.ArrayList;

/**
 * Inventory class
 * @author Brendan Yeong
 */
public class Inventory {
   private ArrayList<Medicine> medicines;

   public Inventory(){
       medicines = new ArrayList<>();
       medicines.add(new Medicine("Vicodin", 5));
       medicines.add(new Medicine("Synthroid", 5));
       medicines.add(new Medicine("Delasone", 5));
       medicines.add(new Medicine("Amoxil", 5));
       medicines.add(new Medicine("Neurontin", 5));
   }

    /**
     * Adds medicine name into the inventory
     * @param name Name of the medicine
     */
    public void addMedicine(String name){
        String temp;
        for(int i = 0; i < medicines.size(); i++ ) {
            temp = medicines.get(i).getMediName();
            if(temp.equals(name))
                medicines.get(i).addAmount(1);
            if(i+1 == medicines.size() && !temp.equals(name))
                medicines.add(new Medicine(name));
        }
    }

    /**
     * Adds medicine name and the amount into the inventory
     * @param name Name of the medicine
     * @param amount the amount of medicine
     */
   public void addMedicine(String name, int amount){
       String temp;
       for(int i = 0; i < medicines.size(); i++ ) {
           temp = medicines.get(i).getMediName();
           if(temp.equals(name))
               medicines.get(i).addAmount(amount);
           if(i+1 == medicines.size() && !temp.equals(name))
               medicines.add(new Medicine(name, amount));
       }
   }

    /**
     * Remove the entire medicine stock
     * @param name target name medicine
     */
   public void removeWhole(String name){
       String temp;
       for(int i = 0; i < medicines.size(); i++ ){
           temp = medicines.get(i).getMediName();
           if(temp.equals(name))
               medicines.remove(i);
           if(i+1 == medicines.size() && !temp.equals(name)) {
               System.out.println("Medicine: " + name + " does not exist in the inventory.");
               return;
           }
       }
   }

    /**
     * Deduct a certain amount of the target medicine. If the amount is more then inventory it will remove all the amount.
     * @param name The target medicine.
     * @param num The amount that is to be remove.
     */
   public void deduction(String name, int num){
        String temp;
        for(int i = 0; i < medicines.size(); i++ ){
            temp = medicines.get(i).getMediName();
            if(temp.equals(name)){
                if (num > medicines.get(i).getAmount())
                    medicines.get(i).removeAmount(medicines.get(i).getAmount());
                else
                medicines.get(i).removeAmount(num);
            }
            if(i+1 == medicines.size() && !temp.equals(name)) {
                System.out.println("Medicine: " + name + " does not exist in the inventory or OUT OF STOCK.");
                return;
            }
        }
   }

    /**
     * Gets the amount of the target medicine
     * @param name the name of the medicine
     * @return amount of the target medicine
     */
    public int getAmount(String name){
        String temp;
        for(int i = 0; i < medicines.size(); i++ ){
            temp = medicines.get(i).getMediName();
            if(temp.equals(name))
                return medicines.get(i).getAmount();
            else
                return -1;
        }
        return -1;
    }

    /**
     * Prints the entire inventory list. Format(Name: medicineName Amount: medicineAmount)
     */
   public void printList(){
       System.out.println("Inventory: \n");
       for(int i = 0; i < medicines.size(); i++ ){
           System.out.println("Name: " + medicines.get(i).getMediName() + " Amount: " + medicines.get(i).getAmount() + " \n");
       }
   }

}
