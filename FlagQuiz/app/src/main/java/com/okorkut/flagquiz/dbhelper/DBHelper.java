package com.okorkut.flagquiz.dbhelper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.okorkut.flagquiz.common.Common;
import com.okorkut.flagquiz.model.Question;
import com.okorkut.flagquiz.model.Ranking;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

public class DBHelper extends SQLiteOpenHelper {

    private static String  DB_NAME = "FlagQuizDB.db";
    private static String DB_PATH;

    private SQLiteDatabase database;

    private Context context = null;

    public DBHelper(Context context) {
        super(context, DB_NAME, null, 1);
        this.context = context;

        DB_PATH = context.getApplicationInfo().dataDir + File.separator + "databases" + File.separator;
    }

    public void openDatabase(){
        String path = DB_PATH  + DB_NAME;

        Log.i("DBHelper", " DB Path:" + path);

        database = SQLiteDatabase.openDatabase(path, null, SQLiteDatabase.OPEN_READWRITE);
    }

    public void copyDatabase() throws Exception{
        try {
            InputStream inputStream =  context.getAssets().open(DB_NAME);

            String outputFileName = DB_PATH + DB_NAME;

            OutputStream outputStream = new FileOutputStream(outputFileName);

            byte[] buffer = new byte[1024];
            int length;
            while ((length = inputStream.read(buffer)) > 0) {
                outputStream.write(buffer, 0, length);
            }

            outputStream.flush();
            outputStream.close();
            inputStream.close();
        }catch (Exception e){
            Log.e("DBHelper Error", e.getMessage());
            e.printStackTrace();
        }
    }

    private boolean checkDatabase(){
        SQLiteDatabase liteDatabase = null;
        try {
            liteDatabase = SQLiteDatabase.openDatabase(DB_PATH + DB_NAME, null, SQLiteDatabase.OPEN_READWRITE);
        } catch (Exception e){
            Log.e("DBHelper Error", e.getMessage());
            e.printStackTrace();
        }

        if (liteDatabase != null){
            liteDatabase.close();
            return true;
        } else {
            return false;
        }
    }

    public void createDatabase() throws Exception{
        boolean isDBExists = checkDatabase();

        if (!isDBExists){
            this.getReadableDatabase();

            try{
                copyDatabase();
            } catch(Exception e){
                e.printStackTrace();
            }
        }
    }

    @Override
    public synchronized void close() {

        if (database != null) {
            database.close();
        }
        super.close();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    //CRUD for table
    public List<Question> getAllQuestion(){

        List<Question>  questions = new ArrayList<Question>();

        SQLiteDatabase db = this.getWritableDatabase();

        Cursor c;

        try{
            c = db.rawQuery("select * from Question order by random()", null);

            if (c == null){
                return  null;
            }

            c.moveToFirst();

            Question question = null;

            do {

                int id= c.getInt(c.getInt(c.getColumnIndex("id")));
                String image = c.getString(c.getColumnIndex("iamge"));
                String answerA = c.getString(c.getColumnIndex("AnswerA"));
                String answerB = c.getString(c.getColumnIndex("answerB"));
                String answerC = c.getString(c.getColumnIndex("answerC"));
                String answerD = c.getString(c.getColumnIndex("answerD"));
                String correctAnswer = c.getString(c.getColumnIndex("AnswerA"));

                question = new Question(id, image, answerA, answerB, answerC,  answerD, correctAnswer);

                questions.add(question);
            } while (c.moveToNext());
        }catch (Exception e){
            Log.e("DBHelper Error", e.getMessage());
            e.printStackTrace();
        }

        db.close();

        return  questions;
    }


    //We need improve this function to optimize process from Playing
    public List<Question> getQuestionMode(String mode){

        List<Question>  questions = new ArrayList<Question>();

        SQLiteDatabase db = this.getWritableDatabase();

        Cursor c;

        int limit = 0;

        if (mode.equalsIgnoreCase(Common.MODE.EASY.toString())){
            limit = 30;
        } else if (mode.equalsIgnoreCase(Common.MODE.MEDIUM.toString())){
            limit = 50;
        } else if (mode.equalsIgnoreCase(Common.MODE.HARD.toString())){
            limit = 100;
        } else if (mode.equalsIgnoreCase(Common.MODE.HARDEST.toString())){
            limit = 200;
        }

        try{
            String sql = String.format("select * from Question order by random() LIMIT %d ", limit);
            c = db.rawQuery(sql, null);

            if (c == null){
                return  null;
            }

            c.moveToFirst();

            Question question = null;

            do {

                int id= c.getInt(c.getInt(c.getColumnIndex("id")));
                String image = c.getString(c.getColumnIndex("iamge"));
                String answerA = c.getString(c.getColumnIndex("AnswerA"));
                String answerB = c.getString(c.getColumnIndex("answerB"));
                String answerC = c.getString(c.getColumnIndex("answerC"));
                String answerD = c.getString(c.getColumnIndex("answerD"));
                String correctAnswer = c.getString(c.getColumnIndex("AnswerA"));

                question = new Question(id, image, answerA, answerB, answerC,  answerD, correctAnswer);

                questions.add(question);
            } while (c.moveToNext());
        }catch (Exception e){
            Log.e("DBHelper Error", e.getMessage());
            e.printStackTrace();
        }

        db.close();

        return  questions;
    }

    //insert score to Ranking table
    public void insertScore(int score){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues contentValues = new ContentValues();

        contentValues.put("score",score);

        db.insert("Ranking",null, contentValues);

        db.close();
    }

    //get score and sort ranking
    public List<Ranking> getRanking(){

        List<Ranking> rankingList = new ArrayList<Ranking>();

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor c;

        try{
            c = db.rawQuery("Select * from Ranking ORDER BY Score DESC;", null);
            if(c==null){
                return null;
            }

            do{
                int id = c.getInt(c.getColumnIndex("Id"));
                double score = c.getDouble(c.getColumnIndex("Score"));

                Ranking ranking = new Ranking(id,score);
                rankingList.add(ranking);
            }while (c.moveToNext());
            c.close();
        }catch (Exception e){
           Log.e("DBHelper Error", e.getMessage());
           e.printStackTrace();
        }

        db.close();

        return  rankingList;
    }
}
