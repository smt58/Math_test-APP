package com.LokoTechnology.maingoquizagain;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.LokoTechnology.maingoquizagain.QuizContract.*;

import java.util.ArrayList;

public class carpmadb extends SQLiteOpenHelper {

   private static final String DATABASE_NAME = "GoQuiz.db4";
   private static final int DATBASE_VERSION = 4;

    private SQLiteDatabase db;


    carpmadb(Context context) {
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

        Questions q1 = new Questions("3×2 =?","5","9","6","7",3);
        addQuestions(q1);

        Questions q2 = new Questions("6×7 =?","42","49","46","47",1);
        addQuestions(q2);

        Questions q3 = new Questions("3×4 =?","12","9","6","7",1);
        addQuestions(q3);

        Questions q4 = new Questions("3×9 =?","24","29","26","27",4);
        addQuestions(q4);

        Questions q5 = new Questions("7×9 =?","63","69","66","67",1);
        addQuestions(q5);

        Questions q6 = new Questions("4×7 =?","24","29","28","27",3);
        addQuestions(q6);

        Questions q7 = new Questions("12*5 =?","58","79","60","70",3);
        addQuestions(q7);

        Questions q8 = new Questions("9×10 =?","92","90","96","79",2);
        addQuestions(q8);

        Questions q9 = new Questions("9×5 =?","45","59","56","57",1);
        addQuestions(q9);

        Questions q10 = new Questions("7×5 =?","35","39","36","37",1);
        addQuestions(q10);

        Questions q11 = new Questions("15×5 =?","47","97","76","75",4);
        addQuestions(q11);

        Questions q12 = new Questions("12×6 =?","77","79","67","72",4);
        addQuestions(q12);

        Questions q13 = new Questions("17×4 =?","64","91","68","87",3);
        addQuestions(q13);

        Questions q14 = new Questions("25×4 =?","104","109","106","100",4);
        addQuestions(q14);

        Questions q15 = new Questions("14×3 =?","124","129","123","127",3);
        addQuestions(q15);

        Questions q16 = new Questions("26×2 =?","52","59","56","47",1);
        addQuestions(q16);

        Questions q17 = new Questions("7×3 =?","21","29","26","17",1);
        addQuestions(q17);

        Questions q18 = new Questions("4×22 =?","84","89","88","78",3);
        addQuestions(q18);

        Questions q19 = new Questions("12×8 =?","84","96","67","97",2);
        addQuestions(q19);
        //0178 ² ° //0176

        Questions q20 = new Questions("4×2×4 =?","32","39","36","27",1);
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


