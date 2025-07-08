package presentation;

import javax.print.DocFlavor.STRING;
import javax.swing.*;
import javax.swing.border.TitledBorder;

import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.FileInputStream;

import javax.swing.filechooser.FileNameExtensionFilter;

import domain.*;

public class ElectricChargeBalanceGUI extends JFrame{

    private Paint printer;
    private JMenuBar menu;
    private JMenu options;
    private JFileChooser filesChooser;
    private JMenuItem openFile, saveFile;
    private JButton change,calculate;
    private JPanel background,information,grafic;
    private JPanel board;
    private JTextField valueBoard,valuePower;
    private JLabel lValue,lInstructions,lProgramName,lCompanyLogo,lValuePower;
    private JLabel [][] textBoard;
    private JButton [][] buttonsBoard;
    private ElectricChargeBalance program;
    private Integer posX = 0; 
    private Integer posY = 0;
    private Integer x = null; 
    private Integer y = null;  

    public ElectricChargeBalanceGUI(){
        super.setTitle("Balance de Cargas - Senerging S.A.S");
        setBounds(0, 0, 1250, 700);
        setLocationRelativeTo(null);
        setResizable(false);
        program = new ElectricChargeBalance();
        printer = Paint.getPrinter();
        prepareElements();
        prepareActions();
    }

    public void prepareElements(){
        background = new JPanel();
        background.setLayout(null);
        background.setBackground(Color.WHITE);
        prepareElementsMenu();
        prepareElementsBoard();
        PrepareElemenetsProgram();
        add(background);
    }

