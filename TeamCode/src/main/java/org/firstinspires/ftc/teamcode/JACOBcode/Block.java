package org.firstinspires.ftc.teamcode.JACOBcode;
public class Block {
    private int xPositio;
    private final int yPosition;
    private final int width;
    private final int height;
    private final int ID;
    private final boolean isLearned;

    public Block(int xPosition, int yPosition, int width, int height, int ID){
        this.xPosition = xPosition;
        this.yPosition = yPosition;
        this.width = width;
        this.height = height;
        this.ID = ID;
        this.isLearned = ID > 0;
    }

    public int getXPosition(){
        return this.xPosition;
    }

    public int getYPosition(){
        return this.yPosition;
    }

    public int getWidth(){
        return this.width;
    }

    public int getHeight(){
        return this.height;
    }

    public int getId(){
        return this.ID;
    }

    public boolean getIsLearned(){
        return this.isLearned;
    }
}
