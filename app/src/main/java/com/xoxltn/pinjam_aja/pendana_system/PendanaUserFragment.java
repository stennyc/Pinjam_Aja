package com.xoxltn.pinjam_aja.pendana_system;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.xoxltn.pinjam_aja.AboutActivity;
import com.xoxltn.pinjam_aja.LoginActivity;
import com.xoxltn.pinjam_aja.R;

import java.util.Objects;

/**
 * A simple {@link Fragment} subclass.
 */
public class PendanaUserFragment extends Fragment {

    private FirebaseAuth mAuth;
    private DocumentReference mDocRef;
    private FirebaseUser mFireUser;
    private View mView;

    private TextView mUserNamaPendana, mUserEmailPendana;

    private String mNama, mEmail;

    //-------------------------------------------------------------------------------------------//

    public PendanaUserFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mView = inflater.inflate(R.layout.fragment_pendana_user, container, false);

        mAuth = FirebaseAuth.getInstance();
        mFireUser = mAuth.getCurrentUser();
        FirebaseFirestore fire = FirebaseFirestore.getInstance();

        String userID = mFireUser.getUid();
        mDocRef = fire.collection("USER").document(userID);

        // OBJECT HOOK
        mUserNamaPendana = mView.findViewById(R.id.nama_user_pendana);
        mUserEmailPendana = mView.findViewById(R.id.email_user_pendana);

        CallUserName();
        CallUserEmail();

        buttonPendanaLogOut();
        buttonTentangClick();

        return mView;
    }

    //-------------------------------------------------------------------------------------------//

    @Override
    public void onResume() {
        super.onResume();

        mUserNamaPendana.setText(mNama);
        mUserEmailPendana.setText(mEmail);
    }

    //-------------------------------------------------------------------------------------------//

    private void CallUserName() {
        mDocRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                DocumentSnapshot docLog = Objects.requireNonNull(task.getResult());
                mNama = docLog.getString("name");
                mUserNamaPendana.setText(mNama);
            }
        });
    }

    private void CallUserEmail() {
        mEmail = mFireUser.getEmail();
        mUserEmailPendana.setText(mEmail);
    }

    //-------------------------------------------------------------------------------------------//

    private void buttonPendanaLogOut() {
        Button buttonLogoutPendana = mView.findViewById(R.id.button_PendanaLogout);
        buttonLogoutPendana.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (mAuth.getCurrentUser() != null) {
                    mAuth.signOut();

                    Toast.makeText(getActivity(), "LOGOUT SUCCESS",
                            Toast.LENGTH_SHORT).show();

                    Intent backToLogin = new Intent(getActivity(), LoginActivity.class);
                    backToLogin.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    backToLogin.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    startActivity(backToLogin);
                    Objects.requireNonNull(getActivity()).finish();
                }

            }
        });
    }

    private void buttonTentangClick() {
        Button buttonTentangPeminjam = mView.findViewById(R.id.button_TentangApps);
        buttonTentangPeminjam.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent goToAbout = new Intent(getActivity(), AboutActivity.class);
                startActivity(goToAbout);
            }
        });
    }
}
