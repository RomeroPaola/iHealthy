package d.com.alticeacademy.ihealthy;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import java.text.DecimalFormat;


public class IdealWeightActivity extends AppCompatActivity implements View.OnClickListener {
    ToggleButton toggleButton;
    Button idealButton, info;
    RadioGroup radioGroup;
    String gender, complexion;
    Double minIdealWeight = null, maxIdealWeight = null, height;
    Spinner alturaSpinner1, alturaSpinner2, complexSpinner;

    TextView min, max, minlib, maxlib;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ideal_weight);
        info=findViewById(R.id.btn_info);
        toggleButton = findViewById(R.id.toggleButton);
        idealButton = findViewById(R.id.btn_ideal_weight);
        toggleButton.setOnClickListener(this);
        alturaSpinner1 = findViewById(R.id.spinner1);
        alturaSpinner2 = findViewById(R.id.spinner2);
        complexSpinner = findViewById(R.id.spinner3);
        alturaSpinner2.setEnabled(false);
        setSpinner(R.array.complex, complexSpinner);
        setSpinner(R.array.alturaCm, alturaSpinner1);
        radioGroup = findViewById(R.id.radioGroup);
        min = findViewById(R.id.txtMin);
        max = findViewById(R.id.txtMax);
        minlib = findViewById(R.id.minLib);
        maxlib= findViewById(R.id.maxlib);
        idealButton.setOnClickListener(this);
        info.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {

            case R.id.toggleButton:
                setHeight(); break;
            case R.id.btn_ideal_weight: getIdealWeight(); break;
            case R.id.btn_info: getInfo(view); break;


        }
    }

    public void setHeight() {

        if (toggleButton.isChecked()) {
            setSpinner(R.array.alturaCm, alturaSpinner1);
            alturaSpinner2.setSelection(0);
            alturaSpinner2.setEnabled(false);
        } else {
            setSpinner(R.array.alturaPies, alturaSpinner1);
            setSpinner(R.array.alturaPulgada, alturaSpinner2);
            alturaSpinner2.setEnabled(true);

        }

    }


    public void setSpinner(int id, Spinner spinner) {

        ArrayAdapter arrayAdapter = ArrayAdapter.createFromResource(this, id, android.R.layout.simple_spinner_item);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(arrayAdapter);

    }
    public void getInfo(View view)
    {
            // inflate the layout of the popup window
        final ViewGroup nullParent = null;
            LayoutInflater inflater = (LayoutInflater)
                getSystemService(LAYOUT_INFLATER_SERVICE);

        View popupView = inflater.inflate(R.layout.popup_complexion, nullParent);

        // create the popup window
        int width = LinearLayout.LayoutParams.WRAP_CONTENT;
        int height = LinearLayout.LayoutParams.WRAP_CONTENT;
        // lets taps outside the popup also dismiss it
        final PopupWindow popupWindow = new PopupWindow(popupView, width, height, true);

        // show the popup window
        // which view you pass in doesn't matter, it is only used for the window tolken
        popupWindow.showAtLocation(view, Gravity.CENTER, 0, 0);

    }
    public void getIdealWeight() {

        RadioButton radioButton = findViewById(radioGroup.getCheckedRadioButtonId());

        if (toggleButton.isChecked()) {
            //Altura en metros
            if (alturaSpinner1.getSelectedItem() != null && complexSpinner.getSelectedItem() != null && radioButton != null) {
                height = (Double.parseDouble(alturaSpinner1.getSelectedItem().toString()));

            } else {

                Toast.makeText(this, "Tell us all your details please", Toast.LENGTH_SHORT).show();
                return;
            }
        } else {
            //Altura en pies y pulgadas
            if (alturaSpinner1.getSelectedItem() != null && alturaSpinner2.getSelectedItem() != null && complexSpinner.getSelectedItem() != null && radioButton != null) {
                height = Double.parseDouble(alturaSpinner1.getSelectedItem().toString()) * 0.3048;
                height = height + (Double.parseDouble(alturaSpinner2.getSelectedItem().toString()) * 0.0254);

            } else {
                Toast.makeText(this, "Tell us all your details please", Toast.LENGTH_SHORT).show();
                return;
            }
        }
        if (height != -1) {
            complexion = complexSpinner.getSelectedItem().toString();
            gender = radioButton.getText().toString();
            getWeight1();
            getWeight2();
            StringBuilder sb = new StringBuilder();
            DecimalFormat df = new DecimalFormat("#.00");
            if(minIdealWeight<maxIdealWeight) {
                min.setText(sb.append(df.format(minIdealWeight)).append(" KG"));
                sb.setLength(0);
                 minlib.setText(sb.append(df.format(minIdealWeight*2.22)).append(" LB"));
                sb.setLength(0);
                max.setText(sb.append(df.format(maxIdealWeight)).append(" KG"));
                sb.setLength(0);
                maxlib.setText(sb.append(df.format(maxIdealWeight*2.22)).append(" LB"));
            }
            else
            {
                min.setText(sb.append(df.format(maxIdealWeight)).append(" KG"));
                sb.setLength(0);
                minlib.setText(sb.append(df.format(maxIdealWeight*2.22)).append(" LB"));
                sb.setLength(0);
                max.setText(sb.append(df.format(minIdealWeight)).append(" KG"));
                sb.setLength(0);
                maxlib.setText(sb.append(df.format(minIdealWeight*2.22)).append(" LB"));
            }

        }

    }

    public void getWeight1() {
        if (gender.equalsIgnoreCase("Male")) {
            minIdealWeight = (((((height * 100) - 152.4) / 2.54) * 2.7) + 48.124);
        }
        if (gender.equalsIgnoreCase("Female")) {
            minIdealWeight = ((((height * 100 - 152.4) / 2.54) * 2.7) + 45.4);
        }
       }

    public void getWeight2()
    {
        if (gender.equalsIgnoreCase("Male")) {
            switch (complexion) {
                case "Small":
                    maxIdealWeight = (height * height * 22) - 2; break;
                case "Medium":
                    maxIdealWeight = (height * height * 22); break;
                case "Large":
                    maxIdealWeight = (height * height * 22) + 2; break;
            }

        }
        if (gender.equalsIgnoreCase("Female")) {
            switch (complexion) {
                case "Small":
                    maxIdealWeight = (height * height * 21.7) - 2; break;
                case "Medium":
                    maxIdealWeight = (height * height * 21.7); break;
                case "Large":
                    maxIdealWeight = (height * height * 21.7) + 2; break;
            }
        }

    }

}

