package edu.utfpr.paulo.projetoinicial;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

public class Ajuda extends AppCompatActivity {

    private TextView textView;
    private Intent intent;
    private Bundle bundle;
    private String nome;
    private int situacao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ajuda);

        intent = getIntent();
        bundle = intent.getExtras();

        if(bundle != null) {
            nome = bundle.getCharSequence("nome").toString();
            situacao = bundle.getInt("situacao");
        }
        textView = findViewById(R.id.txvAjuda);
    }

    private void finalizar(){
        Intent data = new Intent(this, Cadastro.class);
        data.putExtra("nome", nome);
        data.putExtra("situacao", situacao);
        setResult(Activity.RESULT_OK, data);
        finish();
    }

    public void voltar(View view){
        finalizar();
    }

    @Override
    public void onBackPressed() {
        finalizar();
    }

}
