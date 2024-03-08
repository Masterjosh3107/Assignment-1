// Student Number: MRRJOS007 
// 28 February 2024
// Joshua Murray
// Program reads in text file and creates a 1D array that allows the user to
// search for a term or sentence and can update text file
import java.util.Scanner;
import java.io.*;

 public class GenericsKbArrayApp {
    private Scanner scanner = new Scanner(System.in);
    private String[] items;
    private int choice;
    private String filePath;
    
    /** 
     * @param args
     */
    public static void main(String[] args) {
        GenericsKbArrayApp App = new GenericsKbArrayApp();
        App.run();
    }
    
    private void run(){
        Boolean running = true;
        while (running == true){
            printStatements();
            switch (choice){
                case 1:
                loadFile();
                break;
                case 2:
                addStatement();
                break;
                case 3:
                searchTerm();
                break;
                case 4:
                searchSentence();
                break;
                case 5:
                running = false;
                break;
                default:
                System.out.println("Invalid choice. Please enter a number between 1 and 5.");
            }
        }
    }

    private void printStatements(){
        System.out.print("Choose an action from the menu:\r\n" + //
        "1. Load a knowledge base from a file\r\n" + //
        "2. Add a new statement to the knowledge base\r\n" + //
        "3. Search for an item in the knowledge base by term\r\n" + //
        "4. Search for a item in the knowledge base by term and sentence\r\n" + //
        "5. Quit\r\n\n" + // +
        "Enter your choice: ");
        choice = scanner.nextInt();
        System.out.println();
    }

    private void loadFile(){
        try{
            int iCount = 0;
            System.out.print("Enter file name: ");
            scanner.nextLine(); 
            filePath = scanner.nextLine();
            BufferedReader fileChoice = new BufferedReader(new FileReader(filePath));
            String line;
            while ((line = fileChoice.readLine()) != null){
                iCount++;
            }
            items = new String[iCount];
            iCount = 0;

            BufferedReader fileChoice2 = new BufferedReader(new FileReader(filePath));
            StringBuilder fileContent = new StringBuilder();

            while ((line = fileChoice2.readLine()) != null){
                fileContent.append(line);
                items[iCount] = line;
                iCount++;
            }
            System.out.println("\nKnowledge base loaded successfully.\n");
            fileChoice.close();
            fileChoice2.close();
        }
        catch (IOException e){
            System.out.println("\nCould not find file.\n");
        }
    }

    private void addStatement(){
        System.out.print("Enter the term: ");
        scanner.nextLine();
        String term = scanner.nextLine();
        System.out.print("Enter the statement: ");
        String statement = scanner.nextLine();
        System.out.print("Enter the confidence score: ");
        Double newScore = scanner.nextDouble();
        //find term in array and update with new statement only if score is higher
            Boolean contains = false;
            Boolean bCheck = false;
            for (int i = 0; i<items.length;i++){
                String[] parts = items[i].split("\t");
                if (parts[0].equals(term)){
                    Double oldScore = Double.parseDouble(parts[2]);
                    if (newScore >= oldScore){
                        items[i] = parts[0] + "\t" + statement + "\t" + newScore;
                    }
                    else{
                        System.out.println("\nDid not update as confidence score is less\n");
                        bCheck = true;
                    }
                    contains = true;
                    break;
                } 
            }
            if (contains == false){
                System.out.println("\nCould not find term in knowledge base\n");
            }
            else if (bCheck == false){
                System.out.println("\nStatement for " + term + " has been updated\n");
                writeTextFile(filePath, items);
            }
           
    }
    
    /** 
     * @param filePath
     * @param dataArray
     */
    private static void writeTextFile(String filePath, String[] dataArray) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            for (String line : dataArray) {
                writer.write(line);
                writer.newLine();
            }
            writer.close();
        } catch (IOException e) {
            System.out.println("Could not write file, error occured");;
        }
    }

    private void searchTerm(){
        System.out.print("Enter the term to search: ");
        scanner.nextLine();
        String term = scanner.nextLine();
        //search through array to find term and print statement with score
        Boolean contains = false;
        for (String line : items){
            String[] parts = line.split("\t");
            if (parts[0].equals(term)){
                Double score = Double.parseDouble(parts[2]);       
                System.out.print("\nStatement found: " + parts[1] + " (Confidence score: ");
                System.out.printf("%.2f", score);
                System.out.print(")\n\n");
                contains = true;
                break;
            }           
        }
        if (contains == false){
            System.out.println("\nCould not find term in knowledge base\n");
        }
    }

    private void searchSentence(){
        System.out.print("Enter the term to search: ");
        scanner.nextLine();
        String term = scanner.nextLine();
        System.out.print("Enter the statement to search for: ");
        String statement = scanner.nextLine();
        //search through array to find term and sentence then print statement with score
        Boolean contains = false;
        for (String line : items){
            String[] parts = line.split("\t");
            if (parts[0].equals(term) && parts[1].equals(statement)){
                Double score = Double.parseDouble(parts[2]);
                System.out.print("\nThe statement was found and has a confidence score of ");
                System.out.printf("%.2f", score);
                System.out.print(".\n\n");;
                contains = true;
                break;
            }    
        }
        if (contains == false){
            System.out.println("\nCould not find term or sentence in knowledge base\n");
        }
    }

 }
