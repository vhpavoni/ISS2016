package Controle;

import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import modelo.ValorHora;
import modeloDAO.ValorHoraDAO;

public class GerenciarCredito {
    
    public static String calcularPreco(Date data){
        if (data == null){
            return "entrada inválida";
        }
        ValorHoraDAO vh = new ValorHoraDAO();
        List<ValorHora> valores = vh.listar();
        
        float valorHora = valores.get(0).getValorHora();
        float valorMin = valorHora/60;
        
        float tempo = (data.getTime()-10800000)/60000;
        DecimalFormat df = new DecimalFormat("000.00");
        return df.format(valorMin*tempo);
    }
    
    public static String calcularHora(float preco){
        if (preco < 0){
            return "entrada inválida";
        }
        ValorHoraDAO vh = new ValorHoraDAO();
        List<ValorHora> valores = vh.listar();
        
        float valorHora = valores.get(0).getValorHora();
        float valorMin = valorHora/60;
        
        DateFormat df = new SimpleDateFormat("HH:mm");
        Date data = null;
        try {
            data = (Date)df.parse("00:00");
        } catch (ParseException ex) {}
        data.setTime((long)Math.ceil(preco/valorMin)*60000+10800000);
        if (preco < 72){
            return df.format(data);
        }
        else{
            int ajuste = Integer.parseInt(df.format(data).substring(0, 2));
            ajuste += 24;
            return ajuste+":"+df.format(data).substring(3, 5);
        }
    }
}
