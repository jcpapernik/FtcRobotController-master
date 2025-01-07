package org.firstinspires.ftc.teamcode.JACOBcode;

public class Arrow {
    private final int xTail;
    private final int yTail;
    private final int xHead;
    private final int yHead;
    private final int ID;
    private final boolean isLearned;

    public Arrow(int xTail, int yTail, int xHead, int yHead, int ID){
        this.xTail = xTail;
        this.yTail = yTail;
        this.xHead = xHead;
        this.yHead = yHead;
        this.ID = ID;
        this.isLearned = ID > 0;
    }

    public int getXTail(){
        return this.xTail;
    }

    public int getYTail(){
        return this.yTail;
    }

    public int getXHead(){
        return this.xHead;
    }

    public int getYHead(){
        return this.yHead;
    }

    public int getId(){
        return this.ID;
    }

    public boolean getIsLearned(){
        return this.isLearned;
    }
}
