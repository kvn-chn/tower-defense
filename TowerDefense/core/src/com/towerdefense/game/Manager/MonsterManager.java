package com.towerdefense.game.Manager;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.towerdefense.game.Renderer.MonsterRenderer;
import com.towerdefense.game.monsters.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MonsterManager {
    private List<AEnemy> monster;
    private List<Vector2> waypoints;
    private Vector2 StartPosition;
    private int WaveNumber=1;
    private MonsterRenderer monsterRenderer;
    private int incrementInterval = 3;
    private int MonsterNumber =0;
    public MonsterManager(Vector2 StartPosition, ArrayList<Vector2> waypoints){
        monster=new ArrayList<AEnemy>();
        this.waypoints=waypoints;
        this.StartPosition=StartPosition;
        monsterRenderer=new MonsterRenderer();
        optimizeWaypoints();
        initMonsters();
    }
    private void initMonsters(){
        //System.out.println(WaveNumber);

        for (int i = 0; i < WaveNumber; i++) {

            Random random = new Random();
            int MonsterType = (WaveNumber/2)+1;

            if (WaveNumber > 4) {
                MonsterType = 4;
            }
            if(WaveNumber==0){
                MonsterType=1;
            }
            int randomNumber = random.nextInt(MonsterType) + 1;
            switch (randomNumber){
                case 1:
                    monster.add(new Bee(StartPosition.x, StartPosition.y));
                    break;
                case 2:
                    monster.add(new Slime(StartPosition.x, StartPosition.y));
                    break;
                case 3:
                    monster.add(new Wolf(StartPosition.x, StartPosition.y));
                    break;
                case 4:
                    monster.add(new Goblin(StartPosition.x, StartPosition.y));
                    break;
            }
            if (WaveNumber >= 5) {
                for (AEnemy enemy : monster) {
                    enemy.increaseSpeed(2);
                    enemy.increaseHP(5);
                }
            }
        }

        System.out.println("CREATED: "+monster.size()+" Monsters");
    }

    private boolean WaveAlive(){
        for (AEnemy monster:monster) {
            if(monster.isAlive()){
                return true;
            }
        }
        return false;
    }

    public void update(float GameTime){

        for (int i = 0; i < MonsterNumber; i++) {
            monster.get(i).DoTUpdate(GameTime);
            if (!waypoints.get(monster.get(i).getPathed()).equals(new Vector2((int) monster.get(i).getPosition().x, (int) monster.get(i).getPosition().y))) {
                monster.get(i).move(waypoints.get(monster.get(i).getPathed()), Gdx.graphics.getDeltaTime());
            } else {
                if (monster.get(i).getPathed() < (waypoints.size() - 1)) {
                    monster.get(i).addPathed();

                }
            }
        }
        if (!WaveAlive()){
            monster.clear();
            MonsterNumber=1;
            WaveNumber+=1;
            initMonsters();
        }
    }
    public void render(float stateTime, SpriteBatch batch){
        for (int i = 0; i < MonsterNumber; i++) {
            monsterRenderer.renderMonster(monster.get(i),stateTime,batch);
        }
    }

    public List<AEnemy> getMonster() {
        return monster;
    }

    public int getIncrementInterval() {
        return incrementInterval;
    }
    
    public void addMonster() {
        if (MonsterNumber < monster.size()) {
            this.MonsterNumber += 1;
        }
    }

    public void setMonsterNumber(int MonsterNumber) {
        this.MonsterNumber = MonsterNumber;
    }

    private void optimizeWaypoints() {
        if (waypoints.size() < 3) {
            return;
        }

        List<Vector2> optimizedWaypoints = new ArrayList<>();
        optimizedWaypoints.add(waypoints.get(0));

        for (int i = 1; i < waypoints.size() - 1; i++) {
            Vector2 previous = waypoints.get(i - 1);
            Vector2 current = waypoints.get(i);
            Vector2 next = waypoints.get(i + 1);


            if (!arePointsCollinear(previous, current, next)) {
                optimizedWaypoints.add(current);
            }
        }

        optimizedWaypoints.add(waypoints.get(waypoints.size() - 1));

        waypoints = optimizedWaypoints;
    }

    private boolean arePointsCollinear(Vector2 point1, Vector2 point2, Vector2 point3) {
        return Math.abs((point2.x - point1.x) * (point3.y - point1.y) - (point2.y - point1.y) * (point3.x - point1.x)) < 0.0001;
    }

}
