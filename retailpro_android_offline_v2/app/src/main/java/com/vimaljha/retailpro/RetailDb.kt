package com.vimaljha.retailpro

import android.content.*
import android.database.sqlite.*

class RetailDb(ctx: Context): SQLiteOpenHelper(ctx,"retailpro_offline.db",null,1){
 override fun onCreate(db: SQLiteDatabase){
  db.execSQL("CREATE TABLE products(id INTEGER PRIMARY KEY AUTOINCREMENT, barcode TEXT UNIQUE, name TEXT NOT NULL, category TEXT, unit TEXT DEFAULT 'pcs', purchase_price REAL DEFAULT 0, selling_price REAL DEFAULT 0, gst_rate REAL DEFAULT 0, stock REAL DEFAULT 0, min_stock REAL DEFAULT 0)")
  db.execSQL("CREATE TABLE customers(id INTEGER PRIMARY KEY AUTOINCREMENT,name TEXT NOT NULL,phone TEXT,address TEXT,opening_balance REAL DEFAULT 0)")
  db.execSQL("CREATE TABLE suppliers(id INTEGER PRIMARY KEY AUTOINCREMENT,name TEXT NOT NULL,phone TEXT,address TEXT,opening_balance REAL DEFAULT 0)")
  db.execSQL("CREATE TABLE sales(id INTEGER PRIMARY KEY AUTOINCREMENT,invoice_no TEXT UNIQUE,customer_id INTEGER,subtotal REAL,discount REAL,gst_total REAL,grand_total REAL,payment_method TEXT,paid_amount REAL,created_at INTEGER)")
  db.execSQL("CREATE TABLE sale_items(id INTEGER PRIMARY KEY AUTOINCREMENT,sale_id INTEGER,product_id INTEGER,barcode TEXT,product_name TEXT,qty REAL,unit TEXT,rate REAL,gst_rate REAL,gst_amount REAL,line_total REAL)")
  db.execSQL("CREATE TABLE purchases(id INTEGER PRIMARY KEY AUTOINCREMENT,purchase_no TEXT UNIQUE,supplier_id INTEGER,subtotal REAL,gst_total REAL,grand_total REAL,paid_amount REAL,created_at INTEGER)")
  db.execSQL("CREATE TABLE purchase_items(id INTEGER PRIMARY KEY AUTOINCREMENT,purchase_id INTEGER,product_id INTEGER,qty REAL,unit TEXT,rate REAL,gst_rate REAL,gst_amount REAL,line_total REAL)")
  db.execSQL("CREATE TABLE ledger(id INTEGER PRIMARY KEY AUTOINCREMENT,party_type TEXT,party_id INTEGER,reference_type TEXT,reference_id INTEGER,debit REAL,credit REAL,note TEXT,created_at INTEGER)")
  db.execSQL("CREATE TABLE settings(id INTEGER PRIMARY KEY CHECK(id=1),shop_name TEXT,owner_name TEXT,phone TEXT,address TEXT)")
  db.execSQL("INSERT INTO settings VALUES(1,'Vimal Jha Kirana Shop','Vimal Jha','','')")
 }
 override fun onUpgrade(db: SQLiteDatabase,o:Int,n:Int){}
 fun q(sql:String,args:Array<String> = emptyArray()): MutableList<MutableMap<String,Any?>> { val out= mutableListOf<MutableMap<String,Any?>>(); readableDatabase.rawQuery(sql,args).use{c-> while(c.moveToNext()){val m=mutableMapOf<String,Any?>();for(i in 0 until c.columnCount)m[c.getColumnName(i)]=when(c.getType(i)){1->c.getLong(i);2->c.getDouble(i);3->c.getString(i);else->null};out.add(m)}};return out }
 fun exec(sql:String,args:Array<Any?>=emptyArray()):Long { val st=writableDatabase.compileStatement(sql);args.forEachIndexed{idx,v->when(v){null->st.bindNull(idx+1);is Number->st.bindDouble(idx+1,v.toDouble());else->st.bindString(idx+1,v.toString())}};return st.executeInsert().let{if(it==-1L)0L else it} }
}
