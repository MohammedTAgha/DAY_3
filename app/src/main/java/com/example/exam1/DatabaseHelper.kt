package com.example.exam1



import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteException
import android.database.sqlite.SQLiteOpenHelper
import android.icu.text.CaseMap



class DatabaseHelper (context: Context): SQLiteOpenHelper(context, DATABASE_NAME,null,DATABASE_VERSION){
    companion object {
        private val DATABASE_VERSION = 1
        private val DATABASE_NAME = "school_db"
        private val TABLE = "school"
        private val KEY_ID = "id"
        private val KEY_NAME = "name"
        private val KEY_ADDRESS = "address"
        private val KEY_PHONE = "phone"
        private val KEY_NUMBER = "number"

    }

    override fun onCreate(db: SQLiteDatabase?) {
        //creating table with fields
        val CREATE_TABLE_STATMENT = ("CREATE TABLE "
                + TABLE + "("
                + KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + KEY_NAME + " VARCHAR(200),"
                + KEY_ADDRESS + " VARCHAR(200),"
                + KEY_PHONE + " VARCHAR(200),"
                + KEY_NUMBER + " INTEGER"
                + ")")
        db?.execSQL(CREATE_TABLE_STATMENT)
    }

    override fun onUpgrade(db: SQLiteDatabase?, p1: Int, p2: Int) {
        db!!.execSQL("DROP TABLE IF EXISTS " + TABLE)
        onCreate(db)
    }

    //method to insert data
    fun add(post: SchoolModle):Long{
        val db = this.writableDatabase

        val contentValues = ContentValues()

        contentValues.put(KEY_NAME, post.Name)
        contentValues.put(KEY_ADDRESS, post.Address)
        contentValues.put(KEY_PHONE, post.Phone)
        contentValues.put(KEY_NUMBER, post.Number)


        // Inserting Row
        val success = db.insert(TABLE, null, contentValues)

        //2nd argument is String containing nullColumnHack
        db.close() // Closing database connection
        return success
    }

    //method to read data
    fun view():List<SchoolModle>{

        val SchoolList:ArrayList<SchoolModle> = ArrayList<SchoolModle>()
        val selectQuery = "SELECT * FROM $TABLE"
        val db = this.readableDatabase
        var cursor: Cursor? = null
        try{
            cursor = db.rawQuery(selectQuery, null)
        }catch (e: SQLiteException) {
            db.execSQL(selectQuery)
            return ArrayList()
        }

        var Id: Int
        var Name:String
        var Address:String
        var Phone:String
        var Number:Int


        if (cursor.moveToFirst()) {
            do {
                Id = cursor.getInt(cursor.getColumnIndex("id"))
                Name = cursor.getString(cursor.getColumnIndex("name"))
                Address = cursor.getString(cursor.getColumnIndex("address"))
                Phone = cursor.getString(cursor.getColumnIndex("phone"))
                Number = cursor.getInt(cursor.getColumnIndex("number"))

                val emp= SchoolModle(Id, Name, Address, Phone, Number)
                SchoolList.add(emp)
            } while (cursor.moveToNext())
        }
        return SchoolList
    }

    //method to update data

    //method to delete data

    fun deleteSchool(id: Int):Int{
        val db = this.writableDatabase
        val contentValues = ContentValues()
        contentValues.put(KEY_ID, id)
        val success = db.delete(TABLE,"$KEY_ID="+ id,null)
        db.close()
        return success
    }


}