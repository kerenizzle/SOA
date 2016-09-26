package cadenabytes;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/**
 * @author Kerenizzle
 * INFO ALUMNO
 * Keren Delgado Nakakawa
 * Sección D01 TAREA JAVA #5
 */

public class CadenaBytes {

    public class Interfaz extends JFrame {
    
        private final JPanel DisplayIns,DisplayCadena;
        private final JLabel LAscii,LCaracteres,LText,ResLon;
        private JTextArea ResText,ResChar,ResAscii;
        private JTextField TString;
        private final JButton BCalc;
        private String Cadena;
        
        public Interfaz(){
        
        super("Práctica Cuatro Java");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(new BorderLayout(5,5));
        DisplayCadena=new JPanel(new FlowLayout(FlowLayout.CENTER));
        DisplayCadena.setBorder(BorderFactory.createEmptyBorder(0,20,0,20));
        DisplayIns=new JPanel(new FlowLayout(FlowLayout.CENTER));
        DisplayIns.add(new JLabel("Ingresa una cadena de caracteres (MAX 60)",SwingConstants.CENTER));
        TString= new JTextField();
        TString.setPreferredSize(new Dimension(850,20));
        BCalc= new JButton("Convertir a bytes");
        BCalc.setPreferredSize(new Dimension(130,20));
        LAscii=new JLabel("ASCII",SwingConstants.CENTER);
        LAscii.setPreferredSize(new Dimension(850,20));
        ResAscii=new JTextArea(2,75);
        ResAscii.setOpaque(false);
        ResAscii.setFont(new Font("Monospaced",Font.PLAIN,12));
        LText=new JLabel("Letra",SwingConstants.CENTER);
        LText.setPreferredSize(new Dimension(850,20));
        ResText=new JTextArea(12,75);
        ResText.setOpaque(false);
        ResText.setFont(new Font("Monospaced",Font.PLAIN,12));
        LCaracteres=new JLabel("Caracteres",SwingConstants.CENTER);
        LCaracteres.setPreferredSize(new Dimension(850,20));
        ResChar=new JTextArea(2,75);
        ResChar.setOpaque(false);
        ResChar.setFont(new Font("Monospaced",Font.PLAIN,12));
        ResLon=new JLabel("Longitud: #",SwingConstants.CENTER);
        ResLon.setForeground(Color.BLUE);
        ResLon.setPreferredSize(new Dimension(850,20));
        
        DisplayCadena.add(TString);
        DisplayCadena.add(BCalc);
        DisplayCadena.add(LAscii);
        DisplayCadena.add(ResAscii);
        DisplayCadena.add(LText);
        DisplayCadena.add(ResText);
        DisplayCadena.add(ResLon);
        DisplayCadena.add(LCaracteres);
        DisplayCadena.add(ResChar);
        this.add("North",DisplayIns);
        this.add("Center",DisplayCadena);
        this.add("South",ResLon);
                
        this.setSize(900,550);
        this.setVisible(true);

    
        BCalc.addActionListener(new ActionListener(){
        @Override
            public void actionPerformed(ActionEvent arg0) {
            Cadena=TString.getText();
            if(Cadena.equals(""))
                JOptionPane.showMessageDialog(new JFrame(), "Debes ingresar una cadena");
            if(Cadena.length()>60)
                JOptionPane.showMessageDialog(new JFrame(), "Ingresa una cadena más corta");
            else{
                byte[] ArregloBytes=new byte[Cadena.length()+1];
                ArregloBytes[0]=(byte)Cadena.length();
                int cont = 1;
                for(byte b : Cadena.getBytes()){
                    ArregloBytes[cont] = b;
                    cont++;
                }
                ResLon.setText("Longitud: "+String.valueOf(ArregloBytes[0]));
                ResAscii.setText(new CadenaBytes().PrintAscii(ArregloBytes));
                ResText.setText(new CadenaBytes().PrintLetter(ArregloBytes));
                ResChar.setText(new CadenaBytes().PrintChar(ArregloBytes));
            }
            
            }}); 
        }
    }
    
