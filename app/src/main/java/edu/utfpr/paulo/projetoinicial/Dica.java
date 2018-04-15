package edu.utfpr.paulo.projetoinicial;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class Dica extends AppCompatActivity {

    private TextView textView;
    private Intent intent;
    private Bundle bundle;
    private String nome;
    private int situacao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dica);

        intent = getIntent();
        bundle = intent.getExtras();
        textView = findViewById(R.id.txvDica);

        if(bundle != null) {
            nome = bundle.getCharSequence("nome").toString();
            situacao = bundle.getInt("situacao");
            escreveMsg(situacao);
        }
    }

    private void escreveMsg(int situacao) {
        switch (situacao) {
            case R.id.rdEmpregado:
                textView.setText(getString(R.string.msgDica1) + nome + "!");
                break;
            case R.id.rdDesempregado:
                textView.setText(getString(R.string.msgDica2) + nome);
                break;
            case R.id.rdDream:
                textView.setText(getString(R.string.msgDica3) + nome + "!");
                break;
            default:
                return;
        }
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
