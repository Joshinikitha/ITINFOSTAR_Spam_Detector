import java.util.Scanner;

// Custom Exception
class InvalidMessageException extends Exception {
    InvalidMessageException(String msg) {
        super(msg);
    }
}

class SpamDetector {
    String[] spamWords;
    int threshold;

    // Default Constructor
    SpamDetector() {
        spamWords = new String[]{"win", "free", "offer", "urgent", "money", "prize", "click"};
        threshold = 2;
    }

    // Parameterized Constructor
    SpamDetector(String[] words, int threshold) {
        this.spamWords = words;
        this.threshold = threshold;
    }

    // Method to analyze message
    void analyzeMessage(String message) throws InvalidMessageException {
        if (message == null || message.trim().isEmpty()) {
            throw new InvalidMessageException("Message cannot be empty!");
        }

        message = message.toLowerCase();
        String[] words = message.split(" ");

        int spamCount = 0;

        System.out.println("\n--- Word Analysis ---");

        // Check each word
        for (int i = 0; i < words.length; i++) {
            int freq = 0;

            // Count frequency
            for (int j = 0; j < words.length; j++) {
                if (words[i].equals(words[j])) {
                    freq++;
                }
            }

            // Print frequency once
            boolean printed = false;
            for (int k = 0; k < i; k++) {
                if (words[i].equals(words[k])) {
                    printed = true;
                    break;
                }
            }

            if (!printed) {
                System.out.println(words[i] + " -> " + freq);
            }

            // Check spam words
            for (int s = 0; s < spamWords.length; s++) {
                if (words[i].contains(spamWords[s])) {
                    spamCount++;
                }
            }
        }

        // Calculate spam percentage
        double spamScore = ((double) spamCount / words.length) * 100;

        System.out.println("\nSpam Score: " + spamScore + "%");

        // Final result
        if (spamCount >= threshold) {
            System.out.println("⚠️ SPAM DETECTED!");
        } else {
            System.out.println("✅ SAFE MESSAGE");
        }
    }
}

public class Main1 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        SpamDetector detector = new SpamDetector();

        int choice;

        do {
            System.out.println("\n===== Spam Detector Menu =====");
            System.out.println("1. Check Message");
            System.out.println("2. Exit");
            System.out.print("Enter choice: ");

            choice = sc.nextInt();
            sc.nextLine(); // consume newline

            switch (choice) {
                case 1:
                    System.out.print("Enter your message: ");
                    String msg = sc.nextLine();

                    try {
                        detector.analyzeMessage(msg);
                    } catch (InvalidMessageException e) {
                        System.out.println("Error: " + e.getMessage());
                    }
                    break;

                case 2:
                    System.out.println("Exiting program...");
                    break;

                default:
                    System.out.println("Invalid choice!");
            }

        } while (choice != 2);

        sc.close();
    }
}