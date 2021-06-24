package com.LokoTechnology.maingoquizagain;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.LokoTechnology.maingoquizagain.QuizContract.QuestionTable;

import java.util.ArrayList;

public class atoplamadb extends SQLiteOpenHelper {

   private static final String DATABASE_NAME = "GoQuiz.db5";
   private static final int DATBASE_VERSION = 5;

    private SQLiteDatabase db;


    atoplamadb(Context context) {
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

        Questions q1 = new Questions("8+9 =?","17","16","15","14",3);
        addQuestions(q1);


        Questions q2 = new Questions("5+6 =?","11","9","10","7",1);
        addQuestions(q2);

        Questions q3 = new Questions("8+32 =?","40","41","42","38",1);
        addQuestions(q3);

        Questions q4 = new Questions("7+9 =?","14","19","16","15",3);
        addQuestions(q4);

        Questions q5 = new Questions("13+12 =?","24","25","26","27",2);
        addQuestions(q5);

        Questions q6 = new Questions("18+71 =?","89","99","96","87",1);
        addQuestions(q6);

        Questions q7 = new Questions("20+64 =?","74","89","84","77",3);
        addQuestions(q7);

        Questions q8 = new Questions("70+21 =?","94","91","86","97",2);
        addQuestions(q8);

        Questions q9 = new Questions("97+13 =?","110","109","119","107",1);
        addQuestions(q9);

        Questions q10 = new Questions("55+15 =?","70","69","86","77",1);
        addQuestions(q10);

        Questions q11 = new Questions("23+79 =?","104","109","102","107",3);
        addQuestions(q11);

        Questions q12 = new Questions("98+41 =?","134","139","136","137",2);
        addQuestions(q12);

        Questions q13 = new Questions("25+36 =?","61","69","66","67",1);
        addQuestions(q13);

        Questions q14 = new Questions("29+17 =?","61","69","56","46",4);
        addQuestions(q14);

        Questions q15 = new Questions("12+66 =?","78","79","76","77",1);
        addQuestions(q15);

        Questions q16 = new Questions("45+63 =?","108","109","96","107",1   );
        addQuestions(q16);

        Questions q17 = new Questions("45+25 =?","70","69","76","77",1);
        addQuestions(q17);

        Questions q18 = new Questions("78+11 =?","74","89","86","87",2);
        addQuestions(q18);

        Questions q19 = new Questions("11+55 =?","64","69","66","67",3);
        addQuestions(q19);
        //0178 ² ° //0176

        Questions q20 = new Questions("33+74 =?","104","109","106","107",4);
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


