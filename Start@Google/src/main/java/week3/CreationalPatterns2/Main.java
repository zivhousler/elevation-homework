package week3.CreationalPatterns2;

import java.time.LocalDate;

public class Main {
    public static void main(String[] args) {
        System.out.println(new JobPosition.Builder(LocalDate.now(), "hello world", false));
        System.out.println(new JobPosition.Builder(LocalDate.now().plusMonths(1), "Google -> Backend developer", true).yearsOfExperienceRequired(1).description("Very cool job!").salaryCap(25.5));
        // TEST!
    }
}
