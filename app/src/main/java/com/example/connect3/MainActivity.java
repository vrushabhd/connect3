package com.example.connect3;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    //0=yellow,1 =red
    int activePlayer = 0;
    boolean gameIsActive= true;
    // 2 means unplayd
    int[] gameState={2,2,2,2,2,2,2,2,2};
    int[][] winningPositions = {{0,1,2},{3,4,5},{6,7,8},{0,3,6},{1,4,7},{2,5,8},{0,4,8},{2,4,6}};

    public void DropIn(View view) {


        ImageView counter = (ImageView) view;
        counter.getTag().toString();
        int tappedCounter = Integer.parseInt(counter.getTag().toString());
        if (gameState[tappedCounter] == 2 && gameIsActive) {
            gameState[tappedCounter]=activePlayer;
        counter.setTranslationY(-1000f);
        if (activePlayer == 0) {
            counter.setImageResource(R.drawable.yellow);
            activePlayer = 1;

        } else {
            counter.setImageResource(R.drawable.red);
            activePlayer = 0;

        }
        counter.animate().translationYBy(1000f).rotation(360).setDuration(300);
        for(int[] winningPostion:winningPositions) {

            if(gameState[winningPostion[0]]==gameState[winningPostion[1]]&&gameState[winningPostion[1]]==gameState[winningPostion[2]]
            && gameState[winningPostion[0]]!=2)
             {
                 //somebody has won
                 gameIsActive =false;
                  String Winner ="Red";
                 if (gameState[winningPostion[0]]==0){

                     Winner ="Yellow";

                 }

             //someone has won
                 TextView winnerMessage =(TextView)findViewById(R.id.textView2);
                 winnerMessage.setText(Winner+" Has Won!!");
                 LinearLayout linearLayout =findViewById(R.id.playAgainLayout);
                 linearLayout.setVisibility(View.VISIBLE);
                 linearLayout.animate().translationYBy(1000f).setDuration(1000);



            }else{

                boolean gameIsOver =true;
                for(int counterState:gameState) {
                    if (counterState == 2) gameIsOver = false;
                }
                       if(gameIsOver){
                           TextView winnerMessage =(TextView)findViewById(R.id.textView2);
                           winnerMessage.setText("It's a Draw");
                           LinearLayout linearLayout =findViewById(R.id.playAgainLayout);
                           linearLayout.setVisibility(View.VISIBLE);
                           linearLayout.animate().translationYBy(1000f).setDuration(1000);

                       }


                }


            }
        }
        }
    public void playAgain(View view){
        gameIsActive =true;
        Button button =findViewById(R.id.button);
        LinearLayout linearLayout =findViewById(R.id.playAgainLayout);
        linearLayout.setVisibility(View.INVISIBLE);
        activePlayer = 0;
        // 2 means unplayed
        linearLayout.setTranslationY(-10f);
         for(int i=0; i<gameState.length ;i++){
             gameState[i]=2;


         }
        android.support.v7.widget.GridLayout gridLayout =findViewById(R.id.gridLayout);
         for(int i=0;i<gridLayout.getChildCount();i++){
             ((ImageView)gridLayout.getChildAt(i)).setImageResource(0);
         }

    }








    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
