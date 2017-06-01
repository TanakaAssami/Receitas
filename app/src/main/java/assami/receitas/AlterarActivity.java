package assami.receitas;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class AlterarActivity extends AppCompatActivity {

    EditText nomeReceita;
    EditText igrendientes;
    EditText mododepreparo;
    EditText servquantaspessoas;
    Button alterar;
    Button deletar;
    Cursor cursor;
    Controller crud;
    String codigo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alterar);

        codigo = this.getIntent().getStringExtra("codigo");

        crud = new Controller(getBaseContext());

        nomeReceita = (EditText)findViewById(R.id.editText4);
        igrendientes = (EditText)findViewById(R.id.editText5);
        mododepreparo = (EditText)findViewById(R.id.editText6);
        servquantaspessoas = (EditText)findViewById(R.id.editText7);

        alterar = (Button)findViewById(R.id.button2);

        cursor = crud.carregaDadoById(Integer.parseInt(codigo));
        nomeReceita.setText(cursor.getString(cursor.getColumnIndexOrThrow(Banco.NOMEDARECEITA)));
        igrendientes.setText(cursor.getString(cursor.getColumnIndexOrThrow(Banco.INGREDIENTES)));
        mododepreparo.setText(cursor.getString(cursor.getColumnIndexOrThrow(Banco.MODODEPREPARO)));
        servquantaspessoas.setText(cursor.getString(cursor.getColumnIndexOrThrow(Banco.QUANTIDADEDEPESSOASSERV)));

        alterar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                crud.alteraRegistro(Integer.parseInt(codigo), nomeReceita.getText().toString(),igrendientes.getText().toString(),
                        mododepreparo.getText().toString(),  servquantaspessoas.getText().toString());
                Intent intent = new Intent(AlterarActivity.this,ConsultarActivity.class);
                startActivity(intent);
                finish();
            }
        });

        deletar = (Button)findViewById(R.id.button3);
        deletar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                crud.deletaRegistro(Integer.parseInt(codigo));
                Intent intent = new Intent(AlterarActivity.this,ConsultarActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
}

