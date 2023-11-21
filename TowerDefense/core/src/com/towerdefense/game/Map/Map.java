package com.towerdefense.game.Map;

import com.badlogic.gdx.math.Vector2;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

public class Map{
    protected int[][] map;
    protected float cellWidth;
    protected float cellHeight;
    protected ArrayList<Vector2> waypoints;
    protected Vector2 endpoint;
    public Map(){
        waypoints=new ArrayList<Vector2>();
    }

    public void findPath() {
        ArrayList<Point> points = new ArrayList<>();
        Point start = null;
        Point nearestPoint = null;


        for (int y = 0; y < map.length; y++) {
            for (int x = 0; x < map[y].length; x++) {
                if (map[y][x] == 4 || map[y][x] == 14) {
                    float cellWidth = 1280 / (float) map[y].length;
                    float cellHeight = 720 / (float) map.length;
                    Vector2 waypoint = new Vector2((x * cellWidth + cellWidth / 2)-21, modifyFloat(y * cellHeight + cellHeight / 2)-15);
                    waypoints.add(waypoint);
                    points.add(new Point(x, y));
                    start = new Point(x, y);
                }
            }
        }

        while (start != null) {
            float minDistance = Float.MAX_VALUE;
            nearestPoint = null;

            for (int y = 0; y < map.length; y++) {
                for (int x = 0; x < map[y].length; x++) {
                    if (map[y][x] == 2 && !points.contains(new Point(x, y)) || map[y][x] == 12 &&!points.contains(new Point(x,y))
                            || map[y][x] == 13 &&!points.contains(new Point(x,y)) || map[y][x] == 15 &&!points.contains(new Point(x,y))
                            || map[y][x] == 16 &&!points.contains(new Point(x,y)) || map[y][x] == 17 &&!points.contains(new Point(x,y))) {
                        float cellWidth = 1280 / (float) map[y].length;
                        float cellHeight = 720 / (float) map.length;
                        float distance = calculateDistance(new Vector2(start.getX() * cellWidth + cellWidth / 2, start.getY() * cellHeight + cellHeight / 2),
                                new Vector2(x * cellWidth + cellWidth / 2, y * cellHeight + cellHeight / 2));
                        if (distance < minDistance) {
                            minDistance = distance;
                            nearestPoint = new Point(x, y);
                        }
                    }
                }
            }

            if (nearestPoint != null) {
                float cellWidth = 1280 / (float) map[nearestPoint.getY()].length;
                float cellHeight = 720 / (float) map.length;
                waypoints.add(new Vector2((nearestPoint.getX() * cellWidth + cellWidth / 2)-21, modifyFloat(nearestPoint.getY() * cellHeight + cellHeight / 2)-15));
                points.add(nearestPoint);
                start = nearestPoint;
            } else {
                start = null;
            }
        }

        points = new ArrayList<Point>();
        start = null;
        nearestPoint = null;
        float minDistance = Float.MAX_VALUE;


        for (int y = 0; y < map.length; y++) {
            for (int x = 0; x < map[y].length; x++) {
                if (map[y][x] == 3) {
                    float cellWidth = 1280 / (float) map[y].length;
                    float cellHeight = 720 / (float) map.length;
                    endpoint = new Vector2((x * cellWidth + cellWidth / 2)-21, modifyFloat(y * cellHeight + cellHeight / 2)-15);
                    waypoints.add(endpoint);
                }
            }
        }
    }

    public static float modifyFloat(float input) {
        return 720.0f - input;
    }


    private float calculateDistance(Vector2 point1, Vector2 point2) {
        return (float) Math.sqrt(Math.pow(point2.x - point1.x, 2) + Math.pow(point2.y - point1.y, 2));
    }


    public Vector2 getVectorForCoordinates(int x, int y) {
        return new Vector2(x * cellWidth + cellWidth / 2, y * cellHeight + cellHeight / 2);
    }

    public int[][] getMap() {
        return map;
    }

    public ArrayList<Vector2> getWaypoints() {
        return waypoints;
    }
}