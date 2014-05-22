package ru.terra.tboard.web.controller;

import com.sun.jersey.api.core.HttpContext;
import org.apache.log4j.Logger;
import ru.terra.server.controller.AbstractResource;
import ru.terra.tboard.constants.FilePatchConstants;
import ru.terra.tboard.constants.URLConstants;
import sun.misc.IOUtils;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.CacheControl;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.EntityTag;
import javax.ws.rs.core.Response;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

/**
 * Date: 13.02.14
 * Time: 11:47
 */
@Path(URLConstants.Resources.RESOURCES)
public class ResourceController {
    private Logger logger = Logger.getLogger(this.getClass());

    @Path("/js/{path}")
    @GET
    public Response getJS(@Context HttpContext hc, @PathParam("path") String path) {
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        logger.info("Loading file '" + path + "'");

        try {
            stream.write(IOUtils.readFully(new FileInputStream(new File(FilePatchConstants.getResFolder() + "js/" + path)), -1, true));
        } catch (IOException e) {
            logger.error("Unable to read file", e);
            Response response = Response
                    .noContent()
                    .build();
            return response;
        }
        CacheControl cc = new CacheControl();
        cc.setNoTransform(true);
        cc.setMustRevalidate(false);
        cc.setNoCache(false);
        cc.setMaxAge(3600);
        EntityTag entityTag = new EntityTag(String.valueOf(path.hashCode()));

        Response response = Response
                .ok()
                .cacheControl(cc)
                .tag(entityTag)
                .entity(stream.toByteArray())
                .build();
        return response;
    }

    @Path("/js/images/{path}")
    @GET
    public Response getImages(@Context HttpContext hc, @PathParam("path") String path) {
        logger.info("Loading file '" + path + "'");
        return AbstractResource.getFile(FilePatchConstants.getResFolder() + "js/images/" + path);
    }

    @Path("/picz/{thread}/{img}")
    @GET
    public Response getPostImage(@Context HttpContext hc, @PathParam("thread") String thread, @PathParam("img") String img) {
        logger.info("Loading pic for thread" + thread + " '" + img + "'");
        try {
            return AbstractResource.getFile(FilePatchConstants.getResFolder() + "picz/" + thread + "/" + img);
        } catch (Exception e) {
            logger.error("Unable to load file");
        }
        return Response.noContent().build();

    }
}
