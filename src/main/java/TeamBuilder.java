package main.java;

import main.java.staff.*;

import java.util.ArrayList;
import java.util.Scanner;

public class TeamBuilder {

    private ArrayList<Admin> admins;
    private ArrayList<Doctor> doctors;
    private ArrayList<Nurse> nurses;
    private ArrayList<SecurityGuard> securityGuards;
    private ArrayList<Surgeon> surgeons;
    private ArrayList<Team> teams;

    public TeamBuilder(){
        admins = new ArrayList<>();
        initAdmins();
        doctors = new ArrayList<>();
        initDoctors();
        nurses = new ArrayList<>();
        initNurses();
        securityGuards = new ArrayList<>();
        initSecurityGuards();
        surgeons = new ArrayList<>();
        initSurgeons();
        teams = new ArrayList<>();
    }

    private void initAdmins() {
        admins.add(new Admin(1, "Axel", "1234", 2000, 0));
        admins.add(new Admin(2, "James", "1234", 2000, 0));
        admins.add(new Admin(3, "Duke", "1234", 2000, 0));
        admins.add(new Admin(4, "Xenon", "1234", 2000, 0));
        admins.add(new Admin(5, "Adam", "1234", 2000, 0));
    }

    private void initDoctors() {
        doctors.add(new Doctor("d1", "Brendan", 10000));
        doctors.add(new Doctor("d2", "Nicholas", 10000));
        doctors.add(new Doctor("d3", "Bojun", 10000));
        doctors.add(new Doctor("d4", "Jay", 10000));
        doctors.add(new Doctor("d5", "Roxas", 10000));
    }

    private void initNurses() {
        nurses.add(new Nurse("Natalie", 5000));
        nurses.add(new Nurse("Sora", 5000));
        nurses.add(new Nurse("Gwen", 5000));
        nurses.add(new Nurse("Kairi", 5000));
        nurses.add(new Nurse("Lily", 5000));

    }

    private void initSecurityGuards() {
        securityGuards.add(new SecurityGuard(10, "Jason", "1234" ,3500 , 0));
        securityGuards.add(new SecurityGuard(20, "Leonard", "1234" ,3500 , 0));
        securityGuards.add(new SecurityGuard(30, "Xigbar", "1234" ,3500 , 0));
        securityGuards.add(new SecurityGuard(40, "Riku", "1234" ,3500 , 0));
        securityGuards.add(new SecurityGuard(50, "Harry", "1234" ,3500 , 0));
    }

    private void initSurgeons() {
        surgeons.add(new Surgeon("Unknown", 12000));
        surgeons.add(new Surgeon("Linus", 12000));
        surgeons.add(new Surgeon("Kyle", 12000));
        surgeons.add(new Surgeon("Luke", 12000));
        surgeons.add(new Surgeon("Brian", 12000));
    }

    private void printAdmin(){
        for (Admin aTemp: admins) {
            System.out.println("Admins: " + aTemp.getName());
        }
    }

    private void printDoctor(){
        for (Doctor dTemp: doctors) {
            System.out.println("Doctor: " + dTemp.getName());
        }
    }

    private void printNurse(){
        for (Nurse nTemp: nurses) {
            System.out.println("Nurse: " + nTemp.getName());
        }
    }

    private void printSecurityGuard(){
        for (SecurityGuard sGTemp: securityGuards) {
            System.out.println("SecurityGuard: " + sGTemp.getName());
        }
    }

    private void printSurgeon(){
        for (Surgeon sTemp: surgeons) {
            System.out.println("Surgeon: " + sTemp.getName());
        }
    }

