/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package comptabilitat.botiga;

import java.awt.print.PageFormat;
import java.awt.print.Paper;
import java.awt.print.PrinterJob;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Map;
import javax.swing.DefaultListModel;
import jxl.Sheet;
import jxl.Workbook;
import jxl.Cell;
import jxl.read.biff.BiffException;
import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;

/**
 *
 * @author Broquil
 */
public class Funcions {
    
    static int numeroTicket = 0;
    
    public static String getCell(int col, int fil, Sheet sheet) throws IOException, BiffException{
        try{
            Cell c = sheet.getCell(col, fil);
            return c.getContents();
        }catch (ArrayIndexOutOfBoundsException e){
            return null;
        }
    }


    static void carregarExcel(String ruta) throws IOException, BiffException {
        
        File f = new File(ruta);
        Workbook workbook = Workbook.getWorkbook(f);
        Sheet[] sheet = new Sheet[5];
        sheet[0] = workbook.getSheet(0);
        sheet[1] = workbook.getSheet(1);
        sheet[2] = workbook.getSheet(2);
        sheet[3] = workbook.getSheet(3);
        sheet[4] = workbook.getSheet(4);
        
        Producte p;
        int i;
       
        for(int s = 0; s < 3; s++){
            i = 2;
            while(getCell(1, i, sheet[s]) != "" && getCell(1, i, sheet[s]) != null){
                        
                String n;
                p = new Producte();
                n = getCell(1,i,sheet[s]);
                p.nom = n;

                n = getCell(9,i,sheet[s]);
                if(n.contains(",")){
                    n = n.replace(",", ".");
                }
                p.preu = Float.parseFloat(n);
                p.iva = Dades.getLlistaIva()[s];
                p.marca = getCell(10,i,sheet[s]);
                p.prestatgeMaxim = Integer.parseInt(getCell(11,i,sheet[s]));
                p.prestatgeActual = Integer.parseInt(getCell(12,i,sheet[s]));

                int c = 13;
                while(getCell(c,i,sheet[s])!=null){
                    p.afegirCodiBarres(getCell(c,i,sheet[s]));
                    c++;
                }
                i++;
                Dades.afegirProducte(p);
            }
        }
        
        i = 2;
        String n;
        String preu = "";
        Float preuf;
        while(!"".equals(getCell(1, i, sheet[3])) && getCell(1, i, sheet[3]) != null){
            n = getCell(1,i,sheet[3]);
            preu = getCell(3,i,sheet[3]);
            if(preu.contains(",")){
                preu = preu.replace(",", ".");
            }
            preuf = Float.parseFloat(preu);
            Dades.verdura.put(n, preuf);
            i++;
        }
        
        i = 2;
        while(!"".equals(getCell(1, i, sheet[4])) && getCell(1, i, sheet[3]) != null){
            n = getCell(1,i,sheet[4]);
            preu = getCell(3,i,sheet[4]);
            if(preu.contains(",")){
                preu = preu.replace(",", ".");
            }
            preuf = Float.parseFloat(preu);
            Dades.productesGranel.put(n, preuf);
            i++;
        }
    }
    
    public static void obreDades() throws FileNotFoundException, IOException, ClassNotFoundException{
        /*
        FileInputStream fin = new FileInputStream("prova.txt");
        ObjectInputStream ois = new ObjectInputStream(fin);
        Dades.llistaProductes = (ArrayList<Producte>) ois.readObject();
        */
    }

    static void afegirProducte(String c) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    static void guardarTicket(Ticket ticketActual) throws IOException {
        
        
        DateFormat dateFormat = new SimpleDateFormat("yyyy_MM_dd__HH_mm_ss");
        Date date = new Date();
        String data = dateFormat.format(date); //2014/08/06 15:59:48
        File arxiuTicket = new File("Tickets/" + data + ".txt");
        float total = 0;
        try (BufferedWriter arxiuOutput = new BufferedWriter(new FileWriter(arxiuTicket))) {
            arxiuOutput.write(String.format("%19s %n", "Súper Eva"));
            arxiuOutput.write(String.format("%15s %n", "Carrer Major, 13"));
            arxiuOutput.write(String.format("%14s %n", "Tlf: 977 81 60 34"));
            arxiuOutput.write(String.format("%19s %n", "39879993V"));
            arxiuOutput.write(String.format("%n %n"));
            arxiuOutput.write("Data: " + data);
            arxiuOutput.write(String.format("%n"));
            arxiuOutput.write("Numero: " + numeroTicket);
            arxiuOutput.write(String.format("%n %n"));
            arxiuOutput.write(String.format("%-16s %8s %s %n", "Producte", "Quant.", "Preu"));
            arxiuOutput.write(String.format("%n"));
            for(ItemTicket t: Dades.ticketActual.llistaItems){
                if(t.nom.length()>19){
                    arxiuOutput.write(String.format("%-20s %.1f %5.2f %n", t.nom.toUpperCase().substring(0, 19), t.quantitat, t.total));
                }else{
                    arxiuOutput.write(String.format("%-20s %.1f %5.2f %n", t.nom.toUpperCase(), t.quantitat, t.total));
                }
                total += t.total;
            }
            arxiuOutput.write(String.format("%n %n"));
            arxiuOutput.write(String.format("%20s %7.2f %n", "Efectiu:", Dades.ticketActual.Efectiu));
            arxiuOutput.write(String.format("%20s %7.2f %n", "Canvi:", Dades.ticketActual.Canvi));
            arxiuOutput.write(String.format("%20s %7.2f", "Total:", total));
            arxiuOutput.write(String.format("%n %n"));
            arxiuOutput.write(String.format("*******************************"));
        }
        Funcions.numeroTicket++;
    }
    
    
    static Producte cercaPerNom(String nom) {
        java.lang.Boolean trobat = false;
        int i = 0;
        Producte retorn = null;
        ArrayList<Producte> productes = Dades.getLlistaProductes();
        while(i<Dades.llistaProductes.size() && !trobat){
            if(nom.equals(productes.get(i).nom)){
                trobat = true;
                retorn = productes.get(i);
            }
            i++;
        }
        if(trobat){
            return retorn;
        }
        return null;
    }

