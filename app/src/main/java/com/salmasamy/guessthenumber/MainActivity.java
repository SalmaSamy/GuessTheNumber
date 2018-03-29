package com.salmasamy.guessthenumber;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private int myNumber;
    private int counter = 0 ;

    public void guessGame(View view){

        EditText editText = (EditText) findViewById(R.id.editText);
        String guess = editText.getText().toString();

        //handle if editText is not a number
        boolean error = false;
        for(int i=0; i<guess.length() ; i++){
            if(guess.charAt(i) < '0' || guess.charAt(i) > '9') {
                error = true;
                error();
                break;
            }
        }
        if(!error)
            guessAction(Integer.parseInt(guess));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Random random = new Random();
        myNumber = random.nextInt(20);
    }

    private void guessAction(int guess){

        counter++;
        if(guess == myNumber){
            youDidIt();
        }
        else if(guess < myNumber){
            Toast.makeText(getApplicationContext(), "My number is higher", Toast.LENGTH_LONG).show();
        }
        else{
            Toast.makeText(getApplicationContext(), "My number is lower", Toast.LENGTH_LONG).show();
        }
    }

    private void youDidIt() {

        AlertDialog.Builder confirmBuilder = new AlertDialog.Builder(this);
        confirmBuilder.setTitle("Amazing!");
        confirmBuilder.setMessage("You guessed my number in " + counter + " steps\nPlay Again?");
        confirmBuilder.setPositiveButton("Play", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                EditText editText = (EditText) findViewById(R.id.editText);
                editText.setText("");
                recreate();
            }
        });
        confirmBuilder.setNegativeButton("No Thanks, close", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                finish();
                System.exit(0);
            }
        });
        confirmBuilder.show();

    }

    private void error(){
        AlertDialog.Builder confirmBuilder = new AlertDialog.Builder(this);
        confirmBuilder.setTitle("Error");
        confirmBuilder.setMessage("You Have to enter a number!");
        confirmBuilder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //do nothing
            }
        });
        confirmBuilder.show();
    }
}
