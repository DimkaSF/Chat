package messenger.resources;

import com.google.gson.Gson;
import messenger.model.Message;
import messenger.service.MessageService;

import javax.ws.rs.*;
import javax.ws.rs.core.Response;

/**
 * Created by Антон on 14.11.2017.
 */
@Path("/messages")
public class MessageResource {


    MessageService msgService = new MessageService();
    Gson gson = new Gson();

    @GET
    @Produces("application/json")
    public Response getMessages() {
        String output=gson.toJson(msgService.getAllMessages());
        return Response.status(200).entity(output).build();
    }

    @GET
    @Path("/{messageId}")
    @Produces("application/json")
    public Response test(@PathParam("messageId") long id) {
        String output=gson.toJson(msgService.getMessage(id));
        return Response.status(200).entity(output).build();
    }
    @GET
    @Path("/send")
    public void sendMessage(
            @QueryParam("name") String name,
            @QueryParam("text") String text,
            @QueryParam("file") String file) {

        msgService.addMessage(3,new Message(3,text,name,file));

//        System.out.println("Имя:" + name);
//        System.out.println("Текст:" + text);
    }

    @GET
    @Path("/update")
    public void updateMessage(
            @QueryParam("id") Long id,
            @QueryParam("author") String author,
            @QueryParam("message") String message,
            @QueryParam("file") String file) {

        msgService.updateMessage(new Message(id,message,author,file));

//        System.out.println("Имя:" + name);
//        System.out.println("Текст:" + text);
    }

    @GET
    @Path("/removemessage")
    public void removeMessage(
            @QueryParam("id") Long id){
        msgService.removeMessage(id);
    }



}
