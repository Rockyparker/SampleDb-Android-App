package example.rockyparker.sampledb;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import example.rockyparker.sampledb.DatabaseNames.DatabaseEntry;


public class AddEntryActivity extends AppCompatActivity {
    private SQLiteDatabase mdb;
    EditText et1,et2;
    Button b1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_entry);

        et1 = (EditText) findViewById(R.id.editText);
        et2 = (EditText) findViewById(R.id.editText2);

        Button b1 = (Button) findViewById(R.id.button3);
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addNewEntry(et1.getText().toString(),et2.getText().toString());
            }
        });

        DbHelper dbhelper=new DbHelper(this);
        mdb = dbhelper.getWritableDatabase();
    }
    private void addNewEntry(String name, String details) {
        ContentValues cv = new ContentValues();
        cv.put(DatabaseEntry.NAME, name);
        cv.put(DatabaseEntry.DETAILS, details);
        mdb.insert(DatabaseEntry.TABLE_NAME, null, cv);
        Toast.makeText(this, "Data inserted successfully", Toast.LENGTH_SHORT).show();
    }
}
