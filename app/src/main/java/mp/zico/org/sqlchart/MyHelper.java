package mp.zico.org.sqlchart;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

/**
 * Created by zico on 17/09/2017.
 */

public class MyHelper extends SQLiteOpenHelper {
//    private Context con;
    public MyHelper(Context context) {
        super(context, "MyDatabase", null, 1);
//        con=context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String createTable="create table myTable (xValues INTEGER, yValues INTEGER);";
        db.execSQL(createTable);
//        Toast.makeText(con, "Table Created", Toast.LENGTH_LONG).show();
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public void insertData(int valX, int valY){
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put("xValues",valX);
        contentValues.put("yValues",valY);
        db.insert("myTable", null, contentValues);
//        Toast.makeText(con, "Data Inserted", Toast.LENGTH_LONG).show();
    }

}
