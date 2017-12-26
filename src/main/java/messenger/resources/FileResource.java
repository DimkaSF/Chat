package messenger.resources;

import com.google.gson.Gson;
import messenger.database.ConnectionDB;
import messenger.model.File;
import messenger.model.Message;
import messenger.service.FileService;
import messenger.service.MessageService;
import messenger.service.MessageService;

import javax.ws.rs.*;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.*;
import java.nio.file.*;
import java.sql.SQLException;
import java.util.UUID;
import static java.nio.file.StandardCopyOption.*;

/**
 * Created by Антон on 28.11.2017.
 */
@Path("/files")
public class FileResource {

    FileService flService = new FileService();
    MessageService msgService = new MessageService();
    Gson gson = new Gson();
    ConnectionDB con = new ConnectionDB();
///////////////////////////////////
//ОНО ДОЛЖНО РАБОТАТЬ, НО НЕТ :С //
///////////////////////////////////
//    @POST
//    @Path("/upload")
//    @Consumes(MediaType.MULTIPART_FORM_DATA)
//    public Response uploadFile(
//            @FormDataParam("file") InputStream fileInputString,
//            @FormDataParam("file") FormDataContentDisposition fileInputDetails
//    ) {
//        String folder = sc.getRealPath("\\resources\\files");
//
//        String filename = fileInputDetails.getFileName();
//        //Long fileId = dbi.saveFilename(filename);
//        //String message = "<a class='filename' href='http://localhost:8080/api/file/download/" + fileInputDetails.getFileName() + "'>";
//
//        try {
//            OutputStream out = new FileOutputStream(new java.io.File(folder + "\\" + fileInputDetails.getFileName()));
//            byte[] buffer = new byte[1024];
//            int bytes = 0;
//            while ((bytes = fileInputString.read(buffer)) != -1) {
//                out.write(buffer, 0, bytes);
//            }
//            out.flush();
//            out.close();
//            //message += filename + "</a>";
//            msgService.addMessage(3, new Message(3, "file", "smbd", fileInputDetails.getFileName()));
//            return Response.status(200).build();
//        } catch (IOException e) {
//            System.out.println("Unable to save file: " + folder + filename);
//            return Response.status(403).build();
//        }
//    }
//
//    @GET
//    @Path("/download/{filename}")
//    @Produces(MediaType.APPLICATION_OCTET_STREAM)
//    public Response downloadFilebyPath(@PathParam("filename") String filename) {
//        return download(filename);
//    }
//
//    private Response download(String filename) {
//        String folder = sc.getRealPath("\\resources\\files");
//        String fileLocation = folder + "\\" + filename;
//        Response response = null;
//
//        // Retrieve the file
//        java.io.File file = new java.io.File(fileLocation);
//        if (file.exists()) {
//            Response.ResponseBuilder builder = Response.ok(file);
//            builder.header("Content-Disposition", "attachment; filename=" + filename);
//            response = builder.build();
//        } else {
//            response = Response.status(404).
//                    entity("FILE NOT FOUND: " + fileLocation).
//                    type("text/plain").
//                    build();
//        }
//
//        return response;
//    }













    @GET
    @Produces("application/json")
    public Response getFiles() {
        String output=gson.toJson(flService.getAllFiles());
        return Response.status(200).entity(output).build();
    }

   /* @GET
    @Path("/{fileId}")
    @Produces("application/json")
    public Response test(@PathParam("fileId") long id) {
        String output=gson.toJson(flService.getFiles(id));
        return Response.status(200).entity(output).build();
    }*/
    @GET
    @Path("/send")

    public void sendFile(
            @QueryParam("file") String file,
            @QueryParam("message") String message,
            @QueryParam("name") String name ) {
        UUID uuid = UUID.randomUUID();
        UUID id = UUID.randomUUID();

//            flService.addFile(uuid, name);
        System.out.println(uuid.toString());
        System.out.println(file);
        //file = "D:\\Chatty\\src\\main\\resources\\files\\" + file;
        flService.addFile(id, new File(uuid, file));
        msgService.addMessage(3, new Message(3L, message, name, file));
        try {
            con.CreateTableFile();
            con.CreateFile(file);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        InputStream inStream = null;
        OutputStream outStream = null;
        try {

            java.io.File afile = new java.io.File("D:\\" + file);
            java.io.File bfile = new java.io.File("D:\\Chatty\\src\\main\\resources\\files\\" + file);

            inStream = new FileInputStream(afile);
            outStream = new FileOutputStream(bfile);

            byte[] buffer = new byte[1024];

            int length;
            //copy the file content in bytes
            while ((length = inStream.read(buffer)) > 0) {

                outStream.write(buffer, 0, length);

            }

            inStream.close();
            outStream.close();

            System.out.println("File is copied successful!");


        } catch (IOException e) {
            e.printStackTrace();
        }


 /*   @POST
    @Path("/send")
    @Consumes("application/json")
    public void uploadFile(
            @QueryParam("name") String name ) {


        //return Response.status(200).entity().build();
    }
*/
    }
}
