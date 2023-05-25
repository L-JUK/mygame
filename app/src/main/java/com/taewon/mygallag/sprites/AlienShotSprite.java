package com.taewon.mygallag.sprites;

import android.content.Context;

import com.taewon.mygallag.R;
import com.taewon.mygallag.SpaceInvadersView;

public class AlienShotSprite extends Sprite{

    private Context context;
    private SpaceInvadersView game;   //AilenShotSprite 객체를 생성할 때 호출

    public AlienShotSprite(Context context, SpaceInvadersView game, float x, float y, int dy){    //총알의 생성위치 좌표 , 총알 속도
        super(context, R.drawable.shot_001, x, y);     //Sprite 객체로 총알 생성
        this.game = game;           //SpaceInvadersView
        this.context = context;     //context
        setDy(dy);                  //속도값 설정

    }


}
