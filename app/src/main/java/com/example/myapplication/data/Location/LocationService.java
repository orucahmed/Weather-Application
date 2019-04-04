package com.example.myapplication.data.Location;



import io.reactivex.Observable;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface LocationService {
    @GET("get-time-zone")
    Observable<TimeOffest> getOffset(@Query(value="key") String key, @Query(value="format") String format, @Query(value = "by") String by, @Query(value = "lng") double lng, @Query(value = "lat" )double lat);


}
