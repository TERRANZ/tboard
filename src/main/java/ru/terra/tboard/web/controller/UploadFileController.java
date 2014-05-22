package ru.terra.tboard.web.controller;

import com.sun.jersey.core.header.FormDataContentDisposition;
import com.sun.jersey.multipart.FormDataParam;
import ru.terra.server.dto.SimpleDataDTO;
import ru.terra.tboard.constants.FilePatchConstants;
import ru.terra.tboard.constants.URLConstants;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import java.io.*;
import java.util.Date;

/**
 * Date: 22.05.14
 * Time: 10:17
 */
@Path(URLConstants.Ui.UPLOAD)
public class UploadFileController {
    @POST
    @Path("/file")
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    public SimpleDataDTO<String> uploadFile(
            @FormDataParam("thread") String thread,
            @FormDataParam("file") InputStream uploadedInputStream,
            @FormDataParam("file") FormDataContentDisposition fileDetail) {

        String fileName = "";
        String uploadFileFileName = fileDetail.getFileName();
        fileName += String.valueOf(new Date().getTime());
        fileName += uploadFileFileName.substring(uploadFileFileName.lastIndexOf("."), uploadFileFileName.length());

        String uploadedFileLocation = FilePatchConstants.getPiczFolder() + "/" + thread + "/" + fileName;
        // save it
        File targetDir = new File(FilePatchConstants.getPiczFolder() + "/" + thread + "/");
        if (!targetDir.exists())
            targetDir.mkdirs();
        writeToFile(uploadedInputStream, uploadedFileLocation);
        return new SimpleDataDTO<>(fileName);

    }

    // save uploaded file to new location
    private void writeToFile(InputStream uploadedInputStream, String uploadedFileLocation) {
        try {
            OutputStream out;
            int read = 0;
            byte[] bytes = new byte[1024];

            out = new FileOutputStream(new File(uploadedFileLocation));
            while ((read = uploadedInputStream.read(bytes)) != -1) {
                out.write(bytes, 0, read);
            }
            out.flush();
            out.close();
        } catch (IOException e) {

            e.printStackTrace();
        }

    }
}
