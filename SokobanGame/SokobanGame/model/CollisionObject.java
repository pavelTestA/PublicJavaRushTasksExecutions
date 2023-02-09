package com.javarush.task.task24.SokobanGame.SokobanGame.model;

public abstract class CollisionObject extends GameObject{
    public CollisionObject(int x, int y) {
        super(x, y);
    }

    public boolean isCollision(GameObject gameObject, Direction direction){
        int newX = this.getX();
        int newY = this.getY();
        switch (direction){
            case UP: newY -= Model.FIELD_CELL_SIZE; break;
            case DOWN: newY += Model.FIELD_CELL_SIZE; break;
            case LEFT: newX -= Model.FIELD_CELL_SIZE; break;
            case RIGHT: newX += Model.FIELD_CELL_SIZE; break;
            default: return false;
        }

        return gameObject.getX()==newX && gameObject.getY()==newY;
    }
}
