package to;

import java.sql.ResultSet;
import org.json.JSONObject;

/**
 *
 * @author Daniel
 */
public class TOPais extends TOBase{
    
    private long idpais;
    
    private String nomepais;
    

    public long getIdpais() {
        return idpais;
    }

    public void setIdpais(long idpais) {
        this.idpais = idpais;
    }

    public String getNomepais() {
        return nomepais;
    }

    public void setNomepais(String nomepais) {
        this.nomepais = nomepais;
    }


    public TOPais() {
    }

    
    public TOPais (ResultSet rs) throws Exception{

        this.idpais = rs.getLong("idpais");
        this.nomepais = rs.getString("nomepais");
      
    }

    @Override
    public JSONObject getJson() throws Exception {
        JSONObject j = new JSONObject();
        
        //populando o objeto j
        j.put("idpais", idpais);
        j.put("nomepais", nomepais);
        
        return j;
    }
    
    
}
