/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.City.game;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Rectangle;

/**
 *
 * @author Stefan
 */
public class UnitFactory {
    building b;
    public UnitFactory (){
    // 0 = placeholder
    // 1 = hq    
    // 2 = small house
    // 2 = medium house 
    // 4 = road
    // 5 = decoration
    }
    public building  smallHouse(int x , int y,Sprite s){
        s.setSize(5,5);
        b = new building(x,y,5,5,s,2,2,1);
        b.setFinished("house3");
        b.setCost(100000);
        b.setBaseRent(1500);
        b.setMaturity(2);
        return b;
      
    }
    public building  mediumHouse(int x , int y ,Sprite s){
        s.setSize(8, 8);
        b = new building(x,y,8,8,s,2,3,1);
        b.setFinished("house4");
        b.setCost(200000);
         b.setBaseRent(3200);
         b.setMaturity(4);
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
}
