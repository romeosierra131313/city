package com.City.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import java.util.ArrayList;
import java.util.HashMap;

public class City extends ApplicationAdapter implements InputProcessor {
	SpriteBatch batch;
        OrthographicCamera cam;
        BitmapFont font;
        InputMultiplexer multiplexer;
                
        TextureAtlas atlas;
        Sprite bdg_s;
        Sprite ssss;
        ArrayList<building> buildings;
        building hq;
        HashMap<Vector2,building> map;
        HashMap<Vector2,Integer> effects;
        
        private Boolean IsAttachedToMouse = false;
        private Boolean BuildingBuilding = false;
        private Boolean BuildingRoad =false;
        private Boolean RoadSelected =false;
        private Boolean RenderingPay =false; 
       
        Vector3 mousePos;
        static final int WORLD_WIDTH = 150;
	static final int WORLD_HEIGHT = 100;
        static final int TILE_HEIGHT = 64;
        
       private float rotationSpeed;
       Calendar calendar;
       UI ui;
       float dt;
       int G = 2500000;
       float h = 0;
	
	@Override
	public void create () {
                
                mousePos = new Vector3();
                rotationSpeed = 0.5f;
                float w = Gdx.graphics.getWidth();
	        float h = Gdx.graphics.getHeight();
                cam = new OrthographicCamera(30  , 30 * (h / w));
                cam.position.set(cam.viewportWidth / 2f, cam.viewportHeight / 2f, 0);
                cam.update();
                
                
                map = new HashMap();
                effects = new HashMap();
                buildings = new ArrayList();
                
                atlas = new TextureAtlas(Gdx.files.internal("atlas.atlas"));
                bdg_s = new Sprite(atlas.findRegion("bdg"));
                bdg_s.setPosition(0, 0);
                bdg_s.setSize(WORLD_WIDTH, WORLD_HEIGHT);
                
                font = new BitmapFont();
                font.setColor(Color.YELLOW);
                font.getData().setScale(0.05f);
                batch = new SpriteBatch();
                
                calendar = new Calendar(this);
                ui = new UI(this);
                multiplexer = new InputMultiplexer();
                multiplexer.addProcessor(this);
                multiplexer.addProcessor(ui.stage);
                Gdx.input.setInputProcessor(multiplexer);
                 
                defineRoadRegions();
                defineTreeRegions();
                seteffects();
                addMapItems();
                
                 ssss = new Sprite(atlas.findRegion("f8"));
                 ssss.setSize(1, 1);
                
	}
        @Override
        public void dispose(){
        batch.dispose();
        font.dispose();
        ui.stage.dispose();
        ui.skin.dispose();
        atlas.dispose();
        
        }
	@Override
	public void render () {
		Gdx.gl.glClearColor(1, 1, 1, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
                cam.update();
                dt += Gdx.graphics.getDeltaTime();
                Clock();
                batch.setProjectionMatrix(cam.combined);
               
                moveSpriteWithMouse();
                
		batch.begin();
                  renderBackground();
                   
                  renderBuildings();
                  renderTopLayer();
                  renderPay(dt);
                  if(RoadSelected){
                  ssss.draw(batch);
                  }
                  
                batch.end();
                  ui.Date.setText("Date:" + Integer.toString(calendar.days)+ "/" + Integer.toString(calendar.month) + "/" + Integer.toString(calendar.year));
                  ui.GOLD.setText("$" + Integer.toString(G));
                  ui.stage.act();
                  ui.stage.draw();
                 
	}
@Override
    public boolean keyDown(int i) {
                if (Gdx.input.isKeyPressed(Input.Keys.P)) {
			
		}
                if (Gdx.input.isKeyPressed(Input.Keys.A)) {
			cam.zoom += 0.2;
		}
		if (Gdx.input.isKeyPressed(Input.Keys.Q)) {
			cam.zoom -= 0.2;
		}
		if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
			cam.translate(-10, 0, 0);
		}
		if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
			cam.translate(10, 0, 0);
		}
		if (Gdx.input.isKeyPressed(Input.Keys.DOWN)) {
			cam.translate(0, -10, 0);
		}
		if (Gdx.input.isKeyPressed(Input.Keys.UP)) {
			cam.translate(0, 10, 0);
		}
		if (Gdx.input.isKeyPressed(Input.Keys.W)) {
			cam.rotate(-rotationSpeed, 0, 0, 1);
		}
		if (Gdx.input.isKeyPressed(Input.Keys.E)) {
			cam.rotate(rotationSpeed, 0, 0, 1);
		}

