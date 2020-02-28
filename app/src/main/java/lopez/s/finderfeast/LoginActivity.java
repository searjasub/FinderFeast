package lopez.s.finderfeast;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LoginActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;
    private TextView loginText;
    private EditText username;
    private EditText password;

    public FirebaseUser getFirebaseUser() {
        return FirebaseAuth.getInstance().getCurrentUser();
    }

    @Override
    protected void onStart() {
        super.onStart();
        loginText = findViewById(R.id.login_info);
        FirebaseUser currentUser = mAuth.getCurrentUser();
        username = findViewById(R.id.username);
        password = findViewById(R.id.password);

        if (currentUser != null) {
            updateUI(currentUser);
        } else {
            Toast.makeText(this, "Not logged in", Toast.LENGTH_SHORT).show();
        }
    }

    private void updateUI(FirebaseUser currentUser) {

        loginText.setText(currentUser.getEmail() + "signed in.");

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

//        DatabaseConnection.connect();
        mAuth = FirebaseAuth.getInstance();
    }

    public void signUp(View view) {

        String email = "";
        String password = "";


        Intent intent = new Intent(this, SignUpActivity.class);
        startActivity(intent);

    }

    private void signIn(String email, String password) {
        Log.e("Reach", "Sign in reached.");
        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Log.e("Reach", "task was successful.");
                            // Sign in success, update UI with the signed-in user's information
                            System.out.println("signInWithEmail:success");
                            FirebaseUser user = mAuth.getCurrentUser();
                            if (user != null) {
                                Log.e("Reach", "User was not null");
                                updateUI(user);
                                Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                                startActivity(intent);
                            } else {
                                Toast.makeText(LoginActivity.this, "Not logged in", Toast.LENGTH_SHORT).show();
                            }
                        } else {
                            // If sign in fails, display a message to the user.
                            System.out.println("signInWithEmail:failure \n" + task.getException());
                            Log.e("Reach", task.getException().getLocalizedMessage());
                            Toast.makeText(LoginActivity.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
//                            updateUI(null);
                        }

                    }
                });
    }

    public void login(View view) {
        Log.e("Reach", "Login method.");
        System.out.println("Reached the Login method.");
        signIn(username.getText().toString(), password.getText().toString());

    }
}
