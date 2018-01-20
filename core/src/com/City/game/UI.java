/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.City.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.List;
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import java.util.ArrayList;
import java.util.HashMap;

/**
 *
 * @author Stefan
 */
public class UI {
      transient building b;
      transient UnitFactory uf;
      final City City;
      transient Skin skin;
      transient Stage stage;
      transient Table table;
      transient ScrollPane sp;
      transient Label GOLD;
      transient Label Date;
      transient List buildtypes;
      transient List decotypes;
      transient final TextButton build;
      transient final TextButton deco;
      
    public UI (final City C){
       
       uf = new UnitFactory();
       this.City = C;
       stage = new Stage(new ScreenViewport());
       skin = new Skin(Gdx.files.internal("uiskin.json"));
       int Gthis = C.G;
                GOLD =new Label(Integer.toString(C.G),skin);
                Date = new Label("Date:" + Integer.toString(C.calendar.days)+ "/" + Integer.toString(C.calendar.month) +
                        "/" + Integer.toString(C.calendar.year),skin);
                Date.setX(Gdx.graphics.getWidth()-100f);
        build = new TextButton("build", skin, "default");
        build.setWidth(100f);
        build.setHeight(20f);
        build.setPosition(Gdx.graphics.getWidth()/20, Gdx.graphics.getHeight()/20 *18);
        build.addListener(new ClickListener(){
            @Override 
            public void clicked(InputEvent event, float x, float y){
                buildtypes.setSelectedIndex(0);
                buildScrollpane(buildtypes,C);
                stage.addActor(table);
                stage.addActor(Date);
                stage.addActor(GOLD);
                Gdx.input.setInputProcessor(stage);
            }
        });
        deco = new TextButton("deco", skin, "default");
        deco.setWidth(100f);
        deco.setHeight(20f);
        deco.setPosition(Gdx.graphics.getWidth()/20 + 100f, Gdx.graphics.getHeight()/20 *18);
        deco.addListener(new ClickListener(){
            @Override 
            public void clicked(InputEvent event, float x, float y){
                decotypes.setSelectedIndex(0);
                buildScrollpane(decotypes,C);
                stage.addActor(table);
                stage.addActor(Date);
                stage.addActor(GOLD);
                Gdx.input.setInputProcessor(stage);
            }
        });
       
       table = new Table(skin);
    //   table.setBounds(0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
       table.setFillParent(true);
       table.setDebug(true);
       buildtypes = new List(skin);
       buildtypes.setItems(new String[]{"","cancel","small","medium","road"});
       buildtypes.setSelectedIndex(0);
       decotypes = new List(skin);
       decotypes.setItems(new String[]{"","cancel","tree","tree2","tree3","tree4"});
       decotypes.setSelectedIndex(0);
 
       

       table.add(sp).width(200).height(300);
       
        
        basicMenu();


    
    }
public void OpenBuildigMenu(OrthographicCamera cam,Vector3 v,final HashMap map,final ArrayList buildings,final City C){
    v = cam.project(v);
    System.out.println("menu");
        TextButton Destroy = new TextButton("Destroy", skin, "default");
        Destroy.setWidth(100f);
        Destroy.setHeight(20f);
        Destroy.setPosition(v.x+50f,v.y);
        Destroy.addListener(new ClickListener(){
            @Override 
            public void clicked(InputEvent event, float x, float y){
                b = C.getSelectedBuilding();
                C.addeffects(b,-b.geteffect());
                map.remove(new Vector2(b.x,b.y));
                City.IncG(C.getSelectedBuilding().getCost());
                buildings.remove(C.getSelectedBuilding());
                basicMenu();
                
            }
        });
         TextButton Cancel = new TextButton("Cancel", skin, "default");
        Cancel.setWidth(100f);
        Cancel.setHeight(20f);
        Cancel.setPosition(v.x+50f,v.y -30f);
        Cancel.addListener(new ClickListener(){
            @Override 
            public void clicked(InputEvent event, float x, float y){
                 C.getSelectedBuilding().s.setColor(Color.WHITE);
                 if(C.getSelectedBuilding().s2 != null){
                 C.getSelectedBuilding().s2.setColor(Color.WHITE);
                  }
                 C.getSelectedBuilding().setisSelected(Boolean.FALSE);
               
                basicMenu();
                
            }
        });
        building b = C.getSelectedBuilding();
       if(b.getage() < b.getMaturity()){
        TextButton Complete = new TextButton("Complete", skin, "default");
        Complete.setWidth(100f);
        Complete.setHeight(20f);
        Complete.setPosition(v.x+50f,v.y+60f);
        Complete.addListener(new ClickListener(){
            @Override 
            public void clicked(InputEvent event, float x, float y){
                C.getSelectedBuilding().setage(C.getSelectedBuilding().getMaturity()+1);
                C.IncG(- C.getSelectedBuilding().getCost() );
                C.getSelectedBuilding().s.setColor(Color.WHITE);
                 if(C.getSelectedBuilding().s2 != null){
                 C.getSelectedBuilding().s2.setColor(Color.WHITE);
                  }
                 C.getSelectedBuilding().setisSelected(Boolean.FALSE);
                basicMenu();
                
            }
        });
       
        stage.addActor(Complete);
       }
      stage.addActor(Cancel);
      stage.addActor(Destroy);
      
  }

