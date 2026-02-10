package webservices;

import entities.UniteEnseignement;
import metiers.UniteEnseignementBusiness;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;
@Path("/ue")
public class UERestApi {

    public UniteEnseignementBusiness helper =
            new UniteEnseignementBusiness();
    //methode =>web srvice =>Rest Api
    //URI
    //getAllUEs
    @GET
    @Path("/List")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAll(){
        return Response.status(200).entity(this.helper.getListeUE()).build();
    }
    @GET
    @Path("/get/{code}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getByCode(@PathParam("code") int code) {
        UniteEnseignement ue = helper.getUEByCode(code);
        if (ue != null) {
            return Response.ok(ue).build();
        }
        return Response
                .status(Response.Status.NOT_FOUND)
                .entity( code + " introuvable")
                .build();
    }
    @GET
    @Path("/domaine/{domaine}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getByDomaine(@PathParam("domaine") String domaine) {
        List<UniteEnseignement> liste = helper.getUEByDomaine(domaine);
        return Response.ok(liste).build();
    }
    @GET
    @Path("/semestre/{semestre}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getBySemestre(@PathParam("semestre") int semestre) {
        List<UniteEnseignement> liste = helper.getUEBySemestre(semestre);
        return Response.ok(liste).build();
    }
    @POST
    @Path("/add")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addUE(UniteEnseignement ue) {
        boolean added = helper.addUniteEnseignement(ue);
        if (added) {
            return Response
                    .status(Response.Status.CREATED)
                    .entity("UE ajoutée avec succès")
                    .build();
        }
        return Response
                .status(Response.Status.BAD_REQUEST)
                .entity("Erreur lors de l'ajout")
                .build();
    }
    @PUT
    @Path("/update/{code}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updateUE(@PathParam("code") int code,
                             UniteEnseignement ue) {
        boolean updated = helper.updateUniteEnseignement(code, ue);
        if (updated) {
            return Response.ok("UE modifiée avec succès").build();
        }
        return Response
                .status(Response.Status.NOT_FOUND)
                .entity("UE non trouvée")
                .build();
    }
    @DELETE
    @Path("/delete/{code}")
    public Response deleteUE(@PathParam("code") int code) {
        boolean deleted = helper.deleteUniteEnseignement(code);
        if (deleted) {
            return Response.ok("UE supprimée avec succès").build();
        }
        return Response
                .status(Response.Status.NOT_FOUND)
                .entity("UE non trouvée")
                .build();
    }
}