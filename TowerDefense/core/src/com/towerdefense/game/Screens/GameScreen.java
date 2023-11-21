package com.towerdefense.game.Screens;

import com.badlogic.gdx.*;
import com.badlogic.gdx.graphics.*;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.ScreenUtils;
import com.towerdefense.game.Manager.MonsterManager;
import com.towerdefense.game.Map.Map;
import com.towerdefense.game.Map.Map1;
import com.towerdefense.game.Map.Map3;
import com.towerdefense.game.Renderer.MonsterRenderer;
import com.towerdefense.game.Map.Map2;
import com.towerdefense.game.Player;
import com.towerdefense.game.TowerDefense;
import com.towerdefense.game.monsters.*;
import com.towerdefense.game.tower.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class GameScreen implements Screen,InputProcessor {
    private TowerDefense game;
    private boolean isPaused = false;
    private static final int WORLD_WIDTH = 1280;
    private static final int WORLD_HEIGHT = 720;
    private SpriteBatch batch;
    private int[][] map;
    private Texture enemyPathTexture;
    private Texture castleTexture;
    private Texture greenPath;
    private Texture fireIcon;
    private Texture iceIcon;
    private Texture thunderIcon;
    private Texture placeForTower;
    private Texture startTexture;
    private Animation<TextureRegion> fireTowerAnimation;
    private Animation <TextureRegion> iceTowerAnimation;
    private Animation <TextureRegion> thunderTowerAnimation;
    private float stateTime;
    private float stateTime2;
    private OrthographicCamera camera;
    private boolean towerPlacementMode = false;
    private int nbr = 0;
    private List<ATower> tower;
    private ArrayList<Vector2> waypoints;
    private Texture ennemyPathHorizontal;
    private Texture enemyPathVertical;
    private Texture enemyPathUpToRight;
    private Texture enemyPathLeftToDown;
    private Texture enemyPathLeftToUp;
    private Texture enemyPathDownToRight;
    private Texture shadow1;
    private Texture shadow2;
    private Texture shadow6;
    private Texture bush6;
    private Texture tree1;
    private Texture tree2;
    private Texture flower1;
    private Texture flower9;
    private Texture box1;
    private Texture camp2;
    private Texture camp3;
    private Texture camp4;
    private Texture campFire1;
    private Texture campFire2;
    private TextureRegion[][] frameCampFire1;
    private TextureRegion[][] frameCampFire2;
    private Animation<TextureRegion> campFireAnimation1;
    private Animation<TextureRegion> campFireAnimation2;
    private TextureRegion campFireFrame1;
    private TextureRegion campFireFrame2;
    private Texture log2;
    private Texture log3;
    private Texture lamp1;
    private Texture grass4;
    private Texture grass6;
    private Texture stone1;
    private Texture stone6;
    private Texture dirt6;
    private Texture stone11;
    private Texture poisonIcon;
    private Texture laserIcon;
    private ShapeRenderer HPRenderer;
    private MonsterRenderer monsterRenderer;
    private ShapeRenderer shapeRenderer;
    private Texture infoBgTexture;
    private Texture costBg;
    private SpriteBatch uiBatch;
    private BitmapFont font;
    private Animation<TextureRegion> startingPointAnimation;
    private boolean destroyTowerMode = false;
    //private boolean[][] towerCreated;
    private ATower[][] towerInGrid;
    private ATower selectedTower = null;

    private Texture upgradeButtonTexture;
    private Texture deleteButtonTexture;

    private List<ATower> towers;
    private int ForWave = 0;
    private Player player;
    private MonsterManager monsterManager;
    private Map map1;
    private Map2 map2;
    private Map3 map3;
    private Random random;
    private Texture backgroundTexture;
    private Texture background1Texture;
    private Texture resumeButtonTexture;
    private Texture pauseButtonTexture;
    private Texture exitButtonTexture;
    private Texture playButtonTexture;
    private float buttonWidth;
    private float buttonHeight;
    private float buttonSpacing;
    private int rdmMap;
    public GameScreen(SpriteBatch batch, TowerDefense game){
        this.batch=batch;
        this.game=game;
    }
    @Override
    public void show() {

        Gdx.gl.glClearColor(1, 1, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        Gdx.input.setInputProcessor(this);
        camera = new OrthographicCamera();
        camera.setToOrtho(false, WORLD_WIDTH, WORLD_HEIGHT);
        camera.update();

        batch = new SpriteBatch();
        monsterRenderer = new MonsterRenderer();
        shapeRenderer = new ShapeRenderer();
        HPRenderer = new ShapeRenderer();

        greenPath = new Texture("FieldsTile_38.png");
        shadow1 = new Texture("Objects/1 Shadow/1.png");
        shadow2 = new Texture("Objects/1 Shadow/2.png");
        shadow6 = new Texture("Objects/1 Shadow/6.png");
        bush6 = new Texture("Objects/9 Bush/6.png");
        tree1 = new Texture("Objects/7 Decor/tree1.png");
        tree2 = new Texture("Objects/7 Decor/tree2.png");
        flower1 = new Texture("Objects/6 Flower/1.png");
        flower9 = new Texture("Objects/6 Flower/9.png");
        box1 = new Texture("Objects/7 Decor/Box1.png");
        log2 = new Texture("Objects/7 Decor/Log2.png");
        log3 = new Texture("Objects/7 Decor/Log3.png");
        camp2 = new Texture("Objects/8 Camp/2.png");
        camp3 = new Texture("Objects/8 Camp/3.png");
        camp4 = new Texture("Objects/8 Camp/4.png");
        campFire1 = new Texture("Animated Objects/2 Campfire/2.png");
        campFire2 = new Texture("Animated Objects/2 Campfire/1.png");
        frameCampFire1 = TextureRegion.split(campFire1, 32, 32);
        frameCampFire2 = TextureRegion.split(campFire2, 32, 64);
        campFireAnimation1 = new Animation<>(0.1f, frameCampFire1[0]);
        campFireAnimation2 = new Animation<>(0.1f, frameCampFire2[0]);
        lamp1 = new Texture("Objects/7 Decor/Lamp1.png");
        grass4 = new Texture("Objects/5 Grass/4.png");
        grass6 = new Texture("Objects/5 Grass/6.png");
        stone1 = new Texture("Objects/4 Stone/1.png");
        stone6 = new Texture("Objects/4 Stone/6.png");
        stone11 = new Texture("Objects/4 Stone/11.png");
        dirt6 = new Texture("Objects/7 Decor/Dirt6.png");

        ennemyPathHorizontal = new Texture("Tiles/FieldsTile_31.png");
        enemyPathVertical = new Texture("Tiles/FieldsTile_32.png");
        enemyPathUpToRight = new Texture("Tiles/FieldsTile_22.png");
        enemyPathLeftToDown = new Texture("Tiles/FieldsTile_12.png");
        enemyPathLeftToUp = new Texture("Tiles/FieldsTile_28.png");
        enemyPathDownToRight = new Texture("Tiles/FieldsTile_10.png");

        castleTexture = new Texture("castle.png");
        startTexture = new Texture("startingPoint.png");
        fireIcon = new Texture("tower/flame/icon.png");
        iceIcon = new Texture("tower/ice/icon.png");
        thunderIcon = new Texture("tower/thunder/icon.png");
        poisonIcon = new Texture("tower/poison/icon.png");
        laserIcon = new Texture("tower/laser/icon.png");
        placeForTower = new Texture("Objects/PlaceForTower1.png");

        upgradeButtonTexture = new Texture("upgradeButton.png");
        deleteButtonTexture = new Texture("sellButton.png");

        infoBgTexture = new Texture("info/infoBg.png");
        costBg = new Texture("empty.png");
        uiBatch = new SpriteBatch();
        font = new BitmapFont();

        Pixmap cursorPixmap = new Pixmap(Gdx.files.internal("cursor.png"));
        Gdx.graphics.setCursor(Gdx.graphics.newCursor(cursorPixmap, 0, 0));
        cursorPixmap.dispose();

        random = new Random();

        rdmMap = random.nextInt(3) + 1;
        switch (rdmMap) {
            case 1:
                map1 = new Map1();
                break;
            case 2:
                map1 = new Map2();
                break;
            case 3:
                map1 = new Map3();
                break;
        }
        //map = map2.getMap();

        map = map1.getMap();
        towers = new ArrayList<>();

        towerInGrid = new ATower[map.length][map[0].length];
        //towerCreated = new boolean[map.length][map[0].length];
        for (int y = 0; y < map.length; y++) {
            for (int x = 0; x < map[y].length; x++) {
                towerInGrid[y][x] = null;
            }
        }

        TextureRegion[][] frameStartingPoint = TextureRegion.split(startTexture, 46, 66);

        float frameDuration = 0.1f;

        startingPointAnimation = new Animation<>(frameDuration, frameStartingPoint[0]);

        stateTime = 0;
        stateTime2 = 0;

        backgroundTexture = new Texture("bk.png");
        background1Texture = new Texture("bk 1.png");
        playButtonTexture = new Texture("btn1.png");
        exitButtonTexture = new Texture("btn2.png");
        resumeButtonTexture = new Texture("Resume.png");
        pauseButtonTexture = new Texture("Menu.png");
        buttonWidth = Gdx.graphics.getWidth() / 4f;
        buttonHeight = Gdx.graphics.getHeight() / 8f;
        buttonSpacing = Gdx.graphics.getHeight() / 30f;

        map1.findPath();
        //map2.findPath();
        waypoints = map1.getWaypoints();
        //waypoints=map2.getWaypoints();

        player=new Player(100,200);
        monsterManager=new MonsterManager(new Vector2(waypoints.get(0).x,waypoints.get(0).y),waypoints);
    }

    @Override
    public void render(float delta) {
        ScreenUtils.clear(1, 1, 1, 1);
        batch.begin();
        if (!isPaused) {

            stateTime += Gdx.graphics.getDeltaTime();
            stateTime2 += Gdx.graphics.getDeltaTime();
            if (stateTime >= monsterManager.getIncrementInterval()) {
                monsterManager.addMonster();
                if (ForWave > monsterManager.getMonster().size()) {
                    monsterManager.setMonsterNumber(monsterManager.getMonster().size());
                }
                stateTime = stateTime % monsterManager.getIncrementInterval();
            }

            creatingtower();
            batch.end();
            creatingmonster(stateTime2);
            batch.begin();
            if (player.getHp() < 1) {

                game.setScreen(new MenuScreen(batch,game));
                if (player.isAlive()) {
                    System.out.println("GAME OVER");
                    player.Dies();
                }
            }

            for (ATower tower : towers) {
                tower.updateTime(stateTime2, monsterManager);
                tower.renderProjectiles(batch);
            }

            if (!monsterManager.getMonster().isEmpty()) {
                for (AEnemy enemy : monsterManager.getMonster()) {
                    for (ATower tower : towers) {
                        tower.updateProjectile(enemy);
                        if (tower.isEnemyInRange(enemy) && tower.getCurrentTarget() != null) {
                            tower.attack(tower.getCurrentTarget());
                            if (enemy.getPV() <= 0 && enemy.isBountyAvailable()) {//
                                enemy.Dies();
                                enemy.GiveBounty();
                                player.gainMoney(tower.getCurrentTarget().getGold());
                            }
                        }
                    }
                }
            }

            batch.end();
            if (selectedTower != null) {
                selectedTower.showRadius(shapeRenderer);
            }

            uiBatch.begin();
            drawInformationPanel();
            uiBatch.end();
            if (player.isAlive()) {
                renderCastleHealthBar(batch, shapeRenderer);
            }
            batch.begin();
        }else {
            pause();
        }
        batch.end();
    }

    public void creatingmonster(float stateTime){
        monsterManager.update(Gdx.graphics.getDeltaTime());
        monsterManager.render(stateTime,batch);
        for (AEnemy Themonster:monsterManager.getMonster()) {
            monsterRenderer.renderHealthBar(batch, HPRenderer ,Themonster);
            if(Themonster.getPosition().equals(waypoints.get(waypoints.size()-1))){
                if(Themonster.isAlive()){
                    player.LoseHp(Themonster.getDamage());
                    System.out.println(player.getHp());
                }
                Themonster.Dies();
            }
        }
    }

    private ATower aTowerFire;
    private ATower aTowerIce;
    private ATower aTowerThunder;
    private ATower aTowerPoison;
    private ATower aTowerLaser;


    public void creatingtower(){
        TextureRegion startingPointFrame = startingPointAnimation.getKeyFrame(stateTime, true);
        campFireFrame1 = campFireAnimation1.getKeyFrame(stateTime2, true);
        campFireFrame2 = campFireAnimation2.getKeyFrame(stateTime2, true);


        for (int y = 0; y < map.length; y++) {
            for (int x = 0; x < map[y].length; x++) {
                float xPos = x * ((float) WORLD_WIDTH / map[y].length);
                float yPos = (map.length - y - 1) * ((float) WORLD_HEIGHT / map.length);

                float imageScale = 1.0f;
                int gridWidth = map[y].length;
                int gridHeight = map.length;
                float gridCellWidth = (float) WORLD_WIDTH / gridWidth;
                float gridCellHeight = (float) WORLD_HEIGHT / gridHeight;
                switch (map[y][x]) {
                    case 0:
                        batch.draw(greenPath, xPos, yPos, gridCellWidth, gridCellHeight);
                        break;
                    case 1:
                        imageScale = (float) 62 / gridCellWidth;
                        batch.draw(greenPath, xPos, yPos, gridCellWidth, gridCellHeight);
                        batch.draw(placeForTower, xPos, yPos + 8, gridCellWidth * imageScale, gridCellHeight * imageScale / 1.2f);
                        break;
                    case 2:
                        batch.draw(ennemyPathHorizontal, xPos, yPos, gridCellWidth, gridCellHeight);
                        break;
                    case 3:
                        batch.draw(ennemyPathHorizontal, xPos, yPos, gridCellWidth, gridCellHeight);
                        batch.draw(castleTexture, xPos, yPos, castleTexture.getWidth() * 1.6f, castleTexture.getHeight() * 1.8f);
                        break;
                    case 4:
                        imageScale = (float) 46 / gridCellWidth;
                        batch.draw(ennemyPathHorizontal, xPos, yPos, gridCellWidth, gridCellHeight);
                        batch.draw(startingPointFrame, xPos, yPos, gridCellWidth, gridCellHeight);
                        break;
                    case 5:
                        imageScale = (float) 45 / gridCellWidth;
                        if (towerInGrid[y][x] == null) {
                            ATower towerFire = new FireTower((xPos + (gridCellWidth - 45 * imageScale) / 2.7f), (yPos + (gridCellHeight - 89 * imageScale)));
                            towers.add(towerFire);
                            aTowerFire = towerFire;
                            towerInGrid[y][x] = towerFire;
                        }
                        batch.draw(greenPath, xPos, yPos, gridCellWidth, gridCellHeight);
                        batch.draw(aTowerFire.getTowerFrame(), xPos + (gridCellWidth - aTowerFire.getTowerFrame().getRegionWidth() * imageScale) / 2.7f, yPos + (gridCellHeight - aTowerFire.getTowerFrame().getRegionHeight() * imageScale), aTowerFire.getTowerFrame().getRegionWidth(), aTowerFire.getTowerFrame().getRegionHeight());
                        break;
                    case 6:
                        imageScale = (float) 45 / gridCellWidth;
                        if (towerInGrid[y][x] == null) {
                            ATower towerIce = new IceTower((xPos + (gridCellWidth - 45 * imageScale) / 2.7f), (yPos + (gridCellHeight - 77 * imageScale)));
                            towers.add(towerIce);
                            aTowerIce = towerIce;
                            towerInGrid[y][x] = towerIce;
                        }
                        batch.draw(greenPath, xPos, yPos, gridCellWidth, gridCellHeight);
                        batch.draw(aTowerIce.getTowerFrame(), xPos + (gridCellWidth - aTowerIce.getTowerFrame().getRegionWidth() * imageScale) / 2.7f, yPos + (gridCellHeight - aTowerIce.getTowerFrame().getRegionHeight() * imageScale), aTowerIce.getTowerFrame().getRegionWidth(), aTowerIce.getTowerFrame().getRegionHeight());
                        break;
                    case 7:
                        imageScale = (float) 45 / gridCellWidth;
                        if (towerInGrid[y][x] == null) {
                            ATower thunderTower = new ThunderTower((xPos + (gridCellWidth - 45 * imageScale) / 2.7f), (yPos + (gridCellHeight - 72 * imageScale)));
                            towers.add(thunderTower);
                            aTowerThunder = thunderTower;
                            towerInGrid[y][x] = thunderTower;
                        }
                        batch.draw(greenPath, xPos, yPos, gridCellWidth, gridCellHeight);
                        batch.draw(aTowerThunder.getTowerFrame(), xPos + (gridCellWidth - aTowerThunder.getTowerFrame().getRegionWidth() * imageScale) / 2.9f, yPos + (gridCellHeight - aTowerThunder.getTowerFrame().getRegionHeight() * imageScale), aTowerThunder.getTowerFrame().getRegionWidth(), aTowerThunder.getTowerFrame().getRegionHeight());
                        break;
                    case 8:
                        imageScale = (float) 45 / gridCellWidth;
                        if (towerInGrid[y][x] == null) {
                            ATower poisonTower = new PoisonTower((xPos + (gridCellWidth - 45 * imageScale) / 2.7f), (yPos + (gridCellHeight - 75 * imageScale)));
                            towers.add(poisonTower);
                            aTowerPoison = poisonTower;
                            towerInGrid[y][x] = poisonTower;
                        }
                        batch.draw(greenPath, xPos, yPos, gridCellWidth, gridCellHeight);
                        batch.draw(aTowerPoison.getTowerFrame(), xPos + (gridCellWidth - aTowerPoison.getTowerFrame().getRegionWidth() * imageScale) / 2.9f, yPos + (gridCellHeight - aTowerPoison.getTowerFrame().getRegionHeight() * imageScale), aTowerPoison.getTowerFrame().getRegionWidth(), aTowerPoison.getTowerFrame().getRegionHeight());
                        break;
                    case 9:
                        imageScale = (float) 36 / gridCellWidth;
                        if (towerInGrid[y][x] == null) {
                            ATower laserTower = new LaserTower((xPos + (gridCellWidth - 36) / 4f), (yPos + (gridCellHeight - 38 * imageScale) / 2f));
                            towers.add(laserTower);
                            aTowerLaser = laserTower;
                            towerInGrid[y][x] = laserTower;
                        }
                        batch.draw(greenPath, xPos, yPos, gridCellWidth, gridCellHeight);
                        batch.draw(aTowerLaser.getTowerFrame2(), xPos + (gridCellWidth - aTowerLaser.getTowerFrame2().getRegionWidth() * imageScale) / 4f, yPos + (gridCellHeight - aTowerLaser.getTowerFrame2().getRegionHeight() * imageScale) / 2f, aTowerLaser.getTowerFrame2().getRegionWidth(), aTowerLaser.getTowerFrame2().getRegionHeight());
                        break;
                    case 10:
                        batch.draw(greenPath, xPos, yPos, gridCellWidth, gridCellHeight);
                        batch.draw(shadow6, xPos - 13, yPos, gridCellWidth * 1.3f, gridCellHeight);
                        batch.draw(tree1, xPos, yPos + 24, gridCellWidth, gridCellHeight);
                        break;
                    case 11:
                        batch.draw(greenPath, xPos, yPos, gridCellWidth, gridCellHeight);
                        batch.draw(shadow6, xPos - 13, yPos, gridCellWidth * 1.3f, gridCellHeight);
                        batch.draw(bush6, xPos, yPos + 24, gridCellWidth, gridCellHeight / 1.7f);
                        break;
                    case 12:
                        batch.draw(enemyPathVertical, xPos, yPos, gridCellWidth, gridCellHeight);
                        break;
                    case 13:
                        batch.draw(enemyPathUpToRight, xPos, yPos, gridCellWidth, gridCellHeight);
                        break;
                    case 14:
                        imageScale = (float) 46 / gridCellWidth;
                        batch.draw(enemyPathVertical, xPos, yPos, gridCellWidth, gridCellHeight);
                        batch.draw(startingPointFrame, xPos, yPos, gridCellWidth, gridCellHeight);
                        break;
                    case 15:
                        batch.draw(enemyPathLeftToDown, xPos, yPos, gridCellWidth, gridCellHeight);
                        break;
                    case 16:
                        batch.draw(enemyPathLeftToUp, xPos, yPos, gridCellWidth, gridCellHeight);
                        break;
                    case 17:
                        batch.draw(enemyPathDownToRight, xPos, yPos, gridCellWidth, gridCellHeight);
                        break;
                    case 20:
                        batch.draw(greenPath, xPos, yPos, gridCellWidth, gridCellHeight);
                        batch.draw(tree2, xPos + 18, yPos + 24, gridCellWidth / 2f, gridCellHeight / 2f);
                        break;
                    case 21:
                        batch.draw(greenPath, xPos, yPos, gridCellWidth, gridCellHeight);
                        batch.draw(shadow1, xPos + 22, yPos + 24, shadow1.getWidth() * 1.2f, shadow1.getHeight() * 1.2f);
                        batch.draw(flower1, xPos + 28, yPos + 32, flower1.getWidth() * 2, flower1.getHeight() * 2);
                        break;
                    case 22:
                        batch.draw(greenPath , xPos, yPos, gridCellWidth, gridCellHeight);
                        batch.draw(shadow6, xPos - 3, yPos + 5, shadow6.getWidth() * 0.7f, shadow6.getHeight() * 0.7f);
                        batch.draw(campFireFrame2, xPos + 8, yPos + 20, (float) campFire2.getWidth() /6 * 1.5f, campFire2.getHeight() * 1.5f);
                        break;
                    case 23:
                        batch.draw(greenPath , xPos, yPos, gridCellWidth, gridCellHeight);
                        batch.draw(shadow6, xPos - 3, yPos + 15, shadow6.getWidth() * 0.7f, shadow6.getHeight() * 0.7f);
                        batch.draw(campFireFrame1, xPos + 8, yPos + 30, (float) campFire1.getWidth() /6 * 1.5f, campFire1.getHeight() * 1.5f);
                        break;
                    case 24:
                        batch.draw(greenPath , xPos, yPos, gridCellWidth, gridCellHeight);
                        batch.draw(box1, xPos + 18, yPos + 26, box1.getWidth() * 1.7f, box1.getHeight() * 1.7f);
                        break;
                    case 25:
                        batch.draw(greenPath , xPos, yPos, gridCellWidth, gridCellHeight);
                        batch.draw(log3, xPos + 5, yPos + 60, log3.getWidth() * 1.2f, log3.getHeight() * 1.3f);
                        break;
                    case 26:
                        batch.draw(greenPath , xPos, yPos, gridCellWidth, gridCellHeight);
                        batch.draw(lamp1, xPos, yPos + 10, lamp1.getWidth()* 1.5f, lamp1.getHeight() * 1.9f);
                        break;
                    case 27:
                        batch.draw(greenPath , xPos, yPos, gridCellWidth, gridCellHeight);
                        batch.draw(shadow2, xPos, yPos, shadow2.getWidth(), shadow2.getHeight());
                        batch.draw(grass4, xPos + 5, yPos + 5, grass4.getWidth() * 2f, grass4.getHeight() * 2f);
                        batch.draw(shadow2, xPos + 10, yPos + 37, shadow2.getWidth(), shadow2.getHeight());
                        batch.draw(grass6, xPos + 15, yPos + 42, grass6.getWidth() * 2f, grass6.getHeight() * 2f);
                        batch.draw(shadow2, xPos + 25, yPos + 15, shadow2.getWidth(), shadow2.getHeight());
                        batch.draw(flower9, xPos + 30, yPos + 20, flower9.getWidth() * 2f, flower9.getHeight() * 2f);
                        break;
                    case 28:
                        batch.draw(greenPath , xPos, yPos, gridCellWidth, gridCellHeight);
                        batch.draw(stone1, xPos, yPos + 5, stone1.getWidth() * 2f, stone1.getHeight() * 2f);
                        batch.draw(stone6, xPos + 30, yPos + 5, stone6.getWidth() * 2f, stone6.getHeight() * 2f);
                        batch.draw(stone11, xPos + 7, yPos + 30, stone11.getWidth() * 1.1f, stone11.getHeight() * 1.1f);
                        break;
                    case 29:
                        batch.draw(greenPath, xPos, yPos, gridCellWidth, gridCellHeight);
                        batch.draw(dirt6, xPos, yPos + 28, dirt6.getWidth(), dirt6.getHeight());
                        batch.draw(camp3, xPos + 3, yPos + 28, camp3.getWidth() * 1.2f, camp3.getHeight() * 1.2f);
                        break;
                    case 30:
                        batch.draw(greenPath, xPos, yPos, gridCellWidth, gridCellHeight);
                        batch.draw(camp2, xPos + 10, yPos, camp2.getWidth(), camp2.getHeight());
                        break;
                    case 31:
                        batch.draw(greenPath, xPos, yPos, gridCellWidth, gridCellHeight);
                        batch.draw(log2, xPos + 15, yPos + 15, log2.getWidth() * 1.2f, log2.getHeight() * 1.2f);
                        break;
                    case 32:
                        batch.draw(greenPath, xPos, yPos, gridCellWidth, gridCellHeight);
                        batch.draw(dirt6, xPos, yPos + 22, dirt6.getWidth(), dirt6.getHeight());
                        batch.draw(camp4, xPos, yPos + 18, camp4.getWidth() * 1.2f, camp4.getHeight() * 1.2f);
                        break;
                }
            }
        }


        batch.draw(fireIcon, WORLD_WIDTH - fireIcon.getWidth(), 0);
        batch.draw(iceIcon, WORLD_WIDTH - fireIcon.getWidth() - iceIcon.getWidth(), 0);
        batch.draw(thunderIcon, WORLD_WIDTH - fireIcon.getWidth() - iceIcon.getWidth() - thunderIcon.getWidth(), 0);
        batch.draw(poisonIcon, WORLD_WIDTH - fireIcon.getWidth() - iceIcon.getWidth() - thunderIcon.getWidth() - poisonIcon.getWidth(), 0);
        batch.draw(laserIcon, WORLD_WIDTH - fireIcon.getWidth() - iceIcon.getWidth() - thunderIcon.getWidth() - poisonIcon.getWidth() - laserIcon.getWidth(), 15);
        batch.draw(costBg, 1235, 0, 45, 20);
        batch.draw(costBg, 1190, 0, 45, 20);
        batch.draw(costBg, 1145, 0, 45, 20);
        batch.draw(costBg, 1100, 0, 45, 20);
        batch.draw(costBg, 1064, 0, 36, 20);

    }


    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {
        PauseMenu();
    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }


    @Override
    public void dispose() {
        batch.dispose();
        backgroundTexture.dispose();
        shapeRenderer.dispose();
        uiBatch.dispose();
        infoBgTexture.dispose();
        font.dispose();
        HPRenderer.dispose();
    }


    @Override
    public boolean keyDown(int keycode) {
        if (keycode == Input.Keys.P) {
            togglePause();
        }
        return false;
    }
    public void togglePause() {
        isPaused= !isPaused;
    }

    @Override
    public boolean keyUp(int keycode) {
        return false;
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    private int cost;

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        if (isPaused) {
            float touchX = screenX;
            float touchY = Gdx.graphics.getHeight() - screenY;

            if (touchX >= 480 && touchX <= 480 + buttonWidth &&
                    touchY >= 455 && touchY <= 455 + buttonHeight) {
                togglePause();
            } else if (touchX >= 480 && touchX <= 480 + buttonWidth &&
                    touchY >= 310 && touchY <= 310 + buttonHeight) {
                game.setScreen(new MenuScreen(batch, game));
            } else if (touchX >= 480 && touchX <= 480 + buttonWidth &&
                    touchY >= 155 && touchY <= 155 + buttonHeight) {
                Gdx.app.exit();
            }
        } else {
            Vector3 worldCoordinates = camera.unproject(new Vector3(screenX, screenY, 0));
            float x = worldCoordinates.x;
            float y = worldCoordinates.y;
            if (isClickOnFireIcon(x, y)) {
                nbr = 5;
                cost = 100;
                towerPlacementMode = true;
                setCursor("tower/flame/icon.png");
            } else if (isClickOnIceIcon(x, y)) {
                nbr = 6;
                cost = 130;
                towerPlacementMode = true;
                setCursor("tower/ice/icon.png");
            } else if (isClickOnThunderIcon(x, y)) {
                nbr = 7;
                cost = 150;
                towerPlacementMode = true;
                setCursor("tower/thunder/icon.png");
            } else if (isClickOnPoisonIcon(x, y)) {
                nbr = 8;
                cost = 140;
                towerPlacementMode = true;
                setCursor("tower/poison/icon.png");
            } else if (isClickOnLaserIcon(x, y)) {
                nbr = 9;
                cost = 150;
                towerPlacementMode = true;
                setLaserCursor("tower/laser/icon.png");
            } else if (towerPlacementMode) {
                if (player.getMoney() >= cost) {
                    placeTower(x, y, nbr);
                }
                towerPlacementMode = false;
                nbr = 0;
                cost = 0;
                resetCursor();
            } else if (isClickOnUpgradeIcon(x, y) && selectedTower != null) {
                if (player.getMoney() >= selectedTower.getCost()) {
                    player.loseMoney(selectedTower.getCost());
                    selectedTower.upgradeDamage();
                    selectedTower.upgradeRange();
                    selectedTower.upgradeSpeed();
                    selectedTower.upgradeLevel();
                    selectedTower.costIncrease();
                    System.out.println("Upgraded");
                }
                //selectedTower = null;
            } else if (isClickOnDeleteIcon(x, y) && selectedTower != null) {
                player.gainMoney(selectedTower.getCost() / 2);
                cancelTowerPlacement(selectedTower);
                selectedTower = null;
            } else {
                checkTowerSelection(x, y);
            }
            return true;
        }
        return false;
    }


    private void PauseMenu() {
        batch.draw(background1Texture, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        batch.draw(resumeButtonTexture, 480, 455, buttonWidth, buttonHeight);
        batch.draw(pauseButtonTexture, 480, 310, buttonWidth, buttonHeight);
        batch.draw(exitButtonTexture, 480, 155, buttonWidth, buttonHeight);
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchCancelled(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }

    public void placeTower(float x, float y, int nbr) {
        int gridX = (int) (x / (WORLD_WIDTH / map[0].length));
        int gridY = (int) ((WORLD_HEIGHT - y) / (WORLD_HEIGHT / map.length));

        if (isValidPlacement(gridX, gridY)) {
            map[gridY][gridX] = nbr;
            player.loseMoney(cost);
        }
    }

    private boolean isValidPlacement(int gridX, int gridY) {
        if (gridX >= 0 && gridX < map[0].length && gridY >= 0 && gridY < map.length) {
            return map[gridY][gridX] == 1;
        }
        return false;
    }

    @Override
    public boolean scrolled(float amountX, float amountY) {
        return false;
    }

    private boolean isClickOnFireIcon(float clickX, float clickY) {
        float iconX = WORLD_WIDTH - fireIcon.getWidth();
        float iconY = 0;
        float iconWidth = 45;
        float iconHeight = 77;
        return clickX >= iconX && clickX <= iconX + iconWidth && clickY >= iconY && clickY <= iconY + iconHeight;
    }

    private boolean isClickOnIceIcon(float clickX, float clickY) {
        float iconX = WORLD_WIDTH - fireIcon.getWidth() - iceIcon.getWidth();
        float iconY = 0;
        float iconWidth = 45;
        float iconHeight = 77;
        return clickX >= iconX && clickX <= iconX + iconWidth && clickY >= iconY && clickY <= iconY + iconHeight;
    }

    private boolean isClickOnThunderIcon(float clickX, float clickY) {
        float iconX = WORLD_WIDTH - fireIcon.getWidth() - iceIcon.getWidth() - thunderIcon.getWidth();
        float iconY = 0;
        float iconWidth = 45;
        float iconHeight = 72;
        return clickX >= iconX && clickX <= iconX + iconWidth && clickY >= iconY && clickY <= iconY + iconHeight;
    }

    private boolean isClickOnPoisonIcon(float clickX, float clickY) {
        float iconX = WORLD_WIDTH - fireIcon.getWidth() - iceIcon.getWidth() - thunderIcon.getWidth() - poisonIcon.getWidth();
        float iconY = 0;
        float iconWidth = 45;
        float iconHeight = 74;
        return clickX >= iconX && clickX <= iconX + iconWidth && clickY >= iconY && clickY <= iconY + iconHeight;
    }

    private boolean isClickOnLaserIcon(float clickX, float clickY) {
        float iconX = WORLD_WIDTH - fireIcon.getWidth() - iceIcon.getWidth() - thunderIcon.getWidth() - poisonIcon.getWidth() - laserIcon.getWidth();
        float iconY = 15;
        float iconWidth = 36;
        float iconHeight = 38;
        return clickX >= iconX && clickX <= iconX + iconWidth && clickY >= iconY && clickY <= iconY + iconHeight;
    }

    private boolean isClickOnUpgradeIcon(float clickX, float clickY) {
        float iconX = 155;
        float iconY = 48;
        float iconWidth = 30;
        float iconHeight = 30;
        return clickX >= iconX && clickX <= iconX + iconWidth && clickY >= iconY && clickY <= iconY + iconHeight;
    }

    private boolean isClickOnDeleteIcon(float clickX, float clickY) {
        float iconX = 155;
        float iconY = 18;
        float iconWidth = 30;
        float iconHeight = 30;
        return clickX >= iconX && clickX <= iconX + iconWidth && clickY >= iconY && clickY <= iconY + iconHeight;
    }

    private void checkTowerSelection(float x, float y) {
        int gridX = (int) (x / (WORLD_WIDTH / map[0].length));
        int gridY = (int) ((WORLD_HEIGHT - y) / (WORLD_HEIGHT / map.length));
        if (gridX >= 0 && gridX < map[0].length && gridY >= 0 && gridY < map.length) {
            if (map[gridY][gridX] == 5 || map[gridY][gridX] == 6 || map[gridY][gridX] == 7 || map[gridY][gridX] == 8 || map[gridY][gridX] == 9) {
                selectedTower = towerInGrid[gridY][gridX];
                selectedTower.showRadius(shapeRenderer);
            }
            else {
                selectedTower = null;
            }
        }
    }

    private void cancelTowerPlacement(ATower towerToRemove) {
        int gridX = (int) (towerToRemove.getPosition().x / (WORLD_WIDTH / map[0].length));
        int gridY = (int) ((WORLD_HEIGHT - towerToRemove.getPosition().y) / (WORLD_HEIGHT / map.length));

        if (gridX >= 0 && gridX < map[0].length && gridY >= 0 && gridY < map.length) {
            map[gridY][gridX] = 1;
            towers.remove(towerToRemove);
            towerInGrid[gridY][gridX] = null;
            selectedTower = null;
        }
    }

    private void drawInformationPanel() {
        float panelX = 10;
        float panelY = 10;
        uiBatch.draw(infoBgTexture, panelX, panelY, infoBgTexture.getWidth(), infoBgTexture.getHeight());
        font.draw(uiBatch, "100", 1245, 16);
        font.draw(uiBatch, "130", 1200, 16);
        font.draw(uiBatch, "150", 1155, 16);
        font.draw(uiBatch, "140", 1110, 16);
        font.draw(uiBatch, "150", 1070, 16);
        if (selectedTower != null) {
            font.draw(uiBatch, "Level: " + selectedTower.getLevel(), 35 , infoBgTexture.getHeight() - 5);
            font.draw(uiBatch, "Damage: " + selectedTower.getDamage(), 35, infoBgTexture.getHeight() - 25);
            font.draw(uiBatch, "Range: " + selectedTower.getRange(), 35, infoBgTexture.getHeight() - 45);
            font.draw(uiBatch,"Speed: " + selectedTower.getSpeed(), 35, infoBgTexture.getHeight() - 65);
            font.draw(uiBatch, "Balance: " + player.getMoney(), 160, infoBgTexture.getHeight() - 5);

            float iconWidth = 30;
            float iconHeight = 30;
            uiBatch.draw(upgradeButtonTexture, 155, 48, iconWidth, iconHeight);
            font.draw(uiBatch, "Cost: " + selectedTower.getCost(), 190, infoBgTexture.getHeight() - 35);

            uiBatch.draw(deleteButtonTexture, 155, 18, iconWidth, iconHeight);
            font.draw(uiBatch, "Gain: " + selectedTower.getCost() / 2, 190, infoBgTexture.getHeight() - 65);
        }
        else {
            font.draw(uiBatch, "No tower selected", 85 , infoBgTexture.getHeight() - 20);
            font.draw(uiBatch, "Current balance: " + player.getMoney(), 80, infoBgTexture.getHeight() - 50);
        }
    }

    private void setCursor(String file) {
        int newWidth = 32;
        int newHeight = 64;
        Pixmap originalPixmap = new Pixmap(Gdx.files.internal(file));
        Pixmap resizedPixmap = new Pixmap(newWidth, newHeight, originalPixmap.getFormat());
        resizedPixmap.drawPixmap(originalPixmap, 0, 0, originalPixmap.getWidth(), originalPixmap.getHeight(), 0, 0, newWidth, newHeight);
        Texture cursorTexture = new Texture(resizedPixmap);
        Gdx.graphics.setCursor(Gdx.graphics.newCursor(resizedPixmap, 16, 32));
        originalPixmap.dispose();
        resizedPixmap.dispose();
        cursorTexture.dispose();
    }

    private void setLaserCursor(String file) {
        int newWidth = 32;
        int newHeight = 32;
        Pixmap originalPixmap = new Pixmap(Gdx.files.internal(file));
        Pixmap resizedPixmap = new Pixmap(newWidth, newHeight, originalPixmap.getFormat());
        resizedPixmap.drawPixmap(originalPixmap, 0, 0, originalPixmap.getWidth(), originalPixmap.getHeight(), 0, 0, newWidth, newHeight);
        Texture cursorTexture = new Texture(resizedPixmap);
        Gdx.graphics.setCursor(Gdx.graphics.newCursor(resizedPixmap, 16, 16));
        originalPixmap.dispose();
        resizedPixmap.dispose();
        cursorTexture.dispose();
    }

    private void resetCursor() {
        Pixmap cursorPixmap = new Pixmap(Gdx.files.internal("cursor.png"));
        Gdx.graphics.setCursor(Gdx.graphics.newCursor(cursorPixmap, 0, 0));
        cursorPixmap.dispose();
    }

    public void renderCastleHealthBar(SpriteBatch batch, ShapeRenderer shapeRenderer) {
            batch.begin();
            float healthBarWidth = 55;
            float healthBarHeight = 5;

            float x = 1220;
            float y = 310;

            float healthPercentage = (float) player.getHp() / player.getMaxHP();

            shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
            shapeRenderer.setColor(Color.RED);
            shapeRenderer.rect(x, y, healthBarWidth, healthBarHeight);

            shapeRenderer.setColor(Color.GREEN);
            shapeRenderer.rect(x, y, healthBarWidth * healthPercentage, healthBarHeight);
            shapeRenderer.end();
            batch.end();
    }

}