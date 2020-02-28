package lopez.s.finderfeast.ui.account;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.google.firebase.auth.FirebaseAuth;

import lopez.s.finderfeast.R;

public class ProfileFragment extends Fragment {

    private ProfileViewModel profileViewModel;
    private Button signOutBtn;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        profileViewModel =
                ViewModelProviders.of(this).get(ProfileViewModel.class);
        View root = inflater.inflate(R.layout.fragment_profile, container, false);
        final TextView textView = root.findViewById(R.id.text_account);
        profileViewModel.getText().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });

        final TextView textName = root.findViewById(R.id.text_name);
        final TextView favoritesList = root.findViewById((R.id.favoritesList));

        textName.setText("Testing name");

        signOutBtn = root.findViewById(R.id.signOutBtn);
        signOutBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                signOut();
            }
        });

        return root;
    }

    public void signOut() {
        FirebaseAuth.getInstance().signOut();
    }
}