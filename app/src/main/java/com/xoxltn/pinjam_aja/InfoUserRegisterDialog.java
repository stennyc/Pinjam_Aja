/*
 * Created by Albert Kristaen (Kayzweller) on 03/07/20 22.25
 * Copyright (c) 2020 . All rights reserved.
 * Last modified 25/06/20 10.08
 */

package com.xoxltn.pinjam_aja;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import com.xoxltn.pinjam_aja.MainRulesActivity;

public class InfoUserRegisterDialog extends DialogFragment {

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("Perhatian!")
                .setMessage("Sebelum melanjutkan Registrasi, " +
                        "Anda diharapkan membaca Aturan dan Resiko " +
                        "serta menyetujuinya terlebih dahulu.")
                .setNegativeButton("Lanjut >", (dialog, which) -> {
                    Intent goToFaq = new Intent(getActivity(), MainRulesActivity.class);
                    startActivity(goToFaq);
                });

        return builder.create();
    }

}

