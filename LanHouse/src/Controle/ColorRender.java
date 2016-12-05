/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Controle;

import java.awt.Color;
import java.awt.Component;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import modelo.Maquina;
import modeloDAO.MaquinaDAO;
import view.Inicial;
 
 
public class ColorRender extends DefaultTableCellRenderer {
    private Inicial inicial;
    public ColorRender(Inicial aThis) {
         inicial=aThis;//To change body of generated methods, choose Tools | Templates.
    }
 
    @Override
    public Component getTableCellRendererComponent(
            JTable table, Object value,
            boolean isSelected, boolean hasFocus,
            int row, int column) {
 
        super.getTableCellRendererComponent(table, value, isSelected,
                hasFocus, row, column);
         
        // seta o resultado p/ falso porque isso sera usado em varias Jtables do sistema
        int result = 0;
        MaquinaDAO md=new MaquinaDAO();
       
        
        try{
// no nosso exemplo 
            
            if (table.getName() != null) { // p/ não escrever um null cada vez que carregar uma célula

                    if (table.getName().equals("tabela")) {
                        if((inicial.getValor()!=4)&&(inicial.getValor()!=2)){
                            result=0;
                        }else{
                            Maquina aux=md.buscarPorChave((String) table.getModel().getValueAt(row, 0));

                            if(aux==null){
                                result=3;
                            }else{

                                if(aux.getUsado()==0){
                                    result=1;
                                }else{
                                    result=2;
                                }
                            }
                        }
                    }
            }
        }
        catch (java.lang.NullPointerException ex){
            System.out.println(ex.getMessage());
        }
         
        //se for uma linha selecionada
        if (isSelected) {
            setBackground(table.getSelectionBackground());
            setForeground(table.getSelectionForeground());
        } else {
            // se o checkbox estiver selecionado
            // pinta a linha inteira
            
            if (result==1) {
                setBackground(Color.GREEN);
                setForeground(Color.black);
            } else {
                if(result==2){
                // se não , colore alternado as linhas
                    setBackground(Color.RED);
                    setForeground(Color.WHITE);
                }
                
            else{
                
                    setBackground(Color.WHITE);
                    setForeground(Color.black);
                
            }
        }
      }
      return this;
    
    }
}