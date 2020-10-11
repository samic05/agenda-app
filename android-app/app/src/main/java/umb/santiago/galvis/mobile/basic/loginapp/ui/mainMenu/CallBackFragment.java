package umb.santiago.galvis.mobile.basic.loginapp.ui.mainMenu;

import umb.santiago.galvis.mobile.basic.loginapp.data.model.Agenda;

import java.util.List;

public interface CallBackFragment {
    void switchToCreateEntry();
    void switchToLoggedInScreen(List<Agenda> entries);
    void goBackToLoginFragment();
}