    public String PrintAscii(byte[] B){
    String Res=new String();
    for(int cont=1;cont<=B[0];cont++){
                    if(cont<B[0])
                        Res += B[cont]+ "-";
                    else
                        Res += B[cont];
                    if(cont%30==0)
                        Res += "\n";
                }
    return Res;
    }
   
    public String PrintChar(byte[] B){
    String Res=new String();
    for(int cont=1;cont<=B[0];cont++){
                    if(cont<B[0])
                        Res += (char)B[cont]+ "-";
                    else
                        Res += (char)B[cont];
                    if (cont%50==0)
                        Res += "\n";                  
                }
    return Res;
    }
    
    public String PrintLetter(byte[] B){
    String Res=new String();
        for(int cont=1;cont<=B[0];cont++){
                    if(cont<B[0])
                        Res += (new CadenaBytes().ConvertText(B[cont]))+ " - ";
                    else
                        Res += (new CadenaBytes().ConvertText(B[cont]));
                    if(cont%5==0){
                        Res += "\n";
                    }
                }
    return Res.toUpperCase();
    }
    
    public String ConvertText(int num){
        int Uni,Dec,decenas=0;
        boolean keep=true;
        String Res=new String();
        if(num/100==1){
            Res+="Ciento ";
            num=num-100;
        }
        while(keep){
            decenas+=10;
            Dec=num/decenas;
            switch(Dec){
                case 0:
                    keep=false;
                    break;
                case 1:
                    if(num%decenas>5){
                        Res+="Dieci";
                        keep=false;
                    }
                    else{
                        switch(num%decenas){
                            case 0:
                                Res+="Diez";
                                return Res;
                            case 1:
                                Res+="Once";
                                return Res;
                            case 2:
                                Res+="Doce";
                                return Res;
                            case 3:
                                Res+="Trece";
                                return Res;
                            case 4:
                                Res+="Catorce";
                                return Res;
                            case 5:
                                Res+="Quince";
                                return Res;
                        }
                    }
                    break;
                case 2:
                    if(num%decenas==0)
                        Res+="Veinte";
                    else
                        Res+="Veinti";
                    keep=false;
                    break;
                case 3:
                    Res+="Treinta";
                    keep=false;
                    break;
                case 4:
                    Res+="Cuarenta";
                    keep=false;
                    break;
                case 5:
                    Res+="Cincuenta";
                    keep=false;
                    break;
                case 6:
                    Res+="Sesenta";
                    keep=false;
                    break;
                case 7:
                    Res+="Setenta";
                    keep=false;
                    break;
                case 8:
                    Res+="Ochenta";
                    keep=false;
                    break;
                case 9:
                    Res+="Noventa";
                    keep=false;
                    break;    
            }
        }
        
        for (int unidades=1;unidades<10;unidades++){
        String conector=new String();
            if(num>9){
                Uni=(num%decenas);
                    if(num/decenas>2)
                        conector+=" y ";
            }
            else
                Uni=num;
            switch(Uni){
                case 1:
                    Res+=conector+"uno";
                    return Res;
                case 2:
                    Res+=conector+"dos";
                    return Res;
                case 3: 
                    Res+=conector+"tres";
                    return Res;
                case 4:
                    Res+=conector+"cuatro";
                    return Res;
                case 5:
                    Res+=conector+"cinco";
                    return Res;
                case 6:
                    Res+=conector+"seis";
                    return Res;
                case 7:
                    Res+=conector+"siete";
                    return Res;
                case 8:
                    Res+=conector+"ocho";
                    return Res;
                case 9:
                    Res+=conector+"nueve";
                    return Res;  
            }
        }
    return Res;
    }
    

    public static void main(String[] args) {
        Interfaz Ventana=new CadenaBytes().new Interfaz();
        Ventana.setVisible(true);       
    }
    
}
