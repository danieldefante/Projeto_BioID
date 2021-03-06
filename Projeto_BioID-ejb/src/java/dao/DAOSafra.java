/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import fw.Data;
import bo.BOFactory;
import java.sql.Connection;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import org.json.JSONArray;
import to.TOBase;
import to.TOSafra;
import to.TOLogin;

/**
 *
 * @author Aimee
 */
public class DAOSafra extends DAOBase{

    @Override
    public long inserir(Connection c, TOBase t, String metodo) throws Exception {
        String sql = null;
        
        
        
//        TOSafra to = ((TOSafra)t);
        List<Object> p = new ArrayList<Object>();
        
        switch(metodo){
            default:
                sql = "INSERT INTO safra(statussafra_idstatussafra, propriedade_idpropriedade, cultivar_idcultivar, safra,"
                + " datareceb, qtdrecebida, status_entrevistador) VALUES (?, ?, ?, ?, ?, ?, ?)";
                
                p.add(((TOSafra)t).getStatussafra_idstatussafra());
                p.add(((TOSafra)t).getPropriedade_idpropriedade());
                p.add(((TOSafra)t).getCultivar_idcultivar());
                p.add(((TOSafra)t).getSafra());
                p.add(((TOSafra)t).getDatareceb());
                p.add(((TOSafra)t).getQtdrecebida());
                p.add(9);
                break;
        }
        
        //passa por parametros a conexao e a lista de objetos da insercao de um novo produto
        return Data.executeUpdate(c, sql, p);
    }

    @Override
    public void editar(Connection c, TOBase t, String metodo) throws Exception {
        String sql = null;
                

        
//        TOSafra to = (TOSafra)t;
        
        List<Object> p = new ArrayList<Object>();
        
        switch(metodo){
            default:
                sql = "UPDATE safra SET qtdcolhida=?, ultimadatacolheita=?, statussafra_idstatussafra=? WHERE idsafra = ?";
                p.add(((TOSafra)t).getQtdcolhida());
                p.add(((TOSafra)t).getUltimadatacolheita());
                p.add(((TOSafra)t).getStatussafra_idstatussafra());
                p.add(((TOSafra)t).getIdsafra());
        
                break;
        }
        
        
        //passa por parametros a conexao e a lista de objetos da insercao de um novo produto        
        Data.executeUpdate(c, sql, p);
        
    }

    @Override
    public TOBase get(Connection c, TOBase t, String metodo) throws Exception {
        String sql = null;
        
        ResultSet rs = null;
        
        try{
//            TOSafra to = (TOSafra)t;
            
            switch(metodo){
                default:
                    sql = "select * from safra where idsafra IN(?)";
                    break;
            }
            rs = Data.executeQuery(c, sql, ((TOSafra)t).getIdsafra());
            
            if(rs.next()){
                return new TOSafra(rs, metodo);
            }else{
                return null;
            }
        }finally{
            rs.close();
        }
    }


    @Override
    public JSONArray listar(Connection c, TOBase t, String metodo) throws Exception {
        JSONArray  ja = new JSONArray();
  
        ResultSet rs = null;
        try{
            //variavel com lista dos parametros
            List<Object> u = new ArrayList<Object>();
            
            String sql = null;
            
            switch(metodo){
                case "NAO_RELATADAS":
                    sql = "SELECT idsafra, cultivar_idcultivar, c.nomecultivar, u.grandeza, safra, datareceb, qtdrecebida, status_entrevistador "
                            + "FROM public.safra "
                            + "INNER JOIN cultivar c ON(idcultivar = cultivar_idcultivar) "
                            + "INNER JOIN unidademedida u ON(idunidademedida = c.unidademedida_idunidademedida) "
                            + "WHERE status_entrevistador IN(9) AND propriedade_idpropriedade IN(?)";

                    u.add(((TOSafra) t).getPropriedade_idpropriedade());
                    break;
                default:
                    sql = "SELECT s.idsafra, s.statussafra_idstatussafra, s.safra, s.datareceb, s.qtdrecebida,"
                        + " (select SUM(d.qtddestinada) AS qtddestinada FROM destinacao d WHERE d.safra_idsafra IN(s.idsafra)),"
                        + " um.grandeza as grandeza_recebida, s.qtdcolhida, c.nomecultivar, pr.nomepropriedade, c.tempodecolheita, c.tempodestinacao FROM login l"
                        + " INNER JOIN pessoa p ON( p.idpessoa = l.pessoa_idpessoa)"
                        + " INNER JOIN relacaopa r ON( r.agricultor_pessoa_idpessoa = p.idpessoa)"
                        + " INNER JOIN propriedade pr ON (pr.idpropriedade = r.propriedade_idpropriedade)"
                        + " INNER JOIN safra s ON (s.propriedade_idpropriedade = pr.idpropriedade)"
                        + " INNER JOIN cultivar c ON (c.idcultivar = s.cultivar_idcultivar)"
                        + " INNER JOIN unidademedida um ON(um.idunidademedida = s.unidademedida_idunidademedida) where l.pessoa_idpessoa IN(?) ORDER BY s.safra DESC";

                    u.add(((TOLogin) t).getPessoa_idpessoa());
                    break;
            }
            rs = Data.executeQuery(c, sql, u);
            
            while (rs.next()){
                TOSafra ts = new TOSafra(rs, metodo);

//                switch ((int)((TOSafra)t).getStatussafra_idstatussafra()) {
//                    case 1:
//                    case 2:
//                    case 3:
//                        ((TOSafra)t).setPrazo_colheita(verificarPrazoColheita(((TOSafra)t), metodo));
//                        ((TOSafra)t).setPrazo_destinacao(verificarPrazoDestinacao(((TOSafra)t), metodo));
//                        break;
//                    case 4:
//                    case 5:
//                        ((TOSafra)t).setPrazo_colheita("relatada");
//                        ((TOSafra)t).setPrazo_destinacao(verificarPrazoDestinacao(((TOSafra)t), metodo));
//                        break;
//               
//                    case 6:
//               
//                        ((TOSafra)t).setPrazo_colheita("relatada");
//                        ((TOSafra)t).setPrazo_destinacao("relatada");
//                        break;
//                    case 7:
//                        ((TOSafra)t).setPrazo_colheita("relatada");
//                        ((TOSafra)t).setPrazo_destinacao("expirada");
//                        break;
//                    case 8:
//                        ((TOSafra)t).setPrazo_colheita("expirada");
//                        ((TOSafra)t).setPrazo_destinacao("expirada");
//                        break;
//                    
//                }
                
                
//                ja.put(((TOSafra)t).getJson(metodo));
                ja.put(ts.getJson(metodo));
            }
            
                        
        }finally{
            rs.close();
        }
        return ja;
    }


