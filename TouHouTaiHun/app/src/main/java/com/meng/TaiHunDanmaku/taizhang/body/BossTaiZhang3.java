package com.meng.TaiHunDanmaku.taizhang.body;

import com.badlogic.gdx.math.*;
import com.meng.TaiHunDanmaku.baseObjects.bullets.*;
import com.meng.TaiHunDanmaku.baseObjects.bullets.enemy.*;
import com.meng.TaiHunDanmaku.baseObjects.dropItems.*;
import com.meng.TaiHunDanmaku.taizhang.*;
import com.meng.TaiHunDanmaku.taizhang.danmaku.taizhang.*;
import com.meng.TaiHunDanmaku.task.*;
import com.meng.TaiHunDanmaku.ui.*;

public class BossTaiZhang3 extends BaseBossPlane{

    private final int[][] junkoAnim=new int[][]{
		  {10,14},
		  {5,9}
	  };
	private int wf=300;

    @Override
    public void update(){
        super.update();
        if(wf-->0) return;
        FightScreen.instence.spellMode();
        spellCard.update();
	  }

    @Override
    public void init(Vector2 center,int everyAnimFrameTime,int hp,Task[] bmm){
        super.init(center,everyAnimFrameTime,hp,bmm);
        targetPosition=center.cpy();
       spellCard=new spell3();
        spellCard.init(this);
        objectName="chunhu";
        this.everyAnimFrameTime=everyAnimFrameTime;
        animNum=junkoAnim;
		FightScreen.instence.layoutManager.nextPart.add(new partAgent(2600));
	  }

    @Override
    public void hit(float bulletDamage){
        if(wf>0) return;
        super.hit(bulletDamage);
	  }

    @Override
    public void kill(){
        BaseEnemyBullet.killAllBullet(BulletKillMode.KillOnBossLastDeath);
		DropItem.create(objectCenter.cpy(),DropItemType.power);
		new BossTaiZhang4().init(objectCenter.cpy(),10,4800,new Task[]{
									 new TaskMoveTo(193,250)
								   });
        super.kill();
	  }

    @Override
    public Vector2 getSize(){
        return new Vector2(96,128);
	  }
  }
