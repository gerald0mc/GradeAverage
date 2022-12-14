import java.util.*;

public class Main {
    // The main method required to make the application run.
    public static void main(String[] args) {
        // A boolean to make it so the application looks forever until finished. (Same use as a thread.)
        boolean complete = false;
        // Current stage of the application. Is changed multiple times in use so a very crucial variable.
        STAGE currentStage = STAGE.INPUT;
        // A HashMap that is bound to accept a String and Integer. Is used to hold the grades the user submits.
        HashMap<String, Integer> gradeMap = new LinkedHashMap<>();
        // A basic while loop that could be replaced with a Thread.
        while (!complete) {
            // Scanner variable being set to the InputStream of the System application. Done in the while loop to make sure it works properly.
            Scanner scanner = new Scanner(System.in);
            // Prints instructions to the out log.
            sysOut("Please enter the first class you wish to calculate your average with! [SubjectName Score]");
            // A basic while loop that is used to make sure we can stay in input mode whenever the currentStage variable is set to Stage.INPUT.
            while (currentStage == STAGE.INPUT) {
                // A String variable containing the users next inputted variable.
                String line = scanner.nextLine();
                // A String Array created that is holding the line but split into its individual words.
                String[] lineArray = line.split(" ");
                // A catch if the user submits input longer than 2 words in length.
                if (lineArray.length != 2) {
                    sysOut("You entered a incorrect string! User: " + Arrays.asList(lineArray) + " Correct: [SubjectName Score]");
                    // Make sure to include to continue in your while loop if you want it to act as a return. If you use return it breaks out of your program.
                    continue;
                }
                // A blank integer variable that will contain the users input submitted grade. (Hopefully)
                int grade;
                // A try/catch because requires it.
                try {
                    // Attempts to cast the grade variable to the second string the user inputted. (Should be a grade)
                    grade = Integer.parseInt(lineArray[1]);
                // Will fire if the user inputted something that isn't a integer. (Word, Float, Double, Etc...)
                } catch (Exception e) {
                    sysOut("You entered a incorrect integer value for your grade. Please only enter whole numbers for your grade. Thank you!");
                    // Make sure to include to continue in your while loop if you want it to act as a return. If you use return it breaks out of your program.
                    continue;
                }
                // Adds the users inputted string and the cast integer to the grade map.
                gradeMap.put(lineArray[0], grade);
                // Changes the current stage to Yes/No stage.
                currentStage = STAGE.YN;
                // A basic while loop that makes sure the user stays in the Yes/No stage until you perform otherwise.
                while (currentStage == STAGE.YN) {
                    // Prints instructions to the out log.
                    sysOut("Is this your final grade you wish to enter? [Y/N]");
                    // A String variable containing the users next inputted variable.
                    String input = scanner.nextLine();
                    // Checks if the user inputted string equals ignore case sensitivity to Y, if yes it sets the stage the final calculation stage.
                    if (input.equalsIgnoreCase("Y")) {
                        sysOut("Starting final average calculation!");
                        currentStage = STAGE.CALCULATE;
                        // Checks if the user inputted string equals ignore case sensitivity to N, if yes it sets the stage the input stage.
                    } else if (input.equalsIgnoreCase("N")) {
                        sysOut("Please enter the next grade you wish to add to your grades list!");
                        currentStage = STAGE.INPUT;
                        // Funny catch if neither correct options were submit.
                    } else {
                        sysOut("Why did you not do either Y or N?");
                    }
                }
            }
            // Breaks out of the initial basic while loop, which only happens whenever the user gets to the final calculation stage.
            complete = true;
        }
        // A loop through the grade map that prints all the user submitted grades to the output log.
        for (Map.Entry<String, Integer> entry : gradeMap.entrySet()) {
            sysOut(entry.getKey() + ": " + entry.getValue());
        }
        // Prints the final average score in the output log, which it gets from the static returnTotalOfMap method.
        sysOut("Average Score: " + returnTotalOfMap(gradeMap));
    }

    // Ease of use method.
    public static void sysOut(String string) {
        System.out.println(string);
    }

    // Method to return the users average grade.
    public static int returnTotalOfMap(HashMap<String, Integer> gradeMap) {
        // An integer variable that will contain the users total grade.
        int sum = 0;
        // A loop through the grade map that adds all the user submitted grades to the sum variable.
        for (Map.Entry<String, Integer> entry : gradeMap.entrySet()) {
            sum += entry.getValue();
        }
        // Returns the sum divided by the grade map total size. Allowing us to get the average of the grade.
        return sum / gradeMap.size();
    }

    // An enum containing all used stages in the program.
    public enum STAGE {
        // Stage that is used whenever the user is submitting their grade.
        INPUT,
        // Stage that is used whenever the user is answering either Y/N to the Y/N question.
        YN,
        // Stage that is used whenever the final calculation is being performed.
        CALCULATE
    }
}
