package id.luvie.luvieapps;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.google.android.material.tabs.TabLayout;
import id.luvie.luvieapps.R;

public class DokterFragment extends Fragment {

    public DokterFragment() {
        // Required empty public constructor
    }

    TabLayout tabLayoutDokter;
    FrameLayout frameDokter;

    DokterSpesialisFragment dokterSpesialisFragment;
    DokterUmumFragment dokterUmumFragment;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_dokter, container, false);

        tabLayoutDokter = rootView.findViewById(R.id.tablayoutDokter);
        frameDokter = rootView.findViewById(R.id.frame_dokter);

        bindWidgetsWithAnEvent();
        setupTabLayout();

        return rootView;
    }

    private void setupTabLayout() {

        dokterSpesialisFragment = new DokterSpesialisFragment();
        dokterUmumFragment = new DokterUmumFragment();

        tabLayoutDokter.addTab(tabLayoutDokter.newTab().setText("Dokter Spesialis"));
        tabLayoutDokter.addTab(tabLayoutDokter.newTab().setText("Dokter Umum"));

    }

    private void bindWidgetsWithAnEvent() {

        tabLayoutDokter.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                setCurrentTabFragment(tab.getPosition());
            }
            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
            }
            @Override
            public void onTabReselected(TabLayout.Tab tab) {
            }
        });

    }

    private void setCurrentTabFragment(int position) {

        switch (position)
        {
            case 0 :
                replaceFragment(dokterSpesialisFragment);
                break;
            case 1 :
                replaceFragment(dokterUmumFragment);
                break;
        }

    }

    public void replaceFragment(Fragment fragment) {
        FragmentManager fm = getChildFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.replace(R.id.frame_dokter, fragment);
        ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        ft.commit();
    }

}