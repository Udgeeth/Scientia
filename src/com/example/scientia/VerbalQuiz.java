package com.example.scientia;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import java.util.List;
import android.content.Intent;
import android.util.Log;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

public class VerbalQuiz extends Fragment implements View.OnClickListener {
	
	List<Question> quesList;
	int score=0;
	int qid=0;
	Question currentQ;
	TextView txtQuestion;
	RadioButton rda, rdb, rdc, rdd;
	Button butNext;
	RadioGroup grp;
	RadioButton answer;
	
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
	}
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		VerbalDbHelper db = new VerbalDbHelper(getActivity());
		View v=inflater.inflate(R.layout.verbalquiz, container, false);
		quesList=db.getAllQuestions();
		currentQ=quesList.get(qid);
		txtQuestion=(TextView)v.findViewById(R.id.textView1);
		rda=(RadioButton)v.findViewById(R.id.radio0);
		rdb=(RadioButton)v.findViewById(R.id.radio1);
		rdc=(RadioButton)v.findViewById(R.id.radio2);
		rdd=(RadioButton)v.findViewById(R.id.radio3);
		butNext=(Button)v.findViewById(R.id.button1);
		grp=(RadioGroup)v.findViewById(R.id.radioGroup1);
		answer=(RadioButton)v.findViewById(grp.getCheckedRadioButtonId());	
		setQuestionView();

	    butNext.setOnClickListener(this);
	    		
		return v;
	
}

	private void setQuestionView()
{
	txtQuestion.setText(currentQ.getQUESTION());
	rda.setText(currentQ.getOPTA());
	rdb.setText(currentQ.getOPTB());
	rdc.setText(currentQ.getOPTC());
	rdd.setText(currentQ.getOPTD());
	qid++;
}
	@Override
	public void onClick(View v) {
		if(v==butNext){		
		Log.d("yourans", currentQ.getANSWER()+" "+answer.getText());
		if(currentQ.getANSWER().equals(answer.getText()))
		{
			score++;
			Log.d("score", "Your score"+score);
		}
		if(qid<10){					
			currentQ=quesList.get(qid);
			setQuestionView();
		}else{
			Intent intent = new Intent(getActivity(), ResultActivity.class);
			Bundle b = new Bundle();
			b.putInt("score", score); //Your score
			intent.putExtras(b); //Put your score to your next Intent
			startActivity(intent);
//     		finish();
		}
	}
	}	
}