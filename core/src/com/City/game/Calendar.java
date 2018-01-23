/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.City.game;

import com.badlogic.gdx.math.Vector2;
import java.util.ArrayList;
/**
 *
 * @author Stefan
 */
public class Calendar  {
    int seconds = 0;
    int days = 0;
    int month = 0;
    int year = 0;
    City C;
    public Calendar(City C){
    seconds = 0;
    days = 0;
    month = 0;
    year = 0;
    this.C = C;
    }
    public void SecondsInc(ArrayList<building> buildings){
      if(seconds < 60){
       seconds ++;
       }
       if(seconds ==2){
        seconds =0;
        DayInc(buildings);
        }
    }

    private void DayInc(ArrayList<building> buildings) {
      if(days < 30){
          for(int i = 1 ; i < buildings.size();i++){
        if(buildings.get(i).getage() > buildings.get(i).getMaturity() ){
            if(buildings.get(i).getType() == 6){
              if(buildings.get(i).getisConnected()){  
         buildings.get(i).setrent(C.effects.get(new Vector2(buildings.get(i).x,buildings.get(i).y)));
         C.IncG(buildings.get(i).GetRent());
         buildings.get(i).setisRentRendered(Boolean.TRUE);
              }
           }
        }
         C.setRenderingPay(Boolean.TRUE);
       }
          
       days ++;
       }
       if(days ==3){
        days =0;
        monthInc(buildings);
        }
    }

    private void monthInc(ArrayList<building> buildings) {
     if(month < 12){
       month ++;
       for(int i = 1 ; i < buildings.size();i++){
        if(buildings.get(i).getage() > buildings.get(i).getMaturity() ){
            if(buildings.get(i).getType() == 2){
                if(buildings.get(i).getisConnected()){
         buildings.get(i).setrent(C.effects.get(new Vector2(buildings.get(i).x,buildings.get(i).y)));
         C.IncG(buildings.get(i).GetRent());
         buildings.get(i).setisRentRendered(Boolean.TRUE);
                }
            }
        }
         C.AgeBuildings(buildings.get(i));
         C.setRenderingPay(Boolean.TRUE);
       }
       }
       if(month ==12){
        month =0;
        YearInc();
        } }

    private void YearInc() {
       year ++;
    }
    
}
