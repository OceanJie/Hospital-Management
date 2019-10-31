import java.util.StringTokenizer;

/**
 * Pharmacy class
 * @author Brendan Yeong
 */
public class Pharmacy {
    private Inventory inventory;

    /**
     * Creates a pharmacy with a default inventory
     */
    public Pharmacy(){
        inventory = new Inventory();
    }

    /**
     * give patient medicine according to the prescription with a format(medicineName amount medicineName amount .....) and print an invoice.
     * @param prescription patient's prescription
     */
    public boolean giveMedicine(String prescription){
        String name;
        String temp;
        int amount;
        StringTokenizer prescriptionList = new StringTokenizer(prescription);
        while (prescriptionList.hasMoreTokens())
        {
            name = prescriptionList.nextToken();
            temp = prescriptionList.nextToken();
            if(isNumeric(temp)) {
                amount = Integer.parseInt(temp);
            }
            else{
                System.out.println("Expected: medicineName amount medicineName amount .....");
                return false;
            }
            inventory.deduction(name, amount);
    }
        printInvoice(prescription);
        return true;
    }

    /**
     * Helper method that prints an invoice
     * @param prescription patient's prescription
     */
    private void printInvoice(String prescription){
        StringTokenizer preList = new StringTokenizer(prescription);
        String name;
        String amount;
        System.out.println("Invoice: ");
        while (preList.hasMoreTokens()) {
            name = preList.nextToken();
            amount = preList.nextToken();

            System.out.println("Medicine: " + name + " " + "amount: " + amount);
        }

    }

    /**
     * Helper method that check if it the string is a numeric number
     * @param strNum a string that is to be checked
     * @return true if it is numeric else false
     */
    private boolean isNumeric(String strNum) {
        try {
            int d = Integer.parseInt(strNum);
        } catch (NumberFormatException | NullPointerException nfe) {
            return false;
        }
        return true;
    }

    /**
     * For pharmacy to print the entire inventory list
     */
    private void printInventoryList(){
        inventory.printList();
    }

    /**
     * For pharmacy to add a medicine in to the database
     * @param name medicine name that is to be added
     */
    private void addMedicine(String name){
        inventory.addMedicine(name);
    }

    /**
     * For pharmacy to add a medicine with a certain amount in to the database
     * @param name medicine name that is to be added
     * @param amount amount that is to be added
     */
    private void addMedicine(String name, int amount){
        inventory.addMedicine(name, amount);
    }

    /**
     * For pharmacy to delete or empty the whole inventory of a targeted medicine
     * @param name target name of the medicine that is to be removed
     */
    private void emptyMedicineInventory(String name){
        inventory.removeWhole(name);
    }
}
