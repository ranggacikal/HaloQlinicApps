package id.luvie.luvieapps;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import id.luvie.luvieapps.R;

import id.luvie.luvieapps.SharedPreference.SharedPreferencedConfig;
import id.luvie.luvieapps.adapter.ArtikelHomeAdapter;
import id.luvie.luvieapps.adapter.DokterAktifHomeAdapter;
import id.luvie.luvieapps.adapter.MitraHomeAdapter;
import id.luvie.luvieapps.api.ConfigRetrofit;
import id.luvie.luvieapps.model.artikel.ResponseArtikel;
import id.luvie.luvieapps.model.getPlayerId.ResponseGetPlayerId;
import id.luvie.luvieapps.model.listDokterAktifHome.ResponseDokterAktifHome;
import id.luvie.luvieapps.model.mitraKlinik.ResponseDataMitra;

import com.onesignal.OSDeviceState;
import com.onesignal.OneSignal;
import com.thekhaeng.pushdownanim.PushDownAnim;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.thekhaeng.pushdownanim.PushDownAnim.MODE_SCALE;

public class HomeFragment extends Fragment {

    public HomeFragment() {
        // Required empty public constructor
    }

    private Handler handler = new Handler();

    // Define the code block to be executed
    private Runnable runnable = new Runnable() {
        @Override
        public void run() {
            loadDokterOnline();

            // Repeat every 2 seconds
            handler.postDelayed(runnable, 2000);
        }
    };

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    RecyclerView rvDokterOnline, rvMitra, rvArtikel;
    TextView txtLihatSemuaDokterTersedia, txtLihatSemuaMitra, txtLihatSemuaArtikel;
    LinearLayout linearDokterSpesialis, linearSkincare, linearJadwalKonsultasi;

    String token, token_from, user_id, user_id_from;

    private static final String ONESIGNAL_APP_ID = "67314311-5f01-4b4e-b20c-1e0f6fb9958c";

    private SharedPreferencedConfig preferencedConfig;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootview = inflater.inflate(R.layout.fragment_home, container, false);

        rvDokterOnline = rootview.findViewById(R.id.recycler_dokter_online_home);
        txtLihatSemuaDokterTersedia = rootview.findViewById(R.id.text_lihat_semua_dokter_tersedia);
        linearDokterSpesialis = rootview.findViewById(R.id.linear_dokter_spesialis_home);
        rvMitra = rootview.findViewById(R.id.recycler_mitra_klinik_home);
        txtLihatSemuaMitra = rootview.findViewById(R.id.text_lihat_semua_mitra_klinik);
        linearSkincare = rootview.findViewById(R.id.linear_skincare_home);
        linearJadwalKonsultasi = rootview.findViewById(R.id.linear_jadwal_home);
        rvArtikel = rootview.findViewById(R.id.recycler_artikel_home);
        txtLihatSemuaArtikel = rootview.findViewById(R.id.text_lihat_semua_artikel);

        preferencedConfig = new SharedPreferencedConfig(getActivity());

        handler.post(runnable);

        OneSignal.initWithContext(getContext());
        OneSignal.setAppId(ONESIGNAL_APP_ID);

        OSDeviceState device = OneSignal.getDeviceState();

        token = device.getPushToken();
        user_id = device.getUserId();

        getPlayerId();

