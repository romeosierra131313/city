/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.City.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

/**
 *
 * @author Stefan
 */
public class UnitFactory {
    building b;
    TextureRegion[] red_f;
    TextureRegion[] blu_f;
    TextureRegion[] yel_f;
     TextureRegion[][] temp;
    
    public UnitFactory (){
    // 0 = placeholder
    // 1 = hq    
    // 2 = building
    // 4 = road
    // 5 = decoration
    // 6 = CORP
    
        red_f = new TextureRegion[3];
        blu_f = new TextureRegion[3];
        yel_f = new TextureRegion[3];
        temp = TextureRegion.split(new Texture(Gdx.files.internal("flower2.png")), 16, 16);
        red_f[0] = temp[0][0];
        red_f[1] = temp[0][1];
        red_f[2] = temp[0][2];
        blu_f[0] = temp[1][0];
        blu_f[1] = temp[1][1];
        blu_f[2] = temp[1][2];
        yel_f[0] = temp[2][0];
        yel_f[1] = temp[2][1];
        yel_f[2] = temp[2][2];
        
    
    }
    public building  House3(int x , int y,Sprite s){    //////woood shack
        s.setSize(3,3);
        b = new building(x,y,3,3,s,2,2,1);
        b.setFinished("house14");
        b.setCost(50000);
        b.setBaseRent(900);
        b.setMaturity(1);
        return b;
      
    }
    public building  House4(int x , int y ,Sprite s){     ////////orange extra
        s.setSize(4,4);
        b = new building(x,y,4,4,s,2,3,1);
        b.setFinished("house15");
        b.setCost(75000);
         b.setBaseRent(1600);
         b.setMaturity(2);
        return b;
      
    }
    public building  House5(int x , int y ,Sprite s){    /////////skylight side
        s.setSize(5, 5);
        b = new building(x,y,5,5,s,2,3,1);
        b.setFinished("house5");
        b.setCost(75000);
         b.setBaseRent(2500);
         b.setMaturity(6);
        return b;
      
    }
    public building  House6(int x , int y ,Sprite s){    //////////skylight top
        s.setSize(5, 5);
        b = new building(x,y,5,5,s,2,3,1);
        b.setFinished("house6");
        b.setCost(85000);
         b.setBaseRent(2600);
         b.setMaturity(5);
        return b;
      
    }
    public building  House7(int x , int y ,Sprite s){     ////////blue roof flat 
        s.setSize(5, 5);
        b = new building(x,y,5,5,s,2,3,1);
        b.setFinished("house7");
        b.setCost(85000);
         b.setBaseRent(2700);
         b.setMaturity(6);
        return b;
      
    }
    public building  House8(int x , int y ,Sprite s){     ///////gray roof flat
        s.setSize(8,8);
        b = new building(x,y,8,8,s,2,3,1);
        b.setFinished("house8");
        b.setCost(100000);
         b.setBaseRent(3200);
         b.setMaturity(7);
        return b;
      
    }
    public building  House9(int x , int y ,Sprite s){     ///////purple roof flat
        s.setSize(8,8);
        b = new building(x,y,8,8,s,2,3,1);
        b.setFinished("house9");
        b.setCost(100000);
         b.setBaseRent(3150);
         b.setMaturity(5);
        return b;
      
    }
    public building  House10(int x , int y ,Sprite s){     ////////orange roof 
        s.setSize(10,10);
        b = new building(x,y,10,10,s,2,3,1);
        b.setFinished("house10");
        b.setCost(150000);
         b.setBaseRent(4000);
         b.setMaturity(10);
        return b;
      
    }
    public building  House11(int x , int y ,Sprite s){     ////////green roof
        s.setSize(6,6);
        b = new building(x,y,6,6,s,2,3,1);
        b.setFinished("house11");
        b.setCost(125000);
         b.setBaseRent(3500);
         b.setMaturity(5);
        return b;
      
    }
    public building  House12(int x , int y ,Sprite s){     ////////green roof delux
        s.setSize(6,6);
        b = new building(x,y,6,6,s,2,3,1);
        b.setFinished("house12");
        b.setCost(155000);
         b.setBaseRent(4000);
         b.setMaturity(6);
        return b;
      
    }
    public building  House13(int x , int y ,Sprite s){    //////green roof mansion
        s.setSize(10,10);
        b = new building(x,y,10,10,s,2,3,1);
        b.setFinished("house13");
        b.setCost(250000);
         b.setBaseRent(6000);
         b.setMaturity(11);
        return b;
      
    }
    public building  House14(int x , int y ,Sprite s){  /// purple roof 2 storey
        s.setSize(7,6);
        b = new building(x,y,7,7,s,2,3,1);
        b.setFinished("house3");
        b.setCost(180000);
         b.setBaseRent(3775);
         b.setMaturity(6);
        return b;
      
    }
    public building  House15(int x , int y ,Sprite s){     ////////blue roof with awning
        s.setSize(8,8);
        b = new building(x,y,8,8,s,2,3,1);
        b.setFinished("house4");
        b.setCost(185000);
         b.setBaseRent(4125);
         b.setMaturity(8);
        return b;
      
    }
    public building  House16(int x , int y ,Sprite s){     /////////blue roof delux
        s.setSize(8,6);
        b = new building(x,y,8,8,s,2,3,1);
        b.setFinished("house16");
        b.setCost(125000);
         b.setBaseRent(3200);
         b.setMaturity(8);
        return b;
      
    }
    public building  road(int x , int y,Sprite s ){
        s.setSize(1, 1);
        b = new building(x,y,1,1,s,4,2,1);
        b.setCost(10);
         b.setBaseRent(0);
        return b;
      
    }
        public building  tree1(int x , int y,Sprite s,int effect ){
        s.setSize(2, 2);
        b = new building(x,y,1,1,s,5,4,effect);
        b.setCost(100);
         b.setBaseRent(0);
        return b;
      
    }
        public building  flower1(int x , int y,Sprite s,int effect ){
        s.setSize(1, 1);
        b = new building(x,y,1,1,s,5,2,effect);
        b.setCost(100);
         b.setBaseRent(0);
        return b;
      
    }
        public building  flower2(int x , int y,int effect ){
        
        Animation a ;
        a = new Animation(0.25f, red_f);
        
         b = new building(x,y,1,1,a,5,2,effect);
         b.a = a;
         
        Sprite st = new Sprite() ;
        st.setSize(1, 1);
        b.SetS(st);
        b.setCost(100);
        b.setBaseRent(0);
        return b;
      
    }
        public building  flower3(int x , int y,int effect ){
        
        Animation a ;
        a = new Animation(0.25f, blu_f);
        
         b = new building(x,y,1,1,a,5,2,effect);
         b.a = a;
         
        Sprite st = new Sprite() ;
        st.setSize(1, 1);
        b.SetS(st);
        b.setCost(100);
        b.setBaseRent(0);
        return b;
      
    }
        public building  flower4(int x , int y,int effect ){
        
        Animation a ;
        a = new Animation(0.25f, yel_f);
        
         b = new building(x,y,1,1,a,5,2,effect);
         b.a = a;
         
        Sprite st = new Sprite() ;
        st.setSize(1, 1);
        b.SetS(st);
        b.setCost(100);
        b.setBaseRent(0);
        return b;
      
    }
        public building  B1(int x , int y ,Sprite s){     /////////blue roof delux
        s.setSize(4,3);
        b = new building(x,y,4,3,s,6,3,1);
        b.setFinished("B1");
        b.setCost(125000);
         b.setBaseRent(3200);
         b.setMaturity(8);
        return b;
      
    }
        public building  B2(int x , int y ,Sprite s){     /////////blue roof delux
        s.setSize(8,6);
        b = new building(x,y,8,6,s,6,3,1);
        b.setFinished("B2");
        b.setCost(125000);
         b.setBaseRent(3200);
         b.setMaturity(8);
        return b;
      
    }
        public building  B3(int x , int y ,Sprite s){     /////////blue roof delux
        s.setSize(8,8);
        b = new building(x,y,8,8,s,6,3,1);
        b.setFinished("B3");
        b.setCost(125000);
         b.setBaseRent(3200);
         b.setMaturity(8);
        return b;
      
    }
        public building  B4(int x , int y ,Sprite s){     /////////blue roof delux
        s.setSize(4,4);
        b = new building(x,y,4,4,s,6,3,1);
        b.setFinished("B4");
        b.setCost(125000);
         b.setBaseRent(3200);
         b.setMaturity(8);
        return b;
      
    }
        public building  B5(int x , int y ,Sprite s){     /////////blue roof delux
        s.setSize(8,6);
        b = new building(x,y,8,6,s,6,3,1);
        b.setFinished("B5");
        b.setCost(125000);
         b.setBaseRent(3200);
         b.setMaturity(8);
        return b;
      
    }
        public building  B6(int x , int y ,Sprite s){     /////////blue roof delux
        s.setSize(8,6);
        b = new building(x,y,8,6,s,6,3,1);
        b.setFinished("B6");
        b.setCost(125000);
         b.setBaseRent(3200);
         b.setMaturity(8);
        return b;
      
    }
        public building  B7(int x , int y ,Sprite s){     /////////blue roof delux
        s.setSize(8,8);
        b = new building(x,y,8,8,s,6,3,1);
        b.setFinished("B7");
        b.setCost(125000);
         b.setBaseRent(3200);
         b.setMaturity(8);
        return b;
      
    }
        public building  B8(int x , int y ,Sprite s){     /////////blue roof delux
        s.setSize(4,4);
        b = new building(x,y,4,4,s,6,3,1);
        b.setFinished("B8");
        b.setCost(125000);
         b.setBaseRent(3200);
         b.setMaturity(8);
        return b;
      
    }
        public building  B9(int x , int y ,Sprite s){     /////////blue roof delux
        s.setSize(8,8);
        b = new building(x,y,8,8,s,6,3,1);
        b.setFinished("B9");
        b.setCost(125000);
         b.setBaseRent(3200);
         b.setMaturity(8);
        return b;
      
    }
}
