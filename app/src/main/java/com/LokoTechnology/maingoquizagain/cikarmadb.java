package com.LokoTechnology.maingoquizagain;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.LokoTechnology.maingoquizagain.QuizContract.QuestionTable;

import java.util.ArrayList;

public class cikarmadb extends SQLiteOpenHelper {

   private static final String DATABASE_NAME = "GoQuiz.db3";
   private static final int DATBASE_VERSION = 3;

    private SQLiteDatabase db;


    cikarmadb(Context context) {
        super(context, DATABASE_NAME,null, DATBASE_VERSION);
    }



    @Override
    public void onCreate(SQLiteDatabase db) {

        this.db = db;

        final String SQL_CREATE_QUESTIONS_TABLE = "CREATE TABLE " +
                QuestionTable.TABLE_NAME + " ( " +
                QuestionTable._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                QuestionTable.COLUMN_QUESTION + " TEXT, " +
                QuestionTable.COLUMN_OPTION1 + " TEXT, " +
                QuestionTable.COLUMN_OPTION2 + " TEXT, " +
                QuestionTable.COLUMN_OPTION3 + " TEXT, " +
                QuestionTable.COLUMN_OPTION4 + " TEXT, " +
                QuestionTable.COLUMN_ANSWER_NR + " INTEGER" +
                ")";

        db.execSQL(SQL_CREATE_QUESTIONS_TABLE);
        fillQuestionsTable();
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP TABLE IF EXISTS " + QuestionTable.TABLE_NAME);
        onCreate(db);

    }


    private void fillQuestionsTable()
    {

        Questions q1 = new Questions("10-4","5","9","6","7",3);
        addQuestions(q1);

        Questions q2 = new Questions("25-12 =?","13","19","16","12",1);
        addQuestions(q2);

        Questions q3 = new Questions("46-21 =?","25","29","26","27",1);
        addQuestions(q3);

        Questions q4 = new Questions("84-62 =?","24","22","26","17",2);
        addQuestions(q4);

        Questions q5 = new Questions("87-58 =?","34","29","63","27",2);
        addQuestions(q5);

        Questions q6 = new Questions("74-62 =?","12","19","8","7",1);
        addQuestions(q6);

        Questions q7 = new Questions("63-12 =?","54","49","60","51",4);
        addQuestions(q7);

        Questions q8 = new Questions("32-18 =?","14","19","16","17",1);
        addQuestions(q8);

        Questions q9 = new Questions("98-45 =?","54","53","56","57",2);
        addQuestions(q9);

        Questions q10 = new Questions("45-31 =?","14","19","16","12",1);
        addQuestions(q10);

        Questions q11 = new Questions("62-14-2 =?","46","49","56","57",1);
        addQuestions(q11);

        Questions q12 = new Questions("76-38 =?","38","39","36","37",1);
        addQuestions(q12);

        Questions q13 = new Questions("69-41 =?","34","28","26","37",2);
        addQuestions(q13);

        Questions q14 = new Questions("87-54 =?","33","39","36","27",1);
        addQuestions(q14);

        Questions q15 = new Questions("90-26 =?","64","69","56","77",1);
        addQuestions(q15);

        Questions q16 = new Questions("120-51 =?","64","69","67","87",2);
        addQuestions(q16);

        Questions q17 = new Questions("96-57 =?","34","39","36","37",2);
        addQuestions(q17);

        Questions q18 = new Questions("122-87 =?","35","39","36","47",1);
        addQuestions(q18);

        Questions q19 = new Questions("120-65 =?","55","59","56","57",1);
        addQuestions(q19);
        //0178 ² ° //0176

        Questions q20 = new Questions("156-95 =?","61","69","60","73",1);
        addQuestions(q20);



    }

    private void addQuestions(Questions question){

        ContentValues cv = new ContentValues();
        cv.put(QuestionTable.COLUMN_QUESTION,question.getQuestion());
        cv.put(QuestionTable.COLUMN_OPTION1,question.getOption1());
        cv.put(QuestionTable.COLUMN_OPTION2,question.getOption2());
        cv.put(QuestionTable.COLUMN_OPTION3,question.getOption3());
        cv.put(QuestionTable.COLUMN_OPTION4,question.getOption4());
        cv.put(QuestionTable.COLUMN_ANSWER_NR,question.getAnswerNr());
        db.insert(QuestionTable.TABLE_NAME,null,cv);

    }

    public ArrayList<Questions> getAllQuestions() {

        ArrayList<Questions> questionList = new ArrayList<>();
        db = getReadableDatabase();



        String Projection[] = {

                QuestionTable._ID,
                QuestionTable.COLUMN_QUESTION,
                QuestionTable.COLUMN_OPTION1,
                QuestionTable.COLUMN_OPTION2,
                QuestionTable.COLUMN_OPTION3,
                QuestionTable.COLUMN_OPTION4,
                QuestionTable.COLUMN_ANSWER_NR
        };



            Cursor c = db.query(QuestionTable.TABLE_NAME,
                    Projection,
                    null,
                    null,
                    null,
                    null,
                    null);


            if (c.moveToFirst()) {
                do {

                    Questions question = new Questions();
                    question.setQuestion(c.getString(c.getColumnIndex(QuestionTable.COLUMN_QUESTION)));
                    question.setOption1(c.getString(c.getColumnIndex(QuestionTable.COLUMN_OPTION1)));
                    question.setOption2(c.getString(c.getColumnIndex(QuestionTable.COLUMN_OPTION2)));
                    question.setOption3(c.getString(c.getColumnIndex(QuestionTable.COLUMN_OPTION3)));
                    question.setOption4(c.getString(c.getColumnIndex(QuestionTable.COLUMN_OPTION4)));
                    question.setAnswerNr(c.getInt(c.getColumnIndex(QuestionTable.COLUMN_ANSWER_NR)));

                    questionList.add(question);

                } while (c.moveToNext());

            }
            c.close();
            return questionList;

        }

    }


