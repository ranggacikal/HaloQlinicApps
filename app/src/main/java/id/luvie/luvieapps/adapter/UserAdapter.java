package id.luvie.luvieapps.adapter;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import id.luvie.luvieapps.MainActivity;
import id.luvie.luvieapps.R;
import id.luvie.luvieapps.SharedPreference.SharedPreferencedConfig;
import id.luvie.luvieapps.model.userMesibo.UsersItem;
import com.mesibo.api.Mesibo;
import com.mesibo.api.MesiboProfile;
import com.mesibo.calls.api.MesiboCall;
import com.mesibo.messaging.MesiboUI;

import java.util.List;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.UserViewHolder> implements Mesibo.ConnectionListener, Mesibo.MessageListener {

    List<UsersItem> usersItems;
    Context context;

    MainActivity mainActivity;

    private SharedPreferencedConfig preferencedConfig;


    MesiboProfile mProfile;
    Mesibo.ReadDbSession mReadSession;

    class TestUser {
        public String token;
        public String address;

        TestUser(String token, String address) {
            this.token = token;
            this.address = address;
        }
    }

    TestUser mUser;

    public UserAdapter(List<UsersItem> usersItems, Context context) {
        this.usersItems = usersItems;
        this.context = context;
    }

    @NonNull
    @Override
    public UserViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_dokter_online, parent, false);
        return new UserViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull UserViewHolder holder, @SuppressLint("RecyclerView") int position) {

        preferencedConfig = new SharedPreferencedConfig(context);

        holder.txtNamaDokter.setText(usersItems.get(position).getAddress());
//        holder.txtSpesialisDokter.setText(currentUser.getSpesialis());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String token2 = usersItems.get(position).getToken();
                String address2 = usersItems.get(position).getAddress();

//                String token1 = preferencedConfig.getPreferenceTokenUser();
//                String address1 = preferencedConfig.getPreferenceAddressUser();

//                TestUser user1 = new TestUser(token1, address1);
                TestUser user2 = new TestUser(token2, address2);

                Dialog alertDialog = new Dialog(context);
                alertDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                alertDialog.setContentView(R.layout.dialog_chat_vc);
                alertDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

                Window window = alertDialog.getWindow();
                WindowManager.LayoutParams wlp = window.getAttributes();

                wlp.gravity = Gravity.BOTTOM;
                wlp.width = WindowManager.LayoutParams.MATCH_PARENT;
                window.setAttributes(wlp);

                RelativeLayout relativeChat = alertDialog.findViewById(R.id.relative_dialog_chat);
                RelativeLayout relativeVc = alertDialog.findViewById(R.id.relative_dialog_vc);
                alertDialog.show();

                relativeChat.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
//                        mesiboInit(user1, user2);
                        MesiboUI.launchMessageView(context, mProfile.address, 0);
                        alertDialog.dismiss();
                    }
                });

                relativeVc.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
//                        mesiboInit(user1, user2);
                        MesiboCall.getInstance().callUi(context, mProfile.address, true);
                        alertDialog.dismiss();
                    }
                });

            }
        });


    }

    @Override
    public int getItemCount() {
        return usersItems.size();
    }

    public class UserViewHolder extends RecyclerView.ViewHolder {

        TextView txtNamaDokter, txtSpesialisDokter;

        public UserViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }

    @Override
    public void Mesibo_onConnectionStatus(int i) {
        Log.d("checkConnectionMesibo", "Mesibo_onConnectionStatus: "+i);
    }

    @Override
    public boolean Mesibo_onMessage(Mesibo.MessageParams messageParams, byte[] bytes) {
        return false;
    }

    @Override
    public void Mesibo_onMessageStatus(Mesibo.MessageParams messageParams) {

    }

    @Override
    public void Mesibo_onActivity(Mesibo.MessageParams messageParams, int i) {

    }

    @Override
    public void Mesibo_onLocation(Mesibo.MessageParams messageParams, Mesibo.Location location) {

    }

    @Override
    public void Mesibo_onFile(Mesibo.MessageParams messageParams, Mesibo.FileInfo fileInfo) {

    }

    private void mesiboInit(TestUser user, TestUser remoteUser) {
        Mesibo api = Mesibo.getInstance();
        api.init(context);

        Mesibo.addListener(this);
        Mesibo.setSecureConnection(true);
        Mesibo.setAccessToken(user.token);
        Mesibo.setDatabase("mydb", 0);
        Mesibo.start();

        mUser = remoteUser;
        mProfile = Mesibo.getProfile(remoteUser.address);


        MesiboCall mesiboCall = MesiboCall.getInstance();
        mesiboCall.init(context);

        // Read receipts are enabled only when App is set to be in foreground
        Mesibo.setAppInForeground(context, 0, true);
        mReadSession = new Mesibo.ReadDbSession(mUser.address, this);
        mReadSession.enableReadReceipt(true);
        mReadSession.read(100);

    }
}
