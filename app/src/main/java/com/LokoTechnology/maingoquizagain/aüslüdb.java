package com.LokoTechnology.maingoquizagain;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.LokoTechnology.maingoquizagain.QuizContract.QuestionTable;

import java.util.ArrayList;

public class aüslüdb extends SQLiteOpenHelper {

   private static final String DATABASE_NAME = "GoQuiz.db2";
   private static final int DATBASE_VERSION = 2;

    private SQLiteDatabase db;


    aüslüdb(Context context) {
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

        Questions q1 = new Questions("2² = ?","5","4","6","7",2);
        addQuestions(q1);



        Questions q2 = new Questions("9² =?","81","89","86","77",1);
        addQuestions(q2);

        Questions q3 = new Questions("7² =?","44","49","56","57",2);
        addQuestions(q3);

        Questions q4 = new Questions("4² =?","14","19","46","16",4);
        addQuestions(q4);

        Questions q5 = new Questions("3³ =?","24","29","62","27",4);
        addQuestions(q5);

        Questions q6 = new Questions("4³ =?","14","19","16","17",3);
        addQuestions(q6);

        Questions q7 = new Questions("2⁴ =?","4","9","6","7",3);
        addQuestions(q7);

        Questions q8 = new Questions("2⁵+3² =?","74","98","65","73",4);
        addQuestions(q8);

        Questions q9 = new Questions("8²+3² =?","4","9","6","7",3);
        addQuestions(q9);

        Questions q10 = new Questions("3³+3³ =?","54","59","56","47",1);
        addQuestions(q10);

        Questions q11 = new Questions("5³+2⁵ =?","154","149","146","157",4);
        addQuestions(q11);

        Questions q12 = new Questions("2²+3² =?","14","19","16","13",4);
        addQuestions(q12);

        Questions q13 = new Questions("5³ =?","125","129","126","127",1);
        addQuestions(q13);

        Questions q14 = new Questions("6² =?","54","58","56","57",2);
        addQuestions(q14);

        Questions q15 = new Questions("6²+22=?","4","9","6","7",3);
        addQuestions(q15);

        Questions q16 = new Questions("4³+14 =?","54","59","56","57",3);
        addQuestions(q16);

        Questions q17 = new Questions("7²+7 =?","4","9","6","7",3);
        addQuestions(q17);

        Questions q18 = new Questions("8²+17=?","81","79","76","87",1);
        addQuestions(q18);

        Questions q19 = new Questions("8²+16 =?","84","79","80","77",3);
        addQuestions(q19);
        //0178 ² ° //0176

        Questions q20 = new Questions("6¹+6 =?","12","19","16","11",1);
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


