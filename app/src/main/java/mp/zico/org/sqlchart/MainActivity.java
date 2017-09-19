package mp.zico.org.sqlchart;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

public class MainActivity extends AppCompatActivity {
    Button insertButton;
    EditText inputTextX, inputTextY;
    GraphView graphView;
    LineGraphSeries<DataPoint> series=new LineGraphSeries<>(new DataPoint[0]);
    MyHelper myHelper;
    SQLiteDatabase sqLiteDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        insertButton=(Button) findViewById(R.id.insertButton);
        inputTextX=(EditText) findViewById(R.id.inputTextX);
        inputTextY=(EditText) findViewById(R.id.inputTextY);
        graphView=(GraphView) findViewById(R.id.graph);

        myHelper=new MyHelper(this);
        sqLiteDatabase=myHelper.getWritableDatabase();

        exqInsert();
//        LineGraphSeries<DataPoint> series=new LineGraphSeries<>(getDataPoint());
//        graphView.addSeries(series);
    }

    private void exqInsert() {
        insertButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int xVal=Integer.parseInt(String.valueOf(inputTextX.getText()));
                int yVal=Integer.parseInt(String.valueOf(inputTextY.getText()));

                myHelper.insertData(xVal,yVal);
                series.resetData(getDataPoint());
//                series=new LineGraphSeries<DataPoint>(getData());
                graphView.addSeries(series);
            }
        });
    }



    private DataPoint[] getDataPoint() {
        // Read Data from database
        String[] columns={"xValues","yValues"};
        Cursor cursor=sqLiteDatabase.query("myTable",columns,null,null,null,null,null);
        DataPoint[] dp=new DataPoint[cursor.getCount()];
        for (int i=0;i<cursor.getCount();i++)
        {
            cursor.moveToNext();
            dp[i]=new DataPoint(cursor.getInt(0), cursor.getInt(1));
        }
        return dp;
    }


/*    public DataPoint[] getDataPoint() {
        DataPoint[] dp=new DataPoint[]{
                new DataPoint(0,1),
                new DataPoint(2,5),
                new DataPoint(3,1),
                new DataPoint(5,6),
                new DataPoint(8,3)
        };
        return (dp);
    }*/
}
