package com.taewon.mygallag.items;


import android.content.Context;

import com.taewon.mygallag.R;
import com.taewon.mygallag.SpaceInvadersView;
import com.taewon.mygallag.sprites.Sprite;

import java.util.Timer;
import java.util.TimerTask;

public class HealitemSprite extends Sprite {    // sprite class 를 상속받음

    SpaceInvadersView game;  //SpaceInvadersView 객체를 담는 변수

    public HealitemSprite(Context context, SpaceInvadersView game,   //다른 class에서 HealitemSprite 객체를 만들 때 호출됨
                        int x, int y, int dx, int dy){    //아이템 생성 위치 (x,y)  |  가로 세로로 움직일 거리(속도)
        super(context, R.drawable.heal_item, x, y);  //Sprite의 생성자를 호출해 매개변수 값으로 item 생성
        this.game = game;
        this.dx = dx;    //가로로 움직이는 거리
        this.dy = dy;    //세로로 움직이는 거리
        new Timer().schedule(new TimerTask() {   //Timer를 이용해 10 초뒤에 autoRemove()를 호출
            @Override
            public void run() {
                autoRemove();
            }
        },10000);  //1초 = 1000ms
    }

    private  void autoRemove() { game.removeSprite(this); }  // game=SpaceInvadersView
                                                             //.removeSprite를 호출해 현재 객체 삭제
    @Override
    public void move() {            //0.01초마다 조건에 맞게 움직임
        if((dx < 0) && (x<120)){    // 디스플레이의 범위에 닿으면 움직이는 방향을 반대로 바꿔줌
            dx *= -1; return;
        }
        if((dx > 0) && (x > game.screenW -120)){
            dx *= -1; return;
        }
        if((dy < 0) && (y<120)){
            dy *= -1; return;
        }
        if((dy > 0) && (y>game.screenH-120)){
            dy *= -1; return;
        }
        super.move();    //Sprite의 move를 통해 움직인다.   왜
    }
}