    public boolean operate(){
        while(true) {
            System.out.println("+---------------------------+");
            System.out.println("1. Build Team and add a team");
            System.out.println("2. Add staff to a selected team");
            System.out.println("3. Create a project in the team");
            System.out.println("4. Add staff to a project in a team");
            System.out.println("5. Calculate total cost of the team");
            System.out.println("6. Calculate a project in a selected team");
            System.out.println("7. Remove a staff from the team");
            System.out.println("8. Remove a staff from a project in a selected team");
            System.out.println("9. view all teams");
            System.out.println("10. View a selected team");
            System.out.println("11. View a selected team details");
            System.out.println("12. Remove a project in a team");
            System.out.println("13. Remove a team");
            System.out.println("\n99. Shut Down");
            System.out.println("+---------------------------+");

            Scanner scan = new Scanner(System.in);
            int option = 0;
            option = scan.nextInt();

            switch (option) {
                case 1:
                    buildTeam();
                    break;

                case 2:
                    addStafftoSelectedTeam();
                    break;

                case 3:
                    createProjectInATeam();
                    break;

                case 4:
                    addStaffToProjects();
                    break;

                case 5:
                    calculateTotalCostOfTeam();
                    break;

                case 6:
                    calcualteAProjectCostInSelectedTeam();
                    break;

                case 7:
                    removeStaffFromTeam();
                    break;

                case 8:
                    removeStaffFromProject();
                    break;

                case 9:
                    printTeams();
                    break;

                case 10:
                    printSelectedTeam();
                    break;

                case 11:
                    printSelectedTeamDetails();
                    break;

                case 12:
                    removeProjectInATeam();
                    break;

                case 13:
                    removeTeam();
                    break;

                case 99:
                    System.out.println("Shutting down...");
                    scan.close();
                    return true;

                default:
                    System.out.println("Error no option available\n");
                    break;
            }

        }
    }

    public boolean buildTeam(){
        Scanner scan = new Scanner(System.in);
        String startTime = "";
        String endTime = "";

        System.out.println("Give a shift time. eg.(13:00 - 23:00) \n");
        System.out.print("Provide a start time: ");
        startTime = scan.nextLine();
        System.out.print("Provide a end time: ");
        endTime = scan.nextLine();
        System.out.println("Successfully created a team and it has added into the list\n");

        teams.add(new Team(startTime, endTime));

        return true;
    }


    public boolean addStafftoSelectedTeam(){
        if(!teams.isEmpty()) {
            Scanner scan = new Scanner(System.in);
            int teamChoice = 0;
            int option = 0;
            String name = "";
            int added = 0;
            System.out.println("\n");
            System.out.println("Pick a team for the staff to be added");
            printTeams();
            teamChoice = scan.nextInt();
//            option = scan.nextInt();
            while(added == 0) {
                System.out.println("Which staff members you wan to add:");
                System.out.println(" 1. Admin\n 2. Doctor\n 3. Nurse\n 4. Security Guard\n 5. Surgeon\n 6. DONE\n");
                option = scan.nextInt();

                if (option == 1) {
                    printAdmin();
                    while(true) {
                        scan.nextLine();
                        System.out.println("Choose a someone to be added. (name)");
                        name = scan.nextLine();
                        for (Admin aTemp: admins) {
                            if(aTemp.getName().equals(name)) {
                                teams.get(teamChoice - 1).addStaff("Admin", name, aTemp.getSalary().getBaseSalary());
                                break;
                            }
                        }
                        break;
                    }
                }
                if (option == 2) {
                    printDoctor();
                    while(true) {
                        scan.nextLine();
                        System.out.println("Choose a someone to be added. (name)");
                        name = scan.nextLine();
                        for (Doctor dTemp: doctors) {
                            if(dTemp.getName().equals(name)) {
                                teams.get(teamChoice - 1).addStaff("Doctor", name, dTemp.getSalary().getBaseSalary());
                                break;
                            }
                        }
                        break;
                    }

                }
                if (option == 3) {
                    printNurse();
                    while(true) {
                        scan.nextLine();
                        System.out.println("Choose a someone to be added. (name)");
                        name = scan.nextLine();
                        for (Nurse nTemp: nurses) {
                            if(nTemp.getName().equals(name)) {
                                teams.get(teamChoice - 1).addStaff("Nurse", name, nTemp.getSalary().getBaseSalary());
                                break;
                            }
                        }
                        break;
                    }

                }
                if (option == 4) {
                    printSecurityGuard();
                    while(true) {
                        scan.nextLine();
                        System.out.println("Choose a someone to be added. (name)");
                        name = scan.nextLine();
                        for (SecurityGuard sGTemp: securityGuards) {
                            if(sGTemp.getName().equals(name)) {
                                teams.get(teamChoice - 1).addStaff("Security Guard", name, sGTemp.getSalary().getBaseSalary());
                                break;
                            }
                        }
                        break;
                    }

                }
                if (option == 5) {
                    printSurgeon();
                    while(true) {
                        scan.nextLine();
                        System.out.println("Choose a someone to be added. (name)");
                        name = scan.nextLine();
                        for (Surgeon sTemp: surgeons) {
                            if(sTemp.getName().equals(name)) {
                                teams.get(teamChoice - 1).addStaff("Surgeon", name, sTemp.getSalary().getBaseSalary());
                                break;
                            }
                        }
                        break;
                    }
                }
                if (option == 6) {
                    System.out.println("You are done adding");
                    added = 1;
                    return true;
                }
                if (option > 6 || option < 1){
                    System.out.println("pick an option again");
                }
            }

        }
        else {
            System.out.println("There are no teams");
            return false;
        }

        return false;
    }

