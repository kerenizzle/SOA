package claseadministradora;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

/**
 * @author Kerenizzle
 * INFO ALUMNO
 * Keren Delgado Nakakawa
 * Sección D01 TAREA JAVA #8
 */

public class ClaseAdministradora {
    
    public Hashtable tabla=new Hashtable();
    public int contador = 0;
    public String Header=" ID\t| FECHA\t| HORA\t| ACTIVIDAD\n";
    
    public class Actividades{
    
        private String Fecha,Hora,Actividad;
    
    public Actividades(){
        
    }
        
    public Actividades(String F, String H, String A){  
         this.Fecha=F;
         this.Hora=H;
         this.Actividad=A;
        }
    
    String getFecha(){
        return Fecha;
    }
    
    String getHora(){
        return Hora;
    }
    
    String getActividad(){
        return Actividad;
    }
    
    int AgregarActividades(int contador){
        tabla.put(contador,this);
        return ++contador;
    }
    String PrintTable(){
        String Res=Header;
        Enumeration e = tabla.keys();
                int llave;
                String F,H,A;
                while(e.hasMoreElements()){
                    llave=(Integer)(e.nextElement());
                    F=((Actividades)tabla.get(llave)).getFecha();
                    H=((Actividades)tabla.get(llave)).getHora();
                    A=((Actividades)tabla.get(llave)).getActividad();
                    Res+=" "+llave+"\t| "+F+"\t| "+H+"\t| "+A+"\n";
                    }
        return Res;
    }
}
    
    public class Interfaz extends JFrame {
    
        private final JPanel Agregar,DatosAgregar,Mostrar,DatosBuscar,
                Buscar,DatosEliminar,Eliminar;
        private final JTabbedPane Pestañas;
        private final JButton BAgregar,BBuscar,BEliminar,BMostrar;
        private final JTextField TFecha,THora,TActividad,TBuscar,TEliminar;
        private final JTextArea BuscarRes,MostrarRes;
        