        Log.d("checkOneSignal", "token: "+token);
        Log.d("checkOneSignal", "user_id: "+user_id);

        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
        LinearLayoutManager layoutManagerMitra = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
        LinearLayoutManager layoutArtikel = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);

        rvDokterOnline.setHasFixedSize(true);
        rvDokterOnline.setLayoutManager(layoutManager);

        rvMitra.setHasFixedSize(true);
        rvMitra.setLayoutManager(layoutManagerMitra);

        rvArtikel.setHasFixedSize(true);
        rvArtikel.setLayoutManager(layoutArtikel);

        PushDownAnim.setPushDownAnimTo(txtLihatSemuaDokterTersedia)
                .setScale( MODE_SCALE, 0.89f  )
                .setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        startActivity(new Intent(getActivity(), DokterTersediaActivity.class));
                    }
                });

        PushDownAnim.setPushDownAnimTo(linearDokterSpesialis)
                .setScale(MODE_SCALE, 0.89f)
                .setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        startActivity(new Intent(getActivity(), DokterSpesialisActivity.class));
                    }
                });

        PushDownAnim.setPushDownAnimTo(txtLihatSemuaMitra)
                .setScale(MODE_SCALE, 0.89f)
                .setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        startActivity(new Intent(getActivity(), MitraKlinikActivity.class));
                    }
                });

        PushDownAnim.setPushDownAnimTo(linearJadwalKonsultasi)
                .setScale(MODE_SCALE, 0.89f)
                .setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        startActivity(new Intent(getActivity(), JadwalKonsultasiActivity.class));
                    }
                });

        PushDownAnim.setPushDownAnimTo(linearSkincare)
                .setScale(MODE_SCALE, 0.89f)
                .setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        startActivity(new Intent(getActivity(), KategoriActivity.class));
                    }
                });

        PushDownAnim.setPushDownAnimTo(txtLihatSemuaArtikel)
                .setScale(MODE_SCALE, 0.89f)
                .setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        startActivity(new Intent(getActivity(), ArtikelActivity.class));
                    }
                });

        loadDokterOnline();
        loadMitraKlinik();
        loadArtikelHome();


        return rootview;
    }

    private void loadArtikelHome() {

        ConfigRetrofit.service.getArtikel("1", "1").enqueue(new Callback<ResponseArtikel>() {
            @Override
            public void onResponse(Call<ResponseArtikel> call, Response<ResponseArtikel> response) {
                if (response.isSuccessful()){

                    List<id.luvie.luvieapps.model.artikel.DataItem> dataArtikel = response.body().getData();
                    ArtikelHomeAdapter adapterArtikel = new ArtikelHomeAdapter(getActivity(), dataArtikel);
                    rvArtikel.setAdapter(adapterArtikel);

                }else{
                    Toast.makeText(getContext(), "Data Kosong", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseArtikel> call, Throwable t) {
                Toast.makeText(getActivity(), "Error: "+t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void getPlayerId() {

        ConfigRetrofit.service.getPlayerId(preferencedConfig.getPreferenceIdCustomer(), user_id)
                .enqueue(new Callback<ResponseGetPlayerId>() {
                    @Override
                    public void onResponse(Call<ResponseGetPlayerId> call, Response<ResponseGetPlayerId> response) {
                        if (response.isSuccessful()){

                            String test = response.body().getPlayerId();

                            Log.d("statusPushPlayer", "berhasil: "+test);

                        }else{
                            Log.d("statusPushPlayer", "GAGAL PUSH ");
                        }
                    }

                    @Override
                    public void onFailure(Call<ResponseGetPlayerId> call, Throwable t) {
                        Log.d("statusPushPlayer", "OnFailure: "+t.getMessage());
                    }
                });

    }

    private void loadMitraKlinik() {

        String status = "0";

        ConfigRetrofit.service.dataMitra(status).enqueue(new Callback<ResponseDataMitra>() {
            @Override
            public void onResponse(Call<ResponseDataMitra> call, Response<ResponseDataMitra> response) {
                if (response.isSuccessful()){
                    List<id.luvie.luvieapps.model.mitraKlinik.DataItem> dataMitra = response.body().getData();
                    MitraHomeAdapter adapter = new MitraHomeAdapter(getActivity(), dataMitra);
                    rvMitra.setAdapter(adapter);
                }
            }

            @Override
            public void onFailure(Call<ResponseDataMitra> call, Throwable t) {

            }
        });

    }

    private void loadDokterOnline() {

        ConfigRetrofit.service.dokterAktifHome("home", "1", "1").enqueue(new Callback<ResponseDokterAktifHome>() {
            @Override
            public void onResponse(Call<ResponseDokterAktifHome> call, Response<ResponseDokterAktifHome> response) {
                if (response.isSuccessful()){

                    List<id.luvie.luvieapps.model.listDokterAktifHome.DataItem> dataItems = response.body().getData();
                    DokterAktifHomeAdapter adapter = new DokterAktifHomeAdapter(getActivity(), dataItems);
                    rvDokterOnline.setAdapter(adapter);
                    Log.d("loadDokterOnline", "onResponse: Success");

                }else{
                    Toast.makeText(getActivity(), "Gagal memuat data dokter online", Toast.LENGTH_SHORT).show();

                    Log.d("loadDokterOnline", "onResponse: Response Failed");
                }
            }

            @Override
            public void onFailure(Call<ResponseDokterAktifHome> call, Throwable t) {
                Toast.makeText(getActivity(), "Error: "+t.getMessage(), Toast.LENGTH_SHORT).show();
                Log.d("loadDokterOnline", "onFailure: "+t.getMessage());
            }
        });

    }

    @Override
    public void onPause() {
        super.onPause();
        handler.removeCallbacks(runnable);
    }

    @Override
    public void onResume() {
        super.onResume();
        handler.post(runnable);
    }
}