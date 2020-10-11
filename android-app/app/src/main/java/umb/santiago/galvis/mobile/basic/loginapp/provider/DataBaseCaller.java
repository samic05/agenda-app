package umb.santiago.galvis.mobile.basic.loginapp.provider;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;
import retrofit2.http.Url;
import umb.santiago.galvis.mobile.basic.loginapp.data.model.Agenda;

import java.util.List;

public interface DataBaseCaller {

    @POST
    Call<List<Agenda>> findAllEntries(@Url String path);

    @POST
    Call<Agenda> registerEntry(@Url String path, @Body Agenda body);

}
