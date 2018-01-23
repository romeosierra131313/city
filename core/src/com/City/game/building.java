/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.City.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

/**
 *
 * @author Stefan
 */
public class building {
    int x;
    int y;
    private final int type;
    private int cost;
    private int age = 0;
    private int maturity;
    private final int width;
    private final int height;
    transient Sprite s;
    transient Sprite s2;
    transient Animation a;
    String Sprite1Name;
    String Sprite2Name;
    String finished ;
    private int baserent;
    private int rent ;
    private final int aoe;
    private  int effect;
    private Boolean isSelected = false;
    private Boolean isRentRendered = false;
    private Boolean isConnected = false;
    
   public building(int x, int y,int width,int height, Sprite s,int type,int aoe,int effect){
     this.x = x;
     this.y = y;
     this.width = width;
     this.height =height;
    if(s != null){
     this.s = s;
     s.setPosition(x, y);}
     this.type = type;
     this.aoe = aoe;
     this.effect = effect;
     rent = baserent;
     
   }
   public building(int x, int y,int width,int height, Animation a,int type,int aoe,int effect){
     this.x = x;
     this.y = y;
     this.width = width;
     this.height =height;
    if(s != null){
     this.a = a;
     s.setPosition(x, y);}
     this.type = type;
     this.aoe = aoe;
     this.effect = effect;
     rent = baserent;
     
   }

   
      public void SetX(int x){
    this.x = x;
   }
      public void SetY(int y){
    this.y = y;
   }
      public void SetS(Sprite s){
    this.s = s;
   }
      public void SetS2(Sprite s){
    this.s2 = s;
   }
      public int GetRent(){
        return rent;
      }
      public void setrent(int modifier){
          
                        
          rent = baserent  + modifier;
          System.out.println("setrent" + rent);
      }
      public void IncAge(){
       age++;
      }
      public void FinishBuilding(Sprite s){
        if(age > maturity){
         this.s = s;
         
        }
      }
      public int getType(){
        return type;
      }
      public int getHeight(){
        return height;
      }
      public int getWidth(){
        return width;
      }
      public int getCost(){
        return cost;
      }
      public void setCost(int i){
          cost = i;
      }
      public int getaoe(){
        return aoe;
      }
      public int getage(){
        return age;
      }
      public void setage(int i){
        age = i;
      }
      public int geteffect(){
        return effect;
      }
      public void setEffect(int effect ){
      
       this.effect = effect;
      }
      public void setFinished(String f){
        finished = f;
      }
      public String getFinished(){
        return finished;
      }
      public Boolean isSelected(){ return isSelected; }
      public void setisSelected(Boolean b){ isSelected = b; }
      public int getMaturity(){
        return maturity;
      }
      public void setMaturity(int m_age){
         maturity = m_age;
      }
      public void setBaseRent(int i){
          baserent = i;
      }
      public Boolean getIsSlected(){
       return isSelected;
      }
      public Boolean getisRentRendered(){
       return isRentRendered;
      }
      public void setisRentRendered(Boolean b){
        isRentRendered = b;
      }
      public Boolean getisConnected(){
       return isConnected;
      }
      public void setisisConnected(Boolean b){
       isConnected = b;
      }
}
