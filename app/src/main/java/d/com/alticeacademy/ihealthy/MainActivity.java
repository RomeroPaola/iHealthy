package d.com.alticeacademy.ihealthy;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import java.util.ArrayList;


public class MainActivity extends AppCompatActivity implements View.OnClickListener{


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final ArrayList<MenuItem> menu= new ArrayList<>();
        setMenu(menu);
        AdapterItem adapter = new AdapterItem(this, menu);
        ListView listView= findViewById(R.id.Lista);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapter, View view, int i, long l) {
                Class myClass=null;
                switch (i)
                {
                    case 0: myClass =IdealWeightActivity.class; break;

                }
                goActivity(myClass);
            }


        });


    }
    @Override
    public void onClick(View view) {


    }
    public void goActivity(Class myclass){
        Intent intent = new Intent(this, myclass);
        startActivity(intent);
    }
    public void setMenu (ArrayList<MenuItem> menu){
     MenuItem menuItem0 = new MenuItem("Ideal Weight", 0, getResources().getDrawable(R.drawable.ic_idealweight), getResources().getDrawable(R.drawable.ic_go));
     MenuItem menuItem1 = new MenuItem("IMC", 1, getResources().getDrawable(R.drawable.ic_imc), getResources().getDrawable(R.drawable.ic_go));
     MenuItem menuItem2 = new MenuItem("Tips", 2, getResources().getDrawable(R.drawable.ic_tips), getResources().getDrawable(R.drawable.ic_go));
     MenuItem menuItem3 = new MenuItem("Timeline", 3, getResources().getDrawable(R.drawable.ic_timeline), getResources().getDrawable(R.drawable.ic_go));
     menu.add(0,menuItem0);
     menu.add(1,menuItem1);
     menu.add(2,menuItem2);
     menu.add(3,menuItem3);



    }
}