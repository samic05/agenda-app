package umb.santiago.galvis.mobile.basic.loginapp.ui.handler;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import org.apache.commons.lang3.StringUtils;
import umb.santiago.galvis.mobile.basic.loginapp.R;
import umb.santiago.galvis.mobile.basic.loginapp.data.model.Agenda;
import umb.santiago.galvis.mobile.basic.loginapp.provider.DataBaseProvider;
import umb.santiago.galvis.mobile.basic.loginapp.ui.mainMenu.CallBackFragment;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class createNewEntryFragment extends Fragment {

    private CallBackFragment callBackFragment;
    private DataBaseProvider dataBaseProvider;
    private Button createButton, goBack;
    private Map<String, EditText> resources;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View viewRegister = inflater.inflate(R.layout.register_fragment, container, false);
        createButton = viewRegister.findViewById(R.id.addButton);
        goBack = viewRegister.findViewById(R.id.goBack);
        resources = new HashMap<>();

        createButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View e) {
                resources.put("actividad",viewRegister.findViewById(R.id.actividadLabel));
                resources.put("fecha",viewRegister.findViewById(R.id.fechaLabel));
                resources.put("asunto",viewRegister.findViewById(R.id.asuntoLabel));
                boolean valid =true;
                for(Map.Entry<String,EditText> single:resources.entrySet()){
                    if(StringUtils.isBlank(single.getValue().getText())){
                        valid=false;
                        Toast.makeText(getContext(), "Verifique su entrada", Toast.LENGTH_SHORT).show();
                    }
                }
                if(valid){

                    dataBaseProvider = DataBaseProvider.getDataBaseProvider();
                    try {
                        Agenda agenda = new Agenda();
                        agenda.setActividad(resources.get("actividad").getText().toString());
                        agenda.setAsunto(resources.get("asunto").getText().toString());
                        agenda.setFecha(resources.get("fecha").getText().toString());
                        dataBaseProvider.getAllEntries();
                        dataBaseProvider.saveEntry(agenda);
                        agenda = dataBaseProvider.getResultSave();
                        Toast.makeText(getContext(), "Se ha guardado el id: "+ agenda.getIdAgenda(), Toast.LENGTH_SHORT).show();
                        callBackFragment.goBackToLoginFragment();
                    } catch (IOException o) {
                        o.printStackTrace();
                    }
                }
            }
        });

        goBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                callBackFragment.goBackToLoginFragment();
            }
        });
        return viewRegister;
    }

    public void setCallBackFragment(CallBackFragment callBackFragment) {
        this.callBackFragment = callBackFragment;
    }
}

