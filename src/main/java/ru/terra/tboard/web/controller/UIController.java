package ru.terra.tboard.web.controller;

import com.sun.jersey.api.core.HttpContext;
import ru.terra.server.controller.AbstractResource;
import ru.terra.tboard.constants.FilePatchConstants;
import ru.terra.tboard.constants.URLConstants;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;

/**
 * Date: 27.02.14
 * Time: 15:55
 */

@Path(URLConstants.Ui.UI)
public class UIController extends AbstractResource {

    @Path(URLConstants.Ui.MAIN)
    @GET
    public Response getMain(@Context HttpContext hc) {
        return returnHtmlFile(FilePatchConstants.getMainFolder() + "main.html");
    }

    @Path(URLConstants.Ui.BOARD)
    @GET
    public Response getBoard(@Context HttpContext hc, @PathParam("board") String board) {
        return returnHtmlFile(FilePatchConstants.getMainFolder() + "board.html");
    }

    @Path(URLConstants.Ui.THREAD)
    @GET
    public Response getThread(@Context HttpContext hc, @PathParam("board") String board, @PathParam("thread") String thread) {
        return returnHtmlFile(FilePatchConstants.getMainFolder() + "thread.html");
    }
}
