package com.example.profi25.Presentation;

import static com.example.profi25.Common.Utils.APIKEY;
import static com.example.profi25.Common.Utils.BASE_URL1;
import static com.example.profi25.Common.Utils.CONTENT_TYPE;
import static com.example.profi25.Common.Utils.user;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.profi25.Domain.Api;
import com.example.profi25.R;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.card.MaterialCardView;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class SignInActivity extends AppCompatActivity {

    MaterialButton button;
    TextInputEditText email, pswrd;
    Retrofit retrofit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_sign_in);

        email = findViewById(R.id.logInEmailAddressField);
        pswrd = findViewById(R.id.logInPasswordField);
        button = findViewById(R.id.signin_button);

        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL1)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        Api api = retrofit.create(Api.class);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String e = String.valueOf(email.getText());
                String p = String.valueOf(pswrd.getText());
                boolean isOk = false;
                if (!e.isEmpty() && !p.isEmpty() && isEmailValid(e) && e.equals(e.toLowerCase())) {
                    isOk = true;
                    user.setEmail(e);
                    user.setPassword(p);
                }

                if (isOk) {
                    Call<ResponseBody> call = api.signin("password", APIKEY, CONTENT_TYPE, user);
                    call.enqueue(new Callback<ResponseBody>() {
                        @Override
                        public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                            if (response.isSuccessful()) {
                                startActivity(new Intent(SignInActivity.this, HomeActivity.class));
                            } else {
                                AlertDialogFragment alertDialogFragment = new AlertDialogFragment("Ошибка авторизации", "ответ сервера");
                                alertDialogFragment.show(getSupportFragmentManager(), "");

                            }
                        }

                        @Override
                        public void onFailure(Call<ResponseBody> call, Throwable throwable) {

                        }
                    });
                }

            }
        });


        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    public boolean isEmailValid(CharSequence email) {
        return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }
}