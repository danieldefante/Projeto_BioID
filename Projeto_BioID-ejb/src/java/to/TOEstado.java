package to;

import java.sql.ResultSet;
import org.json.JSONObject;

/**
 *
 * @author Daniel
 */
public class TOEstado extends TOBase{
    
    private long idestado;
    
    private String nomeestado;

    public long getIdestado() {
        return idestado;
    }

    public void setIdestado(long idestado) {
        this.idestado = idestado;
    }

    public String getNomeestado() {
        return nomeestado;
    }

    public void setNomeestado(String nomeestado) {
        this.nomeestado = nomeestado;
    }


    public TOEstado() {
    }

    
    public TOEstado (ResultSet rs) throws Exception{

        this.idestado = rs.getLong("idestado");
        this.nomeestado = rs.getString("nomeestado");
      
    }

    @Override
    public JSONObject getJson() throws Exception {
        JSONObject j = new JSONObject();
        
        //populando o objeto j
        j.put("idestado", idestado);
        j.put("nomeestado", nomeestado);
        
        return j;
    }
    
    
}
