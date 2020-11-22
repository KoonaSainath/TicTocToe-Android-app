package com.kunasainath.tictoctoe.fragments;

import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;

import com.kunasainath.tictoctoe.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link PlayerNames#newInstance} factory method to
 * create an instance of this fragment.
 */
public class PlayerNames extends Fragment {

    Button btnPlay;
    EditText edtName1, edtName2;
    PlayerNamesFragmentInterface playerNamesInterface;

    public interface PlayerNamesFragmentInterface{
        public void playButtonIsClicked(String first, String second);
    }

    public PlayerNames() {
    }

    public static PlayerNames newInstance() {
        return new PlayerNames();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        playerNamesInterface = (PlayerNamesFragmentInterface) getActivity();

        btnPlay.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onClick(View view) {
                String name1 = edtName1.getText().toString(), name2 = edtName2.getText().toString();
                if(name1.length() < 3){
                    edtName1.setError("Enter a valid name with minimum 3 letters");
                    return ;
                }
                if(name2.length() < 3){
                    edtName2.setError("Enter a valid name with minimum 3 letters");
                    return ;
                }
                playerNamesInterface.playButtonIsClicked(name1 , name2);
            }
        });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_player_names, container, false);
        btnPlay = view.findViewById(R.id.btnPlay);
        edtName1 = view.findViewById(R.id.edt_name1);
        edtName2 = view.findViewById(R.id.edt_name2);
        return view;
    }

    public View.OnClickListener editTextListener(){
        return new View.OnClickListener(){

            @Override
            public void onClick(View view) {

            }
        };
    }
}