    public void prepareActions(){
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
            	actionClose();
            }
        });
        prepareActionsMenu();
    }

    public void prepareElementsMenu(){
        menu = new JMenuBar();
        options = new JMenu("Opciones");
        openFile = new JMenuItem("Abrir");
        saveFile = new JMenuItem("Guardar");
        filesChooser = new JFileChooser();
        options.add(openFile);
        options.add(saveFile);
        menu.add(options);
        setJMenuBar(menu);
    }

    public void prepareElementsBoard(){
        board = new JPanel(new GridLayout(47, 9, 0, 0));
        board.setBackground(Color.WHITE);
        board.setBounds(10, 0, 750, 650);
        textBoard = new JLabel[47][9];
        buttonsBoard = new JButton[47][9];
        createBoardText();
    }

    public void PrepareElemenetsProgram(){
    	lProgramName =  new JLabel("BALANCE DE CARGAS");
    	lCompanyLogo = new JLabel();
    	lInstructions = new JLabel("<html><font size=\"5\"><i>Seleccione alguna de las casillas grises a la que desea cambiarle el valor y escriba el valor nuevo, si es un decimal escribalo con punto.</i></font></html>");
    	lValue = new JLabel("<html><font size=\"5\"><i>Ingrese el valor nuevo: </i></font></html>");
    	valueBoard = new JTextField();
    	change = new JButton("Cambiar");
    	lValuePower = new JLabel("<html><font size=\"5\"><i>Ingrese el valor para calcular la potencia: </i></font></html>");
    	valuePower = new JTextField();
    	calculate = new JButton("Calcular");
        information = new JPanel();
        information.setBackground(Color.LIGHT_GRAY);
        information.setBounds(770, 0, 470, 320);
        grafic = new JPanel();
        grafic.setBackground(Color.LIGHT_GRAY);
        grafic.setBounds(770,330,470,320);
        /*background.add(grafic);*/
        createElementsProgram();
    }

    public void createElementsProgram(){
        lProgramName.setFont(new Font("Arial", Font.BOLD, 20));
        lProgramName.setHorizontalAlignment(SwingConstants.CENTER);
        lProgramName.setBounds(865,100 ,260 ,50);
        
        printer.pintarImagen(lCompanyLogo,"img/Logo/logo.png", 260, 120);
        lCompanyLogo.setBounds(865,5,260,100);
        
        lInstructions.setBounds(770,150,470,65);
        
        lValue.setBounds(770,215,220,52);
        
        valueBoard.setHorizontalAlignment(SwingConstants.CENTER);
        Font fontValueBoard = valueBoard.getFont();
        Font newFontValueBoard = fontValueBoard.deriveFont(fontValueBoard.getSize() + 8f);
        valueBoard.setFont(newFontValueBoard);
        valueBoard.setBorder(BorderFactory.createLineBorder(Color.BLACK,2));
        valueBoard.setBounds(990,226,100,30);
        valueBoard.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                actionChangeValue();
            }
        });
        
        change.addActionListener(e -> actionChangeValue());
        change.setFont(new Font("Arial", Font.ITALIC, 20));
        change.setBackground(Color.LIGHT_GRAY);
        change.setOpaque(true);
        change.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
        change.setBounds(1120,226,100,30);
        
        lValuePower.setBounds(770,267,220,52);
        
        valuePower.setHorizontalAlignment(SwingConstants.CENTER);
        Font fontValuePower = valuePower.getFont();
        Font newFontValuePower = fontValuePower.deriveFont(fontValuePower.getSize() + 8f);
        valuePower.setFont(newFontValuePower);
        valuePower.setBorder(BorderFactory.createLineBorder(Color.BLACK,2));
        valuePower.setBounds(990,278,100,30);
        valuePower.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                actionCalculateValuePower();
            }
        });
        
        calculate.addActionListener(e -> actionCalculateValuePower());
        calculate.setFont(new Font("Arial", Font.ITALIC, 20));
        calculate.setBackground(Color.LIGHT_GRAY);
        calculate.setOpaque(true);
        calculate.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
        calculate.setBounds(1120,278,100,30);
        
        
        background.add(lProgramName);
        background.add(lCompanyLogo);
        background.add(lInstructions);
        background.add(lValue);
        background.add(valueBoard);
        background.add(change);
        background.add(lValuePower);
        background.add(valuePower);
        background.add(calculate);
        /*background.add(information);*/
    }

    public void createBoardText(){
        Double[][] boardGUI = program.getBoard();
        for(int i = 1; i <= 46;i+=1){
            for(int j = 0; j < 9;j+=1){
                Double realValue = boardGUI[i-1][j];
                JButton button = new JButton();
                JLabel box = new JLabel();
                box.setBorder(BorderFactory.createLineBorder(Color.BLACK));
                box.setHorizontalAlignment(SwingConstants.CENTER);
                int posX = i;
                int posY = j;
                button.addActionListener(e -> actionSaveButtonPosition(posX, posY));
                if(realValue != null){
                    if(j!= 2 && j!= 3 && j!= 4 && j!= 1){
                        box.setText(String.valueOf(realValue));
                        textBoard[i][j] = box;
                    }
                    if(j == 1){
                        if(realValue == 1.0){
                            box.setText("R");
                            textBoard[i][j] = box; 
                        }
                        if(realValue == 2.0){
                            box.setText("S");
                            textBoard[i][j] = box; 
                        }
                        if(realValue == 3.0){
                            box.setText("T");
                            textBoard[i][j] = box; 
                        }
                    }
                    if((j == 2 || j == 3 || j == 4) && i < 43){
                        box.setText(String.valueOf(realValue));
                        box.setBackground(Color.LIGHT_GRAY);
                        box.setOpaque(true);
                        button.setBorder(null);
                        button.setContentAreaFilled(false);
                        /*button.setBorderPainted(false)*/;
                        box.setLayout(new BorderLayout());
                        box.add(button, BorderLayout.CENTER);
                        textBoard[i][j] = box;
                        buttonsBoard[i][j] = button;
                    }
                    if((j == 2 || j == 3 || j == 4) && i > 43){
                        box.setText(String.valueOf(realValue));
                        textBoard[i][j] = box;
                    }
                }
                else{
                    box.setText("");
                    textBoard[i][j] = box;
                }
            }
        }
        for(int j = 5; j < 8;j+=1){
            JLabel box = textBoard[44][j];
            box.setText(box.getText()+"%");
            textBoard[44][j] = box;
        }
        JLabel totalPercentage = textBoard[46][7];
        totalPercentage.setText(totalPercentage.getText()+"%");
        textBoard[46][7] = totalPercentage;
        
        setTitles();
        createBoard();
    }

    public void setTitles(){
        String[] titles = {"#CIRCUITO","FASE","FASE R (A)","FASE S (A)","FASE T (A)","R","S","T","V-CARGA","Pm","Ped","D%","TOTAL","SUMT-FR",
                            "SUMT-FS","SUMT-FT","CARGA","SV-CARGA ","V-POTENCIA"};
        for(int j = 0; j < 9;j+=1){
            String texto = titles[j];
            JLabel box = new JLabel(texto);
            box.setBorder(BorderFactory.createLineBorder(Color.BLACK));
            box.setHorizontalAlignment(SwingConstants.CENTER);
            if(j== 5){
                box.setBackground(Color.YELLOW);
            }
            if(j== 6){
                box.setBackground(Color.CYAN);
            }
            if(j== 7){
                box.setBackground(Color.RED);
            }
            if(j!= 5 && j!= 6 && j!= 7){
                box.setBackground(Color.ORANGE);
            }
            box.setOpaque(true);
            textBoard[0][j] = box;
        }
        for(int j= 5; j < 8;j+=1){
            String texto = titles[j+4];
            JLabel box = textBoard[45][j];
            box.setText(texto);
            box.setBackground(Color.ORANGE);
            box.setOpaque(true);
            textBoard[45][j] = box;
        }
        for(int i = 43;i < 47; i+= 1){
            String texto = titles[12];
            JLabel box = textBoard[i][0];
            box.setText(texto);
            box.setBackground(Color.LIGHT_GRAY);
            box.setOpaque(true);
            textBoard[i][0] = box;
        }
        for(int j = 2; j < 5;j+= 1){
            String texto = titles[j+11];
            JLabel box = textBoard[43][j];
            box.setText(texto);
            box.setBackground(Color.ORANGE);
            box.setOpaque(true);
            textBoard[43][j] = box;
        }

        JLabel carga = textBoard[45][4];
        carga.setText(titles[16]);
        carga.setBackground(Color.ORANGE);
        carga.setOpaque(true);
        textBoard[45][4] = carga;

        JLabel sumVC = textBoard[45][8];
        sumVC.setText(titles[17]);
        sumVC.setBackground(Color.ORANGE);
        sumVC.setOpaque(true);
        textBoard[45][8] = sumVC;

        JLabel vPower = textBoard[45][2];
        vPower.setText(titles[18]);
        vPower.setBackground(Color.ORANGE);
        vPower.setOpaque(true);
        textBoard[45][2]= vPower;
        
        JLabel sumPhaseOne = textBoard[43][5];
        sumPhaseOne.setBackground(Color.YELLOW);
        sumPhaseOne.setOpaque(true);
        textBoard[43][5] = sumPhaseOne;
        
        JLabel sumPhaseTwo = textBoard[43][6];
        sumPhaseTwo.setBackground(Color.CYAN);
        sumPhaseTwo.setOpaque(true);
        textBoard[43][6] = sumPhaseTwo;
        
        JLabel sumPhaseTree = textBoard[43][7];
        sumPhaseTree.setBackground(Color.RED);
        sumPhaseTree.setOpaque(true);
        textBoard[43][7] = sumPhaseTree;
        

    }

    public void createBoard(){
        for(int i = 0;i<47;i+=1 ){
            for(int j = 0; j<9;j+=1 ){
                JLabel label = textBoard[i][j];
                board.add(label);
            }
        }
        background.add(board);
    }

    public void actionSaveButtonPosition(int x, int y){
    	this.x = this.posX;
    	this.y = this.posY;
        this.posX = x;
        this.posY = y;
        JLabel selected = textBoard[this.x][this.y];
        JLabel newSelected = textBoard[this.posX][this.posY];
    	if(this.x!=0 && this.y!=0){
    		if(Double.parseDouble(textBoard[this.x][this.y].getText()) == 0.0) {
				selected.setBackground(Color.LIGHT_GRAY);
				selected.setOpaque(true);
				textBoard[this.x][this.y] = selected;
    		}
    		else if (this.y == 2) {
    			selected.setBackground(Color.YELLOW);
    			selected.setOpaque(true);
    			textBoard[this.x][this.y] = selected;
    		}
    		else if (this.y == 3) {
    			selected.setBackground(Color.CYAN);
    			selected.setOpaque(true);
    			textBoard[this.x][this.y] = selected;
    		}
    		else if(this.y == 4) {
    			selected.setBackground(Color.RED);
    			selected.setOpaque(true);
    			textBoard[this.x][this.y] = selected;
    		}
        }
        newSelected.setBackground(Color.MAGENTA);
        newSelected.setOpaque(true);
        textBoard[this.posX][this.posY] = newSelected;  
    }
    

    public void actionChangeValue(){
        String realValue = this.valueBoard.getText();
        Integer x = this.posX;
        Integer y = this.posY;
        if( x == 0 && y == 0){
            JOptionPane.showMessageDialog(null, "Debes seleccionar una casilla","Alerta!",1);
        }
        else if(realValue == null || realValue.isEmpty()) {
            JOptionPane.showMessageDialog(null, "El campo de texto está vacío","Alerta",2);
        }
        else if(!isCovertibleToDouble(realValue)){
            JOptionPane.showMessageDialog(null, "La entrada no es un número válido","Alerta",3);
            this.valueBoard.setText("");
        }
        if(x != null && y != null && realValue != null && !realValue.isEmpty() && isCovertibleToDouble(realValue)){
            program.changeValue(x-1, y, realValue);
            this.valueBoard.setText("");
            refresh();
        }
    }
    
    public void actionCalculateValuePower() {
    	String valuePower = this.valuePower.getText();
    	 if(valuePower == null || valuePower.isEmpty()) {
             JOptionPane.showMessageDialog(null, "El campo de texto está vacío","Alerta",2);
         }
         else if(!isCovertibleToDouble(valuePower)){
             JOptionPane.showMessageDialog(null, "La entrada no es un número válido","Alerta",3);
             this.valuePower.setText("");
         }
         if(valuePower != null && !valuePower.isEmpty() && isCovertibleToDouble(valuePower)){
             program.calculateValuePower(valuePower);
             this.valuePower.setText("");
             refresh();
         }
    }


    public boolean isCovertibleToDouble(String text){
        boolean bandera  = false;
        try {
            Double valor = Double.parseDouble(text);
            bandera = true;
        } catch (NumberFormatException e) {
            bandera = false;
        }
        return bandera;
    }

    public void refresh(){
    Double[][] updatedBoardGUI = program.getBoard();
    for(int i = 1;i <= 46;i+=1){
        for(int j = 2;j<9;j+=1){
            Double realValue = updatedBoardGUI[i-1][j];
            JLabel box = textBoard[i][j];
            if(realValue != null && !box.getText().isEmpty()){
                box.setText(String.valueOf(realValue));
                textBoard[i][j] = box;
            }
        }
    }
    for(int j = 5; j < 8;j+=1){
        JLabel box = textBoard[44][j];
        box.setText(box.getText()+"%");
        textBoard[44][j] = box;
    }
    JLabel totalPercentage = textBoard[46][7];
    totalPercentage.setText(totalPercentage.getText()+"%");
    textBoard[46][7] = totalPercentage; 
    setColorsBoard();  
    }

    public void setColorsBoard(){
        for(int i = 1;i<43;i+=1){
            for(int j = 2;j<5;j+=1){
                JLabel box = textBoard[i][j];
                JLabel boxOne = null;
                JLabel boxTwo = null;
                JLabel boxTree = null;
                if(box.getText()!= "" && Double.parseDouble(box.getText())!= 0.0){
                    if(j == 2){
                        boxOne = textBoard[i][0];
                        boxTwo = textBoard[i][1];
                        boxTree = textBoard[i][5];
                        boxOne.setBackground(Color.YELLOW);
                        boxOne.setOpaque(true);
                        boxTwo.setBackground(Color.YELLOW);
                        boxTwo.setOpaque(true);
                        boxTree.setBackground(Color.YELLOW);
                        boxTree.setOpaque(true);
                        box.setBackground(Color.YELLOW);
                        box.setOpaque(true);
                    }
                    if(j == 3){
                        boxOne = textBoard[i][0];
                        boxTwo = textBoard[i][1];
                        boxTree = textBoard[i][6];
                        boxOne.setBackground(Color.CYAN);
                        boxOne.setOpaque(true);
                        boxTwo.setBackground(Color.CYAN);
                        boxTwo.setOpaque(true);
                        boxTree.setBackground(Color.CYAN);
                        boxTree.setOpaque(true);
                        box.setBackground(Color.CYAN);
                        box.setOpaque(true);
                    }
                    if(j == 4){
                        boxOne = textBoard[i][0];
                        boxTwo = textBoard[i][1];
                        boxTree = textBoard[i][7];
                        boxOne.setBackground(Color.RED);
                        boxOne.setOpaque(true);
                        boxTwo.setBackground(Color.RED);
                        boxTwo.setOpaque(true);
                        boxTree.setBackground(Color.RED);
                        boxTree.setOpaque(true);
                        box.setBackground(Color.RED);
                        box.setOpaque(true);
                    }
                }
                if(box.getText()!="" && Double.parseDouble(box.getText())== 0.0){
                    if(j == 2){
                        boxOne = textBoard[i][0];
                        boxTwo = textBoard[i][1];
                        boxTree = textBoard[i][5];
                        boxOne.setBackground(null);
                        boxOne.setOpaque(true);
                        boxTwo.setBackground(null);
                        boxTwo.setOpaque(true);
                        boxTree.setBackground(null);
                        boxTree.setOpaque(true);
                        box.setBackground(Color.LIGHT_GRAY);
                        box.setOpaque(true);
                    }
                    if(j == 3){
                        boxOne = textBoard[i][0];
                        boxTwo = textBoard[i][1];
                        boxTree = textBoard[i][6];
                        boxOne.setBackground(null);
                        boxOne.setOpaque(true);
                        boxTwo.setBackground(null);
                        boxTwo.setOpaque(true);
                        boxTree.setBackground(null);
                        boxTree.setOpaque(true);
                        box.setBackground(Color.LIGHT_GRAY);
                        box.setOpaque(true);
                    }
                    if(j == 4){
                        boxOne = textBoard[i][0];
                        boxTwo = textBoard[i][1];
                        boxTree = textBoard[i][7];
                        boxOne.setBackground(null);
                        boxOne.setOpaque(true);
                        boxTwo.setBackground(null);
                        boxTwo.setOpaque(true);
                        boxTree.setBackground(null);
                        boxTree.setOpaque(true);
                        box.setBackground(Color.LIGHT_GRAY);
                        box.setOpaque(true);
                    }
                }
            }
        }

        for(int j = 5; j < 8;j+=1){
            String color = program.getPhase(j-4).getColorPhase();
            JLabel box = textBoard[44][j];
            if(color == "Green"){
                box.setBackground(Color.GREEN);
                box.setOpaque(true);
            }
            if(color == "Red"){
                box.setBackground(Color.RED);
                box.setOpaque(true);
            }
        }

        String colorTpercentage = program.getColorTotalPercentage();
        JLabel totalPercentage = textBoard[46][7];
        if(colorTpercentage == "Green"){
            totalPercentage.setBackground(Color.GREEN);
            totalPercentage.setOpaque(true);
        }
        if(colorTpercentage == "Red"){
            totalPercentage.setBackground(Color.RED);
            totalPercentage.setOpaque(true);
        }
    }
 


    public void prepareActionsMenu() {
        openFile.addActionListener( new ActionListener(){ 
    		public void actionPerformed( ActionEvent event ){
            	actionOpen();
            } 
    	});
    	saveFile.addActionListener( new ActionListener(){
            public void actionPerformed( ActionEvent event ){
            	actionSave();
            }
        });
    }

    public void actionClose(){
        int confirmation = JOptionPane.showConfirmDialog(null,"Seguro que desea salir?","Cerrar",0);
        if(confirmation == JOptionPane.YES_OPTION){
            System.exit(0);
        }
    }

    public void actionSave(){
        program = program.getProgram();
    	if (program == null) {JOptionPane.showMessageDialog(this, "Se esta tratando de guardar una partida no iniciada.","Informacion",1);}
    	else {
    		File file = null;
        	FileNameExtensionFilter filter = new FileNameExtensionFilter("Archivo DAT (*.dat)", "dat");
        	filesChooser.setFileFilter(filter);
        	filesChooser.setVisible(true);
        	int confirmation = filesChooser.showSaveDialog(saveFile);
        	if (confirmation == filesChooser.APPROVE_OPTION) {
        		file = new File(filesChooser.getSelectedFile()+".dat");
        	}
        	filesChooser.setVisible(false);
        	if (file == null) {JOptionPane.showMessageDialog(this, "Se cancelo la operacion de guardar.","Informacion",1);}
        	else {
        		try {
        			program.guarde(file);
            	} catch (ElectricChargeBalanceException pe) {
            		System.out.println(pe.getMessage());
            	}
        	}
    	}  
    }

    public void actionOpen(){
        File file = null;
    	FileNameExtensionFilter filter = new FileNameExtensionFilter("Archivo DAT (*.dat)", "dat");
    	filesChooser.setFileFilter(filter);
    	filesChooser.setVisible(true);
    	int confirmation = filesChooser.showOpenDialog(openFile);
    	if (confirmation == filesChooser.APPROVE_OPTION) {
    		file = filesChooser.getSelectedFile();
    	}
    	filesChooser.setVisible(false);
    	if (file == null) {JOptionPane.showMessageDialog(this, "Se cancelo la operacion de abrir.","Informacion",1);}
    	else {
    		try {
        		this.program = program.abra(file);
        	} catch (ElectricChargeBalanceException pe) {
        		JOptionPane.showMessageDialog(this, pe.getMessage(),"Informacion",1);
        	}
    	}
    }

    public static void main(String[] arguments) {
        ElectricChargeBalanceGUI gui = new ElectricChargeBalanceGUI();
        gui.setVisible(true);
    }
    
}