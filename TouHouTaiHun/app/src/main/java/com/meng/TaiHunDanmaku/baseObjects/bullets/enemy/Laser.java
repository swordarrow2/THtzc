package com.meng.TaiHunDanmaku.baseObjects.bullets.enemy;

import com.badlogic.gdx.graphics.*;
import com.badlogic.gdx.graphics.g2d.*;
import com.badlogic.gdx.math.*;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.meng.TaiHunDanmaku.baseObjects.planes.myPlane.*;
import com.meng.TaiHunDanmaku.ui.*;

public class Laser extends Actor{

    public Vector2 position = new Vector2();
    public float distance;
    public Color color = new Color(Color.RED);
    public Color rayColor = new Color(Color.WHITE);
    public float degrees;
    public Sprite begin1, begin2, mid1, mid2, end1, end2;
    public Vector2 p1=new Vector2();
	public Vector2 p2=new Vector2();

    public Laser(Sprite s1, Sprite s2, Sprite m1, Sprite m2, Sprite e1, Sprite e2){
        begin1=s1;
        begin2=s2;
        mid1=m1;
        mid2=m2;
        end1=e1;
        end2=e2;
	  }

    public void render(GameMain gameMain){
        begin1.setColor(color);
        begin2.setColor(rayColor);
        mid1.setColor(color);
        mid2.setColor(rayColor);
        end1.setColor(color);
        end2.setColor(rayColor);
        begin1.setOrigin(begin1.getWidth()/2,begin1.getHeight()/2);
        begin2.setOrigin(begin1.getWidth()/2,begin1.getHeight()/2);
        mid1.setOrigin(mid1.getWidth()/2,begin1.getHeight()/2-begin1.getHeight());
        mid2.setOrigin(mid2.getWidth()/2,begin1.getHeight()/2-begin1.getHeight());
        end1.setOrigin(mid1.getWidth()/2,begin1.getHeight()/2-begin1.getHeight()-mid1.getHeight());
        end2.setOrigin(mid2.getWidth()/2,begin1.getHeight()/2-begin1.getHeight()-mid2.getHeight());
        mid1.setSize(mid1.getWidth(),distance);
        mid2.setSize(mid1.getWidth(),distance);
        begin1.setPosition(position.x-begin1.getHeight()/2,position.y-begin1.getHeight()/2);
        begin2.setPosition(position.x-begin1.getHeight()/2,position.y-begin1.getHeight()/2);
        mid1.setPosition(begin1.getX(),begin1.getY()+begin1.getHeight());
        mid2.setPosition(begin1.getX(),begin1.getY()+begin1.getHeight());
        end1.setPosition(begin1.getX(),begin1.getY()+begin1.getHeight()+mid1.getHeight());
        end2.setPosition(begin1.getX(),begin1.getY()+begin1.getHeight()+mid1.getHeight());
        begin1.setRotation(degrees);
        begin2.setRotation(degrees);
        mid1.setRotation(degrees);
        mid2.setRotation(degrees);
        end1.setRotation(degrees);
        end2.setRotation(degrees);
        begin1.draw(gameMain.spriteBatch);
        begin2.draw(gameMain.spriteBatch);
        mid1.draw(gameMain.spriteBatch);
        mid2.draw(gameMain.spriteBatch);
		end1.draw(gameMain.spriteBatch);
		end2.draw(gameMain.spriteBatch);
		p1=new Vector2(begin1.getX()+begin1.getOriginX(),begin1.getY()+begin1.getOriginY());
		p2=new Vector2((float)(begin1.getX()+begin1.getOriginX()+distance*Math.cos(Math.toRadians(degrees+90))), 
					   (float)(begin1.getY()+begin1.getOriginY()+distance*Math.sin(Math.toRadians(degrees+90))));
		Vector2 v3=new Vector2(p2.x-p1.x,p2.y-p1.y).nor();
		v3.scl(distance+begin1.getHeight());
		v3.add(p1);
		p2.set(v3);
		if(pointToLine(p1.x,p1.y,p2.x,p2.y,BaseMyPlane.instance.objectCenter.x,
					   BaseMyPlane.instance.objectCenter.y)<5){
			++gameMain.miss;
		  }
	  }

    // 点到直线的最短距离的判断 点（x0,y0） 到由两点组成的线段（x1,y1） ,( x2,y2 )
    private double pointToLine(double x1,double y1,double x2,double y2,double x0,double y0){
        double space = 0;
        double a, b, c;
        a=lineSpace(x1,y1,x2,y2);// 线段的长度
        b=lineSpace(x1,y1,x0,y0);// (x1,y1)到点的距离
        c=lineSpace(x2,y2,x0,y0);// (x2,y2)到点的距离
        if(c<=0.000001||b<=0.000001){
            space=0;
            return space;
		  }
        if(a<=0.000001){
            space=b;
            return space;
		  }
        if(c*c>=a*a+b*b){
            space=b;
            return space;
		  }
        if(b*b>=a*a+c*c){
            space=c;
            return space;
		  }
        double p = (a+b+c)/2;// 半周长
        double s = Math.sqrt(p*(p-a)*(p-b)*(p-c));// 海伦公式求面积
        space=2*s/a;// 返回点到线的距离（利用三角形面积公式求高）
        return space;
	  }

    // 计算两点之间的距离
    private double lineSpace(double x1,double y1,double x2,double y2){
        double lineLength = 0;
        lineLength=Math.sqrt((x1-x2)*(x1-x2)+(y1-y2)*(y1-y2));
        return lineLength;
	  }
  }