		clampcam();
	

        
       return false; }
@Override
    public boolean keyUp(int i) {
      return false; }
@Override
    public boolean keyTyped(char c) {
      return false; }
@Override
    public boolean touchUp(int i, int i1, int i2, int i3) {
                   if(BuildingBuilding){
                       if(IsAttachedToMouse){
                           for(int i7 = 1 ; i7 < buildings.size(); i7 ++){
                              
                                if(buildings.get(i7).s.getBoundingRectangle().overlaps(buildings.get(0).s.getBoundingRectangle())){
                                    System.out.println("overlapsb");
                                    return true;
                       } 
                     }
                           
                           
                           
                    building b2 = buildings.get(0);
                    buildings.add(b2);
                    
                   if(b2.getType() == 2){
                    b2.setrent(effects.get(new Vector2(b2.x,b2.y)));}
                   addeffects(b2,b2.geteffect());
                    
                    G -=b2.getCost();
                    map.put(new Vector2(b2.x,b2.y),b2);
                    
                    int d = b2.x;
                    int e = b2.y;
                    if(b2.getWidth() != 1 || b2.getHeight() != 1){
                      
                    }
                    
                    IsAttachedToMouse=false;
                    BuildingBuilding=false;
                    buildings.remove(0);
                   
                          
            }
       }
       IsAttachedToMouse = false;
       BuildingRoad = false; 
        return false;
    }
@Override
    public boolean touchDragged(int i, int i1, int i2) { 
       if (BuildingRoad){
         mousePos.x = i;
        mousePos.y = i1;
        cam.unproject(mousePos);
        int rx = Math.round(mousePos.x);
        int ry = Math.round(mousePos.y);
         
        
               if(!map.containsKey(new Vector2(rx,ry))){         ///////if there is nothing here
                  for(int i22=0; i22 < buildings.size(); i22++){ ///////if this mouse pos is in building ////////
                   if(buildings.get(i22).getType() == 2 || buildings.get(i22).getType() == 5 || buildings.get(i22).getType() == 1){
                      if(buildings.get(i22).s.getBoundingRectangle().contains(rx,ry)){
                         return false;   ///////dont do the rest /////////
                       }
                   }
                  }     
                building b3 =  ui.uf.road(rx,ry,new Sprite(atlas.findRegion(getRoadSprite(getBitValue(rx,ry)))) );//////add a road ///////
                map.put(new Vector2(b3.x,b3.y), b3);
                addeffects(b3,b3.geteffect());
                buildings.add(b3);
                
                //////smooth roads/////
                       for(int i21=0; i21 < buildings.size(); i21++){     
                        if(buildings.get(i21).getType() == 4){
                           Sprite rt = new Sprite(atlas.findRegion(getRoadSprite(getBitValue( buildings.get(i21).x, buildings.get(i21).y))));
                           rt.setSize(1, 1);
                           rt.setPosition(buildings.get(i21).x, buildings.get(i21).y);
                           buildings.get(i21).SetS(rt);
                    }
                } //////smooth roads/////
               }
            
        // System.out.println("theres something there");
        
       } 
       return false;}
@Override
    public boolean mouseMoved(int i, int i1) {
       
        return false; }
@Override
    public boolean scrolled(int i) {
        if(i == 1){
        cam.zoom += 0.2;
        }
        if(i == -1){
        cam.zoom -= 0.2;
        }
        clampcam();
	
     return false; }
