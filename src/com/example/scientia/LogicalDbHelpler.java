package com.example.scientia;

import java.util.ArrayList;
import java.util.List;
import com.example.scientia.Question;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class LogicalDbHelpler extends SQLiteOpenHelper {

	private static final int DATABASE_VERSION = 1;
	// Database Name
	private static final String DATABASE_NAME = "logicalQuiz";
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
	
	public LogicalDbHelpler(Context context) {
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
		Question q1=new Question("In a certain code, CAT is written as SATC and DEAR is written as SEARD. How would SING be written in that code ?","GNISS", "SINGS","SGNIS", "BGINS","SINGS");
		this.addQuestion(q1);
		Question q2=new Question("If the code of ABCDEF is ZYXWVU, then what is the code for PASS?", "KZHH","KHZZ","KMHH","WZHH","KZHH");
		this.addQuestion(q2);
		Question q3=new Question("At an enquiry office at a railway station, a passenger was told ‘A train for Delhi has left 15 minutes ago, but after every 45 minutes a train leaves for Delhi. "
				+ "The next train will leave at 8.30 pm’. At what time was this information given to the passenger?", "7.45 pm","8.00 pm","8.15 pm", "8.05 pm","8.00 pm" );
		this.addQuestion(q3);
		Question q4=new Question("Five newly - born babies were weighed by the doctor. In her report, she stated that child A is lighter than child B. Child C is lighter than child D. "
				+ "Child B is lighter than child D, but heavier than child E. Which child is the heaviest?", "E", "D","C","A", "D");
		this.addQuestion(q4);
		Question q5=new Question("AH, DK, GN, JQ, ? , PW","LV","MW","MT","KT","MT");
		this.addQuestion(q5);
		Question q6=new Question("EYXB, HVUE, LRQI, VHGS, ?", "AZYD", "YDEV","SJKP", "NPOK","NPOK");
		this.addQuestion(q6);
		Question q7=new Question("TSix persons are playing a card game, suresh is facing raghubir who is to the left of ajay and to the right of pamod."
				+ "Ajay is to the left of dhiraj.Yogendra is to the left of Pramod.If dhiraj exchanges his seat with yogendra and Pramod exchanges with raghubir,who will be sitting to the left of dhiraj?","yogendra","raghubir","suresh","ajay","suresh");
		this.addQuestion(q7);
		Question q8=new Question("A boy is asked to put in a basket one mango when ordered ‘one’.One orange when ordered ‘two’ , one apple when ordered ‘three’ and is asked to take out from the basket one mango and an orange when ordered ‘four’. "
				+ "A sequence of orders is given as \n1 2 3 3 2 1 4 2 3 1 4 2 2 3 3 1 4 1 1 3 2 3 4 \nHow many oranges are there in the basket after the above sequence?", "1","2","3","4","2");
		this.addQuestion(q8);
		Question q9=new Question("Testifying before the Senate committee investigating charges that cigarette manufacturers had manipulated nicotine levels in cigarettes in order to addict consumers to their products, tobacco executives argued that cigarette smoking is not addictive. "
				+ "The primary reason they gave in support of this claim was that cigarette smoking was not regulated by the Federal Drug Administration.\nFor the tobacco executives' argument to be logically correct, which of the following must be assumed?", 
				"Substances that are not addictive are not regulated by ...........the Federal Drug Administration.", "The tobacco executives lied when they claimed that ...........cigarette smoking was not addictive.","Some addictive substances are not regulated by the ...........Federal Drug Administration.",
				"Substances that are not regulated by the Federal Drug ...........Administration are not addictive","Substances that are not regulated by the Federal Drug ...........Administration are not addictive");
		this.addQuestion(q9);
		Question q10=new Question("People should be held accountable for their own behavior, and if holding people accountable for their own behavior entails capital punishment, then so be it. However, no person should be held accountable for behavior over which he or she had no control.\n"
				+ "Which of the following is the most logical conclusion of the argument above?","People should not be held accountable for the ...........behavior of other people.","People have control over their own behavior.",
				"People cannot control the behavior of other people.","People have control over behavior that is subject ...........to capital punishment.","People have control over their own behavior.");
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