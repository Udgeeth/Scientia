package com.example.scientia;

import android.os.Bundle;
import android.app.Activity;
import android.widget.TextView;

public class ResultActivity extends Activity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.result);
		
		//get text view
		TextView t=(TextView)findViewById(R.id.textView1);
		//get score
		Bundle b = getIntent().getExtras();
		int score= b.getInt("score");
		//display score
		switch (score)
		{
		case 1:	t.setText("Your score: "+score+"/10 \n Keep learning...coz learning is fun, learning is the key to success");
			break;
		case 2: t.setText("Your score: "+score+"/10 \n Keep learning...coz learning is fun, learning is the key to success");
			break;
		case 3: t.setText("Your score: "+score+"/10 \n Keep learning...with a lil more effort you can climb the mountains");
			break;
		case 4:	t.setText("Your score: "+score+"/10 \n Just a bit more of effort...and success will come kiss your feet");
			break;
		case 5: t.setText("Your score: "+score+"/10 \n Not bad! But you can do much better!");
			break;
		case 6: t.setText("Your score: "+score+"/10 \n You are good! You can shine with just a bit more effort");
			break;
		case 7: t.setText("Your score: "+score+"/10 \n You are good! You can shine with just a bit more effort");
			break;
		case 8: t.setText("Your score: "+score+"/10 \n You are really good at this! You surely can make it all right!");
			break;
		case 9:	t.setText("Your score: "+score+"/10 \n You are amazing! You can shine with just a bit more effort");
			break;
		case 10: t.setText("Your score: "+score+"/10 \n You are unbelievable! Hats off _/\\_ :)");
			break;
		
		}
	}
}
