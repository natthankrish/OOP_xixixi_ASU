package program.entities.clients;

import jakarta.xml.bind.annotation.adapters.XmlAdapter;

public class ClientXMLAdapter extends XmlAdapter<Object, ClientType> {

    // When reading
    @Override
    public ClientType unmarshal(Object obj) throws Exception{
        if (obj instanceof Customer){
            return new Customer();
        } else if (obj instanceof Member mr){
            return new Member(mr.getName(), mr.getPhoneNumber(), mr.getPoint(), mr.getActive());
        } else if (obj instanceof VIP vp){
            return new VIP(vp.getName(), vp.getPhoneNumber(), vp.getPoint(), vp.getActive());
        }
        return null;
    }

    // When writing
    @Override
    public Object marshal(ClientType ct) throws Exception{
        if (ct instanceof Customer){
            return new Customer();
        } else if (ct instanceof Member mr){
            return mr;
        } else if (ct instanceof VIP vp){
            return vp;
        }
        return null;
    }
}
