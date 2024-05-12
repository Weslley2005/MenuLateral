package br.unigran.menulateral.model;

public class QuizFrances {
    private String question;
    private String[] options;
    private int correctOptionIndex;

    public QuizFrances(String question, String[] options, int correctOptionIndex) {
        this.question = question;
        this.options = options;
        this.correctOptionIndex = correctOptionIndex;
    }

    public String getQuestion() {
        return question;
    }

    public String[] getOptions() {
        return options;
    }

    public int getCorrectOptionIndex() {
        return correctOptionIndex;
    }
}
