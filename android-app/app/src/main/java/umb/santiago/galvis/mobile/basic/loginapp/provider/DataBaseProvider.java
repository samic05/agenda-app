package umb.santiago.galvis.mobile.basic.loginapp.provider;

import android.widget.Toast;
import okhttp3.OkHttpClient;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import umb.santiago.galvis.mobile.basic.loginapp.data.model.Agenda;

import java.io.IOException;
import java.util.List;


public class DataBaseProvider{


    public List<Agenda> getResult() {
        return result;
    }

    private List<Agenda> result;
    private Agenda resultSave;
    private static DataBaseProvider dataBaseProvider = null;
    private static final String GET_ENTRIES_PATH = "get-all";
    private static final String URL = "http:/10.0.2.2:8101/";

    public void getAllEntries() throws IOException {
        new Thread(){
            @Override
            public void run() {
                synchronized (DataBaseProvider.this){
                    super.run();
                    try {
                        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
                        Retrofit retrofit = new Retrofit.Builder()
                                .baseUrl(URL)
                                .addConverterFactory(GsonConverterFactory.create())
                                .client(httpClient.build())
                                .build();
                        Response<List<Agenda>> response = retrofit.create(DataBaseCaller.class).findAllEntries(GET_ENTRIES_PATH)
                                .execute();
                        DataBaseProvider.this.setResult(response.body());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    DataBaseProvider.this.notifyAll();
                }
            }
        }.start();
        synchronized(Thread.currentThread()) {
            try {
                Thread.currentThread().wait(1000L);
            } catch (InterruptedException e) {
                System.out.println("Main Thread interrupted while waiting");
                e.printStackTrace();
            }
        }
    }

    public void saveEntry(Agenda agenda) throws IOException {
        new Thread(){
            @Override
            public void run() {
                synchronized (DataBaseProvider.this){
                    super.run();
                    try {
                        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
                        Retrofit retrofit = new Retrofit.Builder()
                                .baseUrl(URL)
                                .addConverterFactory(GsonConverterFactory.create())
                                .client(httpClient.build())
                                .build();
                        Response<Agenda> response = retrofit.create(DataBaseCaller.class).registerEntry("save",agenda)
                                .execute();
                        DataBaseProvider.this.setResultSave(response.body());

                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    DataBaseProvider.this.notifyAll();
                }
            }
        }.start();
        synchronized(Thread.currentThread()) {
            try {
                Thread.currentThread().wait(1000L);
            } catch (InterruptedException e) {
                System.out.println("Main Thread interrupted while waiting");
                e.printStackTrace();
            }
        }
    }

    public static DataBaseProvider getDataBaseProvider(){
        if(dataBaseProvider==null){
            dataBaseProvider = new DataBaseProvider();
        }
        return dataBaseProvider;
    }

    public void setResult(List<Agenda> result) {
        this.result = result;
    }

    public Agenda getResultSave() {
        return resultSave;
    }

    public void setResultSave(Agenda resultSave) {
        this.resultSave = resultSave;
    }
}
