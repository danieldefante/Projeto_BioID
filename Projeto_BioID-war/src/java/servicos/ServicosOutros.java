/*
lista pais
lista estado
lista cidade
 */
package servicos;

import bo.BOFactory;
import dao.DAOCidade;
import dao.DAOLogin;
import dao.DAOEstado;
import dao.DAOPais;
import dao.DAOSessao;
import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.Date;
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
            
            //busca no banco usuario e senha
            to = (TOLogin) BOFactory.get(new DAOLogin(), to, "VALIDACAO");
            
            //se voltar vazio retorna mensagem de erro
            if(to == null){
                j.put("sucesso", false);
                j.put("mensagem", "Usuário ou senha incorretos!");
                
            //senao retorna um JSON com dados
            }else{
                //retorna valores do login
                j.put("data", to.getJson("VALIDACAO"));
                j.put("sucesso", true);
    
            }
        }catch (Exception e){
            j.put("sucesso", false);
            j.put("mensagem", e.getMessage());
        }
        
        return j.toString();
    }
    
    @GET
    @Path("listarpais")
    @Produces(MediaType.APPLICATION_JSON)
    public String listarPais() throws Exception{
        
        JSONObject j = new JSONObject();

        try{ 
            JSONArray ja = BOFactory.listar(new DAOPais());
             
                if(ja.length() > 0){
                    j.put("sucesso", true);
                    j.put("data", ja);

                }else{
                    j.put("sucesso", false);
                    j.put("mensagem", "Tabela país vazia!");
                }
            
        }catch(Exception e){
            j.put("sucesso", false);
            j.put("mensagem", e.getMessage());
        }
        
        return j.toString();
    }
    
    @POST
    @Path("listarestados")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public String listarEstados(String dataJson) throws Exception{
        
        JSONObject j = new JSONObject();
         
        
        try{
                JSONArray ja = BOFactory.listar(new DAOEstado(), dataJson);
                  
             
                if(ja.length() > 0){
                    j.put("sucesso", true);
                    j.put("data", ja);

                }else{
                    j.put("sucesso", false);
                    j.put("mensagem", "Tabela estado vazia!");
                }
            
        }catch(Exception e){
            j.put("sucesso", false);
            j.put("mensagem", e.getMessage());
        }
        
        return j.toString();
    }
    
    @POST
    @Path("listarcidades")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public String listarcidades(String dataJson) throws Exception{
        
        JSONObject j = new JSONObject();
         
        try{
                JSONArray ja = BOFactory.listar(new DAOCidade(), dataJson);
                  
             
                if(ja.length() > 0){
                    j.put("sucesso", true);
                    j.put("data", ja);

                }else{
                    j.put("sucesso", false);
                    j.put("mensagem", "Tabela cidade vazia!");
                }
            
        }catch(Exception e){
            j.put("sucesso", false);
            j.put("mensagem", e.getMessage());
        }
        
        return j.toString();
   }
    
  
   
//   @RolesAllowed("agricultores")
   @GET
   @Path("testeget")  
//   @Consumes(MediaType.APPLICATION_JSON)
   @Produces(MediaType.APPLICATION_JSON)
   public String testeget() throws Exception{
        

        
        JSONArray  ja = new JSONArray();

        ja.put("GET");
        ja.put("serviço");
        ja.put("retorno");
        ja.put("teste");
        ja.put("bioid");
        
        return ja.toString();
   }
   
   @POST
   @Path("testepost")  
   @Consumes(MediaType.APPLICATION_JSON)
   @Produces(MediaType.APPLICATION_JSON)
   public String testepost(String dataJson) throws Exception{
        
        JSONObject k = new JSONObject(dataJson);
        

        
        JSONArray  ja = new JSONArray();

        ja.put("POST");
        ja.put("serviço");
        ja.put("retorno");
        ja.put(k.get("valores"));

        
        return ja.toString();
   }
  

}