    public boolean createProjectInATeam(){
        if(!teams.isEmpty()) {
            Scanner scan = new Scanner(System.in);
            int teamChoice = 0;
            String description = "";
            String start = "";
            String end = "";
            System.out.println("Pick a team to create a project into");
            printTeams();
            teamChoice = scan.nextInt();
            scan.nextLine();
            System.out.print("Give the project a name or description: ");
            description = scan.nextLine();
            System.out.print("Enter a Start time (ie. 13:00): ");
            start = scan.nextLine();
            System.out.print("Enter a End time (ie. 16:00): ");
            end = scan.nextLine();
            teams.get(teamChoice - 1).createProject(description, start, end );
            return true;
        }
        else{
            System.out.println("There are no teams now. Please create one in option 1");
            return false;
        }
    }

    public boolean addStaffToProjects(){
        if(!teams.isEmpty()) {
            Scanner scan = new Scanner(System.in);
            int teamChoice = 0;
            int nChoice;
            int pChoice;
            System.out.println("Pick a team to manage ie add a staff into a project");
            printTeams();
            teamChoice = scan.nextInt();
            teams.get(teamChoice - 1).printProjectsList();
            System.out.println("pick a project to manage ie.add a staff");
            pChoice = scan.nextInt();
            System.out.println("Pick the person to be added into the project (number)");
            teams.get(teamChoice - 1).printPoeplesList();
            nChoice = scan.nextInt();
            teams.get(teamChoice - 1).addPeopleToProject(pChoice, nChoice);
            return true;
        } else{
            System.out.println("There are no teams now. Please add one");
        }
        return false;
    }

    public void calculateTotalCostOfTeam(){
        if(!teams.isEmpty()) {
            Scanner scan = new Scanner(System.in);
            int teamChoice = 0;
            System.out.println("Select a team to calculate the cost");
            printTeams();
            teamChoice = scan.nextInt();
            System.out.println("The team's total cost: " + teams.get(teamChoice - 1).calculateTotalCost());
        } else{
        System.out.println("There are no teams now. Please add one");
    }
    }

    public void calcualteAProjectCostInSelectedTeam(){
        if(!teams.isEmpty()) {
            Scanner scan = new Scanner(System.in);
            int teamChoice = 0;
            int pChoice;
            System.out.println("Select a team to view a single project cost");
            printTeams();
            teamChoice = scan.nextInt();
            teams.get(teamChoice - 1).printProjectsList();
            System.out.println("Select a project to view the cost");
            pChoice = scan.nextInt();
            System.out.println("The cost of the project is " + teams.get(teamChoice - 1).getProjectCost(pChoice));
        } else{
            System.out.println("There are no teams now. Please add one");
        }
    }

