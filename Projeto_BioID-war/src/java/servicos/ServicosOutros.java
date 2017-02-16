/*
lista pais
lista estado
lista cidade
 */
package servicos;

import bo.BOFactory;
import dao.DAOLogin;
import dao.DAOOutrosIDNome;
import dao.DAOSessao;
import fw.Criptografia;
import java.math.BigInteger;
import java.security.SecureRandom;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Produces;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import org.json.JSONArray;
import org.json.JSONObject;
import to.TOLogin;
import to.TOOutrosIDNome;
import to.TOSessao;

/**
 * REST Web Service
 *
 * @author daniel
 */
@Path("outros")

public class ServicosOutros {

    @Context
    private UriInfo context;

//    @Inject
//    private OutletService outletService;
   
    public ServicosOutros() {
    }

    //login
    @Path("validacao")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public String login(String dataJson) throws Exception{
        
        //objeto de retorno da requisicao
        JSONObject j = new JSONObject();
        JSONObject k = new JSONObject(dataJson);
        
        try{
            TOLogin to = new TOLogin();
            
           

            to.setUsuario(k.getString("usuario"));
            to.setSenha(k.getString("senha"));
            
            
            to = (TOLogin) BOFactory.get(new DAOLogin(), to, k.getString("metodo"));
            
            if(to == null){
                j.put("sucesso", false);
                j.put("mensagem", "Usuário ou senha incorretos!");
            }else{
                //gera um idsessao e cria um novo registro
                TOSessao ts = new TOSessao();
                SecureRandom random = new SecureRandom();     
        
//                ts.setLogin_idlogin(to.getIdlogin());
                ts.setSessao(new BigInteger(130, random).toString(32));
                
                
             
                //salva uma nova sessao no banco de dados
//                BOFactory.inserir(new DAOSessao(), ts, k.getString("metodo"));
                
                //atribui o valor da nova sessao para o retorno
                to.setSessao(ts.getSessao());
                //retorna valores do login
                j.put("data", to.getJson("default"));
                j.put("sucesso", true);
                //retorna a data de login que espirará em um tempo determinado
                //j.put("logTempo", ((730 * Float.parseFloat(getData("M"))) - (730 - (Float.parseFloat(getData("d"))*24)))+168 );
                
                
            }
        }catch (Exception e){
            j.put("sucesso", false);
            j.put("mensagem", e.getMessage());
        }
        
        return j.toString();
    }
    
    @POST
    @Path("listar")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public String listar(String dataJson) throws Exception{
        
        JSONObject j = new JSONObject();
        
        JSONObject k = new JSONObject(dataJson);
        
         
        
        try{ 
            JSONArray ja = null;
            switch(k.getString("metodo")){
                case "pais":
                    ja = BOFactory.listar(new DAOOutrosIDNome(), null, k.getString("metodo"));
                    break;
                case "estados":
                    TOOutrosIDNome tp = new TOOutrosIDNome();
                    tp.setId(k.getLong("idpais"));
                    ja = BOFactory.listar(new DAOOutrosIDNome(), tp, k.getString("metodo"));
                    break;
                case "cidades":
                    TOOutrosIDNome te = new TOOutrosIDNome();
                    te.setId(k.getLong("idestado"));
                    ja = BOFactory.listar(new DAOOutrosIDNome(), te, k.getString("metodo"));
                    break;
                case "nome_id_unidades":
                    ja = BOFactory.listar(new DAOOutrosIDNome(), null, k.getString("metodo"));
                    break;
            }

             
                if(ja.length() > 0){
                    j.put("sucesso", true);
                    j.put("data", ja);

                }else{
                    j.put("sucesso", false);
                    j.put("mensagem", "Sem dados na tabela" + k.getString("metodo"));
                }
            
        }catch(Exception e){
            j.put("sucesso", false);
            j.put("mensagem", e.getMessage());
        }
        
        return j.toString();
   }
   
  
   
//   @RolesAllowed("agricultores")
   @GET
   @Path("teste")  
//   @Consumes(MediaType.APPLICATION_JSON)
   @Produces(MediaType.APPLICATION_JSON)
   public String teste() throws Exception{
        

        
        JSONArray  ja = new JSONArray();

        ja.put("AppleScript");
        ja.put("Asp");
        ja.put("BASIC");
        ja.put("ColdFusion");
        ja.put("COBOL");
        ja.put("Clojure");
        ja.put("JavaScript");
        ja.put("Python");
        ja.put("daniel");
        
        return ja.toString();
   }

}
