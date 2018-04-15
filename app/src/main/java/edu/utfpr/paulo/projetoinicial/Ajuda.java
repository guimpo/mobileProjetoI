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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ajuda);

        textView = findViewById(R.id.txvAjuda);
    }

    private void finalizar(){
        setResult(Activity.RESULT_OK, getIntent());
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
