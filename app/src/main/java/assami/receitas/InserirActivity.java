package assami.receitas;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class InserirActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inserir);

        Button botao = (Button)findViewById(R.id.button);

        botao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Controller crud = new Controller(getBaseContext());
                EditText receita = (EditText)findViewById(R.id.editText);
                EditText igredientes = (EditText)findViewById((R.id.editText2));
                EditText modoDePreparo = (EditText)findViewById(R.id.editText7);
                EditText servequantaspessoa = (EditText)findViewById(R.id.editText4);
                String receitaString = receita.getText().toString();
                String igredientesString = igredientes.getText().toString();
                String mododepreparoString = modoDePreparo.getText().toString();
                String servequantaspessoaString = servequantaspessoa.getText().toString();
                String resultado;

                resultado = crud.insereDado(receitaString,igredientesString,mododepreparoString,servequantaspessoaString);

                Toast.makeText(getApplicationContext(), resultado, Toast.LENGTH_LONG).show();
            }
        });
    }
}
