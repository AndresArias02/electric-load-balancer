package domain;
import java.util.ArrayList;
import java.util.List;

public class Phase {
    private ArrayList<Data> valuesPhase = new ArrayList<Data>();
    private int id;
    private Double sumPhase;
    private Double percentagePhase;
    private String colorPhase;


    public Phase(int id){
        this.id = id; 
        sumPhase = 0.0;
        colorPhase = "White";
        percentagePhase = 0.0;
        setValuesPhase();
    }

    public void changeValue(int posx,int posy,Double newValue){
        Data data = getSpecificData(posx,posy);
        valuesPhase.remove(data);
        data.changeValue(newValue);
        valuesPhase.add(data);
    }

    public Double getPercentagePhase(){
        return this. percentagePhase;
    }

    public void setPercentagePhase(Double sumOtherPhase){
        Double max = Math.max(sumPhase, sumOtherPhase);
        Double min = Math.min(sumPhase, sumOtherPhase);
        percentagePhase = round(Math.abs((max-min)/max)*100);
        setColor();
    }

    public String getColorPhase(){
        return this.colorPhase;
    }

    public Double getSumPhase(){
        setSumPhase();
        return sumPhase;
    }

    public void setSumPhase(){
        Double cont = valuesPhase.get(0).getValue();
        for(int i =1;i< valuesPhase.size();i+=1){
            Double data = valuesPhase.get(i).getValue();
            cont = cont + data;
        }
        sumPhase = cont;
    }

    public ArrayList<Data> getData(){
        return valuesPhase;
    }

    public Data getSpecificData(int posx,int posy){
        Data dataR = null;
         for(int i =0;i< valuesPhase.size();i+=1){
            Data data = valuesPhase.get(i);
            int x = data.getPosX();
            int y = data.getPosY();
            if(posx == x && posy == y){
                dataR = data;
            }
        }
        return dataR;
    }

    public Double getValueOfData(int posx,int posy){
        Double Value = null;  
        for(int i = 0;i < valuesPhase.size();i+=1){
            Data d = valuesPhase.get(i);
            int x = d.getPosX();
            int y = d.getPosY();
            if(posx == x && posy == y ){
                Value = d.getValue();
            } 
        }
        return Value;
    }

    private void setColor(){
        if(percentagePhase <= 5.0){
            colorPhase = "Green";
        }
        if(percentagePhase > 5.0){
            colorPhase = "Red";
        }
    }

    private void setValuesPhase(){
        if(id == 1){
            for(int i = 0;i < 42;i+=3){
                valuesPhase.add(new Data(i, 2, id));
            }
        }
        if(id == 2){
            for(int i = 1;i < 42;i+=3){
                valuesPhase.add(new Data(i, 3, id));
            }
        }   
        if(id == 3){
            for(int i = 2;i < 42;i+=3){
                valuesPhase.add(new Data(i, 4, id));
            }
        }       
    }

    private Double round(Double numero) {  
        return Math.round(numero * 100.0)/100.0;
    }
}
