package umb.santiago.galvis.mobile.basic.loginapp.ui.handler;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import umb.santiago.galvis.mobile.basic.loginapp.R;
import umb.santiago.galvis.mobile.basic.loginapp.data.model.Agenda;
import umb.santiago.galvis.mobile.basic.loginapp.ui.mainMenu.CallBackFragment;

import java.util.List;

public class getAllEntriesFragment extends Fragment {

    private CallBackFragment callBackFragment;
    private List<Agenda> resources;
    private TextView fecha, asunto, actividad, id;
    private int counter;
    private Button prev, next, exit;


    public getAllEntriesFragment(List<Agenda> resources){
        this.resources = resources;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.main_screen_fragment, container, false);
        prev = view.findViewById(R.id.beforeButton);
        next = view.findViewById(R.id.nextButton);
        exit = view.findViewById(R.id.exitButton);
        counter = 0;
        graphAgenda(view);
        prev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View e) {
                if(counter!=0){
                    counter--;
                    graphAgenda(view);
                }else{
                    Toast.makeText(getContext(), "No hay entradas previas", Toast.LENGTH_LONG).show();
                }
            }
        });

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View e) {
              if(counter<resources.size()-1){
                  counter++;
                  graphAgenda(view);
              }else{
                  Toast.makeText(getContext(), "No hay mas entradas", Toast.LENGTH_LONG).show();
              }
            }
        });


        exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                callBackFragment.goBackToLoginFragment();
            }
        });

        return view;


    }

    private void graphAgenda(View view) {
        id = view.findViewById(R.id.agendaId);
        fecha = view.findViewById(R.id.fecha);
        asunto = view.findViewById(R.id.asunto);
        actividad = view.findViewById(R.id.actividad);
        Agenda single = resources.get(counter);
        id.setText("id: "+single.getIdAgenda()+"");
        fecha.setText("fecha: "+single.getFecha());
        asunto.setText(single.getAsunto());
        actividad.setText(single.getActividad());
    }

    public void setCallBackFragment(CallBackFragment callBackFragment) {
        this.callBackFragment = callBackFragment;
    }
}
