package com.example.ea88c.courtcounter;

        import android.os.Bundle;
        import android.support.v7.app.AppCompatActivity;
        import android.view.View;
        import android.widget.Button;
        import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    //global variables
    int scoreTeamA = 0;
    int scoreTeamB = 0;
    final int gamePoint = 20;
    final int deuceMax = 25;
    Boolean isMatchCurrent = true;
    String svscoreTeamA = "score_TeamA";
    String svscoreTeamB = "score_TeamB";
    TextView msg;
    TextView scoreA;
    TextView scoreB;
    Button btnPlyrA1;
    Button btnPlyrA2;
    Button btnPlyrB1;
    Button btnPlyrB2;
    String servedPlayer = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // recovering the instance state
        if (savedInstanceState != null) {
            scoreTeamA = savedInstanceState.getInt(svscoreTeamA);
            scoreTeamB = savedInstanceState.getInt(svscoreTeamB);
        }
        msg = findViewById(R.id.message_text_view);
        scoreA = findViewById(R.id.scoreA_text_view);
        scoreB = findViewById(R.id.scoreB_text_view);
        btnPlyrA1 = findViewById(R.id.A1_button);
        btnPlyrA2 = findViewById(R.id.A2_button);
        btnPlyrB1 = findViewById(R.id.B1_button);
        btnPlyrB2 = findViewById(R.id.B2_button);
    }

    @Override
    public void onSaveInstanceState(Bundle saveState) {
        super.onSaveInstanceState(saveState);
        saveState.putInt(svscoreTeamA, scoreTeamA);
        saveState.putInt(svscoreTeamB, scoreTeamB);
    }

    @Override
    public void onRestoreInstanceState(Bundle restoreState) {
        super.onRestoreInstanceState(restoreState);
        scoreTeamA = restoreState.getInt(svscoreTeamA);
        scoreTeamB = restoreState.getInt(svscoreTeamB);
    }

    // add teamA score
    public void addTeamA(View v) {
        if (isMatchCurrent) {
            scoreTeamA++;
            displayScore();

        } else {
            msg.setText("Match Over !");
        }
    }

    // add teamB score
    public void addTeamB(View v) {
        if (isMatchCurrent) {
            scoreTeamB++;
            displayScore();
        } else {
            msg.setText("Match Over !");
        }

    }

    //display team scores
    public void displayScore() {

        if ((scoreTeamB == scoreTeamA) && scoreTeamB >= gamePoint && scoreTeamB < deuceMax) {
            msg.setText("Deuce");
        } else if ((scoreTeamA < gamePoint && scoreTeamB == gamePoint + 1) || (scoreTeamA >= gamePoint && scoreTeamB == scoreTeamA + 2)
                || scoreTeamB > deuceMax) {
            msg.setText("Team B Won. Congrats !!");
            isMatchCurrent = false;
        } else if ((scoreTeamB < gamePoint && scoreTeamA == gamePoint + 1) || (scoreTeamB >= gamePoint && scoreTeamA == scoreTeamB + 2)
                || scoreTeamA > deuceMax) {
            msg.setText("Team A Won. Congrats !!");
            isMatchCurrent = false;
        } else if (scoreTeamB >= gamePoint && scoreTeamA >= scoreTeamB + 1) {
            msg.setText("Advantage for Team A");
        } else if (scoreTeamA > gamePoint && scoreTeamB >= scoreTeamA + 1) {
            msg.setText("Advantage for Team B");
        } else {
            msg.setText("");
        }

        scoreB.setText(String.valueOf(scoreTeamB));
        scoreA.setText(String.valueOf(scoreTeamA));
    }

    // reset team scores for a rematch
    public void resetScores(View v) {
        scoreTeamB = 0;
        scoreTeamA = 0;
        isMatchCurrent = true;
        displayScore();
    }

    public void onButtonClick(View v) {

        switch (v.getId()) {

            case R.id.A1_button:
                addTeamA(v);
                btnPlyrA1.setText("Scored");
                servedPlayer = "PlayerA1";
                break;

            case R.id.A2_button:
                addTeamA(v);
                btnPlyrA2.setText("Scored");
                servedPlayer = "PlayerA2";
                break;

            case R.id.B1_button:
                addTeamB(v);
                btnPlyrB1.setText("Scored");
                servedPlayer = "PlayerB1";
                break;

            case R.id.B2_button:
                addTeamB(v);
                btnPlyrB2.setText("Scored");
                servedPlayer = "PlayerB2";
                break;

            case R.id.reset_button:
                resetScores(v);
                btnPlyrB1.setText("Player 1");
                btnPlyrB2.setText("Player 2");
                btnPlyrA1.setText("Player 1");
                btnPlyrA2.setText("Player 2");
                break;
        }

        switch (servedPlayer){

            case "PlayerA1":
                btnPlyrA2.setText("Player 2");
                btnPlyrB1.setText("Player 1");
                btnPlyrB2.setText("Player 2");
                break;

            case "PlayerA2":
                btnPlyrA1.setText("Player 1");
                btnPlyrB1.setText("Player 1");
                btnPlyrB2.setText("Player 2");
                break;

            case "PlayerB1":
                btnPlyrB2.setText("Player 2");
                btnPlyrA1.setText("Player 1");
                btnPlyrA2.setText("Player 2");
                break;

            case "PlayerB2":
                btnPlyrB1.setText("Player 1");
                btnPlyrA1.setText("Player 1");
                btnPlyrA2.setText("Player 2");
                break;
        }
    }
}
