package com.android.csit.csit;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;



/**
 * A simple {@link Fragment} subclass.
 */
public class NoteFragment extends Fragment {
    Button notesButton1;
    Button notesButton2;


    public NoteFragment() {
        // Required empty public constructor
    }

       //launching activity from fragment;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_note, container, false);
        Button notesButton1 = (Button) v.findViewById(R.id.notesButton1);
        Button sem2 = (Button) v.findViewById(R.id.notesButton2);
        Button sem8 = (Button) v.findViewById(R.id.notesButton8);
        Button sem3 = (Button) v.findViewById(R.id.notesButton3);
        Button sem4 = (Button) v.findViewById(R.id.notesButton4);
        Button sem5 = (Button) v.findViewById(R.id.notesButton5);
        Button sem6 = (Button) v.findViewById(R.id.notesButton6);
        Button sem7 = (Button) v.findViewById(R.id.notesButton7);
        return v;
    }}
//
//      notesButton1.setOnClickListener(new View.OnClickListener() {
/*            notesButton1.setOnClickListener(this);
        sem2.setOnClickListener(this);
        sem3.setOnClickListener(this);
        sem4.setOnClickListener(this);
        sem5.setOnClickListener(this);
        sem6.setOnClickListener(this);
        sem7.setOnClickListener(this);
        sem8.setOnClickListener(this);*/
//                @Override
//                public void onClick(View v) {
//                    Intent firstSem = new Intent(getActivity(), Semester.class);
//                    firstSem.putExtra("semester","button value");
//                    startActivity(firstSem);
//                }
//                });

/*
        return v;
    }

    @Override
    public void onClick(View v) {
        String test;

        switch (v.getId()) {

            case R.id.notesButton1:
                test="One";
                // do your code
                break;

            case R.id.notesButton2:
                test="Two";
                // do your code
                break;

            case R.id.notesButton3:
                // do your code
                test="Three";
                break;
            case R.id.notesButton4:
                test="Four";
                break;

            case R.id.notesButton5:
                test="Five";
                break;

            case R.id.notesButton6:
                test="Six";
                break;

            case R.id.notesButton7:
                test="Seven";
                break;

            case R.id.notesButton8:
                test="Eight";
                break;

            default:
                test ="default";
                break;
        }

        Intent firstSem = new Intent(getActivity(), Semester.class);
        firstSem.putExtra("semester",test);
        startActivity(firstSem);
*/



