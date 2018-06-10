package gym.managym;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class TraineeMainActivity extends AppCompatActivity {
    private Bundle bundle;
    private TraineeData userData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trainee_main);

        bundle = getIntent().getExtras();
        userData = bundle.getParcelable("userData");

        final TextView idText = findViewById(R.id.idText);
        final TextView pwText = findViewById(R.id.pwText);
        final TextView nameText = findViewById(R.id.nameText);
        final TextView birthText = findViewById(R.id.birthText);
        final TextView phoneText = findViewById(R.id.phoneText);
        final TextView weightText = findViewById(R.id.weightText);
        final TextView heightText = findViewById(R.id.heightText);
        final TextView pointText = findViewById(R.id.pointText);
        final TextView trainerText = findViewById(R.id.trainerText);
        final TextView adminText = findViewById(R.id.adminText);

        idText.setText("ID : " + userData.getUserID());
        pwText.setText("PW : " + userData.getUserPW());
        nameText.setText("Name : " + userData.getName());
        birthText.setText("Birth : " + userData.getBirth());
        phoneText.setText("Phone : " + userData.getPhone());
        weightText.setText("Weight : " + userData.getWeight());
        heightText.setText("Height : " + userData.getHeight());
        pointText.setText("Point : " + userData.getPoint());
        trainerText.setText("Trainer : " + userData.getTrainer());
        adminText.setText("Admin : " + userData.getAdmin());

        final Button noticeButton = findViewById(R.id.noticeButton);
        final Button attendButton = findViewById(R.id.attendButton);
        final Button bodyDataButton = findViewById(R.id.bodyDataButton);

        noticeButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(TraineeMainActivity.this, NoticeActivity.class);
                intent.putExtra("userData", userData);
                startActivity(intent);
            }
        });

        bodyDataButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(TraineeMainActivity.this, BodyDataActivity.class);
                intent.putExtra("userData", userData);
                startActivity(intent);
            }
        });

        attendButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                //Intent intent = new Intent(TraineeMainActivity.this, attendActivity.class);
                //startActivity(intent);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_menu_default, menu); // getMenuInflater 를 사용해서 xml 파일 가져옴
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch(id){
            case R.id.menu_back:
                logout();
                break;
            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        logout();
    }

    private void logout() {
        AlertDialog.Builder builder = new AlertDialog.Builder(TraineeMainActivity.this);
        builder.setMessage("로그아웃 하시겠습니까?");
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
                Intent intent = new Intent(TraineeMainActivity.this, LoginActivity.class);
                startActivity(intent);
                finish();
            }
        });
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        builder.create();
        builder.show();
    }
}

