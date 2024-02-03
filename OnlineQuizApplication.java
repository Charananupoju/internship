
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

class Question {
    private String question;
    private List<String> options;
    private String correctAnswer;

    public Question(String question, List<String> options, String correctAnswer) {
        this.question = question;
        this.options = options;
        this.correctAnswer = correctAnswer;
    }

    public String getQuestion() {
        return question;
    }

    public List<String> getOptions() {
        return options;
    }

    public String getCorrectAnswer() {
        return correctAnswer;
    }
}

public class OnlineQuizApplication {
    private static final List<Question> QUESTIONS = new ArrayList<>();

    static {
        // Initialize questions and add them to the QUESTIONS list
        QUESTIONS.add(new Question("What is the capital of France?", Arrays.asList("a. Paris", "b. Berlin", "c. Madrid", "d. Rome"), "a"));
        QUESTIONS.add(new Question("Which planet is known as the Red Planet?", Arrays.asList("a. Venus", "b. Mars", "c. Jupiter", "d. Saturn"), "b"));
        QUESTIONS.add(new Question("What is the largest mammal?", Arrays.asList("a. Elephant", "b. Blue Whale", "c. Giraffe", "d. Lion"), "b"));
        QUESTIONS.add(new Question(" What is the capital of India?", Arrays.asList("a. New delhi ", "b. Mumbai", "c. gujrat", "d. chennai"), "a"));
        QUESTIONS.add(new Question(" How many continents are there in the world?", Arrays.asList("a. 8", "b. 6", "c. 7", "d. 9"), "c"));
        QUESTIONS.add(new Question(" What is the largest ocean on Earth?", Arrays.asList("a. Antartica", "b. pacific ocean", "c. indian ocean", "d. arcitic "), "b"));
        QUESTIONS.add(new Question("What is the national flower of Japan?", Arrays.asList("a. Cherryblossom", "b. lotus", "c. none of them above", "d. simuli"), "a"));
    }

    private int score = 0;

    public static void main(String[] args) {
        OnlineQuizApplication quizApp = new OnlineQuizApplication();
        quizApp.startQuiz();
    }

    public void startQuiz() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Welcome to the Online Quiz Application!");

        for (int i = 0; i < QUESTIONS.size(); i++) {
            Question question = QUESTIONS.get(i);

            System.out.println("\nQuestion " + (i + 1) + ": " + question.getQuestion());

            for (String option : question.getOptions()) {
                System.out.println(option);
            }

            System.out.print("Your answer: ");
            String userAnswer = scanner.next();

            if (checkAnswer(question, userAnswer)) {
                System.out.println("Correct!\n");
                score++;
            } else {
                System.out.println("Incorrect. The correct answer is: " + question.getCorrectAnswer() + "\n");
            }
        }

        System.out.println("Quiz completed! Your score: " + score + " out of " + QUESTIONS.size());

        scanner.close();
    }

    private boolean checkAnswer(Question question, String userAnswer) {
        return userAnswer.equalsIgnoreCase(question.getCorrectAnswer());
    }
}
