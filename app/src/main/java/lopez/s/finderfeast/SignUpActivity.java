package lopez.s.finderfeast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class SignUpActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;
    private EditText name;
    private EditText email;
    private EditText username;
    private EditText password;

    @Override
    protected void onStart() {
        super.onStart();

        //name, email, username, password
        name = findViewById(R.id.name);
        email = findViewById(R.id.email);
        username = findViewById(R.id.username);
        password = findViewById(R.id.password);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        mAuth = FirebaseAuth.getInstance();

    }

    public void submit(View view) {
        signUp(email.getText().toString(), password.getText().toString());
    }

    private void signUp(String email, String password) {
        if (email.isEmpty()) {
            this.email.setError("Please provide an email.");
        } else if (password.isEmpty()) {
            this.password.setError("Please provide a password.");
        } else if (password.length() < 6) {
            this.password.setError("Password needs to be longer than 6 characters.");
        } else if (!email.isEmpty() && !password.isEmpty()) {
            mAuth.createUserWithEmailAndPassword(email, password)
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                //Check sign up successful and change the screen to be logged in.
                                Log.d("createdUser", "UserCreated.");
                                FirebaseUser user = mAuth.getCurrentUser();
                                Intent intent = new Intent(SignUpActivity.this, MainActivity.class);
                                startActivity(intent);
                            } else {
                                Toast.makeText(SignUpActivity.this.getApplicationContext(), "Sign-up unsuccessful: " + task.getException().getMessage(), Toast.LENGTH_LONG).show();
                            }
                        }
                    });
        } else {
            Toast.makeText(SignUpActivity.this, "Error", Toast.LENGTH_SHORT).show();
        }
    }

}
