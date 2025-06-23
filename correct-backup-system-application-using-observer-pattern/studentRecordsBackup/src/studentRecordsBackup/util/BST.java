package studentRecordsBackup.util;

import java.util.ArrayList;
import java.util.List;

public class BST {
    public Node root;
    List<Node> records;

    public BST() {
        root = null;
        records = new ArrayList<>();
    }

    public void insert(int bNumber, String firstName) {
        root = insert(root, bNumber, firstName);
        records.add(new Node(bNumber, firstName));
    }

    private Node insert(Node node, int bNumber, String firstName) {
        if (node == null) {
            return new Node(bNumber, firstName);
        }
        if (bNumber < node.getBNumber()) {
            node.left = insert(node.left, bNumber, firstName);
        } else if (bNumber >= node.getBNumber()) {
            node.right = insert(node.right, bNumber, firstName);
        }
        return node;
    }

    public String inorderTraversal() {
        String result = inorderTraversal(root);
        return trimTrailingCommas(result);
    }
    
    public String inorderTraversal(Node node) {
        StringBuilder result = new StringBuilder();
        if (node != null) {
            result.append(inorderTraversal(node.left));
            result.append(node.toString());
            result.append(inorderTraversal(node.right));
        }
        return result.toString();
    }
    
    private String trimTrailingCommas(String input) {
        int length = input.length();
        int index = length - 1;
    
        while (index >= 0 && (input.charAt(index) == ' ' || input.charAt(index) == ',')) {
            index--;
        }
    
        if (index < length - 1) {
            return input.substring(0, index + 1);
        } else {
            return input;
        }
    }    
    
    public int calculateSum(List<Node> records) {
        int sum = 0;
        for (Node record : records) {
            sum += record.getBNumber();
        }
        return sum;
    }

    public String printSumOfBNumbers() {
        int mainTreeSum = calculateSum(records);
        int backup1Sum = calculateSum(records);
        int backup2Sum = calculateSum(records);
        int backup3Sum = calculateSum(records);
    
        StringBuilder result = new StringBuilder();
        result.append("\nBST: " + mainTreeSum);
        result.append("\nBackup-1: " + backup1Sum);
        result.append("\nBackup-2: " + backup2Sum);
        result.append("\nBackup-3: " + backup3Sum);
    
        return result.toString();
    }    

    public void updateNodeValues(int incrementValue) {
        updateNodeValues(root, incrementValue);
    }
    
    private void updateNodeValues(Node node, int incrementValue) {
        if (node != null) {
            node.setBNumber(node.getBNumber() + incrementValue);
            node.notifyAllObservers(incrementValue);
            updateNodeValues(node.left, incrementValue);
            updateNodeValues(node.right, incrementValue);
        }
    }    

    public int calculateSum1() {
        return calculateSum1(root);
    }
    
    private int calculateSum1(Node node) {
        if (node == null) {
            return 0;
        }
        return node.getBNumber() + calculateSum1(node.left) + calculateSum1(node.right);
    }
    
    public Node getNode(int bNumber) {
        return getNode(root, bNumber);
    }
    
    private Node getNode(Node node, int bNumber) {
        if (node == null) {
            return null;
        }
        if (bNumber == node.getBNumber()) {
            return node;
        } else if (bNumber < node.getBNumber()) {
            return getNode(node.left, bNumber);
        } else {
            return getNode(node.right, bNumber);
        }
    } 

    public List<Node> getRecords() {
        return records;
    }

    public void notifyAllObservers(int incrementValue) {
        notifyAllObservers(root, incrementValue);
    }

    private void notifyAllObservers(Node node, int incrementValue) {
        if (node != null) {
            node.notifyAllObservers(incrementValue);

            notifyAllObservers(node.left, incrementValue);
            notifyAllObservers(node.right, incrementValue);
        }
    }
}
