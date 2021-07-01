package com.basteez.rest;

import com.basteez.model.User;
import org.hibernate.envers.AuditReader;
import org.hibernate.envers.AuditReaderFactory;
import org.hibernate.envers.query.AuditEntity;
import org.hibernate.envers.query.AuditQuery;

import javax.persistence.EntityManager;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;
import java.util.List;
import java.util.Map;

@Path("/enver")
public class EnverRetrieverRS{

    @GET
    @Path("/find")
    public List<User> find(){
        AuditReader reader = AuditReaderFactory.get(User.getEntityManager());
        List<User> results = reader.createQuery().forRevisionsOfEntity(User.class, true, true).getResultList();
        return results;
    }

    @GET
    @Path("/findRevisions/{uuid}")
    public Response findByUuid(@PathParam("uuid") String uuid){
        AuditReader reader = AuditReaderFactory.get(User.getEntityManager());
        AuditQuery query = reader.createQuery()
                .forRevisionsOfEntity(User.class, false, true)
                .add(AuditEntity.property("uuid").eq(uuid));
        List<Object[]> res = query.getResultList();
        //return actually throw an exception (I'm working on that)
        //in the meantime you can debug the project and see that getResultList() returns what you expect
        return Response.ok().entity(res).build();
    }
}
