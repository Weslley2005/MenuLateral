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

public class QuizFrances extends AppCompatActivity {

    private List<QuizQuestion> quizQuestions;
    private int currentQuestionIndex = 0;
    private int score = 0;

    private TextView textViewQuestion;
    private RadioGroup radioGroupOptions;
    private Button buttonNext;
    DatabaseReference databaseReference;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz_frances);

        initializeQuizQuestions();

        textViewQuestion = findViewById(R.id.textViewQuestion1);
        radioGroupOptions = findViewById(R.id.radioGroupOptions1);
        buttonNext = findViewById(R.id.buttonNext1);
        databaseReference = FirebaseDatabase.getInstance().getReference();
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
        quizQuestions.add(new QuizQuestion("Como se diz NOVE(9) em Frances?",
                new String[]{"DEUX", "NEUF", "SEPT", "TROIS"},
                1));
    }

    private void showQuestion() {
        QuizQuestion currentQuestion = quizQuestions.get(currentQuestionIndex);

        radioGroupOptions.clearCheck();

        textViewQuestion.setText(currentQuestion.getQuestion());
        for (int i = 0; i < currentQuestion.getOptions().length; i++) {
            RadioButton radioButton = new RadioButton(this);
            radioButton.setText(currentQuestion.getOptions()[i]);
            radioGroupOptions.addView(radioButton);
        }
    }

    private void checkAnswer() {
        int selectedRadioButtonId = radioGroupOptions.getCheckedRadioButtonId();

        if (selectedRadioButtonId != -1) {
            RadioButton selectedRadioButton = findViewById(selectedRadioButtonId);
            int selectedOptionIndex = radioGroupOptions.indexOfChild(selectedRadioButton);

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
        DatabaseReference quizRef = databaseReference.child("Quiz2");
        DatabaseReference novoQuizRef = quizRef.child(quizQuestions.get(0).getQuestion());
        novoQuizRef.child("pergunta").setValue(quizQuestions.get(0).getQuestion());

    }
}
