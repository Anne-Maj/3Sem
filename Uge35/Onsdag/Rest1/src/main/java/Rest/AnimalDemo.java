/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Rest;

import Entity.Animal;
import Model.AnimalNoDB;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.core.MediaType;

/**
 * REST Web Service
 *
 * @author 45222
 */
@Path("animals")
public class AnimalDemo {

    private Gson gson = new GsonBuilder().setPrettyPrinting().create();
    
    @Context
    private UriInfo context;

    /**
     * Creates a new instance of AnimalDemo
     */
    public AnimalDemo() {
    }

    /**
     * Retrieves representation of an instance of Rest.AnimalDemo
     * @return an instance of java.lang.String
     */
    //Exercise 1:
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String getJson() {
        //TODO return proper representation object
       return "Vuf... (Message from a dog)"; 
    }
    
    //Exercise 2. Json endpoint:
    @GET
    @Path("/animal_list")
    @Produces(MediaType.APPLICATION_JSON)
    public String animalList(){
      return  "[\"Dog\", \"Cat\", \"Mouse\", \"Bird\"]";  
    }
    
    //Exercise 3.
    @GET
    @Path("/animal")
    @Produces(MediaType.APPLICATION_JSON)
    public String returnObject(){
      AnimalNoDB animal = new AnimalNoDB ("And", "Rap");
    return new Gson().toJson(animal);
    }
  
    /**
     * PUT method for updating or creating an instance of AnimalDemo
     * @param content representation for the resource
     */
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public void putJson(String content) {
    }
    
 
}
