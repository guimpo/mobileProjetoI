package edu.utfpr.paulo.projetoinicial;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.content.Intent;
import android.widget.TextView;
import android.widget.Toast;

public class Cadastro extends AppCompatActivity {

    private EditText etxNome;
    private RadioGroup rdGroup;
    public static final int DADOS_USUARIO = 1;

    private static final String ARQUIVO = "preferencias";
    private static final String SITUACAO = "SITUACAO";
    private static final String NOME = "NOME";

    private int opcao = -1;
    private String nome = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro);

        etxNome = (EditText) findViewById(R.id.etxNome);
        rdGroup = (RadioGroup) findViewById(R.id.radioGroup);

        lerPreferencias();
        etxNome.setText(nome);
        rdGroup.check(opcao);

        etxNome.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
                if(i == EditorInfo.IME_ACTION_DONE){
                    salvarPreferencias();
                }
                return false;
            }
        });

        rdGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                salvarPreferencias();
            }
        });
    }

    @Override
    protected void onPause() {
        super.onPause();
        salvarPreferencias();
    }

    //
    // Menu
    //
    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.ajuda, menu);
        return true;
    }

    //
    // Chamar telas
    //
    private void chamaTela(Class<?> classe) {
        Intent intent = new Intent(this, classe);
        salvarPreferencias();
        intent.putExtra("nome", nome);
        intent.putExtra("situacao", opcao);
        startActivityForResult(intent, DADOS_USUARIO);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu:
                chamaTela(Ajuda.class);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void chamaDica(View view) {
        chamaTela(Dica.class);
    }

    //
    // Retorno de outras activities
    //
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        boolean retornoOk = (requestCode == DADOS_USUARIO && resultCode == Activity.RESULT_OK);
        Bundle bundle = data.getExtras();
        if (retornoOk && bundle != null ){
            etxNome.setText(bundle.getCharSequence("nome"));
            rdGroup.check(bundle.getInt("situacao"));
        }
    }

    //
    // Tratar preferências
    //
    private void lerPreferencias() {
        SharedPreferences shared = getSharedPreferences(ARQUIVO, Context.MODE_PRIVATE);
        opcao = shared.getInt(SITUACAO, opcao);
        nome = shared.getString(NOME, nome);
    }

    private void salvarPreferencias() {
        SharedPreferences shared = getSharedPreferences(ARQUIVO, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = shared.edit();
        nome = etxNome.getText().toString();
        opcao = rdGroup.getCheckedRadioButtonId();
        editor.putInt(SITUACAO, opcao);
        editor.putString(NOME, nome);
        editor.apply();
    }

    //
    // Limpar cadastro
    //
    public void limparCadastro(View view) {
        etxNome.setText(null);
        rdGroup.clearCheck();
        opcao = -1;
        nome = "";
        salvarPreferencias();
    }
}
