package com.example.scientia;

import java.util.ArrayList;
import java.util.List;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class AptitudeDbHelper extends SQLiteOpenHelper {

	private static final int DATABASE_VERSION = 1;
	// Database Name
	private static final String DATABASE_NAME = "aptitudeQuiz";
	// tasks table name
	private static final String TABLE_QUEST = "quest";
	// tasks Table Columns names
	private static final String KEY_ID = "id";
	private static final String KEY_QUES = "question";
	private static final String KEY_ANSWER = "answer"; //correct option
	private static final String KEY_OPTA= "opta"; //option a
	private static final String KEY_OPTB= "optb"; //option b
	private static final String KEY_OPTC= "optc"; //option c
	private static final String KEY_OPTD= "optd"; //option d
	private SQLiteDatabase dbase;
	
	public AptitudeDbHelper(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}
	@Override
	public void onCreate(SQLiteDatabase db) {
		dbase=db;
		String sql = "CREATE TABLE IF NOT EXISTS " + TABLE_QUEST + " ( "
				+ KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + KEY_QUES
				+ " TEXT, " + KEY_ANSWER+ " TEXT, "+KEY_OPTA +" TEXT, "
				+KEY_OPTB +" TEXT, "+KEY_OPTC+" TEXT, "+KEY_OPTD+" TEXT);";
		db.execSQL(sql);		
		addQuestions();
//		db.close();
	}
	private void addQuestions()
	{
		Question q1=new Question("Radius of a circle is 5 cm. The area of the circle is what percentage of it circumference?","200", "225","240", "250","250");
		this.addQuestion(q1);
		Question q2=new Question("Each side of a rectangle is increased by 20%. What is the percentage increase in the area?", "20%","40%","42%","44%","44%");
		this.addQuestion(q2);
		Question q3=new Question("A father divides his property among his 4 children. A gets 1/3 of the property, B gets 2/5 of the remaining. Rest is divided equally among C and D. What fraction of property do C or D get?", "1/17","1/6"," 1/5", "1/4"," 1/5" );
		this.addQuestion(q3);
		Question q4=new Question("The average of 5 numbers is 27. One number is excluded and the average becomes 25. What is the excluded number?", "25", "27","35","40", "35");
		this.addQuestion(q4);
		Question q5=new Question("A and B's ages are in the ratio 7:9 and A is younger than B by 4 years. What is A's age?","14","18","24.5","None of these","14");
		this.addQuestion(q5);
		Question q6=new Question("There are three pipes - A , B, C. They can fill a tank in 5 hrs, 10 hrs and 30 hrs respectively. If all the pipes are opened together, how long will they take to fill the tank?", "1", "2","2.5", "3","3");
		this.addQuestion(q6);
		Question q7=new Question("The length of a train is 110 metres. It is running at the speed of 72 kmph. How much will it take to cross a bridge 132 m in length?","9.8 sec","12.1 sec","12.42 sec","14 sec", "12.1 sec");
		this.addQuestion(q7);
		Question q8=new Question("Three numbers are in the ratio 3:4:5. The sum of their squares is Rs 1250. What is the sum of numbers?", "40","50","60","90" ,"60");
		this.addQuestion(q8);
		Question q9=new Question("A mixture of 15 litres consists of 20% alcohol and rest is water. If 3 more litres of water are added to the mixture, what will be the percentage of alcohol in new mixture?", "15%", "15.75%","16.66%","17%","16.66%");
		this.addQuestion(q9);
		Question q10=new Question("Two pipes A and B can fill a tank in 6 hours and 4 hours respectively. They are opened for alternate hours starting with pipe A. How long will it take to fill the tank?","4 hrs","5 hrs","5.5 hrs","6 hrs","5 hrs");
		this.addQuestion(q10);
	}
	@Override
	public void onUpgrade(SQLiteDatabase db, int oldV, int newV) {
		// Drop older table if existed
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_QUEST);
		// Create tables again
		onCreate(db);
	}
	// Adding new question
	public void addQuestion(Question quest) {
		//SQLiteDatabase db = this.getWritableDatabase();
		ContentValues values = new ContentValues();
		values.put(KEY_QUES, quest.getQUESTION()); 
		values.put(KEY_ANSWER, quest.getANSWER());
		values.put(KEY_OPTA, quest.getOPTA());
		values.put(KEY_OPTB, quest.getOPTB());
		values.put(KEY_OPTC, quest.getOPTC());
		values.put(KEY_OPTD, quest.getOPTD());
		// Inserting Row
		dbase.insert(TABLE_QUEST, null, values);		
	}
	public List<Question> getAllQuestions() {
		List<Question> quesList = new ArrayList<Question>();
		// Select All Query
		String selectQuery = "SELECT  * FROM " + TABLE_QUEST;
		dbase=this.getReadableDatabase();
		Cursor cursor = dbase.rawQuery(selectQuery, null);
		// looping through all rows and adding to list
		if (cursor.moveToFirst()) {
			do {
				Question quest = new Question();
				quest.setID(cursor.getInt(0));
				quest.setQUESTION(cursor.getString(1));
				quest.setANSWER(cursor.getString(2));
				quest.setOPTA(cursor.getString(3));
				quest.setOPTB(cursor.getString(4));
				quest.setOPTC(cursor.getString(5));
				quest.setOPTD(cursor.getString(6));
				quesList.add(quest);
			} while (cursor.moveToNext());
		}
		// return quest list
		return quesList;
	}
	public int rowcount()
	{
		int row=0;
		String selectQuery = "SELECT  * FROM " + TABLE_QUEST;
		SQLiteDatabase db = this.getWritableDatabase();
		Cursor cursor = db.rawQuery(selectQuery, null);
		row=cursor.getCount();
		return row;
	}

}