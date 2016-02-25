package com.example.scientia;

import java.util.ArrayList;
import java.util.List;
import com.example.scientia.Question;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class VerbalDbHelper extends SQLiteOpenHelper {

	private static final int DATABASE_VERSION = 1;
	// Database Name
	private static final String DATABASE_NAME = "verbalQuiz";
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
	
	public VerbalDbHelper(Context context) {
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
		Question q1=new Question("Please choose the word with the closest in meaning: \nA decisive, catastrophic conflict.","battle", "periwinkle","armageddon","sacerdotal","battle");
		this.addQuestion(q1);
		Question q2=new Question("Please choose the word with the closest in meaning: \nRoughness or harshness, as of surface, sound, or climate", "asperity","bedlam","concoction","semial","asperity");
		this.addQuestion(q2);
		Question q3=new Question("Select the option that completes the given para in most appropriate way. \nThe _________ science of seismology has grown just enough so that the first overly bold theories have been ___________.", "magnetic..accepted","fledgling..refuted","tentative..analyze", "predictive..protected"," fledgling..refuted" );
		this.addQuestion(q3);
		Question q4=new Question("Select the option that completes the given para in most appropriate way.\nNonviolent demonstrations often create such tensions that a community that has constantly refused to ______ its injustices is forced to correct them: the injustices can no longer be ______.", "acknowledge..ignored", "decrease..verified","tolerate..accepted","address..eliminated", "acknowledge..ignored");
		this.addQuestion(q4);
		Question q5=new Question("Select the option that completes the given para in most appropriate way.\nThe actual ______ of Wilson's position was always ______ by his refusal to compromise after having initially agreed to negotiate a settlement","outcome..foreshadowed","logic..enhanced","rigidity..betrayed","uncertainty..alleviated","uncertainty..alleviated");
		this.addQuestion(q5);
		Question q6=new Question("Identify the option with the correct spelling.", "paraphernalia", "programme","pediatrics", "beserk","pediatrics");
		this.addQuestion(q6);
		Question q7=new Question("Which two sentences in the following convey the same idea? \n"
				+ "1. Wasn’t there any checking at the airport?\n2. I want to know if there was any checking at the airport.\n3. I wonder if there should have been any checking at the airport?\n4. There should have been checking at the airport.","2, 3","1, 4","3, 4","2, 4", "2, 3");
		this.addQuestion(q7);
		Question q8=new Question("Choose the grammatically correct sentence.", "You should at once report it to the concerned authority.","You should report it at once, to the authority concerned.","You should report it at once, to the concerned authority.","You should at once report it to the authority concerned." ,"You should at once report it to the authority concerned.");
		this.addQuestion(q8);
		Question q9=new Question("Antonym of NEOPHYTE", "student", "clown","veteran","professional","veteran");
		this.addQuestion(q9);
		Question q10=new Question("A contextual usage is provided for the word below. Pick the word that is most inappropriate.\nMALINGER: The young man made it a point to malinger inspite of the assigned work load.","Wander","Laze","Evade","Argue","Wander");
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
