package com.meng.TaiHunDanmaku.baseObjects.planes.subPlane;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.meng.TaiHunDanmaku.helpers.ObjectPools;
import com.meng.TaiHunDanmaku.helpers.ResourcesManager;
import com.meng.TaiHunDanmaku.helpers.TextureNameManager;
import com.meng.TaiHunDanmaku.baseObjects.planes.myPlane.BaseMyPlane;

public class SubPlaneReimuA extends BaseSubPlane{
    private int degree=0;
    private Vector2 tmpv=new Vector2();
    private int[] pos=new int[]{
		0,20,
		0,32,
		-16,-20,16,-20,
		-32,0,32,0,
		-16,-20,0,-30,16,-20,
		-32,0,0,-32,32,0,
		-16,-20,-8,-30,8,-30,16,-20,
		-32,0,-16,-32,16,-32,32,0
	  };

    @Override
    public Drawable getDrawable(){
        return ResourcesManager.textures.get(TextureNameManager.ReimuSubPlane);
	  }

    @Override
    public void init(BaseMyPlane myPlane,int subPlaneNumber){
        super.init(myPlane,subPlaneNumber);
        switch(bianHao){
            case 1:
			  tmpv.set(0,8).rotate(10);
			  break;
            case 2:
			  tmpv.set(0,8);
			  break;
            case 3:
			  tmpv.set(0,8);
			  break;
            case 4:
			  tmpv.set(0,8).rotate(-10);
			  break;
		  }
	  }


    @Override
    public float getRotationDegree(){
        degree+=5;
        return degree;
	  }

    @Override
    public int[] getSubPlanePosition(){
        return pos;
	  }

    @Override
    public Vector2 getSize(){
        return new Vector2(16,16);
	  }

    @Override
    public void shoot(){
        if(existTime%7==1){
			ObjectPools.reimuSubPlaneBulletInducePool.obtain().init(new Vector2(objectCenter.x,objectCenter.y),tmpv);
		  }
	  }
  }
