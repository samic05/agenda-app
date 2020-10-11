package umb.santiago.galvis.mobile.basic.loginapp.ui.mainMenu;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import umb.santiago.galvis.mobile.basic.loginapp.R;
import umb.santiago.galvis.mobile.basic.loginapp.data.model.Agenda;
import umb.santiago.galvis.mobile.basic.loginapp.provider.DataBaseProvider;

import java.io.IOException;
import java.util.List;

public class MainMenuFragment extends Fragment {
    private CallBackFragment callBackFragment;
    private DataBaseProvider dataBaseProvider;
    private EditText username,password;
    private String usernameString, passwordString;
    private Button loginButton, registerButton;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View loginView = inflater.inflate(R.layout.login_fragment, container, false);
        loginButton = loginView.findViewById(R.id.loginButton);
        registerButton = loginView.findViewById(R.id.registerButton);

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dataBaseProvider = DataBaseProvider.getDataBaseProvider();
                try {
                    dataBaseProvider.getAllEntries();
                    List<Agenda> entries = dataBaseProvider.getResult();
                    callBackFragment.switchToLoggedInScreen(entries);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            callBackFragment.switchToCreateEntry();
            }
        });

        return loginView;
    }

    public void setCallBackFragment(CallBackFragment callBackFragment) {
        this.callBackFragment = callBackFragment;
    }


}

