package br.unigran.menulateral.telas;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

import br.unigran.menulateral.R;
import br.unigran.menulateral.model.QuizQuestion;

public class Quiz extends AppCompatActivity {

    private List<QuizQuestion> quizQuestions;
    private int currentQuestionIndex = 0;
    private int score = 0;

    private TextView pergunta;
    private RadioGroup questao;
    private Button buttonNext;
    DatabaseReference databaseReference;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);
        databaseReference = FirebaseDatabase.getInstance().getReference();

        initializeQuizQuestions();

        pergunta = findViewById(R.id.idPergunta);
        questao = findViewById(R.id.idQuestao);
        buttonNext = findViewById(R.id.buttonNext);

        showQuestion();

        buttonNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkAnswer();
                currentQuestionIndex++;

                if (currentQuestionIndex < quizQuestions.size()) {
                    showQuestion();
                } else {
                    endQuiz();
                }
            }
        });
    }

    private void initializeQuizQuestions() {
        quizQuestions = new ArrayList<>();
        quizQuestions.add(new QuizQuestion("Como se diz UM(1) em Ingles?",
                new String[]{"TWO", "ONE", "FOUR", "SIX"},
                1));

    }

    private void showQuestion() {
        QuizQuestion currentQuestion = quizQuestions.get(currentQuestionIndex);

        questao.clearCheck();

        pergunta.setText(currentQuestion.getQuestion());
        questao.removeAllViews();

        for (int i = 0; i < currentQuestion.getOptions().length; i++) {
            RadioButton radioButton = new RadioButton(this);
            radioButton.setText(currentQuestion.getOptions()[i]);
            questao.addView(radioButton);
        }
    }

    private void checkAnswer() {
        int selectedRadioButtonId = questao.getCheckedRadioButtonId();

        if (selectedRadioButtonId != -1) {
            RadioButton selectedRadioButton = findViewById(selectedRadioButtonId);
            int selectedOptionIndex = questao.indexOfChild(selectedRadioButton);

            QuizQuestion currentQuestion = quizQuestions.get(currentQuestionIndex);

            if (selectedOptionIndex == currentQuestion.getCorrectOptionIndex()) {
                score++;
            }
        }
    }

    private void endQuiz() {
        Toast.makeText(this, "Quiz concluído! Pontuação: " + score, Toast.LENGTH_SHORT).show();
        salvar1();
    }

    private void salvar1() {
        DatabaseReference quizRef = databaseReference.child("Quiz1");
        DatabaseReference novoQuizRef = quizRef.child(quizQuestions.get(0).getQuestion());
        novoQuizRef.child("pergunta").setValue(quizQuestions.get(0).getQuestion());
    }
}
