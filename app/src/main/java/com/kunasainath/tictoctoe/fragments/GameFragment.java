
package com.kunasainath.tictoctoe.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.gridlayout.widget.GridLayout;

import com.kunasainath.tictoctoe.R;

public class GameFragment extends Fragment {


    public interface GameFragmentInterface{
        public void resultIsOut(String result);
    }

    private GameFragmentInterface gameInterface;


    enum Player{
        ONE, TWO, NONE;
    }

    boolean winnerDeclared = false;
    boolean tied = false;

    private final static String FIRST = "FIRST";
    private final static String SECOND = "SECOND";

    private String first, second;

    Player currentPlayer = Player.ONE;
    Player[] choices = new Player[9];
    int[][] winningPatterns = {{0,1,2},{3,4,5},{6,7,8},{0,3,6},{1,4,7},{2,5,8},{0,4,8},{2,4,6}};


    private ImageView img1, img2, img3, img4, img5, img6, img7, img8, img9;
    private TextView playNow, players, turn;
    private GridLayout grid;

    public GameFragment() {
    }

    public static GameFragment newInstance(String firstPlayerName, String secondPlayerName) {
        GameFragment game = new GameFragment();
        Bundle bundle = new Bundle();
        bundle.putString(FIRST, firstPlayerName);
        bundle.putString(SECOND, secondPlayerName);
        game.setArguments(bundle);
        return game;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        playNow.animate().alpha(0).setDuration(5000);
        players.animate().translationYBy(-100f).setDuration(5000);
        turn.animate().translationYBy(-100f).setDuration(5000);
        grid.animate().translationYBy(-100f).setDuration(5000);

        gameInterface = (GameFragmentInterface) getActivity();

        first = getArguments().getString(FIRST);
        second = getArguments().getString(SECOND);

        players.setText(first + " VS " + second);

        turn.setText(first + "'s TURN");

        setAllBoxesUnSelected();
        setOnClickListeners();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_game, container, false);
        initializeViews(view);
        return view;
    }

    public View.OnClickListener getListenerForImageClick(){
        return new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(winnerDeclared){
                    return;
                }

                if(tied){
                    return;
                }

                ImageView image = (ImageView) view;
                image.setTranslationY(-2000f);
                int position = Integer.parseInt(image.getTag().toString());
                choices[position] = currentPlayer;

                if(currentPlayer == Player.ONE){
                    turn.setText(second + "'s TURN");
                    currentPlayer = Player.TWO;
                    image.animate().alpha(1).translationYBy(2000f).rotation(3600).setDuration(1000);
                    image.setImageResource(R.drawable.first_player);
                }else if(currentPlayer == Player.TWO){
                    turn.setText(first + "'s TURN");
                    currentPlayer = Player.ONE;
                    image.animate().alpha(1).translationYBy(2000f).rotation(3600).setDuration(1000);
                    image.setImageResource(R.drawable.second_player);
                }


                for(int[] pattern : winningPatterns){
                    if(choices[pattern[0]] == choices[pattern[1]] && choices[pattern[1]] == choices[pattern[2]] && choices[pattern[0]] != Player.NONE){
                        winnerDeclared = true;
                        if(currentPlayer == Player.ONE){
                            gameInterface.resultIsOut(second);
                        }else{
                            gameInterface.resultIsOut(first);
                        }
                    }
                }

                if(!winnerDeclared) {

                    if (gameDrawn()) {
                        gameInterface.resultIsOut("TIED");
                        tied = true;
                    }
                }
            }

            private boolean gameDrawn() {
                for(int i = 0; i < 9; i++){
                    if(choices[i] == Player.NONE){
                        return false;
                    }
                }return true;
            }
        };
    }

    private void setAllBoxesUnSelected(){
        for(int i = 0; i < 9; i++){
            choices[i] = Player.NONE;
        }
    }
    private void initializeViews(View view){
        img1 = view.findViewById(R.id.img1);
        img2 = view.findViewById(R.id.img2);
        img3 = view.findViewById(R.id.img3);
        img4 = view.findViewById(R.id.img4);
        img5 = view.findViewById(R.id.img5);
        img6 = view.findViewById(R.id.img6);
        img7 = view.findViewById(R.id.img7);
        img8 = view.findViewById(R.id.img8);
        img9 = view.findViewById(R.id.img9);
        playNow = view.findViewById(R.id.txt_play_now);
        players = view.findViewById(R.id.txt_who_are_playing);
        turn = view.findViewById(R.id.txt_turn);
        grid = view.findViewById(R.id.game_board);
    }
    private void setOnClickListeners() {
        img1.setOnClickListener(getListenerForImageClick());
        img2.setOnClickListener(getListenerForImageClick());
        img3.setOnClickListener(getListenerForImageClick());
        img4.setOnClickListener(getListenerForImageClick());
        img5.setOnClickListener(getListenerForImageClick());
        img6.setOnClickListener(getListenerForImageClick());
        img7.setOnClickListener(getListenerForImageClick());
        img8.setOnClickListener(getListenerForImageClick());
        img9.setOnClickListener(getListenerForImageClick());
    }
}