    static Producte cercaPerCodiBarres(String c) {
        java.lang.Boolean trobat = false;
        int i = 0;
        Producte retorn = null;
        ArrayList<String> cb;
        ArrayList<Producte> productes = Dades.getLlistaProductes();
        while(i<Dades.llistaProductes.size() && !trobat){
            cb = productes.get(i).codiBarres;
            for(String k: cb){
                if(c.equals(k)){
                    trobat = true;
                    retorn = productes.get(i);
                }
            }
            
            i++;
        }
        if(trobat){
            return retorn;
        }
        return null;
    }

    static Producte cercaPerCategoria(String nom) {
        java.lang.Boolean trobat = false;
        int i = 0;
        Producte retorn = null;
        ArrayList<Producte> productes = Dades.getLlistaProductes();
        while(i < Dades.llistaProductes.size() && !trobat){
            if(nom.equals(productes.get(i).marca)){
                trobat = true;
                retorn = productes.get(i);
            }
            i++;
        }
        if(trobat){
            return retorn;
        }
        return null;
    }

    static DefaultListModel cercaPorcioProducte(String c) {
        
        DefaultListModel llistaCercaNoms = new DefaultListModel();
        ArrayList<Producte> llistaElements = Dades.llistaProductes;
        
        for(Producte p: llistaElements){
            String nomActual = p.nom.toUpperCase();
            int i = 0;

            String porcio = ""; //substring del nom o marca actual
            boolean trobat = false;
                
            while (i + c.length() <= nomActual.length() && !trobat){
                porcio = nomActual.substring(i, i + c.length());
                if(porcio.equals(c)){
                    llistaCercaNoms.addElement(p.nom);
                    trobat=true;
                }
                i++;
            }
            i = 0;
        }
        
        for(Producte p: llistaElements){
            String nomActual = p.marca.toUpperCase();
            int i = 0;
            String porcio = ""; //substring del nom o marca actual
            boolean trobat = false;
                
            while (i + c.length() <= nomActual.length() && !trobat){
                porcio = nomActual.substring(i, i + c.length()).toUpperCase();
                if(porcio.equals(c) && llistaCercaNoms.contains(c)){
                    llistaCercaNoms.addElement(p.nom);
                    trobat=true;
                }
                i++;
            }
            i = 0;
        }
        
        return llistaCercaNoms;
    }

    static DefaultListModel cercaPorcioGranel(String c) {
        
        DefaultListModel llistaCercaNoms = new DefaultListModel();
        
        Map<String, Float> llistaElements = new HashMap() {};
        
        llistaElements.putAll(Dades.productesGranel);
        llistaElements.putAll(Dades.verdura);
        
        int i = 0;
        String porcio = "";
        for (Map.Entry<String, Float> p : llistaElements.entrySet()){
            String nomActual = p.getKey().toUpperCase();
            boolean trobat = false;
                
            while (i + c.length() <= nomActual.length() && !trobat){
                porcio = nomActual.substring(i, i + c.length());
                if(porcio.equals(c)){
                    llistaCercaNoms.addElement(p.getKey());
                    trobat=true;
                }
                i++;
            }
            porcio = "";
            i = 0;
        }
        
        return llistaCercaNoms;
    }

    /**
     * Cerca el producte per nom numeroTicket les llistes de productes numeroTicket granel, la de verdura i la de carns i altres
     * @param nom nom del producte que es busca
     * @return producte coincident, si n'hi ha.
     */
    
    static Map.Entry<String, Float> cercaPerNomGranel(String nom) {

        for (Map.Entry<String, Float> p : Dades.verdura.entrySet()){
            if(p.getKey()==nom){
                return p;
            }
        }
        
        for (Map.Entry<String, Float> p : Dades.productesGranel.entrySet()){
            if(p.getKey()==nom){
                return p;
            }
        }
        
        return null;
    }
    
