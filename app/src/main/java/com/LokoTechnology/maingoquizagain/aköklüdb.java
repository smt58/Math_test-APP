package com.LokoTechnology.maingoquizagain;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.LokoTechnology.maingoquizagain.QuizContract.QuestionTable;

import java.util.ArrayList;

public class aköklüdb extends SQLiteOpenHelper {

   private static final String DATABASE_NAME = "GoQuiz.db6";
   private static final int DATBASE_VERSION = 6;

    private SQLiteDatabase db;


    aköklüdb(Context context) {
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

        Questions q1 = new Questions("√16 =?","4","9","6","7",1);
        addQuestions(q1);

        Questions q2 = new Questions("√9 =?","4","9","3","7",3);
        addQuestions(q2);

        Questions q3 = new Questions("√4 =?","4","9","6","2",4);
        addQuestions(q3);

        Questions q4 = new Questions("√81 =?","10","9","6","7",1);
        addQuestions(q4);

        Questions q5 = new Questions("√100 =?","4","10","5","7",2);
        addQuestions(q5);

        Questions q6 = new Questions("√36 =?","4","9","7","6",4);
        addQuestions(q6);

        Questions q7 = new Questions("√64 =?","8","9","4","7",1);
        addQuestions(q7);

        Questions q8 = new Questions("√49 =?","4","7","9","8",2);
        addQuestions(q8);

        Questions q9 = new Questions("√121 =?","11","9","10","8",1);
        addQuestions(q9);

        Questions q10 = new Questions("√169 =?","13","9","5","7",1);
        addQuestions(q10);

        Questions q11 = new Questions("√16+√4 =?","4","9","6","7",3);
        addQuestions(q11);

        Questions q12 = new Questions("√9+√49 =?","4","10","6","7",2);
        addQuestions(q12);

        Questions q13 = new Questions("√16+√121 =?","16","13","6","15",4);
        addQuestions(q13);

        Questions q14 = new Questions("√81+√64 =?","17","16","6","7",1);
        addQuestions(q14);

        Questions q15 = new Questions("√4+√4 =?","4","9","6","7",1);
        addQuestions(q15);

        Questions q16 = new Questions("√25+√81 =?","14","19","16","12",1);
        addQuestions(q16);

        Questions q17 = new Questions("√9+√4 =?","4","3","6","5",4);
        addQuestions(q17);

        Questions q18 = new Questions("√25+√4 =?","4","9","6","7",4);
        addQuestions(q18);

        Questions q19 = new Questions("√4+√100=?","10","12","13","7",2);
        addQuestions(q19);
        //0178 ² ° //0176

        Questions q20 = new Questions("√64+√81 =?","17","15","16","7",1);
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


