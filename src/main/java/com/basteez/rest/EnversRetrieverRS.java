package com.basteez.rest;

import com.basteez.model.RevisionPojo;
import com.basteez.model.User;
import org.hibernate.envers.AuditReader;
import org.hibernate.envers.AuditReaderFactory;
import org.hibernate.envers.RevisionType;
import org.hibernate.envers.query.AuditEntity;
import org.hibernate.envers.query.AuditQuery;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;

@Path("/envers")
public class EnversRetrieverRS {


    @GET
    @Path("/findRevisions/{uuid}")
    public Response findByUuid(@PathParam("uuid") String uuid){
        AuditReader reader = AuditReaderFactory.get(User.getEntityManager());
        AuditQuery query = reader.createQuery()
                .forRevisionsOfEntity(User.class, false, true)
                .add(AuditEntity.property("uuid").eq(uuid));
        List<Object[]> res = query.getResultList();
        List<RevisionPojo> revisions = new ArrayList<>();
        for(Object[] o : res){
            RevisionPojo rp = new RevisionPojo();
            rp.user = (User)o[0];
            rp.revisionType = (RevisionType) o[2];
            revisions.add(rp);
        }
        return Response.ok().entity(revisions).build();
    }
}
