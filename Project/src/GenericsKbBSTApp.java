// Student Number: MRRJOS007 
// 28 February 2024
// Joshua Murray
// Program reads in text file and creates a BST that allows the user to
// search for a term or sentence and can update text file
import java.io.*;
import java.util.*;

public class GenericsKbBSTApp {
    private int choice;
    private Scanner scanner = new Scanner(System.in);
    private BinarySearchTree<Entry> tree = new BinarySearchTree<>();
    private String filePath;
    public static void main(String[] args) {
        GenericsKbBSTApp App = new GenericsKbBSTApp();
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
                    System.out.println("\nInvalid choice. Please enter a number between 1 and 5.");
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

    private void searchSentence() {
        System.out.print("Enter the term to search: ");
        scanner.nextLine();
        String term = scanner.nextLine();
        System.out.print("Enter the statement to search for: ");
        String statement = scanner.nextLine();
        //search through BST to find term and sentence then print statement with score
        Entry entry = new Entry(term, statement, null);
        if (tree.find(entry) != null){
            if (tree.find(entry).data.getTerm().equals(term) && tree.find(entry).data.getStatement().equals(statement)){
                System.out.print("\nStatement found: " + statement + " (Confidence score: " + tree.find(entry).data.getScore() + ")\n\n");    
            }
            else{
                System.out.println("\nCould not find term or statement in knowledge base\n");
            }
        }
        else{
            System.out.println("\nCould not find term or statement in knowledge base\n");
        }
    }

    private void searchTerm() {
        System.out.print("Enter the term to search: ");
        scanner.nextLine();
        String term = scanner.nextLine();
        //search through BST to find term and print statement with score
        Entry entry = new Entry(term, null, null);
        if (tree.find(entry) != null){
            BinaryTreeNode<Entry> node = tree.find(entry);
            System.out.print("\nStatement found: " + node.data.getStatement() + " (Confidence score: " + node.data.getScore() + ")\n\n");
        }
        else{
            System.out.println("\nCould not find term in knowledge base\n");
        }
    }

    private void addStatement() {
        System.out.print("Enter the term: ");
        scanner.nextLine();
        String term = scanner.nextLine();
        System.out.print("Enter the statement: ");
        String statement = scanner.nextLine();
        System.out.print("Enter the confidence score: ");
        Double newScore = scanner.nextDouble();
        //find term in BST and update with new statement only if score is higher
        Entry entry = new Entry(term, null, null);
        if (tree.find(entry) != null){
            if (tree.find(entry).data.getScore() < newScore){
                tree.find(entry).data.setStatement(statement);
                tree.find(entry).data.setScore(newScore);
                System.out.println("\nStatement for " + term + " has been updated\n");
                writeTextFile(filePath, tree);
            }
            else{
                System.out.println("\nDid not update as confidence score is less\n");
            }
        }
        else{
            System.out.println("\nCould not find term in knowledge base\n");
        }
    }

    private void loadFile() {
        try{
            System.out.print("Enter file name: ");
            scanner.nextLine(); 
            filePath = scanner.nextLine();
            BufferedReader fileChoice = new BufferedReader(new FileReader(filePath));
            String line;
            while ((line = fileChoice.readLine()) != null){
                String[] parts = line.split("\t");
                Double score = Double.parseDouble(parts[2]);
                Entry entry = new Entry(parts[0], parts[1], score);
                tree.insert(entry); 
            }
            System.out.println("\nKnowledge base loaded successfully.\n");
            fileChoice.close();
        }
        catch (IOException e){
            System.out.println("\nCould not find file.\n");
        }
    }

    private static void writeTextFile(String filePath, BinarySearchTree<Entry> bst) {
        try { 
            BufferedWriter writer = new BufferedWriter(new FileWriter(filePath));
            List<BinaryTreeNode<Entry>> list = bst.levelOrder();
            for (int i = 0; i < bst.getSize(); i++){
                writer.write(list.get(i).toString());
                writer.newLine();
            }
            writer.close();
        } catch (IOException e) {
            System.out.println("Could not write file, error occured");;
        }
    }
}
