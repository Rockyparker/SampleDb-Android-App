package example.rockyparker.sampledb;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import example.rockyparker.sampledb.DatabaseNames.DatabaseEntry;
public class ViewEntriesActivity extends AppCompatActivity {
    private SQLiteDatabase mdb;
    DbHelper dbhelper;
    ListView lv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_entries);
        dbhelper=new DbHelper(this);
        lv=(ListView) findViewById(R.id.listing);
        viewingData();
    }
    public Cursor getData(){
        mdb=dbhelper.getReadableDatabase();
        String[] col={};
        Cursor cr=mdb.query(DatabaseEntry.TABLE_NAME,col,null,null,null,null,null);
        return cr;
    }
    public void viewingData()
    {
        Cursor cr=getData();
        ArrayList<String> arraylst = new ArrayList<String>();
        cr.moveToFirst();
        String str="Name Details";
        arraylst.add(str);
        for(int i=0;i<cr.getCount();i++){
            String str1=cr.getString(0)+"  "+cr.getString(1);
            arraylst.add(str1);
            cr.moveToNext();
        }
        cr.close();
        Log.d("SIZE",arraylst.size()+"");
        String[] tem = new String[arraylst.size()];
        tem=arraylst.toArray(tem);

        ArrayAdapter adapter1 = new ArrayAdapter<String>(ViewEntriesActivity.this,
                R.layout.activity_listview, tem);
        lv.setAdapter(adapter1);
    }
}
