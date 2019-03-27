package my.app;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    private Button connexion;
    private Button inscription;
    private EditText id;
    private EditText password;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // mon code
        this.connexion = (Button) findViewById(R.id.btnLogin);
        this.inscription = (Button) findViewById(R.id.btnCreateAccount);
        this.id = (EditText) findViewById(R.id.loginIdTxt);
        this.password = (EditText) findViewById(R.id.loginPasswordTxt);
        this.connexion.setEnabled(false);

        this.id.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                connexion.setEnabled(isValidEmail(s));
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });

        connexion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                executeConnexionClick();
            }
        });

    }

    public static boolean isValidEmail(CharSequence target) {
        return target != null && android.util.Patterns.EMAIL_ADDRESS.matcher(target).matches();
    }

    private void executeConnexionClick(){
        startActivity(new Intent(this, Lobby.class));
    }



}
