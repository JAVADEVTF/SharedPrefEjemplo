package com.fjrodriguez.sharedprefejemplo;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;


public class MyActivity extends Activity {

    static EditText usuarioTxt;
    static EditText usuarioIntentos;
    static CheckBox usuarioCheckbox;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my);

        usuarioTxt = (EditText) findViewById(R.id.editTextUsuario);
        usuarioIntentos = (EditText) findViewById(R.id.editTextIntentos);
        usuarioCheckbox  = (CheckBox) findViewById(R.id.checkBox);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.my, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void guardarConfiguracion (View view) {

        SharedPreferences configuracion = getSharedPreferences("MmConf", MODE_PRIVATE);
        // MODE_PRIVATE, MODE_WORLD_READABLE, MODE_WORLD_WRITEABLE.

        // Editamos el fichero de configuración.
        SharedPreferences.Editor editor = configuracion.edit();
        editor.putString("Usuario", usuarioTxt.getText().toString());
        editor.putInt("Max_intentos", Integer.parseInt(usuarioIntentos.getText().toString()));
        editor.putBoolean("Repetir_color", usuarioCheckbox.isChecked());

        // Guardamos la configuración.
        editor.commit();
    }

    public void recuperarConfiguracion (View view) {

        SharedPreferences configuracion = getSharedPreferences("MmConf", MODE_PRIVATE);

        usuarioTxt.setText(configuracion.getString("Usuario" , "User"));
        usuarioIntentos.setText(String.valueOf(configuracion.getInt("Max_intentos", 8)));
        usuarioCheckbox.setChecked(configuracion.getBoolean("Repetir_color", false));
    }
}
