// GitHub repository link: https://github.com/tabemn/Binary-Search-Tree.git

import java.util.Scanner;

/**
 * Builds a binary search tree (BST) from characters entered by the user.
 * A user may type "null" to skip a value. The program displays a menu
 * for inorder, preorder, and postorder traversal until Exit is selected.
 */

class Node {
    char data;
    Node left;
    Node right;

    /** Creates a node containing a character. */

    Node(char data) {
        this.data = data;
        this.left = null;
        this.right = null;
    }
}

public class BSTExample {

    /**
     * Inserts a character into the BST.
     * Smaller values go left and larger values go right.
     * Duplicate characters are not inserted, as in the provided example.
     */

    static Node insert(Node root, char value) {
        if (root == null) {
            return new Node(value);
        }

        if (value < root.data) {
            root.left = insert(root.left, value);
        } else if (value > root.data) {
            root.right = insert(root.right, value);
        }
        return root;
    }

    /** Displays nodes in left -> root -> right order. */

    static void inorder(Node root) {
        if (root == null) return;
        inorder(root.left);
        System.out.print(root.data + " ");
        inorder(root.right);
    }

    /** Displays nodes in root -> left -> right order. */

    static void preorder(Node root) {
        if (root == null) return;
        System.out.print(root.data + " ");
        preorder(root.left);
        preorder(root.right);
    }

    /** Displays nodes in left -> right -> root order. */

    static void postorder(Node root) {
        if (root == null) return;
        postorder(root.left);
        postorder(root.right);
        System.out.print(root.data + " ");
    }

    /** Reads a nonnegative integer, asking again if the entry is invalid. */

    static int readCount(Scanner scanner) {
        while (true) {
            System.out.print("How many character values will you enter? ");
            String input = scanner.nextLine().trim();
            try {
                int count = Integer.parseInt(input);
                if (count >= 0) {
                    return count;
                }
            } catch (NumberFormatException ignored) {
                // Invalid input; show the message below and ask again.
            }
            System.out.println("Please enter a nonnegative whole number.");
        }
    }

    /**
     * Reads one character or the text "null".
     * Returns null when the user wants to skip this array position.
     */

    static Character readCharacter(Scanner scanner, int position) {
        while (true) {
            System.out.print("Enter character " + position + " (or null to skip): ");
            String input = scanner.nextLine().trim();
            if (input.equalsIgnoreCase("null")) {
                return null;
            }
            if (input.length() == 1) {
                return input.charAt(0);
            }
            System.out.println("Please enter exactly one character or null.");
        }
    }

    /** Displays choices until the user selects option 4. */

    static void runMenu(Scanner scanner, Node root) {
        while (true) {
            System.out.println("\n1. Inorder traversal");
            System.out.println("2. Preorder traversal");
            System.out.println("3. Postorder traversal");
            System.out.println("4. Exit");
            System.out.print("Select an option (1-4): ");

            String choice = scanner.nextLine().trim();
            switch (choice) {
                case "1":
                    System.out.print("Inorder: ");
                    inorder(root);
                    System.out.println();
                    break;
                case "2":
                    System.out.print("Preorder: ");
                    preorder(root);
                    System.out.println();
                    break;
                case "3":
                    System.out.print("Postorder: ");
                    postorder(root);
                    System.out.println();
                    break;
                case "4":
                    System.out.println("Program ended.");
                    return;
                default:
                    System.out.println("Invalid choice. Please select 1, 2, 3, or 4.");
            }
        }
    }

    /** Collects values, builds the BST using insert, and opens the menu. */

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int count = readCount(scanner);

        // Character (not char) allows null entries in the array.
        Character[] data = new Character[count];
        for (int i = 0; i < count; i++) {
            data[i] = readCharacter(scanner, i + 1);
        }

        Node root = null;
        for (Character value : data) {
            if (value != null) {
                root = insert(root, value);
            }
        }

        runMenu(scanner, root);
        scanner.close();
    }
}