    private String verificarPrazoColheita(TOSafra ts, String metodo) {
        String teste = null;
        
               
        try{
            SimpleDateFormat formatar = new SimpleDateFormat("dd/MM/yyyy");

                Calendar datarecebimento = Calendar.getInstance();
                Calendar diaAtual = Calendar.getInstance();

                datarecebimento.setTime(formatar.parse(ts.getDatareceb()));

                datarecebimento.add(Calendar.DATE, +ts.getTempodecolheita());


                if(datarecebimento.getTimeInMillis() < diaAtual.getTimeInMillis()){
                    teste = "expirada";
                    //atualiza o status da safra
                    if(ts.getStatussafra_idstatussafra() == 1){
                        ts.setStatussafra_idstatussafra(8);  
                    }else if(ts.getStatussafra_idstatussafra() == 2){
                        ts.setStatussafra_idstatussafra(4);
                    }else if(ts.getStatussafra_idstatussafra() == 3){
                        ts.setStatussafra_idstatussafra(5);
                    }

                    BOFactory.editar(new DAOSafra(), ts, metodo);
                   
                }else{

                    teste = formatar.format(datarecebimento.getTime()); 
                }
        }catch(Exception e){
            teste = "Erro em verificar a safra colheita - "+ e;
        }

        return teste;  
    }

    private String verificarPrazoDestinacao(TOSafra ts, String metodo) {
        String teste = null;

        try{
            SimpleDateFormat formatar = new SimpleDateFormat("dd/MM/yyyy");
        
            Calendar datarecebimento = Calendar.getInstance();
            Calendar diaAtual = Calendar.getInstance();

            datarecebimento.setTime(formatar.parse(ts.getDatareceb()));

            datarecebimento.add(Calendar.DATE, +ts.getTempodestinacao());



            if(datarecebimento.getTimeInMillis() < diaAtual.getTimeInMillis()){
                teste = "expirada";
                //atualiza o status da safra
                        
                if(ts.getStatussafra_idstatussafra() == 4){
                    ts.setStatussafra_idstatussafra(7);
                }else if(ts.getStatussafra_idstatussafra() == 5){
                    ts.setStatussafra_idstatussafra(6);
                }
                BOFactory.editar(new DAOSafra(), ts, metodo);
                ////
            }else{

                teste = formatar.format(datarecebimento.getTime());
            }
        }catch(Exception e){
            teste = "Erro em verificar a safra destinacao - "+ e;
        
        }
        return teste;
    }

//    @Override
//    public JSONArray backupentrevista(Connection c, TOBase t) throws Exception {
//        JSONArray  ja = new JSONArray();
//        
//        
//        String sql = "SELECT s.idsafra, s.safra, c.nomecultivar, s.qtdrecebida, s.propriedade_idpropriedade, um.grandeza as grandeza_recebida, s.datareceb FROM safra s INNER JOIN cultivar c ON(c.idcultivar = s.cultivar_idcultivar) INNER JOIN unidademedida um ON(um.idunidademedida = s.unidademedida_idunidademedida) WHERE s.propriedade_idpropriedade IN(?) AND s.status_entrevistador IN(9);";
//        ResultSet rs = null;
//        
//        try{
//            //variavel com lista dos parametros
//            List<Object> u = new ArrayList<Object>();
//            TOSafra to = (TOSafra)t;
//            u.add(to.getPropriedade_idpropriedade());
//            
//            rs = Data.executeQuery(c, sql, u);
//            while (rs.next()){
//                TOSafra ts = new TOSafra().backupentrevista(rs);
//                ja.put(ts.getJsonConsulta());
//            }
//        }finally{
//            rs.close();
//        }
//        return ja;
//    }
}