@Override
    public boolean touchDown(int i, int i1, int i2, int i3) {
                  mousePos.x = Gdx.input.getX(); 
                  mousePos.y = Gdx.input.getY(); 
                  cam.unproject(mousePos);
                  mousePos.x =   Math.round( mousePos.x);
                  mousePos.y =   Math.round( mousePos.y);
                   System.out.println(effects.get(new Vector2(mousePos.x,mousePos.y)));
         if(!IsAttachedToMouse && !BuildingBuilding && !BuildingRoad && !RoadSelected){
              for(int i5 = 0; i5< buildings.size(); i5++){buildings.get(i5).setisSelected(false);}
             for(int i4 = 0; i4< buildings.size(); i4++){
                  if(buildings.get(i4).s.getBoundingRectangle().contains(new Vector2(mousePos.x,mousePos.y))){
                  buildings.get(i4).setisSelected(true);
                 
             ui.OpenBuildigMenu(cam,mousePos,map,buildings,this);
             Gdx.input.setInputProcessor(ui.stage);
                  }
             }
             if(map.containsKey(new Vector2(mousePos.x,mousePos.y))){
             }
         }
         if(RoadSelected) {
           BuildingRoad = true;
           RoadSelected = false;
         }      
         if(buildings.size()>0){
           for (int l =0; l < buildings.size(); l++){
             if(buildings.get(l).s.getBoundingRectangle().contains(mousePos.x,mousePos.y)){
                
             }
           }
         }

     return false;  }
    private void clampcam() {
      
                cam.zoom = MathUtils.clamp(cam.zoom, 0.1f, 150/cam.viewportWidth);

		float effectiveViewportWidth = cam.viewportWidth * cam.zoom;
		float effectiveViewportHeight = cam.viewportHeight * cam.zoom;

		cam.position.x = MathUtils.clamp(cam.position.x, effectiveViewportWidth /2f, 150 - effectiveViewportWidth / 2f);
		cam.position.y = MathUtils.clamp(cam.position.y, effectiveViewportHeight / 2f, 100 - effectiveViewportHeight / 2f);  }
    public void IncG(int rent){
        G += rent;
 
}
    private void moveSpriteWithMouse() {
                  mousePos.x = Gdx.input.getX(); 
                  mousePos.y = Gdx.input.getY(); 
                  cam.unproject(mousePos);
                  
                  
       if(IsAttachedToMouse){
                  
                 ui.b.SetX(Math.round(mousePos.x));
                 ui.b.SetY(Math.round(mousePos.y));
                 ui.b.s.setPosition(Math.round(mousePos.x), Math.round(mousePos.y));
                   if(ui.b.getType()==5){
                     if(ui.b.s2 != null){
                         ui.b.s2.setPosition(Math.round(mousePos.x), Math.round(mousePos.y)+ ui.b.s.getHeight());
                      
                     }
                   }
                  }
      if(RoadSelected){
       batch.begin();
       ssss.setPosition(Math.round(mousePos.x), Math.round(mousePos.y));
       ssss.draw(batch);
       batch.end();
      }
        }
    private void Clock() {
        
        if (dt > 1){
                dt = 0;
                h = 0;
                RenderingPay = false;
                calendar.SecondsInc(buildings);    // everysecond add to calender , buildings needed to give gold every month
                
                } }
    private void renderBuildings() {
      for(int i = 0 ; i < buildings.size(); i ++){
                  if(buildings.get(i).getType() != 0){
                   if( buildings.get(i).getIsSlected()){
                      if(buildings.get(i).s2 != null){
                          buildings.get(i).s2.setColor(Color.BLUE);
                      }
                          buildings.get(i).s.setColor(Color.PINK);
                         // buildings.get(i).s.draw(batch);
                   }
                    buildings.get(i).s.draw(batch);
                           
                  }
      
      }
    }
    public void renderPay(float dt) {

    if(RenderingPay){
     
      for(int i = 1 ; i < buildings.size(); i ++){
              if(buildings.get(i).getType() != 4 && buildings.get(i).getType() != 5){
                  if(buildings.get(i).getage()> buildings.get(i).getMaturity()){
                 int t = buildings.get(i).GetRent()+effects.get(new Vector2(buildings.get(i).x,buildings.get(i).y));
                 int x = buildings.get(i).x;
                 int y = buildings.get(i).y;
                 font.draw(batch,"$"+Integer.toString(t) ,buildings.get(i).x + h, buildings.get(i).y+3 +h); 
              }
      }
    }h+= 0.03;
    }
    }
    private void renderBackground() {
            bdg_s.draw(batch); }
    private void  renderTopLayer(){
         for(int i = 0 ; i < buildings.size(); i ++){
                  if(buildings.get(i).getType() != 0){
                      if(buildings.get(i).getType() == 5)   {
                        if(buildings.get(i).s2 != null){
                          buildings.get(i).s2.draw(batch);
                        
                        }
                      }         
                  }
      
      }
    
    }
    public int GetG(){
    return G;}
    public Boolean isAttached(){ return IsAttachedToMouse; }
    public void setisAttached(Boolean b){ IsAttachedToMouse = b; }
    public Boolean BuildingBuilding(){ return BuildingBuilding; }
    public void setisBuildingBuilding(Boolean b){ BuildingBuilding = b; }
    public Boolean BuildingRoad(){ return BuildingRoad; }
    public void setisBuildingRoad(Boolean b){ BuildingRoad = b; }
    public Boolean RoadSelected(){ return RoadSelected; }
    public void setisRoadSelected(Boolean b){ RoadSelected = b; }
    public Boolean RenderingPay(){ return RenderingPay; }
    public void setRenderingPay(Boolean b){ RenderingPay = b; }
    public void AgeBuildings(building b){
       b.IncAge();
       if(b.getFinished() != null){
       Sprite sp = new Sprite(atlas.findRegion(b.finished));
       sp.setSize(b.getWidth(), b.getHeight());
       sp.setPosition(b.x, b.y);
       b.FinishBuilding(sp);}
     }
    private void addMapItems() {
                Sprite sp = new Sprite(atlas.findRegion("house1"));
                sp.setSize(10, 10);
                hq = new building(75, 50,3,3,sp,1,10,1);
                buildings.add(hq);
                map.put(new Vector2(75, 50), hq);
                cam.translate(65, 40, 0); 
                
               
               
           //   int xx = 40;
           //   for(int i = xx; i < 90 ; i++){
           //   building b;
           //   b =  ui.uf.road(70, 48,new Sprite(atlas.findRegion("a1")));
           //   buildings.add(b);
           //   addeffects(b);
           //   map.put(new Vector2(b.x,b.y), b);
           //   }

    
    }
    public void addeffects(building b2,int effect) {
       
         for(int x = b2.x - b2.getaoe() ; x < (b2.x+b2.getaoe()+b2.getaoe()+b2.getWidth()); x++){
              for(int y =b2.y - b2.getaoe() ; y < (b2.y+b2.getaoe()+b2.getaoe()+b2.getHeight()); y++){
                if(b2.x == x && b2.y == y){
                    
                  }else{
                      int eff = effects.get(new Vector2(x,y));
                  eff = eff + effect;
                  effects.replace(new Vector2(x,y),eff);
                   }
              }
           }
    
     }
    private void seteffects() {
          for(int x = -2 ; x < 152; x++){
              for(int y = -2 ; y < 102; y++){
                  effects.put(new Vector2(x,y), 0);
                  
                 }
              }
          }
    public building getSelectedBuilding(){
    for(int i = 0 ; i < buildings.size(); i ++){
                  if(buildings.get(i).isSelected()){
                     return buildings.get(i);
                      
                  
                  } 
    
    }
     System.out.println("returned null");return null;
    }
    private void defineRoadRegions() {
        
        Sprite t = new Sprite(atlas.findRegion("road4"));
               atlas.addRegion("a1", t.getTexture(),724 , 221, 48, 48);
               atlas.addRegion("a2", t.getTexture(),772 , 221, 48,48);
               atlas.addRegion("a3", t.getTexture(),821 , 222, 48,47);
               atlas.addRegion("a4", t.getTexture(),868 , 221, 48,48);
               atlas.addRegion("a5", t.getTexture(),916 , 221,  48,48);
               atlas.addRegion("a6", t.getTexture(),965 , 222,  48,47);
               atlas.addRegion("a7", t.getTexture(),1012, 221,  48,48);
               atlas.addRegion("a8", t.getTexture(),1060, 221, 48,48);
               
               atlas.addRegion("b1", t.getTexture(),724 , 270, 48, 48);
               atlas.addRegion("b2", t.getTexture(),772 , 269, 48,48);
               atlas.addRegion("b3", t.getTexture(),820 , 269, 48,48);
               atlas.addRegion("b4", t.getTexture(),868 , 269, 48,48);
               atlas.addRegion("b5", t.getTexture(),916 , 269,  48,48);
               atlas.addRegion("b6", t.getTexture(),964 , 269,  48,48);
               atlas.addRegion("b7", t.getTexture(),1012, 269,  48,48);
               atlas.addRegion("b8", t.getTexture(),1060, 269, 48,48);
               
               atlas.addRegion("c1", t.getTexture(),724 , 317, 48, 48);
               atlas.addRegion("c2", t.getTexture(),772 , 317, 48,48);
               atlas.addRegion("c3", t.getTexture(),820 , 317, 48,48);
               atlas.addRegion("c4", t.getTexture(),868 , 317, 48,48);
               atlas.addRegion("c5", t.getTexture(),916 , 317,  48,48);
               atlas.addRegion("c6", t.getTexture(),964 , 317,  48,48);
               atlas.addRegion("c7", t.getTexture(),1012, 317,  48,48);
               atlas.addRegion("c8", t.getTexture(),1060, 317, 48,48);
               
               atlas.addRegion("d1", t.getTexture(),724 , 365, 48, 48);
               atlas.addRegion("d2", t.getTexture(),772 , 365, 48,48);
               atlas.addRegion("d3", t.getTexture(),820 , 365, 48,48);
               atlas.addRegion("d4", t.getTexture(),868 , 365, 48,48);
               atlas.addRegion("d5", t.getTexture(),916 , 365,  48,48);
               atlas.addRegion("d6", t.getTexture(),964 , 365,  48,48);
               atlas.addRegion("d7", t.getTexture(),1012, 365,  48,48);
               atlas.addRegion("d8", t.getTexture(),1060, 365, 48,48);
               
               atlas.addRegion("e1", t.getTexture(),724 , 413, 48, 48);
               atlas.addRegion("e2", t.getTexture(),772 , 413, 48,48);
               atlas.addRegion("e3", t.getTexture(),820 , 413, 48,48);
               atlas.addRegion("e4", t.getTexture(),868 , 413, 48,48);
               atlas.addRegion("e5", t.getTexture(),916 , 413,  48,48);
               atlas.addRegion("e6", t.getTexture(),964 , 413,  48,48);
               atlas.addRegion("e7", t.getTexture(),1012, 413,  48,48);
               atlas.addRegion("e8", t.getTexture(),1060, 413, 48,48);
               
               atlas.addRegion("f1", t.getTexture(),724 , 461, 48, 48);
               atlas.addRegion("f2", t.getTexture(),772 , 461, 48,48);
               atlas.addRegion("f3", t.getTexture(),820 , 461, 48,48);
               atlas.addRegion("f4", t.getTexture(),868 , 461, 48,48);
               atlas.addRegion("f5", t.getTexture(),916 , 461,  48,48);
               atlas.addRegion("f6", t.getTexture(),964 , 461,  48,48);
               atlas.addRegion("f7", t.getTexture(),1012, 461,  48,48);
               atlas.addRegion("f8", t.getTexture(),1060, 461, 48,48);  }
    private void defineTreeRegions() {
        Sprite t = new Sprite(atlas.findRegion("tree"));
               atlas.addRegion("t1_1", t.getTexture(),1110 , 484, 32, 24);
               atlas.addRegion("t1_2", t.getTexture(),1110 , 462, 32, 24);
               
               atlas.addRegion("t2_1", t.getTexture(),1140 , 484, 32, 24);
               atlas.addRegion("t2_2", t.getTexture(),1140 , 462, 32, 24);
               
               atlas.addRegion("t3_1", t.getTexture(),1174 , 484, 32, 24);
               atlas.addRegion("t3_2", t.getTexture(),1174 , 462, 32, 24);
               
               atlas.addRegion("t4_1", t.getTexture(),1204 , 484, 32, 24);
               atlas.addRegion("t4_2", t.getTexture(),1204 , 462, 32, 24);
       }
    private String getRoadSprite(int bitvalue){
    String s = null;
   
       switch(bitvalue){
           case 0  :s = "f8";
            break;
           case 1  :s = "b7";   ////north
           break;
           case 2 :s = "b1";   //// N+S
          break;
           case 3 :s = "a7";   //// N+S
          break;
         case 4  :s = "b7";   ////east
          break;
          case 5 :s = "b7";   //// N+E
          break;
          case 6 :s = "c3";   //// N+S
          break;
          case 7 :s = "c4";   //// N+S
          break;
          case 8 :s = "b1";   //// N+S
          break;
          case 10 :s = "b1";   //// N+S
          break;
          case 11 :s = "b2";   //// N+S
          break;
          case 12 :s = "b8";   //// N+S
          break;
          case 13 :s = "c1";   //// N+S
          break;
         case 9:s = "a4";   ////south
          break;
          case 14 :s = "c6";   //// N+S
          break;
          case 15 :s = "c7";   //// N+S
          break;
         
         default: s = "f8";
         break;
          
       
       }
    
     return  s;
    }
    private int getBitValue(int rx,int ry){
        int bitvalue = 0;
    
                  
            if(map.containsKey(new Vector2(rx,ry+1)) && map.get(new Vector2(rx,ry+1)).getType() == 4){
               bitvalue +=1;
            }
            if(map.containsKey(new Vector2(rx+1,ry)) && map.get(new Vector2(rx+1,ry)).getType() == 4){
               bitvalue +=2;
            }
            if(map.containsKey(new Vector2(rx,ry-1)) && map.get(new Vector2(rx,ry-1)).getType() == 4){
               bitvalue +=4;
            }
            if(map.containsKey(new Vector2(rx-1,ry)) && map.get(new Vector2(rx-1,ry)).getType() == 4){
               bitvalue +=8;
            }
            
            System.out.println("m" + bitvalue);
            return bitvalue;
   
}



}
     
    
   

