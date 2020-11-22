package com.kunasainath.tictoctoe.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.kunasainath.tictoctoe.R;

public class ResultOfGame extends Fragment {

    public interface ResultOfGameInterface{
        public void getResult();
    }

    private TextView txtResult;
    private Button btnPlayAgain;

    private final static String RESULT_KEY = "result";

    ResultOfGameInterface resultInterface;

    public ResultOfGame() {
        // Required empty public constructor
    }

    public static ResultOfGame newInstance(String result) {
        ResultOfGame resultOfGame = new ResultOfGame();
        Bundle bundle = new Bundle();
        bundle.putString(RESULT_KEY, result);
        resultOfGame.setArguments(bundle);
        return resultOfGame;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        resultInterface = (ResultOfGameInterface) getActivity();

        String result = getArguments().getString(RESULT_KEY);
        if(result.equals("TIED")){
            txtResult.setText("GAME IS DRAWN");
        }else{
            txtResult.setText(result.toUpperCase() + "\nHAS WON");
        }

        btnPlayAgain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                resultInterface.getResult();
            }
        });

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_result_of_game, container, false);
        txtResult = view.findViewById(R.id.txt_result);
        btnPlayAgain = view.findViewById(R.id.btn_play_again);
        return view;
    }
}