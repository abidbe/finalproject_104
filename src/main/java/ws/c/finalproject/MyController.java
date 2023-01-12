/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ws.c.finalproject;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.springframework.http.HttpEntity;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author asus
 * 20200140104
 */
@RestController
@CrossOrigin
public class MyController {
    Ktp data = new Ktp();
    KtpJpaController dctrl = new KtpJpaController();
    
    
    @RequestMapping("/GET")
    @ResponseBody
    public List<Ktp> getDatabyID(){
        List<Ktp> datas = new ArrayList<>();
        try {datas = dctrl.findKtpEntities();}
        catch(Exception error) {}        
        return datas;
    }
    
    
    
    @RequestMapping(value ="/POST", method = RequestMethod.POST, consumes = APPLICATION_JSON_VALUE)
    public String sendData(HttpEntity<String> kiriman) throws Exception{
        String message="no action";
        String json_receive = kiriman.getBody();
        ObjectMapper mapper = new ObjectMapper();
        Ktp data = new Ktp(); //jika ingin banyak data pake List atau ArrayList
        data = mapper.readValue(json_receive, Ktp.class);
        dctrl.create(data);
        message = data.getNama()+" Saved";
        return message;
    }
    

    @RequestMapping(value ="/PUT", method = RequestMethod.PUT, consumes = APPLICATION_JSON_VALUE)
    public String editData(HttpEntity<String> kiriman) throws Exception{
        String message="no action";
        String json_receive = kiriman.getBody();
        ObjectMapper mapper = new ObjectMapper();
        Ktp newdatas = new Ktp(); //jika ingin banyak data pake List atau ArrayList
        
        newdatas = mapper.readValue(json_receive, Ktp.class);
        try {dctrl.edit(newdatas);} catch (Exception e){}
        message = newdatas.getNama()+" Saved";
        return message;
    }
    
        @RequestMapping(value ="/DELETE", method = RequestMethod.DELETE, consumes = APPLICATION_JSON_VALUE)
    public String deleteData(HttpEntity<String> kiriman) throws Exception{
        String message="no action";
        String json_receive = kiriman.getBody();
        ObjectMapper mapper = new ObjectMapper();
        Ktp newdatas = new Ktp(); //jika ingin banyak data pake List atau ArrayList     
        newdatas = mapper.readValue(json_receive, Ktp.class);
        dctrl.destroy(newdatas.getId());
        return "deleted";
    }
    
}
    

