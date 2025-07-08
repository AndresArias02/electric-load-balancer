package domain;

public class Data {
    
    private int posX;
    private int posY;
    private int phaseID;
    private Double value;

    public Data(int posX,int posY,int phaseID){
        this.phaseID = phaseID;
        this.posX = posX;
        this.posY = posY;
        this.value = 0.0;
    }

    public void changeValue(Double newValue){
        this.value = newValue;
    }

    public Double getValue(){
        return this.value;
    }

    public void setPosX(int x){
        this.posX = x;
    }

    public void setPosY(int y){
        this.posY = y;
    }

    public int getPosX(){
        return this.posX;
    }

    public int getPosY(){
        return this.posY;
    }
}
