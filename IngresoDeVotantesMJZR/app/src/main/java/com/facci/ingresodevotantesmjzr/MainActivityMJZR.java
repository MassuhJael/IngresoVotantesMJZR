package com.facci.ingresodevotantesmjzr;

import android.database.Cursor;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivityMJZR extends AppCompatActivity {

    DBHelper dbSQLITE;

    EditText txtID,txtNombre,txtApellido,txtRecintoElectoral,txtAnoNacimiento;

    Button btnInsertar,btnModificar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_mjzr);

        dbSQLITE = new DBHelper(this);
    }

    public void insertarClick(View v){

        txtNombre = (EditText) findViewById(R.id.txtNombre);
        txtApellido = (EditText) findViewById(R.id.txtApellido);
        txtRecintoElectoral = (EditText) findViewById(R.id.txtRecintoElectoral);
        txtAnoNacimiento = (EditText) findViewById(R.id.txtAnoNacimiento);

        boolean estaInsertado = dbSQLITE.insertar(txtNombre.getText().toString(),txtApellido.getText().toString(),txtRecintoElectoral.getText().toString(),Integer.parseInt(txtAnoNacimiento.getText().toString()));

        if(estaInsertado )
            Toast.makeText(MainActivityMJZR.this,"Datos Ingresador",Toast.LENGTH_SHORT).show();
        else
            Toast.makeText(MainActivityMJZR.this,"Lo sentimos ocurrio un error",Toast.LENGTH_SHORT).show();
    }

    public void buscartodosClick(View v){

        Cursor res = dbSQLITE.selectbuscartodos();
        if(res.getCount() == 0){
            mostrarMensaje("Error","No se encontraron registros");
            return;
        }

        StringBuffer buffer = new StringBuffer();

        while (res.moveToNext()){
            buffer.append("Id :"+res.getString(0)+"\n");
            buffer.append("Nombre :"+res.getString(1)+"\n");
            buffer.append("Apellido :"+res.getString(2)+"\n");
            buffer.append("RecintoElectoral :"+res.getString(3)+"\n");
            buffer.append("AnoNacimiento :"+res.getString(4)+"\n\n");


        }

        mostrarMensaje("Registros",buffer.toString());

    }
    public void mostrarMensaje(String titulo, String Mensaje) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(titulo);
        builder.setMessage(Mensaje);
        builder.show();
    }

    public void modificarRegistroClick(View v){

        txtID = (EditText) findViewById(R.id.txtID);
        txtNombre = (EditText) findViewById(R.id.txtNombre);
        txtApellido = (EditText) findViewById(R.id.txtApellido);
        txtRecintoElectoral = (EditText) findViewById(R.id.txtRecintoElectoral);
        txtAnoNacimiento = (EditText) findViewById(R.id.txtAnoNacimiento);

        boolean estaActualizado = dbSQLITE.modificarRegistro(txtID.getText().toString(),txtNombre.getText().toString(),txtApellido.getText().toString(),txtRecintoElectoral.getText().toString(),Integer.parseInt(txtAnoNacimiento.getText().toString()));
        if (estaActualizado == true){
            Toast.makeText(MainActivityMJZR.this,"Registro Actualizado",Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(MainActivityMJZR.this,"ERROR: Registro NO Actualizado",Toast.LENGTH_SHORT).show();
        }
    }

    public void eliminarRegistroClick(View v){

        txtID = (EditText) findViewById(R.id.txtID);
        Integer registrosEliminados = dbSQLITE.eliminarRegistro(txtID.getText().toString());
        if(registrosEliminados > 0 ){
            Toast.makeText(MainActivityMJZR.this,"Registro(s) Eliminado(s)",Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(MainActivityMJZR.this,"ERROR: Registro no eliminado",Toast.LENGTH_SHORT).show();
        }

    }

}
