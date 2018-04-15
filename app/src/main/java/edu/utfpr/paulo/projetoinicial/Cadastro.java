package edu.utfpr.paulo.projetoinicial;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.content.Intent;

public class Cadastro extends AppCompatActivity {

    private EditText etxNome;
    private RadioGroup rdGroup;
    public static final int DADOS_USUARIO = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro);

        etxNome = (EditText) findViewById(R.id.etxNome);
        rdGroup = (RadioGroup) findViewById(R.id.radioGroup);
    }

    public void limparCadastro(View view) {
        etxNome.setText(null);
        rdGroup.clearCheck();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.ajuda, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.menu:
                chamaAjuda();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void chamaAjuda() {
        Intent intent = new Intent(this, Ajuda.class);
        intent.putExtra("nome", etxNome.getText().toString());
        intent.putExtra("situacao", rdGroup.getCheckedRadioButtonId());
        startActivityForResult(intent, DADOS_USUARIO);
    }

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
}
