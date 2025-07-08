package domain;

import java.util.ArrayList;

public class Board {

    private Phase phaseOne;
    private Phase phaseTwo;
    private Phase phaseThree;
    private Double averagePhases;
    private Double maxPhasesValue;
    private Double totalPercentage;
    private Double valuePower;
    private String colorTotalPercentage;
    private Double [][] board;

    public Board(){
        this.phaseOne = new Phase(1);
        this.phaseTwo = new Phase(2);
        this.phaseThree = new Phase(3);
        this.averagePhases = 0.0;
        this.maxPhasesValue = 0.0;
        this.valuePower = 0.0;
        this.colorTotalPercentage ="White";
        this.board =  new Double[46][9];
        setValues();
    }

    public Double [][] getBoard(){
        return this.board;
    }

    public Double getValues(int posx, int posy){
        Double value = board[posx][posy];
        return value;
    }

    public String getColorTotalPercentage(){
        return colorTotalPercentage;
    }

    public void changeValue(int posx, int posy,Double newValue){
        if(posy == 2){
            phaseOne.changeValue(posx,posy,newValue);
        }
        if(posy == 3){
            phaseTwo.changeValue(posx,posy,newValue);
        }
        if(posy == 4){
            phaseThree.changeValue(posx,posy,newValue);
        }
        updateBoard();
    }

    public void calculateValuePower(Double valueTocalulatePower){
        this.valuePower = valueTocalulatePower;
        setValuePower();
    }

     public void updateBoard(){
        setValuesPhases();
        setSumPhases();
        setPercentagePhases();
        setMaxValue();
        setAveragePhases();
        setTotalPercentage();
        setSumCharge();
        setValuePower();
        setColor();
    }

    public void setValues(){
        setColumn0();
        setColumn1();
        setValuesPhases();
        setSumPhases();
        setPercentagePhases();
        setMaxValue();
        setAveragePhases();
        setTotalPercentage();
        setSumCharge();
        setValuePower();
        setColor();
    }

    public void setColumn0(){
        int contadori = 0;
        int contadorj = 21;
        while( contadori < 21){
          for(int j = 1;j<=42;j+=1){
            if(j%2==1){
                board[contadori][0] = Double.valueOf(j);
                contadori+=1;
            }
          }   
        }

        while( contadorj < 42){
          for(int j = 1;j<=42;j+=1){
            if(j%2==0){
                board[contadorj][0] = Double.valueOf(j);
                contadorj+=1;
            }
          }   
        }
    }

    public void setColumn1(){
        for(int i =0; i <42;i+=3){
            Double cont = 1.0;
            for(int j = i; j < 3+i;j+=1){
                board[j][1] = cont;
                cont += 1.0;
            }
        }
    }

    public void setValuesPhases(){
        ArrayList<Data> valuesPhase1 = phaseOne.getData();
        ArrayList<Data> valuesPhase2 = phaseTwo.getData();
        ArrayList<Data> valuesPhase3 = phaseThree.getData();

        for(int i = 0;i<valuesPhase1.size();i+=1){
            Data data = valuesPhase1.get(i);
            Double value = data.getValue();
            int x = data.getPosX();
            int y = data.getPosY();
            board[x][y] = value;
            board[x][y+3] = round(value);
            board[x][y+6] = round(value);
        }
        for(int i = 0;i<valuesPhase2.size();i+=1){
            Data data = valuesPhase2.get(i);
            Double value = data.getValue();
            int x = data.getPosX();
            int y = data.getPosY();
            board[x][y] = value;
            board[x][y+3] = round(value);
            board[x][y+5] = round(value);
        }
        for(int i = 0;i<valuesPhase3.size();i+=1){
            Data data = valuesPhase3.get(i);
            Double value = data.getValue();
            int x = data.getPosX();
            int y = data.getPosY();
            board[x][y] = value;
            board[x][y+3] = round(value);
            board[x][y+4] = round(value);
        }
    }

    public void setSumPhases(){
        board[42][5] = round(phaseOne.getSumPhase());
        board[42][6] = round(phaseTwo.getSumPhase());
        board[42][7] = round(phaseThree.getSumPhase());
        board[43][2] = phaseOne.getSumPhase();
        board[43][3] = phaseTwo.getSumPhase();
        board[43][4] = phaseThree.getSumPhase();
        board[45][4] = Math.round(((phaseOne.getSumPhase()+phaseTwo.getSumPhase()+phaseThree.getSumPhase())/1000)*100000.0) / 100000.0;
    }

    public void setPercentagePhases(){
        phaseOne.setPercentagePhase(this.phaseTwo.getSumPhase());
        phaseTwo.setPercentagePhase(this.phaseThree.getSumPhase());
        phaseThree.setPercentagePhase(this.phaseOne.getSumPhase());
        board[43][5] = Round(phaseOne.getPercentagePhase());
        board[43][6] = Round(phaseTwo.getPercentagePhase());
        board[43][7] = Round(phaseThree.getPercentagePhase());
    }

    public void setSumCharge(){
        board[45][8] = round(phaseOne.getSumPhase()+phaseTwo.getSumPhase()+phaseThree.getSumPhase());
    }

    public Phase getPhase(int id){
        Phase phase = null;
        if(id == 1){
            phase = this.phaseOne;
        }
        if(id == 2){
            phase = this.phaseTwo;
        }
        if(id == 3){
            phase = this.phaseThree;
        }
        return phase;
    }

    private void setMaxValue(){
        Double sumPhaseOne = phaseOne.getSumPhase();
        Double sumPhaseTwo = phaseTwo.getSumPhase();
        Double sumPhaseThree = phaseThree.getSumPhase();
        maxPhasesValue = sumPhaseOne;
        if(sumPhaseTwo > maxPhasesValue){
            maxPhasesValue = sumPhaseTwo;
        }
        if (sumPhaseThree > maxPhasesValue ){
            maxPhasesValue = sumPhaseThree;
        }
        board[45][5] = round(maxPhasesValue); 
    }

    private void setAveragePhases(){
        Double sumPhaseOne = phaseOne.getSumPhase();
        Double sumPhaseTwo = phaseTwo.getSumPhase();
        Double sumPhaseThree = phaseThree.getSumPhase();
        averagePhases = Math.abs((sumPhaseOne+sumPhaseTwo+sumPhaseThree)/3);
        board[45][6]= round(averagePhases);
    }

    private void setTotalPercentage(){
        totalPercentage = Round(Math.abs((maxPhasesValue-averagePhases)/averagePhases) * 100);
        board[45][7]= totalPercentage;
    }

    private void setValuePower(){
        this.valuePower = Math.round((this.valuePower * board[45][4]) * 100000.0) / 100000.0;
        board[45][2] = this.valuePower;
    }

    private void setColor(){
        if(totalPercentage <= 5.0){
            colorTotalPercentage = "Green";
        }
        if(totalPercentage > 5.0){
            colorTotalPercentage = "Red";
        }
    }

    private Double round(Double numero) {  
        return Math.round(numero * 1.0)/1.0;
    }

    private Double Round(Double numero) {  
        return Math.round(numero * 100.0)/100.0;
    }
}
