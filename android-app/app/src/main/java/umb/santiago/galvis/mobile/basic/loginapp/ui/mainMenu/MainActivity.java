package umb.santiago.galvis.mobile.basic.loginapp.ui.mainMenu;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;
import umb.santiago.galvis.mobile.basic.loginapp.R;
import umb.santiago.galvis.mobile.basic.loginapp.data.model.Agenda;
import umb.santiago.galvis.mobile.basic.loginapp.ui.handler.getAllEntriesFragment;
import umb.santiago.galvis.mobile.basic.loginapp.ui.handler.createNewEntryFragment;

import java.util.List;

public class MainActivity extends AppCompatActivity implements CallBackFragment {
    private MainMenuFragment mainMenuFragment;
    private createNewEntryFragment createNewEntryFragment;
    private getAllEntriesFragment agendaEntriesFragment;
    private FragmentTransaction fragmentTransaction;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        fragmentTransaction = getSupportFragmentManager().beginTransaction();
        showLoginFragment();
    }

    private void showLoginFragment(){
        mainMenuFragment = new MainMenuFragment();
        mainMenuFragment.setCallBackFragment(this);
        fragmentTransaction = fragmentTransaction.addToBackStack(null);
        fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.add(R.id.fragmentContainer, mainMenuFragment);
        fragmentTransaction.commit();
    }
    private void showEntries(List<Agenda> entries){
        agendaEntriesFragment = new getAllEntriesFragment(entries);
        agendaEntriesFragment.setCallBackFragment(this);
        fragmentTransaction = fragmentTransaction.addToBackStack(null);
        fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.fragmentContainer, agendaEntriesFragment);
        fragmentTransaction.commit();
    }

    public void goBackToLoginFragment(){
        mainMenuFragment = new MainMenuFragment();
        mainMenuFragment.setCallBackFragment(this);
        fragmentTransaction = fragmentTransaction.addToBackStack(null);
        fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.fragmentContainer, mainMenuFragment);
        fragmentTransaction.commit();
    }

    public void goToEntry(){
        createNewEntryFragment = new createNewEntryFragment();
        createNewEntryFragment.setCallBackFragment(this);
        fragmentTransaction = fragmentTransaction.addToBackStack(null);
        fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.fragmentContainer, createNewEntryFragment);
        fragmentTransaction.commit();
    }
    @Override
    public void switchToCreateEntry() {
        goToEntry();
    }

    @Override
    public void switchToLoggedInScreen(List<Agenda> entries) {
        showEntries(entries);
    }

}
