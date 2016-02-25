package com.example.scientia;

import java.util.ArrayList;
import java.util.List;
import com.example.scientia.Question;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class IqDbHelper extends SQLiteOpenHelper {

	private static final int DATABASE_VERSION = 1;
	// Database Name
	private static final String DATABASE_NAME = "iqQuiz";
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
	
	public IqDbHelper(Context context) {
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
		
	}
	private void addQuestions()
	{
		Question q1=new Question("We need to cut a cake in more than 6 pieces. How many cuts we must make atleast to get this?","2", "3","4", "5","3");
		this.addQuestion(q1);
		Question q2=new Question("Cake: Bakery:: Beer: X", "Brewery", "Distillery", "Wine shop","Vinification","Brewery");
		this.addQuestion(q2);
		Question q3=new Question("The mirror image of a clock at 2:45 p.m. will show the following time:", "9:15 p.m.","9:15 a.m.","None of these","9:15","9:15" );
		this.addQuestion(q3);
		Question q4=new Question("How many meters will I be away from my home if I travel 5 metres towards north, take a right and travel 4 metres and travel 5 metres towards south?", "4","10","14","5","4");
		this.addQuestion(q4);
		Question q5=new Question("(896)²-(205)² is divisible by 691","691","1101","Both a and b","None of these","Both a and b");
		this.addQuestion(q5);
		Question q6=new Question("Reindeer: Fawn:: Llama:", "Colt","Cria","Foal","Kid","Cria");
		this.addQuestion(q6);
		Question q7=new Question("Which number should come next in the series? \n1 2 3 5 8 13","8","13","21","28","21");
		this.addQuestion(q7);
		Question q8=new Question("PEACH is to HCAEP as 46251 is to:", "15264","26541","52614","15246","15264");
		this.addQuestion(q8);
		Question q9=new Question("If you take the first alphabet of each word in this proverb, Birds of same flock fly together, "
				+ "and reverse the order it will form the following word.", "BOSFFT","TFFSOB","TFSFOB","TSSFOB","TFFSOB");
		this.addQuestion(q9);
		Question q10=new Question("Finger is to Hand as Leaf is to:","Twig","Tree","Branch","Bark","Twig");
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
