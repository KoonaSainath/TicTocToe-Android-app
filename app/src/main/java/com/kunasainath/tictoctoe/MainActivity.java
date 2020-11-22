package com.kunasainath.tictoctoe;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.kunasainath.tictoctoe.fragments.GameFragment;
import com.kunasainath.tictoctoe.fragments.PlayerNames;
import com.kunasainath.tictoctoe.fragments.ResultOfGame;

public class MainActivity extends AppCompatActivity implements PlayerNames.PlayerNamesFragmentInterface, GameFragment.GameFragmentInterface, ResultOfGame.ResultOfGameInterface {

    PlayerNames mPlayerNames = PlayerNames.newInstance();
    GameFragment game;
    ResultOfGame mResultOfGame;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.player_names_fragment_container, mPlayerNames)
                .commit();
    }

    @Override
    public void playButtonIsClicked(String firstPlayerName, String secondPlayerName) {

        game = GameFragment.newInstance(firstPlayerName,secondPlayerName);

        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.player_names_fragment_container, game)
                .addToBackStack(null)
                .commit();

        setTitle("Game Is Started");
    }

    @Override
    public void resultIsOut(String result) {
        setTitle("RESULT IS OUT");
        getSupportFragmentManager()
                .beginTransaction()
                .remove(game)
                .commit();
        mResultOfGame = ResultOfGame.newInstance(result);
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.player_names_fragment_container, mResultOfGame)
                .addToBackStack(null)
                .commit();
    }

    @Override
    public void getResult() {
        getSupportFragmentManager()
                .beginTransaction()
                .remove(mResultOfGame)
                .commit();

        setTitle(getString(R.string.app_name));

        getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.player_names_fragment_container, mPlayerNames)
                .commit();
    }
}