        public Interfaz(){
        
        super("Práctica Ocho Java");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Pestañas=new JTabbedPane(); 
        Agregar= new JPanel(new FlowLayout(FlowLayout.CENTER));
        Agregar.setBorder(BorderFactory.createEmptyBorder(5,5,5,5));
        Agregar.add(new JLabel("Ingresa los datos"));
        DatosAgregar=new JPanel(new GridLayout(2,3));
        DatosAgregar.add(new JLabel("Fecha"));
        DatosAgregar.add(new JLabel("Hora"));
        DatosAgregar.add(new JLabel("Actividad"));
        DatosAgregar.setPreferredSize(new Dimension(400,60));
        TFecha= new JTextField("",SwingConstants.CENTER);
        THora= new JTextField("",SwingConstants.CENTER);
        TActividad=  new JTextField("",SwingConstants.CENTER);
        DatosAgregar.add(TFecha);
        DatosAgregar.add(THora);
        DatosAgregar.add(TActividad);
        Agregar.add(DatosAgregar);
        BAgregar= new JButton("Agregar");
        BAgregar.setPreferredSize(new Dimension(400,30));
        Agregar.add(BAgregar);
        
        Buscar= new JPanel(new FlowLayout(FlowLayout.CENTER));
        Buscar.setBorder(BorderFactory.createEmptyBorder(5,5,5,5));
        DatosBuscar= new JPanel(new GridLayout(1,2));
        DatosBuscar.setBorder(BorderFactory.createEmptyBorder(0,5,0,5));
        TBuscar=  new JTextField("",SwingConstants.CENTER);
        BBuscar= new JButton("Buscar");
        DatosBuscar.add(TBuscar);
        DatosBuscar.add(BBuscar);
        DatosBuscar.setPreferredSize(new Dimension(400,30));
        BuscarRes=new JTextArea(2,43);
        Border BCentro= BorderFactory.createLineBorder(Color.GRAY,1);
        BuscarRes.setBorder(BorderFactory.createTitledBorder(BCentro,"Resultado",TitledBorder.CENTER,TitledBorder.TOP ));
        BuscarRes.setFont(new Font("Monospaced",Font.PLAIN,12));
        BuscarRes.setOpaque(false);
        Buscar.add(DatosBuscar);
        Buscar.add(BuscarRes);
        
        Eliminar= new JPanel(new FlowLayout(FlowLayout.CENTER));
        Eliminar.setBorder(BorderFactory.createEmptyBorder(5,5,5,5));
        DatosEliminar=new JPanel(new GridLayout(1,2));
        DatosBuscar.setPreferredSize(new Dimension(400,30));
        TEliminar=  new JTextField("",SwingConstants.CENTER);
        BEliminar= new JButton("Eliminar");
        DatosEliminar.add(TEliminar);
        DatosEliminar.add(BEliminar);
        Eliminar.add(DatosEliminar);
        
        Mostrar= new JPanel(new FlowLayout(FlowLayout.CENTER));
        Mostrar.setBorder(BorderFactory.createEmptyBorder(5,5,5,5));
        BMostrar= new JButton("Mostrar");
        BMostrar.setPreferredSize(new Dimension(400,30));
        MostrarRes=new JTextArea(15,43);
        MostrarRes.setOpaque(false);
        MostrarRes.setBorder(BorderFactory.createTitledBorder(BCentro,"Registro de Actividades",TitledBorder.CENTER,TitledBorder.TOP ));
        MostrarRes.setFont(new Font("Monospaced",Font.PLAIN,12));
        MostrarRes.setText("");
        Mostrar.add(BMostrar);
        Mostrar.add(MostrarRes);
        
        Pestañas.addTab("Agregar", Agregar);
        Pestañas.addTab("Buscar", Buscar);
        Pestañas.addTab("Eliminar", Eliminar);
        Pestañas.addTab("Mostrar", Mostrar);
        getContentPane().add(Pestañas);
        this.setSize(500,400);
        this.setVisible(true);
        
        BAgregar.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent arg0) {
                MostrarRes.setText("");
                String SFecha,SHora,SActividad;
                SFecha=TFecha.getText();
                SHora=THora.getText();
                SActividad=TActividad.getText();
                if(SFecha.isEmpty()||SHora.isEmpty()||SActividad.isEmpty()){
                    JOptionPane.showMessageDialog(new JFrame(), "Hay campos vacios, verifica información");
                }
                else{
                    Actividades A = new Actividades(SFecha,SHora,SActividad);
                    contador=A.AgregarActividades(contador);
                    TFecha.setText("");
                    THora.setText("");
                    TActividad.setText("");
                   JOptionPane.showMessageDialog(new JFrame(),new Actividades().PrintTable(),"Datos actualizaados",JOptionPane.DEFAULT_OPTION);
                }        
            }  
        });
        
        BBuscar.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent arg0) {
                Actividades A;
    		try{
    		int id=Integer.parseInt(TBuscar.getText());
    		if(tabla.isEmpty()){
                	TBuscar.setText("");
    			JOptionPane.showMessageDialog(new JFrame(), "No hay registros","Tabla vacía",JOptionPane.DEFAULT_OPTION);
    		}
    		else{
        		if(tabla.containsKey((Integer)id)){
                            A = (Actividades)(tabla.get(id));
                            BuscarRes.setText(Header+" "+id+"\t| "+
                            A.getFecha()+"\t| "+
                            A.getHora()+"\t| "+
                            A.getActividad()+"\n");        	
                            TBuscar.setText("");
    			}
    			else
                            JOptionPane.showMessageDialog(new JFrame(), "ID no encontrado","Error de búsqueda",JOptionPane.DEFAULT_OPTION);
    			}
    		}
                catch(NumberFormatException | HeadlessException exc){
                    JOptionPane.showMessageDialog(null, "Ingresa un valor entero","Error de entrada",JOptionPane.ERROR_MESSAGE);
    		}
            }  
        });
        
        BEliminar.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent arg0) {
                Actividades A;
                MostrarRes.setText("");
    		try{
    		int id=Integer.parseInt(TEliminar.getText());
    		if(tabla.isEmpty()){
                	TEliminar.setText("");
    			JOptionPane.showMessageDialog(new JFrame(), "No hay registros","Tabla vacía",JOptionPane.DEFAULT_OPTION);
    		}
    		else{
        		if(tabla.containsKey((Integer)id)){
                            tabla.remove(id);
                            JOptionPane.showMessageDialog(new JFrame(),new Actividades().PrintTable(),"Datos actualizaados",JOptionPane.DEFAULT_OPTION);
                        }
    			else
                            JOptionPane.showMessageDialog(new JFrame(), "ID no encontrado","Error de búsqueda",JOptionPane.DEFAULT_OPTION);
    			}
    		}
                catch(NumberFormatException | HeadlessException exc){
                    JOptionPane.showMessageDialog(null, "Ingresa un valor entero","Error de entrada",JOptionPane.ERROR_MESSAGE);
    		}
            }  
        });
        
        BMostrar.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent arg0) { 
                
                MostrarRes.setText(new Actividades().PrintTable());
            }  
        });
        
        ChangeListener changeListener = new ChangeListener() {
        public void stateChanged(ChangeEvent changeEvent) {
        
        BuscarRes.setText("");
        MostrarRes.setText("");
        TBuscar.setText("");
        TEliminar.setText("");
        }
        };
        Pestañas.addChangeListener(changeListener);
        
        }
    
    }
    
    public static void main(String[] args) {
        Interfaz Ventana=new ClaseAdministradora().new Interfaz();
        Ventana.setVisible(true);   
    }
    
}