    public boolean removeStaffFromTeam(){
        if(!teams.isEmpty()) {
            Scanner scan = new Scanner(System.in);
            int teamChoice;
            String nChoice;
            System.out.print("Pick a team to manage ie remove a staff from a selected team");
            printTeams();
            teamChoice = scan.nextInt();
            teams.get(teamChoice - 1).printPoeplesList();
            System.out.println("choose a staff member to be removed from the team");
            nChoice = scan.nextLine();
            if(teams.get(teamChoice - 1).removeStaff(nChoice)) {
                System.out.println("Staff member has been removed from the team");
                return true;
            }
        } else{
            System.out.println("There are no teams now. Please add one");
        }
        return false;
    }

    public boolean removeStaffFromProject(){
        if(!teams.isEmpty()) {
            Scanner scan = new Scanner(System.in);
            int teamChoice = 0;
            String nChoice;
            int pChoice;
            System.out.print("Pick a team to manage ie. remove a staff into a project");
            printTeams();
            teamChoice = scan.nextInt();
            teams.get(teamChoice - 1).printProjectsList();
            System.out.println("pick a project to manage ie.remove a staff into a project");
            pChoice = scan.nextInt();
            System.out.println("Pick the person's name to be remove in the project");
            teams.get(teamChoice - 1).printSelectedProjectPplList(pChoice);
            nChoice = scan.nextLine();
            if(teams.get(teamChoice - 1).removePeopleFromProject(pChoice, nChoice)) {
                System.out.println("person has been successfully removed");
                return true;
            }
        } else{
            System.out.println("There are no teams now. Please add one");
        }
        return false;
    }

    public void printTeams(){
        int count = 1;
        if(!teams.isEmpty()){
            for(Team tTemp: teams){
                System.out.print(count + ". ");
                tTemp.printShift();
                count ++;
            }
        }
        System.out.println("\n");
    }

    public void printSelectedTeam(){
        Scanner scan = new Scanner(System.in);
        int choice = 0;
        System.out.println("\n");
        System.out.println("Select a team to view details");
        printTeams();
        choice = scan.nextInt();

        teams.get(choice - 1).printPoeplesList();
    }

    public void printSelectedTeamDetails(){
        Scanner scan = new Scanner(System.in);
        int choice;
        System.out.println("\n");
        System.out.println("Select a team to view employee");
        printTeams();
        choice = scan.nextInt();

        teams.get(choice - 1).showDetail();
    }

    public boolean removeProjectInATeam(){
        if(!teams.isEmpty()) {
            Scanner scan = new Scanner(System.in);
            int choice;
            int pChoice;
            System.out.println("\n");
            printTeams();
            System.out.println("Select a team");
            choice = scan.nextInt();
            teams.remove(choice - 1).printProjectsList();
            System.out.println("Select a project to be removed");
            pChoice = scan.nextInt();
            if(teams.get(choice - 1).deleteProject(pChoice)) {
                System.out.println("Project was successfully removed");
                return true;
            }
        }
        else{
            System.out.println("There are no teams now. Please add one");
            return false;
        }
        return false;
    }

    public boolean removeTeam(){
        if(!teams.isEmpty()) {
            Scanner scan = new Scanner(System.in);
            int choice = 0;
            System.out.println("\n");
            printTeams();
            System.out.println("Select a team to be remove");
            choice = scan.nextInt();
            teams.remove(choice - 1);
            System.out.println("Team was successfully removed");
            printTeams();
            return true;
        }
        else{
            System.out.println("There are no teams now. Please add one");
            return false;
        }
    }

    //Testing only
    public static void main(String[] args) {
        //Default cafeteria
        TeamBuilder teamBuilder = new TeamBuilder();
        teamBuilder.operate();
    }
}