    public static float arrodonirFloat(float d) {
        BigDecimal bd = new BigDecimal(Float.toString(d));
        bd = bd.setScale(2, BigDecimal.ROUND_HALF_UP);
        return bd.floatValue();
    }

    static void definirImpresora() {
        PrinterJob job = PrinterJob.getPrinterJob();
        PageFormat pf = job.defaultPage();
        Paper paper = new Paper();
        paper.setImageableArea(pf.getHeight()*72d, pf.getWidth()*72d, pf.getHeight()*72d, pf.getWidth()*72d);
        pf.setPaper(paper);
        boolean ok = job.printDialog();
        Dades.configImpresora = job;
        Dades.dialegImprimir = ok;
        Dades.format = pf;
    }

    static void afegirVendes(Ticket ticketActual) {
        ArrayList<ItemTicket> items = ticketActual.llistaItems;
        items.stream().forEach((i) -> {
            Dades.vendes.llistaItems.add(i);
        });
        Dades.vendes.llistaItems = colapsaTicket(Dades.vendes.llistaItems);
    }

    static void afegirPrestatges(Ticket ticketActual) {
        ArrayList<ItemTicket> items = ticketActual.llistaItems;
        items.stream().forEach((i) -> {
            Dades.prestatges.llistaItems.add(i);
        });
        Dades.prestatges.llistaItems = colapsaTicket(Dades.prestatges.llistaItems);
    }

    /**
     * Rep una llista d'items de ticket i en retorna una altra sense cap element repetit
     * @param ticket Ticket que s'ha de colapsar
     * @return buffer Llista amb els elements sense repeticions
     */
    static ArrayList<ItemTicket> colapsaTicket(ArrayList<ItemTicket> ticket) {
        
        ArrayList<ItemTicket> buffer = new ArrayList<>();
        
        int s;
        while(!ticket.isEmpty()){
            s = 1;
            while(s < ticket.size()){
                if(ticket.get(0).nom.equals(ticket.get(s).nom)){
                    ticket.get(0).quantitat += ticket.get(s).quantitat;
                    ticket.remove(s);
                }else{
                    s++;
                };
            }
            buffer.add(ticket.get(0));
            ticket.remove(0);
        }
        return buffer;
    }

    /*static void actualitzaProducte(Producte original, Producte canvi) throws IOException, BiffException, WriteException {
        
        try {
            File exlFile = new File(Dades.arxiuDefecte);
            WritableWorkbook writableWorkbook = Workbook.createWorkbook(exlFile);
 
            WritableSheet[] writableSheet = writableWorkbook.getSheets();
            System.out.print(writableSheet.length);
            //Create Cells with contents of different data types.
            //Also specify the Cell coordinates in the constructor
            //Label label = new Label(0, 0, "Label (String)");
            DateTime date = new DateTime(1, 0, new Date());
            Boolean bool = new Boolean(2, 0, true);
            Number num = new Number(3, 0, 9.99);
 
            //Add the created Cells to the sheet
            //writableSheet.addCell(label);
            writableSheet[0].addCell(date);
            writableSheet[0].addCell(bool);
            writableSheet[0].addCell(num);
 
            //Write and close the workbook
            writableWorkbook.write();
            writableWorkbook.close();
 
        } catch (IOException | RowsExceededException e) {
        } catch (WriteException e) {
        }
        
        /*File entrada = new File(Dades.arxiuDefecte);
        File sortida = new File("temp.xls");
        
        Workbook workbook = Workbook.getWorkbook(entrada);
        WritableWorkbook copy = Workbook.createWorkbook(sortida, workbook);

        copy.write();
        
        Sheet[] sheet = new Sheet[4];
        sheet[0] = workbook.getSheet(0);
        sheet[1] = workbook.getSheet(1);
        sheet[2] = workbook.getSheet(2);
        //sheet[3] = workbook.getSheet(3);

        int s = -1;
        Cell c = null;

        while (c == null && s > 3) {
            s++;
            c = sheet[s].findCell(original.nom);
        }

        try {
            

            WritableSheet sheet2 = copy.getSheet(s);
            
            Label l = new Label("numeroTicket", 1);
            l.setText("aaaaaaaaaaaa");
            WritableCell cell = (WritableCell) l;

            
            sheet2.addCell(cell);
            
            copy.write();
            copy.close();
            workbook.close();
        } catch (ArrayIndexOutOfBoundsException e) {
        }

        File antic = new File(Dades.arxiuDefecte);
        File nou = new File("temp.xls");
        antic.delete();
        nou.renameTo(antic);

        setCell(1,i,s, canvi.nom);
        setCell(9,i,s, String.valueOf(canvi.preu));
        setCell(10,i,s, canvi.marca);
        setCell(11,i,s, String.valueOf(canvi.prestatgeMaxim));
        setCell(13,i,s, canvi.nom);
        
    }*/

}
