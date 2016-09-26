package anagrama;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;

/**
 * @author Kerenizzle
 * INFO ALUMNO
 * Keren Delgado Nakakawa
 * Sección D01 TAREA JAVA #6
 */

public class Anagrama {

public class Interfaz extends JFrame {
    
        private final JPanel Compara,Encuentra,CenterResComp,CenterResEnc;
        private final JLabel ResComparaBool,ResCompara,ResCantidad,ResEncuentra;
        private final JTabbedPane pestañas;
        JTextField TStringUno,TStringDos;
        private JTextArea TStringArray;
        private final JButton BComparar,BEncontrar;
        private String StringUno,StringDos,StringArray;
        
        public Interfaz(){
        
        super("Práctica Seis Java");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pestañas=new JTabbedPane();
        Compara=new JPanel(new GridLayout(5,1));
        Compara.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
        Compara.add(new JLabel("Ingresa dos cadenas a comparar",SwingConstants.CENTER));
        TStringUno=new JTextField();
        TStringDos=new JTextField();
        Compara.add(TStringUno);
        Compara.add(TStringDos);
        BComparar=new JButton("Comparar");
        Compara.add(BComparar);
        CenterResComp= new JPanel(new FlowLayout(FlowLayout.CENTER));
        ResComparaBool=new JLabel("? ",SwingConstants.CENTER);
        ResComparaBool.setForeground(Color.GRAY);
        ResCompara=new JLabel("son anagramas");
        CenterResComp.add(ResComparaBool);
        CenterResComp.add(ResCompara);
        Compara.add(CenterResComp);
        
        Encuentra=new JPanel(new BorderLayout(5,5));
        Encuentra.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
        Encuentra.add("North",new JLabel("Ingresa una lista de palabras separadas por comas",SwingConstants.CENTER));
        TStringArray=new JTextArea(60,30);
        Encuentra.add("Center",TStringArray);
        ResCantidad=new JLabel("# ");
        ResCantidad.setForeground(Color.BLUE);
        ResEncuentra=new JLabel("pares de anagramas encontrados",SwingConstants.RIGHT);
        BEncontrar=new JButton("Encontrar");
        BEncontrar.setPreferredSize(new Dimension(130,20));
        CenterResEnc=new JPanel(new FlowLayout(FlowLayout.CENTER));
        CenterResEnc.add(BEncontrar);
        CenterResEnc.add(ResCantidad);
        CenterResEnc.add(ResEncuentra);
        Encuentra.add("South",CenterResEnc);
        
        pestañas.addTab("Comparar anagramas", Compara);
        pestañas.addTab("Encontrar anagramas", Encuentra);
        getContentPane().add(pestañas);
        this.setSize(500,200);
        this.setVisible(true);
        
        BComparar.addActionListener(new ActionListener(){
        @Override
            public void actionPerformed(ActionEvent arg0) {
            StringUno=TStringUno.getText();
            StringDos=TStringDos.getText();
            if(StringUno.isEmpty()||StringDos.isEmpty())
                JOptionPane.showMessageDialog(new JFrame(), "Hay espacios vacios. Ingresa cadenas");
            else{
               if(new Anagrama().EsAnagrama(StringUno,StringDos)){
                   ResComparaBool.setText("Sí");
                   ResComparaBool.setForeground(Color.GREEN);
               }
               else{
                   ResComparaBool.setText("No");
                   ResComparaBool.setForeground(Color.RED);
               }
            }            
            }});
        
         BEncontrar.addActionListener(new ActionListener(){
        @Override
            public void actionPerformed(ActionEvent arg0) {
            StringArray=TStringArray.getText();
            if(StringArray.isEmpty())
                JOptionPane.showMessageDialog(new JFrame(), "Hay espacios vacios. Ingresa cadenas");
            else{
                   int res=(new Anagrama().CuentaAnagrama(StringArray));
                   ResCantidad.setText(String.valueOf(res));
            }
            
            
            }}); 
        }
    }

public boolean EsAnagrama(String UNO, String DOS) {
     char[] ArrayUNO = UNO.toUpperCase().toCharArray();
     char[] ArrayDOS = DOS.toUpperCase().toCharArray();
     Arrays.sort(ArrayUNO);
     Arrays.sort(ArrayDOS);
     return Arrays.equals(ArrayUNO,ArrayDOS);
}

public int CuentaAnagrama(String Lista){
   int contador=0;
   Set<String> stringSet = new HashSet<>(Arrays.asList(Lista.toUpperCase().split(",")));
   String[] palabras= stringSet.toArray(new String[0]);
   for (String P: palabras){
       for (String Q: palabras){
           if(!P.equals(Q)){
               if(new Anagrama().EsAnagrama(P,Q)){
                   contador++;
               }
           }
       }
   }
   return contador;    
}

    public static void main(String[] args) {
        Interfaz Ventana=new Anagrama().new Interfaz();
        Ventana.setVisible(true);
    }
    
}