    private void basicMenu() {
    stage.clear();
    stage.addActor(GOLD);
    stage.addActor(Date);
    stage.addActor(build);
    stage.addActor(deco);
    Gdx.input.setInputProcessor(City.multiplexer);
    }
    private void buildScrollpane(List l,final City C){
       table.clear();
       sp = new ScrollPane(l,skin);
       
       sp.setPosition(Gdx.graphics.getWidth()/20 + build.getWidth(), Gdx.graphics.getHeight()/20 *18);
       if(l == buildtypes){    
       sp.addListener(new ChangeListener() {
           @Override
           public void changed(ChangeListener.ChangeEvent ce, Actor actor) {
                if(buildtypes.getSelected() == buildtypes.getItems().get(1)){
                   stage.clear();
                   stage.addActor(build);
                
                }                                                //////CANCEL/////
               if(buildtypes.getSelected() == buildtypes.getItems().get(2)){                                                  //////SMALLHOUSE///
                 if(C.GetG()>100000){
                 b =  uf.smallHouse(Gdx.input.getX(), Gdx.input.getY(), new Sprite(C.atlas.findRegion("clear")));
                 C.buildings.add(0, b);
                 C.setisAttached(Boolean.TRUE);
                 C.setisBuildingBuilding(Boolean.TRUE);
               }else{
               System.out.println("you too poor");
               }
                
               }
                if(buildtypes.getSelected() == buildtypes.getItems().get(3)){                                                 ////////MEDIUM HOUSE/////////
                 if(C.G>200000){
                 b = uf.mediumHouse(Gdx.input.getX(), Gdx.input.getY(), new Sprite(C.atlas.findRegion("clear")));
                 C.buildings.add(0, b);
                 C.setisAttached(Boolean.TRUE);
                 C.setisBuildingBuilding(Boolean.TRUE);
                 }else{
                 System.out.println("you too poor");}
                }
                if(buildtypes.getSelected() == buildtypes.getItems().get(4)){                                                ////////////ROAD//////////
                 if(C.G>10){
                 
                 C.setisRoadSelected(Boolean.TRUE);
                 
                 }else{
                 System.out.println("you too poor");}
                }
               
               stage.clear();
               stage.addActor(build);
               stage.addActor(deco);
               stage.addActor(GOLD);
               stage.addActor(Date);
               Gdx.input.setInputProcessor(C.multiplexer);
           }
          
       });}
       
       if(l == decotypes){
        sp.addListener(new ChangeListener() {
           @Override
           public void changed(ChangeListener.ChangeEvent ce, Actor actor) {
                if(decotypes.getSelected() == decotypes.getItems().get(1)){
                   stage.clear();
                   stage.addActor(deco);
                
                }                                                //////CANCEL/////
               if(decotypes.getSelected() == decotypes.getItems().get(2)){                                                  //////SMALLHOUSE///
                 if(C.GetG()>100){
                 b =  uf.tree1(Gdx.input.getX(), Gdx.input.getY(), new Sprite(C.atlas.findRegion("t1_1")),1);
                 b.SetS2( new Sprite(C.atlas.findRegion("t1_2")));
                 b.s2.setSize(2,2);
                 C.buildings.add(0, b);
                 C.setisAttached(Boolean.TRUE);
                 C.setisBuildingBuilding(Boolean.TRUE);
               }else{
               System.out.println("you too poor");
               }
                
               }
               if(decotypes.getSelected() == decotypes.getItems().get(3)){                                                  //////SMALLHOUSE///
                 if(C.GetG()>100){
                 b =  uf.tree1(Gdx.input.getX(), Gdx.input.getY(), new Sprite(C.atlas.findRegion("t2_1")),2);
                 b.SetS2( new Sprite(C.atlas.findRegion("t2_2")));
                 b.s2.setSize(2,2);
                 C.buildings.add(0, b);
                 C.setisAttached(Boolean.TRUE);
                 C.setisBuildingBuilding(Boolean.TRUE);
               }else{
               System.out.println("you too poor");
               }
                
               }
               if(decotypes.getSelected() == decotypes.getItems().get(4)){                                                  //////SMALLHOUSE///
                 if(C.GetG()>100){
                 b =  uf.tree1(Gdx.input.getX(), Gdx.input.getY(), new Sprite(C.atlas.findRegion("t3_1")),3);
                 b.SetS2( new Sprite(C.atlas.findRegion("t3_2")));
                 b.s2.setSize(2,2);
                 C.buildings.add(0, b);
                 C.setisAttached(Boolean.TRUE);
                 C.setisBuildingBuilding(Boolean.TRUE);
               }else{
               System.out.println("you too poor");
               }
                
               }
               if(decotypes.getSelected() == decotypes.getItems().get(5)){                                                  //////SMALLHOUSE///
                 if(C.GetG()>100){
                 b =  uf.tree1(Gdx.input.getX(), Gdx.input.getY(), new Sprite(C.atlas.findRegion("t4_1")),4);
                 b.SetS2( new Sprite(C.atlas.findRegion("t4_2")));
                 b.s2.setSize(2,2);
                 C.buildings.add(0, b);
                 C.setisAttached(Boolean.TRUE);
                 C.setisBuildingBuilding(Boolean.TRUE);
               }else{
               System.out.println("you too poor");
               }
                
               }
               
                
               
               stage.clear();
               stage.addActor(build);
               stage.addActor(deco);
               stage.addActor(GOLD);
               stage.addActor(Date);
               Gdx.input.setInputProcessor(C.multiplexer);
           }
          
       });
       }
       
       table.add(sp).width(200).height(300);
    
    } 
}
