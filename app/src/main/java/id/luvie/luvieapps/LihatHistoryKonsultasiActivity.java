package id.luvie.luvieapps;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.os.Bundle;

import com.mesibo.api.MesiboProfile;
import com.mesibo.messaging.MesiboMessagingFragment;
import com.mesibo.messaging.MesiboUI;

public class LihatHistoryKonsultasiActivity extends AppCompatActivity implements MesiboMessagingFragment.FragmentListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lihat_history_konsultasi);

        MesiboMessagingFragment mFragment = new MesiboMessagingFragment();
        Bundle bl = new Bundle();
        bl.putString(MesiboUI.PEER, "Putri Fidya");
        bl.putLong(MesiboUI.GROUP_ID, 0);
        mFragment.setArguments(bl);

        getSupportFragmentManager().beginTransaction()
                .add(R.id.frame_container_history, mFragment).commit();

    }

    @Override
    public void Mesibo_onUpdateUserPicture(MesiboProfile profile, Bitmap thumbnail, String picturePath) {

    }

    @Override
    public void Mesibo_onUpdateUserOnlineStatus(MesiboProfile profile, String status) {

    }

    @Override
    public void Mesibo_onShowInContextUserInterface() {

    }

    @Override
    public void Mesibo_onHideInContextUserInterface() {

    }

    @Override
    public void Mesibo_onContextUserInterfaceCount(int count) {

    }

    @Override
    public void Mesibo_onError(int type, String title, String message) {

    }
}