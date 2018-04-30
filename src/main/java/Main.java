import org.eclipse.persistence.jaxb.MarshallerProperties;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.transform.stream.StreamSource;
import java.io.StringReader;
import java.io.StringWriter;

public class Main {
    public static void main(String[] args) {
        System.setProperty("javax.xml.bind.context.factory","org.eclipse.persistence.jaxb.JAXBContextFactory");
        Entity entity = new Entity();
        entity.setId(25);
        entity.setName("Hello");

        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(Entity.class);
            Marshaller marshaller = jaxbContext.createMarshaller();
            marshaller.setProperty(MarshallerProperties.MEDIA_TYPE, "application/json");

            StringWriter stringWriter = new StringWriter();
            marshaller.marshal(entity, stringWriter);
            String jsonString = stringWriter.toString();
            System.out.println(jsonString);


            Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
            unmarshaller.setProperty(MarshallerProperties.MEDIA_TYPE, "application/json");
            StreamSource json = new StreamSource(new StringReader(jsonString));
            Entity entity1 = unmarshaller.unmarshal(json, Entity.class).getValue();
            System.out.println(entity.getId()+ " " + entity1.getName());

        } catch (JAXBException e) {
            e.printStackTrace();
        }
    }
}
