package com.taewon.mygallag;

import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaParser;
import android.media.MediaPlayer;
import android.media.SoundPool;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class StartActivity extends AppCompatActivity {

    int characterId, effectId;
    ImageButton startBtn;
    TextView guideTv;
    MediaPlayer mediaPlayer;
    ImageView imgView[] = new ImageView[8];
    Integer img_id[] = {R.id.ship_001, R.id.ship_002, R.id.ship_003, R.id.ship_004,
            R.id.ship_005, R.id.ship_006, R.id.ship_007, R.id.ship_008};

    Integer img[] = {R.drawable.ship_0000, R.drawable.ship_0001, R.drawable.ship_0002, R.drawable.ship_0003,
            R.drawable.ship_0004, R.drawable.ship_0005, R.drawable.ship_0006, R.drawable.ship_0007};
    SoundPool soundPool;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);    //레이아웃 설정

        mediaPlayer = MediaPlayer.create(this, R.raw.robby_bgm);    //음원 만들고 재생
        mediaPlayer.setLooping(true);
        mediaPlayer.start();
        soundPool = new SoundPool(5, AudioManager.USE_DEFAULT_STREAM_TYPE, 0);  //사운드 풀 초기화
        effectId = soundPool.load(this, R.raw.reload_sound, 1);   //음원로딩
        startBtn = findViewById(R.id.startBtn);
        guideTv = findViewById(R.id.guideTv);

        for (int i = 0; i < imgView.length; i++) {     //반복문 캐릭터마다 클릭 이벤트 정의
            imgView[i] = findViewById(img_id[i]);
            int index = i;    //선택한 이미지번호 알기
            imgView[i].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    characterId = img[index];
                    startBtn.setVisibility(View.VISIBLE);
                    startBtn.setEnabled(true);
                    startBtn.setImageResource(characterId);
                    guideTv.setVisibility(View.INVISIBLE);      //마지막 텍스트뷰 숨기기
                    soundPool.play(effectId, 1, 1, 0 ,0 ,1.0f);  // 로드한 음원 재생
                }
            });
        }

        init();     //호출

        }
        private void init() {
            findViewById(R.id.startBtn).setVisibility(View.GONE);    //시작버튼 비활성화
            findViewById(R.id.startBtn).setEnabled(false);
            findViewById(R.id.startBtn).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(StartActivity.this, MainActivity.class);  //메인액티비티로 이동하는 인텐트  캐릭터 아이디 전달
                    intent.putExtra("character", characterId);
                    startActivity(intent);
                    finish();
                }
            });
        }
        protected void onDestroy() {    //  액티비티 종료될때 호출되는 메서드
        super.onDestroy();
        if (mediaPlayer != null) {    //미디어 플레이어 해제하고 리소스를 정리
            mediaPlayer.release();
            mediaPlayer = null;
        }
    }
}
