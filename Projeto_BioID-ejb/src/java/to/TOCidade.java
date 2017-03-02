package to;

import java.sql.ResultSet;
import org.json.JSONObject;

/**
 *
 * @author Daniel
 */
public class TOCidade extends TOBase{
    
    private long idcidade;
    
    private String nomecidade;

    public long getIdcidade() {
        return idcidade;
    }

    public void setIdcidade(long idcidade) {
        this.idcidade = idcidade;
    }

    public String getNomecidade() {
        return nomecidade;
    }

    public void setNomecidade(String nomecidade) {
        this.nomecidade = nomecidade;
    }


    public TOCidade() {
    }

    
    public TOCidade (ResultSet rs) throws Exception{

        this.idcidade = rs.getLong("idcidade");
        this.nomecidade = rs.getString("nomecidade");
      
    }

    @Override
    public JSONObject getJson() throws Exception {
        JSONObject j = new JSONObject();
        
        //populando o objeto j
        j.put("idcidade", idcidade);
        j.put("nomecidade", nomecidade);
        
        return j;
    }
    
    
}
