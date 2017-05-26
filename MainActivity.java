package application.example.com.volleyrobson;

import android.content.Intent;
import android.os.Looper;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;

import java.util.HashMap;
import java.util.Map;
import java.util.jar.Manifest;

public class MainActivity extends AppCompatActivity {
    //Declaração dos EditText que tem no xml//
    EditText nome;
    EditText apelido;
    EditText email;
    EditText idade;
    //Declaração das "chaves", será o nome do POST para pegar no php//
    public static final String key_nome = "nome";
    public static final String key_apelido = "apelido";
    public static final String key_email = "email";
    public static final String key_idade = "idade";
    //pagina em php que fará ligação com o banco de dados//
    private static final String URL = "http://evocar.16mb.com/volley_add.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //Instanciar os EditText do xml//
        nome = (EditText)findViewById(R.id.nome);
        apelido = (EditText)findViewById(R.id.apelido);
        email = (EditText)findViewById(R.id.email);
        idade = (EditText)findViewById(R.id.idade);
    }

    public void cadastrar(View v) throws JSONException{
        //Obtenção dos valores que serão enviados para o php//
        final String valor_nome = nome.getText().toString();
        final String valor_apelido = apelido.getText().toString();
        final String valor_email = email.getText().toString();
        final String valor_idade = idade.getText().toString();

        StringRequest request = new StringRequest(Request.Method.POST/*O método em que será enviado para a pagina php, são possiveis os envios em POST e GET*/,
                URL/*URL definida como final, é usada para saber para onde mandar os valores, funciona como action, do php.*/,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response/*É o echo que mostra faz no php, se executa ou não*/) {
                        Toast.makeText(MainActivity.this,response,Toast.LENGTH_LONG).show();//Toast de resposta para o usuário
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(MainActivity.this,error.toString(),Toast.LENGTH_LONG).show();//Toast que mostra o erro, caso aconteça
                    }
                }){
            @Override
            protected Map<String,String> getParams(){//Esse método retorna um HashMap com pares de Valor de Chave, vamos colocar os dados que devem ser enviados para o HashMap
                Map<String,String> params = new HashMap<String, String>();
                params.put(key_nome, valor_nome);
                params.put(key_apelido, valor_apelido);
                params.put(key_email, valor_email);
                params.put(key_idade, valor_idade);
                return params;
            }
        };
        //Adicionar o pedido do requirimente na fila, que será enviado ao banco de dados
        RequestQueue fila = Volley.newRequestQueue(this);
        fila.add(request);
    }
}
